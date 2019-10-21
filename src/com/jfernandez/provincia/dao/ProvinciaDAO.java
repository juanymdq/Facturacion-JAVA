package com.jfernandez.provincia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.jfernandez.conexion.Conexion;
import com.jfernandez.provincia.model.Provincia;
 
/*
 * @autor: Juany
 * @Fecha: 17/10/2019
 */

public class ProvinciaDAO {

	private Conexion con;
	private Connection connection;
	
	public ProvinciaDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) throws SQLException {
		
		System.out.println(jdbcURL);
		con = new Conexion(jdbcURL, jdbcUsername, jdbcPassword);
	}	
	// insertar provincia
	public boolean insertar(Provincia prov) throws SQLException {
		String sql = "INSERT INTO provincia (id_provincia, nombre_provincia) VALUES (?, ?)";
		System.out.println(prov.getNombre_provincia());
		con.conectar();
		connection = con.getJdbcConnection();
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setInt(1, prov.getId_provincia());
		statement.setString(2, prov.getNombre_provincia());			
 
		boolean rowInserted = statement.executeUpdate() > 0;
		statement.close();
		con.desconectar();
		return rowInserted;
	}
	
	// listar todas las provincias
	public List<Provincia> listarProvincias() throws SQLException {
 
		List<Provincia> listaProvincias = new ArrayList<Provincia>();
		String sql = "SELECT * FROM provincia";
		con.conectar();
		connection = con.getJdbcConnection();
		Statement statement = connection.createStatement();
		ResultSet resulSet = statement.executeQuery(sql);
 
		while (resulSet.next()) {
			int id = resulSet.getInt("id_provincia");
			String nombre = resulSet.getString("nombre_provincia");				
			Provincia prov = new Provincia(id, nombre);
			listaProvincias.add(prov);
		}
		con.desconectar();
		return listaProvincias;		
	}
	
	// obtener por id
	public Provincia obtenerPorId(int id) throws SQLException {
		Provincia prov = null;
 
		String sql = "SELECT * FROM provincia WHERE id_provincia= ? ";
		con.conectar();
		connection = con.getJdbcConnection();
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setInt(1, id);
 
		ResultSet res = statement.executeQuery();
		
		if (res.next()) {
			prov = new Provincia(res.getInt("id_provincia"), res.getString("nombre_provincia"));
		}
		res.close();
		con.desconectar();
 
		return prov;
	}
	
	// obtener el nombre de la provincia por id
		public String obtieneNombreProvincia(int id) throws SQLException {
			String n_prov="";
	 
			String sql = "SELECT nombre_provincia FROM provincia WHERE id_provincia= ? ";
			con.conectar();
			connection = con.getJdbcConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, id);
	 
			ResultSet res = statement.executeQuery();
			
			if (res.next()) {
				n_prov = res.getString("nombre_provincia");
			}
			res.close();
			con.desconectar();
			
			return n_prov;
			
		}
	
	// actualizar
	public boolean actualizar(Provincia prov) throws SQLException {		
		boolean rowActualizar = false;
		String sql = "UPDATE provincia SET id_provincia=?,nombre_provincia=? WHERE id_provincia=?";
		
		con.conectar();
		connection = con.getJdbcConnection();
		System.out.println(connection);
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setInt(1, prov.getId_provincia());
		statement.setString(2, prov.getNombre_provincia());
		statement.setInt(3, prov.getId_provincia());
				
		rowActualizar = statement.executeUpdate() > 0;
		statement.close();
		con.desconectar();
		
		System.out.println("Actualizado");
		
		return rowActualizar;
	}
	
	//eliminar
	public boolean eliminar(Provincia prov) throws SQLException {
		boolean rowEliminar = false;
		String sql = "DELETE FROM provincia WHERE id_provincia=?";
		try{
			con.conectar();
			connection = con.getJdbcConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, prov.getId_provincia());
	 
			rowEliminar = statement.executeUpdate() > 0;
			
			statement.close();
			con.desconectar();
			
		}catch(SQLException e) {
            System.out.println(e);   
        }		
 
		return rowEliminar;
	}

}

