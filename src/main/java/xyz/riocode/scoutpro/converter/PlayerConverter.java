package xyz.riocode.scoutpro.converter;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import xyz.riocode.scoutpro.dto.DashboardDTO;
import xyz.riocode.scoutpro.dto.PlayerCompleteDTO;
import xyz.riocode.scoutpro.dto.PlayerDashboardDTO;
import xyz.riocode.scoutpro.dto.PlayerFormDTO;
import xyz.riocode.scoutpro.model.*;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Component
public class PlayerConverter{

    private final TransferConverter transferConverter;
    private final MarketValueConverter marketValueConverter;
    private final CompetitionStatisticConverter competitionStatisticConverter;
    private final PositionStatisticConverter positionStatisticConverter;
    private final GameStatisticConverter gameStatisticConverter;
    private final PsmlTransferConverter psmlTransferConverter;

    public PlayerConverter(TransferConverter transferConverter, MarketValueConverter marketValueConverter,
                           CompetitionStatisticConverter competitionStatisticConverter, PositionStatisticConverter positionStatisticConverter,
                           GameStatisticConverter gameStatisticConverter, PsmlTransferConverter psmlTransferConverter) {
        this.transferConverter = transferConverter;
        this.marketValueConverter = marketValueConverter;
        this.competitionStatisticConverter = competitionStatisticConverter;
        this.positionStatisticConverter = positionStatisticConverter;
        this.gameStatisticConverter = gameStatisticConverter;
        this.psmlTransferConverter = psmlTransferConverter;
    }

    public PlayerFormDTO playerToPlayerFormDTO(Player player, String username) {
        PlayerFormDTO playerFormDTO = new PlayerFormDTO();
        playerFormDTO.setId(player.getId().toString());
        playerFormDTO.setPlayerName(player.getPlayerName());
        playerFormDTO.setMyPlayer(player.getUsers().stream()
                .filter(appUserPlayer -> appUserPlayer.getAppUser().getUsername().equals(username))
                .map(AppUserPlayer::isMyPlayer)
                .findFirst()
                .get());
        playerFormDTO.setTransfermarktUrl(player.getTransfermarktUrl());
        playerFormDTO.setWhoScoredUrl(player.getWhoScoredUrl());
        playerFormDTO.setPesDbUrl(player.getPesDbUrl());
        playerFormDTO.setPsmlUrl(player.getPsmlUrl());

        return playerFormDTO;
    }

    public Player playerFormDTOToPlayer(PlayerFormDTO playerDTO){
        Player player = new Player();
        if(playerDTO.getId() != null && playerDTO.getId().length() != 0) player.setId(Long.valueOf(playerDTO.getId()));
        AppUserPlayerId appUserPlayerId = new AppUserPlayerId();
        AppUserPlayer appUserPlayer = new AppUserPlayer();
        appUserPlayer.setAppUserPlayerId(appUserPlayerId);
        appUserPlayer.setMyPlayer(playerDTO.isMyPlayer());
        appUserPlayer.setPlayer(player);
        player.getUsers().add(appUserPlayer);
        player.setTransfermarktUrl(playerDTO.getTransfermarktUrl());
        player.setWhoScoredUrl(playerDTO.getWhoScoredUrl());
        player.setPesDbUrl(playerDTO.getPesDbUrl());
        player.setPsmlUrl(playerDTO.getPsmlUrl());

        return player;
    }

