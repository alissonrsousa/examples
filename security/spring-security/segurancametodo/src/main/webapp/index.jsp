<%@ taglib uri="http://jawr.net/tags" prefix="jwr" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; UTF-8">
	<title>Spring Security</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="shortcut icon" type="image/x-icon" href="${pageContext.request.contextPath}/security/resources/images/favicon.ico" />
	
</head>
<body ng-app="aplicacao" id="ng-app" ng-controller="init">
	
	<div id="corpo">
		Bem Vindo à aplicação. <a href="/segurancametodo/logout">Sair</a>
	</div>
	
</body>
</html>