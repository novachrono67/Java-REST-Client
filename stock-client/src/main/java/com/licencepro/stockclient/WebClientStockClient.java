package com.licencepro.stockclient;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.io.IOException;
import java.time.Duration;

@Log4j2
public class WebClientStockClient
{
    private WebClient webClient;

    public WebClientStockClient(WebClient webClient)
    {
        this.webClient = webClient;
    }

    public Flux<StockPrice> pricesFor(String id)
    {
        return webClient.get()
                .uri("http://localhost:8090/API/utilisateur/{id}", id)
                .retrieve()
                .bodyToFlux(StockPrice.class)
                .retryBackoff(5, Duration.ofSeconds(1), Duration.ofSeconds(20))
                .doOnError(IOException.class, e -> System.out.println(e.getMessage()));
    }
}
