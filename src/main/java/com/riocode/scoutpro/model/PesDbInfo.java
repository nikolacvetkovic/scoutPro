package com.riocode.scoutpro.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.riocode.scoutpro.jpa.converter.ListStringConverter;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Nikola Cvetkovic
 */

@Entity
@Table(name = "pesdbinfo", catalog = "scout_pro_development", schema = "")
@NamedQueries({
    @NamedQuery(name = "PesDbInfo.findAll", query = "SELECT p FROM PesDbInfo p")
    , @NamedQuery(name = "PesDbInfo.findById", query = "SELECT p FROM PesDbInfo p WHERE p.id = :id")
    , @NamedQuery(name = "PesDbInfo.findBySeason", query = "SELECT p FROM PesDbInfo p WHERE p.season = :season")
    , @NamedQuery(name = "PesDbInfo.findByTeamName", query = "SELECT p FROM PesDbInfo p WHERE p.teamName = :teamName")
    , @NamedQuery(name = "PesDbInfo.findByFoot", query = "SELECT p FROM PesDbInfo p WHERE p.foot = :foot")
    , @NamedQuery(name = "PesDbInfo.findByWeekCondition", query = "SELECT p FROM PesDbInfo p WHERE p.weekCondition = :weekCondition")
    , @NamedQuery(name = "PesDbInfo.findByPrimaryPosition", query = "SELECT p FROM PesDbInfo p WHERE p.primaryPosition = :primaryPosition")
    , @NamedQuery(name = "PesDbInfo.findByOtherPositions", query = "SELECT p FROM PesDbInfo p WHERE p.otherPositions = :otherPositions")
    , @NamedQuery(name = "PesDbInfo.findByLastChange", query = "SELECT p FROM PesDbInfo p WHERE p.lastChange = :lastChange")})
