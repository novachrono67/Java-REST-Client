package com.licencepro.stockclient;

import org.junit.jupiter.api.Assertions;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main
{
    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException
    {
        /**
         * TD1: premier appel Rest dans un programme
         * A partir de l’exemple de fonction donnée dans le cours, faites votre premier appel sur l’url
         * https://api.github.com/orgs/octokit/repos et analysez le résultat.
         */
        HttpClient client = HttpClient.newBuilder().version(HttpClient.Version.HTTP_1_1).build();
        HttpRequest request = HttpRequest.newBuilder(new URI("https://api.github.com/orgs/octokit/repos"))
                .GET()
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("Status Code: " + response.statusCode() + "\n");


        /**
         *TD2 : séparer les entêtes du corps de la réponse
         * A partir de l’exemple précédent, trouvez un moyen de séparer
         * les entêtes de la réponse et le corps en lui-même et d’inclure
         * cela dans votre librairie perso pour gérer les appels ReST.
         */
        System.out.println("Header: " + response.headers() + "\n");
        System.out.println("Body: " + response.body() + "\n");


        /**
         * TD3: parser les entêtes
         * Toujours à partir de l’exemple précédent, parser les entêtes
         * HTTP pour ne plus les avoir sous la forme d’une chaîne de
         * caractères mais sous la forme d’un tableau associatif.
         */
        System.out.println("Parsed Header:");
        Map<String, List<String>> map = response.headers().map();
        for(Map.Entry<String, List<String>> entry : map.entrySet())
        {
            System.out.println(entry.getKey() + " --> " + entry.getValue());
        }


        /**
         * TD4: créer votre serveur REST
         * Créer un micro serveur rest avec un endpoint :
         * GET /api/utilisateur/{id}
         * Ce endpoint envoie des informations en français (information au
         * choix) dans un json
         */
        WebClient webClient = WebClient.builder().build();
        WebClientStockClient webClientStockClient = new WebClientStockClient(webClient);
        String STOCK_NAME = "Apple";

        ArrayList<String> stockPriceList = new ArrayList();
        Flux<StockPrice> prices = webClientStockClient.pricesFor(STOCK_NAME);
        Flux<StockPrice> stockPriceFlux = prices.take(5);
        for(StockPrice sp : stockPriceFlux.toIterable())
        {
            stockPriceList.add(sp.toString());
        }
        System.out.println(stockPriceList.toString());
    }
}
