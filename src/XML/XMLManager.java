package XML;

import interfaces.XMLManagerInterface;
import java.io.File; // Necessary to handle the physical file
import javax.xml.bind.JAXBContext; // The entry point to the JAXB API
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
/**
 * Implementation of the XML Management Interface.
 * Handles the serialization (Marshalling), deserialization (Unmarshalling),
 * and XSLT transformation of laboratory data.
 */
public class XMLManager implements XMLManagerInterface {
	/**
     * Converts a Java object structure (XMLDataBase) into a physical XML file.
     * Uses JAXB Marshalling with formatted output for readability.
     */
    public void databaseToXML(XMLDataBase database, String filePath) {
        try {
        	// Context initialization for the wrapper class
            JAXBContext jaxbContext = JAXBContext.newInstance(XMLDataBase.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            
            // Enable 'pretty-printing' to make the XML human-readable
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
     * Parses an XML file and reconstructs the Java object hierarchy.
     * Essential for data recovery and importing external clinical records.
     */
    public XMLDataBase xmlToDatabase(String filePath) {
        try {
            File file = new File(filePath);
            JAXBContext jaxbContext = JAXBContext.newInstance(XMLDataBase.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            
            // Reconstruct the XMLDataBase object from the file source
            XMLDataBase database = (XMLDataBase) unmarshaller.unmarshal(file);
            System.out.println("Database successfully imported from XML.");
            
            return database;
            
        } catch (Exception e) {
            System.err.println("Error importing from XML: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
    /**
     * Applies an XSLT stylesheet to an XML file to produce an HTML document.
     * This allows for professional visualization of the laboratory data.
     */
    public void xmlToHtml(String xmlPath, String xsltPath, String htmlPath) {
        try {
        	// Initialize the Transformer engine
            TransformerFactory factory = TransformerFactory.newInstance();
            StreamSource xslt = new StreamSource(new File(xsltPath));
            Transformer transformer = factory.newTransformer(xslt);
            // Set source and destination streams
            StreamSource xml = new StreamSource(new File(xmlPath));
            StreamResult html = new StreamResult(new File(htmlPath));
            // Execute the transformation
            transformer.transform(xml, html);
            System.out.println("HTML report generated successfully: " + htmlPath);
        } catch (Exception e) {
            System.err.println("Error generating HTML: " + e.getMessage());
        }
    }
}