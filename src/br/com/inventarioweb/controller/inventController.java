package br.com.inventarioweb.controller;

import java.util.ArrayList;

import br.com.inventarioweb.DAO.InventDAO;
import br.com.inventarioweb.DAO.ProdutoDAO;
import br.com.inventarioweb.DAO.localDAO;
import br.com.inventarioweb.model.inventario;
import br.com.inventarioweb.model.local;
import br.com.inventarioweb.model.produto;

public class inventController {
	public ArrayList<inventario> listarTodosinvent(String id){
		String id_local;
		id_local=id;
	//	System.out.println("Enviando para o GIT");
		return InventDAO.getInstance().listarTodosinvent(id_local);
	}
	public inventario Infoinvent(String id_invent){
		//System.out.println("Enviando para o GIT");
		return InventDAO.getInstance().infoinvent(id_invent);
	}
	
}
