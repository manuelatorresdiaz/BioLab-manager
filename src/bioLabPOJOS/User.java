package bioLabPOJOS;

import javax.persistence.*;
import java.io.Serializable;
import javax.xml.bind.annotation.*;

@Entity
@Table(name = "user")
@XmlRootElement(name = "User") // <-- JAXB
@XmlAccessorType(XmlAccessType.FIELD) // <-- JAXB
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @XmlAttribute // <-- JPA y JAXB conviviendo en la misma variable
    private Integer id;

    @Column(name = "username", unique = true, nullable = false)
    @XmlElement // <-- JPA y JAXB juntos
    private String username;

    @Column(name = "password", nullable = false)
    @XmlElement // <-- JPA y JAXB juntos
    private String password;
    
    @XmlElement // <-- JAXB
    // (Si después tus compañeros configuran la relación en BD, aquí iría un @ManyToOne)
    private Role role;

    // Constructor vacío obligatorio para Hibernate y JAXB
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
    
    // (También añadí los getters/setters del rol para que esté completo)
    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }
}
