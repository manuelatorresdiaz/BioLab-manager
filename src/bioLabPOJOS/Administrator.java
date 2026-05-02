package bioLabPOJOS;

import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement(name = "Administrator")
@XmlAccessorType(XmlAccessType.FIELD)
public class Administrator extends User {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int adminId;
    private String fullName;
    private String email;

    public Administrator() {
        super();
    }

    public Administrator(String username, String password, int adminId, String fullName, String email) {
        super(username, password);
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