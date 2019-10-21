package com.jfernandez.provincia.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
 
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jfernandez.provincia.dao.ProvinciaDAO;
import com.jfernandez.provincia.model.Provincia;
 

/**
 * Servlet implementation class AdminArticulo
 */
@WebServlet("/adminProvincia")
public class AdminProvincia extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ProvinciaDAO provinciaDAO;
 
	public void init() {
		String jdbcURL = getServletContext().getInitParameter("jdbcURL");
		String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
		String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");
		try {
 
			provinciaDAO = new ProvinciaDAO(jdbcURL, jdbcUsername, jdbcPassword);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
 
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminProvincia() {
		super();
		// TODO Auto-generated constructor stub
	}
 
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Hola Servlet..");
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
			Provincia prov = new Provincia(Integer.parseInt(request.getParameter("id_provincia")), request.getParameter("nombre_provincia"));			
			provinciaDAO.insertar(prov);
			mostrar(request, response);	
	}
	
	private void nuevo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/vista/provincia/provinciaAlta.jsp");
		dispatcher.forward(request, response);
	}
	
	
	private void mostrar(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException , ServletException{
		RequestDispatcher dispatcher = request.getRequestDispatcher("/vista/provincia/provinciaMostrar.jsp");
		List<Provincia> listaProvincias= provinciaDAO.listarProvincias();
		request.setAttribute("lista", listaProvincias);
		dispatcher.forward(request, response);
	}	
	
	private void showEditar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		Provincia prov = provinciaDAO.obtenerPorId(Integer.parseInt(request.getParameter("id")));
		request.setAttribute("provincia", prov);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/vista/provincia/provinciaEditar.jsp");
		dispatcher.forward(request, response);
	}
	
	private void editar(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		Provincia prov = new Provincia(Integer.parseInt(request.getParameter("id_provincia")), request.getParameter("nombre_provincia"));
		if(provinciaDAO.actualizar(prov)) mostrar(request, response);
	}
	
	private void eliminar(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		Provincia prov = provinciaDAO.obtenerPorId(Integer.parseInt(request.getParameter("id")));
		if(!provinciaDAO.eliminar(prov)){			
			String mensaje = "No se puede eliminar la provincia " + prov.getNombre_provincia()  + " ya que tiene ciudades asociadas";
			request.setAttribute("mensaje", mensaje);
		}else{
			String mensaje = "Provincia: " + prov.getNombre_provincia() + " se elimino correctamente";
			request.setAttribute("mensaje", mensaje);
			//RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
			//dispatcher.forward(request, response);
		}
		mostrar(request, response);
	}
}
