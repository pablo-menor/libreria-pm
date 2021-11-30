package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.IntPerfiles;
import model.dao.IntTema;
import model.dao.IntUsuario;
import model.dao.PerfilDAO;
import model.dao.TemaDAO;
import model.dao.UserDAO;
import model.beans.Libro;
import model.beans.Perfile;
import model.beans.Tema;
import model.beans.Usuario;

/**
 * Servlet implementation class HomeController
 */
@WebServlet("/home")
public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IntUsuario udao;
	private IntPerfiles perfilDao;
	private IntTema temaDao;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HomeController() {
        super();
        udao = new UserDAO();
        perfilDao = new PerfilDAO();
        temaDao = new TemaDAO();
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String option = request.getParameter("option");
		if (option==null) {
			option = "loginPage";
		}
		switch (option) {
		case "loginPage":
			loginPage(request,response);
			break;
		case "signUpPage":
			signUpPage(request, response);
			break;
		case "login":
			checkLogin(request, response);
			break;
		case "signUp":
			signUp(request, response);
			break;
		case "signOut":
			signOut(request, response);
			break;
		case "topics":
			showTopics(request, response);
			break;
		default:
			System.out.println("Opción errónea");
			break;
		}
		
	}
	
	protected void loginPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.getRequestDispatcher("login.jsp").forward(request, response);
	}
	protected void signUpPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("registro.jsp").forward(request, response);
	}
	
	protected void checkLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String password = request.getParameter("password");
		String username= request.getParameter("username");
		
		Usuario user = udao.checkLogin(username, password);
		if (user!=null) {
			doLogin(request,response,user);
		}
		else {
			request.setAttribute("mensaje", "Usuario o contraseña incorrecto");
			loginPage(request, response);
		}

	}
	protected void signUp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Usuario user= new Usuario();
		Date fecha = new Date();
		user.setNombre(request.getParameter("nombre"));
		user.setApellido(request.getParameter("apellidos"));
		user.setUsername(request.getParameter("username"));
		user.setPassword(request.getParameter("password"));
		user.setDireccion(request.getParameter("direccion"));
		user.setEmail(request.getParameter("email"));
		user.setFechaAlta(fecha);
		/*user.setEnabled(1);*/
		//List <Perfile>  perfiles = new ArrayList<Perfile>();
		//perfiles.add(perfilDao.findById(1));
		user.setPerfile(perfilDao.findById(2));
		int filas = udao.insertUser(user);
		
		if (filas==1) {
			//request.getRequestDispatcher("temas.jsp").forward(request, response);
			doLogin(request,response,user);
			
		}
		else {
			request.setAttribute("mensaje", "El usuario ya existe");
			request.getRequestDispatcher("registro.jsp").forward(request, response);
		}

	}
	protected void doLogin(HttpServletRequest request, HttpServletResponse response,Usuario user) throws ServletException, IOException {
	
			request.getSession().setAttribute("user", user);
			request.getSession().setAttribute("mensaje",user.getUsername());
			List<Tema> topics = temaDao.findTopics();
			request.setAttribute("listTopics", topics);
			List<Libro> addedBooks = new ArrayList<Libro>();
			request.getSession().setAttribute("addedBooks",addedBooks);
			System.out.println(user.getPerfile().getIdPerfil());
			
			if (user.getPerfile().getIdPerfil()==2) {
				request.getRequestDispatcher("temas.jsp").forward(request, response);
			}
			else {
				request.setAttribute("listTopics", topics);
				request.getRequestDispatcher("panelAdministrador.jsp").forward(request, response);
			}
		

	}
	
	protected void signOut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.getSession().removeAttribute("user");
		request.getSession().invalidate();
		//request.setAttribute("mensaje", "Cerraste sesión");
		loginPage(request, response);
	}
	
	protected void showTopics(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<Tema> topics = temaDao.findTopics();
		request.setAttribute("listTopics", topics);
		request.getRequestDispatcher("temas.jsp").forward(request, response);

	}
	


}
