<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registered</title>
</head>
<body>
<%
String validateSession=(String)session.getAttribute("userId");
if(validateSession != null){
	response.sendRedirect("loggedIn.jsp");
}
%>
Successfully registered - <%=request.getAttribute("first_name") %> <%=request.getAttribute("last_name") %>.

<a href="signin.jsp"><input type="button" value="Sign In"/></a>

</body>
</html>