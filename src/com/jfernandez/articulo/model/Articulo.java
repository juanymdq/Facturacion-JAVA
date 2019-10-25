package com.jfernandez.articulo.model;

import java.util.Date;

import javax.validation.constraints.*;

import com.jfernandez.categoria.model.Categoria;

public class Articulo {	
	
	private int id_articulo;	
	private Categoria cat;
	private String nombre_articulo;
	private Date fecha_alta;
	private double precio;	
	private int cantidad;
	
	//--------CONSTRUCTORES---------------------------------------------------------------------------------
	public Articulo(int id_articulo, String nombre, double precio) {
		this.id_articulo=id_articulo;
		this.nombre_articulo=nombre;
		this.precio=precio;
	}
	
	
	public Articulo(int id_articulo,Categoria cat, String nombre_articulo, Date fecha_alta, double precio, int cantidad) {
		this.id_articulo = id_articulo;
		this.cat = cat;
		this.nombre_articulo = nombre_articulo;
		this.fecha_alta = fecha_alta;
		this.precio = precio;
		this.cantidad = cantidad;
	}
	
	//-------GETTERS AND SETTERS----------------------------------------------------------------------------
	
	public int getId_articulo() {
		return id_articulo;
	}
	public void setId_articulo(int id_articulo) {
		this.id_articulo = id_articulo;
	}
	public String getNombre_articulo() {
		return nombre_articulo;
	}
	public void setNombre_articulo(String nombre_articulo) {
		this.nombre_articulo = nombre_articulo;
	}
	public Date getFecha_alta() {
		return fecha_alta;
	}

	public void setFecha_alta(Date fecha_alta) {
		this.fecha_alta = fecha_alta;
	}	
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public Categoria getcat() {
		return cat;
	}

	public void setcat(Categoria cat) {
		this.cat = cat;
	}
	
	
	
}
