package xyz.riocode.scoutpro.bootstrap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import xyz.riocode.scoutpro.enums.Foot;
import xyz.riocode.scoutpro.enums.PesDbPosition;
import xyz.riocode.scoutpro.enums.PlayingStyle;
import xyz.riocode.scoutpro.model.*;
import xyz.riocode.scoutpro.repository.AppUserRepository;
import xyz.riocode.scoutpro.repository.PlayerRepository;
import xyz.riocode.scoutpro.repository.TransfermarktInfoRepository;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Component
public class DataLoader implements CommandLineRunner {

    private final PlayerRepository playerRepository;
    private final AppUserRepository appUserRepository;
    private final TransfermarktInfoRepository transfermarktInfoRepository;
    private static final Logger LOGGER = LogManager.getLogger(DataLoader.class.getName());

    public DataLoader(PlayerRepository playerRepository, AppUserRepository appUserRepository, TransfermarktInfoRepository transfermarktInfoRepository) {
        this.playerRepository = playerRepository;
        this.appUserRepository = appUserRepository;
        this.transfermarktInfoRepository = transfermarktInfoRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        LOGGER.info("<<< Data loading started >>>");

        AppUser savedUser = saveUser();

        LOGGER.info("<<< User saved >>>");

        Player savedPlayer = savePlayer(savedUser);

        LOGGER.info("<<< Player <<" + savedPlayer.getPlayerName() + ">> saved >>>");

        savedPlayer = saveMarketValues(savedPlayer);

        LOGGER.info("<<< Market Values created and saved >>>");

        savedPlayer = saveTransers(savedPlayer);

        LOGGER.info("<<< Transfers created and saved >>>");

        //savedPlayer = saveTransfermarktInfo(savedPlayer);

        LOGGER.info("<<< Transfermarkt created and saved >>>");

        savedPlayer = savePesDbInfo(savedPlayer);

        LOGGER.info("<<< PesDbInfo saved, Player updated >>>");
        
        savedPlayer = saveCompetitionStatistics(savedPlayer);

        LOGGER.info("<<< Competition Statistics saved, Player updated >>>");

        savedPlayer = savePositionStatistics(savedPlayer);

        LOGGER.info("<<< Position Statistics saved, Player updated >>>");

        savedPlayer = saveGameStatistics(savedPlayer);

        LOGGER.info("<<< Game Statistics saved, Player updated >>>");

        savedPlayer = saveCharacteristic(savedPlayer);

        LOGGER.info("<<< Characteristic saved, Player updated >>>");

        LOGGER.info("<<< Data loading finished >>>");
    }

    @Transactional
    private AppUser saveUser(){
        AppUser user = new AppUser();
        user.setUsername("cvele");
        user.setPassword("asdasd");

        return appUserRepository.save(user);
    }

    @Transactional
    private Player savePlayer(AppUser appUser){
        Player player = new Player();
        player.setPlayerName("Harry Maguire");
        player.setTransfermarktUrl("https://www.transfermarkt.com/harry-maguire/profil/spieler/177907");
        player.setWhoScoredUrl("https://www.whoscored.com/Players/99487/Show/Harry-Maguire");
        player.setPesDbUrl("http://pesdb.net/pes2019/?id=109329");
        player.setPsmlUrl("http://psml.rs/?action=shwply&playerID=109329");

        player = playerRepository.save(player);

        AppUserPlayerId appUserPlayerId = new AppUserPlayerId();
        AppUserPlayer appUserPlayer = new AppUserPlayer();
        appUserPlayer.setMyPlayer(true);
        appUserPlayer.setAppUser(appUser);
        appUserPlayer.setPlayer(player);
        appUserPlayer.setAppUserPlayerId(appUserPlayerId);
        appUser.getPlayers().add(appUserPlayer);
        player.getUsers().add(appUserPlayer);

        AppUser updatedUser = appUserRepository.save(appUser);

        return updatedUser.getPlayers().stream().findFirst().get().getPlayer();
    }

    @Transactional
    private Player saveMarketValues(Player player){
        Set<MarketValue> marketValues = new HashSet<>();
        MarketValue mv1 = new MarketValue();
        mv1.setClubTeam("Leicester City");
        mv1.setDatePoint(LocalDate.of(2018, 12, 19));
        mv1.setWorth(BigDecimal.valueOf(45000000.00));
        mv1.setPlayer(player);
        marketValues.add(mv1);

        MarketValue mv2 = new MarketValue();
        mv2.setClubTeam("Leicester City");
        mv2.setDatePoint(LocalDate.of(2019, 6, 13));
        mv2.setWorth(BigDecimal.valueOf(50000000.00));
        mv2.setPlayer(player);
        marketValues.add(mv2);

        player.setMarketValues(marketValues);
        player.setMarketValueLastCheck(LocalDateTime.now());

        return playerRepository.save(player);
    }

