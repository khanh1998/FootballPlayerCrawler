//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.03.24 at 10:48:29 AM ICT 
//


package fbref.jaxb;

import java.math.BigDecimal;
import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for standard-statsType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="standard-statsType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="seasons" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger"/>
 *         &lt;element name="squad" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger"/>
 *         &lt;element name="comp_level" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger"/>
 *         &lt;element name="games" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger"/>
 *         &lt;element name="games_starts" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger"/>
 *         &lt;element name="minutes" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger"/>
 *         &lt;element name="goals" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger"/>
 *         &lt;element name="assists" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger"/>
 *         &lt;element name="cards_yellow" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger"/>
 *         &lt;element name="cards_red" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger"/>
 *         &lt;element name="goals_per90" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="assists_per90" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="goals_assists_per90" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="goals_pens_per90" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="goals_assists_pens_per90" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="xa" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="xg_per90" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="xa_per90" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="xg_xa_per90" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="npxg_per90" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="npxg_xa_per90" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "standard-statsType", propOrder = {
    "seasons",
    "squad",
    "compLevel",
    "games",
    "gamesStarts",
    "minutes",
    "goals",
    "assists",
    "cardsYellow",
    "cardsRed",
    "goalsPer90",
    "assistsPer90",
    "goalsAssistsPer90",
    "goalsPensPer90",
    "goalsAssistsPensPer90",
    "xa",
    "xgPer90",
    "xaPer90",
    "xgXaPer90",
    "npxgPer90",
    "npxgXaPer90"
})
public class StandardStatsType {

    @XmlElement(required = true, defaultValue = "0")
    @XmlSchemaType(name = "nonNegativeInteger")
    protected BigInteger seasons;
    @XmlElement(required = true, defaultValue = "0")
    @XmlSchemaType(name = "nonNegativeInteger")
    protected BigInteger squad;
    @XmlElement(name = "comp_level", required = true, defaultValue = "0")
    @XmlSchemaType(name = "nonNegativeInteger")
    protected BigInteger compLevel;
    @XmlElement(required = true, defaultValue = "0")
    @XmlSchemaType(name = "nonNegativeInteger")
    protected BigInteger games;
    @XmlElement(name = "games_starts", required = true, defaultValue = "0")
    @XmlSchemaType(name = "nonNegativeInteger")
    protected BigInteger gamesStarts;
    @XmlElement(required = true, defaultValue = "0")
    @XmlSchemaType(name = "nonNegativeInteger")
    protected BigInteger minutes;
    @XmlElement(required = true, defaultValue = "0")
    @XmlSchemaType(name = "nonNegativeInteger")
    protected BigInteger goals;
    @XmlElement(required = true, defaultValue = "0")
    @XmlSchemaType(name = "nonNegativeInteger")
    protected BigInteger assists;
    @XmlElement(name = "cards_yellow", required = true, defaultValue = "0")
    @XmlSchemaType(name = "nonNegativeInteger")
    protected BigInteger cardsYellow;
    @XmlElement(name = "cards_red", required = true, defaultValue = "0")
    @XmlSchemaType(name = "nonNegativeInteger")
    protected BigInteger cardsRed;
    @XmlElement(name = "goals_per90", required = true, defaultValue = "0")
    protected BigDecimal goalsPer90;
    @XmlElement(name = "assists_per90", required = true, defaultValue = "0")
    protected BigDecimal assistsPer90;
    @XmlElement(name = "goals_assists_per90", required = true, defaultValue = "0")
    protected BigDecimal goalsAssistsPer90;
    @XmlElement(name = "goals_pens_per90", required = true, defaultValue = "0")
    protected BigDecimal goalsPensPer90;
    @XmlElement(name = "goals_assists_pens_per90", required = true, defaultValue = "0")
    protected BigDecimal goalsAssistsPensPer90;
    @XmlElement(required = true, defaultValue = "0")
    protected BigDecimal xa;
    @XmlElement(name = "xg_per90", required = true, defaultValue = "0")
    protected BigDecimal xgPer90;
    @XmlElement(name = "xa_per90", required = true, defaultValue = "0")
    protected BigDecimal xaPer90;
    @XmlElement(name = "xg_xa_per90", required = true, defaultValue = "0")
    protected BigDecimal xgXaPer90;
    @XmlElement(name = "npxg_per90", required = true, defaultValue = "0")
    protected BigDecimal npxgPer90;
    @XmlElement(name = "npxg_xa_per90", required = true, defaultValue = "0")
    protected BigDecimal npxgXaPer90;

