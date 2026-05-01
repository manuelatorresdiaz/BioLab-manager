package XML;

import bioLabPOJOS.Role;
import bioLabPOJOS.User;
import java.util.ArrayList;
import java.util.List;

public class TestXML {
    public static void main(String[] args) {
        // 1. Create a fake Role and User
        Role adminRole = new Role("ADMIN");
        adminRole.setId(1);

        User testUser = new User("clara_dev", "password123");
        testUser.setId(101);
        testUser.setRole(adminRole);

        // 2. Put them into our "Box" (BioLabDatabaseXML)
        XMLDataBase database = new XMLDataBase();
        
        List<User> userList = new ArrayList<>();
        userList.add(testUser);
        database.setUsers(userList);

        List<Role> roleList = new ArrayList<>();
        roleList.add(adminRole);
        database.setRoles(roleList);

        // 3. Use the XMLManager to export it to a file
        XMLManager manager = new XMLManager();
        String fileName = "BioLabTest.xml";
        
        System.out.println("Starting export test...");
        manager.databaseToXML(database, fileName);
        
        System.out.println("Test finished. Check your project folder!");
    }
}