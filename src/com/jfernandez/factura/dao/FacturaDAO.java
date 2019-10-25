package com.jfernandez.factura.dao;

import java.sql.*;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.*;

import com.jfernandez.articulo.model.Articulo;
import com.jfernandez.categoria.model.Categoria;
import com.jfernandez.ciudad.model.Ciudad;
import com.jfernandez.cliente.model.Cliente;
import com.jfernandez.conexion.Conexion;
import com.jfernandez.detalle_factura.model.DetalleFactura;
import com.jfernandez.factura.model.Factura;
import com.jfernandez.posiva.model.PosIva;


/*
 * @autor: Juany
 * @Fecha: 25/10/2019
 */
public class FacturaDAO {

	private Conexion con;
	private Connection connection;
	
	
	public FacturaDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) throws SQLException {
		
		System.out.println(jdbcURL);
		con = new Conexion(jdbcURL, jdbcUsername, jdbcPassword);		
	}
	
	// insertar
		public boolean insertar(Factura fact) throws SQLException {
			String sql = "INSERT INTO factura (id_factura, tipo_factura, fecha, id_cliente, "
						+ "id_posiva, cond_venta, id_detalle, total) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";		
			con.conectar();
			connection = con.getJdbcConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			
			statement.setInt(1, fact.getId_factura());
			statement.setString(2, fact.getTipo_factura());				
			//----conversion de fecha--------------------------------------------------------------------
			java.util.Date utilDate = fact.getFecha();		
			java.sql.Date fechaConvertida = new java.sql.Date(utilDate.getTime());		
			statement.setDate(3, fechaConvertida);
			//--------------------------------------------------------------------------------------------
			statement.setInt(4, fact.getCliente().getId_cliente());
			statement.setInt(5, fact.getPosiva().getId_posiva()); 
			statement.setString(6, fact.getCond_venta());
			statement.setInt(7, fact.getDetalle().getId_detalle());
			statement.setDouble(8, fact.getTotal());
			
			boolean rowInserted = statement.executeUpdate() > 0;		
			statement.close();
			con.desconectar();
			
			return rowInserted;
		}
		
		// listar todas las Facturas
		public List<Factura> listarFactura() throws SQLException { 
			List<Factura> listaFacturas = new ArrayList<Factura>();
			
			String sql = "SELECT * "
					+ "FROM factura as f " 
					+ "INNER JOIN detalle_factura as df"
					+ "INNER JOIN cliente as c "
					+ "INNER JOIN posiva as p "
					+ "INNER JOIN ciudad as ci "
					+ "ON f.id_detalle = df.id_detalle and f.id_cliente=c.id_cliente and "
					+ "f.id_posiva=p.posiva and c.id_ciudad=ci.id_ciudad";
			
			con.conectar();
			connection = con.getJdbcConnection();
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);
	 
			while (rs.next()) {			
				int id = rs.getInt("id_factura");
				String tipo = rs.getString("tipo_factura");
				Date fecha = rs.getDate("fecha");
				Ciudad objCiudad = new Ciudad(rs.getInt("id_ciudad"), rs.getString("nombre_ciudad"));
				Cliente objClie = new Cliente(
						rs.getInt("id_cliente"),
						rs.getString("nombre"),
						rs.getString("apellido"),
						rs.getString("domicilio"),
						objCiudad
				);
				PosIva objPosiva = new PosIva(
						rs.getInt("id_posiva"),
						rs.getString("nombre_posiva"),
						rs.getDouble("Porcentaje")
				);
				
				String condicion = rs.getString("cond_venta");
				
				Articulo objArt = new Articulo(
						rs.getInt("id_articulo"),
						rs.getString("nombre_articulo"),
						rs.getDouble("precio")
				);
				
				DetalleFactura objDetalle = new DetalleFactura(
						rs.getInt("id_detalle"),
						rs.getInt("cantidad"),
						objArt,
						rs.getDouble("importe")
				);
				
				Double total = rs.getDouble("total");
											
				Factura factura= new Factura(id, tipo, fecha, objClie, objPosiva, condicion, objDetalle, total);
				listaFacturas.add(factura);
			}
			con.desconectar();
			return listaFacturas;		
		}
		
		
}
