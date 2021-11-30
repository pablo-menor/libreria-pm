package model.dao;

import java.util.List;

import model.beans.Libro;
import model.beans.Tema;

public interface IntLibro {
	List<Libro> findBooksByTopic(String topic);
	Libro findBookByIsbn(String  isbn) ;
	int insertBook(Libro libro);
}
