/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import nu.xom.ParsingException;
import nu.xom.ValidityException;
import nux.xom.xquery.XQueryUtil;

/**
 *
 * @author badan
 */
public class ProcessingXML {

    public ProcessingXML() {
    }

    public ArrayList readXML(String url) throws ParsingException, ValidityException, IOException {
        System.out.println("URL: " + url);
        nu.xom.Document document = new nu.xom.Builder().build(new File(url + "Products.xml"));
        nu.xom.Nodes nodes = XQueryUtil.xquery(document, "//Product");

        ArrayList arrayList = new ArrayList();
        System.out.println("Nodes size: " + nodes.size());
        for (int i = 0; i < nodes.size(); i++) {
            Product product = new Product();
            nu.xom.Node node = nodes.get(i);
            product.setId(Integer.parseInt(node.getChild(1).getValue()));
            product.setName(node.getChild(3).getValue());
            product.setPrice(Double.parseDouble(node.getChild(5).getValue()));
            product.setDescription(node.getChild(7).getValue());
            arrayList.add(product);
        }
        return arrayList;
    }

    public ArrayList filtedContent(String url, String filter) throws ParsingException, ValidityException, IOException, IOException {
        nu.xom.Document document = new nu.xom.Builder().build(new File(url, "Products.xml"));
        nu.xom.Nodes nodes = XQueryUtil.xquery(document, "//Product[price>" + filter + "]");
        System.out.println("Nodes size filter: " + nodes.size());
        ArrayList arrayList = new ArrayList();
        for(int i = 0; i < nodes.size(); i++){
            Product product = new Product();
            nu.xom.Node node = nodes.get(i);
            product.setId(Integer.parseInt(node.getChild(1).getValue()));
            product.setName(node.getChild(3).getValue());
            product.setPrice(Double.parseDouble(node.getChild(5).getValue()));
            product.setDescription(node.getChild(7).getValue());
            arrayList.add(product);
        }
        return arrayList;
    }
}
