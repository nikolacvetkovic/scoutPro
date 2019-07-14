package xyz.riocode.scoutpro.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

/**
 *
 * @author Nikola Cvetkovic
 */

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "psml_info")
//@NamedQueries({
//    @NamedQuery(name = "PsmlInfo.findAll", query = "SELECT p FROM PsmlInfo p")
//    , @NamedQuery(name = "PsmlInfo.findById", query = "SELECT p FROM PsmlInfo p WHERE p.id = :id")})
public class PsmlInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "psml_team")
    private String psmlTeam;
    @Column(name = "psml_value")
    private BigDecimal psmlValue;
    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "psmlInfo", fetch = FetchType.LAZY)
    private Set<PsmlTransfer> psmlTransfers;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy HH:mm")
    @Basic(optional = false)
    @Column(name = "last_check")
    private LocalDateTime lastCheck;
    @JsonBackReference
    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "id")
    private Player player;
}
