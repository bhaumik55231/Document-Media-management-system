<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Sign In</title>
</head>
<body>
<%
String validateSession=(String)session.getAttribute("userId");
if(validateSession != null){
	response.sendRedirect("loggedIn.jsp");
}
String error=(String)request.getAttribute("error");
if(error != null){
	out.print("<p>"+error +"</p>");
}
%>
<form action="<%=request.getContextPath()%>/login?flag=login" method="post">
<table align="center">
<tr>
<td>User Id</td>  <td><input type="text" name="userId" placeholder="Enter User Id"/></td></tr>
<tr><td>Password</td> <td><input type="password" name="password" placeholder="Enter password"/></td></tr>
<tr><td><input type="submit" value="sign-in"/></td></tr>
</table>
</form>
</body>
</html>