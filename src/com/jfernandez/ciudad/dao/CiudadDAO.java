package com.jfernandez.ciudad.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.jfernandez.ciudad.model.Ciudad;
import com.jfernandez.conexion.Conexion;
import com.jfernandez.provincia.dao.ProvinciaDAO;
import com.jfernandez.provincia.model.Provincia;


/*
 * @autor: Juany
 * @Fecha: 17/10/2019
 */
public class CiudadDAO {

	private Conexion con;
	private Connection connection;
	private ProvinciaDAO provDAO;
	private Provincia objProv=null;
	
	public CiudadDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) throws SQLException {
		
		System.out.println(jdbcURL);
		con = new Conexion(jdbcURL, jdbcUsername, jdbcPassword);
		try{
			provDAO = new ProvinciaDAO(jdbcURL, jdbcUsername, jdbcPassword);
		}catch(Exception e){
			e.printStackTrace();
		}
	}	
	// insertar ciudad
	public boolean insertar(Ciudad ciudad) throws SQLException {
		String sql = "INSERT INTO ciudad (id_ciudad, nombre_ciudad, cod_postal, id_provincia) VALUES (?, ?, ?, ?)";		
		con.conectar();
		connection = con.getJdbcConnection();
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setInt(1, ciudad.getId_ciudad());
		statement.setString(2, ciudad.getNombre_ciudad());
		statement.setString(3, ciudad.getCod_postal());
		statement.setInt(4, ciudad.getProvincia().getId_provincia());
 
		boolean rowInserted = statement.executeUpdate() > 0;
		statement.close();
		con.desconectar();
		return rowInserted;
	}
	
	// listar todas las ciudades
	public List<Ciudad> listarCiudades() throws SQLException { 
		List<Ciudad> listaCiudades = new ArrayList<Ciudad>();
		String sql = "SELECT * FROM ciudad as c INNER JOIN provincia as p ON " +
		"c.id_provincia=p.id_provincia ORDER BY c.id_ciudad";
		con.conectar();
		connection = con.getJdbcConnection();
		Statement statement = connection.createStatement();
		ResultSet resulSet = statement.executeQuery(sql);
 
		while (resulSet.next()) {
			int id = resulSet.getInt("id_ciudad");
			String nombre = resulSet.getString("nombre_ciudad");
			String codp = resulSet.getString("cod_postal");
			objProv = new Provincia(resulSet.getInt("id_provincia"), resulSet.getString("nombre_provincia"));				
			
			Ciudad ciudad = new Ciudad(id, nombre, codp, objProv);
			listaCiudades.add(ciudad);
		}
		con.desconectar();
		return listaCiudades;		
	}
	
	// obtener por id
	public Ciudad obtenerPorId(int id) throws SQLException {
		Ciudad ciudad = null;
 
		String sql = "SELECT * FROM ciudad as c INNER JOIN provincia as p ON " +
				" c.id_provincia=p.id_provincia and c.id_ciudad=?";
		con.conectar();
		connection = con.getJdbcConnection();
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setInt(1, id);
 
		ResultSet res = statement.executeQuery();
		
		if (res.next()) {
			objProv = new Provincia(res.getInt("id_provincia"), res.getString("nombre_provincia"));
			ciudad = new Ciudad(
					res.getInt("id_ciudad"), 
					res.getString("nombre_ciudad"),
					res.getString("cod_postal"),
					objProv
			);
		}
		res.close();
		con.desconectar();
 
		return ciudad;
	}
	
	// actualizar
	public boolean actualizar(Ciudad ciudad) throws SQLException {		
		boolean rowActualizar = false;
		String sql = "UPDATE ciudad SET id_ciudad=?,nombre_ciudad=?, cod_postal=?, id_provincia=? WHERE id_ciudad=?";
		
		con.conectar();
		connection = con.getJdbcConnection();
		
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setInt(1, ciudad.getId_ciudad());
		statement.setString(2, ciudad.getNombre_ciudad());
		statement.setString(3, ciudad.getCod_postal());
		statement.setInt(4, ciudad.getProvincia().getId_provincia());
		statement.setInt(5, ciudad.getId_ciudad());
				
		rowActualizar = statement.executeUpdate() > 0;
		statement.close();
		con.desconectar();	
		
		return rowActualizar;
	}
	
	//eliminar
	public boolean eliminar(Ciudad ciudad) throws SQLException {
		boolean rowEliminar = false;
		String sql = "DELETE FROM ciudad WHERE id_ciudad=?";
		con.conectar();
		connection = con.getJdbcConnection();
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setInt(1, ciudad.getId_ciudad());
 
		rowEliminar = statement.executeUpdate() > 0;
		statement.close();
		con.desconectar();
 
		return rowEliminar;
	}
}
