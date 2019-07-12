package xyz.riocode.scoutpro.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import xyz.riocode.scoutpro.jpa.converter.SetStringStringConverter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Set;

/**
 *
 * @author Nikola Cvetkovic
 */

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "characteristic")
//@NamedQueries({
//    @NamedQuery(name = "Characteristic.findAll", query = "SELECT w FROM Characteristic w")
//    , @NamedQuery(name = "Characteristic.findById", query = "SELECT w FROM Characteristic w WHERE w.id = :id")})
public class Characteristic implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private Long id;
    @Size(max = 512)
    @Convert(converter = SetStringStringConverter.class)
    @Column(name = "strengths")
    private Set<String> strengths;
    @Size(max = 512)
    @Convert(converter = SetStringStringConverter.class)
    @Column(name = "weaknesses")
    private Set<String> weaknesses;
    @Size(max = 512)
    @Convert(converter = SetStringStringConverter.class)
    @Column(name = "style_of_play")
    private Set<String> stylesOfPlay;
    @JsonBackReference
    @OneToOne(optional = false)
    @MapsId
    @JoinColumn(name = "id")
    private Player player;
}
