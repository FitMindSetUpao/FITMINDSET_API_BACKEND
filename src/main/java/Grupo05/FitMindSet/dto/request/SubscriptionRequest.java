package Grupo05.FitMindSet.dto.request;

import Grupo05.FitMindSet.Integracion.dto.ApplicationContext;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SubscriptionRequest {
    @JsonProperty("plan_id")
    private String planId;

    @JsonProperty("application_context")
    private ApplicationContext applicationContext;
}