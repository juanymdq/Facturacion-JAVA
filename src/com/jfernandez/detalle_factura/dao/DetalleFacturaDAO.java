package com.jfernandez.detalle_factura.dao;

import java.sql.*;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.*;

import com.jfernandez.articulo.model.Articulo;
import com.jfernandez.categoria.model.Categoria;
import com.jfernandez.conexion.Conexion;
import com.jfernandez.detalle_factura.model.DetalleFactura;

/*
 * @autor: Juany
 * @Fecha: 30/10/2019
 */
public class DetalleFacturaDAO {

	private Conexion con;
	private Connection connection;
	private DetalleFactura objDet=null;
	private Articulo objArt=null;
	
	public DetalleFacturaDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) throws SQLException {
		
		System.out.println(jdbcURL);
		con = new Conexion(jdbcURL, jdbcUsername, jdbcPassword);		
	}
	
	// insertar
	public boolean insertar(DetalleFactura det) throws SQLException {
		String sql = "INSERT INTO detalle_factura (id_detalle, cantdad, id_articulo) VALUES (?, ?, ?)";		
		con.conectar();
		connection = con.getJdbcConnection();
		PreparedStatement statement = connection.prepareStatement(sql);
		
		statement.setInt(1, det.getId_detalle());
		statement.setInt(2, det.getCantidad());	
		statement.setInt(3, det.getArticulo().getId_articulo());
				
		boolean rowInserted = statement.executeUpdate() > 0;		
		statement.close();
		con.desconectar();
		
		return rowInserted;
	}
	
	// listar todos los articulos
	public List<DetalleFactura> listarDetalle() throws SQLException { 
		List<DetalleFactura> listaDetalles = new ArrayList<DetalleFactura>();
		
		String sql = "SELECT * FROM detalle_factura as d INNER JOIN articulo as a ON a.id_articulo=d.id_articulo";
		con.conectar();
		connection = con.getJdbcConnection();
		Statement statement = connection.createStatement();
		ResultSet rs = statement.executeQuery(sql);
 
		while (rs.next()) {			
			int id_det = rs.getInt("id_detalle");			
			objArt = new Articulo(rs.getInt("id_articulo"));
			int cant = rs.getInt("cantidad");
			
			DetalleFactura objDet = new DetalleFactura(id_det,cant,objArt);
			listaDetalles.add(objDet);
		}
		con.desconectar();
		return listaDetalles;		
	}
	
	// obtener por id
	public DetalleFactura obtenerPorId(int id) throws SQLException { 
		String sql = "SELECT * FROM detalle_factura as d INNER JOIN articulo as a ON "
				+ "d.id_articulo=a.id_articulo and d.id_detalle=?";
		con.conectar();
		connection = con.getJdbcConnection();
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setInt(1, id);
		ResultSet res = statement.executeQuery();
		
		if (res.next()) {
			objArt=new Articulo(res.getInt("id_articulo"));
			objDet = new DetalleFactura(res.getInt("id_detalle"),res.getInt("cantidad"), objArt);
		}
		res.close();
		con.desconectar();
 
		return objDet;
	}
	
	// actualizar
	public boolean actualizar(DetalleFactura det) throws SQLException {			
		String sql = "UPDATE detalle_factura SET id_detalle=?, cantidad=?, id_articulo=? "
				+ "WHERE id_detalle=?";		
		con.conectar();
		connection = con.getJdbcConnection();		
		PreparedStatement statement = connection.prepareStatement(sql);
		
		statement.setInt(1, det.getId_detalle());
		statement.setInt(2, det.getCantidad());
		statement.setInt(3, det.getArticulo().getId_articulo());
		statement.setInt(4, det.getId_detalle());
			
		boolean rowActualizar = statement.executeUpdate() > 0;
		
		statement.close();
		con.desconectar();
						
		return rowActualizar;
	}
	
	//eliminar
	public boolean eliminar(DetalleFactura det) throws SQLException {
		boolean rowEliminar = false;		
		String sql = "DELETE FROM detalle_factura WHERE id_detalle=?";
		con.conectar();
		connection = con.getJdbcConnection();
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setInt(1, det.getId_detalle());
 
		rowEliminar = statement.executeUpdate() > 0;
		statement.close();
		con.desconectar();
 
		return rowEliminar;
	}
	
}
