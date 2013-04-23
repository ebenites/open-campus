package edu.opencampus.lms.modelo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;

import javax.servlet.http.HttpSessionActivationListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;
import javax.servlet.http.HttpSessionEvent;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.opencampus.lms.dao.IngresoDAO;
import edu.opencampus.lms.modelo.usuario.FiltroList;
import edu.opencampus.lms.modelo.usuario.Ingreso;
import edu.opencampus.lms.modelo.usuario.Persona;
import edu.opencampus.lms.modelo.usuario.Rol;
import edu.opencampus.lms.util.Archivo;
import edu.opencampus.lms.util.Constante;
import edu.opencampus.lms.util.Formato;

public class Usuario extends BaseModelo implements Comparable<Usuario>, HttpSessionBindingListener,  HttpSessionActivationListener {

	private static final long serialVersionUID = -8136809346261586013L;

	Log log = LogFactory.getLog(getClass());
	
	private int id;
	
	private String idToString;

	private String clave;

	private String usuario;
	
	private String tipo = Constante.TIPO_USUARIO_PERSONA;
	
	private String idioma;
	
	private int compacto;

	private String idSession;
	
	private String ip;

	private String navegador;
	
	private int estadoSeguridad = Constante.SEGURIDAD_USUARIO_ERROR_DESCONOCIDO;

	private int estado;
	
	private Persona persona;
	
	private List<Rol> roles = new ArrayList<Rol>();

	private Ingreso ingreso = null;
	
	private Collection<Jerarquia> permisos = new ArrayList<Jerarquia>();
	
	private Collection<Periodo> periodos = new ArrayList<Periodo>();
	
	private Collection<Jerarquia> jerarquias = new ArrayList<Jerarquia>();
	
	private Set<Integer> misNoticias = null;
	
	private Set<Integer> misForos = null;
	
	private Rol rolPredeterminado = null;
	
	private AulaVirtual aulaActual = null;
	
	private FiltroList filtros = new FiltroList();
	
	private static Collection<Rol> rolesPrivados = null;
	
	private boolean isAdmin = false;

	public AulaVirtual getAulaActual() {
		return aulaActual;
	}

	public void setAulaActual(AulaVirtual aulaActual) {
		this.aulaActual = aulaActual;
	}

	public Rol getRolPredeterminado() {
		if(rolPredeterminado == null){
			rolPredeterminado = new Rol(Constante.ROL_CAMPUS_ANONIMO, "Sin rol");
			
			rolPredeterminado = getRol(Constante.ROL_CAMPUS_ADMINISTRADOR);
			if(rolPredeterminado != null) return rolPredeterminado;
			
			rolPredeterminado = getRol(Constante.ROL_CAMPUS_SOPORTE);
			if(rolPredeterminado != null) return rolPredeterminado;
			
			rolPredeterminado = getRol(Constante.ROL_CAMPUS_JEFE);
			if(rolPredeterminado != null) return rolPredeterminado;
			
			rolPredeterminado = getRol(Constante.ROL_CAMPUS_MONITOR);
			if(rolPredeterminado != null) return rolPredeterminado;
			
			rolPredeterminado = getRol(Constante.ROL_CAMPUS_EMPRESA);
			if(rolPredeterminado != null) return rolPredeterminado;
			
			rolPredeterminado = getRol(Constante.ROL_CAMPUS_DOCENTE);
			if(rolPredeterminado != null) return rolPredeterminado;
			
			rolPredeterminado = getRol(Constante.ROL_CAMPUS_USUARIO);
			if(rolPredeterminado != null) return rolPredeterminado;
			
		}
		return rolPredeterminado;
	}

	public FiltroList getFiltros() {
		return filtros;
	}

	public void setFiltros(FiltroList filtros) {
		this.filtros = filtros;
	}

	public void setRolPredeterminado(Rol rolPredeterminado) {
		this.rolPredeterminado = rolPredeterminado;
	}

	public boolean hasRol(int idrol){
		return roles.contains(new Rol(idrol));
	}
	
