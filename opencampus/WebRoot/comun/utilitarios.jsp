<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page errorPage="/error_action.jsp"%>
<%@ page import="edu.opencampus.lms.util.Constante"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="ct" uri="/WEB-INF/CampusTags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:set var="contextPath" value='${pageContext.request.contextPath}' />
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<s:include value="/comun/jslibs.jsp"/>
	</head>
	<body>
		<div id="container">
			
			<s:include value="/comun/bienvenida.jsp"></s:include>

			<div id="body">
					<table width="100%" cellpadding="3" cellspacing="0" class="open_table">
						<caption><s:text name="portal.menu.utilitarios"/></caption>
						<thead>
						<tr>
							<td width="11" align="center">
								&nbsp;
							</td>
							<td width="200"  align="center">
								<strong><s:text name="portal.utilitarios.producto"/></strong>
							</td>
							<td width="300"  align="center">
								<strong><s:text name="portal.utilitarios.descripcion"/></strong>
							</td>
							<td width="43" align="center">
								<strong><s:text name="portal.utilitarios.version"/></strong>
							</td>
							<td width="47" align="center">
								<strong><s:text name="portal.utilitarios.tamano"/></strong>
							</td>

							<td width="61" align="center">
								<strong><s:text name="portal.utilitarios.fabricante"/></strong>
							</td>
							<td width="50" align="center">
								<strong><s:text name="portal.utilitarios.website"/></strong>
							</td>
							<td width="53" align="center" class="moduloAbajo1">
								<strong><s:text name="portal.utilitarios.descarga"/></strong>
							</td>
						</tr>
						</thead>

						<tbody>
												
						<tr >

							<td class="bor_der_unid">
								1
							</td>
							<td  class="bor_der_cur">
								<strong>Flash Player para Internet Explorer </strong>
							</td>
							<td class="bor_der_cur">
							 	<s:text name="portal.utilitarios.flash"/>
							
							</td>
							<td align="center" class="bor_der_cur">
								9.0
							</td>

							<td align="center" class="bor_der_unid">
								1.1 MB
							</td>
							<td align="center" class="bor_der_unid">
								Adobe
							</td>
							<td class="bor_der_cur" align="center">
									<a
									href="http://www.adobe.com/shockwave/download/index.cgi?P1_Prod_Version=ShockwaveFlash&Lang=Spanish"
									target="_blank"><img src="<%=request.getContextPath()%>/img/website_icon.gif" alt="Web" border="0"></a>
							</td>
							<td align="center">
								<a href="http://www.tecsup.edu.pe/webcampus/soft/install_flash_player.exe"><img
										src="<%=request.getContextPath()%>/img/icon_download.gif" alt="Descargar"  border="0" /> </a>
							</td>
						</tr>
						<tr class="fon_color01">
							<td class="bor_der_unid">
								2
							</td>
							<td  class="bor_der_cur">
								<strong>Mozilla Firefox</strong>
							</td>
							<td class="bor_der_cur">	
							<s:text name="portal.utilitarios.firefox"/>
								
							</td>
							<td align="center" class="bor_der_cur">
								3.0
							</td>
							<td align="center" class="bor_der_cur">
								7.1 MB
							</td>
							<td align="center" class="bor_der_cur">
								Mozilla
							</td>
							<td class="bor_der_cur" align="center">
							<a href="http://www.mozilla-europe.org/es/products/firefox/" target="_blank"><img src="<%=request.getContextPath()%>/img/website_icon.gif" alt="Web" border="0"></a>
							</td>
							<td align="center">
								<a href="http://www.tecsup.edu.pe/webcampus/soft/Firefox Setup 3.0.6.exe"><img
										src="<%=request.getContextPath()%>/img/icon_download.gif" alt="Descargar" border="0" /> </a>
							</td>
						</tr>
						<tr>
							<td class="bor_der_unid">
								3
							</td>
							<td  class="bor_der_cur">
								<strong>Adobe Reader </strong>
							</td>
							<td class="bor_der_cur">	
							<s:text name="portal.utilitarios.acrobat"/>
								
							</td>
							<td align="center" class="bor_der_cur">
								9.0
							</td>
							<td align="center" class="bor_der_cur">
								24.9 MB
							</td>
							<td align="center" class="bor_der_cur">
								Adobe
							</td>
							<td class="bor_der_cur" align="center">
							<a href="http://www.adobe.com/es/products/acrobat/" target="_blank"><img src="<%=request.getContextPath()%>/img/website_icon.gif" alt="Web" border="0"></a>
							</td>
							<td align="center">
								<a href="http://www.tecsup.edu.pe/webcampus/soft/AdbeRdr90_es_ES.exe"><img
										src="<%=request.getContextPath()%>/img/icon_download.gif" alt="Descargar" border="0" /> </a>
							</td>
						</tr>	
						<tr class="fon_color01">
							<td class="bor_der_unid">
								4
							</td>
							<td  class="bor_der_cur">
								<strong>Flash Player para Mozilla Firefox</strong>
							</td>
							<td class="bor_der_cur">	
							<s:text name="portal.utilitarios.flash"/>								
							</td>
							<td align="center" class="bor_der_cur">
								10.0
							</td>
							<td align="center" class="bor_der_cur">
								1.8 MB
							</td>
							<td align="center" class="bor_der_cur">
								Adobe
							</td>
							<td class="bor_der_cur" align="center">
							<a href="http://www.adobe.com/es/products/acrobat/" target="_blank"><img src="<%=request.getContextPath()%>/img/website_icon.gif" alt="Web" border="0"></a>
							</td>
							<td align="center">
								<a href="http://www.tecsup.edu.pe/webcampus/soft/install_flash_player.exe"><img
										src="<%=request.getContextPath()%>/img/icon_download.gif" alt="Descargar" border="0" /> </a>
							</td>
						</tr>							
					</tbody>
					</table>
				
				
			</div>
			<s:include value="/comun/pie.jsp"/>
		</div>				
	</body>
</html>
