package com.erickWck.paymentservice.domain;

import com.erickWck.paymentservice.payment.PaymentDetails;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;

import java.math.BigDecimal;
import java.time.Instant;

@Builder
public record Card(

        @Id
        Long paymentId,

        @NotBlank(message = "Insira o nome do responsavel pelo cartão.")
        String cardholderName,

        @NotNull(message = "Insira o valor total do produto.")
        @Positive(message = "O preço deve ser um número positovo.")
        BigDecimal amount,

        @NotBlank(message = "Insira o tipo do cartão credito ou debito")
        String type,

        @NotBlank(message = "Insira o numero do cartão")
        @Pattern(regexp = "^([0-9]{16})$", message = "O número do cartão está invalido.")
        String cardNumber,

        @NotBlank(message = "Insira a data de expiração")
        @Pattern(regexp = "^([0-9]{6})$", message = "A data de expiração deve conter só 6 numeros.")
        String expiryDate,

        @NotBlank(message = "Insira o código de segurançã do cartão")
        @Pattern(regexp = "^([0-9]{3})$", message = "O número do cartão está invalido.")
        String cvv,

        @NotNull(message = "Insira o número do pedido")
        Long orderId,

        PaymentStatus status,

        @CreatedDate
        Instant createdDate,

        @CreatedDate
        Instant lastModifiedDate,

        @Version
        int version

) {

    public static Card dtoFromEntity(PaymentDetails details) {
        return new Card(null, details.cardholderName(), details.amount(), details.type(), details.cardNumber(),
                details.expiryDate(), details.cvv(), details.orderId(), null, null, null, 0);
    }

    @Override
    public String toString() {
        return "Card{" +
                "paymentId=" + paymentId +
                ", cardholderName='" + cardholderName + '\'' +
                ", amount=" + amount +
                ", type='" + type + '\'' +
                ", cardNumber='" + cardNumber + '\'' +
                ", expiryDate='" + expiryDate + '\'' +
                ", cvv='" + cvv + '\'' +
                ", orderId=" + orderId +
                ", createdDate=" + createdDate +
                ", lastModifiedDate=" + lastModifiedDate +
                ", version=" + version +
                '}';
    }
}
