package com.gestaorh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gestaorh.entity.Cargo;

@Repository
public interface CargoRepository extends JpaRepository<Cargo, Long> {

	boolean existsByNome(String nome);
}
