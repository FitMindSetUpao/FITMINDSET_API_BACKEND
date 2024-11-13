package Grupo05.FitMindSet.Service.Impl;

import Grupo05.FitMindSet.Exception.ResourceNotFoundException;
import Grupo05.FitMindSet.Exception.SubscriptionInactiveException;
import Grupo05.FitMindSet.Exception.UnauthorizedException;
import Grupo05.FitMindSet.Integration.Service.PaypalService;
import Grupo05.FitMindSet.Integration.dto.ApplicationContext;
import Grupo05.FitMindSet.Repository.PlanRepository;
import Grupo05.FitMindSet.Repository.RecursoRepository;
import Grupo05.FitMindSet.Repository.SuscripcionRepository;
import Grupo05.FitMindSet.Service.SuscripcionService;
import Grupo05.FitMindSet.domain.Entity.*;
import Grupo05.FitMindSet.domain.Enum.SubscriptionStatus;
import Grupo05.FitMindSet.domain.Enum.TipoSuscripcion;
import Grupo05.FitMindSet.dto.*;
import Grupo05.FitMindSet.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SuscripcionServiceImpl implements SuscripcionService {

    private final PaypalService paypalService;
    private final SuscripcionRepository suscripcionRepository;
    private final UsuarioRepository usuarioRepository;
    private final PlanRepository planRepository;
    private final RecursoRepository recursoRepository;


    public SuscripcionServiceImpl(PaypalService paypalService, SuscripcionRepository suscripcionRepository, UsuarioRepository usuarioRepository, PlanRepository planRepository, RecursoRepository recursoRepository) {
        this.paypalService = paypalService;
        this.suscripcionRepository = suscripcionRepository;
        this.usuarioRepository = usuarioRepository;
        this.planRepository = planRepository;
        this.recursoRepository = recursoRepository;
    }

    @Override
    public SubscriptionResponse createSubscription(String paypalPlanId, String returnUrl, String cancelUrl) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication.getPrincipal().equals("anonymousUser")) {
            throw new RuntimeException("Usuario no autenticado");
        }
        String correoUsuario = authentication.getName();
        Usuario usuario = usuarioRepository.findByCorreo(correoUsuario)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));
        Customer customer = Optional.ofNullable(usuario.getCustomer())
                .orElseThrow(() -> new RuntimeException("El usuario no tiene un 'Customer' asociado"));
        Plan planSeleccionado = planRepository.findByPaypalPlanId(paypalPlanId)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró el plan con el ID proporcionado"));

        Suscripcion suscripcion = new Suscripcion();
        suscripcion.setCustomer(customer);
        suscripcion.setPlan(planSeleccionado);
        suscripcion.setPrecio(planSeleccionado.getPrecio());
        suscripcion.setPermiteDescargar(planSeleccionado.isPermiteDescargar());
        String tipoSuscripcionStr = String.valueOf(planSeleccionado.getTipoSuscripcion());

        try {
            TipoSuscripcion tipoSuscripcion = TipoSuscripcion.valueOf(tipoSuscripcionStr);
            switch (tipoSuscripcion) {
                case FREE:
                    manejarSuscripcionFree(suscripcion);
                    break;
                case BASICO:
                    manejarSuscripcionPagoBasico(suscripcion, returnUrl, cancelUrl);
                    break;
                case PREMIUM:
                    manejarSuscripcionPagoPremium(suscripcion, returnUrl, cancelUrl);
                    break;
                default:
                    throw new RuntimeException("Tipo de suscripción desconocido: " + tipoSuscripcionStr);
            }
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Tipo de suscripción inválido: " + tipoSuscripcionStr);
        }
        try {
            suscripcionRepository.save(suscripcion);
        } catch (Exception e) {
            throw new RuntimeException("Error al guardar la suscripción: " + e.getMessage(), e);
        }
        return SubscriptionResponse.builder()
                .id(suscripcion.getId().toString())
                .status("PENDING")
                .build();
    }

    private void manejarSuscripcionFree(Suscripcion suscripcion) {
        LocalDateTime fechaInicio = LocalDateTime.now();
        suscripcion.setFechainicio(fechaInicio);
        suscripcion.setFechafin(fechaInicio.plusDays(15)); // Duración de prueba de 15 días
        suscripcion.setActiva(true);
        suscripcion.setStatus(SubscriptionStatus.ACTIVE.name());
    }

    private void manejarSuscripcionPagoBasico(Suscripcion suscripcion, String returnUrl, String cancelUrl) throws RuntimeException {
        LocalDateTime fechaInicio = LocalDateTime.now();
        suscripcion.setFechainicio(fechaInicio);
        suscripcion.setFechafin(fechaInicio.plusMonths(1)); // Duración de un mes
        suscripcion.setActiva(false);

        SuscripcionDTO suscripcionDTO = new SuscripcionDTO();
        suscripcionDTO.setPaypalPlanId(paypalService.getAccessToken());
        suscripcionDTO.setCustomerId(suscripcion.getCustomer().getId());

        SubscriptionRequest request = new SubscriptionRequest();
        request.setPlanId(suscripcion.getPlan().getPaypalPlanId());
        request.setApplicationContext(new ApplicationContext("FITMINDSET", returnUrl, cancelUrl));

        try {
            SubscriptionResponse paypalResponse = paypalService.createSubscription(suscripcionDTO, returnUrl, cancelUrl);

            if (paypalResponse == null || paypalResponse.getId() == null) {
                throw new RuntimeException("Error al crear la suscripción en PayPal: respuesta inválida.");
            }

            suscripcion.setOrderId(paypalResponse.getId());
            suscripcion.setStatus(SubscriptionStatus.PENDING.name());
        } catch (Exception e) {
            throw new RuntimeException("Error al procesar la suscripción de pago: " + e.getMessage(), e);
        }
    }


    private void manejarSuscripcionPagoPremium(Suscripcion suscripcion, String returnUrl, String cancelUrl) {
        LocalDateTime fechaInicio = LocalDateTime.now();
        suscripcion.setFechainicio(fechaInicio);
        suscripcion.setFechafin(fechaInicio.plusMonths(6)); // Duración de seis meses
        suscripcion.setActiva(false);

        SuscripcionDTO suscripcionDTO = new SuscripcionDTO();
        suscripcionDTO.setPaypalPlanId(suscripcionDTO.getPaypalPlanId());
        suscripcionDTO.setCustomerId(suscripcion.getCustomer().getId());

        SubscriptionRequest request = new SubscriptionRequest();
        request.setPlanId(suscripcion.getPlan().getPaypalPlanId());
        request.setApplicationContext(new ApplicationContext("FITMINDSET", returnUrl, cancelUrl));
        try {
            SubscriptionResponse paypalResponse = paypalService.createSubscription(suscripcionDTO, returnUrl, cancelUrl);
            if (paypalResponse == null || paypalResponse.getId() == null) {
                throw new RuntimeException("Error al crear la suscripción en PayPal: respuesta inválida.");
            }
            suscripcion.setOrderId(paypalResponse.getId());
            suscripcion.setStatus(SubscriptionStatus.PENDING.name());
        } catch (Exception e) {
            throw new RuntimeException("Error al procesar la suscripción de pago para el plan PREMIUM: " + e.getMessage(), e);
        }
    }

    @Override
    public SubscriptionCaptureResponse captureSubscription(String subscriptionId) {
        if (subscriptionId == null || subscriptionId.isEmpty()) {
            throw new IllegalArgumentException("El ID de la suscripción no puede estar vacío.");
        }

        try {
            SubscriptionCaptureResponse response = paypalService.captureSubscription(subscriptionId);
            if (response == null) {
                throw new RuntimeException("No se recibió respuesta de PayPal al intentar capturar la suscripción.");
            }
            return response;
        } catch (WebClientResponseException e) {
            String errorMessage = String.format("Error al capturar la suscripción con ID %s: %s", subscriptionId, e.getResponseBodyAsString());
            throw new RuntimeException(errorMessage, e);
        } catch (Exception e) {
            String errorMessage = String.format("Error inesperado al capturar la suscripción con ID %s.", subscriptionId);
            throw new RuntimeException(errorMessage, e);
        }
    }
}