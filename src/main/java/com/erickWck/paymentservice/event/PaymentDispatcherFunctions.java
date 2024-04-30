package com.erickWck.paymentservice.event;


import com.erickWck.paymentservice.domain.Card;
import com.erickWck.paymentservice.domain.PaymentSerivce;
import com.erickWck.paymentservice.payment.PaymentDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.Consumer;

@Configuration
public class PaymentDispatcherFunctions {

    private static final Logger log = LoggerFactory.getLogger(PaymentDispatcherFunctions.class);


    @Bean
    public Consumer<Flux<PaymentDetails>> dispatcherPedidoService(PaymentSerivce paymentSerivce) {

        return cardFlux ->
                paymentSerivce.addCreditCard(cardFlux)
                        .doOnNext(card -> log.info("Card com Identificador: {} foi despachado.", card.paymentId()))
                        .onErrorResume(throwable -> {
                            log.error("Erro ao processar cartão: {}", throwable.getMessage());
                            return Mono.empty();
                        })
                        .subscribe();
    }
}


