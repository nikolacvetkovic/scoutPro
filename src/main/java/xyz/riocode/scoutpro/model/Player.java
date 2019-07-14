package xyz.riocode.scoutpro.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Nikola Cvetkovic
 */

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "player")
//@NamedQueries({
//    @NamedQuery(name = "Player.findAll", query = "SELECT p FROM Player p")
//    , @NamedQuery(name = "Player.findById", query = "SELECT p FROM Player p WHERE p.id = :id")})
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

    @Pattern(regexp = "^(http(s)?://www\\.transfermarkt\\.com/.+/profil/spieler/)\\d+$", message = "Not valid Transfermarkt url")
    @Size(max = 256)
    @Column(name = "transfermarkt_url")
    private String transfermarktUrl;
    @Pattern(regexp = "^(http(s)?://www\\.whoscored\\.com/Players/\\d+/Show/).+$", message = "Not valid WhoScored url")
    @Size(max = 256)
    @Column(name = "who_scored_url")
    private String whoScoredUrl;
    @Pattern(regexp = "^(http://pesdb\\.net/pes20[1-9][0-9]/\\?id=)\\d+$", message = "Not valid PesDb url")
    @Size(max = 256)
    @Column(name = "pes_db_url")
    private String pesDbUrl;
    @Pattern(regexp = "^(http://psml\\.rs/(index\\.php)?\\?action=shwply&playerID=)\\d+$", message = "Not valid Psml url")
    @Size(max = 256)
    @Column(name = "psml_url")
    private String psmlUrl;

    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "player", fetch = FetchType.LAZY)
    private Set<MarketValue> marketValues = new HashSet<>();
    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "player", fetch = FetchType.LAZY)
    private Set<Transfer> transfers = new HashSet<>();

    @JsonManagedReference
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "player", fetch = FetchType.LAZY)
    private TransfermarktInfo transfermarktInfo;
    @JsonManagedReference
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "player", fetch = FetchType.LAZY)
    private Characteristic characteristic;
    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "player", fetch = FetchType.LAZY)
    private Set<CompetitionStatistic> competitionStatistics = new HashSet<>();
    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "player", fetch = FetchType.LAZY)
    private Set<PositionStatistic> positionStatistics = new HashSet<>();
    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "player", fetch = FetchType.LAZY)
    private Set<GameStatistic> gameStatistics = new HashSet<>();
    @JsonManagedReference
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "player", fetch = FetchType.LAZY)
    private PesDbInfo pesDbInfo;
    @JsonManagedReference
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "player", fetch = FetchType.LAZY)
    private PsmlInfo psmlInfo;

    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "player", fetch = FetchType.LAZY)
    private Set<News> news = new HashSet<>();
    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "player", orphanRemoval = true)
    private Set<AppUserPlayer> users = new HashSet<>();

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy HH:mm")
    @Basic(optional = false)
    @Column(name = "transfer_last_check")
    private LocalDateTime transferLastCheck;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy HH:mm")
    @Basic(optional = false)
    @Column(name = "market_value_last_check")
    private LocalDateTime marketValueLastCheck;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy HH:mm")
    @Basic(optional = false)
    @Column(name = "statistic_last_check")
    private LocalDateTime statisticLastCheck;
}
