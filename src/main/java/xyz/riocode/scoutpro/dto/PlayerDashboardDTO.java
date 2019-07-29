package xyz.riocode.scoutpro.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class PlayerDashboardDTO {

    private String id;
    private String playerName;
    private boolean myPlayer;

    private String tmValue;

    private String nationalTeam;
    private String clubTeam;
    private String contractUntil;

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
    private String pesDbLastCheck;

    private String psmlTeam;
    private String psmlValue;
    private String psmlLastTransferDate;
    private String psmlLastTransferFromTeam;
    private String psmlLastTransferToTeam;
    private String psmlLastTransferFee;
    private String psmlLastCheck;

    private int totalStartedApps;
    private int totalMins;
    private int totalGoals;
    private int totalAssists;
    private String averageShotsPerGame;
    private String averagePassSuccess;
    private String averageAerialsWon;
    private int totalManOfTheMatch;
    private String averageRating;
    private String statisticsLastCheck;
}
