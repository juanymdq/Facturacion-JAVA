package com.jfernandez.posiva.model;

public class PosIva {

	private int id_posiva;
	private String nombre_posiva;
	private double porcentaje;
	
	//-----CONSTRUCTORES--------------------------------------------------------------
	
	public PosIva(int id_posiva) {
		this.id_posiva = id_posiva;
	}
	
	
	public PosIva(int id_posiva, String nombre_posiva, double porcentaje) {		
		this.id_posiva = id_posiva;
		this.nombre_posiva = nombre_posiva;
		this.porcentaje = porcentaje;
	}

	public int getId_posiva() {
		return id_posiva;
	}

	public void setId_posiva(int id_posiva) {
		this.id_posiva = id_posiva;
	}

	public String getNombre_posiva() {
		return nombre_posiva;
	}

	public void setNombre_posiva(String nombre_posiva) {
		this.nombre_posiva = nombre_posiva;
	}

	public double getPorcentaje() {
		return porcentaje;
	}

	public void setPorcentaje(double porcentaje) {
		this.porcentaje = porcentaje;
	}
	
	
	
}
