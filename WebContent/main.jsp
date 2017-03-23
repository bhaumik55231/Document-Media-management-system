<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="Model.retrieveFile" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Upload File</title>
</head>
<body>
<%
String validateSession=(String)session.getAttribute("firstName");
if(validateSession == null){
	response.sendRedirect("login.jsp");
}
String error=(String)request.getAttribute("error");
if(error != null){
	out.print("<p>"+error +"</p>");
}
%>
<%=session.getAttribute("firstName") %>

<form action="<%=request.getContextPath()%>/login?flag=logout" method="post">
<table align="right"><tr><td><input type="submit" value="Logout"></td></tr></table>
</form>

<form action="<%=request.getContextPath()%>/upload" method="post" enctype="multipart/form-data">
<table align="center">
<tr><td>Upload file</td><td><input type="file" name="fileName"></td></tr>
<tr><td>Description</td><td><textarea name="description" rows="5" cols="50" placeholder="Write some description about the file...."></textarea></td></tr>
<tr><td colspan="2"><input type="Submit" value="Upload File"></td></tr>
</table>

</form>
<br><br><br><br>
<hr>
<br>
<table border="1px" align="center">						
<tr><td>Files</td><td>Description</td></tr>
<c:forEach items="${sessionScope.media_files }" var="x">

<tr>
<td><a href="view.jsp?mediaid=${x.srno }">${x.file_name }</a></td>
<td>${x.description }</td>
</tr>
</c:forEach>

		</table>
				
</body>
</html>