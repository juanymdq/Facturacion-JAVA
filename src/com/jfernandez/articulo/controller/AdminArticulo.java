package com.jfernandez.articulo.controller;

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

import com.jfernandez.articulo.dao.ArticuloDAO;
import com.jfernandez.articulo.model.Articulo;
import com.jfernandez.categoria.dao.CategoriaDAO;
import com.jfernandez.categoria.model.Categoria;
import com.jfernandez.provincia.dao.ProvinciaDAO;


@WebServlet("/adminArticulo")
public class AdminArticulo extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	ArticuloDAO articuloDAO;
	CategoriaDAO categoriaDAO;
	private Categoria cat = null;
	
	public void init() {
		String jdbcURL = getServletContext().getInitParameter("jdbcURL");
		String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
		String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");
		try {
 
			articuloDAO = new ArticuloDAO(jdbcURL, jdbcUsername, jdbcPassword);
			categoriaDAO = new CategoriaDAO(jdbcURL, jdbcUsername, jdbcPassword);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
 
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminArticulo() {
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
		
		//----------FORMATO DE FECHA------------------------------------------
		SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
		
		Date fecha=null;
		
		try {
			fecha = formatoFecha.parse(request.getParameter("fecha_alta"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//--------------------------------------------------------------------
		cat = new Categoria(Integer.parseInt(request.getParameter("categoria")));
		Articulo articulo = new Articulo(
				Integer.parseInt(request.getParameter("id_articulo")),
				cat,
				request.getParameter("nombre_articulo"),
				fecha,			
				Double.valueOf(request.getParameter("precio")),
				Integer.parseInt(request.getParameter("cantidad")));			
			
		articuloDAO.insertar(articulo);
		mostrar(request, response);
				
		//RequestDispatcher dispatcher = request.getRequestDispatcher("/vista/mostrar.jsp");
		
		//dispatcher.forward(request, response);
	}
	//Deriva al formulario de altas
	private void nuevo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/vista/articulo/articuloAlta.jsp");
		List<Categoria>lCategorias = categoriaDAO.listarCategorias();		
		request.setAttribute("listaC", lCategorias);	
		dispatcher.forward(request, response);
	}
	
	//muestra todoa los articulos
	private void mostrar(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException , ServletException{
		RequestDispatcher dispatcher = request.getRequestDispatcher("/vista/articulo/articuloMostrar.jsp");
		List<Articulo> listaArticulos= articuloDAO.listarArticulo();
		request.setAttribute("lista", listaArticulos);
		dispatcher.forward(request, response);
	}	
	
	private void showEditar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		Articulo art = articuloDAO.obtenerPorId(Integer.parseInt(request.getParameter("id")));		
		request.setAttribute("articulos", art);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/vista/articulo/articuloEditar.jsp");
		dispatcher.forward(request, response);
	}
	
	private void editar(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		//----------FORMATO DE FECHA------------------------------------------
		SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
		
		Date fecha=null;
		
		try {
			fecha = formatoFecha.parse(request.getParameter("fecha_alta"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//--------------------------------------------------------------------
				
		Articulo articulo = new Articulo(
				Integer.parseInt(request.getParameter("id_articulo")),
				cat,
				request.getParameter("nombre_articulo"),
				fecha,				
				Double.parseDouble(request.getParameter("precio")),
				Integer.parseInt(request.getParameter("cantidad")));
		
		if(articuloDAO.actualizar(articulo)) {
			String mensaje = "Se ha actualizado el articulo: " + articulo.getNombre_articulo();
			request.setAttribute("mensaje", mensaje);
			mostrar(request, response);
		}
	}
	
	private void eliminar(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		Articulo art = articuloDAO.obtenerPorId(Integer.parseInt(request.getParameter("id")));
		articuloDAO.eliminar(art);
		String mensaje = "Se ha eliminado el articulo: " + art.getNombre_articulo();
		request.setAttribute("mensaje", mensaje);
		mostrar(request, response);		
	}

}
