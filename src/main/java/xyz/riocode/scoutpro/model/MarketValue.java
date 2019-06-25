package xyz.riocode.scoutpro.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 *
 * @author Nikola Cvetkovic
 */

@NoArgsConstructor
@Data
@Entity
@Table(name = "market_value")
@NamedQueries({
    @NamedQuery(name = "MarketValue.findAll", query = "SELECT m FROM MarketValue m")
    , @NamedQuery(name = "MarketValue.findById", query = "SELECT m FROM MarketValue m WHERE m.id = :id")})
public class MarketValue implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "worth")
    private BigDecimal worth;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date_point")
    private LocalDate datePoint;
    @Basic(optional = false)
    @NotNull
    @Column(name = "club_team")
    private String clubTeam;
    @JsonBackReference
    @ManyToOne(optional = false)
    @JoinColumn(name = "player_id", referencedColumnName = "id")
    private Player player;
}
