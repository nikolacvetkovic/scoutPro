package com.riocode.scoutpro.model;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "positionplayedstats", catalog = "scout_pro_development", schema = "")
@NamedQueries({
    @NamedQuery(name = "PositionPlayedStats.findAll", query = "SELECT p FROM PositionPlayedStats p")
    , @NamedQuery(name = "PositionPlayedStats.findById", query = "SELECT p FROM PositionPlayedStats p WHERE p.id = :id")})
public class PositionPlayedStats implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "position")
    private String position;
    @Basic(optional = false)
    @NotNull
    @Column(name = "apps")
    private int apps;
    @Basic(optional = false)
    @NotNull
    @Column(name = "goals")
    private int goals;
    @Basic(optional = false)
    @NotNull
    @Column(name = "assists")
    private int assists;
    @Basic(optional = false)
    @NotNull
    @Column(name = "rating")
    private BigDecimal rating;
    @JoinColumn(name = "whoScoredInfoId", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private WhoScoredInfo whoScoredInfo;

    public PositionPlayedStats() {
    }

    public PositionPlayedStats(Integer id, String position, int apps, int goals, int assists, BigDecimal rating) {
        this.id = id;
        this.position = position;
        this.apps = apps;
        this.goals = goals;
        this.assists = assists;
        this.rating = rating;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getApps() {
        return apps;
    }

    public void setApps(int apps) {
        this.apps = apps;
    }

    public int getGoals() {
        return goals;
    }

    public void setGoals(int goals) {
        this.goals = goals;
    }

    public int getAssists() {
        return assists;
    }

    public void setAssists(int assists) {
        this.assists = assists;
    }

    public BigDecimal getRating() {
        return rating;
    }

    public void setRating(BigDecimal rating) {
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
        return 11;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (this == object) return true;
        if (!(object instanceof PositionPlayedStats)) {
            return false;
        }
        PositionPlayedStats c = (PositionPlayedStats) object;        
        return this.id != null && Objects.equals(this.id, c.id);
    }

    @Override
    public String toString() {
        return "com.riocode.scoutpro.model.Positionplayedstats[ id=" + this.id + " ]";
    }

}
