package com.example.ac2.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.ac2.models.Funcionario;
import com.example.ac2.models.Projeto;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Integer> {
	@Query("select f.projetos from Funcionario f where f.id = :id")
	List<Projeto> findProjetosByFuncionarioId(@Param("id") Integer id);
}
