package edu.opencampus.lms.util;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.swing.ImageIcon;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.ProjectHelper;
import org.apache.tools.ant.taskdefs.Chmod;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public final class Archivo {

	protected static Log log = LogFactory.getLog(new Archivo().getClass());

	public static final void escribirArchivo(InputStream iStream, String destino)
			throws IOException {

		log.info("Archivo: escribirArchivo: " + destino);
		OutputStream bos = new FileOutputStream(destino);
		int bytesRead = 0;
		byte[] buffer = new byte[8192];
		while ((bytesRead = iStream.read(buffer, 0, 8192)) != -1) {
			bos.write(buffer, 0, bytesRead);
		}
		bos.close();
	}

	public static final void copiarArchivo(String origen, String destino)
			throws IOException {
		log.info("Archivo: copiarArchivo: inicio");
		File o = new File(origen);
		//
		String destinoDirectorio = destino.substring(0, destino
				.lastIndexOf(Constante.SLASH));
		if (!(new File(destinoDirectorio)).exists())
			crearDirectorio(destinoDirectorio);
		//
		File d = new File(destino);

		BufferedInputStream in = new BufferedInputStream(new FileInputStream(o));
		BufferedOutputStream out = new BufferedOutputStream(
				new FileOutputStream(d));

		int bytesRead = 0;
		byte[] buffer = new byte[8192];
		while ((bytesRead = in.read(buffer, 0, 8192)) != -1) {
			out.write(buffer, 0, bytesRead);
		}
		in.close();
		out.flush();
		out.close();
		log.info("Archivo: copiarArchivo: fin - " + destino);
	}

	public static final boolean crearDirectorio(String dir) throws IOException {
		boolean rc = true;
		File d = new File(dir);
		if (!d.exists()) {
			rc = d.mkdirs();
			if (!rc) {
				log.info("Archivo: crearDirectorio: No se pudo crear el directorio: "+ dir);
			} else {
				log.info("Archivo: crearDirectorio: Directorio creado - "+ dir);
				Chmod chmod = new Chmod();
				chmod.setProject(new org.apache.tools.ant.Project());
				chmod.setPerm("775");
				chmod.setDir(d);
				chmod.execute();
				log.info("Archivo: crearDirectorio: Permiso establecido 775 - "+ dir);
			}
		}
		return rc;
	}

	public static final boolean copiarRepaso(String origen, String idunidad,
			String session) {
		boolean ok = false;
		try {
			String baseDir = Constante.HOME_ANT;
			File ff = new File(session);
			if (!ff.exists()) {
				boolean rc = ff.mkdir();
				if (!rc) {
					throw new Exception(
							"Archivo: copiarRepaso: No se puede crear el directorio: "
									+ session);
				} else {
					log.info("Archivo: copiarRepaso: Creando directorio "
							+ session);
				}
			}

			File yy = new File(ff, idunidad);
			if (!yy.exists()) {
				boolean rc = yy.mkdir();
				if (!rc) {
					throw new Exception("Unable to create directory: "
							+ session + idunidad);
				}
			}

			Project project = new Project();
			project.setUserProperty("src", origen);
			project.setUserProperty("dest", yy.getPath());
			project.fireBuildStarted();
			project.init();
			File buildFile = new File(baseDir, "build.xml");
			project.setBaseDir(new File(baseDir));
			ProjectHelper.configureProject(project, buildFile);
			project.executeTarget(project.getDefaultTarget());

			ok = true;
		} catch (Exception e) {
			ok = false;
			log
					.info("Archivo: copiarRepaso: No se encuentra la carpeta del repaso: "
							+ e.getMessage());
		}
		return ok;
	}

	/*
	 * param origen: ruta absoluta param destino: ruta absoluta
	 */
	public static final void copiarDirectorio(String origen, String destino) {

		try {

			String baseDir = Constante.HOME_ANT;
			log.info("Archivo: copiarDirectorio: origen: " + origen);
			log.info("Archivo: copiarDirectorio: destino: " + destino);
			Project project = new Project();
			project.setUserProperty("src", origen);
			project.setUserProperty("dest", destino);
			project.fireBuildStarted();
			project.init();
			File buildFile = new File(baseDir, "build.xml");
			project.setBaseDir(new File(baseDir));
			ProjectHelper.configureProject(project, buildFile);
			project.executeTarget(project.getDefaultTarget());
		} catch (Exception e) {
			log.info("Archivo: copiarDirectorio: Exception: " + e.getMessage());
		}
	}

	public static final boolean eliminarDirectorio(String dir) {
		boolean ok = false;
		try {
			File carpeta = new File(dir);
			if (carpeta.exists()) {
				eliminaraSubcarpetas(carpeta);
				if (carpeta.delete()) {
					log.info("Archivo: eliminarDirectorio: "
							+ carpeta.getPath());
				}
			}
			ok = true;
		} catch (Exception e) {
			log.error("Archivo: eliminarDirectorio: " + e.getMessage());
		}
		return ok;
	}
	
	public static final boolean eliminarArchivo(String file) {
		boolean ok = false;
		try {
			File archivo = new File(file);
			if (archivo.exists()) {
				if (archivo.delete()) {
					log.info("Archivo: eliminarArchivo: Archivo Eliminado -> " + archivo.getName());
				}else{
					log.error("Archivo: eliminarArchivo: No fue posible eliminar -> "+ archivo.getName());
				}
			}else{
				log.info("Archivo: eliminarArchivo: El archivo no existe");
			}
			ok = true;
		} catch (Exception e) {
			log.error("Archivo: eliminarArchivo: Error al intentar eliminar el archivo -> " + e);
		}
		return ok;
	}
	
	public static final boolean eliminarArchivoFiltrado(String dir, String nombre, boolean startsWith){
		
		File dirFile = new File(dir);
		
		if(dirFile.isDirectory()){
			
			String[] listFile = dirFile.list(new Filter(nombre,startsWith));
			
			for (int i = 0; i < listFile.length; i++) {
				if(!eliminarArchivo(dir+listFile[i])){
					log.error("Archivo: eliminarArchivoFiltrado: Se cancela el proceso de eliminacion.");
					return false;
				}
			}
			log.info("Archivo: eliminarArchivoFiltrado: Proceso de eliminacion por filtro completado.");
		}else{
			log.error("Archivo: eliminarArchivoFiltrado: No se encuentra el directorio: " + dir);
		}
		return true;
	}

	public static void eliminaraSubcarpetas(File carpeta) {
		try {
			if (carpeta.delete()) {
			} else {
				File[] a = carpeta.listFiles();
				for (int w = 0; w < a.length; w++) {
					if (a[w].delete()) {
						carpeta.delete();
					} else {
						Archivo.eliminaraSubcarpetas(a[w]);
					}
				}
			}
		} catch (Exception e) {
			log.error("No se pudo eliminar la carpeta " + e.getMessage());
		}
	}

	public static void downloadImage(String virtualFilename, String source,
			HttpServletResponse response) throws IOException,
			FileNotFoundException {
		
		response.setHeader("Content-Disposition", "inline; filename=\""+ virtualFilename + "\"");
		download(virtualFilename, source,response);
		
	}
	
	public static void downloadFile(String virtualFilename, String source,
			HttpServletResponse response) throws IOException,
			FileNotFoundException {
		
		response.setHeader("Content-Disposition", "attachment; filename=\""+ virtualFilename + "\"");
		download(virtualFilename, source,response);
	}
	
	private static void download(String virtualFilename, String source,
			HttpServletResponse response) throws IOException,
			FileNotFoundException {

		// String filename = "test.img";
		// String source = "c:\\download\\test1.img";

		int data;
		ServletOutputStream stream = null;
		BufferedInputStream buffer = null;

		try {

			// read file
			log.info("source :" + source);

			buffer = new BufferedInputStream(new FileInputStream(source));

			// get out
			stream = response.getOutputStream();

			// do Tx.
			log.info("before read");
			while ((data = buffer.read()) != -1)
				stream.write(data);
			log.info("after read");

			buffer.close();

			// write whatever to stream
			// stream.flush();
			stream.close();

		} catch (FileNotFoundException e){
			log.error("Archivo No Existe: "+ e.toString());
		} catch (Exception e) {
			log.info("Descarga Cancelada: "+ e.toString());
		} finally {
			response.setHeader("Content-Disposition", null);
			//
			if (stream != null) {
				// stream.flush();
				// stream.close();
			}
			//
			if (buffer != null) {
				buffer.close();
			}
		}
	}
	
	public static void downloadResizedFile(String virtualFilename, String source, int width, int height,
			HttpServletResponse response) throws IOException,
			FileNotFoundException {

		ServletOutputStream stream = null;
		BufferedImage imgFinal = null;

		try {
			response.setHeader("Content-Disposition", "inline; filename=\"" + virtualFilename + "\"");
			
			log.info("source :" + source + " - " + width + "px x " + height + "px");
			
			// Carga la imagen desde el fichero
			Image imagen = new ImageIcon(source).getImage();
			
			// Calculando el otro tamaño: si height=0 la altura cambia en proporcion al ancho y viceversa
			if(height == 0){
				height = (imagen.getHeight(null)*width)/imagen.getWidth(null);
			}else if (width == 0){
				width = (imagen.getWidth(null)*height)/imagen.getHeight(null);
			}
			
			// Crear un buffer sobre el cual se va a dibujar la imagen (ancho,alto,tipoDeImagen)
			imgFinal = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			
			// Fija la escala
			AffineTransform transformador = new AffineTransform();
			double newWidth = (double)width/imagen.getWidth(null);
			double newHeight = (double)height/imagen.getHeight(null);
			transformador.scale(newWidth, newHeight);
			
			// Dibuja la imagen
			Graphics2D g2 = imgFinal.createGraphics();
			g2.drawImage(imagen, transformador, null);
			g2.dispose();
			
			// get out
			stream = response.getOutputStream();
			
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(stream);
			encoder.encode(imgFinal);
			
			stream.close();

		} catch (FileNotFoundException e){
			log.error("Archivo No Existe: "+ e.toString());
		} catch (Exception e) {
			log.info("Descarga Cancelada: "+ e.toString());
		} finally {
			response.setHeader("Content-Disposition", null);
			//
			if (stream != null) {
				// stream.flush();
				// stream.close();
			}
			
		}
	}
}

class Filter implements FilenameFilter {
	
	private String joker;
	private boolean startsWith;
	
	public Filter(String joker,boolean startsWith){
		this.joker = joker;
		this.startsWith = startsWith;
	}
    public boolean accept(File dir, String name) {
        if(startsWith){
        	return (name.startsWith(joker));
        }
    	return (name.endsWith(joker));
    }
}