public class PesDbInfo implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "season")
    private String season;
    @Basic(optional = false)
    @NotNull()
    @Size(min = 1, max = 50)
    @Column(name = "teamName")
    private String teamName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "foot")
    private String foot;
    @Basic(optional = false)
    @NotNull
    @Column(name = "weekCondition")
    private String weekCondition;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "primaryPosition")
    private String primaryPosition;
    @Size(max = 40)
    @Convert(converter = ListStringConverter.class)
    @Column(name = "otherPositions")
    private List<String> otherPositions;
    @Basic(optional = false)
    @NotNull
    @Min(40)
    @Max(99)
    @Column(name = "attackingProwess")
    private int attackingProwess;
    @Basic(optional = false)
    @NotNull
    @Min(40)
    @Max(99)
    @Column(name = "ballControl")
    private int ballControl;
    @Basic(optional = false)
    @NotNull
    @Min(40)
    @Max(99)
    @Column(name = "dribbling")
    private int dribbling;
    @Basic(optional = false)
    @NotNull
    @Min(40)
    @Max(99)
    @Column(name = "lowPass")
    private int lowPass;
    @Basic(optional = false)
    @NotNull
    @Min(40)
    @Max(99)
    @Column(name = "loftedPass")
    private int loftedPass;
    @Basic(optional = false)
    @NotNull
    @Min(40)
    @Max(99)
    @Column(name = "finishing")
    private int finishing;
    @Basic(optional = false)
    @NotNull
    @Min(40)
    @Max(99)
    @Column(name = "placeKicking")
    private int placeKicking;
    @Basic(optional = false)
    @NotNull
    @Min(40)
    @Max(99)
    @Column(name = "swerve")
    private int swerve;
    @Basic(optional = false)
    @NotNull
    @Min(40)
    @Max(99)
    @Column(name = "header")
    private int header;
    @Basic(optional = false)
    @NotNull
    @Min(40)
    @Max(99)
    @Column(name = "defensiveProwess")
    private int defensiveProwess;
    @Basic(optional = false)
    @NotNull
    @Min(40)
    @Max(99)
    @Column(name = "ballWinning")
    private int ballWinning;
    @Basic(optional = false)
    @NotNull
    @Min(40)
    @Max(99)
    @Column(name = "kickingPower")
    private int kickingPower;
    @Basic(optional = false)
    @NotNull
    @Min(40)
    @Max(99)
    @Column(name = "speed")
    private int speed;
    @Basic(optional = false)
    @NotNull
    @Min(40)
    @Max(99)
    @Column(name = "explosivePower")
    private int explosivePower;
    @Basic(optional = false)
    @NotNull
    @Min(40)
    @Max(99)
    @Column(name = "bodyControl")
    private int bodyControl;
    @Basic(optional = false)
    @NotNull
    @Min(40)
    @Max(99)
    @Column(name = "physicalContact")
    private int physicalContact;
    @Basic(optional = false)
    @NotNull
    @Min(40)
    @Max(99)
    @Column(name = "jump")
    private int jump;
    @Basic(optional = false)
    @NotNull
    @Min(40)
    @Max(99)
    @Column(name = "stamina")
    private int stamina;
    @Basic(optional = false)
    @NotNull
    @Min(40)
    @Max(99)
    @Column(name = "goalkeeping")
    private int goalkeeping;
    @Basic(optional = false)
    @NotNull
    @Min(40)
    @Max(99)
    @Column(name = "catching")
    private int catching;
    @Basic(optional = false)
    @NotNull
    @Min(40)
    @Max(99)
    @Column(name = "clearing")
    private int clearing;
    @Basic(optional = false)
    @NotNull
    @Min(40)
    @Max(99)
    @Column(name = "reflexes")
    private int reflexes;
    @Basic(optional = false)
    @NotNull
    @Min(40)
    @Max(99)
    @Column(name = "coverage")
    private int coverage;
    @Basic(optional = false)
    @Min(1)
    @Max(8)
    @Column(name = "form")
    private int form;
    @Basic(optional = false)
    @NotNull
    @Min(1)
    @Max(3)
    @Column(name = "injuryResistance")
    private int injuryResistance;
    @Basic(optional = false)
    @Min(1)
    @Max(4)
    @Column(name = "weakFootUsage")
    private int weakFootUsage;
    @Basic(optional = false)
    @NotNull
    @Min(1)
    @Max(4)
    @Column(name = "weakFootAccuracy")
    private int weakFootAccuracy;
    @Basic(optional = false)
    @NotNull
    @Min(40)
    @Max(99)
    @Column(name = "overallRating")
    private int overallRating;
    @Lob
    @Size(max = 65535)
    @Column(name = "playingStyle")
    private String playingStyle;
    @Lob
    @Size(max = 65535)
    @Convert(converter = ListStringConverter.class)
    @Column(name = "playerSkills")
    private List<String> playerSkills;
    @Lob
    @Size(max = 65535)
    @Convert(converter = ListStringConverter.class)
    @Column(name = "comPlayingStyles")
    private List<String> comPlayingStyles;
    @Basic(optional = false)
    @NotNull
    @Column(name = "lastChange")
    private LocalDateTime lastChange;
    @JsonBackReference    
    @JoinColumn(name = "playerId", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Player player;

    public PesDbInfo() {
    }

    public PesDbInfo(String season, String teamName, String foot, String weekCondition, String primaryPosition, LocalDateTime lastChange) {
        this.season = season;
        this.teamName = teamName;
        this.foot = foot;
        this.weekCondition = weekCondition;
        this.primaryPosition = primaryPosition;
        this.lastChange = lastChange;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public Player getPlayer() {
        return player;
    }
    public void setPlayer(Player player) {
        this.player = player;
    }
    
    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getFoot() {
        return foot;
    }

    public void setFoot(String foot) {
        this.foot = foot;
    }

    public String getWeekCondition() {
        return weekCondition;
    }

    public void setWeekCondition(String weekCondition) {
        this.weekCondition = weekCondition;
    }

    public String getPrimaryPosition() {
        return primaryPosition;
    }

    public void setPrimaryPosition(String primaryPosition) {
        this.primaryPosition = primaryPosition;
    }

    public List<String> getOtherPositions() {
        return otherPositions;
    }

    public void setOtherPositions(List<String> otherPositions) {
        this.otherPositions = otherPositions;
    }

    public int getAttackingProwess() {
        return attackingProwess;
    }

    public void setAttackingProwess(int attackingProwess) {
        this.attackingProwess = attackingProwess;
    }

    public int getBallControl() {
        return ballControl;
    }

    public void setBallControl(int ballControl) {
        this.ballControl = ballControl;
    }

    public int getDribbling() {
        return dribbling;
    }

    public void setDribbling(int dribbling) {
        this.dribbling = dribbling;
    }

    public int getLowPass() {
        return lowPass;
    }

    public void setLowPass(int lowPass) {
        this.lowPass = lowPass;
    }

    public int getLoftedPass() {
        return loftedPass;
    }

    public void setLoftedPass(int loftedPass) {
        this.loftedPass = loftedPass;
    }

    public int getFinishing() {
        return finishing;
    }

    public void setFinishing(int finishing) {
        this.finishing = finishing;
    }

    public int getPlaceKicking() {
        return placeKicking;
    }

    public void setPlaceKicking(int placeKicking) {
        this.placeKicking = placeKicking;
    }

    public int getSwerve() {
        return swerve;
    }

    public void setSwerve(int swerve) {
        this.swerve = swerve;
    }

    public int getHeader() {
        return header;
    }

    public void setHeader(int header) {
        this.header = header;
    }

    public int getDefensiveProwess() {
        return defensiveProwess;
    }

    public void setDefensiveProwess(int defensiveProwess) {
        this.defensiveProwess = defensiveProwess;
    }

    public int getBallWinning() {
        return ballWinning;
    }

    public void setBallWinning(int ballWinning) {
        this.ballWinning = ballWinning;
    }

    public int getKickingPower() {
        return kickingPower;
    }

    public void setKickingPower(int kickingPower) {
        this.kickingPower = kickingPower;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getExplosivePower() {
        return explosivePower;
    }

    public void setExplosivePower(int explosivePower) {
        this.explosivePower = explosivePower;
    }

    public int getBodyControl() {
        return bodyControl;
    }

    public void setBodyControl(int bodyControl) {
        this.bodyControl = bodyControl;
    }

    public int getPhysicalContact() {
        return physicalContact;
    }

    public void setPhysicalContact(int physicalContact) {
        this.physicalContact = physicalContact;
    }

    public int getJump() {
        return jump;
    }

    public void setJump(int jump) {
        this.jump = jump;
    }

    public int getStamina() {
        return stamina;
    }

    public void setStamina(int stamina) {
        this.stamina = stamina;
    }

    public int getGoalkeeping() {
        return goalkeeping;
    }

    public void setGoalkeeping(int goalkeeping) {
        this.goalkeeping = goalkeeping;
    }

    public int getCatching() {
        return catching;
    }

    public void setCatching(int catching) {
        this.catching = catching;
    }

    public int getClearing() {
        return clearing;
    }

    public void setClearing(int clearing) {
        this.clearing = clearing;
    }

    public int getReflexes() {
        return reflexes;
    }

    public void setReflexes(int reflexes) {
        this.reflexes = reflexes;
    }

    public int getCoverage() {
        return coverage;
    }

    public void setCoverage(int coverage) {
        this.coverage = coverage;
    }

    public int getForm() {
        return form;
    }

    public void setForm(int form) {
        this.form = form;
    }

    public int getInjuryResistance() {
        return injuryResistance;
    }

    public void setInjuryResistance(int injuryResistance) {
        this.injuryResistance = injuryResistance;
    }

    public int getWeakFootUsage() {
        return weakFootUsage;
    }

    public void setWeakFootUsage(int weakFootUsage) {
        this.weakFootUsage = weakFootUsage;
    }

    public int getWeakFootAccuracy() {
        return weakFootAccuracy;
    }

    public void setWeakFootAccuracy(int weakFootAccuracy) {
        this.weakFootAccuracy = weakFootAccuracy;
    }

    public int getOverallRating() {
        return overallRating;
    }

    public void setOverallRating(int overallRating) {
        this.overallRating = overallRating;
    }

    public String getPlayingStyle() {
        return playingStyle;
    }

    public void setPlayingStyle(String playingStyle) {
        this.playingStyle = playingStyle;
    }

    public List<String> getPlayerSkills() {
        return playerSkills;
    }

    public void setPlayerSkills(List<String> playerSkills) {
        this.playerSkills = playerSkills;
    }

    public List<String> getComPlayingStyles() {
        return comPlayingStyles;
    }

    public void setComPlayingStyles(List<String> comPlayingStyles) {
        this.comPlayingStyles = comPlayingStyles;
    }

    public LocalDateTime getLastChange() {
        return lastChange;
    }

    public void setLastChange(LocalDateTime lastChange) {
        this.lastChange = lastChange;
    }
    
    @Override
    public int hashCode() {
        return 4;
    }
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (this == object) return true;
        if (!(object instanceof PesDbInfo)) {
            return false;
        }
        PesDbInfo c = (PesDbInfo) object;        
        return this.id != null && Objects.equals(this.id, c.id);
    }
    
    @Override
    public String toString() {
        return "com.riocode.scoutpro.model.Pesdbinfo[ id=" + this.id + " ]";
    }

}
