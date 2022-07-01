package model.dao;

import java.util.List;

import entities.Departament;

public interface DepartmentDaoInterface {
	
	void inserir(Departament dep);
	void apagar(Integer id);
	void buscarUm(Integer id);
	void atualizar(Departament dep);
	List<Departament> listarTodos();
	
}
