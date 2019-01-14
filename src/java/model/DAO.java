/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author badan
 */
public class DAO {

    protected Connection connection;

    public DAO() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
            connection = DriverManager.getConnection("jdbc:sqlserver://localhost;databaseName=ecommerce", "sa", "123456");
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ResultSet getData() {
        ResultSet rs = null;
        try {
            Statement statement = connection.createStatement();
            rs = statement.executeQuery("select * from product");
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
    
    public void writeXML(String url) throws ParserConfigurationException, SQLException, TransformerConfigurationException, TransformerException, IOException{
        System.out.println("URL: " + url);
        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document document = builder.newDocument();
        Element root = document.createElement("Products");
        document.appendChild(root);
        
        ResultSet rs = this.getData();
        while(rs.next()){
            Element child = document.createElement("Product");
            root.appendChild(child);
            Element id = document.createElement("id");
            id.setTextContent(rs.getString("id"));
            child.appendChild(id);
            Element name = document.createElement("name");
            name.setTextContent(rs.getString("name"));
            child.appendChild(name);
            Element price = document.createElement("price");
            price.setTextContent(rs.getString("price"));
            child.appendChild(price);
            Element description = document.createElement("description");
            description.setTextContent(rs.getString("description"));
            child.appendChild(description);
        }
        
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
        transformer.setOutputProperty(OutputKeys.STANDALONE, "yes");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty(OutputKeys.ENCODING, "iso-8859-1");
        
        StringWriter writer = new StringWriter();
        StreamResult result = new StreamResult(writer);
        
        DOMSource source = new DOMSource(document);
        transformer.transform(source, result);
        
        try (FileWriter fileWriter = new FileWriter(new File(url + "Products.xml"))) {
            fileWriter.write(writer.toString());
        }
        System.err.println("write XML complete...");
    }

}
