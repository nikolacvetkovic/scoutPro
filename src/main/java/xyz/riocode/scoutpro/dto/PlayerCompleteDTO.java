package xyz.riocode.scoutpro.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    private Set<TransferDTO> transferDTOS;
    private Set<MarketValueDTO> marketValueDTOS;

    //ws
    private Set<String> strengths;
    private Set<String> weaknesses;
    private Set<String> stylesOfPlay;
    private Set<CompetitionStatisticDTO> competitionStatisticDTOS;
    private Set<PositionStatisticDTO> positionStatisticDTOS;
    private Set<GameStatisticDTO> gameStatisticDTOS;

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
    private Set<PsmlTransferDTO> psmlTransferDTOS;
    private String psmlLastCheck;
}
