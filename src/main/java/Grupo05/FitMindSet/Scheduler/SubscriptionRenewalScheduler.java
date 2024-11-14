package Grupo05.FitMindSet.Scheduler;

import Grupo05.FitMindSet.Repository.SuscripcionRepository;
import Grupo05.FitMindSet.Service.SuscripcionService;
import Grupo05.FitMindSet.domain.Entity.Suscripcion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class SubscriptionRenewalScheduler {

    private static final Logger logger = LoggerFactory.getLogger(SubscriptionRenewalScheduler.class);

    private final SuscripcionService suscripcionService;
    private final SuscripcionRepository suscripcionRepository;

    public SubscriptionRenewalScheduler(SuscripcionService suscripcionService, SuscripcionRepository suscripcionRepository) {
        this.suscripcionService = suscripcionService;
        this.suscripcionRepository = suscripcionRepository;
    }

    @Scheduled(cron = "0 0 1 * * ?")
    public void renewSubscriptions() {
        LocalDateTime now = LocalDateTime.now();
        List<Suscripcion> suscripcionesPorRenovar = suscripcionRepository.findByExpiryDateBefore(now.plusDays(1));

        for (Suscripcion suscripcion : suscripcionesPorRenovar) {
            try {
                if (!"ACTIVE".equals(suscripcion.getStatus())) {
                    suscripcionService.captureSubscription(suscripcion.getOrderId());

                    suscripcion.setFechainicio(LocalDateTime.now());
                    suscripcion.setFechafin(LocalDateTime.now().plusMonths(1)); // O la duración correspondiente
                    suscripcion.setStatus("ACTIVE");
                    suscripcionRepository.save(suscripcion);
                    logger.info("Suscripción renovada con éxito: " + suscripcion.getId());
                } else {
                    logger.info("Suscripción ya activa: " + suscripcion.getId());
                }
            } catch (Exception e) {
                logger.error("Error al renovar la suscripción: " + suscripcion.getId(), e);
            }
        }
    }
}