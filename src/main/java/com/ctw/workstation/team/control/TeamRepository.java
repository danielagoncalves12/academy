package com.ctw.workstation.team.control;

import com.ctw.workstation.team.entity.Team;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TeamRepository implements PanacheRepository<Team> {

    public Team getTeamByName(String name) {
        return find("name", name).firstResult();
    }
}
