package br.com.inventarioweb.model;

import javax.xml.bind.annotation.XmlRootElement;

import com.google.gson.annotations.SerializedName;

@XmlRootElement

public class foto {
private Integer id;
private String formato;
private String imagem;
@SerializedName("data_f")
private String data_f; 
public String getImagem() {
	return imagem;
}

public void setImagem(String imagem) {
	this.imagem = imagem;
}

public String getData_f() {
	return data_f;
}

public void setData_f(String data_f) {
	this.data_f = data_f;
}

public String getFormato() {
	return formato;
}

public void setFormato(String formato) {
	this.formato = formato;
}

public Integer getId() {
	return id;
}

public void setId(Integer id) {
	this.id = id;
}


}
