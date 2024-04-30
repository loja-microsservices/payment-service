package com.erickWck.paymentservice.domain;

import com.erickWck.paymentservice.exception.LimiteExcededException;
import com.erickWck.paymentservice.payment.CardMapper;
import com.erickWck.paymentservice.payment.PaymentDetails;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

@Service
public class PaymentSerivce {

    private final BigDecimal limite = new BigDecimal("8500.00");

    private final PaymentRepository paymentRepository;

    public PaymentSerivce(PaymentRepository cardRepository) {
        this.paymentRepository = cardRepository;
    }

    public Flux<Card> addCreditCard(Flux<PaymentDetails> paymentDetailsFlux) {
        return paymentDetailsFlux
                .flatMap(obj -> buildSaveCard(CardMapper.dtoFromEntity(obj)))
                .switchIfEmpty(Mono.error(new LimiteExcededException("Limite Insuficiente para fazer a compra.")));

    }

    private Mono<Card> buildSaveCard(Card card) {

        if (card.amount().compareTo(limite) > 0) {
            return Mono.error(new LimiteExcededException("Limite Insuficiente para fazer a compra."));

        }
        return paymentRepository.save(getBuild(card));
    }

    private static Card getBuild(Card card) {
        return Card.builder().cardholderName(card.cardholderName())
                .amount(card.amount())
                .type(card.type())
                .cardNumber(card.cardNumber())
                .expiryDate(card.expiryDate())
                .cvv(card.cvv())
                .status(PaymentStatus.ACCEPTED)
                .orderId(card.orderId())
                .createdDate(card.createdDate())
                .build();
    }


}
