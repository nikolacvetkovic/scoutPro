package com.riocode.scoutpro.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 *
 * @author Nikola Cvetkovic
 */

@NoArgsConstructor
@Data
@Entity
@Table(name = "transfermarkt_info")
@NamedQueries({
    @NamedQuery(name = "TransfermarktInfo.findAll", query = "SELECT t FROM TransfermarktInfo t")
    , @NamedQuery(name = "TransfermarktInfo.findById", query = "SELECT t FROM TransfermarktInfo t WHERE t.id = :id")
    , @NamedQuery(name = "TransfermarktInfo.findByAge", query = "SELECT t FROM TransfermarktInfo t WHERE t.age = :age")
    , @NamedQuery(name = "TransfermarktInfo.findByNationality", query = "SELECT t FROM TransfermarktInfo t WHERE t.nationality = :nationality")
    , @NamedQuery(name = "TransfermarktInfo.findByNationalTeam", query = "SELECT t FROM TransfermarktInfo t WHERE t.nationalTeam = :nationalTeam")
    , @NamedQuery(name = "TransfermarktInfo.findByClubTeam", query = "SELECT t FROM TransfermarktInfo t WHERE t.clubTeam = :clubTeam")
    , @NamedQuery(name = "TransfermarktInfo.findByContractUntil", query = "SELECT t FROM TransfermarktInfo t WHERE t.contractUntil = :contractUntil")
    , @NamedQuery(name = "TransfermarktInfo.findByPosition", query = "SELECT t FROM TransfermarktInfo t WHERE t.position = :position")})
public class TransfermarktInfo implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    //@GenericGenerator(name = "myGen", strategy = "foreign", parameters = @Parameter(name = "property",value = "player"))
    //@GeneratedValue(generator = "myGen")
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "date_of_birth")
    private String dateOfBirth;
    @Basic(optional = false)
    @NotNull
    @Column(name = "age")
    private int age;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "nationality")
    private String nationality;
    @Basic(optional = false)
    @Size(min = 1, max = 50)
    @Column(name = "national_team")
    private String nationalTeam;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "club_team")
    private String clubTeam;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 12)
    @Column(name = "contract_until")
    private String contractUntil;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "position")
    private String position;
    @JsonBackReference
    @OneToOne(optional = false)
    //@JoinColumn(name = "id", referencedColumnName = "id")
    @MapsId
    private Player player;

    public TransfermarktInfo(String dateOfBirth, int age, String nationality, String nationalTeam, String clubTeam, String contractUntil, String position) {
        this.dateOfBirth = dateOfBirth;
        this.age = age;
        this.nationality = nationality;
        this.nationalTeam = nationalTeam;
        this.clubTeam = clubTeam;
        this.contractUntil = contractUntil;
        this.position = position;
    }
}
