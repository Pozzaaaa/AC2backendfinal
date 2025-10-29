package com.example.ac2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.query.Param;
import com.example.ac2.models.Setor;

public interface SetorRepository extends JpaRepository<Setor, Integer> {

    @Query("select distinct s from Setor s left join fetch s.funcionarios")
    List<Setor> findAllWithFuncionarios();

    @Query("select distinct s from Setor s left join fetch s.funcionarios where s.id = :id")
    Optional<Setor> findByIdWithFuncionarios(@Param("id") Integer id);

}
