package bioLabPOJOS;

import java.io.Serializable;
import javax.xml.bind.annotation.*;

@XmlRootElement(name = "Administrator")
@XmlAccessorType(XmlAccessType.FIELD)
public class Administrator implements Serializable {

    private static final long serialVersionUID = 1L;

    private int adminId;
    private String fullName;
    private String email;

    public Administrator() {}

    public Administrator(int adminId, String fullName, String email) {
        this.adminId = adminId;
        this.fullName = fullName;
        this.email = email;
    }

    public int getAdminId() { return adminId; }
    public void setAdminId(int adminId) { this.adminId = adminId; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}