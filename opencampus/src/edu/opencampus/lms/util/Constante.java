package edu.opencampus.lms.util;

public class Constante {

	/**
	 * Redireccion de correo
	 */
	public static final String DIRECCION_CORREO_SALIENTE = "informes@tecsup.edu.pe";
	
	/**
	 * CONSTANTES PARA EL REPOSITORIO
	 */

	public static final String PROYECTO = "OpenCampus";
	public static final String SLASH = "/";
	private static final String RUTA_PROYECTO = "opencampus";
//	private static final String RUTA_REPOSITORIO = "/home/";
//	private static final String RUTA_REPOSITORIO = "/home/webcampus/";
	private static final String RUTA_REPOSITORIO = "/var/data/";
	
	public static final String REPOSITORIO = RUTA_REPOSITORIO + RUTA_PROYECTO + SLASH;
	
	public static final String RUTA_NOTICIA = REPOSITORIO + "noticia" + SLASH;
	
	public static final String RUTA_FOTOS = REPOSITORIO + "fotos" + SLASH;
	
	public static final String RUTA_BUZON = REPOSITORIO + "buzon" + SLASH;
	
	public static final String RUTA_UNIDADES = REPOSITORIO + "unidades" + SLASH;
	
		public static final String PATH_TEXTO = "texto" + SLASH;
		
		public static final String PATH_REPASO = "repaso" + SLASH;
		
		public static final String PATH_PAQUETE = "paquete" + SLASH;
		
		public static final String PATH_LAB = "lab" + SLASH;
		
		public static final String PATH_TEST = "test" + SLASH;
		
		public static final String PATH_TRABAJO = "trabajo" + SLASH;
		
		
	public static final String RUTA_PUBLICACIONES = REPOSITORIO + "pub" + SLASH;
	
	public static final String RUTA_TRABAJOS = REPOSITORIO + "trabajo" + SLASH;
	
	public static final String RUTA_GRUPALES = REPOSITORIO + "grupales" + SLASH;
		
	
	
	/**
	 * Para los casos de estado de la seguridad del Usuario
	 */
	public static final int SEGURIDAD_USUARIO_ACEPTADA = 0;

	public static final int SEGURIDAD_USUARIO_NO_ENCONTRADO = -1;

	public static final int SEGURIDAD_USUARIO_DESABILITADO = -2;

	public static final int SEGURIDAD_USUARIO_PASSWORD_ERRONEO = -3;

	public static final int SEGURIDAD_USUARIO_ERROR_DESCONOCIDO = -4;
	
	/**
	 * Para el estado 
	 */
	public static final int ESTADO_ACTIVO = 1;

	public static final int ESTADO_INACTIVO = 0;

	/**
	 * Tipos de usuarios
	 */
	public static final String TIPO_USUARIO_PERSONA = "P";
	
	public static final String TIPO_USUARIO_EMPRESA = "E";
	
	/**
	 * Tipos de servicios
	 */
	public static final int TIPO_SERVICIO_FORO = 1;
	
	public static final int TIPO_SERVICIO_NOTICIA = 2;
	
	public static final int TIPO_SERVICIO_AVISO = 3;
	
	/**
	 * Tipos de publicaciones
	 */
	public static final int TIPO_PUBLICACION_AVISO = 1;
	
	public static final int TIPO_PUBLICACION_LECTURA = 2;
		
	/**
	 * Constantes de session
	 */
	public static final String USUARIO_ACTUAL = "usuario_actual";
	
	public static final String USUARIOS_ACTUAL = "usuarios_actual";
	
	/**
	 * Genero
	 */
	public static String SEXO_MASCULINO = "M";

	public static String SEXO_FEMENINO = "F";

	public static String SEXO_SAKO = "R";
	
	public static String SEXO_NODETERMINADO = "N";
	
	/**
	 * Roles de plataforma
	 */
	
	public static final int ROL_CAMPUS_ADMINISTRADOR = 1;

	public static final int ROL_CAMPUS_SOPORTE = 2;

	public static final int ROL_CAMPUS_JEFE = 3; 
	
	public static final int ROL_CAMPUS_MONITOR = 4;

	public static final int ROL_CAMPUS_EMPRESA = 5;

	public static final int ROL_CAMPUS_DOCENTE = 6;

	public static final int ROL_CAMPUS_USUARIO = 7;
	
	public static final int ROL_CAMPUS_ANONIMO = 0;

	/**
	 * Roles de aula
	 */

	public static final int ROL_CAMPUS_AULAVIRTUAL_RESPONSABLE = 1;

