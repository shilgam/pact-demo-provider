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
            log.info("Preloading " + repository.save(new Product("Bilbo Baggins", "burglar")));
            log.info("Preloading " + repository.save(new Product("Frodo Baggins", "thief")));
        };
    }
}
