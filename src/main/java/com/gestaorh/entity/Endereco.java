package com.gestaorh.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.gestaorh.enumeration.UF;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "enderecos")
public class Endereco implements Serializable {

	private static final long serialVersionUID = -7260177523882500743L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 255)
	private String logradouro;

	@Column(nullable = false, length = 255)
	private String bairro;

	@Column(nullable = false, length = 255)
	private String cidade;

	@Column(length = 255)
	private String complemento;

	@Column(nullable = false, length = 9)
	private String cep;

	@Column(length = 11)
	private Integer numero;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 2)
	private UF uf;
}
