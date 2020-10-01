package productservice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
class LoadDatabase {

    @Bean
    CommandLineRunner initDatabase(ProductRepository repository) {
        return args -> {
            log.info("Preloading " + repository.saveAndFlush(new Product("CREDIT_CARD", "Gem Visa", "v1")));
            log.info("Preloading " + repository.saveAndFlush(new Product("CREDIT_CARD", "28 Degrees", "v1")));
            log.info("Preloading " + repository.saveAndFlush(new Product("PERSONAL_LOAN", "MyFlexiPay", "v2")));
        };
    }
}
