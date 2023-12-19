package com.biel.atvtlp2.modelo;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Roupa {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty
	@Size(min = 3, message = "O nome da peça de roupa deve ter no mínimo 3 carateres")
	private String nomePeca;
	
	@NotNull(message = "A data deve ser informada.")
	@Basic
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dataLancamento;
	
		
	@NotEmpty(message = "O código da peça deve ser informado")
	@Size(min = 3, message = "O código tem ao menos 4 digitos.")
	private String codPeca;	
	
	@NotEmpty(message = "O tipo da peça deve ser informado")
	private String tipo;
	
	@NotEmpty(message = "A cor da peça deve ser informada")
	private String cor;
	
	private boolean ativo;	
	
	

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNomePeca() {
		return nomePeca;
	}
	public void setNomePeca(String nomePeca) {
		this.nomePeca = nomePeca;
	}
	public String getCodPeca() {
		return codPeca;
	}
	public void setCodPeca(String codPeca) {
		this.codPeca = codPeca;
	}
	public Date getDataLancamento() {
		return dataLancamento;
	}
	public void setDataLancamento(Date dataLancamento) {
		this.dataLancamento = dataLancamento;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public void setEmail(String tipo) {
		this.tipo = tipo;
	}
	public String getCor() {
		return cor;
	}
	public void setCor(String cor) {
		this.cor = cor;
	}
	public boolean isAtivo() {
		return ativo;
	}
	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	
}
