package model.dao;

import java.util.List;

import model.beans.Libro;
import model.beans.Perfile;
import model.beans.Tema;


public class TemaDAO extends AbstractFactory implements IntTema{

	@Override
	public List<Tema> findTopics() {
		return em.createQuery("select t from Tema t").getResultList();
	}

	@Override
	public int insertTopic(Tema tema) {
		tx.begin();
		int rows = 0;
		try {
			em.persist(tema);
			tx.commit();
			rows =  1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return rows;
	}

	@Override
	public Tema findTopicById(String id) {
		return em.find(Tema.class, Integer.parseInt(id));
	}
	
}

