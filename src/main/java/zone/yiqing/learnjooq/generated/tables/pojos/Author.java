/*
 * This file is generated by jOOQ.
*/
package zone.yiqing.learnjooq.generated.tables.pojos;


import javax.annotation.Generated;

import zone.yiqing.learnjooq.generated.tables.interfaces.IAuthor;


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
public class Author implements IAuthor {

    private static final long serialVersionUID = 742735671;

    private Integer id;
    private String  firstName;
    private String  lastName;

    public Author() {}

    public Author(Author value) {
        this.id = value.id;
        this.firstName = value.firstName;
        this.lastName = value.lastName;
    }

    public Author(
        Integer id,
        String  firstName,
        String  lastName
    ) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public Integer getId() {
        return this.id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String getFirstName() {
        return this.firstName;
    }

    @Override
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public String getLastName() {
        return this.lastName;
    }

    @Override
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Author (");

        sb.append(id);
        sb.append(", ").append(firstName);
        sb.append(", ").append(lastName);

        sb.append(")");
        return sb.toString();
    }

    // -------------------------------------------------------------------------
    // FROM and INTO
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public void from(IAuthor from) {
        setId(from.getId());
        setFirstName(from.getFirstName());
        setLastName(from.getLastName());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <E extends IAuthor> E into(E into) {
        into.from(this);
        return into;
    }
}