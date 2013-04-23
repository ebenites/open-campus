<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@  page import="edu.opencampus.lms.modelo.Usuario"%>
<%@  page import="edu.opencampus.lms.util.Constante"%>
<%Usuario usuario = (Usuario)session.getAttribute(Constante.USUARIO_ACTUAL); %>
<%if(false){ %>
<div id="avisoback" class="avisoback_red" style="display: none;">
	<marquee behavior="scroll" scrolldelay="10" scrollamount="2"><span id="aviso">Aviso de prueba</span></marquee>
</div>
<script>initAviso();</script>
<%} %>
<div id="menu_principal_alumno" style="padding-left: 5px;">
<table width="99%" border="0" cellpadding="0" cellspacing="0" class="tabla_sin_layout">
					<tr   height="20" >
					<td width="20" align="center">
						<a href="<%=request.getContextPath()%>/Portal.action"><img src="<%=request.getContextPath()%>/img/icon_config.gif" 
							alt="<s:text name="portal.menu.inicio"/>" border="0"/></a>
					</td>
					<td width="80">
						<a href="<%=request.getContextPath()%>/Portal.action"><span id="top_campus" class="anatips" title="<s:text name="titulo.campus.virtual"/>"><s:text name="titulo.campus.virtual"/></span></a>
					</td>
					<td width="5" align="center">|</td>
					<td>
					<a href="<%=request.getContextPath()%>/admin/ficha/Buscar.action" onclick="javascript:cerrarAulaVirtualVentanas();">
							Cursos
						</a>
						&gt;							
						<span id="top_usuario" style="color: #487FA0; font-weight: bold">
							<%=usuario.getAulaActual().getCurso().getNombre()%>
						</span>
					</td>
						<td width="27%" align="right">
							<div id="reloj"></div>
					  </td>
						<td width="5" align="center">
							&nbsp;					</td>
						<td width="20" valign="middle">
							<a href="<%=request.getContextPath() %>/Curso.action"><img
									src="<%=request.getContextPath()%>/img/icon_libro_admin.gif"
									alt="Cursos"  border="0" title="Cursos"  />							</a>						
						</td>
						<td width="5" align="center">
							|						
						<td width="20" valign="middle">
							<a href="javascript:void(0)" onClick="abrir_servicio_buzon();"><img
									src="<%=request.getContextPath()%>/img/icon_mail.gif"
									alt="Correo" width="16" height="11" border="0" title="Correo" />							</a>						</td>
						<td width="5" align="center">
							|						</td>
							<td width="20" valign="middle" align="center">	
						<a href="javascript:void(0)" onclick="abrir_servicio_agenda();">						
							<img
													src="<%=request.getContextPath()%>/img/agenda_icon.gif"
													 alt="Agenda" border="0"/>
													</a>				
						</td>						
						<td width="5" height="10" align="center">
							|						</td>
						<td width="20" align="center">	
									<a href="javascript:void(0)" onclick="abrir_servicio_chat()">						
										<img
													src="<%=request.getContextPath()%>/img/icono_chat.gif"
													 alt="Chat" border="0"/>			
													</a>	
						</td>
						<td width="5" height="10" align="center">
							|						</td>			
											
						 <td width="20" height="10"  valign="middle" align="center"><a href="javascript:void(0)" onclick="abrir_servicio_apuntes()">
								<img src="<%=request.getContextPath()%>/img/apuntes_icon.gif" title="Apuntes"  border="0">
						</img></a></td>						
						<td width="5" height="10" align="center">
							|						</td>
						<td width="20">
							<a href="#" onclick="abrirGuiaEstudiante()"><img
									src="<%=request.getContextPath()%>/img/icon_guia.gif"
									alt="Gu&iacute;a del Estudiante"
									title="Gu&iacute;a del Estudiante" width="16" height="16"
									border="0" /> </a>						</td>
						<td width="5" align="center">
							|						</td>
						<td width="30">
							<a href="<%=request.getContextPath()%>/Salir.action"
								class="salir" onclick="javascript:cerrarAulaVirtualVentanas();">Salir</a>						</td>
						<td width="20" align="left">
						<a href="<%=request.getContextPath()%>/Salir.action"
								class="salir" onclick="javascript:cerrarAulaVirtualVentanas();">
							<img src="<%=request.getContextPath()%>/img/icon_salir.gif"
								alt="Salir" title="Salir" width="16" height="16" border="0" /></a>						</td>
					</tr>
				</table>
			</div>
			
			<div id="bienvenida">
					
					<table cellspacing="3" border="0">
						<tr>
							
							<td width="10" class="menu_prin01" align="center">
								&nbsp;
							</td>
							<td width="75" align="center">
								<span class="menu"
									onclick="javascript:abrirConfiguracion('<%=request.getContextPath()%>/admin/aulavirtual/Configurar.action','Configuracion','530','350');">
									Configuraci&oacute;n </span>
							</td>
							<td width="10" class="menu_prin01" align="center">
								|
							</td>
							<td width="50" align="center">
								<span class="menu"
									onClick="javascript:abrirPlanDocente('<%=request.getContextPath()%>/aulavirtual/SilaboDeCurso.action','PlanDocente','530','350');">
									S&iacute;labo </span>
							</td>
							<td width="10" class="menu_prin01" align="center">
								|
							</td>
							<td width="47" align="center">
								<span class="menu"
									onclick="javascript:abrirReporte('<%=request.getContextPath()%>/aulavirtual/Reporte.action','MiClase','560','350');">
									Reporte </span>
							</td>
							<td width="10" class="menu_prin01" align="center">
								|
							</td>
							<td width="87" align="center">
								<span class="menu"
									onclick="javascript:abrirReporte('<%=request.getContextPath()%>/aulavirtual/ReporteNotas.action','Reporte de Notas','640','350');">
									<s:text name="aula.alumno.menu.reporte_notas"/> </span>
							</td>
							<td width="10" class="menu_prin01" align="center">
								|
							</td>
							<td width="52" align="center">
								<span class="menu"
									onclick="javascript:abrirMiClaseAdmin('<%=request.getContextPath()%>/admin/aulavirtual/MiClase.action','MiClase','645','350');">
									Mi Clase </span>
							</td>
							<td width="5" class="menu_prin01" align="center">
								|
							</td>
							<td width="52" align="center">
								<span class="menu"
									onclick="javascript:abrirVitrina('<%=request.getContextPath()%>/aulavirtual/Mensajes.action','Mensajes','560','350');">
									Avisos</span>
							</td>
							<td width="10" class="menu_prin01" align="center">
								|
							</td>
							<td width="45" align="center">
								<span class="menu"
									onclick="javascript:abrirLectura('<%=request.getContextPath()%>/aulavirtual/Recursos.action','Recursos','560','350');">
									Lectura </span>
							</td>
							<td width="10" class="menu_prin01" align="center">
								|
							</td>
							<td width="45" align="center">
								<span class="menu"
									onclick="javascript:abrirInforme('<%=request.getContextPath()%>/aulavirtual/Informe.action');">
									Informe </span>
							</td>
						</tr>
					</table>
			</div>