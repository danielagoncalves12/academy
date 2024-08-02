package com.ctw.workstation.rack.entity;

import com.ctw.workstation.team.entity.Team;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "T_RACK")
public class Rack {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="rackIdGenerator")
    @SequenceGenerator(name="rackIdGenerator", sequenceName="SEQ_RACK_ID", allocationSize=1)
    private Long id;

    @Column(name="TEAM_ID")
    private Long teamId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="TEAM_ID", updatable = false, insertable = false, nullable = false)
    private Team team;

    @Column(name="NAME", nullable = false)
    private String name;

    @Column(name="SERIAL_NUMBER", nullable = false)
    private String serialNumber;

    @Column(name="STATUS", nullable = false, length = 20)
    private String status;

    @Column(name="DEFAULT_LOCATION", nullable = false, length = 10)
    private String defaultLocation;

    @JsonIgnore
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="CREATED_AT", nullable = false)
    private LocalDateTime createdAt;

    @JsonIgnore
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="MODIFIED_AT", nullable = false)
    private LocalDateTime modifiedAt;

    @PrePersist
    public void onPrePersist() {
        this.setCreatedAt(LocalDateTime.now());
        this.setModifiedAt(LocalDateTime.now());
    }

    public Long getId() { return id; }

    public String getSerialNumber() {
        return serialNumber;
    }

    public String getName() { return this.name; }

    public String getStatus() {
        return status;
    }

    public String getDefaultLocation() {
        return defaultLocation;
    }

    @JsonProperty("created_at")
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @JsonProperty("modified_at")
    public LocalDateTime getModifiedAt() {
        return modifiedAt;
    }

    public Team getTeam() { return team; }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setDefaultLocation(String defaultLocation) {
        this.defaultLocation = defaultLocation;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setModifiedAt(LocalDateTime modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    public void setName(String name) { this.name = name; }

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }
}