	public static final int ROL_CAMPUS_AULAVIRTUAL_DOCENTE = 2;

	public static final int ROL_CAMPUS_AULAVIRTUAL_MONITOR_DOCENTE = 3;

	public static final int ROL_CAMPUS_AULAVIRTUAL_ESTUDIANTE = 4;

	public static final int ROL_CAMPUS_AULAVIRTUAL_ESTUDIANTE_MONITOR = 5;
	
	/**
	 * Tipo de Registro de Usuario
	 */
	public static final int ELEMENTO_LOGIN_AS = 0;

	public static final int ELEMENTO_LOGEO = 1;

	public static final int ELEMENTO_CURSO = 2;

	public static final int ELEMENTO_TEXTO = 3;

	public static final int ELEMENTO_REPASO = 4;

	public static final int ELEMENTO_LABORATORIO = 5;

	public static final int ELEMENTO_DIALOGO = 6;

	public static final int ELEMENTO_TRABAJO = 7;

	public static final int ELEMENTO_ACTIVIDAD_GRUPAL = 8;

	public static final int ELEMENTO_TEST = 9;

	public static final int ELEMENTO_PRACTICA = 10;

	public static final int ELEMENTO_REPASO_ENLACE = 11;

	public static final int ELEMENTO_TEXTO_FUENTES = 12;

	public static final int ELEMENTO_VIDEOS = 13;

	public static final int ELEMENTO_DEBATE = 14;
	
	/**
	 * Tipo Jerarquia 
	 */
	public static final String JERARQUIA_TIPO_GENERAL = "G";
	
	public static final String JERARQUIA_TIPO_NODO = "N";
	
	/**
	 * Idiomas
	 */
	public static final String IDIOMA_ATRIBUTO = "WW_TRANS_I18N_LOCALE";

	public static final String IDIOMA_ESPANOL = "es";

	public static final String IDIOMA_INGLES = "en";
	
	/**
	 * Formato fechas
	 */
	public static final String FECHA_DIA = "dd";
	
	public static final String FECHA_MES = "MM";
	
	public static final String FECHA_ANIO = "yyyy";
	
	public static final String FECHA_DIA_MES_ANO = "dd-MM-yyyy";
	
	public static final String FECHA_HORA_MI = "HH:mm";
	
	public static final String FECHA_DIA_MES_ANO_HORA_MI = "dd-MM-yyyy HH:mm";

	public static final String FECHA_DIA_MES_ANO_HORA_MI_SEG = "dd-MM-yyyy HH:mm:ss";
	
	public static final String FECHA_DIA_MES_ANO_HORA_MI_SEG_FILE = "yyyy-MM-dd-HH-mm-ss";
	
	/**
	 * Para estados de cambio de clave
	 */
	public static final int PASSWORD_ACEPTADO = 0;

	public static final int PASSWORD_CORTO = -1;

	public static final int PASSWORD_NO_COMPLEJO = -2;

	public static final int PASSWORD_REPETIDO = -3;

	public static final int PASSWORD_NO_COINCIDE = -4;
	
	public static final int PASSWORD_ERROR_DESCONOCIDO = -5;

	/**
	 * Para estados de cambio de clave
	 */
	public static final int PASSWORD_LONGITUD_MINIMA = 6;
	
	/**
	 * Portal cantidad de servicios
	 */
	public static int PORTAL_CANTIDAD_CORREOS = 5;

	public static int PORTAL_CANTIDAD_CURSOS_CANTIDAD = 7;

	public static int PORTAL_CANTIDAD_CUMPLEANOS = 1000;

	public static int PORTAL_CANTIDAD_ANOTACIONES = 5;
	
	public static int PORTAL_CANTIDAD_NOTICIAS = 3;
	
	public static int PORTAL_CANTIDAD_FOROS = 5;
	
	/**
	 * Paginacion
	 */
	public static int BUSQUEDA_CANTIDAD_DIRECTORIO = 10;
	
	/**
	 * Constantes para nombres de archivos 
	 */
	public static final String IMAGEN = "imagen";
	
	public static final String TEXTO = "texto";
	
	public static final String REPASO = "repaso";
	
	public static final String TRABAJO = "trabajo";

	public static final String LABORATORIO = "lab";

	public static final String FILETYPE_PDF = ".pdf";
	
	public static final String FILETYPE_ZIP = ".zip";
	
	public static final String FILETYPE_XLS = ".xls";
	
	public static final String FILETYPE_SWF = ".swf";
	
	public static final String FILETYPE_EXE = ".exe";
	
	/**
	 * ID Recursos
	 */
	
	public static final int RECURSO_ID_TEXTO = 1;

