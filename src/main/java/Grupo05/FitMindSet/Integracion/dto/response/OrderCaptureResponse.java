package Grupo05.FitMindSet.Integracion.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.List;

@Data
public class OrderCaptureResponse {
    private String status;
    @JsonProperty("purchase_units")
    private List<PurchaseUnit> purchaseUnits;
}