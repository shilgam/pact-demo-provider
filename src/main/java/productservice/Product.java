package productservice;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
class Product {
    private @Id @GeneratedValue Long id;
    private String name;
    private String role;

    Product() {}

    Product(String name, String role) {
        this.name = name;
        this.role = role;
    }
}
