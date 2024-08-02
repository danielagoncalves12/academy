package com.ctw.workstation.teammember.control;

import com.ctw.workstation.teammember.entity.TeamMember;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class TeamMemberService {

    public TeamMember create(TeamMember teamMember) {
        TeamMember.persist(teamMember);
        return teamMember;
    }

    public TeamMember getById(Long id) {
        return TeamMember.findById(id);
    }

    public boolean delete(Long id) {
        return TeamMember.deleteById(id);
    }

    public TeamMember update(Long id, TeamMember teamMember) {

        TeamMember teamMemberFound = getById(id);

        if (teamMemberFound != null) {
            teamMemberFound.setTeam(teamMember.getTeam());
            teamMemberFound.setName(teamMember.getName());
            teamMemberFound.setCtw_id(teamMember.getCtw_id());
        }

        return teamMemberFound;
    }

    public List<TeamMember> getTeamMembers() {
        return TeamMember.listAll();
    }
}
