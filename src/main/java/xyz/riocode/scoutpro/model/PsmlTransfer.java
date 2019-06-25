package xyz.riocode.scoutpro.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@NoArgsConstructor
@Data
@Entity
@Table(name = "psml_transfer")
public class PsmlTransfer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @Column(name = "from_team")
    private String fromTeam;
    @Basic(optional = false)
    @Column(name = "to_team")
    private String toTeam;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    @Basic(optional = false)
    @Column(name = "date_of_transfer")
    private LocalDateTime dateOfTransfer;
    @ManyToOne(optional = false)
    @JoinColumn(name = "psml_info_id",referencedColumnName = "id")
    private PsmlInfo psmlInfo;
}
