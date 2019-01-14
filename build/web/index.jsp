<%-- 
    Document   : index
    Created on : Jan 9, 2019, 12:00:59 PM
    Author     : badan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Index Page</title>
    </head>
    <body>
        ${info}
        <form action="ActionProcess" method="POST">
            <input type="hidden" name="act" value="getdata"/> <input type="submit" value="Get Data"/>
        </form>
        <form action="ActionProcess" method="POST">
            <input type="hidden" name="act" value="showdata"/> <input type="submit" value="Show Data"/>
        </form>
        <form action="ActionProcess" method="POST">
            <input type="text" name="filter" value=""/>
            <input type="hidden" name="act" value="filteddata"/> <input type="submit" value="Filted Data"/>
        </form>
    </body>
</html>
