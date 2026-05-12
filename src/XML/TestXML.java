package XML;

import bioLabPOJOS.Role;
import bioLabPOJOS.User;
import java.util.ArrayList;
import java.util.List;
/**
 * Isolated Test Launcher for XML Marshaling and HTML Transformation.
 * This class validates the data portability pipeline: 
 * POJOs -> XML File -> HTML Report via XSLT.
 */
public class TestXML {

    public static void main(String[] args) {

    	// 1. DATA MOCKING: Create a fake Role and User for the report
        Role adminRole = new Role("ADMIN");
        adminRole.setId(1);

        User testUser = new User("clara_dev", "password123");
        testUser.setId(101);
        testUser.setRole(adminRole);
        
        // 2. DATA STRUCTURE: Wrapping objects in the XML Root element structure
        XMLDataBase database = new XMLDataBase();

        List<User> userList = new ArrayList<>();
        userList.add(testUser);
        database.setUsers(userList);

        List<Role> roleList = new ArrayList<>();
        roleList.add(adminRole);
        database.setRoles(roleList);

        // 3. XML MARSHALING: Exporting Java objects to a physical XML file
        XMLManager manager = new XMLManager();
        String fileName = "BioLabTest.xml";

        System.out.println("Starting export to XML...");
        // This method utilizes JAXB to convert the database structure into tags
        manager.databaseToXML(database, fileName);

        // 4. XSL TRANSFORMATION: Generate HTML using the provided XSLT stylesheet
        System.out.println("Generating HTML report...");
        manager.xmlToHtml(
            fileName,        // XML input
            "biolab.xsl",    // XSL file
            "report.html"    // HTML output
        );

        System.out.println("Process finished. Check your project folder!");
    }
}