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

import model.beans.Libro;
import model.beans.LineaPedido;
import model.beans.Pedido;
import model.beans.Tema;
import model.beans.Usuario;
import model.dao.IntLibro;
import model.dao.IntPedido;
import model.dao.IntTema;
import model.dao.LibroDAO;
import model.dao.PedidoDAO;
import model.dao.TemaDAO;

/**
 * Servlet implementation class BooksController
 */
@WebServlet("/books")
public class BooksController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IntLibro libroDAO;
	private IntTema temaDao;
	private IntPedido pedidoDao;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BooksController() {
        super();
       libroDAO = new LibroDAO();
       temaDao = new TemaDAO();
       pedidoDao = new PedidoDAO();
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
		case "topic":
			showBooks(request,response);
			break;
		case "addToCart":
			addBooksToCart(request,response);
			break;
		case "removeFromCart":
			removeFromCart(request, response);
			break;
		case "cart":
			cart(request,response);
			break;
		case "emptyCart":
			emptyCart(request,response);
			break;
		case "buyCart":
			buyCart(request,response);
			break;
		default:
			System.out.println("Opción errónea");
			break;
		}
	}
	
	protected void showBooks(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String topic = request.getParameter("topic");
		List<Libro> books = libroDAO.findBooksByTopic(topic);
		request.setAttribute("listBooks", books);
		request.getRequestDispatcher("libros.jsp").forward(request, response);
	}
	
	protected void addBooksToCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] isbns = request.getParameterValues("isbn");
		List<Libro> addedBooks = new ArrayList<Libro>();
	
		for(String e:isbns) {
			addedBooks.add(libroDAO.findBookByIsbn(e));
		}
		
		if (request.getSession().getAttribute("addedBooks")==null) {
			request.getSession().setAttribute("addedBooks",addedBooks);
		}
		else {
			List<Libro> cartList= (List<Libro>) request.getSession().getAttribute("addedBooks");
			for(Libro e:addedBooks) {
				if (!cartList.contains(e)) {
					cartList.add(e);
				}
				
			}
			request.getSession().setAttribute("addedBooks",cartList);
		}
		
		
		
		List<Tema> topics = temaDao.findTopics();
		request.setAttribute("listTopics", topics);
		request.getRequestDispatcher("temas.jsp").forward(request, response);
	}
	
	protected void removeFromCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String isbn = request.getParameter("isbn");
		List<Libro> cartList= (List<Libro>) request.getSession().getAttribute("addedBooks");
		Libro book = libroDAO.findBookByIsbn(isbn);
		cartList.remove(book);
		for(Libro e:cartList) {
			if (e.getIsbn() == Long.parseLong(isbn)) {
				cartList.remove(e);
			}		
		}
		request.getSession().setAttribute("addedBooks",cartList);
		cart(request, response);
		
	}
	
	protected void cart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Libro> cartList= (List<Libro>) request.getSession().getAttribute("addedBooks");
		
		if (cartList.size()==0) 
			request.getRequestDispatcher("carritoVacio.jsp").forward(request, response);
		else
			request.getRequestDispatcher("carrito.jsp").forward(request, response);
	
	}
	protected void emptyCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Libro> addedBooks = new ArrayList<Libro>();
		request.getSession().setAttribute("addedBooks", addedBooks);	
		request.getRequestDispatcher("carritoVacio.jsp").forward(request, response);	
	}
	protected void buyCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Pedido order = new Pedido();
		Usuario user = (Usuario)(request.getSession().getAttribute("user"));
		order.setUsuario(user);
		order.setEstado("Comprado");
		order.setDireccionEntrega(user.getDireccion());
		order.setFechaAlta(new Date());
		List<Libro> cart = (List<Libro>) request.getSession().getAttribute("addedBooks");
		for(Libro e:cart) {
			LineaPedido linea = new LineaPedido();
			linea.setCantidad(1);
			linea.setFechaAlta(new Date());
			linea.setLibro(e);
			linea.setPedido(order);
			linea.setPrecioVenta(e.getPrecioUnitario());
			order.addLineaPedido(linea);
		}
		
		pedidoDao.insertPedido(order);
		List<Tema> topics = temaDao.findTopics();
		request.setAttribute("listTopics", topics);
		List<Libro> addedBooks = new ArrayList<Libro>();
		request.getSession().setAttribute("addedBooks", addedBooks);	
		request.getRequestDispatcher("temas.jsp").forward(request, response);
		
	}


}
