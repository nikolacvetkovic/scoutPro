package com.riocode.scoutpro.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 *
 * @author Nikola Cvetkovic
 */

@Entity
@Table(name = "transfermarktinfo", catalog = "scout_pro_development", schema = "")
@NamedQueries({
    @NamedQuery(name = "TransfermarktInfo.findAll", query = "SELECT t FROM TransfermarktInfo t")
    , @NamedQuery(name = "TransfermarktInfo.findById", query = "SELECT t FROM TransfermarktInfo t WHERE t.id = :id")
    , @NamedQuery(name = "TransfermarktInfo.findByPlayerName", query = "SELECT t FROM TransfermarktInfo t WHERE t.playerName = :playerName")
    , @NamedQuery(name = "TransfermarktInfo.findByDateOfBirth", query = "SELECT t FROM TransfermarktInfo t WHERE t.dateOfBirth = :dateOfBirth")
    , @NamedQuery(name = "TransfermarktInfo.findByAge", query = "SELECT t FROM TransfermarktInfo t WHERE t.age = :age")
    , @NamedQuery(name = "TransfermarktInfo.findByNationality", query = "SELECT t FROM TransfermarktInfo t WHERE t.nationality = :nationality")
    , @NamedQuery(name = "TransfermarktInfo.findByNationalTeam", query = "SELECT t FROM TransfermarktInfo t WHERE t.nationalTeam = :nationalTeam")
    , @NamedQuery(name = "TransfermarktInfo.findByClubTeam", query = "SELECT t FROM TransfermarktInfo t WHERE t.clubTeam = :clubTeam")
    , @NamedQuery(name = "TransfermarktInfo.findByContractUntil", query = "SELECT t FROM TransfermarktInfo t WHERE t.contractUntil = :contractUntil")
    , @NamedQuery(name = "TransfermarktInfo.findByPosition", query = "SELECT t FROM TransfermarktInfo t WHERE t.position = :position")
    , @NamedQuery(name = "TransfermarktInfo.findByLastChange", query = "SELECT t FROM TransfermarktInfo t WHERE t.lastChange = :lastChange")})
public class TransfermarktInfo implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GenericGenerator(name = "myGen", strategy = "foreign", parameters = @Parameter(name = "property",value = "player"))
    @GeneratedValue(generator = "myGen")
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 256)
    @Column(name = "playerName")
    private String playerName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "dateOfBirth")
    private String dateOfBirth;
    @Basic(optional = false)
    @NotNull
    @Column(name = "age")
    private int age;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "nationality")
    private String nationality;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "nationalTeam")
    private String nationalTeam;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "clubTeam")
    private String clubTeam;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 12)
    @Column(name = "contractUntil")
    private String contractUntil;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "position")
    private String position;
    @Basic(optional = false)
    @NotNull
    @Column(name = "lastChange")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastChange;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "transfermarktInfo")
    private List<Transfer> transferList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "transfermarktInfo")
    private List<MarketValue> marketValueList;
    @OneToOne(optional = false)
    @JoinColumn(name = "playerid", referencedColumnName = "id")
    private Player player;

    public TransfermarktInfo() {
    }

    public TransfermarktInfo(Integer id) {
        this.id = id;
    }

    public TransfermarktInfo(Integer id, String playerName, String dateOfBirth, int age, String nationality, String nationalTeam, String clubTeam, String contractUntil, String position, Date lastChange) {
        this.id = id;
        this.playerName = playerName;
        this.dateOfBirth = dateOfBirth;
        this.age = age;
        this.nationality = nationality;
        this.nationalTeam = nationalTeam;
        this.clubTeam = clubTeam;
        this.contractUntil = contractUntil;
        this.position = position;
        this.lastChange = lastChange;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getNationalTeam() {
        return nationalTeam;
    }

    public void setNationalTeam(String nationalTeam) {
        this.nationalTeam = nationalTeam;
    }

    public String getClubTeam() {
        return clubTeam;
    }

    public void setClubTeam(String clubTeam) {
        this.clubTeam = clubTeam;
    }

    public String getContractUntil() {
        return contractUntil;
    }

    public void setContractUntil(String contractUntil) {
        this.contractUntil = contractUntil;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Date getLastChange() {
        return lastChange;
    }

    public void setLastChange(Date lastChange) {
        this.lastChange = lastChange;
    }

    public List<Transfer> getTransferList() {
        return transferList;
    }

    public void setTransferList(List<Transfer> transferList) {
        this.transferList = transferList;
    }

    public List<MarketValue> getMarketValueList() {
        return marketValueList;
    }

    public void setMarketValueList(List<MarketValue> marketValueList) {
        this.marketValueList = marketValueList;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
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
        if (!(object instanceof TransfermarktInfo)) {
            return false;
        }
        TransfermarktInfo other = (TransfermarktInfo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.riocode.scoutpro.model.Transfermarktinfo[ id=" + id + " ]";
    }

}