    public DashboardDTO playersToDashboardDTO(Page<Player> playersPage, String username){
        DashboardDTO dashboardDTO = new DashboardDTO();
        List<PlayerDashboardDTO> playerDashboardDTOS = new ArrayList<>();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss").withLocale(Locale.US);
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy").withLocale(Locale.US);
        for (Player player : playersPage.getContent()) {
            PlayerDashboardDTO playerDashboardDTO = new PlayerDashboardDTO();
            playerDashboardDTO.setId(player.getId().toString());
            playerDashboardDTO.setPlayerName(player.getPlayerName());
            playerDashboardDTO.setMyPlayer(player.getUsers().stream()
                    .filter(appUserPlayer -> appUserPlayer.getAppUser().getUsername().equals(username))
                    .map(AppUserPlayer::isMyPlayer)
                    .findFirst()
                    .get());

            playerDashboardDTO.setTmCurrentValue(player.getMarketValues().stream().findFirst().get().getWorth().toString());
            playerDashboardDTO.setTmValueLastChanged(player.getMarketValues().stream().findFirst().get().getDatePoint().format(dateFormatter));
            playerDashboardDTO.setTmValueLastCheck(player.getMarketValueLastCheck().format(dateTimeFormatter));
            playerDashboardDTO.setAge(String.valueOf(player.getTransfermarktInfo().getAge()));
            playerDashboardDTO.setNationalTeam(player.getTransfermarktInfo().getNationalTeam());
            playerDashboardDTO.setClubTeam(player.getTransfermarktInfo().getClubTeam());
            playerDashboardDTO.setContractUntil(player.getTransfermarktInfo().getContractUntil());

            playerDashboardDTO.setPesDbPlayerName(player.getPesDbInfo().getPlayerName());
            playerDashboardDTO.setPesDbTeamName(player.getPesDbInfo().getTeamName());
            playerDashboardDTO.setFoot(player.getPesDbInfo().getFoot().toString());
            playerDashboardDTO.setWeekCondition(player.getPesDbInfo().getWeekCondition().toString());
            playerDashboardDTO.setPosition(player.getPesDbInfo().getPrimaryPosition());
            playerDashboardDTO.setOtherStrongPositions(player.getPesDbInfo().getOtherStrongPositions());
            playerDashboardDTO.setOtherWeakPositions(player.getPesDbInfo().getOtherWeakPositions());
            playerDashboardDTO.setOffensiveAwareness(player.getPesDbInfo().getOffensiveAwareness());
            playerDashboardDTO.setBallControl(player.getPesDbInfo().getBallControl());
            playerDashboardDTO.setDribbling(player.getPesDbInfo().getDribbling());
            playerDashboardDTO.setTightPossession(player.getPesDbInfo().getTightPossession());
            playerDashboardDTO.setLowPass(player.getPesDbInfo().getLowPass());
            playerDashboardDTO.setLoftedPass(player.getPesDbInfo().getLoftedPass());
            playerDashboardDTO.setFinishing(player.getPesDbInfo().getFinishing());
            playerDashboardDTO.setHeading(player.getPesDbInfo().getHeading());
            playerDashboardDTO.setPlaceKicking(player.getPesDbInfo().getPlaceKicking());
            playerDashboardDTO.setCurl(player.getPesDbInfo().getCurl());
            playerDashboardDTO.setSpeed(player.getPesDbInfo().getSpeed());
            playerDashboardDTO.setAcceleration(player.getPesDbInfo().getAcceleration());
            playerDashboardDTO.setKickingPower(player.getPesDbInfo().getKickingPower());
            playerDashboardDTO.setJump(player.getPesDbInfo().getJump());
            playerDashboardDTO.setPhysicalContact(player.getPesDbInfo().getPhysicalContact());
            playerDashboardDTO.setBalance(player.getPesDbInfo().getBalance());
            playerDashboardDTO.setStamina(player.getPesDbInfo().getStamina());
            playerDashboardDTO.setDefensiveAwareness(player.getPesDbInfo().getDefensiveAwareness());
            playerDashboardDTO.setBallWinning(player.getPesDbInfo().getBallWinning());
            playerDashboardDTO.setAggression(player.getPesDbInfo().getAggression());
            playerDashboardDTO.setGkAwareness(player.getPesDbInfo().getGkAwareness());
            playerDashboardDTO.setGkCatching(player.getPesDbInfo().getGkCatching());
            playerDashboardDTO.setGkClearing(player.getPesDbInfo().getGkClearing());
            playerDashboardDTO.setGkReflexes(player.getPesDbInfo().getGkReflexes());
            playerDashboardDTO.setGkReach(player.getPesDbInfo().getGkReach());
            playerDashboardDTO.setWeakFootUsage(player.getPesDbInfo().getWeakFootUsage());
            playerDashboardDTO.setWeakFootAccuracy(player.getPesDbInfo().getWeakFootAccuracy());
            playerDashboardDTO.setForm(player.getPesDbInfo().getForm());
            playerDashboardDTO.setInjuryResistance(player.getPesDbInfo().getInjuryResistance());
            playerDashboardDTO.setOverallRating(player.getPesDbInfo().getOverallRating());
            playerDashboardDTO.setPlayingStyle(player.getPesDbInfo().getPlayingStyle());
            playerDashboardDTO.setPlayerSkills(player.getPesDbInfo().getPlayerSkills());
            playerDashboardDTO.setComPlayingStyles(player.getPesDbInfo().getComPlayingStyles());
            playerDashboardDTO.setPesDbLastCheck(player.getPesDbInfo().getLastCheck().format(dateTimeFormatter));

            playerDashboardDTO.setPsmlTeam(player.getPsmlInfo().getPsmlTeam());
            playerDashboardDTO.setPsmlValue(player.getPsmlInfo().getPsmlValue().toString());
            Optional<PsmlTransfer> optionalPsmlTransfer = player.getPsmlInfo().getPsmlTransfers().stream().findFirst();
            if (optionalPsmlTransfer.isPresent()) {
                PsmlTransfer psmlTransfer = optionalPsmlTransfer.get();
                playerDashboardDTO.setPsmlLastTransferDate(psmlTransfer.getDateOfTransfer().format(dateTimeFormatter));
                playerDashboardDTO.setPsmlLastTransferFromTeam(psmlTransfer.getFromTeam());
                playerDashboardDTO.setPsmlLastTransferToTeam(psmlTransfer.getToTeam());
                playerDashboardDTO.setPsmlLastTransferFee(psmlTransfer.getTransferFee().toString());
            }
            playerDashboardDTO.setPsmlLastCheck(player.getPsmlInfo().getLastCheck().format(dateTimeFormatter));

            Optional<CompetitionStatistic> competitionStatisticOptional = player.getCompetitionStatistics().stream().findFirst();
            if(competitionStatisticOptional.isPresent()) {
                CompetitionStatistic competitionStatistic = competitionStatisticOptional.get();
                playerDashboardDTO.setTotalStartedApps(competitionStatistic.getStartedApps());
                playerDashboardDTO.setTotalMins(competitionStatistic.getMins());
                playerDashboardDTO.setTotalAssists(competitionStatistic.getAssists());
                playerDashboardDTO.setTotalGoals(competitionStatistic.getGoals());
                playerDashboardDTO.setTotalAssists(competitionStatistic.getAssists());
                playerDashboardDTO.setAverageShotsPerGame(competitionStatistic.getShotsPerGame().toString());
                playerDashboardDTO.setAveragePassSuccess(competitionStatistic.getPassSuccess().toString());
                playerDashboardDTO.setAverageAerialsWon(competitionStatistic.getAerialsWon().toString());
                playerDashboardDTO.setTotalManOfTheMatch(competitionStatistic.getManOfTheMatch());
                playerDashboardDTO.setAverageRating(competitionStatistic.getRating().toString());
                playerDashboardDTO.setStatisticsLastCheck(player.getStatisticLastCheck().format(dateTimeFormatter));

                playerDashboardDTOS.add(playerDashboardDTO);
            }
        }
        dashboardDTO.setPlayers(playerDashboardDTOS);
        dashboardDTO.setCurrentPage(playersPage.getNumber());
        dashboardDTO.setTotalPages(playersPage.getTotalPages());

        return dashboardDTO;
    }

