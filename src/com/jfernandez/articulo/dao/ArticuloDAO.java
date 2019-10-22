package com.jfernandez.articulo.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.jfernandez.articulo.model.Articulo;
import com.jfernandez.categoria.model.Categoria;
import com.jfernandez.ciudad.model.Ciudad;
import com.jfernandez.conexion.Conexion;
import com.jfernandez.provincia.dao.ProvinciaDAO;

/*
 * @autor: Juany
 * @Fecha: 18/10/2019
 */
public class ArticuloDAO {

	private Conexion con;
	private Connection connection;
	private ProvinciaDAO provDAO;
	private Categoria objCat=null;
	
	public ArticuloDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) throws SQLException {
		
		System.out.println(jdbcURL);
		con = new Conexion(jdbcURL, jdbcUsername, jdbcPassword);		
	}	
	
	// insertar
	public boolean insertar(Articulo art) throws SQLException {
		String sql = "INSERT INTO articulos (id_articulo, id_categoria, nombre_articulo, fecha_alta, precio, cantidad) VALUES (?, ?, ?, ?, ?, ?)";		
		con.conectar();
		connection = con.getJdbcConnection();
		PreparedStatement statement = connection.prepareStatement(sql);
		
		statement.setInt(1, art.getId_articulo());
		statement.setInt(2, art.getcat().getId_categoria());
		statement.setString(3, art.getNombre_articulo());		
		//----conversion de fecha--------------------------------------------------------------------
		java.util.Date utilDate =art.getFecha_alta();		
		java.sql.Date fechaConvertida = new java.sql.Date(utilDate.getTime());		
		statement.setDate(4, fechaConvertida);
		//--------------------------------------------------------------------------------------------
		statement.setDouble(5, art.getPrecio());
		statement.setInt(6, art.getCantidad()); 
		
		boolean rowInserted = statement.executeUpdate() > 0;		
		statement.close();
		con.desconectar();
		
		return rowInserted;
	}
	
	// listar todos los articulos
	public List<Articulo> listarArticulo() throws SQLException { 
		List<Articulo> listaArticulos = new ArrayList<Articulo>();
		
		String sql = "SELECT * FROM articulos as a INNER JOIN categoria as c ON a.id_categoria=c.id_categoria";
		con.conectar();
		connection = con.getJdbcConnection();
		Statement statement = connection.createStatement();
		ResultSet rs = statement.executeQuery(sql);
 
		while (rs.next()) {			
			int id = rs.getInt("id_articulo");
			objCat = new Categoria(rs.getInt("id_categoria"), rs.getString("nombre_categoria"));
			String nombre = rs.getString("nombre_articulo");				
			Date fecha = rs.getDate("fecha_alta");
			double precio = rs.getDouble("precio");
			int cant = rs.getInt("cantidad");
			
			Articulo articulo = new Articulo(id, objCat, nombre, fecha, precio,cant);
			listaArticulos.add(articulo);
		}
		con.desconectar();
		return listaArticulos;		
	}
	
	// obtener por id
	public Articulo obtenerPorId(int id) throws SQLException {
		Articulo articulo = null;
 
		String sql = "SELECT * FROM articulos as a INNER JOIN categoria as c ON a.id_categoria=c.id_categoria and a.id_articulo=?";
		con.conectar();
		connection = con.getJdbcConnection();
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setInt(1, id);
		//System.out.println("obtener id: " + id);
		ResultSet res = statement.executeQuery();
		
		if (res.next()) {
			objCat=new Categoria(res.getInt("id_categoria"), res.getString("nombre_categoria"));
			articulo = new Articulo(res.getInt("id_articulo"), objCat, res.getString("nombre_articulo"), 
					res.getDate("fecha_alta"), res.getDouble("precio"), res.getInt("cantidad"));
		}
		res.close();
		con.desconectar();
 
		return articulo;
	}
	
	// actualizar
	public boolean actualizar(Articulo art) throws SQLException {			
		String sql = "UPDATE articulos SET id_articulo=?, id_categoria=?, nombre_articulo=?, fecha_alta=?, precio=?, cantidad=? WHERE id_articulo=?";		
		con.conectar();
		connection = con.getJdbcConnection();		
		PreparedStatement statement = connection.prepareStatement(sql);
		
		statement.setInt(1, art.getId_articulo());
		statement.setObject(2, art.getcat().getId_categoria());
		statement.setString(3, art.getNombre_articulo());		
		//----conversion de fecha--------------------------------------------------------------------
		java.util.Date utilDate =art.getFecha_alta();		
		java.sql.Date fechaConvertida = new java.sql.Date(utilDate.getTime());		
		statement.setDate(4, fechaConvertida);
		//--------------------------------------------------------------------------------------------
		statement.setDouble(5, art.getPrecio());
		statement.setInt(6, art.getCantidad());
		statement.setInt(7, art.getId_articulo());
	
		boolean rowActualizar = statement.executeUpdate() > 0;
		
		statement.close();
		con.desconectar();
						
		return rowActualizar;
	}
	
	//eliminar
	public boolean eliminar(Articulo art) throws SQLException {
		boolean rowEliminar = false;		
		String sql = "DELETE FROM articulos WHERE id_articulo=?";
		con.conectar();
		connection = con.getJdbcConnection();
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setInt(1, art.getId_articulo());
 
		rowEliminar = statement.executeUpdate() > 0;
		statement.close();
		con.desconectar();
 
		return rowEliminar;
	}
}