    @Transactional
    private Player saveTransers(Player player){
        Set<Transfer> transfers = new HashSet<>();
        Transfer tr1 = new Transfer();
        tr1.setFromTeam("Wigan");
        tr1.setToTeam("Hull City");
        tr1.setDateOfTransfer(LocalDate.of(2015, 5, 31));
        tr1.setMarketValue("2000000");
        tr1.setTransferFee("End of Loan");
        tr1.setPlayer(player);
        transfers.add(tr1);

        Transfer tr2 = new Transfer();
        tr2.setFromTeam("Hull City");
        tr2.setToTeam("Leicester City");
        tr2.setDateOfTransfer(LocalDate.of(2017, 7, 1));
        tr2.setMarketValue("8000000");
        tr2.setTransferFee("13700000");
        tr2.setPlayer(player);
        transfers.add(tr2);

        player.setTransfers(transfers);
        player.setTransferLastCheck(LocalDateTime.now());

        return playerRepository.save(player);
    }

    @Transactional
    private Player saveTransfermarktInfo(Player player){
        Player foundPlayer = playerRepository.findById(player.getId()).get();

        TransfermarktInfo transfermarktInfo = new TransfermarktInfo();
        transfermarktInfo.setAge(26);
        transfermarktInfo.setClubTeam("Leicester City");
        transfermarktInfo.setContractUntil("30.06.2023");
        transfermarktInfo.setDateOfBirth("Mar 5, 1993");
        transfermarktInfo.setNationality("England");
        transfermarktInfo.setNationalTeam("England");
        transfermarktInfo.setPosition("Centre-Back");
        transfermarktInfo.setPlayer(foundPlayer);

        foundPlayer.setTransfermarktInfo(transfermarktInfo);

        return playerRepository.save(foundPlayer);
    }

    @Transactional
    private Player savePesDbInfo(Player player){
        PesDbInfo pesDbInfo = new PesDbInfo();
        pesDbInfo.setPlayerName("H. MAGUIRE");
        pesDbInfo.setTeamName("EAST MIDLANDS");
        pesDbInfo.setFoot(Foot.RIGHT);
        pesDbInfo.setWeekCondition('C');
        pesDbInfo.setPrimaryPosition(PesDbPosition.CENTRAL_DEFENDER);
        pesDbInfo.setAttackingProwess(64);
        pesDbInfo.setBallControl(78);
        pesDbInfo.setDribbling(73);
        pesDbInfo.setLowPass(79);
        pesDbInfo.setLoftedPass(74);
        pesDbInfo.setFinishing(60);
        pesDbInfo.setPlaceKicking(61);
        pesDbInfo.setSwerve(62);
        pesDbInfo.setHeader(89);
        pesDbInfo.setDefensiveProwess(86);
        pesDbInfo.setBallWinning(89);
        pesDbInfo.setKickingPower(73);
        pesDbInfo.setSpeed(66);
        pesDbInfo.setExplosivePower(65);
        pesDbInfo.setUnwaveringBalance(68);
        pesDbInfo.setPhysicalContact(93);
        pesDbInfo.setJump(79);
        pesDbInfo.setGoalkeeping(40);
        pesDbInfo.setGkCatch(40);
        pesDbInfo.setClearing(40);
        pesDbInfo.setReflexes(40);
        pesDbInfo.setCoverage(40);
        pesDbInfo.setStamina(82);
        pesDbInfo.setWeakFootUsage(2);
        pesDbInfo.setWeakFootAccuracy(2);
        pesDbInfo.setForm(6);
        pesDbInfo.setInjuryResistance(3);
        pesDbInfo.setOverallRating(83);
        pesDbInfo.setPlayingStyle(PlayingStyle.BUILD_UP);
//        Set<PlayerSkill> playerSkills = new HashSet<>();
//        playerSkills.add(PlayerSkill.HEADING);
//        playerSkills.add(PlayerSkill.WEIGHTED_PASS);
//        playerSkills.add(PlayerSkill.MAN_MARKING);
//        playerSkills.add(PlayerSkill.FIGHTING_SPIRIT);
//        pesDbInfo.setPlayerSkills(playerSkills);
        pesDbInfo.setPlayer(player);
        pesDbInfo.setId(player.getId());
        player.setPesDbInfo(pesDbInfo);

        return playerRepository.save(player);
    }

