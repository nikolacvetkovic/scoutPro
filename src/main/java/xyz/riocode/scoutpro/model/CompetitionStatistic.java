package xyz.riocode.scoutpro.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;

@NoArgsConstructor
@Data
@Entity
@Table(name = "statistics_by_competition")
public class CompetitionStatistic implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "competition")
    private String competition;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "started_apps")
    private int startedApps;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "sub_apps")
    private int subApps;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4)
    @Column(name = "mins")
    private int mins;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "goals")
    private int goals;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "assists")
    private int assists;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "yellow_cards")
    private int yellowCards;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "red_cards")
    private int redCards;
    @Basic(optional = false)
    @NotNull
    @DecimalMin("0.0")
    @Column(name = "shots_per_game")
    private BigDecimal shotsPerGame;
    @Basic(optional = false)
    @NotNull
    @DecimalMin("0.00")
    @DecimalMax("100.00")
    @Column(name = "pass_success")
    private BigDecimal passSuccess;
    @Basic(optional = false)
    @NotNull
    @DecimalMin("0.00")
    @Column(name = "aerials_won")
    private BigDecimal aerialsWon;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "man_of_the_match")
    private int manOfTheMatch;
    @Basic(optional = false)
    @NotNull
    @DecimalMin("0.00")
    @DecimalMax("10.00")
    @Column(name = "rating")
    private BigDecimal rating;
    @ManyToOne(optional = false)
    @JoinColumn(name = "player_id", referencedColumnName = "id")
    private Player player;
}
