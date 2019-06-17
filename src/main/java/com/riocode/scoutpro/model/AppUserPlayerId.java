package com.riocode.scoutpro.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@NoArgsConstructor
@Data
@Embeddable
public class AppUserPlayerId implements Serializable {

    @Column(name = "user_id")
    private Long userId;
    @Column(name = "player_id")
    private Long playerId;

}
