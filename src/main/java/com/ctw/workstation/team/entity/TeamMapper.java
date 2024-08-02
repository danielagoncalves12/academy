package com.ctw.workstation.team.entity;

import org.mapstruct.Mapper;

@Mapper(componentModel = "jakarta")
public interface TeamMapper {

    TeamDTO toDTO(Team team);
    Team toEntity(TeamDTO teamDTO);
}
