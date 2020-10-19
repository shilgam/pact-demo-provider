package productservice;

import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
@Profile("test")
public class StateChangeController {

    private final ProductRepository repository;

    public StateChangeController(ProductRepository repository) {
        this.repository = repository;
    }

    @RequestMapping(value = "/pactStateChange", method = RequestMethod.POST)
    public Map providerState(@RequestBody Map body) {

        if (body.get("state").equals("products exist")) {
            repository.saveAndFlush(new Product("CREDIT_CARD", "Visa", "v1"));
            repository.saveAndFlush(new Product("PERSONAL_LOAN", "MyFlexiPay", "v2"));
        }
        else if (body.get("state").equals("no products exist")) {
            repository.deleteAllInBatch();
        }
        else if (body.get("state").equals("product with ID 10 exists")) {
            for (int counter = 0; counter < 7; counter++) {
                repository.saveAndFlush(new Product("CREDIT_CARD", "Visa_" + counter, "v1"));
            }
        }
        else if (body.get("state").equals("product with ID 11 does not exist")) {
        }
        return Collections.emptyMap();
    }
}
