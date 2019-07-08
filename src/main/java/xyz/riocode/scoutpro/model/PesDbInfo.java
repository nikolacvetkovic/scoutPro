package xyz.riocode.scoutpro.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.NoArgsConstructor;
import xyz.riocode.scoutpro.enums.*;
import xyz.riocode.scoutpro.jpa.converter.ListStringConverter;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Set;

/**
 *
 * @author Nikola Cvetkovic
 */

@NoArgsConstructor
@Data
@Entity
@Table(name = "pes_db_info")
@NamedQueries({
    @NamedQuery(name = "PesDbInfo.findAll", query = "SELECT p FROM PesDbInfo p")
    , @NamedQuery(name = "PesDbInfo.findById", query = "SELECT p FROM PesDbInfo p WHERE p.id = :id")
    , @NamedQuery(name = "PesDbInfo.findByTeamName", query = "SELECT p FROM PesDbInfo p WHERE p.teamName = :teamName")
    , @NamedQuery(name = "PesDbInfo.findByFoot", query = "SELECT p FROM PesDbInfo p WHERE p.foot = :foot")
    , @NamedQuery(name = "PesDbInfo.findByWeekCondition", query = "SELECT p FROM PesDbInfo p WHERE p.weekCondition = :weekCondition")
    , @NamedQuery(name = "PesDbInfo.findByPrimaryPosition", query = "SELECT p FROM PesDbInfo p WHERE p.primaryPosition = :primaryPosition")})
public class PesDbInfo implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
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
    @Size(min = 1, max = 5)
    @Column(name = "foot")
    private Foot foot;
    @Basic(optional = false)
    @NotNull
    @Column(name = "week_condition")
    private Character weekCondition;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "primary_position")
    private PesDbPosition primaryPosition;
    @Size(max = 50)
    @Convert(converter = ListStringConverter.class)
    @Column(name = "other_strong_positions")
    private Set<PesDbPosition> otherStrongPositions;
    @Size(max = 50)
    @Convert(converter = ListStringConverter.class)
    @Column(name = "other_weak_positions")
    private Set<PesDbPosition> otherWeakPositions;
    @Basic(optional = false)
    @NotNull
    @Min(40)
    @Max(99)
    @Column(name = "attacking_prowess")
    private int attackingProwess;
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
    @Column(name = "place_kicking")
    private int placeKicking;
    @Basic(optional = false)
    @NotNull
    @Min(40)
    @Max(99)
    @Column(name = "swerve")
    private int swerve;
    @Basic(optional = false)
    @NotNull
    @Min(40)
    @Max(99)
    @Column(name = "header")
    private int header;
    @Basic(optional = false)
    @NotNull
    @Min(40)
    @Max(99)
    @Column(name = "defensive_prowess")
    private int defensiveProwess;
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
    @Column(name = "kicking_power")
    private int kickingPower;
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
    @Column(name = "explosive_power")
    private int explosivePower;
    @Basic(optional = false)
    @NotNull
    @Min(40)
    @Max(99)
    @Column(name = "body_control")
    private int bodyControl;
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
    @Column(name = "jump")
    private int jump;
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
    @Column(name = "goalkeeping")
    private int goalkeeping;
    @Basic(optional = false)
    @NotNull
    @Min(40)
    @Max(99)
    @Column(name = "catching")
    private int catching;
    @Basic(optional = false)
    @NotNull
    @Min(40)
    @Max(99)
    @Column(name = "clearing")
    private int clearing;
    @Basic(optional = false)
    @NotNull
    @Min(40)
    @Max(99)
    @Column(name = "reflexes")
    private int reflexes;
    @Basic(optional = false)
    @NotNull
    @Min(40)
    @Max(99)
    @Column(name = "coverage")
    private int coverage;
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
    @NotNull
    @Min(40)
    @Max(99)
    @Column(name = "overall_rating")
    private int overallRating;
    @Column(name = "playing_style")
    @Enumerated(EnumType.STRING)
    private PlayingStyle playingStyle;
    @Lob
    @Size(max = 65535)
    @Convert(converter = ListStringConverter.class)
    @Column(name = "player_skills")
    private Set<PlayerSkill> playerSkills;
    @Lob
    @Size(max = 65535)
    @Convert(converter = ListStringConverter.class)
    @Column(name = "com_playing_styles")
    private Set<COMPlayingStyle> comPlayingStyles;
    @JsonBackReference    
    @JoinColumn(name = "player_id", referencedColumnName = "id")
    @OneToOne(optional = false)
    private Player player;
}
