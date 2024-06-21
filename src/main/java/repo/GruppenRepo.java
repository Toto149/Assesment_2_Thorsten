package repo;

import model.Gruppe;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GruppenRepo extends MongoRepository<Gruppe, String> {
}
