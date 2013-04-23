package edu.opencampus.lms.action.seguridad;

import edu.opencampus.lms.action.BaseAction;
import edu.opencampus.lms.modelo.Usuario;
import edu.opencampus.lms.service.UsuarioService;
import edu.opencampus.lms.util.Constante;

public class SeguridadAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private UsuarioService usuarioService;

	private String idUsuario;

	private String clave;
	
	private String compacto;

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	public void setUsuarioService(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	public String ingreso() throws Exception{
		log.info("ingreso()" + " compacto:"+compacto);
		
		setIdUsuario(idUsuario.toLowerCase().trim());
		if(!getRequest().isRequestedSessionIdFromCookie()){
			setMessage("Habilite el uso de cookies.");
			log.info(getMessage());
			return INPUT;
		}
		
		Usuario usuario = new Usuario(idUsuario, clave);
		usuario.setIdSession(getRequest().getSession().getId());
		usuario.setIp(getRequest().getRemoteAddr());
		usuario.setNavegador(getRequest().getHeader("user-agent"));
		if("ok".equals(compacto))usuario.setCompacto(Constante.ESTADO_ACTIVO);
		usuario = usuarioService.validarUsuario(usuario);
		
		switch (usuario.getEstadoSeguridad()) {
			case Constante.SEGURIDAD_USUARIO_NO_ENCONTRADO:
				setMessage("Usuario y/o clave incorrectos.");
				log.info(message);
				return INPUT;
				
			case Constante.SEGURIDAD_USUARIO_PASSWORD_ERRONEO:
				setMessage("Usuario y/o clave incorrectos.");
				log.info(message);
				return INPUT;
				
			case Constante.SEGURIDAD_USUARIO_DESABILITADO:
				setMessage("Usuario deshabilitado.");
				log.info(message);
				return INPUT;
		}
		
//		if("ebenites".equals(usuario.getUsuario())){
//			usuario.getRoles().add(new Rol(Constante.ROL_CAMPUS_ADMINISTRADOR, "THE GOD!"));
//		}
		
		//Commit
		setUsuarioSession(usuario);
		log.info("Ingreso Satisfactorio!: " + usuario.toStringFull());
		
		return SUCCESS;
	}

	public String salida() throws Exception{
		log.info("salida()");
		try{
		if (null != getUsuarioSession()) {
			getSession().remove(Constante.USUARIO_ACTUAL);
			getSession().invalidate();
		}}catch (Exception e) {
			log.error(e);
		}
		return SUCCESS;
	}

	public String ingresarComo() throws Exception{
		log.info("ingresarComo(): "+idUsuario);
		if(idUsuario != null && idUsuario.trim().length()>0){
			clave = usuarioService.violar(idUsuario.toLowerCase().trim());
			if(clave != null){
				// Salir del Campus
				salida();
				// Para entrar como idUsuario
				Usuario usuario = new Usuario(idUsuario, clave);
				usuario.setAdmin(true);
				usuario = usuarioService.validarUsuario(usuario);
				//Commit
				setUsuarioSession(usuario);
				log.info("Ingreso Satisfactorio!: " + usuario.toStringFull());
			}else{
				addActionError("El usuario no existe o se encuentra deshabilitado.");
				log.info("Usuario no encontrado o deshabilitado.");
				return INPUT;
			}
		}
		return SUCCESS;
	}


	public void setCompacto(String compacto) {
		this.compacto = compacto;
	}

}
