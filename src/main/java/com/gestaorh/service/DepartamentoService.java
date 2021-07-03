package com.gestaorh.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.gestaorh.entity.Departamento;
import com.gestaorh.repository.DepartamentoRepository;

@Service
public class DepartamentoService {

	@Autowired
	private DepartamentoRepository repository;
	
	public void saveOrUpdate(Departamento departamento) {
		repository.save(departamento);
	}
	
	public void delete(Departamento departamento) {
		repository.delete(departamento);
	}
	
	public void deleteById(Long id) {
		repository.deleteById(id);
	}
	
	public List<Departamento> findAll() {
		return repository.findAll(Sort.by(Sort.Direction.ASC, "nome"));
	}
	
	public boolean existsByNome(String nome) {
		return repository.existsByNome(nome);
	}
}
