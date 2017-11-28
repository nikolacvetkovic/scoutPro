package com.riocode.scoutpro.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
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
    , @NamedQuery(name = "Transfer.findById", query = "SELECT t FROM Transfer t WHERE t.id = :id")
    , @NamedQuery(name = "Transfer.findByFromTeam", query = "SELECT t FROM Transfer t WHERE t.fromTeam = :fromTeam")
    , @NamedQuery(name = "Transfer.findByToTeam", query = "SELECT t FROM Transfer t WHERE t.toTeam = :toTeam")
    , @NamedQuery(name = "Transfer.findByDateOfTransfer", query = "SELECT t FROM Transfer t WHERE t.dateOfTransfer = :dateOfTransfer")
    , @NamedQuery(name = "Transfer.findByMarketValue", query = "SELECT t FROM Transfer t WHERE t.marketValue = :marketValue")
    , @NamedQuery(name = "Transfer.findByTransferFee", query = "SELECT t FROM Transfer t WHERE t.transferFee = :transferFee")})
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
    @Basic(optional = false)
    @NotNull
    @Column(name = "dateOfTransfer")
    private Date dateOfTransfer;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "marketValue")
    private BigDecimal marketValue;
    @Basic(optional = false)
    @NotNull
    @Column(name = "transferFee")
    private BigDecimal transferFee;
    @JoinColumn(name = "transfermarktInfoId", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private TransfermarktInfo transfermarktInfo;

    public Transfer() {
    }

    public Transfer(Integer id) {
        this.id = id;
    }

    public Transfer(Integer id, String fromTeam, String toTeam, Date dateOfTransfer, BigDecimal marketValue, BigDecimal transferFee) {
        this.id = id;
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

    public Date getDateOfTransfer() {
        return dateOfTransfer;
    }

    public void setDateOfTransfer(Date dateOfTransfer) {
        this.dateOfTransfer = dateOfTransfer;
    }

    public BigDecimal getMarketValue() {
        return marketValue;
    }

    public void setMarketValue(BigDecimal marketValue) {
        this.marketValue = marketValue;
    }

    public BigDecimal getTransferFee() {
        return transferFee;
    }

    public void setTransferFee(BigDecimal transferFee) {
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
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Transfer)) {
            return false;
        }
        Transfer other = (Transfer) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.riocode.scoutpro.model.Transfer[ id=" + id + " ]";
    }

}
