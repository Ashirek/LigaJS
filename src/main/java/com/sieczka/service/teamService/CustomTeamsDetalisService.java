package com.sieczka.service.teamService;

import com.sieczka.model.*;
import com.sieczka.repository.adminRepository.FootballerStatsRepository;
import com.sieczka.repository.adminRepository.FootballersRepository;
import com.sieczka.repository.teamRepository.TeamRepository;
import com.sieczka.repository.UserRepository;
import com.sieczka.repository.adminRepository.LeagueTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

/**
 * Created by Patryk on 2018-01-03.
 */
@Service
public class CustomTeamsDetalisService {
    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private LeagueTypeRepository leagueTypeRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FootballersRepository footballersRepository;

    @Autowired
    private FootballerStatsRepository footballerStatsRepository;

    public void createTeam(String userName, String teamName, String leagueTypeName) {

        User user = userRepository.findByUsername(userName);
        Teams teams = teamRepository.findByTeamName(teamName);
        LeagueType leagueType = leagueTypeRepository.findByLeagueTypeName(leagueTypeName);

        if (teams != null){

        }
        else {
            teams = new Teams();
            teams.setUser(user);
            teams.setTeamName(teamName);
            teams.setTeamType(leagueType);
            teams.setTeamPoints(0);
            teams.setBank(100.0);
            teamRepository.save(teams);
        }

    }

    public void addPoints(String leagueTypeName,Integer gameWeekNumber){
        List<Teams> teams = teamRepository.findByTeamType_LeagueTypeName(leagueTypeName);
       Integer points,fPoints;
        /*Iterator<String> flavoursIter = aFlavours.iterator();
        while (flavoursIter.hasNext()){
            System.out.println(flavoursIter.next());*/
        for (Teams team: teams
             ) {
            List<Footballers> footballers = team.getRosters().getStarters().getFootballersStarters();
            points = team.getTeamPoints();
            for (Footballers footballer: footballers) {

                FootballerStats footballerStats =
                        footballerStatsRepository.findByGameWeek_GameWeekNumberAndFootballers_FootballerLastName
                                (gameWeekNumber,footballer.getFootballerLastName());
                if(footballerStats != null){
                    fPoints = footballerStats.getWeekPoints();
                    points = points+ fPoints;
                }



            }
            team.setTeamPoints(points);
            teamRepository.save(team);
        }
    }
}
