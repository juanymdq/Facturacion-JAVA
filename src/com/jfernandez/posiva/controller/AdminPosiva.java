package com.jfernandez.posiva.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
 
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jfernandez.posiva.dao.PosIvaDAO;
import com.jfernandez.posiva.model.PosIva;

/**
 * Servlet implementation class AdminArticulo
 */
@WebServlet("/adminPosiva")
public class AdminPosiva extends HttpServlet{

	private static final long serialVersionUID = 1L;
	PosIvaDAO posivaDAO;
 
	public void init() {
		String jdbcURL = getServletContext().getInitParameter("jdbcURL");
		String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
		String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");
		try {
 
			posivaDAO = new PosIvaDAO(jdbcURL, jdbcUsername, jdbcPassword);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
 
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminPosiva() {
		super();		
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
		System.out.println("Hola Servletv AdminPosiva");
		doGet(request, response);
	}
	
	private void index (HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		RequestDispatcher dispatcher= request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);
	}
 
	private void registrar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
			PosIva pos = new PosIva(
					Integer.parseInt(request.getParameter("id_posiva")),
					request.getParameter("nombre_posiva"),
					Double.parseDouble(request.getParameter("porcentaje")));
			posivaDAO.insertar(pos);
			mostrar(request, response);	
	}
	
	private void nuevo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/vista/posiva/posivaAlta.jsp");
		dispatcher.forward(request, response);
	}
	
	
	private void mostrar(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException , ServletException{
		RequestDispatcher dispatcher = request.getRequestDispatcher("/vista/posiva/posivaMostrar.jsp");
		List<PosIva> listaIva= posivaDAO.listarPosiva();
		request.setAttribute("listaI", listaIva);
		dispatcher.forward(request, response);
	}	
	
	private void showEditar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		PosIva pos = posivaDAO.obtenerPorId(Integer.parseInt(request.getParameter("id")));
		request.setAttribute("posicion", pos);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/vista/posiva/posivaEditar.jsp");
		dispatcher.forward(request, response);
	}
	
	private void editar(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		PosIva pos = new PosIva(
				Integer.parseInt(request.getParameter("id_posiva")),
				request.getParameter("nombre_posiva"),
				Double.parseDouble(request.getParameter("porcentaje")));
		if(posivaDAO.actualizar(pos)) mostrar(request, response);
	}
	
	private void eliminar(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		PosIva pos = posivaDAO.obtenerPorId(Integer.parseInt(request.getParameter("id")));
		if(!posivaDAO.eliminar(pos)){			
			String mensaje = "No se puede eliminar la posicion IVA" + pos.getNombre_posiva()  + " ya que tiene personas asociadas";
			request.setAttribute("mensaje", mensaje);
		}else{
			String mensaje = "La posicion IVA: " + pos.getNombre_posiva() + " se elimino correctamente";
			request.setAttribute("mensaje", mensaje);			
		}
		mostrar(request, response);
	}
}
