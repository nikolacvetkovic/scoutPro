package com.riocode.scoutpro.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Nikola Cvetkovic
 */

@Entity
@Table(name = "game", catalog = "scout_pro_development", schema = "")
@NamedQueries({
    @NamedQuery(name = "Game.findAll", query = "SELECT g FROM Game g")
    , @NamedQuery(name = "Game.findById", query = "SELECT g FROM Game g WHERE g.id = :id")})
public class Game implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "competition")
    private String competition;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dateOfGame")
    private LocalDate dateOfGame;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "team1")
    private String team1;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "team2")
    private String team2;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "result")
    private String result;
    @Basic(optional = false)
    @NotNull
    @Column(name = "manOfTheMatch")
    private boolean manOfTheMatch;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "goals")
    private String goals;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "assists")
    private String assists;
    @Basic(optional = false)
    @NotNull
    @Column(name = "yellowCard")
    private boolean yellowCard;
    @Basic(optional = false)
    @NotNull
    @Column(name = "redCard")
    private boolean redCard;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "minutesPlayed")
    private String minutesPlayed;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "rating")
    private String rating;
    @JoinColumn(name = "whoScoredInfoId", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private WhoScoredInfo whoScoredInfo;

    public Game() {
    }

    public Game(String competition, LocalDate dateOfGame, String team1, String team2, String result, boolean manOfTheMatch, String goals, String assists, boolean yellowCard, boolean redCard, String minutesPlayed, String rating) {
        this.competition = competition;
        this.dateOfGame = dateOfGame;
        this.team1 = team1;
        this.team2 = team2;
        this.result = result;
        this.manOfTheMatch = manOfTheMatch;
        this.goals = goals;
        this.assists = assists;
        this.yellowCard = yellowCard;
        this.redCard = redCard;
        this.minutesPlayed = minutesPlayed;
        this.rating = rating;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCompetition() {
        return competition;
    }

    public void setCompetition(String competition) {
        this.competition = competition;
    }

    public LocalDate getDateOfGame() {
        return dateOfGame;
    }

    public void setDateOfGame(LocalDate dateOfGame) {
        this.dateOfGame = dateOfGame;
    }

    public String getTeam1() {
        return team1;
    }

    public void setTeam1(String team1) {
        this.team1 = team1;
    }

    public String getTeam2() {
        return team2;
    }

    public void setTeam2(String team2) {
        this.team2 = team2;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public boolean getManOfTheMatch() {
        return manOfTheMatch;
    }

    public void setManOfTheMatch(boolean manOfTheMatch) {
        this.manOfTheMatch = manOfTheMatch;
    }

    public String getGoals() {
        return goals;
    }

    public void setGoals(String goals) {
        this.goals = goals;
    }

    public String getAssists() {
        return assists;
    }

    public void setAssists(String assists) {
        this.assists = assists;
    }

    public boolean getYellowCard() {
        return yellowCard;
    }

    public void setYellowCard(boolean yellowCard) {
        this.yellowCard = yellowCard;
    }

    public boolean getRedCard() {
        return redCard;
    }

    public void setRedCard(boolean redCard) {
        this.redCard = redCard;
    }

    public String getMinutesPlayed() {
        return minutesPlayed;
    }

    public void setMinutesPlayed(String minutesPlayed) {
        this.minutesPlayed = minutesPlayed;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public WhoScoredInfo getWhoScoredInfo() {
        return whoScoredInfo;
    }

    public void setWhoScoredInfo(WhoScoredInfo whoScoredInfo) {
        this.whoScoredInfo = whoScoredInfo;
    }

    @Override
    public int hashCode() {
        return 10;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (this == object) return true;
        if (!(object instanceof Game)) {
            return false;
        }
        Game c = (Game) object;        
        return this.id != null && Objects.equals(this.id, c.id);
    }

    @Override
    public String toString() {
        return "com.riocode.scoutpro.model.Game[ id=" + this.id + " ]";
    }

}
