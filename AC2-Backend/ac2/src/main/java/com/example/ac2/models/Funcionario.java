package com.example.ac2.models;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity // Indica que esta classe é uma entidade JPA mapeada para uma tabela no banco de dados
@Data // Gera getters, setters, equals, hashCode e toString (exceto os campos excluídos)
@NoArgsConstructor // Gera um construtor sem argumentos
@AllArgsConstructor // Gera um construtor com todos os argumentos
@Builder // Permite construir objetos usando o padrão Builder
public class Funcionario {

    @Id // Define a chave primária da tabela
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Gera o ID automaticamente pelo banco de dados
    private Integer id;

    @Column(length = 200, nullable = false) // Define o tamanho máximo e que não pode ser nulo
    private String nome;

    
    // mappedBy indica que o lado inverso é "funcionarios" na entidade Projeto
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "funcionarios")
    @ToString.Exclude // Evita referência circular ao gerar o toString
    private List<Projeto> projetos;


    @ManyToOne
    @JoinColumn(name = "setor_id", nullable = true) // Define a coluna estrangeira e permite nulo
    @ToString.Exclude // Evita referência circular ao gerar o toString
    private Setor setor;

    // Sobrescreve o toString para exibir apenas id e nome
    @Override
    public String toString() {
        return String.format("Funcionario{id=%d, nome='%s'}", id, nome);
    }
}
