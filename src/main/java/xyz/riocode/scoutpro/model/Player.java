package xyz.riocode.scoutpro.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
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

    @Column(name = "transfermarkt_url")
    private String transfermarktUrl;
    @Column(name = "who_scored_url")
    private String whoScoredUrl;
    @Column(name = "pes_db_url")
    private String pesDbUrl;
    @Column(name = "psml_url")
    private String psmlUrl;

    @OrderBy("datePoint DESC")
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "player", fetch = FetchType.LAZY)
    private Set<MarketValue> marketValues = new HashSet<>();

    @OrderBy("dateOfTransfer DESC")
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "player", fetch = FetchType.LAZY)
    private Set<Transfer> transfers = new HashSet<>();

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "player", fetch = FetchType.LAZY)
    private TransfermarktInfo transfermarktInfo;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "player", fetch = FetchType.LAZY)
    private Characteristic characteristic;

    @OrderBy("id DESC")
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "player", fetch = FetchType.LAZY)
    private Set<CompetitionStatistic> competitionStatistics = new HashSet<>();

    @OrderBy("id DESC")
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "player", fetch = FetchType.LAZY)
    private Set<PositionStatistic> positionStatistics = new HashSet<>();

    @OrderBy("dateOfGame")
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "player", fetch = FetchType.LAZY)
    private Set<GameStatistic> gameStatistics = new HashSet<>();

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "player", fetch = FetchType.LAZY)
    private PesDbInfo pesDbInfo;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "player", fetch = FetchType.LAZY)
    private PsmlInfo psmlInfo;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "player", fetch = FetchType.LAZY)
    private Set<News> news = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "player", orphanRemoval = true)
    private Set<AppUserPlayer> users = new HashSet<>();

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

    public void removeUser(AppUserPlayer appUserPlayer){
        this.users.remove(appUserPlayer);
        appUserPlayer.setPlayer(null);
    }
}
