package xyz.riocode.scoutpro.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;


@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "transfer")
//@NamedQueries({
//    @NamedQuery(name = "Transfer.findAll", query = "SELECT t FROM Transfer t")
//    , @NamedQuery(name = "Transfer.findById", query = "SELECT t FROM Transfer t WHERE t.id = :id")})
public class Transfer implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "from_team")
    private String fromTeam;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "to_team")
    private String toTeam;

    @Basic(optional = false)
    @NotNull
    @Column(name = "date_of_transfer")
    private LocalDate dateOfTransfer;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "market_value")
    private String marketValue;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "transfer_fee")
    private String transferFee;

    @ManyToOne(optional = false)
    @JoinColumn(name = "player_id", referencedColumnName = "id")
    private Player player;

}
