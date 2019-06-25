package xyz.riocode.scoutpro.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Data
@Entity
@Table(name = "user_player")
public class AppUserPlayer {

    @EmbeddedId
    private AppUserPlayerId appUserPlayerId;

    @ManyToOne
    @MapsId("userId")
    private AppUser appUser;

    @ManyToOne
    @MapsId("playerId")
    private Player player;

    @Basic(optional = false)
    @Column(name = "my_player")
    private boolean myPlayer;

}
