package uk.co.breadhub.mcapi.database;

import uk.co.breadhub.mcapi.model.PrevNames;
import org.springframework.data.repository.CrudRepository;
import uk.co.breadhub.mcapi.model.User;

import java.util.List;
import java.util.Optional;

public interface PrevNamesRepository extends CrudRepository<PrevNames, Long> {
    List<PrevNames> findByName(String name);

    List<PrevNames> findUserByUuid(String uuid);
}