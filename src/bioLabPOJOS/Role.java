package bioLabPOJOS;

import javax.persistence.*;
import java.io.Serializable;
import javax.xml.bind.annotation.*;

@Entity
@Table(name = "role") // El nombre de tu tabla en la base de datos
@XmlRootElement(name = "Role") // JAXB
@XmlAccessorType(XmlAccessType.FIELD) // JAXB
public class Role implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @XmlAttribute // JPA y JAXB juntos
    private Integer id;

    @Column(name = "role_name", nullable = false)
    @XmlElement // JPA y JAXB juntos
    private String roleName;

    // Constructor vacío obligatorio
    public Role() {}

    public Role(String roleName) {
        this.roleName = roleName;
    }

    // Getters y Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getRoleName() { return roleName; }
    public void setRoleName(String roleName) { this.roleName = roleName; }
}