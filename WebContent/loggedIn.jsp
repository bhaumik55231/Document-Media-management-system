<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Logged In</title>
</head>
<%
String validateSession=(String)session.getAttribute("firstName");
if(validateSession == null){
	response.sendRedirect("login.jsp");
}
%>
<body>
<form action="<%=request.getContextPath()%>/login?flag=logout" method="post">
You are already Logged In
<br>
<input type="submit" value="Logout">
</form>
</body>
</html>