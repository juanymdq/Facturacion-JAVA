package com.jfernandez.cliente.dao;

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
import com.jfernandez.cliente.model.Cliente;
import com.jfernandez.conexion.Conexion;
import com.jfernandez.posiva.dao.PosIvaDAO;
import com.jfernandez.posiva.model.PosIva;
import com.jfernandez.provincia.dao.ProvinciaDAO;
import com.jfernandez.provincia.model.Provincia;

/*
 * @autor: Juany
 * @Fecha: 22/10/2019
 */
public class ClienteDAO {

	private Conexion con;
	private Connection connection;
	private PosIvaDAO posivaDAO;
	private PosIva objIva=null;
	private Ciudad objCiudad=null;
	private Provincia objProv=null;
	
	public ClienteDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) throws SQLException {
		
		System.out.println(jdbcURL);
		con = new Conexion(jdbcURL, jdbcUsername, jdbcPassword);		
	}	
	
	// insertar
	public boolean insertar(Cliente cli) throws SQLException {
		String sql = "INSERT INTO cliente (id_cliente, nombre, apellido, fecha_nacimiento, dni, "
				+ "domicilio, email, telefono, id_posiva, id_ciudad, cuit) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";		
		con.conectar();
		connection = con.getJdbcConnection();
		PreparedStatement statement = connection.prepareStatement(sql);
			
		statement.setInt(1, cli.getId_cliente());
		statement.setString(2, cli.getNombre());
		statement.setString(3, cli.getApellido());		
		//----conversion de fecha--------------------------------------------------------------------
		java.util.Date utilDate =cli.getFecha_nacimiento();		
		java.sql.Date fechaConvertida = new java.sql.Date(utilDate.getTime());		
		statement.setDate(4, fechaConvertida);
		//--------------------------------------------------------------------------------------------
		statement.setString(5, cli.getDni());
		statement.setString(6, cli.getDomicilio()); 
		statement.setString(7, cli.getEmail());
		statement.setString(8, cli.getTelefono());
		statement.setInt(9, cli.getPosIva().getId_posiva());
		statement.setInt(10, cli.getCiudad().getId_ciudad());
		statement.setString(11, cli.getCuit());
		
		System.out.println("pos " + cli.getPosIva().getId_posiva());
		System.out.println("ciu " + cli.getCiudad().getId_ciudad());
		boolean rowInserted = statement.executeUpdate() > 0;		
		statement.close();
		con.desconectar();
		
		return rowInserted;
	}
	
	// listar todos los articulos
	public List<Cliente> listarCliente() throws SQLException { 
		List<Cliente> listaClientes = new ArrayList<Cliente>();
		String sql =
		"SELECT * " +
		"FROM cliente as cl " +
		"INNER JOIN posiva as p ON cl.id_posiva=p.id_posiva " +
		"INNER JOIN ciudad as c ON cl.id_ciudad=c.id_ciudad";
		
		//String sql = "SELECT * FROM cliente as c INNER JOIN posiva as p ON c.id_posiva=p.id_posiva";
		con.conectar();
		connection = con.getJdbcConnection();
		Statement statement = connection.createStatement();
		ResultSet rs = statement.executeQuery(sql);
 
		while (rs.next()) {			
			int id = rs.getInt("id_cliente");			
			String nombre = rs.getString("nombre");				
			String apellido = rs.getString("apellido");
			Date fecha_nac = rs.getDate("fecha_nacimiento");
			String dni = rs.getString("dni");
			String domicilio = rs.getString("domicilio");
			String email  = rs.getString("email");
			String telefono = rs.getString("telefono");
			objIva = new PosIva(rs.getInt("id_posiva"), rs.getString("nombre_posiva"),rs.getDouble("porcentaje"));
			objProv = new Provincia(rs.getInt("id_provincia"));
			objCiudad = new Ciudad(
					rs.getInt("id_ciudad"),
					rs.getString("nombre_ciudad"),
					rs.getString("cod_poastal"),
					objProv
			);
			String cuit = rs.getString("cuit");
			
			Cliente cliente = new Cliente(
					id,
					nombre,
					apellido,
					fecha_nac,
					dni,
					domicilio,
					email,
					telefono,
					objIva,
					objCiudad,
					cuit
			);
			
			listaClientes.add(cliente);
		}
		con.desconectar();
		return listaClientes;		
	}
	
	// obtener por id
	public Cliente obtenerPorId(int id) throws SQLException {
		Cliente cliente = null;
 
		String sql = "SELECT * "
				+ "FROM cliente as c "
				+ "INNER JOIN posiva as p ON c.id_posiva=p.id_pociva "
				+ "INNER JOIN ciudad as ci ON c.id_ciudad=ci.id_ciudad"
				+ "and c.id_cliente=?";
		
		con.conectar();
		connection = con.getJdbcConnection();
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setInt(1, id);
		ResultSet rs = statement.executeQuery();
		
		if (rs.next()) {
			id = rs.getInt("id_cliente");			
			String nombre = rs.getString("nombre");				
			String apellido = rs.getString("apellido");
			Date fecha_nac = rs.getDate("fecha_nacimiento");
			String dni = rs.getString("dni");
			String domicilio = rs.getString("domicilio");
			String email  = rs.getString("email");
			String telefono = rs.getString("telefono");
			objIva=new PosIva(rs.getInt("id_poaiva"), rs.getString("nombre_posiva"), rs.getDouble("porcentaje"));
			objProv = new Provincia(rs.getInt("id_provincia"));
			objCiudad = new Ciudad(
					rs.getInt("id_ciudad"),
					rs.getString("nombre_ciudad"),
					rs.getString("cod_poastal"),
					objProv
			);
			String cuit = rs.getString("cuit");
			
			cliente = new Cliente(
					id,
					nombre,
					apellido,
					fecha_nac,
					dni,
					domicilio,
					email,
					telefono,
					objIva,
					objCiudad,
					cuit
			);
		}
		
		rs.close();
		con.desconectar();
 
		return cliente;
	}
	
	// actualizar
	public boolean actualizar(Cliente cli) throws SQLException {			
		String sql = "UPDATE clientes SET id_cliente=?, nombre=?, apellido=?, fecha_nacimiento=?, dni=?,"
				+ " domicilio=?, email=?, telefono=?, id_posiva=?, id_ciudad=? cuit=? WHERE id_cliente=?";		
		con.conectar();
		connection = con.getJdbcConnection();		
		PreparedStatement statement = connection.prepareStatement(sql);
		
		statement.setInt(1, cli.getId_cliente());
		statement.setString(2, cli.getNombre());
		statement.setString(3, cli.getApellido());		
		//----conversion de fecha--------------------------------------------------------------------
		java.util.Date utilDate =cli.getFecha_nacimiento();		
		java.sql.Date fechaConvertida = new java.sql.Date(utilDate.getTime());		
		statement.setDate(4, fechaConvertida);
		//--------------------------------------------------------------------------------------------
		statement.setString(5, cli.getDni());
		statement.setString(6, cli.getDomicilio()); 
		statement.setString(7, cli.getEmail());
		statement.setString(8, cli.getTelefono());
		statement.setInt(9, cli.getPosIva().getId_posiva());
		statement.setInt(10, cli.getCiudad().getId_ciudad());
		statement.setString(11, cli.getCuit());
	
		boolean rowActualizar = statement.executeUpdate() > 0;
		
		statement.close();
		con.desconectar();
						
		return rowActualizar;
	}
	
	//eliminar
	public boolean eliminar(Cliente cli) throws SQLException {
		boolean rowEliminar = false;		
		String sql = "DELETE FROM cliente WHERE id_cliente=?";
		con.conectar();
		connection = con.getJdbcConnection();
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setInt(1, cli.getId_cliente());
 
		rowEliminar = statement.executeUpdate() > 0;
		statement.close();
		con.desconectar();
 
		return rowEliminar;
	}
}
