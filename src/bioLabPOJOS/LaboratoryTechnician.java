package bioLabPOJOS;
//HAY QUE REVISAR

import javax.persistence.Entity;
import java.io.Serializable;

@Entity
public class LaboratoryTechnician extends User implements Serializable {

    private String shift; // Ej: "Matutino", "Vespertino"

    public LaboratoryTechnician() {
        super();
    }

    public LaboratoryTechnician(String username, String password, String shift) {
        super(username, password);
        this.shift = shift;
    }

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }
}
