package com.gestaorh.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.gestaorh.entity.Funcionario;
import com.gestaorh.repository.FuncionarioRepository;

@Service
public class FuncionarioService {

	@Autowired
	private FuncionarioRepository repository;
	
	public void saveOrUpdate(Funcionario funcionario) {
		repository.save(funcionario);
	}
	
	public void delete(Funcionario funcionario) {
		repository.delete(funcionario);
	}
	
	public void deleteById(Long id) {
		repository.deleteById(id);
	}
	
	public List<Funcionario> findAll() {
		return repository.findAll(Sort.by(Sort.Direction.ASC, "nome"));
	}
}
