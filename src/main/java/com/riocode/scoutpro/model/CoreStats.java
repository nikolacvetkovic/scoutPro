package com.riocode.scoutpro.model;

import java.io.Serializable;
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
@Table(name = "corestats", catalog = "scout_pro_development", schema = "")
@NamedQueries({
    @NamedQuery(name = "CoreStats.findAll", query = "SELECT c FROM CoreStats c")
    , @NamedQuery(name = "CoreStats.findById", query = "SELECT c FROM CoreStats c WHERE c.id = :id")})
public class CoreStats implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "competition")
    private String competition;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "startedApps")
    private String startedApps;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "subApps")
    private String subApps;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4)
    @Column(name = "mins")
    private String mins;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "goals")
    private String goals;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "assists")
    private String assists;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "yellowCards")
    private String yellowCards;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "redCards")
    private String redCards;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4)
    @Column(name = "shotsPerGame")
    private String shotsPerGame;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "passSuccess")
    private String passSuccess;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4)
    @Column(name = "aerialsWon")
    private String aerialsWon;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "manOfTheMatch")
    private String manOfTheMatch;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "rating")
    private String rating;
    @JoinColumn(name = "whoScoredInfoId", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private WhoScoredInfo whoScoredInfo;

    public CoreStats() {
    }

    public CoreStats(Integer id) {
        this.id = id;
    }

    public CoreStats(Integer id, String competition, String startedApps, String subApps, String mins, String goals, String assists, String yellowCards, String redCards, String shotsPerGame, String passSuccess, String aerialsWon, String manOfTheMatch, String rating) {
        this.id = id;
        this.competition = competition;
        this.startedApps = startedApps;
        this.subApps = subApps;
        this.mins = mins;
        this.goals = goals;
        this.assists = assists;
        this.yellowCards = yellowCards;
        this.redCards = redCards;
        this.shotsPerGame = shotsPerGame;
        this.passSuccess = passSuccess;
        this.aerialsWon = aerialsWon;
        this.manOfTheMatch = manOfTheMatch;
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

    public String getStartedApps() {
        return startedApps;
    }

    public void setStartedApps(String startedApps) {
        this.startedApps = startedApps;
    }

    public String getSubApps() {
        return subApps;
    }

    public void setSubApps(String subApps) {
        this.subApps = subApps;
    }

    public String getMins() {
        return mins;
    }

    public void setMins(String mins) {
        this.mins = mins;
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

    public String getYellowCards() {
        return yellowCards;
    }

    public void setYellowCards(String yellowCards) {
        this.yellowCards = yellowCards;
    }

    public String getRedCards() {
        return redCards;
    }

    public void setRedCards(String redCards) {
        this.redCards = redCards;
    }

    public String getShotsPerGame() {
        return shotsPerGame;
    }

    public void setShotsPerGame(String shotsPerGame) {
        this.shotsPerGame = shotsPerGame;
    }

    public String getPassSuccess() {
        return passSuccess;
    }

    public void setPassSuccess(String passSuccess) {
        this.passSuccess = passSuccess;
    }

    public String getAerialsWon() {
        return aerialsWon;
    }

    public void setAerialsWon(String aerialsWon) {
        this.aerialsWon = aerialsWon;
    }

    public String getManOfTheMatch() {
        return manOfTheMatch;
    }

    public void setManOfTheMatch(String manOfTheMatch) {
        this.manOfTheMatch = manOfTheMatch;
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
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CoreStats)) {
            return false;
        }
        CoreStats other = (CoreStats) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.riocode.scoutpro.model.Corestats[ id=" + id + " ]";
    }

}
