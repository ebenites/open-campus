package edu.opencampus.lms.action.laboratorio;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.GregorianCalendar;

import edu.opencampus.lms.action.BaseAction;
import edu.opencampus.lms.excepcion.ServiceException;
import edu.opencampus.lms.modelo.AulaVirtual;
import edu.opencampus.lms.modelo.Matricula;
import edu.opencampus.lms.modelo.TrabajoIndividual;
import edu.opencampus.lms.modelo.Usuario;
import edu.opencampus.lms.modelo.tindividual.TrabajoIndividualInteraccion;
import edu.opencampus.lms.modelo.tindividual.TrabajoIndividualMatricula;
import edu.opencampus.lms.service.LaboratorioService;
import edu.opencampus.lms.util.Archivo;
import edu.opencampus.lms.util.Constante;
import edu.opencampus.lms.util.Formato;
import edu.opencampus.lms.util.Util;

public class LaboratorioAction extends BaseAction {

	private static final long serialVersionUID = 6987695165251537057L;
	private LaboratorioService laboratorioService;

	public void setLaboratorioService(LaboratorioService laboratorioService) {
		this.laboratorioService = laboratorioService;
	}
 
	public File file;
	public String contentType;
	public String filename;
	
	public String idUnidad;
	public String idMatricula;
	
	public String nota;
	public String descripcion;
	public String fechaActivacion;
	public String fechaEntrega;
	
	public String cmd;
	public String msg;

