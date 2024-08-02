package com.ctw.workstation.team.control;

import com.ctw.workstation.team.entity.Team;
import com.ctw.workstation.team.entity.TeamDTO;
import com.ctw.workstation.team.entity.TeamMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class TeamService {

    @Inject
    TeamRepository repository;

    @Inject
    TeamMapper mapper;

    public TeamDTO create(TeamDTO team) {
        Team teamEntity = mapper.toEntity(team);
        repository.persist(teamEntity);
        return mapper.toDTO(teamEntity);
    }

    public TeamDTO getById(Long id) {
        Team teamEntity = repository.findById(id);
        return mapper.toDTO(teamEntity);
    }

    public boolean delete(Long id) {
        return repository.deleteById(id);
    }

    public TeamDTO update(Long id, TeamDTO teamDTO) {

        Team teamEntity = repository.findById(id);

        if (teamEntity != null) {
            teamEntity.setModifiedAt(LocalDateTime.now());
            teamEntity.setProduct(teamDTO.getProduct());
            teamEntity.setDefaultLocation(teamDTO.getDefaultLocation());
            teamEntity.setName(teamDTO.getName());
        }

        return mapper.toDTO(teamEntity);
    }

    public TeamDTO getTeamByName(String name) {
        Team teamEntity = repository.getTeamByName(name);
        return mapper.toDTO(teamEntity);
    }

    public List<TeamDTO> getTeams() {
        List<Team> allTeams = repository.listAll();

        return allTeams.stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }
}