package bioLabPOJOS;

import javax.persistence.*;
import java.io.Serializable;
import javax.xml.bind.annotation.*;

/**
 * Represents a system user for authentication and authorization.
 * This class maps user credentials to specific roles and profiles (Patient or Physician).
 * Credentials: Who are you?
 * Authorization: what you are allowed to do
 * Identity link: which physical human being in the real world does this account belong to?
 */

@Entity
@Table(name = "user")
@XmlRootElement(name = "User")
@XmlAccessorType(XmlAccessType.FIELD)
public class User implements Serializable {

	// Version identifier for serialization consistency
	
    private static final long serialVersionUID = 1L;

    /**
     * Primary key with identity generation strategy.
     */
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @XmlAttribute
    private Integer id;

    /**
     * Unique identifier for login purposes.
     */
    
    @Column(name = "username", unique = true, nullable = false)
    //unique = true: It tells JPA to instruct SQLite: "Do not allow two rows to have the same username." 
    //nullable = false: Ensures you can never have a "ghost" user with a blank username.
    @XmlElement
    private String username;

    /**
     * Encrypted or plain text password for authentication.
     */
    
    @Column(name = "password", nullable = false)
    @XmlElement
    private String password;

    /**
     * Many-to-One relationship with the Role entity.
     * Uses PERSIST cascade to ensure roles are saved alongside the user if necessary.
     */
    
    @ManyToOne(cascade = CascadeType.PERSIST) //It means Many Users can share One Role
    /*
     * If I create a new User in Java, and I attach a brand new Role to it that isn't in the database 
     * yet, when I save the User, automatically save (persist) the Role into the database first so the 
     * User doesn't crash
     */
    @JoinColumn(name = "role_id") //Tells JPA that the User table has a foreign key column called role_id that points to the Role table.
    @XmlElement
    private Role role;

    /**
     * Optional link to a Patient profile ID.
     */
    
    @Column(name = "patient_id")
    @XmlElement
    private Integer patientId; //integer defaults to null

    /**
     * Optional link to a Physician profile ID.
     */
    
    @Column(name = "physician_id")
    @XmlElement
    private Integer physicianId;

    /**
     * Default constructor required for JPA and JAXB frameworks.
     */
    
    public User() {}

    /**
     * Parameterized constructor for basic user creation.
     * 
     * @param username The unique login name.
     * @param password The account password.
     */
    
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Getters y Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }

    public Integer getPatientId() { return patientId; }
    public void setPatientId(Integer patientId) { this.patientId = patientId; }

    public Integer getPhysicianId() { return physicianId; }
    public void setPhysicianId(Integer physicianId) { this.physicianId = physicianId; }
}