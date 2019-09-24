package xyz.riocode.scoutpro.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class PlayerCompleteDTO {

    private String id;
    private boolean myPlayer;

    //tm
    private String playerName;
    private String dateOfBirth;
    private String age;
    private String nationality;
    private String nationalTeam;
    private String clubTeam;
    private String contractUntil;
    private String tmPosition;

    private List<TransferDTO> transferDTOS;
    private String transferLastCheck;
    private List<MarketValueDTO> marketValueDTOS;
    private String marketValueLastCheck;

    //ws
    private Set<String> strengths;
    private Set<String> weaknesses;
    private Set<String> stylesOfPlay;
    private List<CompetitionStatisticDTO> competitionStatisticDTOS;
    private List<PositionStatisticDTO> positionStatisticDTOS;
    private List<GameStatisticDTO> gameStatisticDTOS;
    private String statisticLastCheck;

    //pesdb
    private String pesDbPlayerName;
    private String pesDbTeamName;
    private String foot;
    private String weekCondition;
    private String position;
    private Set<String> otherStrongPositions;
    private Set<String> otherWeakPositions;
    private int attackingProwess;
    private int ballControl;
    private int dribbling;
    private int lowPass;
    private int loftedPass;
    private int finishing;
    private int placeKicking;
    private int swerve;
    private int header;
    private int defensiveProwess;
    private int ballWinning;
    private int kickingPower;
    private int speed;
    private int explosivePower;
    private int unwaveringBalance;
    private int physicalContact;
    private int jump;
    private int goalkeeping;
    private int gkCatch;
    private int clearing;
    private int reflexes;
    private int coverage;
    private int stamina;
    private int weakFootUsage;
    private int weakFootAccuracy;
    private int form;
    private int injuryResistance;
    private int overall;
    private String playingStyle;
    private Set<String> playerSkills;
    private Set<String> comPlayingStyles;
    private String pesDbLastCheck;

    //psml
    private String psmlTeam;
    private String psmlValue;
    private List<PsmlTransferDTO> psmlTransferDTOS;
    private String psmlLastCheck;
}
