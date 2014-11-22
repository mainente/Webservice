package br.com.inventarioweb.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement


public class Inventinfo {
		private Integer id_produto;
		private Integer id_inventario;
		private String nome_prod;
		private String id;
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getNome_prod() {
			return nome_prod;
		}
		public void setNome_prod(String nome_prod) {
			this.nome_prod = nome_prod;
		}
		public Integer getId_produto() {
			return id_produto;
		}
		public void setId_produto(Integer id_produto) {
			this.id_produto = id_produto;
		}
		public Integer getId_inventario() {
			return id_inventario;
		}
		public void setId_inventario(Integer id_inventario) {
			this.id_inventario = id_inventario;
		}
		}



