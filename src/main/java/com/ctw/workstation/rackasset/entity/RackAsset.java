package com.ctw.workstation.rackasset.entity;

import com.ctw.workstation.rack.entity.Rack;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;

@Entity
@Table(name = "T_RACK_ASSET")
public class RackAsset extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="rackAssetIdGenerator")
    @SequenceGenerator(name="rackAssetIdGenerator", sequenceName="SEQ_RACK_ASSET_ID", allocationSize=1)
    private Long id;

    @Column(name="RACK_ID")
    private Long rack_id;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="RACK_ID", updatable = false, insertable = false, nullable = false)
    private Rack rack;

    @Column(name="ASSET_TAG", nullable = false, length = 10)
    private String assetTag;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRack_id() {
        return rack_id;
    }

    public void setRack_id(Long rack_id) {
        this.rack_id = rack_id;
    }

    public Rack getRack() {
        return rack;
    }

    public void setRack(Rack rack) {
        this.rack = rack;
    }

    public String getAssetTag() {
        return assetTag;
    }

    public void setAssetTag(String assetTag) {
        this.assetTag = assetTag;
    }
}
