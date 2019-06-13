package com.riocode.scoutpro.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.riocode.scoutpro.jpa.converter.ListStringConverter;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    @NamedQuery(name = "WhoScoredInfo.findAll", query = "SELECT w FROM WhoScoredInfo w")
    , @NamedQuery(name = "WhoScoredInfo.findById", query = "SELECT w FROM WhoScoredInfo w WHERE w.id = :id")})
public class WhoScoredInfo implements Serializable {

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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "whoScoredInfo", orphanRemoval = true)
    private Set<StatisticsByPosition> statisticsByPositions = new HashSet<>();
    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "whoScoredInfo", orphanRemoval = true)
    private Set<StatisticsByCompetition> statisticsByCompetitions = new HashSet<>();
    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "whoScoredInfo", orphanRemoval = true)
    private Set<WhoScoredGame> whoScoredGames = new HashSet<>();
    @JsonBackReference
    @JoinColumn(name = "player_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Player player;
}
