package model.dao;

import java.util.List;

import entities.BeanSeller;
import entities.Seller;

public interface SellerDaoInterface {

	void inserir(Seller se);
	void apagar(Integer id);
	void alterar(Seller se);
	List<BeanSeller> listarTodos();
}
