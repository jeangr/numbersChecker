package it.gianni.numberschecker.service;

import it.gianni.numberschecker.entity.SouthAfricanMobileNumberEntity;
import it.gianni.numberschecker.mapper.EntityOmMapper;
import it.gianni.numberschecker.om.SouthAfricanMobileNumberOM;
import it.gianni.numberschecker.repository.SouthAfricanMobileNumberRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class H2DatabaseService implements DatabaseService{

    private final SouthAfricanMobileNumberRepository repository;

    private final EntityOmMapper mapper;

    public H2DatabaseService(SouthAfricanMobileNumberRepository repository, EntityOmMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public void saveAll(List<SouthAfricanMobileNumberOM> oms) {
        repository.saveAll(mapper.omsToEntities(oms));
    }

    @Override
    public List<SouthAfricanMobileNumberOM> getAll() {
        final List<SouthAfricanMobileNumberEntity> entities = repository.findAll();
        return mapper.entitiesToOms(entities);
    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
    }
}
