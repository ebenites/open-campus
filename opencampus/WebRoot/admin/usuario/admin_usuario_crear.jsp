<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page errorPage="../../error_action.jsp" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="ct" uri="/WEB-INF/CampusTags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type"
			content="text/html; charset=ISO-8859-1">
<title>
<s:text name="titulo.campus.virtual" />
</title>
<s:include value="/comun/jslibs.jsp"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/jscalendar/calendar-style.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jscalendar/calendar.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jscalendar/calendar-es.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jscalendar/calendar-setup.js"></script>

<script language="javascript" type="text/javascript" src="<%=request.getContextPath()%>/admin/usuario/admin_usuario_crear.js"></script>
    
</head>
<body>
<div id="contenedor">
  <s:include value="/comun/bienvenida.jsp"></s:include>
  <div id="cuerpo">
    <div id="pop_cuerpo">
    <s:include value="/error_message.jsp"/>
	<form id="form_usuario_crear" action="<%=request.getContextPath()%>/admin/usuario/Crear.action" method="post" enctype="multipart/form-data">
	<table border="0" cellpadding="0" cellspacing="0" width="100%" style="table-layout: fixed;">
	<tr>
		<td valign="top">
		
			
			<!-- Modificar datos de usuario -->
			<c:if test="${usuario != null}">
				<table width="100%" border="0" cellpadding="3" cellspacing="0" class="open_table">
					<caption>Datos de Usuario</caption>
					<tr>
						<td rowspan="3" width="88"><img src="<%=request.getContextPath() %>/VerFoto.action?id=<c:out value="${usuario.id}"/>" 
					width="85" border="0" class="open_border"> 
						</td>
						<td width="90" class="label"><label>Usuario</label></td>
						<td><input type="text" name="username" maxlength="32" class="required validate-alphanum" size="28" onkeyup="verificaUsuarioOnKeyUp(this,'<c:out value="${usuario.usuario}"/>')"
							value="<c:out value="${usuario.usuario}"/>"/><input type="hidden" id="user_ok" value="1">							
							<input type="hidden" name="idusuario" value="<c:out value="${usuario.id}"/>"></td>
					
						<td width="90" class="label"><strong>Clave</strong></td>
						<td><input type=text name="clave" maxlength="32" size="28" class="validate-strongpassword" value=""/></td>
					</tr>
					<tr>
						<td class="label"><strong>Opciones</strong></td>
						<td colspan="3" class="border-top">
							<label><input type="checkbox" name="estado" value="1" <c:if test="${usuario.estado==1}">checked</c:if>>Estado de cuenta </label>
							<label><input type="checkbox" name="estadoforo" value="1" >Estado de foro (*) </label>
						</td>
					</tr>
					<tr>
						<td class="label"><strong>&nbsp;</strong></td>
						<td colspan="3" class="border-top">
							&nbsp;
						</td>
					</tr>
				</table>
				
			</c:if>
		
		
	      <table width="100%" border="0" cellpadding="3" cellspacing="0" class="open_table">
	        <caption>Datos Personales</caption>
	        <tr>
	          <td width="110" class="label"><strong>Primer Nombre</strong></td>
	          <td><input type="text" name="nombre1" maxlength="100" class="required validate-alpha" size="32" value="<c:out value="${usuario.persona.nomuno}"/>"/></td>
	          <td width="110" class="label"><strong>Segundo Nombre</strong></td>
	          <td><input type="text"name="nombre2" maxlength="100" class="validate-alpha" size="32" value="<c:out value="${usuario.persona.nomdos}"/>"/></td>
	        </tr>
	        <tr>
	          <td class="label"><strong>Apellido Paterno</strong></td>
	          <td><input type="text" name="paterno" maxlength="100" class="required validate-alpha" size="32" value="<c:out value="${usuario.persona.apepaterno}"/>"/></td>
	          <td class="label"><strong>Apellido Materno</strong></td>
	          <td><input type="text" name="materno" maxlength="100" class="validate-alpha" size="32" value="<c:out value="${usuario.persona.apematerno}"/>"/></td>
	        </tr>
	        <tr>
	          <td class="label"><strong>Fecha Nacimiento</strong></td>
	          <td><input type="text" id="nacimiento" name="nacimiento" class="required" size="32" readonly="readonly" value="<c:out value="${usuario.persona.fecnacimientoAsString}"/>"/></td>
	          <td class="label"><strong>Sexo</strong></td>
	          <td><div class="allrequired">
	          	<input type="radio" name="sexo" value="M" <c:if test="${usuario.persona.sexo=='M'}">checked</c:if>/>Masculino &nbsp; &nbsp;  
	          	<input type="radio" name="sexo" value="F" <c:if test="${usuario.persona.sexo=='F'}">checked</c:if>/>Femenino </div>
	          </td>
	        </tr>
	        <tr>
	          <td class="label"><strong>Email</strong></td>
	          <td><input type="text" name="email" maxlength="100" class="required validate-email " size="32" value="<c:out value="${usuario.persona.email}"/>"/></td>
	          <td class="label"><strong>DNI</strong></td>
	          <td><input type="text" name="dni" maxlength="20" class="validate-integer " size="32" value="<c:out value="${usuario.persona.dni}"/>"/></td>
	        </tr>
	        <tr>
	          <td class="label"><strong>Pa&iacute;s</strong></td>
	          <td>
	          	<select id="pais" onchange="cargarCombo(this,'departamento',new Array ('provincia','distrito'));" class="required" style="width:190px;">
		          	<option>Elija un valor</option>
		          	<c:forEach items="${paises}" var="pais">
		          		<option value="<c:out value="${pais.idPais}"/>" 
		          			<c:if test="${(pais.idPais == 'PE' && usuario.persona.ubigeo.idPais==null) || usuario.persona.ubigeo.idPais==pais.idPais}"> selected </c:if> 
		          			><c:out value="${pais.pais}"/></option>
		          	</c:forEach>
	          	</select>
	          	<c:if test="${usuario.persona.ubigeo.idPais == null}">
					<script type="text/javascript">
						Event.observe(window, 'load', function() { 
							cargarCombo($('pais'),'departamento',new Array ('provincia','distrito'));
						});
					</script>
				</c:if>
	          </td>
	          <td class="label"><strong>Departamento</strong></td>
	          <td>
				<select id="departamento" onchange="cargarCombo(this,'provincia',new Array ('distrito'));" class="required"  style="width:190px;">
					<c:if test="${departamentos != null}">
						<option>Elija un valor</option>
			          	<c:forEach items="${departamentos}" var="departamento">
			          		<option value="<c:out value="${departamento.idPais}"/>" 
			          			<c:if test="${usuario.persona.ubigeo.idDepartamento==departamento.idPais}"> selected </c:if> 
			          			><c:out value="${departamento.pais}"/></option>
			          	</c:forEach>
					</c:if>
				</select>
			  </td>
	        </tr>
	        <tr>
	          <td class="label"><strong>Provincia</strong></td>
	          <td>
				<select id="provincia" onchange="cargarCombo(this,'distrito');" class="required"  style="width:190px;">
					<c:if test="${provincias != null}">
						<option>Elija un valor</option>
			          	<c:forEach items="${provincias}" var="provincia">
			          		<option value="<c:out value="${provincia.idPais}"/>" 
			          			<c:if test="${usuario.persona.ubigeo.idProvincia==provincia.idPais}"> selected </c:if> 
			          			><c:out value="${provincia.pais}"/></option>
			          	</c:forEach>
					</c:if>
				</select>
			  </td>
	          <td class="label"><strong>Distrito</strong></td>
	          <td>
	          	<select id="distrito" name="ubigeo" class="required"  style="width:190px;">
	          		<c:if test="${distritos != null}">
						<option>Elija un valor</option>
			          	<c:forEach items="${distritos}" var="distrito">
			          		<option value="<c:out value="${distrito.idPais}"/>" 
			          			<c:if test="${usuario.persona.ubigeo.idDistrito==distrito.idPais}"> selected </c:if> 
			          			><c:out value="${distrito.pais}"/></option>
			          	</c:forEach>
					</c:if>
				</select>
	          </td>
	        </tr>
	        <tr>
	          <td class="label"><strong>Direcci&oacute;n</strong></td>
	          <td><input type="text" name="direccion" maxlength="100" size="32" value="<c:out value="${usuario.persona.dirdomicilio}"/>"/></td>
	          <td class="label"><strong>Tel&eacute;fono Fijo</strong></td>
	          <td><input type="text" name="fijo" maxlength="40" class="validate-integer " size="32" value="<c:out value="${usuario.persona.teldomicilio}"/>"/></td>
	        </tr>
	        <tr>
	          <td class="label"><strong>Tel&eacute;fono M&oacute;vil</strong></td>
	          <td><input type="text" name="movil" maxlength="40" class="validate-integer " size="32" value="<c:out value="${usuario.persona.telcelular}"/>"/></td>
	          <td class="label"><strong>Foto <i>(Solo JPG)</i></strong></td>
	          <td><div id="bloque_foto"><div id="photo" class="file" style="float: left;"><input type="file" id="foto" name="foto" onchange="setPhoto(this)"/></div>
	          	<div id="plabel" style="float: left;">&nbsp;</div>
	          	<div id="ptrash" style="float: rigth;"><img src="<%=request.getContextPath()%>/img/icon_trash.gif" border="0" 
	          	width="13" height="15" style="cursor: pointer;" onclick="delPhoto()"/></div></div>
	          </td>
	        </tr>
	      </table>
	      
	    </td>
	    <td width="10">&nbsp;</td>
	    <td width="300" valign="top">  
	      <table width="100%" border="0" cellpadding="3" cellspacing="0" class="open_table" style="table-layout: fixed;">
	        <caption>Datos de Usuario</caption>
	        <thead>
		        <tr>
		          <td style="text-align: left;"><strong>Roles</strong></td>
		        </tr>
	        </thead>
	        <tbody>
		        <tr>
		          <td>
					<select id="rols_source" style="width:220px;">
			          	<c:forEach items="${roles}" var="rol">
			          		<option value="<c:out value="${rol.idrol}"/>"><c:out value="${rol.nombre}"/></option>
			          	</c:forEach>
		          	</select>
		          	<input type="button" value="Agregar" style="width: 66px" onclick="agregarRegla('rols')">
				  </td>
		        </tr>
		        <tr>
		          <td>
					<select id="rols" name="rols" style="width:274px; height: 62px;" multiple="multiple" ondblclick="quitarRegla('rols')">
						<c:forEach items="${usuario.roles}" var="rol">
			          		<option value="<c:out value="${rol.idrol}"/>"><c:out value="${rol.nombre}"/></option>
			          	</c:forEach>
					</select>
					<img src="<%=request.getContextPath()%>/img/icon_trash.gif" border="0" 
		          	width="13" height="15" style="cursor: pointer;" onclick="quitarRegla('rols')"/>
				  </td>
		        </tr>
	        </tbody>
	        </table>
	        <table width="100%" border="0" cellpadding="3" cellspacing="0" class="open_table">
	        <thead>
		        <tr>
		          <td style="text-align: left;"><strong>Permisos</strong></td>
		        </tr>
	        </thead>
	        <tbody>
	        <tr>
	          <td>
	          	<select id="permisos_source" style="width:220px;">
		          	<c:forEach items="${jerarquias}" var="j">
		          		<option value="<c:out value="${j.idJerarquia}"/>"><c:out value="${j.rutaCompleta}"/></option>
		          	</c:forEach>
	          	</select>
	          	<input type="button" value="Agregar" style="width: 66px" onclick="agregarRegla('permisos')">
	          </td>
	        </tr>
	        <tr>
	          <td>
				<select id="permisos" name="permisos" style="width:274px; height: 62px;" multiple="multiple" ondblclick="quitarRegla('permisos')">
					<c:forEach items="${usuario.permisos}" var="permiso">
		          		<option value="<c:out value="${permiso.idJerarquia}"/>"><c:out value="${permiso.rutaCompleta}"/></option>
		          	</c:forEach>
				</select>
				<img src="<%=request.getContextPath()%>/img/icon_trash.gif" border="0" 
	          	width="13" height="15" style="cursor: pointer;" onclick="quitarRegla('permisos')"/>
			  </td>
	        </tr>
	        </tbody>
	      </table>
	    </td>
    </tr>
    </table>
    
     <table width="100%" border="0" cellpadding="3" cellspacing="0" class="open_table">
     	<tfoot>
		<tr> 
			<td align="center" >
				<input id="btn_usuario_crear" type="submit" value="Guardar">	
				<input type="button" value="Cancelar" 
					onclick="document.location='<%=request.getContextPath()%>/admin/usuario/Buscar.action'">								
			</td>
		</tr>
		</tfoot>
	 </table>
    </form>
        
	</div>
  </div>
  <div id="pie">
    <%@include file="../../comun/pie.jsp" %>
  </div>
</div>
</body>
</html>
