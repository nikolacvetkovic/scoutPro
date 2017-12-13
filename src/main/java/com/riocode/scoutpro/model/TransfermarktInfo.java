package com.riocode.scoutpro.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
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
    , @NamedQuery(name = "TransfermarktInfo.findByAge", query = "SELECT t FROM TransfermarktInfo t WHERE t.age = :age")
    , @NamedQuery(name = "TransfermarktInfo.findByNationality", query = "SELECT t FROM TransfermarktInfo t WHERE t.nationality = :nationality")
    , @NamedQuery(name = "TransfermarktInfo.findByNationalTeam", query = "SELECT t FROM TransfermarktInfo t WHERE t.nationalTeam = :nationalTeam")
    , @NamedQuery(name = "TransfermarktInfo.findByClubTeam", query = "SELECT t FROM TransfermarktInfo t WHERE t.clubTeam = :clubTeam")
    , @NamedQuery(name = "TransfermarktInfo.findByContractUntil", query = "SELECT t FROM TransfermarktInfo t WHERE t.contractUntil = :contractUntil")
    , @NamedQuery(name = "TransfermarktInfo.findByPosition", query = "SELECT t FROM TransfermarktInfo t WHERE t.position = :position")})
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
    @Column(name = "currentValue")
    private BigDecimal currentValue;
    @Basic(optional = false)
    @NotNull
    @Column(name = "lastChangedCurrentValue")
    private LocalDate lastChangedCurrentValue;
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
    @Size(min = 1, max = 40)
    @Column(name = "position")
    private String position;
    @Basic(optional = false)
    @NotNull
    @Column(name = "lastMeasured")
    private LocalDateTime lastMeasured;
    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "transfermarktInfo")
    private List<Transfer> transferList;
    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "transfermarktInfo")
    private List<MarketValue> marketValueList;
    @JsonBackReference
    @OneToOne(optional = false)
    @JoinColumn(name = "id", referencedColumnName = "id")
    private Player player;

    public TransfermarktInfo() {
    }

    public TransfermarktInfo(String playerName, String dateOfBirth, int age, String nationality, String nationalTeam, String clubTeam, String contractUntil, String position, LocalDateTime lastMeasured) {
        this.playerName = playerName;
        this.dateOfBirth = dateOfBirth;
        this.age = age;
        this.nationality = nationality;
        this.nationalTeam = nationalTeam;
        this.clubTeam = clubTeam;
        this.contractUntil = contractUntil;
        this.position = position;
        this.lastMeasured = lastMeasured;
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
    
    public BigDecimal getCurrentValue() {
        return currentValue;
    }

    public void setCurrentValue(BigDecimal currentValue) {
        this.currentValue = currentValue;
    }
    
    public LocalDate getLastChangedCurrentValue() {
        return lastChangedCurrentValue;
    }

    public void setLastChangedCurrentValue(LocalDate lastChangedCurrentValue) {
        this.lastChangedCurrentValue = lastChangedCurrentValue;
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

    public LocalDateTime getLastMeasured() {
        return lastMeasured;
    }

    public void setLastMeasured(LocalDateTime lastMeasured) {
        this.lastMeasured = lastMeasured;
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
        return 2;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (this == object) return true;
        if (!(object instanceof TransfermarktInfo)) {
            return false;
        }
        TransfermarktInfo c = (TransfermarktInfo) object;        
        return this.id != null && Objects.equals(this.id, c.id);
    }

    @Override
    public String toString() {
        return "com.riocode.scoutpro.model.Transfermarktinfo[ id=" + this.id + " ]";
    }   

}
