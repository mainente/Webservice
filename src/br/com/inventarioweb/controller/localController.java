package br.com.inventarioweb.controller;

import java.util.ArrayList;

import br.com.inventarioweb.DAO.localDAO;
import br.com.inventarioweb.model.local;


public class localController {
	public ArrayList<local> listarTodos(){
		//System.out.println("Enviando para o GIT");
		return localDAO.getInstance().listarTodos();
	}
	
}