	public Rol getRol(int idrol){
		if(roles!=null){
			int index = roles.indexOf(new Rol(idrol));
			if(index != -1)
				return roles.get(index);
		}
		return null;
	}

	public String getNombreCompleto(){
		if(this.persona!=null){
			if(Constante.TIPO_USUARIO_PERSONA.equals(this.tipo))
				return this.persona.getNombreCompleto();
			else if(Constante.TIPO_USUARIO_EMPRESA.equals(this.tipo))
				return "this.empresa.getNombreCompleto()";
		}
		return null;
	}
	
	public String getNombreCorto(){
		if(Constante.TIPO_USUARIO_PERSONA.equals(this.tipo))
			return this.persona.getNombreCorto();
		else if(Constante.TIPO_USUARIO_EMPRESA.equals(this.tipo))
			return "this.empresa.getNombreCorto()";
		else
			return null;
	}

	public boolean contieneJerarquiaPadre(int idPadre){
		for (Jerarquia j : jerarquias) {
			if(j.isMyParent(idPadre)){
				return true;
			}
		}
		return false;
	}
	
	public boolean contieneJerarquiaHijo(int idHijo){
		for (Jerarquia j : jerarquias) {
			if(j.isMyChild(idHijo)){
				return true;
			}
		}
		return false;
	}
	
	public boolean contienePermisoPadre(int idPadre){
		for (Jerarquia j : permisos) {
			if(j.isMyParent(idPadre)){
				return true;
			}
		}
		return false;
	}
	
	public boolean contienePermisoHijo(int idHijo){
		for (Jerarquia j : permisos) {
			if(j.isMyChild(idHijo)){
				return true;
			}
		}
		return false;
	}
	
	public String getIdToString() {
		setIdToString(Formato.completarCeros(id, 10));
		return idToString;
	}