	public static final int RECURSO_ID_REPASO = 2;

	public static final int  RECURSO_ID_LABORATORIO = 3;

	public static final int  RECURSO_ID_DIALOGO = 4;

	public static final int RECURSO_ID_TRABAJO_INDIVIDUAL = 5;

	public static final int RECURSO_ID_TRABAJO_GRUPAL = 6;

	public static final int RECURSO_ID_TEST = 7;

	public static final int RECURSO_ID_PRACTICA = 8;

	public static final int RECURSO_ID_VIDEO = 9;
	
	/**
	 * Servicios
	 */

	public static String SERVICIO_CURSO = "servicio_curso";

	public static String SERVICIO_CHAT = "servicio_chat";

	public static String SERVICIO_NOTICIA = "servicio_noticia";

	public static String SERVICIO_CUMPLEANOS = "servicio_cumpleanos";

	public static String SERVICIO_BUZON = "servicio_buzon";

	public static String SERVICIO_FOROS = "servicio_foros";

	public static String SERVICIO_AGENDA = "servicio_agenda";

	public static String SERVICIO_APUNTES = "servicio_apuntes";

	public static String SERVICIO_BIBLIOTECA = "servicio_biblioteca";
	
	public static String SERVICIO_ENLACES = "servicio_enlaces";
	
	/* **********************************************************************************/


	//public static String USUARIOS_LOGEADOS = "USUARIOS_LOGEADOS";

	public static String FICHA_SECCION_NULA = "0";

	public static int FORMACION_C6 = 16;

	public static int FORMACION_C7 = 17;

	/**
	 * Estudios Generales
	 */

	public static String FORMACION_BASE = "Estudios Generales";

	public static int IDFORMACION_BASE = 100;

	public static final String FICHA_ESTADO_FECHA_0 = "Por empezar";

	public static final String FICHA_ESTADO_FECHA_1 = "En ejecuci&oacute;n";

	public static final String FICHA_ESTADO_FECHA_2 = "Finalizado";

	/**
	 * Servicios ofrecidos a las personas
	 */

	public static int PORTAL_SERVICIO_CANTIDAD_LINEA = 2;

	public static int PORTAL_SERVICIO_CURSOS = 1;

	public static int PORTAL_SERVICIO_BUZON = 2;

	public static int PORTAL_SERVICIO_CURSOS_HISTORICO = 3;

	public static int PORTAL_SERVICIO_INTRANET = 4;

	public static int PORTAL_SERVICIO_EGRESADOS = 5;

	public static int PORTAL_SERVICIO_CORREO_ELECTRONICO = 7;

	public static int PORTAL_SERVICIO_POPUP = 8;

	public static int PORTAL_SERVICIO_BROWSER = 9;

	public static int PORTAL_SERVICIO_URL_SIMPLE = 10;

	public static int PORTAL_SERVICIO_URL_COMPLETA = 11;

	public static int PORTAL_SERVICIO_FORO = 12;

	

	/**
	 * Turno
	 */

	public static String TURNO_TARDE = "Tarde";

	public static String TURNO_MANANA = "Mañana";

	

	/**
	 * Test SESION
	 * 
	 */
	public static String TEST_SESION = "test_sesion";

	/**
	 * Productos de SAPER
	 */
	public static int PRODUCTO_PROGRAMAS_INTEGRALES = 3003;

	public static int PRODUCTO_opencampus_VIRTUAL = 3002;

	public static int PRODUCTO_FORMACION = 100;

	/**
	 * Etiqueta Ficha
	 */
	public static String ETIQUETA_VIRTUAL = "1";

	public static String ETIQUETA_IMPORTANTE = "2";

	public static String ETIQUETA_UDS = "4";

	public static String ETIQUETA_PAPELERA = "1000";

	public static String ETIQUETA_HISTORICO = "3";

	public static String ETIQUETA_UDS_HISTORICO = "5";

	/**
	 * Estado de Ejecucion de curso
	 */
	public static int AULA_ESTADO_NOINICIADO = 0;

	public static int AULA_ESTADO_EJECUCION = 1;

	public static int AULA_ESTADO_HISTORICO = 2;

	/**
	 * TIPOS de discusion
	 */

	public static String DISCUSION_VARIABLE = "DISCUSION_TIPO";

	public static String DISCUSION_DIALOGO = "8000";

	public static String DISCUSION_FORO = "8001";

	public static String DISCUSION_DEBATE = "8002";

	/**
	 * Sedes
	 */

	public static String SEDE_AREQUIPA = "AQP";

