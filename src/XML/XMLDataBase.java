package XML; 

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.*;

import bioLabPOJOS.Role;
import bioLabPOJOS.User;
/**
 * Root container for XML Serialization.
 * This class serves as the Data Transfer Object (DTO) that JAXB uses 
 * to map Java collections into a structured XML document.
 */
@XmlRootElement(name = "BioLabDatabase") // Defines the root tag <BioLabDatabase>
@XmlAccessorType(XmlAccessType.FIELD)	 // Allows JAXB to read fields directly
public class XMLDataBase {
	// Wraps the list in a <Users> tag, with each item as <User>
    @XmlElementWrapper(name = "Users")
    @XmlElement(name = "User")
    private List<User> users;
    // Wraps the list in a <Roles> tag, with each item as <Role>
    @XmlElementWrapper(name = "Roles")
    @XmlElement(name = "Role")
    private List<Role> roles;
    /**
     * Default constructor required by JAXB.
     */
    public XMLDataBase() {
        this.users = new ArrayList<>();
        this.roles = new ArrayList<>();
    }
    // Standard Getters and Setters for the Marshaling process
    public List<User> getUsers() { return users; }
    public void setUsers(List<User> users) { this.users = users; }

    public List<Role> getRoles() { return roles; }
    public void setRoles(List<Role> roles) { this.roles = roles; }
}
