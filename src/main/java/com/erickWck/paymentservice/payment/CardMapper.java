package com.erickWck.paymentservice.payment;

import com.erickWck.paymentservice.domain.Card;

public record CardMapper() {

    public static Card dtoFromEntity(PaymentDetails details) {
        return new Card(null, details.cardholderName(), details.amount(), details.type(), details.cardNumber(),
                details.expiryDate(), details.cvv(), details.orderId(), null, null, null, 0);
    }
}
