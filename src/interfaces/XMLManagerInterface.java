package interfaces;

import XML.XMLDataBase;
/**
 * Interface defining the data portability and reporting contract.
 * Facilitates the conversion of system data between Java objects, 
 * persistent XML files, and web-ready HTML documents.
 */
public interface XMLManagerInterface {
	/**
     * Marshaling: Converts the internal database structure into a physical XML file.
     * Used for system backups and data migration.
     * @param database The wrapper object containing the lists of clinical entities.
     * @param filePath The destination path where the .xml file will be saved.
     */
    void databaseToXML(XMLDataBase database, String filePath);
    /**
     * Unmarshaling: Reads an XML file and restores it into a Java object structure.
     * Allows for importing external data or restoring previous system states.
     * @param filePath The path to the source .xml file.
     * @return An XMLDataBase object populated with the imported data.
     */
    XMLDataBase xmlToDatabase(String filePath);
    /**
     * Transformation: Applies an XSLT stylesheet to an XML file to generate an HTML report.
     * This fulfills the requirement for clinical web-based reporting.
     * @param xmlPath Source data file.
     * @param xsltPath Stylesheet defining the visual structure and branding.
     * @param htmlPath Destination path for the final web report.
     */
    void xmlToHtml(String xmlPath, String xsltPath, String htmlPath);
}