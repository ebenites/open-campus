package edu.opencampus.lms.action.unidad;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.opencampus.lms.action.BaseAction;
import edu.opencampus.lms.excepcion.ActionException;
import edu.opencampus.lms.modelo.Jerarquia;
import edu.opencampus.lms.modelo.ficha.Unidad;
import edu.opencampus.lms.service.JerarquiaService;
import edu.opencampus.lms.service.UnidadService;
import edu.opencampus.lms.util.Archivo;
import edu.opencampus.lms.util.Constante;
import edu.opencampus.lms.util.Formato;

public class UnidadAction extends BaseAction {

	Log log = LogFactory.getLog(getClass());

	private static final long serialVersionUID = -8823699153969110941L;

	private UnidadService unidadService;
	private JerarquiaService jerarquiaService;
	private String unidad;
	private Integer ruta;
	private Integer id;
	private String exacto;

	private Collection<Unidad> unidades;
	private Collection<Unidad> ultimas;
	private Collection<Jerarquia> jerarquias;
	
	public Collection<Unidad> getUltimas() {
		return ultimas;
	}

	public void setUltimas(Collection<Unidad> ultimas) {
		this.ultimas = ultimas;
	}

	public Collection<Unidad> getUnidades() {
		return unidades;
	}

	public void setUnidades(Collection<Unidad> unidades) {
		this.unidades = unidades;
	}

	public Collection<Jerarquia> getJerarquias() {
		return jerarquias;
	}

	public void setJerarquias(Collection<Jerarquia> jerarquias) {
		this.jerarquias = jerarquias;
	}

	public String getExacto() {
		return exacto;
	}

