package model.dao;

import java.util.List;

import model.beans.Libro;

public class LibroDAO extends AbstractFactory implements IntLibro {

	@Override
	public List<Libro> findBooksByTopic(String topic) {
		sql = "select l from Libro l where l.tema.idTema = :topic";
		return em.createQuery(sql).setParameter("topic", Integer.parseInt(topic)).getResultList();
	}

	@Override
	public Libro findBookByIsbn(String isbn) {
		return em.find(Libro.class, Long.parseLong(isbn));
	}

	@Override
	public int insertBook(Libro libro) {
		tx.begin();
		int rows = 0;
		try {
			em.persist(libro);
			tx.commit();
			rows =  1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return rows;
	}
	


}
