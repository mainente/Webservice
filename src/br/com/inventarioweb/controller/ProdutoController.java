package br.com.inventarioweb.controller;

import java.util.ArrayList;

import br.com.inventarioweb.DAO.ProdutoDAO;
import br.com.inventarioweb.DAO.localDAO;
import br.com.inventarioweb.model.local;
import br.com.inventarioweb.model.produto;
import br.com.inventarioweb.model.foto;

public class ProdutoController {
	public produto AcharProduto(String id){
		//System.out.println("Enviando para o GIT");
		return ProdutoDAO.getInstance().AcharProduto(id);
	}
	public produto AcharProdutocod(String codigo_barra){
		//System.out.println("Enviando para o GIT");
		return ProdutoDAO.getInstance().AcharProdutocod(codigo_barra);
	}
	public foto foto(String id){
		//System.out.println("Enviando para o GIT");
		return ProdutoDAO.getInstance().foto(id);
	}
}
