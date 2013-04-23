package edu.opencampus.lms.service;

import java.util.Collection;
import java.util.Iterator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.opencampus.lms.dao.AulaVirtualDAO;
import edu.opencampus.lms.excepcion.ServiceException;
import edu.opencampus.lms.modelo.AulaVirtual;
import edu.opencampus.lms.modelo.Matricula;
import edu.opencampus.lms.modelo.Silabo;
import edu.opencampus.lms.modelo.Usuario;
import edu.opencampus.lms.modelo.aulavirtual.MatriculaRol;
import edu.opencampus.lms.modelo.ficha.Recurso;
import edu.opencampus.lms.modelo.ficha.Unidad;
import edu.opencampus.lms.util.Constante;

public class AulaVirtualService {

	protected Log log = LogFactory.getLog(getClass());
	
	private AulaVirtualDAO aulaVirtualDAO;

	public void setAulaVirtualDAO(AulaVirtualDAO aulaVirtualDAO) {
		this.aulaVirtualDAO = aulaVirtualDAO;
	}

	public AulaVirtual obtenerAulaVirtualAdministrar(Integer idficha, Usuario usuario) throws ServiceException {
		try {
			return aulaVirtualDAO.obtenerAulaVirtual(idficha, usuario, true);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	public Silabo obtenerElSilabo(Integer idsilabo) throws ServiceException {
		try {
			return aulaVirtualDAO.obtenerElSilabo(idsilabo);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	public Collection<MatriculaRol> obtenerMatriculasAdministrar(Integer idFicha) throws ServiceException {
		try {
			return aulaVirtualDAO.obtenerMatriculasAdministrar(idFicha);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	public Collection<MatriculaRol> obtenerMatriculas(Integer idFicha) throws ServiceException {
		try {
			return aulaVirtualDAO.obtenerMatriculas(idFicha);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	public Collection<Usuario> buscarNoMatriculados(String[] nombres, int idFicha) throws ServiceException {
		try {
			return aulaVirtualDAO.buscarNoMatriculados(nombres, idFicha);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	public void matricularUsuario(Integer idFicha, Matricula matricula) throws ServiceException {
		try {
			if(!aulaVirtualDAO.esMiAula(idFicha, matricula.getUsuario().getId())){
				if(matricula.getRol().getIdRol() == Constante.ROL_CAMPUS_AULAVIRTUAL_DOCENTE
					&& aulaVirtualDAO.listarDocentes(idFicha).size() == 0) matricula.setPrincipal(Constante.ESTADO_ACTIVO);
				aulaVirtualDAO.matricularUsuario(idFicha, matricula);
			}else{
				throw new ServiceException("El usuario ya se encuentra matriculado en la clase.");
			}
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	public void cambiarEstadoMatricula(Matricula matricula) throws ServiceException {
		try {
			aulaVirtualDAO.cambiarEstadoMatricula(matricula);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	public void eliminarMatricula(Integer idFicha, Matricula matricula) throws ServiceException {
		try {
			aulaVirtualDAO.eliminarMatricula(matricula);
			Collection<Matricula> docentes = aulaVirtualDAO.listarDocentes(idFicha);
			for (Matricula docente : docentes) {
				docente.setUsuarioModificacion(matricula.getUsuarioModificacion());
				aulaVirtualDAO.principal(idFicha, docente);
				break;
			}
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	public void principal(Integer idFicha, Matricula matricula) throws ServiceException {
		try {
			aulaVirtualDAO.principal(idFicha, matricula);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	public AulaVirtual obtenerAulaVirtual(Integer idficha, Usuario usuario) throws ServiceException {
		try {
			AulaVirtual aula = aulaVirtualDAO.verDisponibilidadDeFicha(idficha, usuario);
			if(aula != null){
				if(aula.isDisponible()){
					aula = aulaVirtualDAO.obtenerAulaVirtual(idficha, usuario, false);
					//Eliminar unidades de estado inactivo 
					for (Iterator<Unidad> itUnidad=aula.getSilabo().getUnidades().iterator();itUnidad.hasNext();) {
						Unidad unidad = itUnidad.next();
						if(unidad.getEstado() == Constante.ESTADO_INACTIVO){
							log.info("Eliminando unidad por inactivo: "+unidad.getIdUnidad()+"-"+unidad.getNombreCompleto());
							itUnidad.remove(); //PMpendientePM Si aqui falla por  ConcurrentModificationException haz como la linea de abajo, el itUnidad.remove() que este afuera del foreach 
						}else{
							//Eliminado recursos de estado inactivo
							Recurso recurso = null;
							for (Iterator<Recurso> itRecurso=unidad.getRecursos().iterator();itRecurso.hasNext();) {
								recurso = itRecurso.next();
								if(recurso.getEstado() == Constante.ESTADO_INACTIVO){
									log.info("Eliminando recurso por inactivo: "+unidad.getIdUnidad()+"-"+recurso.getNombre());
									break;
								}
							}
							unidad.getRecursos().remove(recurso);
						}
					}	
					
					//Eliminar unidades de estado inactivo 
					//java.util.ConcurrentModificationException
//					for (Unidad unidad : aula.getSilabo().getUnidades()) {
//						if(unidad.getEstado() == Constante.ESTADO_INACTIVO){
//							log.info("Eliminando unidad por inactivo: "+unidad.getIdUnidad()+"-"+unidad.getNombreCompleto());
//							aula.getSilabo().getUnidades().remove(unidad);
//						}else{
//							//Eliminado recursos de estado inactivo
//							for (Recurso recurso : unidad.getRecursos()) {
//								if(recurso.getEstado() == Constante.ESTADO_INACTIVO){
//									log.info("Eliminando recurso por inactivo: "+unidad.getIdUnidad()+"-"+recurso.getNombre());
//									unidad.getRecursos().remove(recurso);
//								}
//							}
//						}
//					}

				}else{
					throw new ServiceException("El aula de id "+idficha+" se encuentra fuera de periodo. " +
							"Inicio:"+aula.getFechaInicioToString()+" Fin:"+aula.getFechaFinToString());
				}
			}else{
				throw new ServiceException("No tiene permiso para acceder al aula de id "+idficha);
			}
			return aula;
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	
/*
	
	public AulaVirtual obtenerAulaTmpParaAuditoria(int idMatricula) throws ServiceException {
		AulaVirtual aula = null;
		try {
			aula = aulaVirtualDAO.obtenerAulaTmpParaAuditoria(idMatricula);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage());
		}
		return aula;
	}

	public Silabo obtenerSilaboPorIDCurso(int idCurso) throws ServiceException {
		Silabo silabo = null;
		try {
			silabo = aulaVirtualDAO.obtenerSilaboPorIDCurso(idCurso);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage());
		}
		return silabo;
	}

	public void asignarSilabo(AulaVirtual aulaVirtual) throws ServiceException {
		try {
			aulaVirtualDAO.asignarSilabo(aulaVirtual);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public PlanDocente obtenerPlanDocente(AulaVirtual aulaVirtual)
			throws ServiceException {
		PlanDocente plan = new PlanDocente();
		try {
			plan = aulaVirtualDAO.obtenerPlanDocente(aulaVirtual);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage());
		}
		return plan;
	}

	public ReporteDetalle obtenerReporteDetalle(int idMatricula)
			throws ServiceException {
		ReporteDetalle reporte = new ReporteDetalle();
		try {
			reporte = aulaVirtualDAO.obtenerReporteDetalle(idMatricula);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage());
		}
		return reporte;
	}
	
	public Collection<ReporteNotas> listarReporteDeNotas(int idFicha) throws ServiceException {
		Collection<ReporteNotas> reporte = null;
		try {
			reporte = aulaVirtualDAO.listarReporteDeNotas(idFicha);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage());
		}
		return reporte;
	}

	public ReporteDetalle obtenerAuditoria(int idMatricula)
			throws ServiceException {
		ReporteDetalle reporte = new ReporteDetalle();
		try {
			reporte = aulaVirtualDAO.obtenerAuditoria(idMatricula);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage());
		}
		return reporte;
	}

	public Reporte obtenerReporte(int idFicha, int idUnidad, boolean tipo,
			boolean pertenece, Usuario usuario) throws ServiceException {
		Reporte reporte = null;
		try {
			reporte = aulaVirtualDAO.obtenerReporte(idFicha, idUnidad, tipo,
					pertenece, usuario);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage());
		}
		return reporte;
	}

	public void modificarPeriodo(String idPeriodo, int idFicha, String usuario)
			throws ServiceException {
		try {
			aulaVirtualDAO.modificarPeriodo(idPeriodo, idFicha, usuario);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public void modificarFechas(String sede,String fecha1, String fecha2, int diasEdiccion,
			int diasRevision, int idFicha, String usuario)
			throws ServiceException {
		try {
			aulaVirtualDAO.modificarFechas(sede,fecha1, fecha2, diasEdiccion,
					diasRevision, idFicha, usuario);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	public void guardarNoAuditable(int idFicha, int noauditable)
			throws ServiceException {
		try {
			aulaVirtualDAO.guardarNoAuditable(idFicha, noauditable);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage());
		}
	}
*/
}