    /**
     * Gets the value of the seasons property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getSeasons() {
        return seasons;
    }

    /**
     * Sets the value of the seasons property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setSeasons(BigInteger value) {
        this.seasons = value;
    }

    /**
     * Gets the value of the squad property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getSquad() {
        return squad;
    }

    /**
     * Sets the value of the squad property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setSquad(BigInteger value) {
        this.squad = value;
    }

    /**
     * Gets the value of the compLevel property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getCompLevel() {
        return compLevel;
    }

    /**
     * Sets the value of the compLevel property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setCompLevel(BigInteger value) {
        this.compLevel = value;
    }

    /**
     * Gets the value of the games property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getGames() {
        return games;
    }

    /**
     * Sets the value of the games property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setGames(BigInteger value) {
        this.games = value;
    }

    /**
     * Gets the value of the gamesStarts property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getGamesStarts() {
        return gamesStarts;
    }

    /**
     * Sets the value of the gamesStarts property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setGamesStarts(BigInteger value) {
        this.gamesStarts = value;
    }

    /**
     * Gets the value of the minutes property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getMinutes() {
        return minutes;
    }

    /**
     * Sets the value of the minutes property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setMinutes(BigInteger value) {
        this.minutes = value;
    }

    /**
     * Gets the value of the goals property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getGoals() {
        return goals;
    }

    /**
     * Sets the value of the goals property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setGoals(BigInteger value) {
        this.goals = value;
    }

    /**
     * Gets the value of the assists property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getAssists() {
        return assists;
    }

    /**
     * Sets the value of the assists property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setAssists(BigInteger value) {
        this.assists = value;
    }

    /**
     * Gets the value of the cardsYellow property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getCardsYellow() {
        return cardsYellow;
    }

    /**
     * Sets the value of the cardsYellow property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setCardsYellow(BigInteger value) {
        this.cardsYellow = value;
    }

    /**
     * Gets the value of the cardsRed property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getCardsRed() {
        return cardsRed;
    }

    /**
     * Sets the value of the cardsRed property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setCardsRed(BigInteger value) {
        this.cardsRed = value;
    }

    /**
     * Gets the value of the goalsPer90 property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getGoalsPer90() {
        return goalsPer90;
    }

    /**
     * Sets the value of the goalsPer90 property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setGoalsPer90(BigDecimal value) {
        this.goalsPer90 = value;
    }

    /**
     * Gets the value of the assistsPer90 property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getAssistsPer90() {
        return assistsPer90;
    }

    /**
     * Sets the value of the assistsPer90 property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setAssistsPer90(BigDecimal value) {
        this.assistsPer90 = value;
    }

    /**
     * Gets the value of the goalsAssistsPer90 property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getGoalsAssistsPer90() {
        return goalsAssistsPer90;
    }

    /**
     * Sets the value of the goalsAssistsPer90 property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setGoalsAssistsPer90(BigDecimal value) {
        this.goalsAssistsPer90 = value;
    }

    /**
     * Gets the value of the goalsPensPer90 property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getGoalsPensPer90() {
        return goalsPensPer90;
    }

    /**
     * Sets the value of the goalsPensPer90 property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setGoalsPensPer90(BigDecimal value) {
        this.goalsPensPer90 = value;
    }

    /**
     * Gets the value of the goalsAssistsPensPer90 property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getGoalsAssistsPensPer90() {
        return goalsAssistsPensPer90;
    }

    /**
     * Sets the value of the goalsAssistsPensPer90 property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setGoalsAssistsPensPer90(BigDecimal value) {
        this.goalsAssistsPensPer90 = value;
    }

    /**
     * Gets the value of the xa property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getXa() {
        return xa;
    }

    /**
     * Sets the value of the xa property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setXa(BigDecimal value) {
        this.xa = value;
    }

    /**
     * Gets the value of the xgPer90 property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getXgPer90() {
        return xgPer90;
    }

    /**
     * Sets the value of the xgPer90 property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setXgPer90(BigDecimal value) {
        this.xgPer90 = value;
    }

    /**
     * Gets the value of the xaPer90 property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getXaPer90() {
        return xaPer90;
    }

    /**
     * Sets the value of the xaPer90 property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setXaPer90(BigDecimal value) {
        this.xaPer90 = value;
    }

    /**
     * Gets the value of the xgXaPer90 property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getXgXaPer90() {
        return xgXaPer90;
    }

    /**
     * Sets the value of the xgXaPer90 property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setXgXaPer90(BigDecimal value) {
        this.xgXaPer90 = value;
    }

    /**
     * Gets the value of the npxgPer90 property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getNpxgPer90() {
        return npxgPer90;
    }

    /**
     * Sets the value of the npxgPer90 property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setNpxgPer90(BigDecimal value) {
        this.npxgPer90 = value;
    }

    /**
     * Gets the value of the npxgXaPer90 property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getNpxgXaPer90() {
        return npxgXaPer90;
    }

    /**
     * Sets the value of the npxgXaPer90 property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setNpxgXaPer90(BigDecimal value) {
        this.npxgXaPer90 = value;
    }

}