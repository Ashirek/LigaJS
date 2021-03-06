package com.sieczka.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Patryk on 2017-11-23.
 */
@Entity
@Table(name="League")
public class League {

    @Id
    @Column(name="league_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long leagueId;

    @Column(name = "league_name")
    private String leagueName;


    @JsonBackReference(value = "leaguetoteams")
    @OneToMany(mappedBy = "league")
    private List<Teams> teams;

    public League() {
    }

    public Long getLeagueId() {
        return leagueId;
    }

    public void setLeagueId(Long leagueId) {
        this.leagueId = leagueId;
    }

    public String getLeagueName() {
        return leagueName;
    }

    public void setLeagueName(String leagueName) {
        this.leagueName = leagueName;
    }


    public List<Teams> getTeams() {
        return teams;
    }

    public void setTeams(List<Teams> teams) {
        this.teams = teams;
    }


}
