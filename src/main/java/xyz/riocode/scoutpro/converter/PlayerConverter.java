package xyz.riocode.scoutpro.converter;

import org.springframework.stereotype.Component;
import xyz.riocode.scoutpro.dto.PlayerDTO;
import xyz.riocode.scoutpro.model.AppUser;
import xyz.riocode.scoutpro.model.AppUserPlayer;
import xyz.riocode.scoutpro.model.Player;

@Component
public class PlayerConverter{

    public PlayerDTO playerToPlayerDTO(Player player, String username) {
        PlayerDTO playerDTO = new PlayerDTO();
        playerDTO.setId(player.getId());
        playerDTO.setMyPlayer(player.getUsers().stream()
                .filter(appUserPlayer -> appUserPlayer.getAppUser().getUsername().equals(username))
                .map(AppUserPlayer::isMyPlayer)
                .findFirst()
                .get());
        playerDTO.setTransfermarktUrl(player.getTransfermarktUrl());
        playerDTO.setWhoScoredUrl(player.getWhoScoredUrl());
        playerDTO.setPesDbUrl(player.getPesDbUrl());
        playerDTO.setPsmlUrl(player.getPsmlUrl());

        return playerDTO;
    }

    public Player playerDTOToPlayer(PlayerDTO playerDTO, String username){
        Player player = new Player();
        player.setId(playerDTO.getId());
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
}
