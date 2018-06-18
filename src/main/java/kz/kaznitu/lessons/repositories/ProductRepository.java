package kz.kaznitu.lessons.repositories;

import kz.kaznitu.lessons.models.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Integer> {
    List<Product> findByName (String name);
}
