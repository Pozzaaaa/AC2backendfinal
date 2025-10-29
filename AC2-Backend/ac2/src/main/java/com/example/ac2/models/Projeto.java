package com.example.ac2.models;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Projeto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column (length = 200, nullable = false)
    private String descricao;
    @Column (nullable = false)
    private LocalDate dataInicio;
    @Column (nullable = false)
    private LocalDate dataFim;

    @ManyToMany
    @JoinTable(name = "funcionario_projeto",
            joinColumns = @JoinColumn(name = "projeto_id"),
            inverseJoinColumns = @JoinColumn(name = "funcionario_id"))
    @ToString.Exclude
    private List<Funcionario> funcionarios;

    @Override
    public String toString() {
        return "Projeto{" +
                "id=" + id +
                ", descricao='" + descricao + '\'' +
                '}';
    }

}
