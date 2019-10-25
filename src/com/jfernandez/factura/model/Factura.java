package com.jfernandez.factura.model;

import java.util.Date;

import com.jfernandez.cliente.model.Cliente;
import com.jfernandez.detalle_factura.model.DetalleFactura;
import com.jfernandez.posiva.model.PosIva;

public class Factura {

	private int id_factura;
	private String tipo_factura;
	private Date fecha;
	private Cliente cliente;
	private PosIva posiva;
	private String cond_venta;
	private DetalleFactura detalle;
	private double total;
	
	public Factura(int id_factura, String tipo_factura, Date fecha, Cliente cliente, PosIva posiva, String cond_venta,
			DetalleFactura detalle, double total) {
		super();
		this.id_factura = id_factura;
		this.tipo_factura = tipo_factura;
		this.fecha = fecha;
		this.cliente = cliente;
		this.posiva = posiva;
		this.cond_venta = cond_venta;
		this.detalle = detalle;
		this.total = total;
	}

	public int getId_factura() {
		return id_factura;
	}

	public void setId_factura(int id_factura) {
		this.id_factura = id_factura;
	}

	public String getTipo_factura() {
		return tipo_factura;
	}

	public void setTipo_factura(String tipo_factura) {
		this.tipo_factura = tipo_factura;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public PosIva getPosiva() {
		return posiva;
	}

	public void setPosiva(PosIva posiva) {
		this.posiva = posiva;
	}

	public String getCond_venta() {
		return cond_venta;
	}

	public void setCond_venta(String cond_venta) {
		this.cond_venta = cond_venta;
	}

	public DetalleFactura getDetalle() {
		return detalle;
	}

	public void setDetalle(DetalleFactura detalle) {
		this.detalle = detalle;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}
	
	
}
