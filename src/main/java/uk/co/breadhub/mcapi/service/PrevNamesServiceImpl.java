package uk.co.breadhub.mcapi.service;

import uk.co.breadhub.mcapi.model.PrevNames;
import uk.co.breadhub.mcapi.database.PrevNamesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.co.breadhub.mcapi.model.User;

import java.util.List;
import java.util.Optional;

@Service
public class PrevNamesServiceImpl implements PrevNamesService {

    @Autowired
    private PrevNamesRepository prevnamesRepository;

    @Override
    public PrevNames save(PrevNames prevnames) {
        return prevnamesRepository.save(prevnames);
    }

    @Override
    public List<PrevNames> findByUuid(String uuid) {
        return prevnamesRepository.findUserByUuid(uuid);
    }

    @Override
    public List<PrevNames> findByName(String name) {
        return prevnamesRepository.findByName(name);
    }


    @Override
    public Iterable<PrevNames> findAllNames() {
        return prevnamesRepository.findAll();
    }

    @Override
    public long count() {
        return prevnamesRepository.count();
    }

}