package uk.co.breadhub.mcapi.service;

import uk.co.breadhub.mcapi.model.PrevNames;

import java.util.List;

public interface PrevNamesService {

    PrevNames save(PrevNames prevnames);

    List<PrevNames> findByUuid(String id);

    void deleteByUuid(String id);

    List<PrevNames> findByName(String id);

    Iterable<PrevNames> findAllNames();

    long count();

}