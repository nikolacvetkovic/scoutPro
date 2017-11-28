package com.riocode.scoutpro.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Nikola Cvetkovic
 */

@Entity
@Table(name = "psmlinfo", catalog = "scout_pro_development", schema = "")
@NamedQueries({
    @NamedQuery(name = "PsmlInfo.findAll", query = "SELECT p FROM PsmlInfo p")
    , @NamedQuery(name = "PsmlInfo.findById", query = "SELECT p FROM PsmlInfo p WHERE p.id = :id")})
public class PsmlInfo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "teamName")
    private String teamName;
    @Column(name = "teamValue")
    private BigDecimal teamValue;
    @Basic(optional = false)
    @NotNull
    @Column(name = "lastChange")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastChange;
    @JoinColumn(name = "playerId", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Player player;

    public PsmlInfo() {
    }

    public PsmlInfo(Integer id, String teamName, Date lastChange) {
        this.id = id;
        this.teamName = teamName;
        this.lastChange = lastChange;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public BigDecimal getTeamValue() {
        return teamValue;
    }

    public void setTeamValue(BigDecimal teamValue) {
        this.teamValue = teamValue;
    }

    public Date getLastChange() {
        return lastChange;
    }

    public void setLastChange(Date lastChange) {
        this.lastChange = lastChange;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    @Override
    public int hashCode() {
        return 5;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (this == object) return true;
        if (!(object instanceof PsmlInfo)) {
            return false;
        }
        PsmlInfo c = (PsmlInfo) object;        
        return this.id != null && Objects.equals(this.id, c.id);
    }

    @Override
    public String toString() {
        return "com.riocode.scoutpro.model.Psmlinfo[ id=" + this.id + " ]";
    }

}
