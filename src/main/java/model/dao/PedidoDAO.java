package model.dao;

import model.beans.Pedido;

public class PedidoDAO extends AbstractFactory implements IntPedido {

	@Override
	public int insertPedido(Pedido pedido) {
		tx.begin();
		int rows = 0;
		try {
			em.persist(pedido);
			tx.commit();
			rows =  1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return rows;
	}

}
