package kz.kaznitu.lessons.repositories;

import kz.kaznitu.lessons.models.Client;
import org.springframework.data.repository.CrudRepository;

public interface ClientRepository extends CrudRepository<Client, Integer> {
}
