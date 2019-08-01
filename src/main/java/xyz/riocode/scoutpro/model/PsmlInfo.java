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

    @OrderBy("dateOfTransfer DESC")
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
