package xyz.riocode.scoutpro.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import xyz.riocode.scoutpro.enums.Foot;
import xyz.riocode.scoutpro.jpa.converter.SetStringStringConverter;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
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
@Table(name = "pes_db_info")
//@NamedQueries({
//    @NamedQuery(name = "PesDbInfo.findAll", query = "SELECT p FROM PesDbInfo p")
//    , @NamedQuery(name = "PesDbInfo.findById", query = "SELECT p FROM PesDbInfo p WHERE p.id = :id")
//    , @NamedQuery(name = "PesDbInfo.findByTeamName", query = "SELECT p FROM PesDbInfo p WHERE p.teamName = :teamName")
//    , @NamedQuery(name = "PesDbInfo.findByFoot", query = "SELECT p FROM PesDbInfo p WHERE p.foot = :foot")
//    , @NamedQuery(name = "PesDbInfo.findByWeekCondition", query = "SELECT p FROM PesDbInfo p WHERE p.weekCondition = :weekCondition")
//    , @NamedQuery(name = "PesDbInfo.findByPrimaryPosition", query = "SELECT p FROM PesDbInfo p WHERE p.primaryPosition = :primaryPosition")})
public class PesDbInfo implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 256)
    @Column(name = "player_name")
    private String playerName;
    @Basic(optional = false)
    @NotNull()
    @Size(min = 1, max = 50)
    @Column(name = "team_name")
    private String teamName;
    @Basic(optional = false)
    @NotNull
    @Column(name = "foot")
    @Enumerated(EnumType.STRING)
    private Foot foot;
    @Basic(optional = false)
    @NotNull
    @Column(name = "week_condition")
    private Character weekCondition;
    @Basic(optional = false)
    @NotNull
    @Column(name = "primary_position")
    private String primaryPosition;
    @Convert(converter = SetStringStringConverter.class)
    @Column(name = "other_strong_positions")
    private Set<String> otherStrongPositions;
    @Convert(converter = SetStringStringConverter.class)
    @Column(name = "other_weak_positions")
    private Set<String> otherWeakPositions;
    @Basic(optional = false)
    @NotNull
    @Min(40)
    @Max(99)
    @Column(name = "offensive_awareness")
    private int offensiveAwareness;
    @Basic(optional = false)
    @NotNull
    @Min(40)
    @Max(99)
    @Column(name = "ball_control")
    private int ballControl;
    @Basic(optional = false)
    @NotNull
    @Min(40)
    @Max(99)
    @Column(name = "dribbling")
    private int dribbling;
    @Basic(optional = false)
    @NotNull
    @Min(40)
    @Max(99)
    @Column(name = "tight_possession")
    private int tightPossession;
    @Basic(optional = false)
    @NotNull
    @Min(40)
    @Max(99)
    @Column(name = "low_pass")
    private int lowPass;
    @Basic(optional = false)
    @NotNull
    @Min(40)
    @Max(99)
    @Column(name = "lofted_pass")
    private int loftedPass;
    @Basic(optional = false)
    @NotNull
    @Min(40)
    @Max(99)
    @Column(name = "finishing")
    private int finishing;
    @Basic(optional = false)
    @NotNull
    @Min(40)
    @Max(99)
    @Column(name = "heading")
    private int heading;
    @Basic(optional = false)
    @NotNull
    @Min(40)
    @Max(99)
    @Column(name = "place_kicking")
    private int placeKicking;
    @Basic(optional = false)
    @NotNull
    @Min(40)
    @Max(99)
    @Column(name = "curl")
    private int curl;
    @Basic(optional = false)
    @NotNull
    @Min(40)
    @Max(99)
    @Column(name = "speed")
    private int speed;
    @Basic(optional = false)
    @NotNull
    @Min(40)
    @Max(99)
    @Column(name = "acceleration")
    private int acceleration;
    @Basic(optional = false)
    @NotNull
    @Min(40)
    @Max(99)
    @Column(name = "kicking_power")
    private int kickingPower;
    @Basic(optional = false)
    @NotNull
    @Min(40)
    @Max(99)
    @Column(name = "jump")
    private int jump;
    @Basic(optional = false)
    @NotNull
    @Min(40)
    @Max(99)
    @Column(name = "physical_contact")
    private int physicalContact;
    @Basic(optional = false)
    @NotNull
    @Min(40)
    @Max(99)
    @Column(name = "balance")
    private int balance;
    @Basic(optional = false)
    @NotNull
    @Min(40)
    @Max(99)
    @Column(name = "stamina")
    private int stamina;
    @Basic(optional = false)
    @NotNull
    @Min(40)
    @Max(99)
    @Column(name = "defensive_awareness")
    private int defensiveAwareness;
    @Basic(optional = false)
    @NotNull
    @Min(40)
    @Max(99)
    @Column(name = "ball_winning")
    private int ballWinning;
    @Basic(optional = false)
    @NotNull
    @Min(40)
    @Max(99)
    @Column(name = "aggression")
    private int aggression;
    @Basic(optional = false)
    @NotNull
    @Min(40)
    @Max(99)
    @Column(name = "gk_awareness")
    private int gkAwareness;
    @Basic(optional = false)
    @NotNull
    @Min(40)
    @Max(99)
    @Column(name = "gk_catching")
    private int gkCatching;
    @Basic(optional = false)
    @NotNull
    @Min(40)
    @Max(99)
    @Column(name = "gk_clearing")
    private int gkClearing;
    @Basic(optional = false)
    @NotNull
    @Min(40)
    @Max(99)
    @Column(name = "gk_reflexes")
    private int gkReflexes;
    @Basic(optional = false)
    @NotNull
    @Min(40)
    @Max(99)
    @Column(name = "gk_reach")
    private int gkReach;
    @Basic(optional = false)
    @Min(1)
    @Max(4)
    @Column(name = "weak_foot_usage")
    private int weakFootUsage;
    @Basic(optional = false)
    @NotNull
    @Min(1)
    @Max(4)
    @Column(name = "weak_foot_accuracy")
    private int weakFootAccuracy;
    @Basic(optional = false)
    @Min(1)
    @Max(8)
    @Column(name = "form")
    private int form;
    @Basic(optional = false)
    @NotNull
    @Min(1)
    @Max(3)
    @Column(name = "injury_resistance")
    private int injuryResistance;
    @Basic(optional = false)
    @NotNull
    @Min(40)
    @Max(99)
    @Column(name = "overall_rating")
    private int overallRating;
    @Column(name = "playing_style")
    private String playingStyle;
    @Convert(converter = SetStringStringConverter.class)
    @Column(name = "player_skills")
    private Set<String> playerSkills;
    @Convert(converter = SetStringStringConverter.class)
    @Column(name = "com_playing_styles")
    private Set<String> comPlayingStyles;

    @Basic(optional = false)
    @Column(name = "last_check")
    private LocalDateTime lastCheck;

    @OneToOne(optional = false)
    @MapsId
    @JoinColumn(name = "id")
    private Player player;
}
