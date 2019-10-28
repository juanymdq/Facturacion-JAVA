package com.jfernandez.categoria.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
 
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jfernandez.categoria.dao.CategoriaDAO;
import com.jfernandez.categoria.model.Categoria;
import com.jfernandez.provincia.dao.ProvinciaDAO;
import com.jfernandez.provincia.model.Provincia;

@WebServlet("/adminCategoria")
public class AdminCategoria extends HttpServlet{

	private static final long serialVersionUID = 1L;
	CategoriaDAO categoriaDAO;
	
	public void init() {
		String jdbcURL = getServletContext().getInitParameter("jdbcURL");
		String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
		String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");
		try {
 
			categoriaDAO = new CategoriaDAO(jdbcURL, jdbcUsername, jdbcPassword);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
 
	public AdminCategoria() {
		super();		
	}
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Servlet Categoria doGet");
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
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Servlet Categoria doPost");
		doGet(request, response);
	}
	
	private void index (HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		RequestDispatcher dispatcher= request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);
	}
	
	private void registrar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {		
		Categoria cat = new Categoria(Integer.parseInt(request.getParameter("id_categoria")), request.getParameter("nombre_categoria"));			
		categoriaDAO.insertar(cat);
		int tipo = Integer.parseInt(request.getParameter("tipoGuardado"));
		if(tipo==1) {
			String mensaje = "Se agrego la categoria " + cat.getNombre_categoria();
			request.setAttribute("mensaje", mensaje);
			System.out.println(mensaje);
			response.sendRedirect("/vista/articulo/articuloAlta.jsp");
			//RequestDispatcher dispatcher = request.getRequestDispatcher("/vista/articulo/articuloAlta.jsp");
			//dispatcher.forward(request, response);
		}else {
			nuevo(request, response);
		}
	}

	private void nuevo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/vista/categoria/categoriaAlta.jsp");
		dispatcher.forward(request, response);
	}
	
	private void mostrar(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException , ServletException{
		RequestDispatcher dispatcher = request.getRequestDispatcher("/vista/categoria/categoriaMostrar.jsp");
		List<Categoria> listaCategorias= categoriaDAO.listarCategorias();
		request.setAttribute("listaC", listaCategorias);
		dispatcher.forward(request, response);
	}	
	
	private void showEditar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		Categoria cat = categoriaDAO.obtenerPorId(Integer.parseInt(request.getParameter("id")));
		request.setAttribute("categoria", cat);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/vista/categoria/categoriaEditar.jsp");
		dispatcher.forward(request, response);
	}
	
	private void editar(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		Categoria cat = new Categoria(Integer.parseInt(request.getParameter("id_categoria")), request.getParameter("nombre_categoria"));
		if(categoriaDAO.actualizar(cat)) mostrar(request, response);
	}
	
	private void eliminar(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		Categoria cat = categoriaDAO.obtenerPorId(Integer.parseInt(request.getParameter("id")));
		if(!categoriaDAO.eliminar(cat)){			
			String mensaje = "No se puede eliminar la categoria " + cat.getNombre_categoria()  + " ya que tiene articulos asociados";
			request.setAttribute("mensaje", mensaje);
		}else{
			String mensaje = "Categoria: " + cat.getNombre_categoria() + " se elimino correctamente";
			request.setAttribute("mensaje", mensaje);
		}
		mostrar(request, response);
	}
	
}