	public void setExacto(String exacto) {
		this.exacto = exacto;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getRuta() {
		return ruta;
	}

	public void setRuta(Integer ruta) {
		this.ruta = ruta;
	}

	public String getUnidad() {
		return unidad;
	}

	public void setUnidad(String unidad) {
		this.unidad = unidad.replaceAll("%", "");
	}

	public void setUnidadService(UnidadService unidadService) {
		this.unidadService = unidadService;
	}

	public void setJerarquiaService(JerarquiaService jerarquiaService) {
		this.jerarquiaService = jerarquiaService;
	}

	public String buscarUnidad() throws ActionException{
		log.info("buscarUnidad() Ruta: "+ruta +" Unidad: "+unidad+" Exacto: "+exacto);
		try {
			ultimas = unidadService.listarUltimas();
			jerarquias = jerarquiaService.listarJerarquias();
			
			//Hacer paginacion tipo usuarios
			if(unidad == null){
				unidad = (String)getSession().get("UNIDAD_NOMBRE");
				ruta = (Integer)getSession().get("UNIDAD_RUTA");
				exacto = (String)getSession().get("UNIDAD_EXACTO");
			}
			if (unidad != null && ruta != null) {
				unidades = unidadService.buscarUnidadPorNombre(unidad, ruta, exacto);
				getSession().put("UNIDAD_NOMBRE", unidad);
				getSession().put("UNIDAD_RUTA", ruta);
				getSession().put("UNIDAD_EXACTO", exacto);
			}else{
				addActionError("Ha ingresado un nombre de unidad no válida.");
				log.error("Faltan parametros del formulario.");
			}
		} catch (Exception e) {
			log.error(e);
			throw new ActionException(e);
		}
		return SUCCESS;
	}
	
	public String verRecurso() {
		log.info("verRecurso()");
		try {
			if(id != null){
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
			}
		} catch (FileNotFoundException e) {
			log.error(e);
		} catch (Exception e) {
			log.error(e);
		}
		return NONE;
	}

/*
	public String listarFichas() {
		log.info("String buscarPorId() - ID:" + id);
		try {
			if (id != null && id.trim().length() > 0) {
				Collection<Ficha> fichas = unidadService.listarFichas(Integer
						.parseInt(id));

				if (!fichas.isEmpty()) {
					getResponse().setContentType("text/xml");
					getResponse().setHeader("Cache-Control", "no-cache");
					PrintWriter out = getResponse().getWriter();
					out.write("<?xml version='1.0' encoding='ISO-8859-1'?>");
					out.write("<fichas>");

					for (Ficha ficha : fichas) {
						getResponse().getWriter().write("<ficha>");

						getResponse().getWriter().write(
								"<idUDS>" + ficha.getCodigoCursoUDS()
										+ "</idUDS>");
						getResponse().getWriter()
								.write(
										"<idFicha>" + ficha.getIdFicha()
												+ "</idFicha>");
						getResponse().getWriter().write(
								"<estado>" + ficha.getEstado() + "</estado>");
						getResponse().getWriter().write(
								"<nombre>" + ficha.getNombreCursoUDS()
										+ "</nombre>");
						getResponse().getWriter().write(
								"<departamento>"
										+ ficha.getNombreDepartamento()
										+ "</departamento>");
						getResponse().getWriter().write(
								"<seccion>" + ficha.getNivel() + "-"
										+ ficha.getSecciones() + "</seccion>");
						getResponse().getWriter().write(
								"<familia>" + ficha.getNombreFamilia()
										+ "</familia>");
						getResponse().getWriter().write(
								"<fechaInicio>"
										+ Formato.getStringDeDate(ficha
												.getFechaInicio())
										+ "</fechaInicio>");
						getResponse().getWriter()
								.write(
										"<fechaFin>"
												+ Formato.getStringDeDate(ficha
														.getFechaFin())
												+ "</fechaFin>");

						getResponse().getWriter().write("</ficha>");
					}
					out.write("</fichas>");
					out.close();
				}
			}
		} catch (ServiceException e) {
			log.error(e);
		} catch (NumberFormatException e) {
			log.error(e);
		} catch (IOException e) {
			log.error(e);
		}
		return NONE;
	}
*/
	public String cargar() throws ActionException{
		log.info("cargar()");
		try {
			ultimas = unidadService.listarUltimas();
			jerarquias = jerarquiaService.listarJerarquias();
			
			getSession().remove("UNIDAD_NOMBRE");
			getSession().remove("UNIDAD_RUTA");
			getSession().remove("UNIDAD_EXACTO");
		} catch (Exception e) {
			log.error(e);
			throw new ActionException(e);
		}
		return SUCCESS;
	}

	public String eliminarUnidad() throws ActionException{
		log.info("String eliminarUnidad()" + id);
		try {
			unidadService.eliminar(id);
		} catch (Exception e) {
			log.error(e);
			addActionError("La unidad no ha sido eliminada, posiblemente este en uso, solo pudo ser desactivada.");
			throw new ActionException(e);
		}
		try{
			Archivo.eliminarDirectorio(Constante.RUTA_UNIDADES + Formato.completarCeros(id, 8));
		} catch (Exception e) {
			log.error(e);
			addActionError("La unidad ha sido eliminada de la base de datos pero aun quedan rastros en el repositorio.");
			throw new ActionException(e);
		}
		return SUCCESS;
	}

	public String crearUnidad() throws ActionException{
		log.info("crearUnidad() nombre: "+unidad);
		try {
			if (unidad != null && unidad.trim().length() >= 2 && ruta != null) {
				if (unidadService.existeUnidad(unidad)) {
					log.error("Unidad repetida");
					addActionError("Ya existe una unidad con el mismo nombre.");
					throw new ActionException("Unidad repetida");
				}

				Unidad u = new Unidad();
				u.setNombreCompleto(unidad);
				u.setJerarquia(new Jerarquia(ruta));

				Integer idUnidad = unidadService.crearUnidad(u);
				
				if (idUnidad != null) {
					log.info("Unidad creada: " + idUnidad + " - Inicia secuencia de creación de directorios.");
					String unidadesPath = Constante.RUTA_UNIDADES + Formato.completarCeros(idUnidad, 8)+ Constante.SLASH;
					
					String dirTexto = unidadesPath + Constante.PATH_TEXTO;
					String dirRepaso = unidadesPath + Constante.PATH_REPASO;
					String dirLab = unidadesPath + Constante.PATH_LAB;
					String dirTest = unidadesPath + Constante.PATH_TEST;
					String dirTrabajo = unidadesPath + Constante.PATH_TRABAJO;

					try {
						Archivo.crearDirectorio(dirTexto);
						Archivo.crearDirectorio(dirRepaso);
						Archivo.crearDirectorio(dirLab);
						Archivo.crearDirectorio(dirTest);
						Archivo.crearDirectorio(dirTrabajo);
						log.info("Directorios creados satisfactoriamente.");
					} catch (IOException e) {
						log.error(e);
					}
				}
			}

		}  catch (Exception e) {
			log.error(e);
			throw new ActionException(e);
		}
		return SUCCESS;
	}

	public String modificarUnidad() throws ActionException{
		log.info("String modificarUnidad() nombre: "+unidad);
		try {
			
			if (id != null && unidad != null && unidad.trim().length() >= 2 && ruta != null) {
				if (unidadService.existeUnidad(unidad)) {
					log.error("Unidad repetida");
					addActionError("Ya existe una unidad con el mismo nombre.");
					throw new ActionException("Unidad repetida");
				}
				
				Unidad u = new Unidad();
				u.setIdUnidad(id);
				u.setNombreCompleto(unidad);
				u.setJerarquia(new Jerarquia(ruta));
				unidadService.modificarUnidad(u);
			}
			
		}  catch (Exception e) {
			log.error(e);
			throw new ActionException(e);
		}
		return SUCCESS;
	}
	
	public String cambiarEstado() throws ActionException{
		log.info("cambiarEstado(): " + exacto);
		try {
			if(id != null && exacto != null){
				
				if("Activar".equals(exacto))
					unidadService.cambiarEstado(id, Constante.ESTADO_ACTIVO);
				else
					unidadService.cambiarEstado(id, Constante.ESTADO_INACTIVO);
				
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

}
