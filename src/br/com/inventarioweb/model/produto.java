package br.com.inventarioweb.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement

public class produto {
	Integer id;
	String dt_aquisicao;
	String nome;
	String marca;
	Integer id_local;

	public Integer getId_local() {
		return id_local;
	}
	public void setId_local(Integer id_local) {
		this.id_local = id_local;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDt_aquisicao() {
		return dt_aquisicao;
	}
	public void setDt_aquisicao(String dt_aquisicao) {
		this.dt_aquisicao = dt_aquisicao;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

}
