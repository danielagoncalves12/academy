package com.ctw.workstation.rackasset.control;

import com.ctw.workstation.rackasset.entity.RackAsset;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;

@ApplicationScoped
public class RackAssetService {

    public RackAsset create(RackAsset rack) {
        RackAsset.persist(rack);
        return rack;
    }

    public RackAsset getById(Long id) {
        return RackAsset.findById(id);
    }

    public boolean delete(Long id) {
        return RackAsset.deleteById(id);
    }

    public RackAsset update(Long id, RackAsset rackAsset) {

        RackAsset rackAssetFound = getById(id);

        if (rackAssetFound != null) {
            rackAssetFound.setAssetTag(rackAsset.getAssetTag());
        }

        return rackAssetFound;
    }

    public List<RackAsset> getRackAssets() {
        return RackAsset.listAll();
    }
}
