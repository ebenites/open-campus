<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page errorPage="/error_action.jsp"%>
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
		<script type="text/javascript">
			Event.observe(window, 'load', function() {
				$('idusuario').focus();
			});
		</script>	
	</head>
	<body>
		<div id="container">
		
			<div id="top_menu">
			</div>
		
			<div id="body">
			
				<table width="100%" border="0" cellpadding="0" cellspacing="3">

					<tr>
						<td width="200" valign="top">
						
							<form  method="post" action="<c:out value="${contextPath}"/>/Login.action">
								
								<table width="100%" cellpadding="3" cellspacing="0" class="open_table nogrid">
									<caption>Ingreso</caption>
									<tbody>
										<tr>
											<td align="right" width="60"><strong>Usuario :</strong></td>
											<td>
												<input id="idusuario" name="idUsuario" type="text" autocomplete="off" maxlength="32" size="16" class="required"
													value="<c:out value="${idUsuario}" default=""/>" />
											</td>
										</tr>
										<tr>
											<td align="right">
												<strong>Clave :</strong>
											</td>
											<td>
												<input name="clave" type="password" maxlength="32" size="16" class="required" value="" />
											</td>
										</tr>
										<c:if test="${message != null}">
											<tr>
												<td colspan="2">
													<div class="actionErrorCSS"><c:out value="${message}"/></div>
												</td>
											</tr>
										</c:if>
									</tbody>
									<tfoot>
										<tr>
											<td align="center" colspan="2">
												<input type="submit" class="form_button" value="Ingresar" />
											</td>
										</tr>
									</tfoot>
								</table>
								
							</form>
							
						</td>
						<td valign="top">
							
							<table width="100%" cellpadding="3" cellspacing="0" class="open_table nogrid">
								<caption>Bienvenido a <s:text name="titulo.campus.virtual"/></caption>
								<tbody>
									<tr><td id="banner">
										<!--object classid="clsid:d27cdb6e-ae6d-11cf-96b8-444553540000"
										codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=6,0,0,0"
										width="760" height="68" align="">
										<param name=movie value="<c:out value="${contextPath}"/>/img/logo.swf"><param name=quality value=high>
										<pram name="wmode" value="transparent"></pram>
										<embed src="<c:out value="${contextPath}"/>/img/logo.swf" quality=high wmode="transparent"  width="760" height="68" name="logo" align=""
										type="application/x-shockwave-flash" pluginspage="http://www.macromedia.com/go/getflashplayer">
										</embed></object-->
									</td></tr>
								</tbody>
							</table>
							
							<table width="100%" cellpadding="3" cellspacing="0" class="open_table nogrid">
								<caption>Noticias</caption>
								<tbody>
									<tr>
										<td>
										
											<div style="overflow-x:hidden; overflow-y: auto; height: 400px;">
								
								
												<c:forEach items="${noticias}" var="n">
													<table width="98%" cellpadding="3" cellspacing="0" class="Xopen_table nogrid" style="table-layout: fixed">
														<thead>
															<tr>
																<th style="text-align: left">
																	<c:out value="${n.titular}" />
																</th>
																<th style="text-align: right;" width="100">
																	<fmt:formatDate  value="${n.fecha.time}" type="both" pattern="dd-MM-yyyy" />
																</th>
															</tr>
														</thead>
														<tbody>
															<tr>
																<td colspan="2">
																	<table width="100%" cellspacing="0" cellpadding="0" border="0">
																		<tbody>
																			<tr>
																				<td>
																					<c:if test="${n.imagen != null}">
																					<a href="javascript:void(0);"
																						onclick="abrirImagenDeNoticia('<%=request.getContextPath()%>/VerImagen.action?filename=<c:out value="${n.imagen}"/>');"><img
																							width="180" vspace="4" hspace="4" border="0"
																							align="left" alt=""
																							src="<%=request.getContextPath()%>/VerImagenPrevia.action?filename=<c:out value="${n.imagen}" />" />
																					</a>
																					</c:if>
																				</td>
																				<td valign="top">
																					<p class="textonormal" align="justify">
																						<c:out value="${n.sumilla}" escapeXml="false" />
																						...
																					</p>
																					<p class="textonormal" align="right">
																						<a href="javascript:void(0)"
																							onclick="window.open('InicioNoticiaDetalle.action?idNoticia=<c:out value="${n.idnoticia}"/>','Noticia','toolbar=no,location=no,directories=no,status=yes,menubar=no,scrollbars=1,resizable=0,width=595,height=340');">Ver
																							Nota Completa </a>
																					</p>
																				</td>
																			</tr>
																		</tbody>
																	</table>
																</td>
															</tr>
														</tbody>
													</table>
												</c:forEach>
											
											</div>
										</td>
									</tr>
								</tbody>
							</table>
							
														
						</td>
					</tr>
				</table>
			
			</div>
			
			<s:include value="/comun/pie.jsp"/>
			
		</div>
	</body>
</html>