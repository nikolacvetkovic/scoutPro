package xyz.riocode.scoutpro.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "app_user_player")
public class AppUserPlayer {

    @EmbeddedId
    private AppUserPlayerId appUserPlayerId;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "app_user_id")
    private AppUser appUser;

    @ManyToOne
    @MapsId("playerId")
    private Player player;

    @Basic(optional = false)
    @Column(name = "my_player")
    private boolean myPlayer;

}
