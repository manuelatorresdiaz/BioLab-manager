package bioLabPOJOS;
//HAY QUE REVISAR

import javax.persistence.Entity;
import java.io.Serializable;

@Entity
public class Administrator extends User implements Serializable {

    private String department;

    public Administrator() {
        super();
    }

    public Administrator(String username, String password, String department) {
        super(username, password); // Pasa el usuario y contraseña a la clase padre
        this.department = department;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
