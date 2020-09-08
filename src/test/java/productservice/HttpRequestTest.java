package productservice;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class HttpRequestTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void getProductsList() throws Exception {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/products",
                String.class)).contains(
                        "[{\"id\":1,\"name\":\"Bilbo Baggins\",\"role\":\"burglar\"},{\"id\":2,\"name\":\"Frodo Baggins\",\"role\":\"thief\"}]"
        );
    }

    @Test
    public void getProduct() throws Exception {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/products/1",
                String.class)).contains("{\"id\":1,\"name\":\"Bilbo Baggins\",\"role\":\"burglar\"}"
        );
    }
}
