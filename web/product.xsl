<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : product.xsl
    Created on : January 9, 2019, 12:14 PM
    Author     : badan
    Description:
        Purpose of transformation follows.
-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="html"/>

    <!-- TODO customize transformation rules 
         syntax recommendation http://www.w3.org/TR/xslt 
    -->
    <xsl:template match="/">
        <html>
            <head>
                <title>product.xsl</title>
            </head>
            <body>
                <h2>Display Data</h2>
                <table border="1">
                    <tr bgcolor="#9acd32">
                        <th>id</th>
                        <th>name</th>
                        <th>price</th>
                        <th>description</th>
                    </tr>
                    <xsl:for-each select="Products/Product">
                        <tr>
                            <td>
                                <xsl:value-of select="id"/>
                            </td>
                            <td>
                                <xsl:value-of select="name"/>
                            </td>
                            <td>
                                <xsl:value-of select="price"/>
                            </td>
                            <td>
                                <xsl:value-of select="description"/>
                            </td>
                        </tr>
                    </xsl:for-each>
                </table>
            </body>
        </html>
    </xsl:template>

</xsl:stylesheet>
