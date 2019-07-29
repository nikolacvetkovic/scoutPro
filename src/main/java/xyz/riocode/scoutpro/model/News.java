package xyz.riocode.scoutpro.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "news")
public class News implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @Column(name = "content", columnDefinition = "text")
    private String content;

    @Basic(optional = false)
    @Column(name = "inserted_date")
    private LocalDateTime insertedDate;
    @ManyToOne(optional = false)
    @JoinColumn(name = "player_id", referencedColumnName = "id")
    private Player player;
}
