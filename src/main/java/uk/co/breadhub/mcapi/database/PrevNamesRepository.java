package uk.co.breadhub.mcapi.database;

import org.springframework.data.repository.CrudRepository;
import uk.co.breadhub.mcapi.model.PrevNames;

import java.util.List;

public interface PrevNamesRepository extends CrudRepository<PrevNames, Long> {
    List<PrevNames> findByName(String name);

    List<PrevNames> findUserByUuid(String uuid);
}