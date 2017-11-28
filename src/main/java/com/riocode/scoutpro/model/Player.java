package com.riocode.scoutpro.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "player", catalog = "scout_pro_development", schema = "")
@NamedQueries({
    @NamedQuery(name = "Player.findAll", query = "SELECT p FROM Player p")
    , @NamedQuery(name = "Player.findById", query = "SELECT p FROM Player p WHERE p.id = :id")
    , @NamedQuery(name = "Player.findByName", query = "SELECT p FROM Player p WHERE p.name = :name")
    , @NamedQuery(name = "Player.findByTransfermarktUrl", query = "SELECT p FROM Player p WHERE p.transfermarktUrl = :transfermarktUrl")
    , @NamedQuery(name = "Player.findByWhoScoredUrl", query = "SELECT p FROM Player p WHERE p.whoScoredUrl = :whoScoredUrl")
    , @NamedQuery(name = "Player.findByPesDbUrl", query = "SELECT p FROM Player p WHERE p.pesDbUrl = :pesDbUrl")
    , @NamedQuery(name = "Player.findByPsmlUrl", query = "SELECT p FROM Player p WHERE p.psmlUrl = :psmlUrl")})
public class Player implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 256)
    @Column(name = "name")
    private String name;
    @Size(max = 256)
    @Column(name = "transfermarktUrl")
    private String transfermarktUrl;
    @Size(max = 256)
    @Column(name = "whoScoredUrl")
    private String whoScoredUrl;
    @Size(max = 256)
    @Column(name = "pesDbUrl")
    private String pesDbUrl;
    @Size(max = 256)
    @Column(name = "psmlUrl")
    private String psmlUrl;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "player")
    private List<PsmlInfo> psmlInfoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "player")
    private List<PesDbInfo> pesDbInfoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "player")
    private List<WhoScoredInfo> whoScoredInfoList;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "player")
    private TransfermarktInfo transfermarktInfo;

    public Player() {
    }

    public Player(Integer id) {
        this.id = id;
    }

    public Player(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    
    public List<PsmlInfo> getPsmlInfoList() {
        return psmlInfoList;
    }

    public void setPsmlInfoList(List<PsmlInfo> psmlInfoList) {
        this.psmlInfoList = psmlInfoList;
    }
    
    public List<PesDbInfo> getPesDbInfoList() {
        return pesDbInfoList;
    }

    public void setPesDbInfoList(List<PesDbInfo> pesDbInfoList) {
        this.pesDbInfoList = pesDbInfoList;
    }
    
    public List<WhoScoredInfo> getWhoscoredInfoList() {
        return whoScoredInfoList;
    }

    public void setWhoscoredInfoList(List<WhoScoredInfo> whoScoredInfoList) {
        this.whoScoredInfoList = whoScoredInfoList;
    }

    public TransfermarktInfo getTransfermarktInfo() {
        return transfermarktInfo;
    }

    public void setTransfermarktInfo(TransfermarktInfo transfermarktInfo) {
        this.transfermarktInfo = transfermarktInfo;
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
        if (!(object instanceof Player)) {
            return false;
        }
        Player other = (Player) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.riocode.scoutpro.model.Player[ id=" + id + " ]";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTransfermarktUrl() {
        return transfermarktUrl;
    }

    public void setTransfermarktUrl(String transfermarktUrl) {
        this.transfermarktUrl = transfermarktUrl;
    }

    public String getWhoScoredUrl() {
        return whoScoredUrl;
    }

    public void setWhoScoredUrl(String whoScoredUrl) {
        this.whoScoredUrl = whoScoredUrl;
    }

    public String getPesDbUrl() {
        return pesDbUrl;
    }

    public void setPesDbUrl(String pesDbUrl) {
        this.pesDbUrl = pesDbUrl;
    }

    public String getPsmlUrl() {
        return psmlUrl;
    }

    public void setPsmlUrl(String psmlUrl) {
        this.psmlUrl = psmlUrl;
    }

}
