package com.riocode.scoutpro.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Nikola Cvetkovic
 */

@Entity
@Table(name = "whoscoredinfo", catalog = "scout_pro_development", schema = "")
@NamedQueries({
    @NamedQuery(name = "WhoScoredInfo.findAll", query = "SELECT w FROM WhoScoredInfo w")
    , @NamedQuery(name = "WhoScoredInfo.findById", query = "SELECT w FROM WhoScoredInfo w WHERE w.id = :id")
    , @NamedQuery(name = "WhoScoredInfo.findBySeason", query = "SELECT w FROM WhoScoredInfo w WHERE w.season = :season")})
public class WhoScoredInfo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "season")
    private String season;
    @Basic(optional = false)
    @NotNull
    @Column(name = "lastChange")
    private LocalDateTime lastChange;
    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "whoScoredInfo")
    private List<PositionPlayedStats> positionPlayedStatsList;
    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "whoScoredInfo")
    private List<Game> gameList;
    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "whoScoredInfo")
    private List<CoreStats> coreStatsList;
    @JsonBackReference
    @JoinColumn(name = "playerId", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Player player;
    @JsonManagedReference
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "whoscoredinfo")
    private Characteristic characteristic;

    public WhoScoredInfo() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public LocalDateTime getLastChange() {
        return lastChange;
    }

    public void setLastChange(LocalDateTime lastChange) {
        this.lastChange = lastChange;
    }

    public List<PositionPlayedStats> getPositionPlayedStatsList() {
        return positionPlayedStatsList;
    }

    public void setPositionPlayedStatsList(List<PositionPlayedStats> positionPlayedStatsList) {
        this.positionPlayedStatsList = positionPlayedStatsList;
    }

    public List<Game> getGameList() {
        return gameList;
    }

    public void setGameList(List<Game> gameList) {
        this.gameList = gameList;
    }
    
    public List<CoreStats> getCoreStatsList() {
        return coreStatsList;
    }

    public void setCoreStatsList(List<CoreStats> coreStatsList) {
        this.coreStatsList = coreStatsList;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Characteristic getCharacteristic() {
        return characteristic;
    }

    public void setCharacteristic(Characteristic characteristic) {
        this.characteristic = characteristic;
    }

    @Override
    public int hashCode() {
        return 3;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (this == object) return true;
        if (!(object instanceof WhoScoredInfo)) {
            return false;
        }
        WhoScoredInfo c = (WhoScoredInfo) object;        
        return this.id != null && Objects.equals(this.id, c.id);
    }

    @Override
    public String toString() {
        return "com.riocode.scoutpro.model.Whoscoredinfo[ id=" + this.id + " ]";
    }

}
