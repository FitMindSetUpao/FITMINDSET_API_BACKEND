package Grupo05.FitMindSet.Service;

import Grupo05.FitMindSet.domain.Entity.Recurso;
import Grupo05.FitMindSet.dto.PurchaseCreateUpdateDTO;
import Grupo05.FitMindSet.dto.PurchaseDTO;
import Grupo05.FitMindSet.dto.PurchaseReportDTO;

import java.util.List;

public interface PurchaseService {
    PurchaseDTO createPurchase(PurchaseCreateUpdateDTO purchaseDTO);
    List<PurchaseDTO> getPurchaseHistoryByUserId();
    List<PurchaseReportDTO> getPurchaseReportByDate();
    List<PurchaseDTO> getAllPurchases();
    PurchaseDTO confirmPurchase(Integer purchaseId);
    PurchaseDTO getPurchaseById(Integer id);
    List<Recurso>getPurchasedResourcesByCustomer();
}