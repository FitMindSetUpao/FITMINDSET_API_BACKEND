package Grupo05.FitMindSet.Service;

import Grupo05.FitMindSet.dto.AccessResponse;
import Grupo05.FitMindSet.dto.SubscriptionCaptureResponse;
import Grupo05.FitMindSet.dto.SubscriptionResponse;
import Grupo05.FitMindSet.dto.SuscripcionDTO;

public interface SuscripcionService {

    SubscriptionResponse createSubscription(String paypalPlanId, String returnUrl, String cancelUrl);
    SubscriptionCaptureResponse captureSubscription(String subscriptionId);
    AccessResponse accessSubscriptionBenefits(Long suscripcionId);
    boolean existePlan(Long planId);
}