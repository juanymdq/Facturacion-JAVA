package com.jfernandez.posiva.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.jfernandez.conexion.Conexion;
import com.jfernandez.posiva.model.PosIva;


/*
 * @autor: Juany
 * @Fecha: 23/10/2019
 */
public class PosIvaDAO {

	private Conexion con;
	private Connection connection;
	
	public PosIvaDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) throws SQLException {
		
		System.out.println(jdbcURL);
		con = new Conexion(jdbcURL, jdbcUsername, jdbcPassword);
	}	
	// insertar posiva
	public boolean insertar(PosIva pos) throws SQLException {
		String sql = "INSERT INTO posiva (id_posiva, nombre_posiva,porcentaje) VALUES (?, ?, ?)";		
		con.conectar();
		connection = con.getJdbcConnection();
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setInt(1, pos.getId_posiva());
		statement.setString(2, pos.getNombre_posiva());			
		statement.setDouble(3, pos.getPorcentaje());
 
		boolean rowInserted = statement.executeUpdate() > 0;
		statement.close();
		con.desconectar();
		return rowInserted;
	}
	
	// listar todas las posiciones iva
	public List<PosIva> listarPosiva() throws SQLException {
 
		List<PosIva> listaIva = new ArrayList<PosIva>();
		String sql = "SELECT * FROM posiva";
		con.conectar();
		connection = con.getJdbcConnection();
		Statement statement = connection.createStatement();
		ResultSet resulSet = statement.executeQuery(sql);
 
		while (resulSet.next()) {
			int id = resulSet.getInt("id_posiva");
			String nombre = resulSet.getString("nombre_posiva");
			Double porc = resulSet.getDouble("porcentaje");
			PosIva pos = new PosIva(id, nombre,porc);
			listaIva.add(pos);
		}
		con.desconectar();
		return listaIva;		
	}
	
	// obtener por id
	public PosIva obtenerPorId(int id) throws SQLException {
		PosIva pos = null;
 
		String sql = "SELECT * FROM posiva WHERE id_posiva= ? ";
		con.conectar();
		connection = con.getJdbcConnection();
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setInt(1, id);
 
		ResultSet res = statement.executeQuery();
		
		if (res.next()) {
			pos = new PosIva(res.getInt("id_posiva"), res.getString("nombre_posiva"), res.getDouble("porcentaje"));
		}
		res.close();
		con.desconectar();
 
		return pos;
	}
	
	// obtener el nombre de la poscicion por id
		public String obtieneNombrePosiciocn(int id) throws SQLException {
			String n_pos="";
	 
			String sql = "SELECT nombre_posiva FROM posiva WHERE id_posiva= ? ";
			con.conectar();
			connection = con.getJdbcConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, id);
	 
			ResultSet res = statement.executeQuery();
			
			if (res.next()) {
				n_pos = res.getString("nombre_posiva");
			}
			res.close();
			con.desconectar();
			
			return n_pos;
			
		}
	
	// actualizar
	public boolean actualizar(PosIva pos) throws SQLException {		
		boolean rowActualizar = false;
		String sql = "UPDATE posiva SET id_posiva=?,nombre_posiva=?, porcentaje=? WHERE id_posiva=?";
		
		con.conectar();
		connection = con.getJdbcConnection();
		System.out.println(connection);
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setInt(1, pos.getId_posiva());
		statement.setString(2, pos.getNombre_posiva());
		statement.setDouble(3, pos.getPorcentaje());
		statement.setInt(4, pos.getId_posiva());		
		
		rowActualizar = statement.executeUpdate() > 0;
		statement.close();
		con.desconectar();
		
		System.out.println("Actualizado");
		
		return rowActualizar;
	}
	
	//eliminar
	public boolean eliminar(PosIva pos) throws SQLException {
		boolean rowEliminar = false;
		String sql = "DELETE FROM posiva WHERE id_posiva=?";
		try{
			con.conectar();
			connection = con.getJdbcConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, pos.getId_posiva());
	 
			rowEliminar = statement.executeUpdate() > 0;
			
			statement.close();
			con.desconectar();
			
		}catch(SQLException e) {
            System.out.println(e);   
        }		
 
		return rowEliminar;
	}

}
