package Grupo05.FitMindSet.dto;

import lombok.Data;

import java.util.List;

@Data
public class PurchaseCreateUpdateDTO {
    private Float total;
    private Long customerId;
    private List<PurchaseItemCreateUpdateDTO> items;
}
