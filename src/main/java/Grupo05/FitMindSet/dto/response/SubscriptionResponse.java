package Grupo05.FitMindSet.dto.response;

import Grupo05.FitMindSet.domain.Enum.BillingCycle;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Builder
@Data
public class SubscriptionResponse {
    private String id;
    private String status;
    private Subscriber subscriber;
    private String planId;

    private LocalDateTime startTime;
    private LocalDateTime updateTime;

    private Date nextBillingTime;

    private String quantity;
    private BillingCycle billingCycle;

    @Data
    public static class Subscriber {
        private String nombre;
        private String correo;
    }
}