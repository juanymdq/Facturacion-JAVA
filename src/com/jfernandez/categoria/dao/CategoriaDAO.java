package com.jfernandez.categoria.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.jfernandez.categoria.model.Categoria;
import com.jfernandez.conexion.Conexion;

/*
 * @autor: Juany
 * @Fecha: 21/10/2019
 */
public class CategoriaDAO {

	private Conexion con;
	private Connection connection;
	
	public CategoriaDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) throws SQLException {
		
		System.out.println(jdbcURL);
		con = new Conexion(jdbcURL, jdbcUsername, jdbcPassword);
	}	
	// insertar Categoria
	public boolean insertar(Categoria cat) throws SQLException {
		String sql = "INSERT INTO categoria (id_categoria, nombre_categoria) VALUES (?, ?)";
		con.conectar();
		connection = con.getJdbcConnection();
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setInt(1, cat.getId_categoria());
		statement.setString(2, cat.getNombre_categoria());			
 
		boolean rowInserted = statement.executeUpdate() > 0;
		statement.close();
		con.desconectar();
		return rowInserted;
	}
	
	// listar todas las Categoria
	public List<Categoria> listarCategorias() throws SQLException {		
		List<Categoria> listaCategorias = new ArrayList<Categoria>();
		String sql = "SELECT * FROM categoria";
		con.conectar();
		connection = con.getJdbcConnection();
		Statement statement = connection.createStatement();
		ResultSet resulSet = statement.executeQuery(sql);
		
		while (resulSet.next()) {
			int id = resulSet.getInt("id_categoria");
			String nombre = resulSet.getString("nombre_categoria");				
			Categoria cat = new Categoria(id, nombre);
			listaCategorias.add(cat);		
		}
		con.desconectar();
		return listaCategorias;		
	}
	
	// obtener por id
	public Categoria obtenerPorId(int id) throws SQLException {
		Categoria cat = null;
 
		String sql = "SELECT * FROM categoria WHERE id_categoria= ? ";
		con.conectar();
		connection = con.getJdbcConnection();
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setInt(1, id);
 
		ResultSet res = statement.executeQuery();
		
		if (res.next()) {
			cat = new Categoria(res.getInt("id_categoria"), res.getString("nombre_categoria"));
		}
		res.close();
		con.desconectar();
 
		return cat;
	}
	
	// obtener el nombre de la Categoria por id
		public String obtieneNombreCategoria(int id) throws SQLException {
			String n_cat="";
	 
			String sql = "SELECT nombre_categoria FROM categoria WHERE id_categoria= ? ";
			con.conectar();
			connection = con.getJdbcConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, id);
	 
			ResultSet res = statement.executeQuery();
			
			if (res.next()) {
				n_cat = res.getString("nombre_categoria");
			}
			res.close();
			con.desconectar();
			
			return n_cat;
			
		}
	
	// actualizar
	public boolean actualizar(Categoria cat) throws SQLException {		
		boolean rowActualizar = false;
		String sql = "UPDATE categoria SET id_categoria=?,nombre_categoria=? WHERE id_categoria=?";
		
		con.conectar();
		connection = con.getJdbcConnection();
		System.out.println(connection);
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setInt(1, cat.getId_categoria());
		statement.setString(2, cat.getNombre_categoria());
		statement.setInt(3, cat.getId_categoria());
				
		rowActualizar = statement.executeUpdate() > 0;
		statement.close();
		con.desconectar();
		
		System.out.println("Actualizado");
		
		return rowActualizar;
	}
	
	//eliminar
	public boolean eliminar(Categoria cat) throws SQLException {
		boolean rowEliminar = false;
		String sql = "DELETE FROM categoria WHERE id_categoria=?";
		try{
			con.conectar();
			connection = con.getJdbcConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, cat.getId_categoria());
	 
			rowEliminar = statement.executeUpdate() > 0;
			
			statement.close();
			con.desconectar();
			
		}catch(SQLException e) {
            System.out.println(e);   
        }		
 
		return rowEliminar;
	}
	
}
