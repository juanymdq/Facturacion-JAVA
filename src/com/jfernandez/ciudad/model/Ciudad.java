package com.jfernandez.ciudad.model;

import com.jfernandez.provincia.model.Provincia;

public class Ciudad {

	private int id_ciudad;	
	private String nombre_ciudad;	
	private int id_provincia;
	private String nombre_provincia;
	
	//-----CONSTRUCTORES------------------------------------------------------------
	
	public Ciudad(int id_ciudad, String nombre_ciudad, int id_provincia) {		
		this.id_ciudad = id_ciudad;
		this.nombre_ciudad = nombre_ciudad;		
		this.id_provincia = id_provincia;
	}
	//Sobregarga de contructor para pasar el nombre de la provincia
	public Ciudad(int id_ciudad, String nombre_ciudad, String nombre_provincia) {		
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
	public int getId_provincia() {
		return id_provincia;
	}

	public void setId_provincia(int id_provincia) {
		this.id_provincia = id_provincia;
	}
	public String getNombre_provincia() {
		return nombre_provincia;
	}
	public void setNombre_provincia(String nombre_provincia) {
		this.nombre_provincia = nombre_provincia;
	}
}
