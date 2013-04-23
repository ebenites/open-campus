package edu.opencampus.lms.modelo.usuario;

import java.io.Serializable;
import java.util.GregorianCalendar;

import edu.opencampus.lms.modelo.BaseModelo;
import edu.opencampus.lms.util.Constante;
import edu.opencampus.lms.util.Formato;

public class Persona extends BaseModelo implements Serializable {

	@Override
	public String toString() {
		String cadena = "";
		cadena += "\n\t\t nombre: "+getNombreCompleto();
		cadena += "\n\t\t fecnacimiento: "+fecnacimiento;
		cadena += "\n\t\t sexo: "+getSexoCompleto();
		cadena += "\n\t\t email: "+email;
		return cadena;
	}
	private static final long serialVersionUID = 4357537708202181416L;

	private String nomuno; 
	private String nomdos; 
	private String apepaterno; 
	private String apematerno; 
	private GregorianCalendar fecnacimiento;
	private String sexo;
	private String dirdomicilio; 
	private String teldomicilio; 
	private String telcelular;
	private String email;
	private String dni;
	private Ubigeo ubigeo = new Ubigeo();
	private String nombreCompleto;
	private String nombreCorto;
	private String sexoCompleto;
	
	public Persona() {
	}
	
	public Persona(String nomuno, String nomdos, String apepaterno, String apematerno,String sexo) {
		this.nomuno = nomuno;
		this.nomdos = nomdos;
		this.apepaterno = apepaterno;
		this.apematerno = apematerno;
		this.sexo = sexo;
	}

	public String getNomuno() {
		return nomuno;
	}
	public void setNomuno(String nomuno) {
		this.nomuno = nomuno;
	}
	public String getNomdos() {
		return nomdos;
	}
	public void setNomdos(String nomdos) {
		this.nomdos = nomdos;
	}
	public String getApepaterno() {
		return apepaterno;
	}
	public void setApepaterno(String apepaterno) {
		this.apepaterno = apepaterno;
	}
	public String getApematerno() {
		return apematerno;
	}
	public void setApematerno(String apematerno) {
		this.apematerno = apematerno;
	}
	public String getFecnacimientoAsString() {
		return Formato.calendarToString(fecnacimiento, Constante.FECHA_DIA_MES_ANO);
	}
	public GregorianCalendar getFecnacimiento() {
		return fecnacimiento;
	}
	public void setFecnacimiento(GregorianCalendar fecnacimiento) {
		this.fecnacimiento = fecnacimiento;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		if(sexo != null)sexo.toUpperCase();
		this.sexo = sexo;
	}
	public String getDirdomicilio() {
		return dirdomicilio;
	}
	public void setDirdomicilio(String dirdomicilio) {
		this.dirdomicilio = dirdomicilio;
	}
	public String getTeldomicilio() {
		return teldomicilio;
	}
	public void setTeldomicilio(String teldomicilio) {
		this.teldomicilio = teldomicilio;
	}
	public String getTelcelular() {
		return telcelular;
	}
	public void setTelcelular(String telcelular) {
		this.telcelular = telcelular;
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
	public Ubigeo getUbigeo() {
		return ubigeo;
	}
	public void setUbigeo(Ubigeo ubigeo) {
		this.ubigeo = ubigeo;
	}
	public String getNombreCompleto() {
		setNombreCompleto(/*Formato.formatoTexto*/(this.apepaterno + " " + this.apematerno + ", " + this.nomuno + " " + this.nomdos));
		return nombreCompleto;
	}
	private void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}
	public String getNombreCorto() {
		setNombreCorto(Formato.formatoTexto(this.nomuno + " " + this.apepaterno + " " + ((this.apematerno!=null)?this.apematerno.substring(0, 1):"")));
		return nombreCorto;
	}
	private void setNombreCorto(String nombreCorto) {
		this.nombreCorto = nombreCorto;
	}
	public String getSexoCompleto() {
		if(Constante.SEXO_MASCULINO.equals(this.sexo))
			setSexoCompleto("Masculino");
		else if(Constante.SEXO_FEMENINO.equals(this.sexo))
			setSexoCompleto("Femenino");
		else if(Constante.SEXO_SAKO.equals(this.sexo))
			setSexoCompleto("Raro");
		else
			setSexoCompleto("No determinado");
		
		return sexoCompleto;
	}
	private void setSexoCompleto(String sexoCompleto) {
		this.sexoCompleto = sexoCompleto;
	}
	
	
	
}