	public static String SEDE_LIMA = "LIMA";

	public static String SEDE_TRUJILLO = "TRU";

	public static String SEDE_AREQUIPA_CORTO = "A";

	public static String SEDE_LIMA_CORTO = "L";

	public static String SEDE_TRUJILLO_CORTO = "T";

	

	/**
	 * Tipos de Cursos EVA
	 */
	public static String CURSO_EVA_PFR = "PFR";

	// public static int BD_NINGUNA = 0;
	//
	// public static int BD_CAMPUS = 1;
	//
	// public static int BD_USUARIO = 2;
	//
	// public static int BD_SEGURIDAD = 3;
	//
	// public static int BD_COMERCIAL = 4;
	//
	// public static int BD_UDS = 5;

	// public static String BD_CAMPUS_NOMBRE = "Campus";
	//
	// public static String BD_USUARIO_NOMBRE = "UDS - Usuario";
	//
	// public static String BD_SEGURIDAD_NOMBRE = "UDS - Seguridad";
	//
	// public static String BD_COMERCIAL_NOMBRE = "UDS - Comercial";
	//
	// public static String BD_UDS_NOMBRE = "UDS";
	

	/**
	 * Para los casos de estado de los Usuarios
	 */
	public static int ESTADO_USUARIO_HABILITADO = 0;

	public static int ESTADO_USUARIO_DESHABILITADO = -1;

	/**
	 * Para los casos de estado de los Usuarios
	 */
	public static String ESTADO_UDS_ESACTIVO = "S";

	public static String ESTADO_UDS_ESINACTIVO = "N";


	

	/**
	 * En el caso de logeo
	 */
	public static int LOGEO_ERROR_ACTIVO = 0;

	public static int LOGEO_ERROR_SEGURIDAD = 1;

	/*
	 * FORMATO DE REPASOS
	 */

	public static String FORMATO_REPASO_1 = "770 x 500";

	// public static String FORMATO_REPASO_2 = "800 x 600";

	/*
	 * BANDERA PARA INDICAR PENDIENTES
	 */

	public static int FLAG_INICIA_PENDIENTE_ESTUDIANTE = 0; // desaparecer

	public static int FLAG_NO_INICIADO = 0;

	public static int FLAG_PENDIENTE_DOCENTE = 1;

	public static int FLAG_PENDIENTE_ESTUDIANTE = 2;

	/*
	 * BANDERA PARA INDICAR TRABAJOS EXPIRADOS
	 */

	public static int FLAG_TRABAJO_PENDIENTE = 0;

	public static int FLAG_TRABAJO_NOEXPIRADO = 1;

	public static int FLAG_TRABAJO_EXPIRADO = 2;

//	@Deprecated
//	public static int TRABAJO_ESTADO_PENDIENTE = 0; // desaparecer
//	@Deprecated
//	public static int TRABAJO_ESTADO_NOEXPIRADO = 1; // desaparecer
//	@Deprecated
//	public static int TRABAJO_ESTADO_EXPIRADO = 2; // desaparecer

	/*
	 * MODULOS DEL AULA VIRTUAL
	 */

	public static String AULAVIRTUAL_TRABAJOINDIVIDUAL = "TRABAJO_INDIVIDUAL";

	public static String AULAVIRTUAL_LABORATORIO = "LABORATORIO";

	/*
	 * Jerarquia del Tipo
	 */

	//public static String JERARQUIA_TIPO_NODO = "N";

	// public static String JERARQUIA_TIPO_DEPARTAMENTO = "D";
	//
	// public static String JERARQUIA_TIPO_CARRERA = "C";
	//
	// public static String JERARQUIA_TIPO_PROGRAMA_INTEGRAL = "P";
	//
	// public static String JERARQUIA_TIPO_CURSO_VIRTUAL = "V";

	/*
	 * Aula Virtual
	 */

	public static String MENU_DESHABILITAR = "Desactivar";

	public static String MENU_HABILITAR = "Activar";

	public static String MENU_DESHABILITAR_DOCENTE = "Desactivar a Docentes";

	public static String MENU_DESHABILITAR_ESTUDIANTE = "Desactivar a Estudiantes";

	public static String MENU_HABILITAR_DOCENTE = "Activar a Docentes";

	public static String MENU_HABILITAR_ESTUDIANTE = "Activar a Estudiantes";

	public static String MENU_NOEXISTE = "Eliminar";

	public static String MENU_EXISTE = "Crear";

	/*
	 * Para listar las fichas
	 */
	public static int FICHALISTAR_CANTIDAD = 100;

	/*
	 * Tipos de Test
	 */

