<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page errorPage="../../error_action.jsp" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="json" uri="http://www.atg.com/taglibs/json" %>
<%@  page import="edu.opencampus.lms.util.Constante"%>
<%@taglib prefix="ct" uri="/WEB-INF/CampusTags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type"
			content="text/html; charset=ISO-8859-1" />
		<title><s:text name="titulo.campus.virtual" /></title>
		<link href="<%=request.getContextPath()%>/estilos/estilos.css"
			rel="stylesheet" type="text/css" />
		<link href="<%=request.getContextPath()%>/js/menu/menu.css"
			rel="stylesheet" type="text/css" />
		<script language="javascript" type="text/javascript"
			src="<%=request.getContextPath()%>/js/jPrototype.js"></script>
		<script language="javascript" type="text/javascript"
			src="<%=request.getContextPath()%>/js/jComponente.js"></script>
		<script language="javascript" type="text/javascript"
			src="<%=request.getContextPath()%>/js/util.js"></script>
		<script type="text/javascript"
			src='<%=request.getContextPath()%>/admin/aulavirtual/aula_virtual.js'></script>
		<script language="javascript" type="text/javascript"
			src="<%=request.getContextPath()%>/js/aviso.js"></script>
	</head>
	<body onunload="cerrarAula()">
		<form>
			<div id="contenedor">
			
				<%@include file="/admin/aulavirtual/bienvenida_aula.jsp"%>
				
				<div id="cuerpo">
					<div id="pop_cuerpo">

						<%-- EXISTEN UNIDADES --%>

						<c:if test="${aula!=null && fn:length(aula.silabo.unidades)>0}">

							<table width="100%" align="center" cellpadding="3" cellspacing="0" class="bor_tabla" border="0" style="table-layout: fixed;">
								<caption class="fon_cab tit_cab"><s:text name="aula.alumno.unidades"/></caption>
								<tr class="tabla01_subEncabezado">
									<th class=moduloAbajo width="12">
										&nbsp;
									</th>
									<th class="moduloAbajo" width="40">
										ID
									</th>
									<th class="moduloAbajo" width="46" colspan="2">
										<c:out value="${aula.silabo.unidades[0].recursos[0].nombreCorto}" />
									</th>
									<th class="moduloAbajo1" width="20">
										&nbsp;
									</th>
									<th class="moduloAbajo1">
										<c:out value="${aula.silabo.unidades[0].recursos[1].nombreCorto}" />
									</th>
									<th class="moduloAbajo" width="25">
										&nbsp;
									</th>
									<th class="moduloAbajo" width="46" colspan="2">
										<c:out value="${aula.silabo.unidades[0].recursos[2].nombreCorto}" />
									</th>
									<th class="moduloAbajo" width="46" colspan="2">
										<c:out value="${aula.silabo.unidades[0].recursos[3].nombreCorto}" />
									</th>
									<th class="moduloAbajo" width="46" colspan="2">
										<c:out value="${aula.silabo.unidades[0].recursos[4].nombreCorto}" />
									</th>
									<th class="moduloAbajo" width="50" colspan="2">
										<c:out value="${aula.silabo.unidades[0].recursos[5].nombreCorto}" />
									</th>
									<th class="moduloAbajo" width="76" colspan="3">
										<c:out value="${aula.silabo.unidades[0].recursos[6].nombreCorto}" />
									</th>
									<th class="moduloAbajo1" width="20">
										&nbsp;
									</th>
								</tr>
								<c:forEach items="${aula.silabo.unidades}" var="unidad" varStatus="fila">
									<tr class="tabla01_fila<c:out value="${fila.count%2 + 1}"/>">
										<td align="center" width="12" class="texto1"><c:out value="${fila.count}"/> </td>
										<td align="center" width="40" class="texto1"><b><c:out value="${unidad.idUnidad}"/></b></td>
										
										<!-- ********** Texto ********** -->
										<c:set var="texto" value="${unidad.recursos[0]}" />
										
										<td align="center" width="20">
											<img src="<%=request.getContextPath()%>/img/icon_config.gif" style="cursor: pointer;" alt="Configurar Disponibilidad"
												onclick="configResource(this,'<c:out value="${unidad.idUnidad}"/>','<c:out value="${unidad.recursos[0].idRecurso}"/>')"/>
										</td>
										<td align="center" class="texto1" width="20" style="cursor: pointer;" 
											onclick="abrirTexto('<%=request.getContextPath()%>/admin/unidad/VerRecurso.action?ruta=1&id=<c:out value="${unidad.idUnidad}"/>');">
											<img src="<%=request.getContextPath()%>/img/icon_libro.gif" alt="Ver Texto"/>
											<div style="position: absolute; font-size: 8px;font-weight: bold; margin-left: -20px;">
												<img src="<%=request.getContextPath()%>/img/icon_letter_docente.gif" alt="Habilitado para el Docente"/><img 
												src="<%=request.getContextPath()%>/img/icon_letter_estudiante.gif" alt="Habilitado para el Estudiante"/>
											</div>
										</td>
										
										<!-- ********** Repaso ********** -->
										<c:set var="repaso" value="${unidad.recursos[1]}" />
										
										<td align="center" width="20">
											<img src="<%=request.getContextPath()%>/img/icon_config.gif" style="cursor: pointer;" alt="Configurar Disponibilidad"
												onclick=""/>
										</td>
										<td align="left">
											<span style="cursor: pointer; text-decoration: underline; white-space: nowrap;" 
												onclick="abrirTexto('<%=request.getContextPath()%>/admin/unidad/VerRecurso.action?ruta=3&id=<c:out value="${unidad.idUnidad}"/>');">
												<c:out value="${unidad.nombreCompleto}"/>
											</span>
										</td>
										
										<td align="center" class="texto1" width="25">
											<img src="<%=request.getContextPath()%>/img/icon_download.gif" style="cursor: pointer;" alt="Descargar Repaso"
												onclick="abrirTexto('<%=request.getContextPath()%>/admin/unidad/VerRecurso.action?ruta=4&id=<c:out value="${unidad.idUnidad}"/>')"/>
										</td>
										
										<!-- ********** Laboratorio ********** -->
										<c:set var="lab" value="${unidad.recursos[2]}" />
										
										<td align="center" width="20">
											<img src="<%=request.getContextPath()%>/img/icon_config.gif" style="cursor: pointer;" alt="Configurar Disponibilidad"
												onclick=""/>
										</td>
										<td align="center" class="texto1" width="20">
											<img src="<%=request.getContextPath()%>/img/icon_lab.gif" style="cursor: pointer;" alt="Ver Laboratorio"
												onClick="abrirLaboratorioPdf('<%=request.getContextPath()%>/admin/unidad/VerRecurso.action?ruta=2&id=<c:out value="${unidad.idUnidad}"/>');"/>
										</td>
										
										<!-- ********** Dialogo ********** -->
										<c:set var="dia" value="${unidad.recursos[3]}" />
										
										<td align="center" width="20">
											<img src="<%=request.getContextPath()%>/img/icon_config.gif" style="cursor: pointer;" alt="Configurar Disponibilidad"
												onclick=""/>
										</td>
										<td align="center" class="texto1" width="20">
											<img src="<%=request.getContextPath()%>/img/icon_dialog.gif" style="cursor: pointer;" alt="Sala de Diálogo"
												onClick="abrirDialogo('<%=request.getContextPath()%>/aulavirtual/dialogo/Cargar.action?idUnidad=<c:out value="${unidad.idUnidad}"/>','Dialogo','620','400');"/>
										</td>
										
										<!-- ********** Trabajo ********** -->
										<c:set var="trabajo" value="${unidad.recursos[4]}" />
										
										<td align="center" width="20">
											<img src="<%=request.getContextPath()%>/img/icon_config.gif" style="cursor: pointer;" alt="Configurar Disponibilidad"
												onclick=""/>
										</td>
										<td align="center" class="texto1" width="20">
											<img src="<%=request.getContextPath()%>/img/icon_trab.gif" style="cursor: pointer;" alt="Trabajo Individual"
												onClick="abrirTrabajo('<%=request.getContextPath()%>/aulavirtual/tindividual/Cargar.action?idUnidad=<c:out value="${unidad.idUnidad}"/>')"/>
										</td>
										
										<!-- ********** Grupal ********** -->
										<c:set var="trabajo" value="${unidad.recursos[5]}" />
										
										<td align="center" width="20">
											<img src="<%=request.getContextPath()%>/img/icon_config.gif" style="cursor: pointer;" alt="Configurar Disponibilidad"
												onclick=""/>
										</td>
										<td align="center" class="texto1" width="20">
											<img src="<%=request.getContextPath()%>/img/icon_trab_grup.gif" style="cursor: pointer;" alt="Trabajo Grupal"
												onClick="abrirTrabajo('<%=request.getContextPath()%>/aulavirtual/tgrupal/Cargar.action?idUnidad=<c:out value="${unidad.idUnidad}"/>')"/>
										</td>
										
										<!-- ********** Test ********** -->
										<c:set var="test" value="${unidad.recursos[6]}" />
										
										<td align="center" width="20">
											<img src="<%=request.getContextPath()%>/img/icon_config.gif" style="cursor: pointer;" alt="Configurar Disponibilidad"
												onclick=""/>
										</td>
										<td align="center" width="18">
											<img src="<%=request.getContextPath()%>/img/icon_test.gif" style="cursor: pointer;" alt="Evaluación"
												onClick="abrirTest('<%=request.getContextPath()%>/aulavirtual/test/Listar.action?idUnidad=<c:out value="${unidad.idUnidad}"/>')"/>
										</td>
										<td align="left" class="texto1" width="20">
											(<c:out value="${unidad.cantidadTest}"/>)
										</td>
										
										<!-- ********** Estado Unidad ********** -->
										<td align="center" width="20">
										  	<c:choose>
										  		<c:when test="${unidad.estado==1}">
										  			<img src="<%=request.getContextPath() %>/img/activo.gif" border="0" alt="Hacer No Disponible" style="cursor: pointer;"
										  			onclick="cambiarEstado(this,<c:out value="${unidad.idUnidad}"/>)" />
										  		</c:when>
										  		<c:otherwise>
										  			<img src="<%=request.getContextPath() %>/img/desactivo.gif" border="0" alt="Hacer Disponible" style="cursor: pointer;"
										  			onclick="cambiarEstado(this,<c:out value="${unidad.idUnidad}"/>)"/>
										  		</c:otherwise>
										  	</c:choose>
										  	
										  </td>
										
									</tr>
								</c:forEach>
								
							</table>
						
						</c:if>
						<!--
						<json:object name="aula" escapeXml="true">
						  <json:property name="itemCount" value="${aula.idFicha}"/>
						  <json:property name="subtotal" value="${aula.curso.nombre}"/>
						  <json:array name="items" var="item" items="${aula.silabo.unidades}">
						    <json:object>
						      <json:property name="title" value="${item.idUnidad}"/>
						      <json:property name="description" value="${item.nombreCompleto}"/>
						    </json:object>
						  </json:array>
						</json:object>
						-->
							<!-- if(usuario.esAdmin())mostrar boton de regresar a fichas -->
							<!-- http://json-taglib.sourceforge.net/tutorial.html -->
							<!-- http://www.roseindia.net/jstl/jstlxmltags.shtml -->
					</div>
				</div>
				<div id="pie">
					<%@include file="../../comun/pie.jsp"%>
				</div>
			</div>
		</form>
				
		<div id="menu_config"  style="position:absolute; left:0px; top:160px; background-color:#FFF; padding:1px; Xdisplay: none;">
			<table border="0" cellpadding="3" cellspacing="0" class="tabla01">
				<tr>
					<td width="20" align="center" style="cursor:pointer;"  onClick=""
					onmouseover="seleccion(this, true)" onmouseout="seleccion(this, false)">
						<c:choose>
					  		<c:when test="${unidad.estado==1}">
					  			<img src="<%=request.getContextPath() %>/img/activo.gif" border="0" alt="Hacer No Disponible" style="cursor: pointer;"
					  			onclick="" />
					  		</c:when>
					  		<c:otherwise>
					  			<img src="<%=request.getContextPath() %>/img/desactivo.gif" border="0" alt="Hacer Disponible" style="cursor: pointer;"
					  			onclick=""/>
					  		</c:otherwise>
					  	</c:choose>
					</td>
					<td width="20" align="center" style="cursor:pointer;"  onClick=""
					onmouseover="seleccion(this, true)" onmouseout="seleccion(this, false)">
						<c:choose>
					  		<c:when test="${unidad.estado==1}">
					  			<img src="<%=request.getContextPath() %>/img/icon_letter_docente_si2.gif" border="0" alt="Deshabilitar para el Docente" style="cursor: pointer;"
					  			onclick="" />
					  		</c:when>
					  		<c:otherwise>
					  			<img src="<%=request.getContextPath() %>/img/icon_letter_docente_no2.gif" border="0" alt="Habilitar para el Docente" style="cursor: pointer;"
					  			onclick=""/>
					  		</c:otherwise>
					  	</c:choose>
					</td>
					<td width="20" align="center" style="cursor:pointer;"  onClick=""
					onmouseover="seleccion(this, true)" onmouseout="seleccion(this, false)">
						<c:choose>
					  		<c:when test="${unidad.estado!=1}">
					  			<img src="<%=request.getContextPath() %>/img/icon_letter_estudiante_si2.gif" border="0" alt="Deshabilitar para el Estudiante" style="cursor: pointer;"
					  			onclick="" />
					  		</c:when>
					  		<c:otherwise>
					  			<img src="<%=request.getContextPath() %>/img/icon_letter_estudiante_no2.gif" border="0" alt="Habilitar para el Estudiante" style="cursor: pointer;"
					  			onclick=""/>
					  		</c:otherwise>
					  	</c:choose>
					</td>
				</tr>
			</table>
		</div>
				
		
	</body>
</html>

