package model.dao;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

public abstract class AbstractFactory {

		protected EntityManagerFactory emf;
		protected EntityManager em;
		protected EntityTransaction tx;
		protected String sql;
		protected Query query;
		
		public AbstractFactory() {
			emf = Persistence.createEntityManagerFactory("Libreria");
			em = emf.createEntityManager();
			tx = em.getTransaction();
		}
	


}
