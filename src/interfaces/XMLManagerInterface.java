package interfaces;

import XML.XMLDataBase;

public interface XMLManagerInterface {

    void databaseToXML(XMLDataBase database, String filePath);

    XMLDataBase xmlToDatabase(String filePath);

    void xmlToHtml(String xmlPath, String xsltPath, String htmlPath);
}