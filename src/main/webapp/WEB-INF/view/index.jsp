<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="partials/header.jsp" %>
                
                <h1>Create Player</h1>
                <input type="text" name="transfermarktUrl" value="https://www.transfermarkt.com/suso/profil/spieler/111961" />
                <input type="text" name="whoScoredUrl" value="https://www.whoscored.com/Players/105591/Show/Suso" />
                <input type="text" name="pesDbUrl" value="http://pesdb.net/pes2018/?id=45267" />
                <input type="text" name="psmlUrl" value="http://psml.rs/?action=shwply&playerID=45267" />
                <input type="checkbox" name="myPlayer"> 
                <button>Pošalji</button>
                
<%@include file="partials/footer.jsp" %>
