package edu.opencampus.lms.action.usuario;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import com.sun.org.apache.xerces.internal.dom.DocumentImpl;
import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;

import edu.opencampus.lms.action.BaseAction;
import edu.opencampus.lms.excepcion.ActionException;
import edu.opencampus.lms.modelo.Jerarquia;
import edu.opencampus.lms.modelo.Usuario;
import edu.opencampus.lms.modelo.usuario.Persona;
import edu.opencampus.lms.modelo.usuario.Rol;
import edu.opencampus.lms.modelo.usuario.Ubigeo;
import edu.opencampus.lms.modelo.usuario.UsuarioFiltro;
import edu.opencampus.lms.service.JerarquiaService;
import edu.opencampus.lms.service.UsuarioService;
import edu.opencampus.lms.util.Archivo;
import edu.opencampus.lms.util.Constante;
import edu.opencampus.lms.util.Formato;

public class GestorUsuarioAction extends BaseAction {

	private static final long serialVersionUID = -2958713668864756467L;

	private UsuarioService usuarioService;
	
	private JerarquiaService jerarquiaService;

	private Integer id;
	
	private Integer idusuario;
	
	private Integer rol;

	private String username;

	private String nombre1;

	private String nombre2;

	private String paterno;

	private String materno;
	
	private String nacimiento;
	
	private String sexo;
	
	private String email;
	
	private String dni;
	
	private String fijo;
	
	private String movil;
	
	private String ubigeo;
	
	private String direccion;
	
	private String tipo;

	private Integer pagina;
	
	private Integer total;
	
	private String clave;
	
	private Integer paginas;
	
	private Integer estado;
	
	private String estadoforo;
	
	private Usuario usuario;
	
	private Integer[] rols;
	
	private Integer[] permisos;
	
	private UsuarioFiltro filtro;
	
	private List<Usuario> usuarios;
	
	private Collection<Usuario> ultimos;
	
	private Collection<Rol> roles;
	
	private Collection<Jerarquia> jerarquias;
	
	private Collection<Ubigeo> paises;
	
	private Collection<Ubigeo> departamentos;
	
	private Collection<Ubigeo> provincias;
	
	private Collection<Ubigeo> distritos;
	
	public File foto;

	public String contentType;

	public String filename;

	public UsuarioFiltro getFiltro() {
		return filtro;
	}

