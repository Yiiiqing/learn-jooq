/*
 * This file is generated by jOOQ.
*/
package zone.yiqing.learnjooq.generated.tables.interfaces;


import java.io.Serializable;

import javax.annotation.Generated;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.9.5"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public interface IAuthor extends Serializable {

    /**
     * Setter for <code>library.author.id</code>.
     */
    public void setId(Integer value);

    /**
     * Getter for <code>library.author.id</code>.
     */
    public Integer getId();

    /**
     * Setter for <code>library.author.first_name</code>.
     */
    public void setFirstName(String value);

    /**
     * Getter for <code>library.author.first_name</code>.
     */
    public String getFirstName();

    /**
     * Setter for <code>library.author.last_name</code>.
     */
    public void setLastName(String value);

    /**
     * Getter for <code>library.author.last_name</code>.
     */
    public String getLastName();

    // -------------------------------------------------------------------------
    // FROM and INTO
    // -------------------------------------------------------------------------

    /**
     * Load data from another generated Record/POJO implementing the common interface IAuthor
     */
    public void from(zone.yiqing.learnjooq.generated.tables.interfaces.IAuthor from);

    /**
     * Copy data into another generated Record/POJO implementing the common interface IAuthor
     */
    public <E extends zone.yiqing.learnjooq.generated.tables.interfaces.IAuthor> E into(E into);
}
