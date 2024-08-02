package com.ctw.workstation.teammember.entity;

import com.ctw.workstation.team.entity.Team;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "T_TEAM_MEMBER")
public class TeamMember extends PanacheEntityBase {

    @Id
    @JsonIgnore
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="teamMemberIdGenerator")
    @SequenceGenerator(name="teamMemberIdGenerator", sequenceName="SEQ_TEAM_MEMBER_ID", allocationSize=1)
    private Long id;

    @Column(name = "TEAM_ID")
    private Long team_id;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="TEAM_ID", updatable = false, insertable = false, nullable = false)
    private Team team;

    @Column(name = "CTW_ID", nullable = false, length = 10)
    private String ctw_id;

    @Column(name = "NAME", nullable = false, length = 20)
    private String name;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATED_AT")
    private LocalDateTime created_at;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "MODIFIED_AT")
    private LocalDateTime modified_at;

    @JsonProperty("id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTeam_id() {
        return team_id;
    }

    public void setTeam_id(Long team_id) {
        this.team_id = team_id;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public String getCtw_id() {
        return ctw_id;
    }

    public void setCtw_id(String ctw_id) {
        this.ctw_id = ctw_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public LocalDateTime getModified_at() {
        return modified_at;
    }

    public void setModified_at(LocalDateTime modified_at) {
        this.modified_at = modified_at;
    }
}
