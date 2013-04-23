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
					
	</head>
	<body>
		<div id="container">
		
			<s:include value="/comun/bienvenida.jsp"/>
			
			<div id="body">
				
					<c:choose>
						<c:when test="${fn:length(cursos)>0}">
							
							
							<div id="inicio" style="padding-bottom: 5px;">
								<table width="100%" cellpadding="3" cellspacing="0" class="open_table nogrid">
								<caption><s:text name="portal.cursos.titulo"/></caption>
									<tr>
										<td align="left">
											<div id="otros" style="margin: 0pt 0pt;">
												<center><c:out value="${message}"></c:out></center>
												<table width="98%" border="0" align="left" cellpadding="10" cellspacing="0" >
													<tr align="center">
														
														
														<c:forEach items="${cursos}" var="ficha" varStatus="fila">
														
														
														<td width="220px" align="center" class="ficha_tabla" valign="top">
															<div style="border: 2px black;">
																<table width="314" cellpadding="3" cellspacing="0" class="open_table nogrid">
																	<caption><a
																				href="<%=request.getContextPath()%>/aulavirtual/AulaVirtual.action?id=<c:out value="${ficha.idFicha}"/>"
																				class="tit_cab"> <c:out value="${ficha.curso.nombre}"/> </a></caption>
																	
																	<tr>
																		<td colspan="2">
																			<table border="0" align="center" cellpadding="0" cellspacing="0" 
																				style="padding-left: 3px;padding-right: 3px; ">
																				<tbody>
																				<tr>
																					<td width="85" height="112" align="center" valign="middle" style="border-right: none !important;">
																						<img src="<%=request.getContextPath()%>/VerFoto.action?id=<c:out value="${ficha.docentePrincipal.usuario.id}"/>" width="85" border="0"/> 
																					</td>
																					<td width="115" align="left" style="border-right: none !important; text-align: left !important; ">
																						<p style="text-align: left !important;">
																							<b><c:out value="${ficha.docentePrincipal.usuario.persona.nombreCompleto}"/></b>
																						</p>
																						<p style="text-align: left !important;">
																							<table border="0">
																							<c:if test="${ficha.matriculaActual.rol.idRol == 2 || ficha.matriculaActual.rol.idRol == 3}">
																								<tr>
																									<td style="border-right: none !important; text-align: left !important; "><s:text name="portal.cursos.fechaacceso"/>:</td>
																									<td style="border-right: none !important; text-align: left !important; "><c:out value="${ficha.periodo.fechaEdicionToString}"></c:out></td>
																								</tr>
																							</c:if>
																							<tr>
																								<td style="border-right: none !important; text-align: left !important; "><b><s:text name="portal.cursos.fechainicio"/>:</b></td>
																								<td style="border-right: none !important; text-align: left !important; "><b><c:out value="${ficha.periodo.fechaInicioToString}"></c:out></b></td>
																							</tr>
																							<tr>
																								<td style="border-right: none !important; text-align: left !important; "><b><s:text name="portal.cursos.fechafin"/>:</b></td>
																								<td style="border-right: none !important; text-align: left !important; "><b><c:out value="${ficha.periodo.fechaFinToString}"></c:out></b></td>
																							</tr>
																							<c:if test="${ficha.matriculaActual.rol.idRol == 2 || ficha.matriculaActual.rol.idRol == 3}">
																								<tr>
																									<td style="border-right: none !important; text-align: left !important; "><s:text name="portal.cursos.fechacierre"/>:</td>
																									<td style="border-right: none !important; text-align: left !important; "><c:out value="${ficha.periodo.fechaRevisionToString}"></c:out></td>
																								</tr>
																							</c:if>
																							</table>
																						</p>
																					</td>
																				</tr>
																				<tr>
																					<td colspan="2"  style="border-right: none !important;">
																						<hr width="90%" />
																					</td>
																				</tr>
																				<%if(false){ %>
																				<c:if test="${ficha.matriculaActual.rol.idRol == 2 || ficha.matriculaActual.rol.idRol == 3}">
																						
																				<tr>
																					<td align="left"  style="border-right: none !important;">
																						<s:text name="portal.cursos.fechaacceso"/>
																					</td>
																					<td align="left"  style="border-right: none !important;">
																						:
																						<c:out value="${ficha.periodo.fechaEdicionToString}"></c:out>
																					</td>
																				</tr>
																				
																				</c:if>
																				
																				<tr>
																					<td align="left"  style="border-right: none !important;">
																						<s:text name="portal.cursos.fechainicio"/>
																					</td>
																					<td align="left"  style="border-right: none !important;">
																						:
																						<c:out value="${ficha.periodo.fechaInicioToString}"></c:out>
																					</td>
																				</tr>
																				<tr>
																					<td align="left"  style="border-right: none !important;">
																						<s:text name="portal.cursos.fechafin"/>
																					</td>
																					<td align="left"  style="border-right: none !important;">
																						:
																						<c:out value="${ficha.periodo.fechaFinToString}"></c:out>
																					</td>
																				</tr>
																				
																				<c:if test="${ficha.matriculaActual.rol.idRol == 2 || ficha.matriculaActual.rol.idRol == 3}">
																				
																				<tr>
										
																					<td align="left"  style="border-right: none !important;">
																						<s:text name="portal.cursos.fechacierre"/>
																					</td>
																					<td align="left"  style="border-right: none !important;">
																						:
																						<c:out value="${ficha.periodo.fechaRevisionToString}"></c:out>
																					</td>
																					
																				</tr>
																				
																				</c:if>
																				<%} %>
																				
																				
																				<tr>
																					<c:if test="${ficha.silabo.flagDialogosTotal > 0}">
																					<td align="left"  style="border-right: none !important;">
																						<s:text name="portal.cursos.dialogos"/>
																					</td>
																					<td align="left"  style="border-right: none !important;">
																						:
																						<c:out value="${ficha.silabo.flagDialogosTotal}"></c:out>
																						<img src="<%=request.getContextPath()%>/img/flag.gif" alt="flag" width="8"
																							height="11" />
																					</td>
																					</c:if>
																				</tr>
																				<tr>
																					<c:if test="${ficha.silabo.flagLaboratoriosTotal > 0}">
																					<td align="left"  style="border-right: none !important;">
																						<s:text name="portal.cursos.laboratorios"/>
																					</td>
																					<td align="left"  style="border-right: none !important;">
																						:
																						<c:out value="${ficha.silabo.flagLaboratoriosTotal}"></c:out>
																						<img src="<%=request.getContextPath()%>/img/flag.gif" alt="flag" width="8"
																							height="11" />
																					</td>
																					</c:if>
																				</tr>
																				<tr>
																					<c:if test="${ficha.silabo.flagTIndividualTotal > 0}">
																					<td align="left"  style="border-right: none !important;">
																						<s:text name="portal.cursos.trabajos"/>
																					</td>
																					<td align="left"  style="border-right: none !important;">
																						:
																						<c:out value="${ficha.silabo.flagTIndividualTotal}"></c:out>
																						<img src="<%=request.getContextPath()%>/img/flag.gif" alt="flag" width="8"
																							height="11" />
																					</td>
																					</c:if>
																				</tr>
																				<tr>
																					<c:if test="${ficha.silabo.flagTGrupalTotal > 0}">
																					<td align="left"  style="border-right: none !important;">
																						<s:text name="portal.cursos.grupales"/>
																					</td>
																					<td align="left"  style="border-right: none !important;">
																						:
																						<c:out value="${ficha.silabo.flagTGrupalTotal}"></c:out>
																						<img src="<%=request.getContextPath()%>/img/flag.gif" alt="flag" width="8"
																							height="11" />
																					</td>
																					</c:if>
																				</tr>
																				<tr>
																					<c:if test="${ficha.silabo.flagDebatesTotal > 0}">
																					<td align="left"  style="border-right: none !important;">
																						<s:text name="portal.cursos.debates"/>
																					</td>
																					<td align="left"  style="border-right: none !important;">
																						:
																						<c:out value="${ficha.silabo.flagDebatesTotal}"></c:out>
																						<img src="<%=request.getContextPath()%>/img/flag.gif" alt="flag" width="8"
																							height="11" />
																					</td>
																					</c:if>
																				</tr>
		
		
																				
																				<tr>
																					<td colspan="2" height="10"  style="border-right: none !important;">&nbsp;
																						
																					</td>
																				</tr>
																				
																				
																			</table>
																		</td>
																	</tr>
																	</tbody>
																	<tfoot>
																		<tr>
																		<td align="left" colspan="2">
																			<a
																				href="<%=request.getContextPath()%>/aulavirtual/AulaVirtual.action?id=<c:out value="${ficha.idFicha}"/>"
																				class="ingr_curso" style="padding-right: 5px;"><s:text name="portal.servicios.link" /></a>
																		</td>
																	</tr>
																	</tfoot>
																</table>
															</div>
														</td>
														
														
														<c:if test="${fila.count%3==0}">
															</tr><tr>
														</c:if>
														
														</c:forEach>
														
		
													</tr>
												</table>
											</div>
										</td>
									</tr>
								</table>
							</div>
						</c:when>
						<c:otherwise>
							
							
							<table width="100%" cellpadding="3" cellspacing="0" class="open_table nogrid">
								<caption><s:text name="portal.cursos.titulo"/></caption>
								<tr>
									<td align="center" class="moduloAbajo1">
										<strong><s:text name="portal.cursos.vacio"/></strong>
									</td>
								</tr>
							</table>
							
						</c:otherwise>
					</c:choose>
					
			</div>
			
			<s:include value="/comun/pie.jsp"/>
			
		</div>
	</body>
</html>
