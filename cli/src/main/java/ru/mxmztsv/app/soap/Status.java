
package ru.mxmztsv.app.soap;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for status.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * &lt;pre&gt;
 * &amp;lt;simpleType name="status"&amp;gt;
 *   &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&amp;gt;
 *     &amp;lt;enumeration value="PENDING"/&amp;gt;
 *     &amp;lt;enumeration value="ACTIVE"/&amp;gt;
 *     &amp;lt;enumeration value="INACTIVE"/&amp;gt;
 *   &amp;lt;/restriction&amp;gt;
 * &amp;lt;/simpleType&amp;gt;
 * &lt;/pre&gt;
 * 
 */
@XmlType(name = "status")
@XmlEnum
public enum Status {

    PENDING,
    ACTIVE,
    INACTIVE;

    public String value() {
        return name();
    }

    public static Status fromValue(String v) {
        return valueOf(v);
    }

}
