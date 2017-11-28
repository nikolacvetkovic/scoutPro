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

/**
 *
 * @author Nikola Cvetkovic
 */

@Entity
@Table(name = "marketvalue", catalog = "scout_pro_development", schema = "")
@NamedQueries({
    @NamedQuery(name = "MarketValue.findAll", query = "SELECT m FROM MarketValue m")
    , @NamedQuery(name = "MarketValue.findById", query = "SELECT m FROM MarketValue m WHERE m.id = :id")
    , @NamedQuery(name = "MarketValue.findByValue", query = "SELECT m FROM MarketValue m WHERE m.value = :value")
    , @NamedQuery(name = "MarketValue.findByDatePoint", query = "SELECT m FROM MarketValue m WHERE m.datePoint = :datePoint")})
public class MarketValue implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "value")
    private BigDecimal value;
    @Basic(optional = false)
    @NotNull
    @Column(name = "datePoint")
    private Date datePoint;
    @JoinColumn(name = "transfermarktInfoId", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private TransfermarktInfo transfermarktInfo;

    public MarketValue() {
    }

    public MarketValue(Integer id) {
        this.id = id;
    }

    public MarketValue(Integer id, BigDecimal value, Date datePoint) {
        this.id = id;
        this.value = value;
        this.datePoint = datePoint;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public Date getDatePoint() {
        return datePoint;
    }

    public void setDatePoint(Date datePoint) {
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
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MarketValue)) {
            return false;
        }
        MarketValue other = (MarketValue) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.riocode.scoutpro.model.Marketvalue[ id=" + id + " ]";
    }

}
