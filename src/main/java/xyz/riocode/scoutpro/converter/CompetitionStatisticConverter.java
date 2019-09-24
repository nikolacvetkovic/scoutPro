package xyz.riocode.scoutpro.converter;

import org.springframework.stereotype.Component;
import xyz.riocode.scoutpro.dto.CompetitionStatisticDTO;
import xyz.riocode.scoutpro.model.CompetitionStatistic;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class CompetitionStatisticConverter {

    public List<CompetitionStatisticDTO> competitionStatisticsToCompetitionStatisticDTOs(Set<CompetitionStatistic> competitionStatistics){
        return competitionStatistics.stream().map(competitionStatistic -> {
            CompetitionStatisticDTO competitionStatisticDTO = new CompetitionStatisticDTO();
            competitionStatisticDTO.setCompetition(competitionStatistic.getCompetition());
            competitionStatisticDTO.setStartedApps(competitionStatistic.getStartedApps());
            competitionStatisticDTO.setSubApps(competitionStatistic.getSubApps());
            competitionStatisticDTO.setGoals(competitionStatistic.getGoals());
            competitionStatisticDTO.setAssists(competitionStatistic.getAssists());
            competitionStatisticDTO.setYellowCards(competitionStatistic.getYellowCards());
            competitionStatisticDTO.setRedCards(competitionStatistic.getRedCards());
            competitionStatisticDTO.setShotsPerGame(competitionStatistic.getShotsPerGame().toString());
            competitionStatisticDTO.setPassSuccess(competitionStatistic.getPassSuccess().toString());
            competitionStatisticDTO.setAerialsWon(competitionStatistic.getAerialsWon().toString());
            competitionStatisticDTO.setManOfTheMatch(competitionStatistic.getManOfTheMatch());
            competitionStatisticDTO.setRating(competitionStatistic.getRating().toString());
            return competitionStatisticDTO;
        }).collect(Collectors.toList());
    }

}
