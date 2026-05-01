package XML;

import java.io.File; // Necessary to handle the physical file
import javax.xml.bind.JAXBContext; // The entry point to the JAXB API
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

public class XMLManager {

    /**
     * Marshalls (Exports) the database object to an XML file.
     */
    public void databaseToXML(XMLDataBase database, String filePath) {
        try {
            // Initialize the JAXB Context for our specific wrapper class
            JAXBContext jaxbContext = JAXBContext.newInstance(XMLDataBase.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            
            // Set property for nice formatting (line breaks and tabs)
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            
            File file = new File(filePath);
            marshaller.marshal(database, file);
            
            System.out.println("Database successfully exported to XML at: " + filePath);
            
        } catch (Exception e) {
            System.err.println("Error exporting to XML: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Unmarshalls (Imports) an XML file back into a Java object.
     */
    public XMLDataBase xmlToDatabase(String filePath) {
        try {
            File file = new File(filePath);
            JAXBContext jaxbContext = JAXBContext.newInstance(XMLDataBase.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            
            // Convert XML file content back to our Java class
            XMLDataBase database = (XMLDataBase) unmarshaller.unmarshal(file);
            System.out.println("Database successfully imported from XML.");
            
            return database;
            
        } catch (Exception e) {
            System.err.println("Error importing from XML: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
    public void xmlToHtml(String xmlPath, String xsltPath, String htmlPath) {
        try {
            TransformerFactory factory = TransformerFactory.newInstance();
            StreamSource xslt = new StreamSource(new File(xsltPath));
            Transformer transformer = factory.newTransformer(xslt);

            StreamSource xml = new StreamSource(new File(xmlPath));
            StreamResult html = new StreamResult(new File(htmlPath));

            transformer.transform(xml, html);
            System.out.println("HTML report generated successfully: " + htmlPath);
        } catch (Exception e) {
            System.err.println("Error generating HTML: " + e.getMessage());
        }
    }
}