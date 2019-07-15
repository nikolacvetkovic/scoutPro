package xyz.riocode.scoutpro.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class PlayerDTO {

    private Long id;
    private boolean myPlayer;
    @Pattern(regexp = "^(http(s)?://www\\.transfermarkt\\.com/.+/profil/spieler/)\\d+$", message = "Not valid Transfermarkt url")
    @Size(max = 256)
    @Column(name = "transfermarkt_url")
    private String transfermarktUrl;
    @Pattern(regexp = "^(http(s)?://www\\.whoscored\\.com/Players/\\d+/Show/).+$", message = "Not valid WhoScored url")
    @Size(max = 256)
    @Column(name = "who_scored_url")
    private String whoScoredUrl;
    @Pattern(regexp = "^(http://pesdb\\.net/pes20[1-9][0-9]/\\?id=)\\d+$", message = "Not valid PesDb url")
    @Size(max = 256)
    @Column(name = "pes_db_url")
    private String pesDbUrl;
    @Pattern(regexp = "^(http://psml\\.rs/(index\\.php)?\\?action=shwply&playerID=)\\d+$", message = "Not valid Psml url")
    @Size(max = 256)
    @Column(name = "psml_url")
    private String psmlUrl;

}
