package kz.kaznitu.lessons.repositories;

import kz.kaznitu.lessons.models.Collection;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CollectionRepository extends CrudRepository<Collection, Integer> {
    List<Collection> findByType (String type);
}
