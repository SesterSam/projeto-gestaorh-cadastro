package com.gestaorh.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.gestaorh.entity.Cargo;
import com.gestaorh.repository.CargoRepository;

@Service
public class CargoService {

	@Autowired
	private CargoRepository repository;
	
	public void saveOrUpdate(Cargo cargo) {
		repository.save(cargo);
	}
	
	public void delete(Cargo cargo) {
		repository.delete(cargo);
	}
	
	public void deleteById(Long id) {
		repository.deleteById(id);
	}
	
	public List<Cargo> findAll() {
		return repository.findAll(Sort.by(Sort.Direction.ASC, "nome"));
	}
	
	public boolean existsByNome(String nome) {
		return repository.existsByNome(nome);
	}
}
