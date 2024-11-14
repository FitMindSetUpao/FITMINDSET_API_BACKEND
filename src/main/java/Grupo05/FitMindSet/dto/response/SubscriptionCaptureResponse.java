package Grupo05.FitMindSet.dto.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SubscriptionCaptureResponse {
    private String id;
    private String status;
    private String subscriptionId;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private Amount amount;
    private String invoiceId;


    @Data
    public static class Amount {
        private String currencyCode;
        private String value;

    }
}