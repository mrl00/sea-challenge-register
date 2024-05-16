package com.sea.challenge.register.services.consumer.viacep;

import com.sea.challenge.register.models.dtos.viacep.ViaCepDTO;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class ViaCepConsumerService {

    private WebClient webClient(String cep) {
        String uri = String.format("https://viacep.com.br/ws/%s/json", cep);
        return WebClient.create(uri);
    }

    public ViaCepDTO getCepDTO(String cep) {
        Mono<ViaCepDTO> mono = webClient(cep)
                .method(HttpMethod.GET)
                .retrieve()
                .bodyToMono(ViaCepDTO.class);

        return mono.block();
    }
}
