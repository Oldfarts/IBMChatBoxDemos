<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored ="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="application.setData" %>

<html>
    <head>
        <meta http-equiv="Content-Type" 
            content="text/html; charset=UTF-8"/>
        <title>ChatBoXPage</title>
    </head>
    <body>

<%
	String uidInput = (String) request.getHeader("uid");
	String msgInput = (String) request.getHeader("msg");
	setData set = new setData();
	set.setDataforDevice(uidInput, msgInput);
%>
    </body>
</html>