package xyz.riocode.scoutpro.bootstrap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import xyz.riocode.scoutpro.enums.Foot;
import xyz.riocode.scoutpro.model.*;
import xyz.riocode.scoutpro.repository.*;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Component
public class DataLoader implements CommandLineRunner {

    private final PlayerRepository playerRepository;
    private final AppUserRepository appUserRepository;
    private final TransfermarktInfoRepository transfermarktInfoRepository;
    private final PesDbInfoRepository pesDbInfoRepository;
    private final PsmlInfoRepository psmlInfoRepository;
    private final CharacteristicRepository characteristicRepository;
    private static final Logger LOGGER = LogManager.getLogger(DataLoader.class.getName());

    public DataLoader(PlayerRepository playerRepository, AppUserRepository appUserRepository, TransfermarktInfoRepository transfermarktInfoRepository, PesDbInfoRepository pesDbInfoRepository, PsmlInfoRepository psmlInfoRepository, CharacteristicRepository characteristicRepository) {
        this.playerRepository = playerRepository;
        this.appUserRepository = appUserRepository;
        this.transfermarktInfoRepository = transfermarktInfoRepository;
        this.pesDbInfoRepository = pesDbInfoRepository;
        this.psmlInfoRepository = psmlInfoRepository;
        this.characteristicRepository = characteristicRepository;
    }

    @Transactional
    @Override
    public void run(String... args) throws Exception {
        LOGGER.info("<<< Data loading started >>>");

        AppUser savedUser = saveUser();

        LOGGER.info("<<< User saved >>>");

        Player savedPlayer = savePlayer(savedUser);

        LOGGER.info("<<< Player <<" + savedPlayer.getPlayerName() + ">> saved >>>");

        saveMarketValues(savedPlayer);

        LOGGER.info("<<< Market Values created and saved >>>");

        saveTransers(savedPlayer);

        LOGGER.info("<<< Transfers created and saved >>>");

        saveTransfermarktInfo(savedPlayer);

        LOGGER.info("<<< Transfermarkt created and saved >>>");

        savePesDbInfo(savedPlayer);

        LOGGER.info("<<< PesDbInfo saved, Player updated >>>");
        
        saveCompetitionStatistics(savedPlayer);

        LOGGER.info("<<< Competition Statistics saved, Player updated >>>");

        savePositionStatistics(savedPlayer);

        LOGGER.info("<<< Position Statistics saved, Player updated >>>");

        saveGameStatistics(savedPlayer);

        LOGGER.info("<<< Game Statistics saved, Player updated >>>");

        saveCharacteristic(savedPlayer);

        LOGGER.info("<<< Characteristic saved, Player updated >>>");

        savePsmlInfo(savedPlayer);

        LOGGER.info("<<< PsmlInfo and PsmlTransfers saved, Player updated >>>");

        LOGGER.info("<<< Data loading finished >>>");
    }


    private AppUser saveUser(){
        AppUser user = new AppUser();
        user.setUsername("cvele");
        user.setPassword("asdasd");

        return appUserRepository.saveAndFlush(user);
    }


    private Player savePlayer(AppUser appUser){
        Player player = new Player();
        player.setPlayerName("Harry Maguire");
        player.setTransfermarktUrl("https://www.transfermarkt.com/harry-maguire/profil/spieler/177907");
        player.setWhoScoredUrl("https://www.whoscored.com/Players/99487/Show/Harry-Maguire");
        player.setPesDbUrl("http://pesdb.net/pes2019/?id=109329");
        player.setPsmlUrl("http://psml.rs/?action=shwply&playerID=109329");

        AppUserPlayerId appUserPlayerId = new AppUserPlayerId();
        AppUserPlayer appUserPlayer = new AppUserPlayer();
        appUserPlayer.setMyPlayer(true);
        appUserPlayer.setAppUser(appUser);
        appUserPlayer.setPlayer(player);
        appUserPlayer.setAppUserPlayerId(appUserPlayerId);
        appUser.getPlayers().add(appUserPlayer);
        player.getUsers().add(appUserPlayer);

        return playerRepository.save(player);
    }

