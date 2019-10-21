package com.jfernandez.provincia.model;

import javax.validation.constraints.*;

public class Provincia {

	@NotNull(message = "El id debe contener un valor")
	private int id_provincia;
	
	@NotNull
	@Size(min=2, message=" Campo requerido")
	private String nombre_provincia;
	
	//CONSTRUCTORES--------------------------------------------------------------------------
	public Provincia() {}
	
	public Provincia(int id_provincia, String nombre_provincia) {
		
		this.id_provincia = id_provincia;
		this.nombre_provincia = nombre_provincia;
	}
	
	//GETTERS AND SETTERS------------------------------------------------------------------
	
	
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
