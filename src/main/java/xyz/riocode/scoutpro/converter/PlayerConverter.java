package xyz.riocode.scoutpro.converter;

import org.springframework.stereotype.Component;
import xyz.riocode.scoutpro.dto.PlayerDashboardDTO;
import xyz.riocode.scoutpro.dto.PlayerFormDTO;
import xyz.riocode.scoutpro.model.*;

import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Locale;
import java.util.Optional;
import java.util.Set;

@Component
public class PlayerConverter{

    public PlayerFormDTO playerToPlayerFormDTO(Player player, String username) {
        PlayerFormDTO playerFormDTO = new PlayerFormDTO();
        playerFormDTO.setId(player.getId().toString());
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

    public Player playerFormDTOToPlayer(PlayerFormDTO playerDTO, String username){
        Player player = new Player();
        player.setId(Long.valueOf(playerDTO.getId()));
        AppUserPlayer appUserPlayer = new AppUserPlayer();
        appUserPlayer.setMyPlayer(playerDTO.isMyPlayer());
        AppUser appUser = new AppUser();
        appUser.setUsername(username);
        appUserPlayer.setAppUser(appUser);
        player.getUsers().add(appUserPlayer);
        player.setTransfermarktUrl(playerDTO.getTransfermarktUrl());
        player.setWhoScoredUrl(playerDTO.getWhoScoredUrl());
        player.setPesDbUrl(playerDTO.getPesDbUrl());
        player.setPsmlUrl(playerDTO.getPsmlUrl());

        return player;
    }

    public Set<PlayerDashboardDTO> playerToPlayerDashboardDTO(Set<Player> players, String username){
        Set<PlayerDashboardDTO> playerDashboardDTOS = new HashSet<>();
        for (Player player : players) {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy").withLocale(Locale.US);
            PlayerDashboardDTO playerDashboardDTO = new PlayerDashboardDTO();
            playerDashboardDTO.setId(player.getId().toString());
            playerDashboardDTO.setPlayerName(player.getPlayerName());
            playerDashboardDTO.setMyPlayer(player.getUsers().stream()
                    .filter(appUserPlayer -> appUserPlayer.getAppUser().getUsername().equals(username))
                    .map(AppUserPlayer::isMyPlayer)
                    .findFirst()
                    .get());

            playerDashboardDTO.setTmValue(player.getMarketValues().stream().findFirst().get().getWorth().toString());
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
            playerDashboardDTO.setAttackingProwess(player.getPesDbInfo().getAttackingProwess());
            playerDashboardDTO.setBallControl(player.getPesDbInfo().getBallControl());
            playerDashboardDTO.setDribbling(player.getPesDbInfo().getDribbling());
            playerDashboardDTO.setLowPass(player.getPesDbInfo().getLowPass());
            playerDashboardDTO.setLoftedPass(player.getPesDbInfo().getLoftedPass());
            playerDashboardDTO.setFinishing(player.getPesDbInfo().getFinishing());
            playerDashboardDTO.setPlaceKicking(player.getPesDbInfo().getPlaceKicking());
            playerDashboardDTO.setSwerve(player.getPesDbInfo().getSwerve());
            playerDashboardDTO.setHeader(player.getPesDbInfo().getHeader());
            playerDashboardDTO.setDefensiveProwess(player.getPesDbInfo().getDefensiveProwess());
            playerDashboardDTO.setBallWinning(player.getPesDbInfo().getBallWinning());
            playerDashboardDTO.setKickingPower(player.getPesDbInfo().getKickingPower());
            playerDashboardDTO.setSpeed(player.getPesDbInfo().getSpeed());
            playerDashboardDTO.setExplosivePower(player.getPesDbInfo().getExplosivePower());
            playerDashboardDTO.setUnwaveringBalance(player.getPesDbInfo().getUnwaveringBalance());
            playerDashboardDTO.setPhysicalContact(player.getPesDbInfo().getPhysicalContact());
            playerDashboardDTO.setJump(player.getPesDbInfo().getJump());
            playerDashboardDTO.setGoalkeeping(player.getPesDbInfo().getGoalkeeping());
            playerDashboardDTO.setGkCatch(player.getPesDbInfo().getGkCatch());
            playerDashboardDTO.setClearing(player.getPesDbInfo().getClearing());
            playerDashboardDTO.setReflexes(player.getPesDbInfo().getReflexes());
            playerDashboardDTO.setCoverage(player.getPesDbInfo().getCoverage());
            playerDashboardDTO.setStamina(player.getPesDbInfo().getStamina());
            playerDashboardDTO.setWeakFootUsage(player.getPesDbInfo().getWeakFootUsage());
            playerDashboardDTO.setWeakFootAccuracy(player.getPesDbInfo().getWeakFootAccuracy());
            playerDashboardDTO.setForm(player.getPesDbInfo().getForm());
            playerDashboardDTO.setInjuryResistance(player.getPesDbInfo().getInjuryResistance());
            playerDashboardDTO.setOverall(player.getPesDbInfo().getOverallRating());
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

            CompetitionStatistic competitionStatistic = player.getCompetitionStatistics().stream().findFirst().get();
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
        return playerDashboardDTOS;
    }
}
