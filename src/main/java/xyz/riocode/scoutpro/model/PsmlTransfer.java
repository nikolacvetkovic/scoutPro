package xyz.riocode.scoutpro.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "psml_transfer")
public class PsmlTransfer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @Column(name = "from_team")
    private String fromTeam;
    @Basic(optional = false)
    @Column(name = "to_team")
    private String toTeam;
    @Basic(optional = false)
    @Column(name = "transfer_fee")
    private BigDecimal transferFee;

    @Basic(optional = false)
    @Column(name = "date_of_transfer")
    private LocalDateTime dateOfTransfer;
    @ManyToOne(optional = false)
    @JoinColumn(name = "player_id",referencedColumnName = "id")
    private Player player;
}