    @Transactional
    private Player saveCompetitionStatistics(Player player){
        Set<CompetitionStatistic> competitionStatistics = new HashSet<>();
        CompetitionStatistic cs1 = new CompetitionStatistic();
        cs1.setCompetition("FIFA World Cup");
        cs1.setStartedApps(6);
        cs1.setSubApps(1);
        cs1.setMins(645);
        cs1.setGoals(1);
        cs1.setAssists(1);
        cs1.setYellowCards(2);
        cs1.setRedCards(0);
        cs1.setShotsPerGame(BigDecimal.valueOf(1.6));
        cs1.setPassSuccess(BigDecimal.valueOf(88.8));
        cs1.setAerialsWon(BigDecimal.valueOf(5.9));
        cs1.setManOfTheMatch(1);
        cs1.setRating(BigDecimal.valueOf(7.22));
        cs1.setPlayer(player);
        competitionStatistics.add(cs1);

        CompetitionStatistic cs2 = new CompetitionStatistic();
        cs2.setCompetition("Premier League");
        cs2.setStartedApps(31);
        cs2.setSubApps(0);
        cs2.setMins(2599);
        cs2.setGoals(3);
        cs2.setAssists(0);
        cs2.setYellowCards(6);
        cs2.setRedCards(1);
        cs2.setShotsPerGame(BigDecimal.valueOf(1.0));
        cs2.setPassSuccess(BigDecimal.valueOf(85.6));
        cs2.setAerialsWon(BigDecimal.valueOf(3.8));
        cs2.setManOfTheMatch(2);
        cs2.setRating(BigDecimal.valueOf(7.01));
        cs2.setPlayer(player);
        competitionStatistics.add(cs2);

        player.setCompetitionStatistics(competitionStatistics);

        return playerRepository.save(player);
    }

    @Transactional
    private Player savePositionStatistics(Player player){
        Set<PositionStatistic> positionStatistics = new HashSet<>();
        PositionStatistic ps1 = new PositionStatistic();
        ps1.setPosition("DC");
        ps1.setApps(42);
        ps1.setGoals(4);
        ps1.setAssists(1);
        ps1.setRating(BigDecimal.valueOf(7.04));
        ps1.setPlayer(player);
        positionStatistics.add(ps1);

        PositionStatistic ps2 = new PositionStatistic();
        ps2.setPosition("Sub");
        ps2.setApps(1);
        ps2.setGoals(0);
        ps2.setAssists(0);
        ps2.setRating(BigDecimal.valueOf(6.27));
        ps2.setPlayer(player);
        positionStatistics.add(ps2);

        player.setPositionStatistics(positionStatistics);

        return playerRepository.save(player);
    }

    @Transactional
    private Player saveGameStatistics(Player player){
        Set<GameStatistic> gameStatistics = new HashSet<>();
        GameStatistic gs1 = new GameStatistic();
        gs1.setCompetition("Premier League");
        gs1.setDateOfGame(LocalDate.of(2019, 3, 16));
        gs1.setTeam1("Burnley");
        gs1.setTeam2("Leicester");
        gs1.setResult("1:2");
        gs1.setAssists(0);
        gs1.setGoals(0);
        gs1.setManOfTheMatch(false);
        gs1.setMinutesPlayed(4);
        gs1.setRating(BigDecimal.valueOf(5.24));
        gs1.setYellowCard(false);
        gs1.setRedCard(true);
        gs1.setPlayer(player);
        gameStatistics.add(gs1);

        GameStatistic gs2 = new GameStatistic();
        gs2.setCompetition("EURO Qualification Grp. A");
        gs2.setDateOfGame(LocalDate.of(2019, 3, 22));
        gs2.setTeam1("England");
        gs2.setTeam2("Czech Republic");
        gs2.setResult("5:0");
        gs2.setAssists(0);
        gs2.setGoals(0);
        gs2.setManOfTheMatch(false);
        gs2.setMinutesPlayed(90);
        gs2.setRating(BigDecimal.valueOf(0.00));
        gs2.setYellowCard(false);
        gs2.setRedCard(false);
        gs2.setPlayer(player);
        gameStatistics.add(gs2);

        player.setGameStatistics(gameStatistics);

        return playerRepository.save(player);
    }

    @Transactional
    private Player saveCharacteristic(Player player){
        Characteristic c = new Characteristic();
        c.getStrengths().add("Concentration");
        c.getStylesOfPlay().add("Indirect set-piece threat");
        c.getStylesOfPlay().add("Likes to dribble");
        c.getStylesOfPlay().add("Likes to play long balls");
        c.getStylesOfPlay().add("Plays the ball off the ground often");
        c.setPlayer(player);
        player.setCharacteristic(c);

        return playerRepository.save(player);
    }
}
