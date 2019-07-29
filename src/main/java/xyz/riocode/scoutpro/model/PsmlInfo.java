package xyz.riocode.scoutpro.model;

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

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "psmlInfo", fetch = FetchType.LAZY)
    private Set<PsmlTransfer> psmlTransfers;

    @Basic(optional = false)
    @Column(name = "last_check")
    private LocalDateTime lastCheck;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "id")
    private Player player;
}
