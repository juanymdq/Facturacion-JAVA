package com.jfernandez.cliente.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
 
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jfernandez.cliente.dao.ClienteDAO;
import com.jfernandez.cliente.model.Cliente;
import com.jfernandez.posiva.model.PosIva;

@WebServlet("/adminCliente")
public class AdminCliente {

	private static final long serialVersionUID = 1L;
	ClienteDAO clienteDAO;
	PosIvaDAO posivaDAO;
	private PosIva posiva = null;
	
	public void init() {
		String jdbcURL = getServletContext().getInitParameter("jdbcURL");
		String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
		String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");
		try {
 
			clienteDAO = new ClienteDAO(jdbcURL, jdbcUsername, jdbcPassword);
			posivaDAO = new PosIvaDAO(jdbcURL, jdbcUsername, jdbcPassword);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
 
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminCliente() {
		super();
		// TODO Auto-generated constructor stub
	}
 
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {		
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
				showEditar(request, response);
				break;	
			case "editar":
				editar(request, response);
				break;
			case "eliminar":
				eliminar(request, response);
				break;
			default:
				break;
			}			
		} catch (SQLException e) {
			e.getStackTrace();
		}
		
	}
 
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Hola Servlet Cliente");
		doGet(request, response);
	}
	
	private void index (HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		//mostrar(request, response);
		RequestDispatcher dispatcher= request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);
	}
 
	private void registrar(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException, SQLException {
		
		//----------FORMATO DE FECHA------------------------------------------
		SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
		
		Date fecha=null;
		
		try {
			fecha = formatoFecha.parse(request.getParameter("fecha_nacimiento"));
		} catch (ParseException e) {			
			e.printStackTrace();
		}
		//--------------------------------------------------------------------
		posiva = new PosIva(Integer.parseInt(request.getParameter("id_posiva")));
		Cliente cliente = new Cliente(
				Integer.parseInt(request.getParameter("id_cliente")),				
				request.getParameter("nombre"),
				request.getParameter("apellido"),
				fecha,
				request.getParameter("dni"),
				request.getParameter("domicilio"),
				request.getParameter("emil"),
				request.getParameter("telefono"),
				posiva,
				request.getParameter("cuit"));
			
		clienteDAO.insertar(cliente);
		mostrar(request, response);
				
		//RequestDispatcher dispatcher = request.getRequestDispatcher("/vista/mostrar.jsp");
		
		//dispatcher.forward(request, response);
	}
	//Deriva al formulario de altas
	private void nuevo(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException, SQLException {
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/vista/cliente/clienteAlta.jsp");
		List<PosIva>liva = posivaDAO.listarPosiva();		
		request.setAttribute("listaIva", liva);	
		dispatcher.forward(request, response);
	}
	
	//muestra todoa los clientes
	private void mostrar(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException , ServletException{
		RequestDispatcher dispatcher = request.getRequestDispatcher("/vista/cliente/clienteMostrar.jsp");
		List<Cliente> listaClientes= clienteDAO.listarCliente();
		request.setAttribute("listaCli", listaClientes);
		dispatcher.forward(request, response);
	}	
	
	private void showEditar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		
		Cliente cli = clienteDAO.obtenerPorId(Integer.parseInt(request.getParameter("id")));
		
		List<PosIva>liva = posivaDAO.listarIva();
		request.setAttribute("id_iva", cli.getPosIva().getId_posiva());
		request.setAttribute("nomiva", cli.getPosIva().getNombre_posiva());
		request.setAttribute("porcent", cli.getPosIva().getPorcentaje());
		request.setAttribute("listaI", liva);
		request.setAttribute("cliente", cli);		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/vista/cliente/clienteEditar.jsp");
		dispatcher.forward(request, response);
	}
	
	private void editar(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		//----------FORMATO DE FECHA------------------------------------------
		SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
		
		Date fecha=null;
		
		try {
			fecha = formatoFecha.parse(request.getParameter("fecha_nacimiento"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//--------------------------------------------------------------------
				
		Cliente cliente = new Cliente(
				Integer.parseInt(request.getParameter("id_cliente")),				
				request.getParameter("nombre"),
				request.getParameter("apellido"),
				fecha,
				request.getParameter("dni"),
				request.getParameter("domicilio"),
				request.getParameter("emil"),
				request.getParameter("telefono"),
				posiva,
				request.getParameter("cuit")
		);
		
		if(clienteDAO.actualizar(cliente)) {
			String mensaje = "Se ha actualizado el cliente: " + cliente.getNombre() + " " + cliente.getApellido();
			request.setAttribute("mensaje", mensaje);
			mostrar(request, response);
		}
	}
	
	private void eliminar(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{		
		Cliente cli = clienteDAO.obtenerPorId(Integer.parseInt(request.getParameter("id")));
		clienteDAO.eliminar(cli);
		String mensaje = "Se ha eliminado el cliente: " + cli.getNombre() + " " + cli.getApellido();
		request.setAttribute("mensaje", mensaje);
		mostrar(request, response);		
	}

}
