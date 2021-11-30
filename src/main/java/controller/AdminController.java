package controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.beans.Libro;
import model.beans.Tema;
import model.beans.Usuario;
import model.dao.IntLibro;
import model.dao.IntPerfiles;
import model.dao.IntTema;
import model.dao.IntUsuario;
import model.dao.LibroDAO;
import model.dao.PerfilDAO;
import model.dao.TemaDAO;
import model.dao.UserDAO;

/**
 * Servlet implementation class AdminController
 */
@WebServlet("/admin")
public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IntTema temaDao;
	private IntLibro libroDao;
	private IntPerfiles perfilDao;
	private IntUsuario udao;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminController() {
        super();
        temaDao = new TemaDAO();
        libroDao = new LibroDAO();
        perfilDao = new PerfilDAO();
        udao = new UserDAO();
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String option = request.getParameter("option");
		
		switch (option) {
		case "newTopic":
			addTopic(request,response);
			break;
		case "newBook":
			addBook(request,response);
			break;
		case "addAdmin":
			addAdmin(request,response);
			break;
		case "panel":
			panel(request,response);
			break;
		default:
			System.out.println("Opción errónea");
			break;
		}
	}
	
	protected void addTopic(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String topic = request.getParameter("topic");
		String symbol = request.getParameter("abrev");
		
		Tema newTopic = new Tema();
		newTopic.setAbreviatura(symbol);
		newTopic.setDescTema(topic);
		
		temaDao.insertTopic(newTopic);
		List<Tema> topics = temaDao.findTopics();
		request.setAttribute("listTopics", topics);
		request.getRequestDispatcher("panelAdministrador.jsp").forward(request, response);
		
	}
	
	protected void addBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String isbn = request.getParameter("isbn");
		String title = request.getParameter("title");
		String author = request.getParameter("author");
		String price = request.getParameter("price");
		String stock = request.getParameter("stock");
		String idTopic = request.getParameter("idTopic");
		
		Libro newBook = new Libro();
		
		newBook.setAutor(author);
		newBook.setIsbn(Long.parseLong(isbn));
		newBook.setPrecioUnitario(BigDecimal.valueOf(Double.parseDouble(price)));
		newBook.setStock(Integer.parseInt(stock));
		newBook.setTema(temaDao.findTopicById(idTopic));
		newBook.setTitulo(title);
		
		
		libroDao.insertBook(newBook);
		List<Tema> topics = temaDao.findTopics();
		request.setAttribute("listTopics", topics);
		request.getRequestDispatcher("panelAdministrador.jsp").forward(request, response);
			

	}
	
	protected void addAdmin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Usuario user= new Usuario();
		Date fecha = new Date();
		user.setNombre(request.getParameter("nombre"));
		user.setApellido(request.getParameter("apellidos"));
		user.setUsername(request.getParameter("username"));
		user.setPassword(request.getParameter("password"));
		user.setDireccion(request.getParameter("direccion"));
		user.setEmail(request.getParameter("email"));
		user.setFechaAlta(fecha);
		
		user.setPerfile(perfilDao.findById(1));
		int filas = udao.insertUser(user);
		List<Tema> topics = temaDao.findTopics();
		request.setAttribute("listTopics", topics);
		
		if (filas==1) {
			request.setAttribute("mensaje2", "Nuevo Administrador añadido");
			request.getRequestDispatcher("nuevoAdministrador.jsp").forward(request, response);
			
		}
		else {
			request.setAttribute("mensaje2", "No ha sido posible añadirlo");
			request.getRequestDispatcher("nuevoAdministrador.jsp").forward(request, response);
		}
		
	}
	
	protected void panel(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<Tema> topics = temaDao.findTopics();
		request.setAttribute("listTopics", topics);
		request.getRequestDispatcher("panelAdministrador.jsp").forward(request, response);
			

	}

}
