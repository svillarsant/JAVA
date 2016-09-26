package com.curso.dto;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="coche")
public class CocheDTO {

	private Integer id;
	private String marca;
	private String modelo;
	private String matricula;

	public CocheDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CocheDTO(Integer id, String marca, String modelo, String matricula) {
		super();
		this.id = id;
		this.marca = marca;
		this.modelo = modelo;
		this.matricula = matricula;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	@Override
	public String toString() {
		return "Coche [id=" + id + ", marca=" + marca + ", modelo=" + modelo + ", matricula=" + matricula + "]";
	}

}
