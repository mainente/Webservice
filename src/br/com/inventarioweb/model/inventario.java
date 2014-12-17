package br.com.inventarioweb.model;


	import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import com.google.gson.annotations.SerializedName;
	@XmlRootElement

	public class inventario {
		@SerializedName("dt_criacao")
		private String dt_criacao; 
		@SerializedName("dt_fim")
		private String dt_fim; 
		private Integer id;
		private String estado;
		private String descricao;
		private String nome;
		public String getDt_criacao() {
			return dt_criacao;
		}
		public void setDt_criacao(String string) {
			this.dt_criacao = string;
		}
		public String getDt_fim() {
			return dt_fim;
		}
		public void setDt_fim(String dt_fim) {
			this.dt_fim = dt_fim;
		}
		public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
			this.id = id;
		}
		public String getEstado() {
			return estado;
		}
		public void setEstado(String estado) {
			this.estado = estado;
		}
		public String getDescricao() {
			return descricao;
		}
		public void setDescricao(String descricao) {
			this.descricao = descricao;
		}
		public String getNome() {
			return nome;
		}
		public void setNome(String nome) {
			this.nome = nome;
		}
		public Integer getId_local() {
			return id_local;
		}
		public void setId_local(Integer id_local) {
			this.id_local = id_local;
		}
		private Integer id_local;

	}



