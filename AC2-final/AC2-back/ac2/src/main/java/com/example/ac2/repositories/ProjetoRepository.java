package com.example.ac2.repositories;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.ac2.models.Projeto;

public interface ProjetoRepository extends JpaRepository<Projeto, Integer> {
        //List<Projeto> findByDescricaoLike(String descricao);

        List<Projeto> findByDataInicioBetween(LocalDate dataInicio, LocalDate dataFim);

        @Query("select distinct p from Projeto p left join fetch p.funcionarios where p.id = :id")
        Optional<Projeto> findByIdWithFuncionarios(@Param("id") Integer id);
}
