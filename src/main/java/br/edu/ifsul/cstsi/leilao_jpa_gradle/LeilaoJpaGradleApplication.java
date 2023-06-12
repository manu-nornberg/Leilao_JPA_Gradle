package br.edu.ifsul.cstsi.leilao_jpa_gradle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LeilaoJpaGradleApplication {

    public static void main(String[] args) {

        SpringApplication.run(LeilaoJpaGradleApplication.class, args);
        HomeController.main(null);
    }
}
