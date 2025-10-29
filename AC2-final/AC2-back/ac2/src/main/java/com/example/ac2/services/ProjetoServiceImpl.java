package com.example.ac2.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.ac2.dtos.DadosProjetoDTO;
import com.example.ac2.dtos.ProjetoDTO;
import com.example.ac2.dtos.FuncionarioDTO;
import com.example.ac2.dtos.RegraNegocioException;
import com.example.ac2.models.Funcionario;
import com.example.ac2.models.Projeto;
import com.example.ac2.repositories.FuncionarioRepository;
import com.example.ac2.repositories.ProjetoRepository;

import jakarta.transaction.Transactional;

@Service
public class ProjetoServiceImpl implements ProjetoService {

    private ProjetoRepository projetoRepository;
    private FuncionarioRepository funcionarioRepository;

    public ProjetoServiceImpl(ProjetoRepository projetoRepository, FuncionarioRepository funcionarioRepository) {
        this.projetoRepository = projetoRepository;
        this.funcionarioRepository = funcionarioRepository;
    }

    @Override
    @Transactional
    public void adicionar(ProjetoDTO projetoDTO) {
        Projeto projeto = Projeto.builder()
                .descricao(projetoDTO.getDescricao())
                .dataInicio(projetoDTO.getDataInicio())
                .dataFim(projetoDTO.getDataFim())
                .build();
        projetoRepository.save(projeto);
    }

    @Override
    public DadosProjetoDTO buscarProjetoPorId(Integer id) {
        Projeto projeto = projetoRepository.findByIdWithFuncionarios(id)
                .orElseThrow(() -> new RegraNegocioException("Projeto não encontrado"));

        List<FuncionarioDTO> funcionarios = projeto.getFuncionarios() == null ? List.of()
                : projeto.getFuncionarios().stream().map(f -> {
                    FuncionarioDTO dto = new FuncionarioDTO();
                    dto.setId(f.getId());
                    dto.setNome(f.getNome());
                    return dto;
                }).collect(Collectors.toList());

        return DadosProjetoDTO.builder()
                .id(projeto.getId())
                .descricao(projeto.getDescricao())
                .dataInicio(projeto.getDataInicio())
                .dataFim(projeto.getDataFim())
                .funcionarios(funcionarios)
                .build();
    }

    @Override
    @Transactional
    public void vincularFuncionario(Integer idProjeto, Integer idFuncionario) {
        Projeto projeto = projetoRepository.findById(idProjeto)
                .orElseThrow(() -> new RegraNegocioException("Projeto não encontrado"));

        Funcionario funcionario = funcionarioRepository.findById(idFuncionario)
                .orElseThrow(() -> new RegraNegocioException("Funcionário não encontrado"));

        if (projeto.getFuncionarios() == null) {
            projeto.setFuncionarios(new java.util.ArrayList<>());
        }
        if (!projeto.getFuncionarios().contains(funcionario)) {
            projeto.getFuncionarios().add(funcionario);
        }

        if (funcionario.getProjetos() == null) {
            funcionario.setProjetos(new java.util.ArrayList<>());
        }
        if (!funcionario.getProjetos().contains(projeto)) {
            funcionario.getProjetos().add(projeto);
        }

        projetoRepository.save(projeto);
        funcionarioRepository.save(funcionario);
    }
}
