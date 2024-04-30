package com.erickWck.paymentservice.payment;

import java.math.BigDecimal;

public record PaymentDetails(


        BigDecimal amount,

        String type,

        String cardNumber,

        String expiryDate,

        String cvv,
        String cardholderName,

        Long orderId

) {
}