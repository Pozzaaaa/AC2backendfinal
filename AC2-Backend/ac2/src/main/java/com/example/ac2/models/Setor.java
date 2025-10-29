package com.example.ac2.models;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity // Indica que esta classe é uma entidade JPA mapeada para uma tabela no banco de dados
@Data // Gera automaticamente getters, setters, equals, hashCode e toString (exceto os campos excluídos)
@NoArgsConstructor // Construtor sem argumentos
@AllArgsConstructor // Construtor com todos os argumentos
@Builder // Permite usar o padrão Builder para criar objetos
public class Setor {

    @Id // Chave primária da tabela
    @GeneratedValue(strategy = GenerationType.IDENTITY) // ID gerado automaticamente pelo banco
    private Integer id;

    @Column(length = 100, nullable = false) // Coluna com tamanho máximo de 100 e não pode ser nula
    private String nome;


    // mappedBy indica que o atributo "setor" na classe Funcionario é o dono da relação
    @OneToMany(mappedBy = "setor")
    @ToString.Exclude // Evita referência circular ao gerar toString
    private List<Funcionario> funcionarios;

    // Sobrescreve o toString para mostrar apenas id e nome
    @Override
    public String toString() {
        return "Setor{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                '}';
    }
}
