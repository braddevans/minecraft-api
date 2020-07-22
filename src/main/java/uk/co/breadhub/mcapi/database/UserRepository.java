package uk.co.breadhub.mcapi.database;

import org.springframework.data.repository.CrudRepository;
import uk.co.breadhub.mcapi.model.User;

public interface UserRepository extends CrudRepository<User, Long> {
}