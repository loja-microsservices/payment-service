package com.erickWck.paymentservice.domain;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Mono;

public interface PaymentRepository extends R2dbcRepository<Card, Long> {

    Mono<Card> findByCardNumber(String cardNumber);

}
