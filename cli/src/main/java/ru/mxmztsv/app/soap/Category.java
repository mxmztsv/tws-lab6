
package ru.mxmztsv.app.soap;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for category.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * &lt;pre&gt;
 * &amp;lt;simpleType name="category"&amp;gt;
 *   &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&amp;gt;
 *     &amp;lt;enumeration value="NEW"/&amp;gt;
 *     &amp;lt;enumeration value="REGULAR"/&amp;gt;
 *     &amp;lt;enumeration value="VIP"/&amp;gt;
 *   &amp;lt;/restriction&amp;gt;
 * &amp;lt;/simpleType&amp;gt;
 * &lt;/pre&gt;
 * 
 */
@XmlType(name = "category")
@XmlEnum
public enum Category {

    NEW,
    REGULAR,
    VIP;

    public String value() {
        return name();
    }

    public static Category fromValue(String v) {
        return valueOf(v);
    }

}
