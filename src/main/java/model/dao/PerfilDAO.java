package model.dao;

import model.beans.Perfile;

public class PerfilDAO extends AbstractFactory implements  IntPerfiles{

	@Override
	public Perfile findById(int id) {
		return em.find(Perfile.class, id);
	}
	
}
