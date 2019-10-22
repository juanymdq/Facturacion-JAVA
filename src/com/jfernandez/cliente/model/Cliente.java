package com.jfernandez.cliente.model;

import java.util.Date;

import com.jfernandez.posiva.model.PosIva;

public class Cliente {

	private int id_cliente;
	private String nombre;
	private String apellido;
	private Date fecha_nacimiento;
	private String dni;
	private String domicilio;
	private String email;
	private String telefono;
	private PosIva posIva;
	private String cuit;
	
	public Cliente(int id_cliente, String nombre, String apellido, Date fecha_nacimiento, String dni, String domicilio,
			String email, String telefono, PosIva posIva, String cuit) {
		
		this.id_cliente = id_cliente;
		this.nombre = nombre;
		this.apellido = apellido;
		this.fecha_nacimiento = fecha_nacimiento;
		this.dni = dni;
		this.domicilio = domicilio;
		this.email = email;
		this.telefono = telefono;
		this.posIva = posIva;
		this.cuit = cuit;
	}

	public int getId_cliente() {
		return id_cliente;
	}

	public void setId_cliente(int id_cliente) {
		this.id_cliente = id_cliente;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public Date getFecha_nacimiento() {
		return fecha_nacimiento;
	}

	public void setFecha_nacimiento(Date fecha_nacimiento) {
		this.fecha_nacimiento = fecha_nacimiento;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public PosIva getPosIva() {
		return posIva;
	}

	public void setPosIva(PosIva posIva) {
		this.posIva = posIva;
	}

	public String getCuit() {
		return cuit;
	}

	public void setCuit(String cuit) {
		this.cuit = cuit;
	}
	
	
	
}
