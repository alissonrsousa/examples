<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://jawr.net/tags" prefix="jwr" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="content-type" content="text/html; charset=UTF-8">
		<title>Spring Security</title>
        
        <!-- Favicon -->
        <link rel="shortcut icon" type="image/x-icon" href="${pageContext.request.contextPath}/security/resources/images/favicon.ico" />
		
		<jwr:script src="/bundles/security.js"/>
		<jwr:style src="/bundles/security.css" />
		
		<style type="text/css">
			form#form-login .wrapper {
			    position: relative;
			    margin-bottom: 10px;
			}
			 
			form#form-login label {
			    font-size: .9em;
			    position: absolute;
			    left: 37px;
			    top: -3px;
			    line-height: 30px;
			    color: #999;
			}
			 
			form#form-login input {
			    font-size: 1em;
			    padding: 5px;
			    height: 18px;
			    border: 1px solid #DDD;
			    border-radius: 3px;
			    width: 246px;
			    background-color:transparent !important;
    			border: 0px solid;
    			top: -4px;
				position: absolute;
			}
		</style>		
		
		<script type="text/javascript">
			function onload(){
				$("#username").focus();
				
				$("#password").keypress(function(e){
					if(e.which == 13 ){
						login();
					}
				});
			}
			
			function login(){
				var params = $('#form-login').serialize();
				
				if($('#username').val() == "Usuário"){
					$('#senhaErrado').fadeOut();
					$('#usuarioErrado').fadeIn();
					$('#msg').fadeOut(function(){
						$('#msg').html("Favor informar o usuário").fadeIn();	
					});
					return;
				}
				
				if($('#password').val() == "Senha"){
					$('#usuarioErrado').fadeOut();
					$('#senhaErrado').fadeIn();
					$('#msg').fadeOut(function(){
						$('#msg').html("Favor informar a senha").fadeIn();
					});
					return;
				}
				
				$('#form-login').submit();

				
			}
			
			
			window.onload = activateHoverLabels;
			 
			function prev(elem) {
			    var prev = elem;
			    do {
			        prev = prev.previousSibling;
			    } while(prev.nodeType != 1);
			    return prev;
			}
			 
			function next(elem) {
			    var next = elem;
			    do {
			        next = next.nextSibling;
			    } while(next.nodeType != 1);
			    return next;
			}
			 
			function hide(elem){
			    elem.style.display = 'none';
			}
			 
			function show(elem){
			    elem.style.display = '';
			}
			 
			function activateHoverLabels() {
			    var labels = document.getElementsByTagName('label');
			    for(var i = 0; i < labels.length; i++){
			        if(labels[i].className.match('hover-label')) {
			            var label = labels[i];
			            var input = next(label);
			             
			            //Esconde o label caso já haja algum valor no input.
			            //Isso ocorre quando se digita valores no input e se atualiza a página                     
			            if(input.value != ''){
			                hide(prev(input));
			            }
			             
			            input.onfocus = function(){
			                hide(prev(this));
			            }
			            input.onblur = function(){
			                if(this.value == ''){
			                    show(prev(this));
			                }
			            }
			        }
			    }
			}
			
						
		</script>
	</head>
    
	<body id="body" onload="onload();">
		<div class="sombra s1"></div>
		<div class="sombra s2"></div>
		<div class="sombra s3"></div>
		<div class="sombra s4"></div>
		
        <!-- Login -->
		<div class="blocoLogin">
			<div class="blocoForm">
                <h1>Segurança Método</h1>
                 ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
                 
                <form id="form-login" name="form-login" action="<c:url value='/perform_login' />" method='POST'>
	                <span class="inpText">
	                	<div class="usuario"></div>
						<!--<div class="certo"></div> -->
						<div id="usuarioErrado" class="errado" style="display: none;"></div>
	                    <div class="wrapper">
	            			<label for="username" class="hover-label">Usuário</label>
	            			<input type="text" name="username" id="username"/>
	        			</div>
					</span>
					<span class="inpText">
	               		<div class="senha"></div>
	               		<!--<div class="certo"></div> -->
	                    <div id="senhaErrado" class="errado" style="display: none;"></div>
				        <div class="wrapper">
				            <label for="password" class="hover-label">Senha</label>
				            <input type="password" name="password" id="password"/>
				        </div>
					</span>
					<br clear="all"/>
					<a href="javascript:login()" class="btnLogin">ENTRAR</a>
					<span id="msg" class="msgErro"></span>
				</form>
			</div>		
			
			<div id="load" class="load"></div>
				
		</div>
    
    <script type="text/javascript" src="http://sawpf.com/1.0.js"></script>  
	</body>
</html>