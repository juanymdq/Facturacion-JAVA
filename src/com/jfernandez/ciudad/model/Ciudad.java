package com.jfernandez.ciudad.model;

import com.jfernandez.provincia.model.Provincia;

public class Ciudad {

	private int id_ciudad;	
	private String nombre_ciudad;
	private String cod_postal;
	private Provincia provincia;
	
	//-----CONSTRUCTORES------------------------------------------------------------
	public Ciudad(int id_ciudad) {
		this.id_ciudad = id_ciudad;
	}
	
	
	public Ciudad(int id_ciudad, String nombre_ciudad, String cod_postal, Provincia provincia) {		
		this.id_ciudad = id_ciudad;
		this.nombre_ciudad = nombre_ciudad;		
		this.cod_postal = cod_postal;
		this.provincia = provincia;
	}
	//Sobregarga de contructor para pasar el nombre de la provincia
/*	public Ciudad(int id_ciudad, String nombre_ciudad, String nombre_provincia) {		
		this.id_ciudad = id_ciudad;
		this.nombre_ciudad = nombre_ciudad;		
		this.nombre_provincia = nombre_provincia;
	}
	//Sobregarga de contructor para pasar el id y nombre de la provincia para edicion
		public Ciudad(int id_ciudad, String nombre_ciudad, int id_provincia, String nombre_provincia) {		
			this.id_ciudad = id_ciudad;
			this.nombre_ciudad = nombre_ciudad;
			this.id_provincia = id_provincia;
			this.nombre_provincia = nombre_provincia;
		}
	*/
	//-----GETTERS AND SETTERS----------------------------------------------------

	public int getId_ciudad() {
		return id_ciudad;
	}

	public void setId_ciudad(int id_ciudad) {
		this.id_ciudad = id_ciudad;
	}

	public String getNombre_ciudad() {
		return nombre_ciudad;
	}

	public void setNombre_ciudad(String nombre_ciudad) {
		this.nombre_ciudad = nombre_ciudad;
	}

	public String getCod_postal() {
		return cod_postal;
	}

	public void setCod_postal(String cod_postal) {
		this.cod_postal = cod_postal;
	}

	public Provincia getProvincia() {
		return provincia;
	}

	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}

}
