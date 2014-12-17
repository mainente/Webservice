package br.com.inventarioweb.controller;

import java.util.ArrayList;

import br.com.inventarioweb.DAO.InventDAO;
import br.com.inventarioweb.DAO.InventinfoDAO;
import br.com.inventarioweb.DAO.ProdutoDAO;
import br.com.inventarioweb.model.Inventinfo;
import br.com.inventarioweb.model.foto;
import br.com.inventarioweb.model.inventario;
import br.com.inventarioweb.model.produto;

public class inventinfoController {
	public ArrayList<Inventinfo> listarTodosprod(String id){
		String id_invent;
		id_invent=id;
		//System.out.println("Enviando para o GIT");
		return InventinfoDAO.getInstance().listarTodosprod(id_invent);
	}
		public ArrayList<Inventinfo> listarTodosprodanterior(String id){
			String id_loc;
			id_loc=id;
			//System.out.println("Enviando para o GIT");
			return InventinfoDAO.getInstance().listarTodosprodanteirior(id_loc);
		}
	
	public Inventinfo Verificarprod(String id, String produto){
		String id_invent;
		id_invent=id;
		//System.out.println("Enviando para o GIT");
		return InventinfoDAO.getInstance().verificarprod(id_invent, produto);
	}

		public Inventinfo AcharProdutoinvent(String id_invent,String id_prod){
			//System.out.println("Enviando para o GIT");
			return InventinfoDAO.getInstance().AcharProdutoinvent(id_prod, id_invent);
		}
	}
