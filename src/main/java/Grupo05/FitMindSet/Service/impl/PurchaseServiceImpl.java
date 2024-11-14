package Grupo05.FitMindSet.Service.impl;

import Grupo05.FitMindSet.Exception.ResourceNotFoundException;
import Grupo05.FitMindSet.Repository.RecursoRepository;
import Grupo05.FitMindSet.Repository.UsuarioRepository;
import Grupo05.FitMindSet.Service.PurchaseService;
import Grupo05.FitMindSet.domain.Entity.Customer;
import Grupo05.FitMindSet.domain.Entity.Purchase;
import Grupo05.FitMindSet.domain.Entity.PurchaseItem;
import Grupo05.FitMindSet.domain.Entity.Recurso;
import Grupo05.FitMindSet.domain.Enum.PaymentStatus;
import Grupo05.FitMindSet.dto.PurchaseCreateUpdateDTO;
import Grupo05.FitMindSet.dto.PurchaseDTO;
import Grupo05.FitMindSet.dto.PurchaseReportDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PurchaseServiceImpl implements PurchaseService {
    private final PurchaseRepository purchaseRepository;
    private final UsuarioRepository usuarioRepository;
    private final RecursoRepository recursoRepository;
    private final PurchaseMapper purchaseMapper;

    @Override
    @Transactional
    public PurchaseDTO createPurchase(PurchaseCreateUpdateDTO purchaseDTO) {
        Purchase purchase = purchaseMapper.toPurchaseEntity(purchaseDTO);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Customer customer = null;
        if (authentication != null && !authentication.getPrincipal().equals("anonymousUser")) {
            customer = usuarioRepository.findByCorreo(authentication.getName())
                    .orElseThrow(ResourceNotFoundException::new).getCustomer();
        }
        purchase.setCustomer(customer);

        purchase.getItems().forEach(item -> {
            Recurso recurso = recursoRepository.findById(item.getRecurso().getId())
                    .orElseThrow(() -> new RuntimeException("Recurso no encontrado con el ID:" + item.getRecurso().getId()));
            item.setRecurso(recurso);
            item.setPurchase(purchase);
        });

        Float monto = purchase.getItems()
                .stream()
                .map(item -> item.getPrice() * item.getQuantity())
                .reduce(0f, Float::sum);
        purchase.setMonto(monto);
        purchase.setCreatedAt(LocalDateTime.now());
        purchase.setPaymentStatus(PaymentStatus.PENDING);
        purchase.setFechaCompra(Timestamp.valueOf(LocalDateTime.now()));

        Purchase savedPurchase = purchaseRepository.save(purchase);
        return purchaseMapper.toPurchaseDTO(savedPurchase);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PurchaseDTO> getPurchaseHistoryByUserId() {


        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Customer customer = null;

        if (authentication != null && !authentication.getPrincipal().equals("anonymousUser")) {
            customer= usuarioRepository.findByCorreo(authentication.getName())
                    .orElseThrow(ResourceNotFoundException::new).getCustomer();
        }

        return purchaseRepository.findByCustomerId(customer.getId()).stream()
                .map(purchaseMapper::toPurchaseDTO)
                .toList();
    }

    public List<PurchaseReportDTO> getPurchaseReportByDate() {
        List<PurchaseReportDTO> report = new ArrayList<>();
        List<Purchase> purchases = purchaseRepository.findAll();

        for (Purchase purchase : purchases) {
            for (PurchaseItem item : purchase.getItems()) {
                PurchaseReportDTO dto = new PurchaseReportDTO();
                dto.setCantidad(item.getQuantity());

                System.out.println("Fecha de compra: " + purchase.getFechaCompra());

                if (purchase.getFechaCompra() != null) {
                    dto.setConsultDate(purchase.getFechaCompra().toString());
                } else {
                    dto.setConsultDate("Fecha no disponible");
                }

                report.add(dto);
            }
        }

        return report;
    }

    /////

    @Override
    public List<PurchaseDTO> getAllPurchases() {
        return purchaseRepository.findAll()
                .stream()
                .map(purchaseMapper::toPurchaseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PurchaseDTO getPurchaseById(Integer id) {
        Purchase purchase = purchaseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Purchase not found"));
        return purchaseMapper.toPurchaseDTO(purchase);
    }

    @Override
    @Transactional
    public PurchaseDTO confirmPurchase(Integer purchaseId) {
        Purchase purchase = purchaseRepository.findById(purchaseId)
                .orElseThrow(() -> new ResourceNotFoundException("Purchase not found"));

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Customer customer = null;
        if (authentication != null && !authentication.getPrincipal().equals("anonymousUser")) {
            customer= usuarioRepository.findByCorreo(authentication.getName())
                    .orElseThrow(ResourceNotFoundException::new).getCustomer();
        }

        purchase.setPaymentStatus(PaymentStatus.PAID);


        Purchase updatedPurchase = purchaseRepository.save(purchase);

        return purchaseMapper.toPurchaseDTO(updatedPurchase);
    }

    @Transactional(readOnly = true)
    public List<Recurso> getPurchasedResourcesByCustomer() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Customer customer = null;

        if (authentication != null && !authentication.getPrincipal().equals("anonymousUser")) {
            customer = usuarioRepository.findByCorreo(authentication.getName())
                    .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado")).getCustomer();

            System.out.println("Usuario autenticado: " + customer.getId());
        }

        List<Recurso> purchasedResources = purchaseRepository.findPurchasedResourcesByCustomerId(customer.getId());

        System.out.println("Recursos comprados: " + purchasedResources.size());
        purchasedResources.forEach(r -> System.out.println("Recurso: " + r.getId()));

        return purchasedResources;
    }
}