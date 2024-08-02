package com.ctw.workstation.rack.control;

import com.ctw.workstation.rack.entity.Rack;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@ApplicationScoped
public class RackService {

    @Inject
    RackRepository repository;

    public Rack create(Rack rack) {
        repository.persist(rack);
        repository.flush();
        return rack;
    }

    public Rack getById(Long id) {
        return repository.findById(id);
    }

    public boolean delete(Long id) {
        return repository.deleteById(id);
    }

    public Rack update(Long id, Rack rack) {

        Rack rackFound = getById(id);

        if (rackFound != null) {
            rackFound.setTeam(rack.getTeam());
            rackFound.setDefaultLocation(rack.getDefaultLocation());
            rackFound.setStatus(rack.getStatus());
            rackFound.setSerialNumber(rack.getSerialNumber());
            rackFound.setModifiedAt(LocalDateTime.now());
        }

        return rackFound;
    }

    public List<Rack> getRacksByStatus(String status) {
        return repository.getRacksByStatus(status);
    }

    public List<Rack> getRacks() {
        return repository.listAll();
    }
}