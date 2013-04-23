package edu.opencampus.lms.action.aulavirtual;

import java.io.FileNotFoundException;
import java.util.Collection;

import edu.opencampus.lms.action.BaseAction;
import edu.opencampus.lms.excepcion.ActionException;
import edu.opencampus.lms.modelo.AulaVirtual;
import edu.opencampus.lms.modelo.Silabo;
import edu.opencampus.lms.modelo.aulavirtual.MatriculaRol;
import edu.opencampus.lms.modelo.ficha.Publicacion;
import edu.opencampus.lms.modelo.usuario.Ingreso;
import edu.opencampus.lms.service.AulaVirtualService;
import edu.opencampus.lms.service.IngresoService;
import edu.opencampus.lms.service.MatriculaService;
import edu.opencampus.lms.service.PublicacionService;
import edu.opencampus.lms.util.Archivo;
import edu.opencampus.lms.util.Constante;
import edu.opencampus.lms.util.Formato;

public class AulaVirtualAction extends BaseAction {

	private static final long serialVersionUID = 6386588540057384239L;

	private AulaVirtualService aulaVirtualService;

	private MatriculaService matriculaService;

	private PublicacionService publicacionService;
	
	private IngresoService ingresoService;
	
	private Collection<Publicacion> publicaciones;

	private Collection<MatriculaRol> grupos;
	
	private Integer idunidad;

	private Integer id;
	
	private Integer ruta;
	
	private Silabo silabo;
	
	private AulaVirtual aula;

	public Integer getRuta() {
		return ruta;
	}

	public Collection<MatriculaRol> getGrupos() {
		return grupos;
	}

	public void setRuta(Integer ruta) {
		this.ruta = ruta;
	}

	public AulaVirtual getAula() {
		return aula;
	}

	public void setAula(AulaVirtual aula) {
		this.aula = aula;
	}

	public Integer getIdunidad() {
		return idunidad;
	}