	public static final String TEST_TIPO_ASIMPLE = "A. Simple";

	public static final String TEST_TIPO_AMULTIPLE = "A. Multiple";

	public static final String TEST_TIPO_VF = "V - F";

	public static final String TEST_TIPO_RELACIONAR = "Relacionar";

	public static final String TEST_TIPO_COMPLETAR = "Completar";

	public static final String TEST_TIPO_ORDENAR = "Ordenar";

	public static final int TEST_NUM_ASIMPLE = 1;

	public static final int TEST_NUM_AMULTIPLE = 2;

	public static final int TEST_NUM_VF = 3;

	public static final int TEST_NUM_RELACIONAR = 4;

	public static final int TEST_NUM_COMPLETAR = 5;

	public static final int TEST_NUM_ORDENAR = 6;

	public static final char TEST_ITEM_CORRECTO = '1';

	public static final char TEST_ITEM_INCORRECTO = '0';

	public static final String TEST_ITEM_A = "A";

	public static final String TEST_ITEM_B = "B";

	public static final String TEST_ITEM_C = "C";

	public static final String TEST_ITEM_D = "D";

	public static final String TEST_ITEM_E = "E";

	public static final String TEST_ITEM_VF_V = "Verdadero";

	public static final String TEST_ITEM_VF_F = "Falso";

	public static final int TEST_CANTIDAD_PREGUNTAS = 10;

	/**
	 * Tamanioo para las herramientas del Aula Virtual
	 */
	public static final int TAMANIO_ARCHIVO_HERRAMIENTA = 2024;

	public static final String UNDERLINE = "_";

	public static final String HOME_ANT = RUTA_REPOSITORIO + SLASH + "ant";

	public static final String HOME_JASPER = RUTA_REPOSITORIO + SLASH
			+ "jasper";

	

	public static final String HOME_LABORATORIOS = RUTA_REPOSITORIO + SLASH
			+ "labs";

	public static final String HOME_NOTICIAS = RUTA_REPOSITORIO + SLASH
			+ "news";

	public static final String HOME_RECURSOS = RUTA_REPOSITORIO + SLASH
			+ "readings";

	public static final String HOME_TRABAJOS_GRUPALES = RUTA_REPOSITORIO
			+ SLASH + "workgrp";

	public static final String HOME_TRABAJOS = RUTA_REPOSITORIO + SLASH
			+ "works";

	/**
	 * FIN DE CONSTANTES PARA EL REPOSITORIO
	 */

	public static final String MAX_TAMANIO_ARCHIVO_RECURSOS = "5242880";

	public static final String TEXTO_PDF = "texto.pdf";

	

	public static final String LAB_PDF = "lab.pdf";

	public static final String EXTENSION_PDF = ".pdf";

	public static final String EXTENSION_ZIP = ".zip";

	public static final String HOME_REPASO = "Repaso_ON";

	public static final String HOME_REPASO_OFF = "Repaso_OFF";

	public static final String HOME_TEXTO = "Texto";

	public static final String HOME_TEST = "Test";

	public static final String HOME_VIDEO = "Video";

	public static final String HOME_TEXTO_FUENTES = "Texto_fuentes";

	public static final String HOME_REPASO_FUENTES = "Repaso_fuentes";

	public static final String HOME_LABORATORIO = "Lab";

	public static final String HOME_TRABAJO = "Trabajo";

	public static final String REPASO_EXE = "repaso.exe";

	public static final String EXTENSION_EXE = ".exe";

	public static final String EXTENSION_EXCEL = ".xls";

	public static final String TRABAJO_PDF = "trabajo.pdf";

	public static final String LABORATORIO_PDF = "lab.pdf";

	public static final String TRABAJO_GRUPAL = "TrabajoGrupal";

	/**
	 * archivo de Usuarios por defecto para matricular
	 */

	public static final String PERIODO_PCC = "C";

	public static final String PERIODO_PFR = "F";

	

	public static final String HERRAMIENTA_ID_MENSAJES = "1";

	public static final String HERRAMIENTA_ID_RECURSOS = "2";

	public static final String HERRAMIENTA_ID_CHAT = "3";

	public static final String HERRAMIENTA_ID_LABORATORIO_VIRTUAL = "4";

	public static final int RECURSOS_TOTAL = 12;

	public static final int HERRAMIENTAS_TOTAL = 4;

	public static final String DESHABILITADO_DOC = "DESHABILITADO DOCENTE";

	public static final String DESHABILITADO_ESTU = "DESHABILITADO ESTU";

	public static final int MAX_FILA_POR_PAG = 10;

	

}
