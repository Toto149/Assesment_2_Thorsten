package repo;

import model.Wanderer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WandererRepo extends MongoRepository<Wanderer, String> {
}
