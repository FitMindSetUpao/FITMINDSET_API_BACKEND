package Grupo05.FitMindSet.dto;

import lombok.Data;

@Data
public class PurchaseItemCreateUpdateDTO {
    private Long recursoId;
    private Integer quantity;
    private Float price;
}
