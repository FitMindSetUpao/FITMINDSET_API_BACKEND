package Grupo05.FitMindSet.Integracion.dto.response;

import Grupo05.FitMindSet.Integracion.dto.Link;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class OrderResponse {
    private String id;
    private String status;
    @JsonProperty("payment_source")
    private PaymentSource paymentSource;
    private List<Link> links;
}