    public PlayerCompleteDTO playerToPlayerCompleteDTO(Player player, String username){
        PlayerCompleteDTO playerCompleteDTO = new PlayerCompleteDTO();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss").withLocale(Locale.US);

        playerCompleteDTO.setId(player.getId().toString());
        playerCompleteDTO.setMyPlayer(player.getUsers().stream()
                .filter(appUserPlayer -> appUserPlayer.getAppUser().getUsername().equals(username))
                .map(AppUserPlayer::isMyPlayer)
                .findFirst()
                .get());

        playerCompleteDTO.setPlayerName(player.getPlayerName());
        playerCompleteDTO.setDateOfBirth(player.getTransfermarktInfo().getDateOfBirth());
        playerCompleteDTO.setAge(String.valueOf(player.getTransfermarktInfo().getAge()));
        playerCompleteDTO.setNationality(player.getTransfermarktInfo().getNationality());
        playerCompleteDTO.setNationalTeam(player.getTransfermarktInfo().getNationalTeam());
        playerCompleteDTO.setClubTeam(player.getTransfermarktInfo().getClubTeam());
        playerCompleteDTO.setContractUntil(player.getTransfermarktInfo().getContractUntil());
        playerCompleteDTO.setTmPosition(player.getTransfermarktInfo().getPosition());

        playerCompleteDTO.setTransferDTOS(transferConverter.transfersToTransferDTOs(player.getTransfers()));
        playerCompleteDTO.setTransferLastCheck(player.getTransferLastCheck().format(dateTimeFormatter));
        playerCompleteDTO.setMarketValueDTOS(marketValueConverter.marketValuesToMarketValueDTOs(player.getMarketValues()));
        playerCompleteDTO.setMarketValueLastCheck(player.getMarketValueLastCheck().format(dateTimeFormatter));

        playerCompleteDTO.setCompetitionStatisticDTOS(competitionStatisticConverter.competitionStatisticsToCompetitionStatisticDTOs(player.getCompetitionStatistics()));
        playerCompleteDTO.setPositionStatisticDTOS(positionStatisticConverter.positionStatisticsToPositionStatisticDTOs(player.getPositionStatistics()));
        playerCompleteDTO.setGameStatisticDTOS(gameStatisticConverter.gameStatisticsToGameStatisticDTOs(player.getGameStatistics()));
        playerCompleteDTO.setStatisticLastCheck(player.getStatisticLastCheck().format(dateTimeFormatter));
        playerCompleteDTO.setStrengths(player.getCharacteristic().getStrengths());
        playerCompleteDTO.setWeaknesses(player.getCharacteristic().getWeaknesses());
        playerCompleteDTO.setStylesOfPlay(player.getCharacteristic().getStylesOfPlay());

        playerCompleteDTO.setPesDbPlayerName(player.getPesDbInfo().getPlayerName());
        playerCompleteDTO.setPesDbTeamName(player.getPesDbInfo().getTeamName());
        playerCompleteDTO.setFoot(player.getPesDbInfo().getFoot().toString());
        playerCompleteDTO.setWeekCondition(player.getPesDbInfo().getWeekCondition().toString());
        playerCompleteDTO.setPosition(player.getPesDbInfo().getPrimaryPosition());
        playerCompleteDTO.setOtherStrongPositions(player.getPesDbInfo().getOtherStrongPositions());
        playerCompleteDTO.setOtherWeakPositions(player.getPesDbInfo().getOtherWeakPositions());
        playerCompleteDTO.setOffensiveAwareness(player.getPesDbInfo().getOffensiveAwareness());
        playerCompleteDTO.setBallControl(player.getPesDbInfo().getBallControl());
        playerCompleteDTO.setDribbling(player.getPesDbInfo().getDribbling());
        playerCompleteDTO.setTightPossession(player.getPesDbInfo().getTightPossession());
        playerCompleteDTO.setLowPass(player.getPesDbInfo().getLowPass());
        playerCompleteDTO.setLoftedPass(player.getPesDbInfo().getLoftedPass());
        playerCompleteDTO.setFinishing(player.getPesDbInfo().getFinishing());
        playerCompleteDTO.setHeading(player.getPesDbInfo().getHeading());
        playerCompleteDTO.setPlaceKicking(player.getPesDbInfo().getPlaceKicking());
        playerCompleteDTO.setCurl(player.getPesDbInfo().getCurl());
        playerCompleteDTO.setSpeed(player.getPesDbInfo().getSpeed());
        playerCompleteDTO.setAcceleration(player.getPesDbInfo().getAcceleration());
        playerCompleteDTO.setKickingPower(player.getPesDbInfo().getKickingPower());
        playerCompleteDTO.setJump(player.getPesDbInfo().getJump());
        playerCompleteDTO.setPhysicalContact(player.getPesDbInfo().getPhysicalContact());
        playerCompleteDTO.setBalance(player.getPesDbInfo().getBalance());
        playerCompleteDTO.setStamina(player.getPesDbInfo().getStamina());
        playerCompleteDTO.setDefensiveAwareness(player.getPesDbInfo().getDefensiveAwareness());
        playerCompleteDTO.setBallWinning(player.getPesDbInfo().getBallWinning());
        playerCompleteDTO.setAggression(player.getPesDbInfo().getAggression());
        playerCompleteDTO.setGkAwareness(player.getPesDbInfo().getGkAwareness());
        playerCompleteDTO.setGkCatching(player.getPesDbInfo().getGkCatching());
        playerCompleteDTO.setGkClearing(player.getPesDbInfo().getGkClearing());
        playerCompleteDTO.setGkReflexes(player.getPesDbInfo().getGkReflexes());
        playerCompleteDTO.setGkReach(player.getPesDbInfo().getGkReach());
        playerCompleteDTO.setWeakFootUsage(player.getPesDbInfo().getWeakFootUsage());
        playerCompleteDTO.setWeakFootAccuracy(player.getPesDbInfo().getWeakFootAccuracy());
        playerCompleteDTO.setForm(player.getPesDbInfo().getForm());
        playerCompleteDTO.setInjuryResistance(player.getPesDbInfo().getInjuryResistance());
        playerCompleteDTO.setOverallRating(player.getPesDbInfo().getOverallRating());
        playerCompleteDTO.setPlayingStyle(player.getPesDbInfo().getPlayingStyle());
        playerCompleteDTO.setPlayerSkills(player.getPesDbInfo().getPlayerSkills());
        playerCompleteDTO.setComPlayingStyles(player.getPesDbInfo().getComPlayingStyles());
        playerCompleteDTO.setPesDbLastCheck(player.getPesDbInfo().getLastCheck().format(dateTimeFormatter));

        playerCompleteDTO.setPsmlTeam(player.getPsmlInfo().getPsmlTeam());
        playerCompleteDTO.setPsmlValue(player.getPsmlInfo().getPsmlValue().toString());
        playerCompleteDTO.setPsmlTransferDTOS(psmlTransferConverter.psmlTransfersToPsmlTransferDTOs(player.getPsmlInfo().getPsmlTransfers()));
        playerCompleteDTO.setPsmlLastCheck(player.getPsmlInfo().getLastCheck().format(dateTimeFormatter));

        return playerCompleteDTO;
    }
}
