package xyz.riocode.scoutpro.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "position_statistic")
public class PositionStatistic implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "position")
    private String position;
    @Basic(optional = false)
    @NotNull
    @Column(name = "apps")
    private int apps;
    @Basic(optional = false)
    @NotNull
    @Column(name = "goals")
    private int goals;
    @Basic(optional = false)
    @NotNull
    @Column(name = "assists")
    private int assists;
    @Basic(optional = false)
    @NotNull
    @DecimalMin("0.00")
    @DecimalMax("10.00")
    @Column(name = "rating")
    private BigDecimal rating;
    @ManyToOne(optional = false)
    @NotNull
    @JoinColumn(name = "player_id", referencedColumnName = "id")
    private Player player;
}