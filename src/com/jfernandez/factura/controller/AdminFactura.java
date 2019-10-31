package com.jfernandez.factura.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.*;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jfernandez.articulo.dao.ArticuloDAO;
import com.jfernandez.articulo.model.Articulo;
import com.jfernandez.cliente.dao.ClienteDAO;
import com.jfernandez.cliente.model.Cliente;
import com.jfernandez.detalle_factura.dao.DetalleFacturaDAO;
import com.jfernandez.detalle_factura.model.DetalleFactura;
import com.jfernandez.factura.dao.FacturaDAO;
import com.jfernandez.factura.model.Factura;
import com.jfernandez.posiva.dao.PosIvaDAO;
import com.jfernandez.posiva.model.PosIva;

@WebServlet("/adminFactura")
public class AdminFactura extends HttpServlet {

	private static final long serialVersionUID = 1L;
	FacturaDAO facturaDAO;
	ClienteDAO clienteDAO;
	PosIvaDAO posivaDAO;
	ArticuloDAO articuloDAO;
	DetalleFacturaDAO detalleDAO;
	
	private PosIva posiva = null;
	private Cliente cliente = null;
	private DetalleFactura detalle = null;
	
	public void init() {		
		String jdbcURL = getServletContext().getInitParameter("jdbcURL");
		String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
		String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");
		try {
 
			clienteDAO = new ClienteDAO(jdbcURL, jdbcUsername, jdbcPassword);
			posivaDAO = new PosIvaDAO(jdbcURL, jdbcUsername, jdbcPassword);
			detalleDAO = new DetalleFacturaDAO(jdbcURL, jdbcUsername, jdbcPassword);
			facturaDAO = new FacturaDAO(jdbcURL, jdbcUsername, jdbcPassword);
			articuloDAO= new ArticuloDAO(jdbcURL, jdbcUsername, jdbcPassword);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	public AdminFactura() {
		super();
	}
 
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {	
		System.out.println("Servlet Factura doGet");
		String action = request.getParameter("action");		
		System.out.println(action);
		try {
			switch (action) {
			case "index":
				index(request, response);
				break;
			case "nuevo":
				nuevo(request, response);
				break;
			case "register":				
				registrar(request, response);
				break;
			case "mostrar":
				mostrar(request, response);
				break;
			case "showedit":
	//			showEditar(request, response);
				break;	
			case "editar":
	//			editar(request, response);
				break;
			case "eliminar":
				//eliminar(request, response);
				break;
			default:
				break;
			}			
		} catch (SQLException e) {
			e.getStackTrace();
		}
		
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Servlet Factura doPost");
		doGet(request, response);
	}
	
	private void index (HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		RequestDispatcher dispatcher= request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);
	}
 
	private void registrar(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException, SQLException {
		
		//----------FORMATO DE FECHA------------------------------------------
		SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
		
		Date fecha=null;
		
		try {
			fecha = formatoFecha.parse(request.getParameter("fecha"));
		} catch (ParseException e) {			
			e.printStackTrace();
		}
		//--------------------------------------------------------------------
		posiva = new PosIva(Integer.parseInt(request.getParameter("id_posiva")));
		cliente = new Cliente(Integer.parseInt(request.getParameter("id_cliente")));
		detalle = new DetalleFactura(Integer.parseInt(request.getParameter("id_detalle")));		
		Factura factura = new Factura(
				Integer.parseInt(request.getParameter("id_factura")),
				request.getParameter("tipo_factura"),
				fecha,
				cliente,
				posiva,
				request.getParameter("cond_venta"),
				detalle,
				Double.parseDouble(request.getParameter("total"))				
		);		
				
		facturaDAO.insertar(factura);
		mostrar(request, response);
	}
	
	//Deriva al formulario de altas
	private void nuevo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/vista/facturacion/facturacionAlta.jsp");
		//trae todos los clientes		
		List<Cliente>lClientes= clienteDAO.listarCliente();		
		request.setAttribute("listaCli", lClientes);
		for (Cliente cliente : lClientes) {
			System.out.println(cliente.getCiudad().getNombre_ciudad());
		}
		
		//trae todos los Posiva
		List<PosIva>lPosiva=posivaDAO.listarPosiva();
		request.setAttribute("listaPosiva", lPosiva);
		//trae todos los articulos para seleccionar en el detalle factura
		List<Articulo>lArticulos=articuloDAO.listarArticulo();
		request.setAttribute("listaArt", lArticulos);
		dispatcher.forward(request, response);
	}
	
	//muestra todas las facturas
	private void mostrar(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException , ServletException{
		RequestDispatcher dispatcher = request.getRequestDispatcher("/vista/facturacion/facturacionMostrar.jsp");
		//Trae todas las facturas
		List<Factura> listaFactura = facturaDAO.listarFactura();
		request.setAttribute("listaF", listaFactura);			
		dispatcher.forward(request, response);
	}
	
/*	private void showEditar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		
		Factura factura = facturaDAO(Integer.parseInt(request.getParameter("id")));
		
		//Paso los atributos de Cliente - id, nombre, apellido, direccion, ciudad
		List<Cliente>lcli = clienteDAO.listarCliente();
		request.setAttribute("id_cliente", factura.getCliente().getId_cliente());
		request.setAttribute("nombreC", factura.getCliente().getNombre());
		request.setAttribute("apellidoC", factura.getCliente().getApellido());
		request.setAttribute("domicilioC", factura.getCliente().getDomicilio());
		request.setAttribute("ciudadC", factura.getCliente().getCiudad().getNombre_ciudad());
		request.setAttribute("listaC", lcli);
		//Paso los atributos de IVA - id, nombre, porcentaje
		List<PosIva>liva = posivaDAO.listarPosiva();
		request.setAttribute("id_iva", factura.getPosiva().getId_posiva());
		request.setAttribute("nomiva", factura.getPosiva().getNombre_posiva());
		request.setAttribute("porcent", factura.getPosiva().getPorcentaje());
		request.setAttribute("listaI", liva);		
		
		request.setAttribute("factura", factura);		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/vista/factura/facturaEditar.jsp");
		dispatcher.forward(request, response);
	}
	
	private void editar(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		//----------FORMATO DE FECHA------------------------------------------
		SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
		
		Date fecha=null;
		
		try {
			fecha = formatoFecha.parse(request.getParameter("fecha"));
		} catch (ParseException e) {			
			e.printStackTrace();
		}
		//--------------------------------------------------------------------
		posiva = new PosIva(Integer.parseInt(request.getParameter("id_posiva")));
		cliente = new Cliente(Integer.parseInt(request.getParameter("id_cliente")));
		detalle = new DetalleFactura(Integer.parseInt(request.getParameter("id_detalle")));
		//ciudad = new Ciudad(Integer.parseInt(request.getParameter("id_ciudad")));
		Factura factura = new Factura(
				Integer.parseInt(request.getParameter("id_factura")),
				request.getParameter("tipo_factura"),
				fecha,
				cliente,
				posiva,
				request.getParameter("cond_venta"),
				detalle,
				Double.parseDouble(request.getParameter("total"))				
		);		
		
		if(facturaDAO.actualizar(factura)) {			
			String mensaje = "Se ha actualizado la factura: " + factura.getId_factura() + ". Con Fecha "
							+ factura.getFecha();
			request.setAttribute("mensaje", mensaje);
			mostrar(request, response);
		}
	}
	*/
	
}
