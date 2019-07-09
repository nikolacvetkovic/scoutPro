package xyz.riocode.scoutpro.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@NoArgsConstructor
@Getter
@Setter
@Embeddable
public class AppUserPlayerId implements Serializable {

    @Column(name = "app_user_id")
    private Long userId;
    @Column(name = "player_id")
    private Long playerId;

}
