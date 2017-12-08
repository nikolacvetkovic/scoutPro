package com.riocode.scoutpro.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.riocode.scoutpro.jpa.converter.ListStringConverter;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
@Table(name = "characteristic", catalog = "scout_pro_development", schema = "")
@NamedQueries({
    @NamedQuery(name = "Characteristic.findAll", query = "SELECT c FROM Characteristic c")
    , @NamedQuery(name = "Characteristic.findById", query = "SELECT c FROM Characteristic c WHERE c.id = :id")})
public class Characteristic implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GenericGenerator(name = "myGen", strategy = "foreign", parameters = @Parameter(name = "property",value = "whoscoredinfo"))
    @GeneratedValue(generator = "myGen")
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Lob
    @Size(max = 65535)
    @Convert(converter = ListStringConverter.class)
    @Column(name = "strengths")
    private List<String> strengths;
    @Lob
    @Size(max = 65535)
    @Convert(converter = ListStringConverter.class)
    @Column(name = "weaknesses")
    private List<String> weaknesses;
    @Lob
    @Size(max = 65535)
    @Convert(converter = ListStringConverter.class)
    @Column(name = "styleOfPlay")
    private List<String> styleOfPlay;
    @JsonBackReference
    @OneToOne(optional = false)
    @JoinColumn(name = "id", referencedColumnName = "id")
    private WhoScoredInfo whoscoredinfo;

    public Characteristic() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<String> getStrengths() {
        return strengths;
    }

    public void setStrengths(List<String> strengths) {
        this.strengths = strengths;
    }

    public List<String> getWeaknesses() {
        return weaknesses;
    }

    public void setWeaknesses(List<String> weaknesses) {
        this.weaknesses = weaknesses;
    }

    public List<String> getStyleOfPlay() {
        return styleOfPlay;
    }

    public void setStyleOfPlay(List<String> styleOfPlay) {
        this.styleOfPlay = styleOfPlay;
    }

    public WhoScoredInfo getWhoscoredinfo() {
        return whoscoredinfo;
    }

    public void setWhoscoredinfo(WhoScoredInfo whoscoredinfo) {
        this.whoscoredinfo = whoscoredinfo;
    }

    @Override
    public int hashCode() {
        return 8;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (this == object) return true;
        if (!(object instanceof Characteristic)) {
            return false;
        }
        Characteristic c = (Characteristic) object;        
        return this.id != null && Objects.equals(this.id, c.id);
    }

    @Override
    public String toString() {
        return "com.riocode.scoutpro.model.Characteristic[ id=" + this.id + " ]";
    }

}
