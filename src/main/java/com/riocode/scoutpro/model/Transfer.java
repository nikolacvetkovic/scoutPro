package com.riocode.scoutpro.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;

/**
 *
 * @author Nikola Cvetkovic
 */
@NoArgsConstructor
@Data
@Entity
@Table(name = "transfer")
@NamedQueries({
    @NamedQuery(name = "Transfer.findAll", query = "SELECT t FROM Transfer t")
    , @NamedQuery(name = "Transfer.findById", query = "SELECT t FROM Transfer t WHERE t.id = :id")})
public class Transfer implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "from_team")
    private String fromTeam;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "to_team")
    private String toTeam;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    @Basic(optional = false)
    @NotNull
    @Column(name = "date_of_transfer")
    private LocalDate dateOfTransfer;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "market_value")
    private String marketValue;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "transfer_fee")
    private String transferFee;
    @JsonBackReference
    @ManyToOne(optional = false)
    @JoinColumn(name = "player_id", referencedColumnName = "id")
    private Player player;

    public Transfer(String fromTeam, String toTeam, LocalDate dateOfTransfer, String marketValue, String transferFee) {
        this.fromTeam = fromTeam;
        this.toTeam = toTeam;
        this.dateOfTransfer = dateOfTransfer;
        this.marketValue = marketValue;
        this.transferFee = transferFee;
    }
}
