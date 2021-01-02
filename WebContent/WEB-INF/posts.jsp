<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>

<html lang="es">
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0 shrink-to-fit=no">
	<title>Recetas</title>
</head>
<body>	
	
	<div>
		<c:forEach info="${posts}" var="post">
			${post.id_blog}
			<br>
			<c:out value="${post.nombre}"/>
			<br>
			<c:out value="${post.contenido}"/>
		</c:forEach>
	</div>	
</body>
</html>