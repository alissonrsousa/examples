<%@ taglib uri="http://jawr.net/tags" prefix="jwr" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; UTF-8">
	<title>Geoestatistica</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="shortcut icon" type="image/x-icon" href="${pageContext.request.contextPath}/security/resources/images/favicon.ico" />
	
	<jwr:script src="/bundles/LoadMessage.js"/>
	
</head>
<body ng-app="aplicacao" id="ng-app" ng-controller="init">

	<script type="text/javascript">
		LoadMessage.create();
		LoadMessage.setTitle("Geoestatistica");
	</script>

	<script type="text/javascript">LoadMessage.setMessage('Carregando estilos...');</script>
	<jwr:style src="/bundles/all.css" />
	<script type="text/javascript">LoadMessage.setMessage('Carregando scripts...');</script>
	<jwr:script src="/bundles/all.js"/>
	<script type="text/javascript">LoadMessage.setMessage('Inicializando interface...');</script>
		
	<div ng-include="'app/view/topo.html'" id="topo"></div>
	
	<div id="corpo" ng-controller="consulta">
	
		<div id="blocoLayers">
			<div ng-include="'app/view/menu/menu.html'" class="content" style="display: block;" id="blocoContentLayers"></div>
		</div>
		
		<div ng-include="ctrl_consulta.contentView" id="centro" />	

	</div>
	
	<div ng-include="'app/view/rodape.html'" id="rodape"></div>
	
</body>
</html>