	public void setFiltro(UsuarioFiltro filtro) {
		this.filtro = filtro;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public Integer getIdusuario() {
		return idusuario;
	}

	public void setIdusuario(Integer idusuario) {
		this.idusuario = idusuario;
	}

	public void setFoto(File foto) {
		this.foto = foto;
	}
	
	public void setFotoContentType(String contentType) {
		this.contentType = contentType;
	}
	
	public void setFotoFileName(String filename) {
		this.filename = filename;
	}
	
	public Collection<Usuario> getUltimos() {
		return ultimos;
	}

	public Integer[] getRols() {
		return rols;
	}

	public void setRols(Integer[] rols) {
		this.rols = rols;
	}

	public Integer[] getPermisos() {
		return permisos;
	}

	public void setPermisos(Integer[] permisos) {
		this.permisos = permisos;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getFijo() {
		return fijo;
	}

	public void setFijo(String fijo) {
		this.fijo = fijo;
	}

	public String getMovil() {
		return movil;
	}

	public void setMovil(String movil) {
		this.movil = movil;
	}

	public String getUbigeo() {
		return ubigeo;
	}

	public void setUbigeo(String ubigeo) {
		this.ubigeo = ubigeo;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Collection<Ubigeo> getPaises() {
		return paises;
	}

	public Collection<Ubigeo> getDepartamentos() {
		return departamentos;
	}

	public Collection<Ubigeo> getProvincias() {
		return provincias;
	}

	public Collection<Ubigeo> getDistritos() {
		return distritos;
	}

	public void setJerarquiaService(JerarquiaService jerarquiaService) {
		this.jerarquiaService = jerarquiaService;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public Collection<Jerarquia> getJerarquias() {
		return jerarquias;
	}

	public String getNacimiento() {
		return nacimiento;
	}

	public void setNacimiento(String nacimiento) {
		this.nacimiento = nacimiento;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public void setPaginas(Integer paginas) {
		this.paginas = paginas;
	}

	public Integer getTotal() {
		return total;
	}

	public Integer getPaginas() {
		return paginas;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public Collection<Rol> getRoles() {
		return roles;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getRol() {
		return rol;
	}

	public void setRol(Integer rol) {
		this.rol = rol;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username.trim().replaceAll("%", "");
	}

	public String getNombre1() {
		return nombre1;
	}

	public void setNombre1(String nombre1) {
		this.nombre1 = nombre1.replaceAll("%", "");
	}

	public String getNombre2() {
		return nombre2;
	}

	public void setNombre2(String nombre2) {
		this.nombre2 = nombre2.replaceAll("%", "");
	}

	public String getPaterno() {
		return paterno;
	}

	public void setPaterno(String paterno) {
		this.paterno = paterno.replaceAll("%", "");
	}

	public String getMaterno() {
		return materno;
	}

	public void setMaterno(String materno) {
		this.materno = materno.replaceAll("%", "");
	}

	public Integer getPagina() {
		return pagina;
	}

	public void setPagina(Integer pagina) {
		this.pagina = pagina;
	}

	public void setUsuarioService(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	public String buscar() throws ActionException{
		log.info("buscar()");
		try{
			
			roles = usuarioService.listarRoles();
			ultimos = usuarioService.listarUltimos();
			
			
			log.info("Determinando filtro...");
			
			if(this.username != null){
				this.filtro = new UsuarioFiltro();
				filtro.setUsuario(username);
				filtro.setRol(rol);
				filtro.setNombre1(nombre1);
				filtro.setNombre2(nombre2);
				filtro.setPaterno(paterno);
				filtro.setMaterno(materno);
							
				getUsuarioSession().getFiltros().setFiltroUsuarios(filtro);
			
			}else if(getUsuarioSession().getFiltros().getFiltroUsuarios() != null){
				
				this.filtro = getUsuarioSession().getFiltros().getFiltroUsuarios();
				
			}else{
				this.filtro = new UsuarioFiltro();
			}
			
			
			log.info("Consultando resultados...");
			
			if(this.filtro != null){
				this.usuarios = usuarioService.buscar(filtro); 
			}
			
			
			log.info("Paginando resultados...");
			
			if(usuarios != null){
				total = usuarios.size();
				paginas = total / Constante.BUSQUEDA_CANTIDAD_DIRECTORIO;
				
				if(pagina==null) pagina=0;
				if(pagina>paginas) pagina=paginas;
				
				int inicio = pagina * Constante.BUSQUEDA_CANTIDAD_DIRECTORIO;
				int fin = inicio + Constante.BUSQUEDA_CANTIDAD_DIRECTORIO;
				this.usuarios = usuarios.subList(inicio, (fin>usuarios.size())?usuarios.size():fin);
			}
			
		} catch (Exception e) {
			log.error(e);
			throw new ActionException(e);
		}
		return SUCCESS;
	}

	public String buscarAutocomplete() throws ActionException{
		log.info("buscarAutocomplete()" + username);
		try{
			if(null != username){
				
				List<Usuario> usuarios = usuarioService.buscarUsuarioPorRol(username, rol); 
				
				
			}
		} catch (Exception e) {
			log.error(e);
			throw new ActionException(e);
		}
		return SUCCESS;
	}

	public String datosUsuario()  throws ActionException{
		log.info("datosUsuario()"+id);
		try {
			if(null != id){
				usuario = usuarioService.obtenerSoloDatos(id);
			}else{
				log.error("Accedo a informacion de usuario no permitido.");
			}
		} catch (Exception e) {
			log.error(e);
			throw new ActionException(e);
		}
		return SUCCESS;
	}
	
	public String restablecerClave() throws ActionException{
		log.info("restablecerClave()");
		try {
			PrintWriter print = getResponse().getWriter();
			if (null != id) {
				Usuario ususario = new Usuario();
				ususario.setId(id);
				ususario.setClave(Usuario.generarClave());
				
				if(usuarioService.restablecerClave(ususario) == Constante.PASSWORD_ACEPTADO){
					print.print(ususario.getClave());
				}else{
					print.print("0");
				}
			}
			print.flush();
			print.close();
		} catch (Exception e) {
			log.error(e);
			//throw new ActionException(e);
		}
		return NONE;
	}
	
	public String nuevo() throws ActionException{
		log.info("nuevo()");
		try {
			roles = usuarioService.listarRoles();
			jerarquias = jerarquiaService.listarJerarquias();
			paises = usuarioService.listarPaises();
		} catch (Exception e) {
			log.error(e);
			throw new ActionException(e);
		}
		return SUCCESS;
	}
	
	public String ubigeo() throws ActionException{
		log.info("ubigeo()");
		try {
			Collection<Ubigeo> ubigeos = null; 
			
			if("pais".equalsIgnoreCase(tipo)){
				ubigeos = usuarioService.listarPaises();
				log.info("Listar Paises");
			}else if("departamento".equalsIgnoreCase(tipo)){
				ubigeos = usuarioService.listarDepartamentos(ubigeo);
				log.info("Listar departamentos "+ubigeo);
			}else if("provincia".equalsIgnoreCase(tipo)){
				ubigeos = usuarioService.listarProvincias(ubigeo);
				log.info("Listar provincias "+ubigeo);
			}else if("distrito".equalsIgnoreCase(tipo)){
				ubigeos = usuarioService.listarDistritos(ubigeo);
				log.info("Listar distritos " +ubigeo);
			}else{
				log.error("Tipo de ubigeo desconocido "+tipo);
				return NONE;
			}
			
			getResponse().setContentType("text/xml");
			getResponse().setHeader("Cache-Control", "no-cache");
			PrintWriter out = getResponse().getWriter();
			
			Document doc= new DocumentImpl();
			Element raiz = doc.createElement("ubigeo");
			
			Element item, codigo, nombre;
			Node codigoTxt, nombreTxt;
			
			for (Ubigeo u : ubigeos) {
				item = doc.createElement("item");
				
				codigo = doc.createElement("codigo");
				codigoTxt = doc.createTextNode(u.getIdPais());
				codigo.appendChild(codigoTxt);
				item.appendChild(codigo);
				
				nombre = doc.createElement("nombre");
				nombreTxt = doc.createTextNode(u.getPais());
				nombre.appendChild(nombreTxt);
				item.appendChild(nombre);
				
				raiz.appendChild(item);
			}
			
			doc.appendChild(raiz);
			
			OutputFormat format = new OutputFormat(doc,"ISO-8859-1",true);   
			new XMLSerializer(out,format).asDOMSerializer().serialize(doc); 
			//new XMLSerializer(System.out,format).asDOMSerializer().serialize(doc); 
			out.close();			
		} catch (Exception e) {
			log.error(e);
			//throw new ActionException(e);
		}
		return NONE;
	}

	public String editar() throws ActionException{
		log.info("editar()");
		try {
			if(id != null){
				roles = usuarioService.listarRoles();
				jerarquias = jerarquiaService.listarJerarquias();
				
				usuario = usuarioService.obtenerUsuario(id);
				paises = usuarioService.listarPaises();
				departamentos = usuarioService.listarDepartamentos(usuario.getPersona().getUbigeo().getIdPais());
				provincias = usuarioService.listarProvincias(usuario.getPersona().getUbigeo().getIdDepartamento());
				distritos = usuarioService.listarDistritos(usuario.getPersona().getUbigeo().getIdProvincia());
			}else {
				throw new ActionException("Hace falta el parametro id");
			}
		} catch (Exception e) {
			log.error(e);
			throw new ActionException(e);
		}
		return SUCCESS;
	}
	
	public String verificarUsuario() throws ActionException{
		log.info("verificarUsuario()" +username);
		try {
			if(username != null){
				Usuario usuario = usuarioService.verificarUsuario(username);
				PrintWriter out = response.getWriter();
				if(usuario == null)
					out.println("OK"); //No esta en uso
				else
					out.println("USED"); //En uso. Si ocurre un error no muestra mensaje
			}
		} catch (Exception e) {
			log.error(e);
			//throw new ActionException(e);
		}
		return NONE;
	}
	
	public String crear()  throws Exception{
		log.info("crear()");
		try{
			GregorianCalendar fecha = null;
					
			if(isValid(nombre1) && isValid(paterno) && isValid(nacimiento)
					&& isValid(sexo) && isValid(email) && isValid(ubigeo) 
					&& (fecha = Formato.stringToCalendar(nacimiento, Constante.FECHA_DIA_MES_ANO)) != null){
				
				Usuario usuario = new Usuario();
				
				log.info("Set Persona");
				Persona persona = new Persona();
				persona.setNomuno(nombre1);
				persona.setNomdos(nombre2);
				persona.setApepaterno(paterno);
				persona.setApematerno(materno);
				persona.setFecnacimiento(fecha);
				persona.setSexo(sexo);
				persona.setEmail(email);
				persona.setDni(dni);
				Ubigeo u = new Ubigeo();
				u.setIdPais(ubigeo);
				persona.setUbigeo(u);
				persona.setDirdomicilio(direccion);
				persona.setTeldomicilio(fijo);
				persona.setTelcelular(movil);
				persona.setUsuarioCreacion(String.valueOf(getUsuarioSession().getId()));
				
				usuario.setPersona(persona);
				
				log.info("Set Roles " + rols);
				List<Rol> roles = new ArrayList<Rol>();
				if(rols != null && rols.length != 0){
					for (int i = 0; i < rols.length; i++) {
						roles.add(new Rol(rols[i]));
					}
				}else{
					roles.add(new Rol(Constante.ROL_CAMPUS_USUARIO));
				}
				usuario.setRoles(roles);
				
				log.info("Set Permisos " + permisos);
				Collection<Jerarquia> jerarquias = new ArrayList<Jerarquia>();
				if(permisos != null){
					for (int i = 0; i < permisos.length; i++) {
						jerarquias.add(new Jerarquia(permisos[i]));
					}
				}
				usuario.setPermisos(jerarquias);
				
				
				Integer id = null;
				if(idusuario != null){
					log.info("Modificar usuario "+ idusuario);
					id = idusuario;
					usuario.setId(idusuario);
					usuario.setUsuario(username);
					usuario.setClave((clave != null && clave.trim().length()>0)?clave:null);
					usuario.setEstado((estado!=null)?Constante.ESTADO_ACTIVO:Constante.ESTADO_INACTIVO);
					//usuario.setEstadoForo(estadoforo);
					
					usuarioService.modificar(usuario);
				}else{
					log.info("Crear usuario");
					usuario.setUsuario(usuario.generarUsuario());
					usuario.setClave(usuario.generarClaveDefault());
					
					log.info("Validar Nombre de usuario repetido");
					usuario.setUsuario(usuarioService.verificarUsuarioSecuencia(usuario.getUsuario()));
					
					id = usuarioService.crear(usuario);
				}
				
				// File
				// **********************************************************

				if (foto != null && foto.exists()) {
					log.info("Registro con imagen: " + filename + " - " + foto.length() + " bytes");
					log.info("Inicia transferencia de archivo... Nombre: "+filename+" ...");
					
					//String extension = filename.substring(filename.lastIndexOf("."));
					String origen = foto.getAbsolutePath();
					if (!"/".equals(Constante.SLASH))origen = origen.replaceAll("/", Constante.SLASH);
					String destino = Constante.RUTA_FOTOS + id + ".jpg";
					try {
						Archivo.copiarArchivo(origen, destino);
						log.info("Se completa la transferencia.");
					} catch (IOException e) {
						log.error("Error en la transferencia.");
					}
					
				} else {
					log.info("Registro sin imagen");
				}
		
			}else{
				nuevo();
				addActionError("Debe indicar todos los campos obligatorios.");
				log.error("Faltan parametros obligatorios.");
				return INPUT;
			}
			
		} catch (Exception e) {
			log.error(e);
			throw new ActionException(e);
		}
		return SUCCESS;
	}

	public String cambiarEstado() throws ActionException{
		log.info("cambiarEstado(): " + estado);
		try {
			if(idusuario != null && estado != null){
				
				usuarioService.cambiarEstado(idusuario, estado);
				
				PrintWriter out = getResponse().getWriter();
				out.print("OK");
				out.close();
			}
		} catch (Exception e) {
			log.error(e);
			//throw new ActionException(e);
		}
		return NONE;
	}
	
	public String eliminar() throws Exception{
		log.info("eliminar() "+ idusuario);
		try {
			if(idusuario != null){
				usuarioService.eliminar(idusuario);
			}else {
				throw new ActionException("Hace falta el parametro id");
			}
		} catch (Exception e) {
			log.error(e);
			throw new ActionException(e);
		}
		return SUCCESS;
	}
	
	public Integer getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	public String getEstadoforo() {
		return estadoforo;
	}

	public void setEstadoforo(String estadoforo) {
		this.estadoforo = estadoforo;
	}
	
}
