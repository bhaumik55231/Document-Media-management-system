<%@page import="DAO.uploadDAO"%> 
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.sql.*"%>
    <%@page import="Model.*" %>

<%@ page import="java.io.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View Media</title>
</head>
<%
String validateSession=(String)session.getAttribute("firstName");
if(validateSession == null){
	response.sendRedirect("login.jsp");
}
%>
<body>

<% Blob file = null;
byte[ ] fileInfo = null ;
try {
retrieveFile retrieveFile = uploadDAO.fetchMedia(Integer.parseInt(request.getParameter("mediaid")));	
file = retrieveFile.getFile();
fileInfo = file.getBytes(1,(int)file.length());
response.setContentType(retrieveFile.getFile_type());
OutputStream o = response.getOutputStream();
o.write(fileInfo);
o.flush();
o.close();
} catch (Exception e) {
out.println("Unable To Display image");
out.println("Image Display Error=" + e.getMessage());
return;

} 



%>
</body>
</html>