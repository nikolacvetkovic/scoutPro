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
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;


@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "player")
public class Player implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "player_name")
    private String playerName;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "date_of_birth")
    private String dateOfBirth;
    @Basic(optional = false)
    @NotNull
    @Column(name = "age")
    private int age;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "nationality")
    private String nationality;
    @Basic(optional = false)
    @Size(min = 1, max = 50)
    @Column(name = "national_team")
    private String nationalTeam;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "club_team")
    private String clubTeam;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 12)
    @Column(name = "contract_until")
    private String contractUntil;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "transfermarkt_position")
    private String transfermarktPosition;

    @Column(name = "transfermarkt_url")
    private String transfermarktUrl;
    @Column(name = "whoscored_url")
    private String whoScoredUrl;
    @Column(name = "pesdb_url")
    private String pesDbUrl;
    @Column(name = "psml_url")
    private String psmlUrl;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "psml_team")
    private String psmlTeam;
    @Column(name = "psml_value")
    private BigDecimal psmlValue;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 256)
    @Column(name = "pesdb_player_name")
    private String pesDbPlayerName;
    @Basic(optional = false)
    @NotNull()
    @Size(min = 1, max = 50)
    @Column(name = "pesdb_team_name")
    private String pesDbTeamName;
    @Basic(optional = false)
    @NotNull
    @Column(name = "pesdb_foot")
    @Enumerated(EnumType.STRING)
    private Foot foot;
    @Basic(optional = false)
    @NotNull
    @Column(name = "pesdb_week_condition")
    private Character weekCondition;
    @Basic(optional = false)
    @NotNull
    @Column(name = "pesdb_primary_position")
    private String primaryPosition;
    @Convert(converter = SetStringStringConverter.class)
    @Column(name = "pesdb_other_strong_positions")
    private Set<String> otherStrongPositions;
    @Convert(converter = SetStringStringConverter.class)
    @Column(name = "pesdb_other_weak_positions")
    private Set<String> otherWeakPositions;
    @Basic(optional = false)
    @NotNull
    @Min(40)
    @Max(99)
    @Column(name = "pesdb_offensive_awareness")
    private int offensiveAwareness;
    @Basic(optional = false)
    @NotNull
    @Min(40)
    @Max(99)
    @Column(name = "pesdb_ball_control")
    private int ballControl;
    @Basic(optional = false)
    @NotNull
    @Min(40)
    @Max(99)
    @Column(name = "pesdb_dribbling")
    private int dribbling;
    @Basic(optional = false)
    @NotNull
    @Min(40)
    @Max(99)
    @Column(name = "pesdb_tight_possession")
    private int tightPossession;
    @Basic(optional = false)
    @NotNull
    @Min(40)
    @Max(99)
    @Column(name = "pesdb_low_pass")
    private int lowPass;
    @Basic(optional = false)
    @NotNull
    @Min(40)
    @Max(99)
    @Column(name = "pesdb_lofted_pass")
    private int loftedPass;
    @Basic(optional = false)
    @NotNull
    @Min(40)
    @Max(99)
    @Column(name = "pesdb_finishing")
    private int finishing;
    @Basic(optional = false)
    @NotNull
    @Min(40)
    @Max(99)
    @Column(name = "pesdb_heading")
    private int heading;
    @Basic(optional = false)
    @NotNull
    @Min(40)
    @Max(99)
    @Column(name = "pesdb_place_kicking")
    private int placeKicking;
    @Basic(optional = false)
    @NotNull
    @Min(40)
    @Max(99)
    @Column(name = "pesdb_curl")
    private int curl;
    @Basic(optional = false)
    @NotNull
    @Min(40)
    @Max(99)
    @Column(name = "pesdb_speed")
    private int speed;
    @Basic(optional = false)
    @NotNull
    @Min(40)
    @Max(99)
    @Column(name = "pesdb_acceleration")
    private int acceleration;
    @Basic(optional = false)
    @NotNull
    @Min(40)
    @Max(99)
    @Column(name = "pesdb_kicking_power")
    private int kickingPower;
    @Basic(optional = false)
    @NotNull
    @Min(40)
    @Max(99)
    @Column(name = "pesdb_jump")
    private int jump;
    @Basic(optional = false)
    @NotNull
    @Min(40)
    @Max(99)
    @Column(name = "pesdb_physical_contact")
    private int physicalContact;
    @Basic(optional = false)
    @NotNull
    @Min(40)
    @Max(99)
    @Column(name = "pesdb_balance")
    private int balance;
    @Basic(optional = false)
    @NotNull
    @Min(40)
    @Max(99)
    @Column(name = "pesdb_stamina")
    private int stamina;
    @Basic(optional = false)
    @NotNull
    @Min(40)
    @Max(99)
    @Column(name = "pesdb_defensive_awareness")
    private int defensiveAwareness;
    @Basic(optional = false)
    @NotNull
    @Min(40)
    @Max(99)
    @Column(name = "pesdb_ball_winning")
    private int ballWinning;
    @Basic(optional = false)
    @NotNull
    @Min(40)
    @Max(99)
    @Column(name = "pesdb_aggression")
    private int aggression;
    @Basic(optional = false)
    @NotNull
    @Min(40)
    @Max(99)
    @Column(name = "pesdb_gk_awareness")
    private int gkAwareness;
    @Basic(optional = false)
    @NotNull
    @Min(40)
    @Max(99)
    @Column(name = "pesdb_gk_catching")
    private int gkCatching;
    @Basic(optional = false)
    @NotNull
    @Min(40)
    @Max(99)
    @Column(name = "pesdb_gk_clearing")
    private int gkClearing;
    @Basic(optional = false)
    @NotNull
    @Min(40)
    @Max(99)
    @Column(name = "pesdb_gk_reflexes")
    private int gkReflexes;
    @Basic(optional = false)
    @NotNull
    @Min(40)
    @Max(99)
    @Column(name = "pesdb_gk_reach")
    private int gkReach;
    @Basic(optional = false)
    @Min(1)
    @Max(4)
    @Column(name = "pesdb_weak_foot_usage")
    private int weakFootUsage;
    @Basic(optional = false)
    @NotNull
    @Min(1)
    @Max(4)
    @Column(name = "pesdb_weak_foot_accuracy")
    private int weakFootAccuracy;
    @Basic(optional = false)
    @Min(1)
    @Max(8)
    @Column(name = "pesdb_form")
    private int form;
    @Basic(optional = false)
    @NotNull
    @Min(1)
    @Max(3)
    @Column(name = "pesdb_injury_resistance")
    private int injuryResistance;
    @Basic(optional = false)
    @NotNull
    @Min(40)
    @Max(99)
    @Column(name = "pesdb_overall_rating")
    private int overallRating;
    @Column(name = "pesdb_playing_style")
    private String playingStyle;
    @Convert(converter = SetStringStringConverter.class)
    @Column(name = "pesdb_player_skills")
    private Set<String> playerSkills;
    @Convert(converter = SetStringStringConverter.class)
    @Column(name = "pesdb_com_playing_styles")
    private Set<String> comPlayingStyles;

    @Size(max = 512)
    @Convert(converter = SetStringStringConverter.class)
    @Column(name = "whoscored_strengths")
    private Set<String> strengths = new HashSet<>();
    @Size(max = 512)
    @Convert(converter = SetStringStringConverter.class)
    @Column(name = "whoscored_weaknesses")
    private Set<String> weaknesses = new HashSet<>();
    @Size(max = 512)
    @Convert(converter = SetStringStringConverter.class)
    @Column(name = "whoscored_style_of_play")
    private Set<String> stylesOfPlay = new HashSet<>();

    @OrderBy("datePoint DESC")
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "player", fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<MarketValue> marketValues = new HashSet<>();

    @OrderBy("dateOfTransfer DESC")
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "player", fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<Transfer> transfers = new HashSet<>();

    @OrderBy("id DESC")
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "player", fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<CompetitionStatistic> competitionStatistics = new HashSet<>();

    @OrderBy("id DESC")
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "player", fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<PositionStatistic> positionStatistics = new HashSet<>();

    @OrderBy("dateOfGame")
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "player", fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<GameStatistic> gameStatistics = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "player", fetch = FetchType.LAZY)
    private Set<News> news = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "player", orphanRemoval = true)
    private Set<AppUserPlayer> users = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "player", orphanRemoval = true)
    private Set<PsmlTransfer> psmlTransfers = new HashSet<>();

    @Basic(optional = false)
    @Column(name = "pesdb_last_check")
    private LocalDateTime pesDbLastCheck;

    @Basic(optional = false)
    @Column(name = "psml_last_check")
    private LocalDateTime psmlLastCheck;

    @Basic(optional = false)
    @Column(name = "transfer_last_check")
    private LocalDateTime transferLastCheck;

    @Basic(optional = false)
    @Column(name = "market_value_last_check")
    private LocalDateTime marketValueLastCheck;

    @Basic(optional = false)
    @Column(name = "statistic_last_check")
    private LocalDateTime statisticLastCheck;

    @Basic(optional = false)
    @Column(name = "inserted")
    private LocalDateTime inserted;

    public void addUser(AppUser appUser){
        AppUserPlayer appUserPlayer = new AppUserPlayer(this, appUser);
        users.add(appUserPlayer);
        appUser.getPlayers().add(appUserPlayer);
    }

    public void removeUser(AppUser appUser){
        for(Iterator<AppUserPlayer> iterator = users.iterator(); iterator.hasNext();){
            AppUserPlayer appUserPlayer = iterator.next();
            if(appUserPlayer.getPlayer().equals(this) && appUserPlayer.getAppUser().equals(appUser)){
                iterator.remove();
                appUserPlayer.getAppUser().getPlayers().remove(appUserPlayer);
                appUserPlayer.setAppUser(null);
                appUserPlayer.setPlayer(null);
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(id, player.id) &&
                playerName.equals(player.playerName) &&
                pesDbPlayerName.equals(player.pesDbPlayerName);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (playerName != null ? playerName.hashCode() : 0);
        result = 31 * result + (pesDbPlayerName != null ? pesDbPlayerName.hashCode() : 0);
        result = 31 * result + (pesDbTeamName != null ? pesDbTeamName.hashCode() : 0);
        return result;
    }
}
