package xyz.riocode.scoutpro.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.NoArgsConstructor;
import xyz.riocode.scoutpro.jpa.converter.ListStringConverter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Nikola Cvetkovic
 */

@NoArgsConstructor
@Data
@Entity
@Table(name = "who_scored_info")
@NamedQueries({
    @NamedQuery(name = "Characteristic.findAll", query = "SELECT w FROM Characteristic w")
    , @NamedQuery(name = "Characteristic.findById", query = "SELECT w FROM Characteristic w WHERE w.id = :id")})
public class Characteristic implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Size(max = 512)
    @Convert(converter = ListStringConverter.class)
    @Column(name = "strengths")
    private List<String> strengths;
    @Size(max = 512)
    @Convert(converter = ListStringConverter.class)
    @Column(name = "weaknesses")
    private List<String> weaknesses;
    @Size(max = 512)
    @Convert(converter = ListStringConverter.class)
    @Column(name = "style_of_play")
    private Set<String> stylesOfPlay;
    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "characteristic", orphanRemoval = true)
    private Set<PositionStatistic> positionStatistics = new HashSet<>();
    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "characteristic", orphanRemoval = true)
    private Set<CompetitionStatistic> competitionStatistics = new HashSet<>();
    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "characteristic", orphanRemoval = true)
    private Set<GameStatistic> gameStatistics = new HashSet<>();
    @JsonBackReference
    @JoinColumn(name = "player_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Player player;
}