	private void setIdToString(String idToString) {
		this.idToString = idToString;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public Set<Integer> getMisNoticias() {
		return misNoticias;
	}

	public void setMisNoticias(Set<Integer> misNoticias) {
		this.misNoticias = misNoticias;
	}

	public Set<Integer> getMisForos() {
		return misForos;
	}

	public void setMisForos(Set<Integer> misForos) {
		this.misForos = misForos;
	}

	public Collection<Periodo> getPeriodos() {
		return periodos;
	}

	public void setPeriodos(Collection<Periodo> periodos) {
		this.periodos = periodos;
	}

	public Collection<Jerarquia> getJerarquias() {
		return jerarquias;
	}

	public void setJerarquias(Collection<Jerarquia> jerarquias) {
		this.jerarquias = jerarquias;
	}

	public Collection<Jerarquia> getPermisos() {
		return permisos;
	}

	public void setPermisos(Collection<Jerarquia> permisos) {
		this.permisos = permisos;
	}

	public List<Rol> getRoles() {
		return roles;
	}

	public void setRoles(List<Rol> roles) {
		this.roles = roles;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getNavegadorAsString() {
		if (navegador != null) {
			navegador = navegador.toLowerCase();
			if (navegador.indexOf("opera") != -1) {
				return "Opera";
			} else if (navegador.indexOf("webkit") != -1) {
				return "Safari";
			} else if (navegador.indexOf("msie 6.0") != -1
					|| navegador.indexOf("msie 7.0") != -1) {
				return "Internet Explorer";
			} else if (navegador.indexOf("gecko") != -1) {
				if (navegador.indexOf("firefox") != -1) {
					return "Firefox";
				}
				if (navegador.indexOf("netscape") != -1) {
					return "Netscape";
				}
				return "Gecko";
			}
		}
		return "Desconocido";
	}
	
	public String getNavegador() {
		return navegador;
	}

	public void setNavegador(String navegador) {
		this.navegador = navegador;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public int getCompacto() {
		return compacto;
	}

	public void setCompacto(int compacto) {
		this.compacto = compacto;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getIdioma() {
		return idioma;
	}

	public void setIdioma(String idioma) {
		if(idioma == null)idioma = Constante.IDIOMA_ESPANOL;
		this.idioma = idioma;
	}

	public String getIdSession() {
		return idSession;
	}

	public void setIdSession(String idSession) {
		this.idSession = idSession;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}
	
	public int getEstadoSeguridad() {
		return estadoSeguridad;
	}

	public void setEstadoSeguridad(int estadoSeguridad) {
		this.estadoSeguridad = estadoSeguridad;
	}
	
	public Ingreso getIngreso() {
		return ingreso;
	}

	public void setIngreso(Ingreso ingreso) {
		this.ingreso = ingreso;
	}
	
	public Usuario(String usuario, String clave) {
		super();
		this.usuario = usuario;
		this.clave = clave;
	}

	public Usuario() {
		super();
	}

	public Usuario(int id) {
		super();
		this.id=id;
	}
	
	public String generarUsuario(){
		if(this.getPersona() != null && this.getPersona().getNomuno() != null  && this.getPersona().getNomuno().length() != 0 
				&& this.getPersona().getApepaterno() != null && this.getPersona().getApepaterno().length() != 0){
			return Formato.normalizarFrace((this.getPersona().getNomuno().substring(0, 1) + this.getPersona().getApepaterno()).replaceAll(" ", ""));
		}
		return null;
	}
	
	public String generarClaveDefault(){
		if(this.getPersona() != null && this.getPersona().getFecnacimiento() != null){
			String dia = Formato.completarCeros(this.getPersona().getFecnacimiento().get(GregorianCalendar.DATE),2);
			String mes = Formato.completarCeros(this.getPersona().getFecnacimiento().get(GregorianCalendar.MONTH)+1,2);
			String ano = String.valueOf(this.getPersona().getFecnacimiento().get(GregorianCalendar.YEAR)).substring(2);
			return dia + mes + ano;
		}
		return null;
	}
	
	public static String generarClave() {
		Random r = new Random(System.currentTimeMillis());
		StringBuffer password = new StringBuffer(Constante.PASSWORD_LONGITUD_MINIMA);
		for (int i = 0; i < Constante.PASSWORD_LONGITUD_MINIMA; i++) {
			password.append(r.nextInt(10));
		}
		return password.toString();
	}
	
	public static Collection<Rol> getRolesPrivados(){
		if(rolesPrivados == null){
			rolesPrivados = new ArrayList<Rol>();
			rolesPrivados.add(new Rol(1));
			rolesPrivados.add(new Rol(2));
			rolesPrivados.add(new Rol(4));
			rolesPrivados.add(new Rol(5));
		}
		return rolesPrivados;
	}
	
	public String toStringFull() {
		StringBuffer cadena = new StringBuffer();
		cadena.append("\n\tid: " + id);
		cadena.append("\n\tclave: " + clave);
		cadena.append("\n\tusuario: " + usuario);
		cadena.append("\n\ttipo: " + tipo);
		cadena.append("\n\tidioma: " + idioma);
		cadena.append("\n\tidSession: " + idSession);
		cadena.append("\n\tip: " + ip);
		cadena.append("\n\tnavegador: " + navegador);
		cadena.append("\n\tpersona: " + persona);
		cadena.append("\n\testadoSeguridad: " + estadoSeguridad);
		cadena.append("\n\trolPredeterminado: " + getRolPredeterminado());
		
		for (Rol r : roles) {
			cadena.append("\n\t\tRol: " + r);
		}
		
		for (Jerarquia j : permisos) {
			cadena.append("\n\t\tPermiso: " + j);
		}
		
		for (Periodo j : periodos) {
			cadena.append("\n\t\tPeriodo: " + j);
		}
		
		for (Jerarquia j : jerarquias) {
			cadena.append("\n\t\tJerarquia: " + j);
		}
		
		cadena.append("\n\tAulaVirtual: "+this.aulaActual);
		cadena.append("\n\tIngreso: " + ((ingreso != null)?ingreso.getFechaIngreso():""));
		
		return cadena.toString();
	}
	
	@Override
	public String toString() {
		String cadena = "ID:"+this.id+" Usuario:"+this.usuario+" Nombre:"+this.getNombreCompleto()+ " Rol:"+getRolPredeterminado();
		return cadena;
	}
	
	@Override
	public boolean equals(Object o) {
		if(o != null && o instanceof Usuario){
			Usuario u = (Usuario) o;
			if (this.id == u.getId()) {
				return true;
			}
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	public void valueBound(HttpSessionBindingEvent e) {
		log.info("valueBound() Ingreso de Usuario: "+this.toString());
		
		//Idioma **********
		e.getSession().setAttribute(Constante.IDIOMA_ATRIBUTO, new Locale((idioma != null)?idioma:Constante.IDIOMA_ESPANOL));
		
		if(!this.isAdmin){
			//Registro a la lista de conectados **********
	        Map<String,Usuario> col = (Map<String,Usuario>)e.getSession().getServletContext().getAttribute(Constante.USUARIOS_ACTUAL);
	        if(col == null) col = new HashMap<String,Usuario>();
	        col.put(this.getNombreCompleto()+" ["+this.idSession+"]",this);
	        
	        e.getSession().getServletContext().setAttribute(Constante.USUARIOS_ACTUAL, new TreeMap<String,Usuario>(col));
	        
	        //Registro de ingreso **********
	        log.info("Inscribir Ingreso IdUsuario:"+this.id);
	        
	        this.ingreso = new Ingreso(Constante.ELEMENTO_LOGEO, this);
			
			this.ingreso = IngresoDAO.getInstance().inscribirIngreso(ingreso);
				
			log.info("Ingreso Inscrito, ID:"+ingreso.getIdIngreso());
		}
	}

	@SuppressWarnings("unchecked")
	public void valueUnbound(HttpSessionBindingEvent e) {
		log.info("valueUnbound() Salida de Usuario: "+this.toString());
		
		if(!this.isAdmin){
			//Registro a la lista de conectados **********
			Map<String,Usuario> col = (Map<String,Usuario>)e.getSession().getServletContext().getAttribute(Constante.USUARIOS_ACTUAL);
			if(col != null){
		        col.remove(this.getNombreCompleto()+" ["+this.idSession+"]");
		        e.getSession().getServletContext().setAttribute(Constante.USUARIOS_ACTUAL, col);
			}
	        
	        //Registro de ingreso **********
	        log.info("Inscribir Salida IdUsuario:"+this.id);
	        
			IngresoDAO.getInstance().inscribirSalida(ingreso);
			
			log.info("Salida Inscrita, ID:" + this.ingreso.getIdIngreso());
		}
		
        //Eliminar archivos temporales **********
//      String ruta = Constante.REPOSITORIO_TEMPORAL + Constante.SLASH + this.idSession;
//		Archivo.eliminarDirectorio(ruta);
        
	}
	
	public void sessionWillPassivate(HttpSessionEvent e) {
		log.info("HttpSessionActivationListener: La sesion esta siendo serializada.");
	}

	@SuppressWarnings("unchecked")
	public void sessionDidActivate(HttpSessionEvent e) {
		log.info("HttpSessionActivationListener: La sesion esta siendo deserializada.");
		if(!this.isAdmin){
			//Registrando usuarios serializados
			Map<String,Usuario> col = (Map<String,Usuario>)e.getSession().getServletContext().getAttribute(Constante.USUARIOS_ACTUAL);
		    if(col == null) col = new HashMap<String,Usuario>();
		    col.put(this.getNombreCompleto()+" ["+this.idSession+"]",this);
		    log.info("HttpSessionActivationListener: Sesion de usuario restaurada: "+this.toString());
		    e.getSession().getServletContext().setAttribute(Constante.USUARIOS_ACTUAL, new TreeMap<String,Usuario>(col));
		}
	}

	@Override
	public int compareTo(Usuario otroUsuario) {
		 if(this.persona == null || otroUsuario.getPersona() == null){
			 return usuario.compareTo(otroUsuario.getUsuario());
		 }else{
			 return this.persona.getNombreCompleto().compareTo(otroUsuario.getPersona().getNombreCompleto());
		 }
	}
	
}
