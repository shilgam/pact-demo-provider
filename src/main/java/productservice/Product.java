package productservice;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
class Product {
    private @Id @GeneratedValue Long id;
    private String type;
    private String name;
    private String version;

    Product() {}

    Product(String type, String name, String version) {
        this.type = type;
        this.name = name;
        this.version = version;
    }
}
