package Grupo05.FitMindSet.Service;

import Grupo05.FitMindSet.Controller.SuscripcionController;
import Grupo05.FitMindSet.Integracion.dto.Amount;
import Grupo05.FitMindSet.Integracion.dto.ApplicationContext;
import Grupo05.FitMindSet.Integracion.dto.PurchaseUnit;
import Grupo05.FitMindSet.Integracion.dto.request.OrderRequest;
import Grupo05.FitMindSet.Integracion.dto.response.OrderCaptureResponse;
import Grupo05.FitMindSet.Integracion.dto.response.OrderResponse;
import Grupo05.FitMindSet.Integracion.dto.response.TokenResponse;
import Grupo05.FitMindSet.domain.Entity.Purchase;
import jakarta.annotation.PostConstruct;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Base64;
import java.util.Collections;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class PaypalService {
    @Value("${paypal.client-id}")
    private String clientId;
    @Value("${paypal.client-secret}")
    private String clientSecret;
    @Value("${paypal.api-base}")
    private String apiBase;

    @NonNull
    private final PurchaseRepository purchaseRepository;

    @NonNull
    private final SuscripcionRepository suscripcionRepository;

    @NonNull
    private final PlanRepository planRepository;

    private WebClient paypalClient;
    private static final Logger logger = LoggerFactory.getLogger(SuscripcionController.class);

    @PostConstruct
    public void init() {
        paypalClient = WebClient.builder()
                .baseUrl(apiBase)
                .build();
    }

    public String getAccessToken() {
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "client_credentials");

        return Objects.requireNonNull(
                        paypalClient.post()
                                .uri("/v1/oauth2/token")
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                                .header(HttpHeaders.AUTHORIZATION, "Basic " + Base64.getEncoder()
                                        .encodeToString((clientId + ":" + clientSecret).getBytes()))
                                .bodyValue(body) // Cambiar a bodyValue
                                .retrieve()
                                .bodyToMono(TokenResponse.class)
                                .block())
                .getAccessToken();
    }

    public OrderResponse createOrder(Integer purchaseId, String returnUrl, String cancelUrl) {
        Purchase purchase = purchaseRepository.findById(purchaseId)
                .orElseThrow(ResourceNotFoundException::new);

        if (purchase.getMonto() == null || purchase.getMonto() <= 0) {
            throw new IllegalArgumentException("El monto de la compra no puede ser nulo o menor que cero.");
        }

        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setIntent("CAPTURE");

        Amount amount = new Amount();
        amount.setCurrencyCode("USD");
        amount.setValue(purchase.getMonto().toString());

        PurchaseUnit purchaseUnit = new PurchaseUnit();
        purchaseUnit.setReferenceId(purchase.getId().toString());
        purchaseUnit.setAmount(amount);

        orderRequest.setPurchaseUnits(Collections.singletonList(purchaseUnit));

        ApplicationContext applicationContext = ApplicationContext.builder()
                .brandName("FITMINDSET")
                .returnURL(returnUrl)
                .cancelURL(cancelUrl)
                .build();
        orderRequest.setApplicationContext(applicationContext);

        try {
            OrderResponse orderResponse = paypalClient.post()
                    .uri("/v2/checkout/orders")
                    .contentType(MediaType.APPLICATION_JSON)
                    .header(HttpHeaders.AUTHORIZATION, "Bearer " + getAccessToken())
                    .bodyValue(orderRequest) // Cambiar a bodyValue
                    .retrieve()
                    .bodyToMono(OrderResponse.class)
                    .block();

            purchase.setOrderId(orderResponse.getId());
            purchaseRepository.save(purchase);

            return orderResponse;
        } catch (Exception e) {
            throw new RuntimeException("Error al crear la orden en PayPal: " + e.getMessage(), e);
        }
    }

    public OrderCaptureResponse captureOrder(String orderId) {
        return paypalClient.post()
                .uri("/v2/checkout/orders/{order_id}/capture", orderId)
                .contentType(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + getAccessToken())
                .retrieve()
                .bodyToMono(OrderCaptureResponse.class)
                .block();
    }
}