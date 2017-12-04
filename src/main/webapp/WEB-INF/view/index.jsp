<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <form action="<c:url value="/player"></c:url>" method="post">
            <input name="name" value="Mika" />
            <input name="transfermarktUrl" value="tm" />
            <input name="whoScoredUrl" value="ws" />
            <input name="pesDbUrl" value="pesdb" />
            <input name="psmlUrl" value="psml" />
            <button>Snimi</button>
        </form>
            
            
            <p>${player.name}</p>
            <p>${player.transfermarktUrl}</p>
            <p>${player.whoScoredUrl}</p>
            <p>${player.pesDbUrl}</p>
            <p>${player.psmlUrl}</p>
    </body>
</html>
