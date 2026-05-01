package XML; 

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.*;

import bioLabPOJOS.Role;
import bioLabPOJOS.User;

@XmlRootElement(name = "BioLabDatabase")
@XmlAccessorType(XmlAccessType.FIELD)
public class XMLDataBase {

    @XmlElementWrapper(name = "Users")
    @XmlElement(name = "User")
    private List<User> users;

    @XmlElementWrapper(name = "Roles")
    @XmlElement(name = "Role")
    private List<Role> roles;

    public XMLDataBase() {
        this.users = new ArrayList<>();
        this.roles = new ArrayList<>();
    }

    public List<User> getUsers() { return users; }
    public void setUsers(List<User> users) { this.users = users; }

    public List<Role> getRoles() { return roles; }
    public void setRoles(List<Role> roles) { this.roles = roles; }
}