    private void saveMarketValues(Player player){
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

        playerRepository.save(player);
    }

    private void saveTransers(Player player){
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

        playerRepository.save(player);
    }

    private void saveTransfermarktInfo(Player player){
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

        transfermarktInfoRepository.save(transfermarktInfo);
    }

    private void savePesDbInfo(Player player){
        PesDbInfo pesDbInfo = new PesDbInfo();
        pesDbInfo.setPlayerName("H. MAGUIRE");
        pesDbInfo.setTeamName("EAST MIDLANDS");
        pesDbInfo.setFoot(Foot.RIGHT);
        pesDbInfo.setWeekCondition('C');
        pesDbInfo.setPrimaryPosition("CB");
        Set<String> otherStrongPositions = new HashSet<>();
        otherStrongPositions.add("CMF");
        pesDbInfo.setOtherStrongPositions(otherStrongPositions);
        Set<String> otherWeakPositions = new HashSet<>();
        otherWeakPositions.add("RB");
        pesDbInfo.setOtherWeakPositions(otherWeakPositions);
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
        pesDbInfo.setPlayingStyle("Build Up");
        Set<String> playerSkills = new HashSet<>();
        playerSkills.add("Heading");
        playerSkills.add("Weighted Pass");
        playerSkills.add("Man Marking");
        playerSkills.add("Fighting Spirit");
        pesDbInfo.setPlayerSkills(playerSkills);
        pesDbInfo.setComPlayingStyles(Collections.emptySet());
        pesDbInfo.setLastCheck(LocalDateTime.now());
        pesDbInfo.setPlayer(player);

        pesDbInfoRepository.save(pesDbInfo);
    }

    private void saveCompetitionStatistics(Player player){
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
        player.setStatisticLastCheck(LocalDateTime.now());

        playerRepository.save(player);
    }

    private void savePositionStatistics(Player player){
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
        player.setStatisticLastCheck(LocalDateTime.now());

        playerRepository.save(player);
    }

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
        player.setStatisticLastCheck(LocalDateTime.now());

        return playerRepository.save(player);
    }

    private void saveCharacteristic(Player player){
        Characteristic c = new Characteristic();
        Set<String> strengths = new HashSet<>();
        strengths.add("Concentration");
        c.setStrengths(strengths);
        Set<String> stylesOfPlay = new HashSet<>();
        stylesOfPlay.add("Indirect set-piece threat");
        stylesOfPlay.add("Likes to dribble");
        stylesOfPlay.add("Likes to play long balls");
        stylesOfPlay.add("Plays the ball off the ground often");
        c.setStylesOfPlay(stylesOfPlay);
        c.setWeaknesses(Collections.EMPTY_SET);
        c.setPlayer(player);

        characteristicRepository.save(c);
    }

    private void savePsmlInfo(Player player){
        PsmlInfo psmlInfo = new PsmlInfo();
        psmlInfo.setPsmlTeam("Atomic Ants");
        psmlInfo.setPsmlValue(BigDecimal.valueOf(15000000));
        PsmlTransfer psmlTransfer = new PsmlTransfer();
        psmlTransfer.setDateOfTransfer(LocalDateTime.now());
        psmlTransfer.setFromTeam("Free Agent");
        psmlTransfer.setToTeam("Atomic Ants");
        psmlTransfer.setPsmlInfo(psmlInfo);
        psmlInfo.setPsmlTransfers(new HashSet<>(Arrays.asList(psmlTransfer)));
        psmlInfo.setLastCheck(LocalDateTime.now());
        psmlInfo.setPlayer(player);

        player.setPsmlInfo(psmlInfo);

        psmlInfoRepository.save(psmlInfo);
    }
}
