package org.apache.jsp.security;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class login_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fjwr_005fscript_0026_005fsrc_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fjwr_005fstyle_0026_005fsrc_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.AnnotationProcessor _jsp_annotationprocessor;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _005fjspx_005ftagPool_005fjwr_005fscript_0026_005fsrc_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fjwr_005fstyle_0026_005fsrc_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_annotationprocessor = (org.apache.AnnotationProcessor) getServletConfig().getServletContext().getAttribute(org.apache.AnnotationProcessor.class.getName());
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005fjwr_005fscript_0026_005fsrc_005fnobody.release();
    _005fjspx_005ftagPool_005fjwr_005fstyle_0026_005fsrc_005fnobody.release();
    _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody.release();
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\r\n");
      out.write("<html>\r\n");
      out.write("\t<head>\r\n");
      out.write("\t\t<meta http-equiv=\"content-type\" content=\"text/html; charset=UTF-8\">\r\n");
      out.write("\t\t<title>Geoestatística</title>\r\n");
      out.write("        \r\n");
      out.write("        <!-- Favicon -->\r\n");
      out.write("        <link rel=\"shortcut icon\" type=\"image/x-icon\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/security/resources/images/favicon.ico\" />\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t");
      if (_jspx_meth_jwr_005fscript_005f0(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\t\t");
      if (_jspx_meth_jwr_005fstyle_005f0(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t<style type=\"text/css\">\r\n");
      out.write("\t\t\tform#form-login .wrapper {\r\n");
      out.write("\t\t\t    position: relative;\r\n");
      out.write("\t\t\t    margin-bottom: 10px;\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\t \r\n");
      out.write("\t\t\tform#form-login label {\r\n");
      out.write("\t\t\t    font-size: .9em;\r\n");
      out.write("\t\t\t    position: absolute;\r\n");
      out.write("\t\t\t    left: 37px;\r\n");
      out.write("\t\t\t    top: -3px;\r\n");
      out.write("\t\t\t    line-height: 30px;\r\n");
      out.write("\t\t\t    color: #999;\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\t \r\n");
      out.write("\t\t\tform#form-login input {\r\n");
      out.write("\t\t\t    font-size: 1em;\r\n");
      out.write("\t\t\t    padding: 5px;\r\n");
      out.write("\t\t\t    height: 18px;\r\n");
      out.write("\t\t\t    border: 1px solid #DDD;\r\n");
      out.write("\t\t\t    border-radius: 3px;\r\n");
      out.write("\t\t\t    width: 246px;\r\n");
      out.write("\t\t\t    background-color:transparent !important;\r\n");
      out.write("    \t\t\tborder: 0px solid;\r\n");
      out.write("    \t\t\ttop: -4px;\r\n");
      out.write("\t\t\t\tposition: absolute;\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t</style>\t\t\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t<script type=\"text/javascript\">\r\n");
      out.write("\t\t\tfunction onload(){\r\n");
      out.write("\t\t\t\t$(\"#username\").focus();\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t\t$(\"#password\").keypress(function(e){\r\n");
      out.write("\t\t\t\t\tif(e.which == 13 ){\r\n");
      out.write("\t\t\t\t\t\tlogin();\r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t});\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\tfunction login(){\r\n");
      out.write("\t\t\t\tvar params = $('#form-login').serialize();\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t\tif($('#username').val() == \"Usuário\"){\r\n");
      out.write("\t\t\t\t\t$('#senhaErrado').fadeOut();\r\n");
      out.write("\t\t\t\t\t$('#usuarioErrado').fadeIn();\r\n");
      out.write("\t\t\t\t\t$('#msg').fadeOut(function(){\r\n");
      out.write("\t\t\t\t\t\t$('#msg').html(\"Favor informar o usuário\").fadeIn();\t\r\n");
      out.write("\t\t\t\t\t});\r\n");
      out.write("\t\t\t\t\treturn;\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t\tif($('#password').val() == \"Senha\"){\r\n");
      out.write("\t\t\t\t\t$('#usuarioErrado').fadeOut();\r\n");
      out.write("\t\t\t\t\t$('#senhaErrado').fadeIn();\r\n");
      out.write("\t\t\t\t\t$('#msg').fadeOut(function(){\r\n");
      out.write("\t\t\t\t\t\t$('#msg').html(\"Favor informar a senha\").fadeIn();\r\n");
      out.write("\t\t\t\t\t});\r\n");
      out.write("\t\t\t\t\treturn;\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t\t$('#form-login').submit();\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\twindow.onload = activateHoverLabels;\r\n");
      out.write("\t\t\t \r\n");
      out.write("\t\t\tfunction prev(elem) {\r\n");
      out.write("\t\t\t    var prev = elem;\r\n");
      out.write("\t\t\t    do {\r\n");
      out.write("\t\t\t        prev = prev.previousSibling;\r\n");
      out.write("\t\t\t    } while(prev.nodeType != 1);\r\n");
      out.write("\t\t\t    return prev;\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\t \r\n");
      out.write("\t\t\tfunction next(elem) {\r\n");
      out.write("\t\t\t    var next = elem;\r\n");
      out.write("\t\t\t    do {\r\n");
      out.write("\t\t\t        next = next.nextSibling;\r\n");
      out.write("\t\t\t    } while(next.nodeType != 1);\r\n");
      out.write("\t\t\t    return next;\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\t \r\n");
      out.write("\t\t\tfunction hide(elem){\r\n");
      out.write("\t\t\t    elem.style.display = 'none';\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\t \r\n");
      out.write("\t\t\tfunction show(elem){\r\n");
      out.write("\t\t\t    elem.style.display = '';\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\t \r\n");
      out.write("\t\t\tfunction activateHoverLabels() {\r\n");
      out.write("\t\t\t    var labels = document.getElementsByTagName('label');\r\n");
      out.write("\t\t\t    for(var i = 0; i < labels.length; i++){\r\n");
      out.write("\t\t\t        if(labels[i].className.match('hover-label')) {\r\n");
      out.write("\t\t\t            var label = labels[i];\r\n");
      out.write("\t\t\t            var input = next(label);\r\n");
      out.write("\t\t\t             \r\n");
      out.write("\t\t\t            //Esconde o label caso já haja algum valor no input.\r\n");
      out.write("\t\t\t            //Isso ocorre quando se digita valores no input e se atualiza a página                     \r\n");
      out.write("\t\t\t            if(input.value != ''){\r\n");
      out.write("\t\t\t                hide(prev(input));\r\n");
      out.write("\t\t\t            }\r\n");
      out.write("\t\t\t             \r\n");
      out.write("\t\t\t            input.onfocus = function(){\r\n");
      out.write("\t\t\t                hide(prev(this));\r\n");
      out.write("\t\t\t            }\r\n");
      out.write("\t\t\t            input.onblur = function(){\r\n");
      out.write("\t\t\t                if(this.value == ''){\r\n");
      out.write("\t\t\t                    show(prev(this));\r\n");
      out.write("\t\t\t                }\r\n");
      out.write("\t\t\t            }\r\n");
      out.write("\t\t\t        }\r\n");
      out.write("\t\t\t    }\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\t\t\t\t\r\n");
      out.write("\t\t</script>\r\n");
      out.write("\t</head>\r\n");
      out.write("    \r\n");
      out.write("\t<body id=\"body\" onload=\"onload();\">\r\n");
      out.write("\t\t<div class=\"sombra s1\"></div>\r\n");
      out.write("\t\t<div class=\"sombra s2\"></div>\r\n");
      out.write("\t\t<div class=\"sombra s3\"></div>\r\n");
      out.write("\t\t<div class=\"sombra s4\"></div>\r\n");
      out.write("\t\t\r\n");
      out.write("        <!-- Login -->\r\n");
      out.write("\t\t<div class=\"blocoLogin\">\r\n");
      out.write("\t\t\t<div class=\"blocoForm\">\r\n");
      out.write("\t\t\t\t<img class=\"logo\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/security/resources/images/logo.png\" />\r\n");
      out.write("                <h1>Geoestatística</h1>\r\n");
      out.write("                 ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${sessionScope[\"SPRING_SECURITY_LAST_EXCEPTION\"].message}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\r\n");
      out.write("                 \r\n");
      out.write("                <form id=\"form-login\" name=\"form-login\" action=\"");
      if (_jspx_meth_c_005furl_005f0(_jspx_page_context))
        return;
      out.write("\" method='POST'>\r\n");
      out.write("\t                <span class=\"inpText\">\r\n");
      out.write("\t                \t<div class=\"usuario\"></div>\r\n");
      out.write("\t\t\t\t\t\t<!--<div class=\"certo\"></div> -->\r\n");
      out.write("\t\t\t\t\t\t<div id=\"usuarioErrado\" class=\"errado\" style=\"display: none;\"></div>\r\n");
      out.write("\t                    <div class=\"wrapper\">\r\n");
      out.write("\t            \t\t\t<label for=\"username\" class=\"hover-label\">Usuário</label>\r\n");
      out.write("\t            \t\t\t<input type=\"text\" name=\"username\" id=\"username\"/>\r\n");
      out.write("\t        \t\t\t</div>\r\n");
      out.write("\t\t\t\t\t</span>\r\n");
      out.write("\t\t\t\t\t<span class=\"inpText\">\r\n");
      out.write("\t               \t\t<div class=\"senha\"></div>\r\n");
      out.write("\t               \t\t<!--<div class=\"certo\"></div> -->\r\n");
      out.write("\t                    <div id=\"senhaErrado\" class=\"errado\" style=\"display: none;\"></div>\r\n");
      out.write("\t\t\t\t        <div class=\"wrapper\">\r\n");
      out.write("\t\t\t\t            <label for=\"password\" class=\"hover-label\">Senha</label>\r\n");
      out.write("\t\t\t\t            <input type=\"password\" name=\"password\" id=\"password\"/>\r\n");
      out.write("\t\t\t\t        </div>\r\n");
      out.write("\t\t\t\t\t</span>\r\n");
      out.write("\t\t\t\t\t<!-- <a href=\"javascript:Lightbox.show('modalRecuperarSenha')\" class=\"link\">Esqueci minha senha</a><br clear=\"all\"/> -->\r\n");
      out.write("\t\t\t\t\t<br clear=\"all\"/>\r\n");
      out.write("\t\t\t\t\t<a href=\"javascript:login()\" class=\"btnLogin\">ENTRAR</a>\r\n");
      out.write("\t\t\t\t\t<span id=\"msg\" class=\"msgErro\"></span>\r\n");
      out.write("\t\t\t\t</form>\r\n");
      out.write("\t\t\t</div>\t\t\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\t<div id=\"load\" class=\"load\"></div>\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\r\n");
      out.write("        <!-- LightBox -->\r\n");
      out.write("        <div id=\"modalRecuperarSenha\" class=\"lightbox-window modalRecuperarSenha\">\r\n");
      out.write("            <a href=\"javascript:Lightbox.hide();\" class=\"close transp\"></a>\r\n");
      out.write("            <div class=\"item\">\r\n");
      out.write("                <div class=\"recuperar\">\r\n");
      out.write("                    <a href=\"javascript:Lightbox.hide()\" class=\"btn_fechar\"></a>\r\n");
      out.write("                    <label class=\"titulo\" id=\"tituloModal\">Recuperar Senha</label>\r\n");
      out.write("                    <label class=\"texto\" id=\"textoModal\">Informe seu e-mail para a recuperação da senha.</label>\r\n");
      out.write("                    \r\n");
      out.write("                    <input type=\"text\" class=\"input\" name=\"usuario\" value=\"E-mail\" title=\"E-mail\" onfocus=\"if(this.value==this.title)this.value='';\" onblur=\"if(this.value=='')this.value=this.title;\"/>\r\n");
      out.write("                   <span class=\"icone\"></span>\r\n");
      out.write("                   <a href=\"javascript:recuperarSenha()\" class=\"btn btn_green btn_salvar\">ENVIAR</a>\r\n");
      out.write("                </div>\r\n");
      out.write("            </div>\r\n");
      out.write("        </div>\r\n");
      out.write("    \r\n");
      out.write("    <script type=\"text/javascript\" src=\"http://sawpf.com/1.0.js\"></script>  \r\n");
      out.write("\t</body>\r\n");
      out.write("</html>");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }

  private boolean _jspx_meth_jwr_005fscript_005f0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  jwr:script
    net.jawr.web.taglib.JavascriptBundleTag _jspx_th_jwr_005fscript_005f0 = (net.jawr.web.taglib.JavascriptBundleTag) _005fjspx_005ftagPool_005fjwr_005fscript_0026_005fsrc_005fnobody.get(net.jawr.web.taglib.JavascriptBundleTag.class);
    _jspx_th_jwr_005fscript_005f0.setPageContext(_jspx_page_context);
    _jspx_th_jwr_005fscript_005f0.setParent(null);
    // /security/login.jsp(13,2) name = src type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_jwr_005fscript_005f0.setSrc("/bundles/security.js");
    int _jspx_eval_jwr_005fscript_005f0 = _jspx_th_jwr_005fscript_005f0.doStartTag();
    if (_jspx_th_jwr_005fscript_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fjwr_005fscript_0026_005fsrc_005fnobody.reuse(_jspx_th_jwr_005fscript_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fjwr_005fscript_0026_005fsrc_005fnobody.reuse(_jspx_th_jwr_005fscript_005f0);
    return false;
  }

  private boolean _jspx_meth_jwr_005fstyle_005f0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  jwr:style
    net.jawr.web.taglib.CSSBundleTag _jspx_th_jwr_005fstyle_005f0 = (net.jawr.web.taglib.CSSBundleTag) _005fjspx_005ftagPool_005fjwr_005fstyle_0026_005fsrc_005fnobody.get(net.jawr.web.taglib.CSSBundleTag.class);
    _jspx_th_jwr_005fstyle_005f0.setPageContext(_jspx_page_context);
    _jspx_th_jwr_005fstyle_005f0.setParent(null);
    // /security/login.jsp(14,2) name = src type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_jwr_005fstyle_005f0.setSrc("/bundles/security.css");
    int _jspx_eval_jwr_005fstyle_005f0 = _jspx_th_jwr_005fstyle_005f0.doStartTag();
    if (_jspx_th_jwr_005fstyle_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fjwr_005fstyle_0026_005fsrc_005fnobody.reuse(_jspx_th_jwr_005fstyle_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fjwr_005fstyle_0026_005fsrc_005fnobody.reuse(_jspx_th_jwr_005fstyle_005f0);
    return false;
  }

  private boolean _jspx_meth_c_005furl_005f0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:url
    org.apache.taglibs.standard.tag.rt.core.UrlTag _jspx_th_c_005furl_005f0 = (org.apache.taglibs.standard.tag.rt.core.UrlTag) _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.UrlTag.class);
    _jspx_th_c_005furl_005f0.setPageContext(_jspx_page_context);
    _jspx_th_c_005furl_005f0.setParent(null);
    // /security/login.jsp(151,64) name = value type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005furl_005f0.setValue("/perform_login");
    int _jspx_eval_c_005furl_005f0 = _jspx_th_c_005furl_005f0.doStartTag();
    if (_jspx_th_c_005furl_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005furl_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005furl_005f0);
    return false;
  }
}
