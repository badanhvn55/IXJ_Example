<%-- 
    Document   : product
    Created on : Jan 9, 2019, 12:04:39 PM
    Author     : badan
--%>

<%@page contentType="text/xml" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<?xml-stylesheet type="text/xsl" href="product.xsl"?>
<Products>
    <c:forEach var="product" items="${products}">
        <Product>
        <id>${product.id}</id>
        <name>${product.name}</name>
        <price>${product.price}</price>
        <description>${product.description}</description>
        </Product>
    </c:forEach>
</Products>
