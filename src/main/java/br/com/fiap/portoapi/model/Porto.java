package br.com.fiap.portoapi.model;

import java.time.LocalDate;
import java.util.Objects;

import jakarta.validation.constraints.NotNull;

public class Porto {

	
	private Long id;

	@NotNull (message = "N pode ficar em branco")
	private String nome;
	
	@NotNull (message = "N pode ficar em branco")
	private String cpf;
	
	@NotNull (message = "N pode ficar em branco")
	private String cpnj;
	
	@NotNull (message = "N pode ficar em branco")
	private LocalDate data;
	
	@NotNull (message = "N pode ficar em branco")
	private String endereco;

	@NotNull (message = "N pode ficar em branco")
	private String cidade; 
	
	@NotNull (message = "N pode ficar em branco")
	private String estado;
	
	@NotNull (message = "N pode ficar em branco")
	private String bairro;

	
	public Porto() {
		super();
	}

	public Porto(Long id, 
			@NotNull(message = "N pode ficar em branco") String nome,
			@NotNull(message = "N pode ficar em branco") String cpf,
			@NotNull(message = "N pode ficar em branco") String cpnj,
			@NotNull(message = "N pode ficar em branco") LocalDate data,
			@NotNull(message = "N pode ficar em branco") String endereco,
			@NotNull(message = "N pode ficar em branco") String cidade,
			@NotNull(message = "N pode ficar em branco") String estado,
			@NotNull(message = "N pode ficar em branco") String bairro) {
		super();
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.cpnj = cpnj;
		this.data = data;
		this.endereco = endereco;
		this.cidade = cidade;
		this.estado = estado;
		this.bairro = bairro;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getCpnj() {
		return cpnj;
	}

	public void setCpnj(String cpnj) {
		this.cpnj = cpnj;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	
	@Override
	public int hashCode() {
		return Objects.hash(bairro, cidade, cpf, cpnj, data, endereco, estado, id, nome);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Porto other = (Porto) obj;
		return Objects.equals(bairro, other.bairro) && Objects.equals(cidade, other.cidade)
				&& Objects.equals(cpf, other.cpf) && Objects.equals(cpnj, other.cpnj)
				&& Objects.equals(data, other.data) && Objects.equals(endereco, other.endereco)
				&& Objects.equals(estado, other.estado) && Objects.equals(id, other.id)
				&& Objects.equals(nome, other.nome);
	}
	
	
	
	
	
	
	}

