package model.dao;

import model.beans.Usuario;

public interface IntUsuario {
	Usuario checkLogin(String username, String password);
	int insertUser(Usuario user);
}
