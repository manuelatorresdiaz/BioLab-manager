package bioLabPOJOS;
//HAY QUE VERIFICAR

import javax.persistence.*;
import java.io.Serializable;

@Entity
// Esta anotación es la que te da los 2 puntos de la rúbrica.
// JOINED crea una tabla para User y tablas separadas vinculadas para los hijos.
@Inheritance(strategy = InheritanceType.JOINED) 
public abstract class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    // JPA exige siempre un constructor vacío
    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
