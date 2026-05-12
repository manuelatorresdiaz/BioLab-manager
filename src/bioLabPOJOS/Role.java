package bioLabPOJOS;

import javax.persistence.*;
import java.io.Serializable;
import javax.xml.bind.annotation.*;

/**
 * Represents a security role within the system.
 * This class uses JPA for Object-Relational Mapping (ORM) and 
 * JAXB for XML serialization.
 */

@Entity
@Table(name = "role") // Name of the table in the database. Knows how to execute INSERT and SELECT
@XmlRootElement(name = "Role") // JAXB
@XmlAccessorType(XmlAccessType.FIELD) // JAXB Access directly to its attributes.
public class Role implements Serializable {
	// Unique version identifier for serialization consistency
    private static final long serialVersionUID = 1L;

    /**
     * Primary key with auto-increment strategy.
     * Mapped as an attribute in XML for concise data representation.
     */
    
    @Id //tells JPA this is the primary key of the database table.
    @GeneratedValue(strategy = GenerationType.IDENTITY) //generate ID automatically
    @XmlAttribute // JPA y JAXB together. Attributes are used to identify, classify, or describe the element itself, rather than holding the core data you want to display.
    private Integer id;

    /**
     * The name of the role (e.g., ADMIN, DOCTOR, TECHNICIAN).
     * Mapped as a required column in the database and an element in XML.
     */
    
    @Column(name = "role_name", nullable = false)
    @XmlElement // Element is the core building block of XML. It consists of an opening tag, the content inside, and a closing tag.
    //hold the actual data.
    private String roleName;

    /**
     * Mandatory no-argument constructor for JPA and JAXB instantiation.
     */
    
    public Role() {}

    /**
     * Parameterized constructor for role creation.
     * 
     * @param roleName The descriptive name of the security role.
     */
    
    public Role(String roleName) {
        this.roleName = roleName;
    }

    // Getters y Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getRoleName() { return roleName; }
    public void setRoleName(String roleName) { this.roleName = roleName; }
}