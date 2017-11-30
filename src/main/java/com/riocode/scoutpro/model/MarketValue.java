package com.riocode.scoutpro.model;

import java.io.Serializable;
import java.math.BigDecimal;
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

/**
 *
 * @author Nikola Cvetkovic
 */

@Entity
@Table(name = "marketvalue", catalog = "scout_pro_development", schema = "")
@NamedQueries({
    @NamedQuery(name = "MarketValue.findAll", query = "SELECT m FROM MarketValue m")
    , @NamedQuery(name = "MarketValue.findById", query = "SELECT m FROM MarketValue m WHERE m.id = :id")})
public class MarketValue implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    // proveriti da li ce raditi sa nazivom  kolone "value"
    @Basic(optional = false)
    @NotNull
    @Column(name = "worth")
    private BigDecimal worth;
    @Basic(optional = false)
    @NotNull
    @Column(name = "datePoint")
    private LocalDate datePoint;
    @JoinColumn(name = "transfermarktInfoId", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private TransfermarktInfo transfermarktInfo;

    public MarketValue() {
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getWorth() {
        return worth;
    }

    public void setWorth(BigDecimal worth) {
        this.worth = worth;
    }

    public LocalDate getDatePoint() {
        return datePoint;
    }

    public void setDatePoint(LocalDate datePoint) {
        this.datePoint = datePoint;
    }

    public TransfermarktInfo getTransfermarktInfo() {
        return transfermarktInfo;
    }

    public void setTransfermarktInfo(TransfermarktInfo transfermarktInfo) {
        this.transfermarktInfo = transfermarktInfo;
    }

    @Override
    public int hashCode() {
        return 6;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (this == object) return true;
        if (!(object instanceof MarketValue)) {
            return false;
        }
        MarketValue c = (MarketValue) object;        
        return this.id != null && Objects.equals(this.id, c.id);
    }

    @Override
    public String toString() {
        return "com.riocode.scoutpro.model.Marketvalue[ id=" + this.id + " ]";
    }

}
