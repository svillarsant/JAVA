<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<h1 align="center">
		Crear el esquema de BB.DD para Spring Batch
	</h1>
	
	<form:form action="generarEsquema.htm">
		<p align=center>
			<input type="submit" value="Generar esquema"/>	
		</p>
	</form:form>
	
	<h1 align="center">
		${mensaje}
	</h1>

</body>
</html>