    public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getCmd() {
		return cmd;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public String getFechaActivacion() {
		return fechaActivacion;
	}

	public String getFechaEntrega() {
		return fechaEntrega;
	}

	public String getIdMatricula() {
		return idMatricula;
	}

	public String getMsg() {
		return msg;
	}

	public String getNota() {
		return nota;
	}

	public void setCmd(String cmd) {
		this.cmd = cmd;
	}

	public String getIdUnidad() {
		return idUnidad;
	}

	public void setFechaActivacion(String fechaActivacion) {
		this.fechaActivacion = fechaActivacion;
	}

	public void setFechaEntrega(String fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}

	public void setFile(File file) {
       this.file = file;
    }

    public void setFileContentType(String contentType) {
       this.contentType = contentType;
    }

    public void setFileFileName(String filename) {
       this.filename = filename;
    }
	
	public void setIdUnidad(String idUnidad) {
		this.idUnidad = idUnidad;
	}

	public void setIdMatricula(String idMatricula) {
		this.idMatricula = idMatricula;
	}

	public void setNota(String nota) {
		this.nota = nota;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String cargar() {
		log.info("cargar()");
		TrabajoIndividual ti = null;
		try {
			AulaVirtual aula = (AulaVirtual) getSession().get(Constante.AULA_ACTUAL);
			
			if(cmd != null && "error".equals(cmd)){
				idUnidad = (String)getSession().get("IDUNIDAD");
				log.info("Upload Error");
				setMsg("error");
			}
			if(aula != null && idUnidad != null){
				if(!"".equals(idUnidad)){
					ti = new TrabajoIndividual();
					ti.setIdFicha(aula.getIdFicha());
					ti.setIdUnidad(Integer.parseInt(idUnidad));
					ti = laboratorioService.obtenerTrabajoIndividual(ti);
				}else{
					ti = (TrabajoIndividual)getSession().get(Constante.AULAVIRTUAL_LABORATORIO);
				}
				if(ti.getInteraccion().getArchivoNombre() == null){
					String source = Constante.RUTA_REPOSITORIO_UNITS+ Constante.SLASH + Formato.completarCeros(idUnidad, 8) +
					Constante.SLASH + Constante.HOME_LABORATORIO + Constante.SLASH + Constante.LABORATORIO_PDF;
					File trabajo = new File(source);
					if(trabajo.exists()){
						ti.getInteraccion().setArchivoNombre("DEFAULT");
					}
				}
				
				getSession().put(Constante.AULAVIRTUAL_LABORATORIO, ti);
				
				getSession().put("IDUNIDAD", idUnidad);
				
				if(ti.getFechaActivacion() != null){
					Collection<TrabajoIndividualMatricula> matriculas 
					= laboratorioService.listarTrabajoPorMatricula(ti.getIdTrabajo());
					getRequest().setAttribute("TRABAJO_MATRICULAS", matriculas);
				}
			}
			
		}catch (ServiceException e) {
			log.error(e);
		}catch (NumberFormatException e){
			log.error(e);
		}catch (Exception e) {
			log.error(e);
		}
		return SUCCESS;
	}
	
	public String calificar(){
		
		Usuario usuario = (Usuario)getSession().get(Constante.USUARIO_ACTUAL);
		TrabajoIndividual trabajo= (TrabajoIndividual)getSession().get(Constante.AULAVIRTUAL_LABORATORIO);
		// Estado: Se Califica
		String estado = "1"; 
		
		try {
			
			TrabajoIndividualMatricula tMatricula = new TrabajoIndividualMatricula();
			if("".equals(nota)){
				// Estado: Se Quita Calificaci�n
				tMatricula.setNota(null);
				estado = "0"; 
			}else{
				tMatricula.setNota(Integer.parseInt(nota));
			}
			tMatricula.setUsuarioModificacion(usuario.getIdUsuario());
			tMatricula.setIdTrabajo(trabajo.getIdTrabajo());
			Matricula usuarioReceptor = new Matricula();
			usuarioReceptor.setIdMatricula(idMatricula);
			tMatricula.setUsuarioReceptor(usuarioReceptor);
			
			laboratorioService.calificarTrabajo(tMatricula);
			
			PrintWriter out = getResponse().getWriter();
			out.print(estado);
			out.close();
			
		} catch (ServiceException e) {
			log.error(e.getMessage());
		} catch (NumberFormatException e){
			log.error(e.getMessage());
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		
		return NONE;
	}
	
	public String publicarTrabajop(){
		log.info("publicarTrabajop()");
		Usuario usuario = (Usuario)getSession().get(Constante.USUARIO_ACTUAL);
		AulaVirtual aula = (AulaVirtual) getSession().get(Constante.AULA_ACTUAL);
		TrabajoIndividual trabajo= (TrabajoIndividual)getSession().get(Constante.AULAVIRTUAL_LABORATORIO);
		//System.out.println(cmd+trabajo.getIdTrabajo()+aula.getIdFicha()+descripcion+fechaActivacion+fechaEntrega+filename+file.length()); 
		
		try {
			if(aula != null && usuario != null && aula.getPeriodoMaestro() != 0){
			
				TrabajoIndividual tindividual = new TrabajoIndividual();
				tindividual.setIdTrabajo(trabajo.getIdTrabajo());
				tindividual.setIdFicha(aula.getIdFicha());
				tindividual.setFechaActivacion(Formato.setDateDeJSPCompleto(fechaActivacion));
				tindividual.setFechaEntrega(Formato.setDateDeJSPCompleto(fechaEntrega));
				tindividual.setUsuarioCreacion(usuario.getIdUsuario());
				tindividual.setUsuarioModificacion(usuario.getIdUsuario());
				
				TrabajoIndividualInteraccion tiInteraccion = new TrabajoIndividualInteraccion();
				Matricula usuarioEmisor = new Matricula();
				usuarioEmisor.setIdMatricula(String.valueOf(aula.getIdMatricula()));
				tiInteraccion.setUsuarioEmisor(usuarioEmisor);
				tiInteraccion.setDescripcion(descripcion);
			
				if(file != null && file.exists()){
				
					tiInteraccion.setArchivoTamanio(String.valueOf(file.length()));
				
					log.info("Publicaci�n de Trabajo - Archivo: "+filename+" - "+file.length()+" bytes");
					
					
					String extension = filename.substring(filename.lastIndexOf("."));		
					String origen = file.getAbsolutePath();
					if(!"/".equals(Constante.SLASH))
						origen = origen.replaceAll("/", Constante.SLASH);
					String directorioDestino = Constante.HOME_LABORATORIOS + Constante.SLASH + aula.getPeriodoMaestro() +
						Constante.SLASH + aula.getIdFicha() + Constante.SLASH + trabajo.getIdTrabajo();
					String nombreDestino = Constante.LABORATORIO + "_" + idUnidad + extension;
					Archivo.copiarArchivo(origen, directorioDestino + Constante.SLASH + nombreDestino);
					
					tiInteraccion.setArchivoNombre(nombreDestino);
					
				}else{
					log.info("El trabajo no adjunta archivo");
				}
				
				tindividual.setInteraccion(tiInteraccion);
				
				if("mod".equals(cmd)){
					log.info("Modificar Trabajo");
					laboratorioService.modificarTrabajo(tindividual);
				}else{
					log.info("Publicar Trabajo Nuevo");
					laboratorioService.publicarTrabajo(tindividual);
				}
			}
		} catch (ServiceException e) {
			log.error(e.getMessage());
		} catch (NumberFormatException e){
			log.error(e.getMessage());
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		
		return SUCCESS;
	}
	
	public String eliminarTrabajo(){
		log.info("eliminarTrabajo()");
		Usuario usuario = (Usuario)getSession().get(Constante.USUARIO_ACTUAL);
		AulaVirtual aula = (AulaVirtual)getSession().get(Constante.AULA_ACTUAL);
		TrabajoIndividual trabajo= (TrabajoIndividual)getSession().get(Constante.AULAVIRTUAL_LABORATORIO);
		try {
			if(trabajo != null && usuario != null && aula.getPeriodoMaestro() != 0){
				TrabajoIndividual tindividual = new TrabajoIndividual();
				tindividual.setIdTrabajo(trabajo.getIdTrabajo());
				tindividual.setUsuarioModificacion(usuario.getIdUsuario());
				
				// Renombrar Archivo
				idUnidad = String.valueOf(trabajo.getIdUnidad());
				String source = Constante.RUTA_REPOSITORIO_UNITS+ Constante.SLASH + Formato.completarCeros(idUnidad, 8) +
				Constante.SLASH + Constante.HOME_LABORATORIO + Constante.SLASH + Constante.LABORATORIO_PDF;
				File doc = new File(source);
				if(doc.exists()){
					String archivoDestino = Constante.HOME_LABORATORIOS + Constante.SLASH + aula.getPeriodoMaestro() +
						Constante.SLASH + aula.getIdFicha() + Constante.SLASH + trabajo.getIdTrabajo() + Constante.SLASH + filename;
					//Archivo.copiarArchivo(archivoDestino, archivoDestino + "_" +Formato.getStringDeDatePaBKP()+".bkp");
					Archivo.eliminarArchivo(archivoDestino);
					
					laboratorioService.eliminarTrabajo(tindividual);
					
				}else{
					setMsg("No existe un documento predefinido.");
					log.error(msg);
				}
				
			}
		} catch (ServiceException e) {
			log.error(e.getMessage());
		} catch (NumberFormatException e){
			log.error(e.getMessage());
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return SUCCESS;
	}

	public String verMensajesDeDocente(){
		
		TrabajoIndividual trabajo= (TrabajoIndividual)getSession().get(Constante.AULAVIRTUAL_LABORATORIO);
		
		try {
			TrabajoIndividualMatricula matricula = new TrabajoIndividualMatricula();
			matricula.setIdTrabajo(trabajo.getIdTrabajo());
			
			Matricula usuarioReceptor = new Matricula();
			usuarioReceptor.setIdMatricula(idMatricula);
			matricula.setUsuarioReceptor(usuarioReceptor);
			
			matricula = laboratorioService.obtenerInteracciones(matricula);
			
			getRequest().setAttribute("TRABAJO_MATRICULA_INTERACCIONES", matricula);
			
		} catch (ServiceException e) {
			log.error(e.getMessage());
		} catch (NumberFormatException e){
			log.error(e.getMessage());
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		
		return SUCCESS;
	}
	
	public String enviarMensajeDeDocente(){
		
		Usuario usuario = (Usuario)getSession().get(Constante.USUARIO_ACTUAL);
		AulaVirtual aula = (AulaVirtual) getSession().get(Constante.AULA_ACTUAL);
		TrabajoIndividual trabajo= (TrabajoIndividual)getSession().get(Constante.AULAVIRTUAL_LABORATORIO);
		String idMensaje = getRequest().getParameter("idMensaje");
		
		try {
			TrabajoIndividualMatricula matricula = new TrabajoIndividualMatricula();
			matricula.setIdTrabajo(trabajo.getIdTrabajo());
			Matricula usuarioReceptor = new Matricula();
			usuarioReceptor.setIdMatricula(idMatricula);
			matricula.setUsuarioReceptor(usuarioReceptor);
			
			TrabajoIndividualInteraccion interaccion = new TrabajoIndividualInteraccion();
			interaccion.setIdMensaje(Integer.parseInt(idMensaje));
			interaccion.setDescripcion(descripcion);
			Matricula usuarioEmisor = new Matricula();
			usuarioEmisor.setIdMatricula(String.valueOf(aula.getIdMatricula()));
			interaccion.setUsuarioEmisor(usuarioEmisor);
			interaccion.setUsuarioCreacion(usuario.getIdUsuario());
			interaccion.setUsuarioModificacion(usuario.getIdUsuario());
			interaccion.setArchivoNombre(filename);
			
			//File
			
			//***/
			int version = Integer.parseInt(idMensaje);
			
			;
			if(laboratorioService.obtenerMensajeEstado(matricula) == Constante.FLAG_PENDIENTE_DOCENTE)
				version++;
			//***/

			if(file != null && file.exists()){
				log.info("Env�o de Mensaje con adjunto: "+filename+" - "+file.length()+" bytes");
				
				String extension = filename.substring(filename.lastIndexOf("."));		
				String origen = file.getAbsolutePath();
				if(!"/".equals(Constante.SLASH))
					origen = origen.replaceAll("/", Constante.SLASH);
				String directorioDestino = Constante.HOME_LABORATORIOS + Constante.SLASH + aula.getPeriodoMaestro() +
					Constante.SLASH + aula.getIdFicha() + Constante.SLASH + trabajo.getIdTrabajo();
				String nombreDestino = Constante.LABORATORIO + "_" + idMatricula + "_" + version + extension;
				Archivo.copiarArchivo(origen, directorioDestino + Constante.SLASH + nombreDestino);
				
				interaccion.setArchivoNombre(nombreDestino);
				interaccion.setArchivoTamanio(String.valueOf(file.length()));
				
			}else{
				log.info("Env�o de Mensaje sin adjunto");
			}
			
			//****
			
			Collection<TrabajoIndividualInteraccion> interacciones = new ArrayList<TrabajoIndividualInteraccion>();
			interacciones.add(interaccion);
			
			matricula.setInteracciones(interacciones);
			
			laboratorioService.enviarMensajeDeDocente(matricula);
		
		} catch (ServiceException e) {
			log.error(e.getMessage());
		} catch (NumberFormatException e){
			log.error(e.getMessage());
		} catch (Exception e) {
			log.error(e.getMessage());
		}
			
		return SUCCESS;
	}
	
	public String verMensajesDeEstudiante(){
		
		AulaVirtual aula = (AulaVirtual) getSession().get(Constante.AULA_ACTUAL);
		TrabajoIndividual ti = null;
		
		try {
			if(aula != null && idUnidad != null){
				ti = new TrabajoIndividual();
				ti.setIdFicha(aula.getIdFicha());
				ti.setIdUnidad(Integer.parseInt(idUnidad));
				ti = laboratorioService.obtenerTrabajoIndividual(ti);
				
				if(ti.getInteraccion().getArchivoNombre() == null){
					String source = Constante.RUTA_REPOSITORIO_UNITS+ Constante.SLASH + Formato.completarCeros(idUnidad, 8) +
					Constante.SLASH + Constante.HOME_LABORATORIO + Constante.SLASH + Constante.LABORATORIO_PDF;
					File trabajo = new File(source);
					if(trabajo.exists()){
						ti.getInteraccion().setArchivoNombre("DEFAULT");
					}
				}
				
				getSession().put(Constante.AULAVIRTUAL_LABORATORIO, ti);
				
				if(ti.getFechaActivacion() != null && ti.getFechaActivacion().before(new GregorianCalendar())){
					
					TrabajoIndividualMatricula matricula = new TrabajoIndividualMatricula();
					matricula.setIdTrabajo(ti.getIdTrabajo());
					Matricula usuarioReceptor = new Matricula();
					usuarioReceptor.setIdMatricula(String.valueOf(aula.getIdMatricula()));
					matricula.setUsuarioReceptor(usuarioReceptor);
					
					matricula = laboratorioService.obtenerInteracciones(matricula);
					
					getRequest().setAttribute("TRABAJO_MATRICULA_INTERACCIONES", matricula);
				}
			}
			
		} catch (ServiceException e) {
			log.error(e.getMessage());
		} catch (NumberFormatException e){
			log.error(e.getMessage());
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		
		return SUCCESS;
	}
	
	public String enviarMensajeDeEstudiante(){
		
		Usuario usuario = (Usuario)getSession().get(Constante.USUARIO_ACTUAL);
		AulaVirtual aula = (AulaVirtual) getSession().get(Constante.AULA_ACTUAL);
		TrabajoIndividual trabajo= (TrabajoIndividual)getSession().get(Constante.AULAVIRTUAL_LABORATORIO);
		String idMensaje = getRequest().getParameter("idMensaje");
		
		try {
			TrabajoIndividualMatricula matricula = new TrabajoIndividualMatricula();
			matricula.setIdTrabajo(trabajo.getIdTrabajo());
			Matricula usuarioReceptor = new Matricula();
			usuarioReceptor.setIdMatricula(String.valueOf(aula.getIdMatricula()));
			matricula.setUsuarioReceptor(usuarioReceptor);
			matricula.setFechaCreacion(trabajo.getFechaEntrega());
			
			TrabajoIndividualInteraccion interaccion = new TrabajoIndividualInteraccion();
			interaccion.setIdMensaje(Integer.parseInt(idMensaje));
			interaccion.setDescripcion(descripcion);
			Matricula usuarioEmisor = new Matricula();
			usuarioEmisor.setIdMatricula(String.valueOf(aula.getIdMatricula()));
			interaccion.setUsuarioEmisor(usuarioEmisor);
			interaccion.setUsuarioCreacion(usuario.getIdUsuario());
			interaccion.setUsuarioModificacion(usuario.getIdUsuario());
			interaccion.setArchivoNombre(filename);
			
			//File
			
			//***/
			int version = Integer.parseInt(idMensaje);
			
			;
			if(laboratorioService.obtenerMensajeEstado(matricula) == Constante.FLAG_PENDIENTE_ESTUDIANTE)
				version++;
			//***/
			
			if(file != null && file.exists()){
				log.info("Env�o de Mensaje con adjunto: "+filename+" - "+file.length()+" bytes");
				
				String extension = filename.substring(filename.lastIndexOf("."));		
				String origen = file.getAbsolutePath();
				if(!"/".equals(Constante.SLASH))
					origen = origen.replaceAll("/", Constante.SLASH);
				String directorioDestino = Constante.HOME_LABORATORIOS + Constante.SLASH + aula.getPeriodoMaestro() +
					Constante.SLASH + aula.getIdFicha() + Constante.SLASH + trabajo.getIdTrabajo();
				String nombreDestino = Constante.LABORATORIO + "_" + aula.getIdMatricula() + "_" + version + extension;
				Archivo.copiarArchivo(origen, directorioDestino + Constante.SLASH + nombreDestino);
				
				interaccion.setArchivoNombre(nombreDestino);
				interaccion.setArchivoTamanio(String.valueOf(file.length()));
				
			}else{
				log.info("Env�o de Mensaje sin adjunto");
			}
			
			//****
			
			Collection<TrabajoIndividualInteraccion> interacciones = new ArrayList<TrabajoIndividualInteraccion>();
			interacciones.add(interaccion);
			
			matricula.setInteracciones(interacciones);
			
			laboratorioService.enviarMensajeDeEstudiante(matricula);
		
		} catch (ServiceException e) {
			log.error(e.getMessage());
		} catch (NumberFormatException e){
			log.error(e.getMessage());
		} catch (Exception e) {
			log.error(e.getMessage());
		}
			
		return SUCCESS;
	}
	
	public String descargarTrabajo(){
		
		AulaVirtual aula = (AulaVirtual) getSession().get(Constante.AULA_ACTUAL);
		TrabajoIndividual trabajo= (TrabajoIndividual)getSession().get(Constante.AULAVIRTUAL_LABORATORIO);
		
		if(aula != null && trabajo != null && aula.getPeriodoMaestro() != 0){
			String source = Constante.HOME_LABORATORIOS + Constante.SLASH + aula.getPeriodoMaestro() +
			Constante.SLASH + aula.getIdFicha() + Constante.SLASH + trabajo.getIdTrabajo() + Constante.SLASH + filename;
			try {
				Archivo.downloadFile(filename, source, getResponse());
			} catch (FileNotFoundException e) {
				log.error(e);
			} catch (IOException e) {
				log.error(e);
			}
		}
		return NONE;
	}
	
	public String descargarTrabajoPredeterminado(){
		
		AulaVirtual aula = (AulaVirtual) getSession().get(Constante.AULA_ACTUAL);
		
		try{
			if(aula != null && !aula.getUnidades().isEmpty() && idUnidad != null && Util.esMiUnidad(aula.getUnidades(), idUnidad)){
				String idUnidadFormated = Formato.completarCeros(idUnidad,8);
				String source = Constante.RUTA_REPOSITORIO_UNITS+ Constante.SLASH + idUnidadFormated +
				Constante.SLASH + Constante.HOME_LABORATORIO + Constante.SLASH + Constante.LABORATORIO_PDF;
				String filenameOut = Constante.LABORATORIO + "_" + idUnidad + Constante.EXTENSION_PDF;
				
				Archivo.downloadFile(filenameOut, source, getResponse());
				
			}
		} catch (FileNotFoundException e) {
			log.error(e);
		} catch (IOException e) {
			log.error(e);
		}
		return NONE;
	}
	
}
