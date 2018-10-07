package com.riocode.scoutpro.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 *
 * @author Nikola Cvetkovic
 */

@Entity
@Table(name = "player", catalog = "scout_pro_development", schema = "")
@NamedQueries({
    @NamedQuery(name = "Player.findAll", query = "SELECT p FROM Player p")
    , @NamedQuery(name = "Player.findById", query = "SELECT p FROM Player p WHERE p.id = :id")})
public class Player implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "myPlayer")
    private boolean myPlayer;
    @Pattern(regexp = "^(http(s)?://www\\.transfermarkt\\.com/.+/profil/spieler/)\\d+$", message = "Not valid Transfermarkt url")
    @Size(max = 256)
    @Column(name = "transfermarktUrl")
    private String transfermarktUrl;
    @Pattern(regexp = "^(http(s)?://www\\.whoscored\\.com/Players/\\d+/Show/).+$", message = "Not valid Whoscored url")
    @Size(max = 256)
    @Column(name = "whoScoredUrl")
    private String whoScoredUrl;
    @Pattern(regexp = "^(http://pesdb\\.net/pes20[1-9][0-9]/\\?id=)\\d+$", message = "Not valid PesDb url")
    @Size(max = 256)
    @Column(name = "pesDbUrl")
    private String pesDbUrl;
    @Pattern(regexp = "^(http://psml\\.rs/(index\\.php)?\\?action=shwply&playerID=)\\d+$", message = "Not valid Psml url")
    @Size(max = 256)
    @Column(name = "psmlUrl")
    private String psmlUrl;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy HH:mm")
    @Basic(optional = false)
    @Column(name = "lastMeasured")
    private LocalDateTime lastMeasured;
    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "player", fetch = FetchType.LAZY)
    @OrderBy("lastMeasured")
    private List<PsmlInfo> psmlInfoList = new ArrayList<>();
    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "player", fetch = FetchType.LAZY)
    @OrderBy("lastMeasured")
    private List<PesDbInfo> pesDbInfoList = new ArrayList<>();
    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "player", fetch = FetchType.LAZY)
    @OrderBy("lastMeasured")
    private List<WhoScoredInfo> whoScoredInfoList = new ArrayList<>();
    @JsonManagedReference
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "player")
    private TransfermarktInfo transfermarktInfo;

    public Player() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    
    public List<PsmlInfo> getPsmlInfoList() {
        return this.psmlInfoList;
    }

    public void setPsmlInfoList(List<PsmlInfo> psmlInfoList) {
        this.psmlInfoList = psmlInfoList;
    }
    
    public List<PesDbInfo> getPesDbInfoList() {
        return this.pesDbInfoList;
    }

    public void setPesDbInfoList(List<PesDbInfo> pesDbInfoList) {
        this.pesDbInfoList = pesDbInfoList;
    }
    
    public List<WhoScoredInfo> getWhoscoredInfoList() {
        return this.whoScoredInfoList;
    }

    public void setWhoscoredInfoList(List<WhoScoredInfo> whoScoredInfoList) {
        this.whoScoredInfoList = whoScoredInfoList;
    }

    public TransfermarktInfo getTransfermarktInfo() {
        return this.transfermarktInfo;
    }

    public void setTransfermarktInfo(TransfermarktInfo transfermarktInfo) {
        this.transfermarktInfo = transfermarktInfo;
    }
    
    public boolean isMyPlayer(){
        return this.myPlayer;
    }
    
    public void setMyPlayer(boolean myPlayer){
        this.myPlayer = myPlayer;
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

    public LocalDateTime getLastMeasured() {
        return lastMeasured;
    }

    public void setLastMeasured(LocalDateTime lastMeasured) {
        this.lastMeasured = lastMeasured;
    }    
    
    @Override
    public int hashCode() {
        return 1;
    }
    
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (this == object) return true;
        if (!(object instanceof Player)) {
            return false;
        }
        Player c = (Player) object;        
        return this.id != null && Objects.equals(this.id, c.id);
    }

    @Override
    public String toString() {
        return "com.riocode.scoutpro.model.Player[ id=" + this.id + " ]";
    }

}
