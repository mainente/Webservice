package br.com.inventarioweb.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement

public class foto {
private byte nome[];
private Integer id;
public Integer getId() {
	return id;
}

public void setId(Integer id) {
	this.id = id;
}

public byte[] getNome() {
	return nome;
}

public void setNome(byte[] nome) {
	this.nome = nome;
}
}
