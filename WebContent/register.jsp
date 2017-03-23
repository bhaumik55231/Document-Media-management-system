<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Register</title>
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
<form action="<%=request.getContextPath()%>/login?flag=register" method="post">
<table align="center">
<tr><td>First Name</td><td><input type="text" name="firstName" placeholder="Enter First Name"></td></tr>
<tr><td>Last Name</td><td><input type="text" name="lastName" placeholder="Enter Last Name"></td></tr>
<tr><td>Email Id</td><td><input type="email" name="emailId" placeholder="abc@gmail.com"></td></tr>
<tr><td>User Id</td><td><input type="text" name="userId" placeholder="Enter User Id"></td></tr>
<tr><td>Password</td><td><input type="password" name="password" placeholder="Enter Password"></td></tr>
<tr><td>Confirm - Password</td><td><input type="password" name="confirmPassword" placeholder="Re - enter Password"></td></tr>
<tr><td colspan="2" align="center"><input type="submit" value="Register"></td></tr>




</table>
</form>
</body>
</html>