package com.jfernandez.ciudad.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
 
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jfernandez.ciudad.dao.CiudadDAO;
import com.jfernandez.ciudad.model.Ciudad;
import com.jfernandez.provincia.dao.ProvinciaDAO;
import com.jfernandez.provincia.model.Provincia;


/**
 * Servlet implementation class AdminArticulo
 */
@WebServlet("/adminCiudad")
public class AdminCiudad extends HttpServlet {

	private static final long serialVersionUID = 1L;
	CiudadDAO ciudadDAO;
	ProvinciaDAO provinciaDAO;
	
	public void init() {
		String jdbcURL = getServletContext().getInitParameter("jdbcURL");
		String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
		String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");
		try {
 
			ciudadDAO = new CiudadDAO(jdbcURL, jdbcUsername, jdbcPassword);
			provinciaDAO = new ProvinciaDAO(jdbcURL, jdbcUsername, jdbcPassword);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
 
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminCiudad() {
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
		System.out.println("Hola Servlet..");
		doGet(request, response);
	}
	
	private void index (HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		//mostrar(request, response);
		RequestDispatcher dispatcher= request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);
	}
 
	private void registrar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		Ciudad ciudad = new Ciudad(Integer.parseInt(request.getParameter("id_ciudad")), request.getParameter("nombre_ciudad"), Integer.parseInt(request.getParameter("id_provincia")));
		ciudadDAO.insertar(ciudad);
		mostrar(request, response);
		//RequestDispatcher dispatcher = request.getRequestDispatcher("/vista/mostrar.jsp");
		
		//dispatcher.forward(request, response);
	}
	//Deriva al formulario de altas con las provincias como atributos
	private void nuevo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/vista/ciudad/ciudadAlta.jsp");		
		List<Provincia>lProvincias = provinciaDAO.listarProvincias();		
		request.setAttribute("listap", lProvincias);	
		dispatcher.forward(request, response);
	}
	
	//muestra todas las ciudades enviandolas como parametros
	private void mostrar(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException , ServletException{
		RequestDispatcher dispatcher = request.getRequestDispatcher("/vista/ciudad/ciudadMostrar.jsp");
		List<Ciudad> listaCiudades= ciudadDAO.listarCiudades();
		request.setAttribute("lista", listaCiudades);
		dispatcher.forward(request, response);
	}	
	
	private void showEditar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		Ciudad ciudad = ciudadDAO.obtenerPorId(Integer.parseInt(request.getParameter("id")));
		List<Provincia>lProvincias = provinciaDAO.listarProvincias();		
		request.setAttribute("listap", lProvincias);
		request.setAttribute("ciudad", ciudad);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/vista/ciudad/ciudadEditar.jsp");
		dispatcher.forward(request, response);
	}
	
	private void editar(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		Ciudad ciudad = new Ciudad(
				Integer.parseInt(request.getParameter("id_ciudad")),
				request.getParameter("nombre_ciudad"),
				Integer.parseInt(request.getParameter("id_provincia")));
		
		if(ciudadDAO.actualizar(ciudad)) mostrar(request, response);
	}
	
	private void eliminar(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		Ciudad ciudad = ciudadDAO.obtenerPorId(Integer.parseInt(request.getParameter("id")));
		ciudadDAO.eliminar(ciudad);
		String mensaje = "Ciudad: " + ciudad.getNombre_ciudad() + " se elimino correctamente del sistema";
		request.setAttribute("mensaje", mensaje);
		mostrar(request, response);		
	}
}
