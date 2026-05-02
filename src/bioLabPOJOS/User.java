package bioLabPOJOS;

import javax.persistence.*;
import java.io.Serializable;
import javax.xml.bind.annotation.*;

@Entity
@Table(name = "user")
@XmlRootElement(name = "User")
@XmlAccessorType(XmlAccessType.FIELD)
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @XmlAttribute
    private Integer id;

    @Column(name = "username", unique = true, nullable = false)
    @XmlElement
    private String username;

    @Column(name = "password", nullable = false)
    @XmlElement
    private String password;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "role_id")
    @XmlElement
    private Role role;

    @Column(name = "patient_id")
    @XmlElement
    private Integer patientId;

    @Column(name = "physician_id")
    @XmlElement
    private Integer physicianId;

    // Constructor vacío
    public User() {}

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