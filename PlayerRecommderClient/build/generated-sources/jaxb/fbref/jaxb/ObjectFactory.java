//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.03.24 at 10:48:29 AM ICT 
//


package fbref.jaxb;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the fbref.jaxb package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Players_QNAME = new QName("", "players");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: fbref.jaxb
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link PlayerTypes }
     * 
     */
    public PlayerTypes createPlayerTypes() {
        return new PlayerTypes();
    }

    /**
     * Create an instance of {@link ShootingStatsType }
     * 
     */
    public ShootingStatsType createShootingStatsType() {
        return new ShootingStatsType();
    }

    /**
     * Create an instance of {@link PassingStatsType }
     * 
     */
    public PassingStatsType createPassingStatsType() {
        return new PassingStatsType();
    }

    /**
     * Create an instance of {@link PlayerType }
     * 
     */
    public PlayerType createPlayerType() {
        return new PlayerType();
    }

    /**
     * Create an instance of {@link StandardStatsType }
     * 
     */
    public StandardStatsType createStandardStatsType() {
        return new StandardStatsType();
    }

    /**
     * Create an instance of {@link StatsType }
     * 
     */
    public StatsType createStatsType() {
        return new StatsType();
    }

    /**
     * Create an instance of {@link MiscellaneousStatsType }
     * 
     */
    public MiscellaneousStatsType createMiscellaneousStatsType() {
        return new MiscellaneousStatsType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PlayerTypes }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "players")
    public JAXBElement<PlayerTypes> createPlayers(PlayerTypes value) {
        return new JAXBElement<PlayerTypes>(_Players_QNAME, PlayerTypes.class, null, value);
    }

}
