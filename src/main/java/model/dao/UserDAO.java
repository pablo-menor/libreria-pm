package model.dao;

import model.beans.Usuario;

public class UserDAO extends AbstractFactory implements IntUsuario{

	@Override
	public Usuario checkLogin(String username, String password) {
		sql = "select u from Usuario u where u.username= :username and u.password= :password";
		query = em.createQuery(sql);
		query.setParameter("username", username);
		query.setParameter("password", password);
		try {
			return (Usuario) query.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public int insertUser(Usuario user) {
		tx.begin();
		int rows = 0;
		try {
			em.persist(user);
			tx.commit();
			rows =  1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return rows;
	}

}
