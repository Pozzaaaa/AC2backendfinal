package com.example.ac2;

import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.ac2.models.Funcionario;
import com.example.ac2.models.Projeto;
import com.example.ac2.models.Setor;
import com.example.ac2.repositories.FuncionarioRepository;
import com.example.ac2.repositories.ProjetoRepository;
import com.example.ac2.repositories.SetorRepository;

@SpringBootApplication // Indica que esta é a classe principal da aplicação Spring Boot
public class Ac2Application {

    public static void main(String[] args) {
        SpringApplication.run(Ac2Application.class, args); // Inicializa a aplicação
    }

    // Bean que será executado ao iniciar a aplicação para popular o banco de dados
    @Bean
    public CommandLineRunner init(@Autowired ProjetoRepository projetoRepository,
            @Autowired FuncionarioRepository funcionarioRepository,
            @Autowired SetorRepository setorRepository) {
        return args -> {

            // Criar e salvar setores
            Setor s1 = setorRepository.save(Setor.builder().nome("Tecnologia").build());
            Setor s2 = setorRepository.save(Setor.builder().nome("Recursos Humanos").build());

            // Criar funcionários e associar setor
            Funcionario f1 = Funcionario.builder().nome("João Silva").setor(s1).build();
            Funcionario f2 = Funcionario.builder().nome("Maria Souza").setor(s1).build();
            Funcionario f3 = Funcionario.builder().nome("Pedro Santos").setor(s2).build();

            f1 = funcionarioRepository.save(f1);
            f2 = funcionarioRepository.save(f2);
            f3 = funcionarioRepository.save(f3);

            // Criar projetos
            Projeto p1 = Projeto.builder()
                    .descricao("Sistema Interno")
                    .dataInicio(LocalDate.now())
                    .dataFim(LocalDate.now().plusDays(30))
                    .build();

            Projeto p2 = Projeto.builder()
                    .descricao("Portal Clientes")
                    .dataInicio(LocalDate.now().plusDays(10))
                    .dataFim(LocalDate.now().plusDays(60))
                    .build();

            // Associar funcionários aos projetos
            p1.setFuncionarios(new ArrayList<>());
            p1.getFuncionarios().add(f1);
            p1.getFuncionarios().add(f2);

            p2.setFuncionarios(new ArrayList<>());
            p2.getFuncionarios().add(f2);
            p2.getFuncionarios().add(f3);

            p1 = projetoRepository.save(p1);
            p2 = projetoRepository.save(p2);

            // Atualizar lista de projetos nos funcionários
            f1.setProjetos(new ArrayList<>());
            f1.getProjetos().add(p1);
            funcionarioRepository.save(f1);

            f2.setProjetos(new ArrayList<>());
            f2.getProjetos().add(p1);
            f2.getProjetos().add(p2);
            funcionarioRepository.save(f2);

            f3.setProjetos(new ArrayList<>());
            f3.getProjetos().add(p2);
            funcionarioRepository.save(f3);

            // Exibir dados inseridos
            System.out.println("Setores:");
            setorRepository.findAll().forEach(System.out::println);

            System.out.println("Funcionários:");
            funcionarioRepository.findAll().forEach(System.out::println);

            System.out.println("Projetos com início nos próximos 20 dias:");
            projetoRepository.findByDataInicioBetween(
                    LocalDate.now().minusDays(1),
                    LocalDate.now().plusDays(20))
                    .forEach(System.out::println);
        };
    }

}
