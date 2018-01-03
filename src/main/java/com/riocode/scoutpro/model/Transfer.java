package com.riocode.scoutpro.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
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
@Table(name = "transfer", catalog = "scout_pro_development", schema = "")
@NamedQueries({
    @NamedQuery(name = "Transfer.findAll", query = "SELECT t FROM Transfer t")
    , @NamedQuery(name = "Transfer.findById", query = "SELECT t FROM Transfer t WHERE t.id = :id")})
public class Transfer implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "fromTeam")
    private String fromTeam;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "toTeam")
    private String toTeam;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    @Basic(optional = false)
    @NotNull
    @Column(name = "dateOfTransfer")
    private LocalDate dateOfTransfer;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "marketValue")
    private String marketValue;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "transferFee")
    private String transferFee;
    @JsonBackReference
    @JoinColumn(name = "transfermarktInfoId", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private TransfermarktInfo transfermarktInfo;

    public Transfer() {
    }

    public Transfer(String fromTeam, String toTeam, LocalDate dateOfTransfer, String marketValue, String transferFee) {
        this.fromTeam = fromTeam;
        this.toTeam = toTeam;
        this.dateOfTransfer = dateOfTransfer;
        this.marketValue = marketValue;
        this.transferFee = transferFee;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFromTeam() {
        return fromTeam;
    }

    public void setFromTeam(String fromTeam) {
        this.fromTeam = fromTeam;
    }

    public String getToTeam() {
        return toTeam;
    }

    public void setToTeam(String toTeam) {
        this.toTeam = toTeam;
    }

    public LocalDate getDateOfTransfer() {
        return dateOfTransfer;
    }

    public void setDateOfTransfer(LocalDate dateOfTransfer) {
        this.dateOfTransfer = dateOfTransfer;
    }

    public String getMarketValue() {
        return marketValue;
    }

    public void setMarketValue(String marketValue) {
        this.marketValue = marketValue;
    }

    public String getTransferFee() {
        return transferFee;
    }

    public void setTransferFee(String transferFee) {
        this.transferFee = transferFee;
    }

    public TransfermarktInfo getTransfermarktInfo() {
        return transfermarktInfo;
    }

    public void setTransfermarktInfo(TransfermarktInfo transfermarktInfo) {
        this.transfermarktInfo = transfermarktInfo;
    }

    @Override
    public int hashCode() {
        return 7;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (this == object) return true;
        if (!(object instanceof Transfer)) {
            return false;
        }
        Transfer c = (Transfer) object;        
        return this.id != null && Objects.equals(this.id, c.id);
    }

    @Override
    public String toString() {
        return "com.riocode.scoutpro.model.Transfer[ id=" + this.id + " ]";
    }

}
