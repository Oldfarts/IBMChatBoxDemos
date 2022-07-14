<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored ="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="java.util.ArrayList"%> 
<%@page import="application.Messages"%>
<%@page import="application.MessagesListServlet"%>
<%@page import="application.viewData"%>
  
<html>
    <head>
        <meta http-equiv="Content-Type" 
            content="text/html; charset=UTF-8"/>
        <title>ChatBoXPage</title>
    </head>
    <body>
    	<form action="setData" method="post">
		<div>This is chat page</div>
		<div>Type message then press ENTER key to send message</div>
		<div>Click logout to return the login page</div>
		<div>Your name: <span id="uid">${UID}</span></div>
		<div id="content" class="content"></div>  
			<textarea class="msg-input" name="msg" type="msg" onkeyup="chat.dokeyup(event);">${messages}</textarea>
			<input name="uid" type="hidden" value="${UID}"></input>
			<input type="submit" value="msg-input" /></form>
	<form action="logout" method="post">
		<input type="submit" value="logout" />            
	</form>
	
	<%
	MessagesListServlet list = new MessagesListServlet();
	String returnList = list.getArraylistValues();
	out.print( returnList );
	%>
    </body>
</html>