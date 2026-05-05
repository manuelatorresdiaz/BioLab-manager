package bioLabPOJOS;

import java.io.Serializable;
import javax.xml.bind.annotation.*;

/**
 * Represents an Administrator user within the BioLab system.
 * This class serves as a POJO for data persistence and XML serialization.
 */

@XmlRootElement(name = "Administrator")
@XmlAccessorType(XmlAccessType.FIELD)
public class Administrator implements Serializable {

    private static final long serialVersionUID = 1L;

    private int adminId;
    private String fullName;
    private String email;

    /**
     * Default constructor for JAXB and JPA compatibility.
     */
    
    public Administrator() {}

    /**
     * Parameterized constructor.
     * 
     * @param adminId  The unique ID of the administrator.
     * @param fullName The full name of the administrator.
     * @param email    The contact email.
     */
    
    public Administrator(int adminId, String fullName, String email) {
        this.adminId = adminId;
        this.fullName = fullName;
        this.email = email;
    }

    // Getters and Setters
    
    public int getAdminId() { return adminId; }
    public void setAdminId(int adminId) { this.adminId = adminId; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}