	public void setIdunidad(Integer idunidad) {
		this.idunidad = idunidad;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Silabo getSilabo() {
		return silabo;
	}

	public Collection<Publicacion> getPublicaciones() {
		return publicaciones;
	}

	public void setIngresoService(IngresoService ingresoService) {
		this.ingresoService = ingresoService;
	}

	public void setPublicacionService(PublicacionService publicacionService) {
		this.publicacionService = publicacionService;
	}

	public void setAulaVirtualService(AulaVirtualService aulaVirtualService) {
		this.aulaVirtualService = aulaVirtualService;
	}

	public AulaVirtualService getAulaVirtualService() {
		return aulaVirtualService;
	}

	public MatriculaService getMatriculaService() {
		return matriculaService;
	}

	public void setMatriculaService(MatriculaService matriculaService) {
		this.matriculaService = matriculaService;
	}

	// ENCUESTA ***********************************
//	 private boolean hayEncuesta;
//	
//	 public boolean getHayEncuesta() {
//	 return hayEncuesta;
//	 }
		
//	 private EncuestaService encuestaService;
//	
//	 public void setEncuestaService(EncuestaService encuestaService) {
//	 this.encuestaService = encuestaService;
//	 }
//		
	// FIN ENCUESTA ***********************************

	
	public String silaboDeCurso() throws ActionException{
		log.info("silaboDeCurso");
		try {
			silabo = aulaVirtualService.obtenerElSilabo(getUsuarioSession().getAulaActual().getSilabo().getIdSilabo());
		} catch (Exception e) {
			log.error(e);
			throw new ActionException(e);
		}
		return SUCCESS;
	}
	
	
	
	public String ingresar() throws ActionException{
		log.info("ingresar() id:" + id + " usuario:" + getUsuarioSession());
		try {
			if (null != id) {
				aula = aulaVirtualService.obtenerAulaVirtual(id, getUsuarioSession());
				aula.setIngreso(ingresoService.inscribirIngreso(new Ingreso(Constante.ELEMENTO_CURSO,getUsuarioSession(),id+"")));
								
				//Publicaciones
				this.publicaciones = publicacionService.listarPublicacionesNoLeidas(aula.getMatriculaActual().getIdMatricula());
				getRequest().getSession().setAttribute("n", "" + publicaciones.size());

				if(aula.getMatriculaActual().isDocenteResponsable()) {
					return "DOCENTE";
				}else{
					return "ESTUDIANTE";
				}

				// ENCUESTA *************************************
//				try {
//					 hayEncuesta =
//					 encuestaService.verEncuesta(aula.getIdMatricula());
//				} catch (ServiceException e) {
//					 log.error(e);
//				}
				// FIN ENCUESTA *********************************

				
			} else {
				throw new ActionException("Hace falta el parámetro que identifica al aula (id).");
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ActionException(e.getMessage());
			//setMessage(e.getMessage());
		}
		//return CURSO;
	}

	public String salir() {
		log.info("salir() ");
		getUsuarioSession().setAulaActual(null);
		return NONE;
	}
	
	public String verRecurso() {
		log.info("verRecurso()" + id);
		try {
			if(id != null && getUsuarioSession().getAulaActual().getSilabo().esMiUnidad(id)){
				String origen, unidadesPath = Constante.RUTA_UNIDADES + Formato.completarCeros(id, 8) + Constante.SLASH;
				
				switch (ruta) {
					case 1: //Texto
						origen = unidadesPath + Constante.PATH_TEXTO + Constante.TEXTO + Constante.FILETYPE_PDF;
						Archivo.downloadImage(Constante.TEXTO+Constante.FILETYPE_PDF, origen, getResponse());
						break;
					case 2: //Lab
						origen = unidadesPath + Constante.PATH_LAB + Constante.LABORATORIO + Constante.FILETYPE_PDF;
						Archivo.downloadImage(Constante.LABORATORIO+Constante.FILETYPE_PDF, origen, getResponse());	
						break;
					case 3: //Repaso
						origen = unidadesPath + Constante.PATH_REPASO + Constante.REPASO + Constante.FILETYPE_SWF;
						if(new java.io.File(origen).exists())
							Archivo.downloadImage(Constante.REPASO+Constante.FILETYPE_SWF, origen, getResponse());
						else{
							origen = unidadesPath + Constante.PATH_REPASO + Constante.REPASO + Constante.FILETYPE_PDF;//Constante.FILETYPE_SWF;
							Archivo.downloadImage(Constante.REPASO+Constante.FILETYPE_PDF/*Constante.FILETYPE_SWF*/, origen, getResponse());
						}
						break;
					case 4: //Descarga
						origen = unidadesPath + Constante.PATH_REPASO + Constante.REPASO + Constante.FILETYPE_ZIP;
						Archivo.downloadFile(Constante.REPASO + Constante.FILETYPE_ZIP, origen, getResponse());	
						break;
					default:
						break;
				}
			}else{
				throw new ActionException("Parametro 'id'incorrecto ("+id+") y no pertenece al aula actual: "+getUsuarioSession().getAulaActual());
			}
		} catch (FileNotFoundException e) {
			log.error(e);
		} catch (Exception e) {
			log.error(e);
		}
		return NONE;
	}
	
	public String miClase() throws ActionException{
		log.info("miClase()");
		try {
			grupos = aulaVirtualService.obtenerMatriculas(getUsuarioSession().getAulaActual().getIdFicha());
		} catch (Exception e) {
			log.error(e);
			throw new ActionException(e);
		}
		return SUCCESS;
	}
	
/*
	
	//para deshabilitar test por parte del docente
	public String fichaUnidadRecursoTestCambiarEstadoEstudiante() {
		String idFicha = String.valueOf(((AulaVirtual) getSession()
				.get(Constante.AULA_ACTUAL)).getIdFicha());
		FichaUnidadRecurso vo1 = null;
		try {
			vo1 = fichaConfiguracionService.obtenerFichaUnidadRecurso(idFicha,
					idUnidad, Constante.RECURSO_ID_TEST);
		} catch (ServiceException e) {
			log.error(e.getMessage());
		}
		FichaUnidadRecurso vo = new FichaUnidadRecurso();
		vo.setIdFicha(idFicha);
		vo.setIdUnidad(idUnidad);
		vo.setIdRecurso(Constante.RECURSO_ID_TEST);
		vo.setUsuarioModificacion(((Usuario) (getSession()
				.get(Constante.USUARIO_ACTUAL))).getIdUsuario());
		vo.setPeso(vo1.getPeso());
		vo.setCalifica(vo1.getCalifica());
		vo.setDeshabilitadoDoc(vo1.getDeshabilitadoDoc());
		String nuevoEstado = (vo1.getDeshabilitadoEstu().equals("1")) ? "0"
				: "1";
		vo.setDeshabilitadoEstu(nuevoEstado);
		vo.setEstado(vo1.getEstado());
		try {
			fichaConfiguracionService.actualizarFichaUnidadRecurso(vo);
		} catch (ServiceException e) {
			log.error(e.getMessage());
		}
		return NONE;
	}
*/
}
