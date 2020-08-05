package com.sq.producerhtttpserver;

import com.sq.producerhtttpserver.stream.ISenderService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableBinding(ISenderService.class)
public class ProducerHtttpServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProducerHtttpServerApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
