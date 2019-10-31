package com.jfernandez.detalle_factura.model;

import com.jfernandez.articulo.model.Articulo;

public class DetalleFactura {

	private int id_detalle;
	private int cantidad;
	private Articulo articulo;	
	
	//---------CONTRUCTORES-------------------------------------------------------
	
	public DetalleFactura(int id_detalle) {
		this.id_detalle = id_detalle;
	}
	
	public DetalleFactura(int id_detalle, int cantidad, Articulo articulo) {
		super();
		this.id_detalle = id_detalle;
		this.cantidad = cantidad;
		this.articulo = articulo;	
	}
	
	//----GETTERS AND SETTERS---------------------------------------------------------
	
	public int getId_detalle() {
		return id_detalle;
	}
	public void setId_detalle(int id_detalle) {
		this.id_detalle = id_detalle;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public Articulo getArticulo() {
		return articulo;
	}
	public void setArticulo(Articulo articulo) {
		this.articulo = articulo;
	}	
	
	
}
