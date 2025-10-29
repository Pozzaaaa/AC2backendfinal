package com.example.ac2.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.ac2.dtos.DadosProjetoDTO;
import com.example.ac2.dtos.FuncionarioDTO;
import com.example.ac2.dtos.RegraNegocioException;
import com.example.ac2.models.Funcionario;
import com.example.ac2.repositories.FuncionarioRepository;
import jakarta.transaction.Transactional;

@Service
public class FuncionarioServiceImpl implements FuncionarioService {
    private FuncionarioRepository funcionarioRepository;

    public FuncionarioServiceImpl(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    @Override
    @Transactional
    public void adicionar(FuncionarioDTO funcionarioDTO) {
        Funcionario funcionario = Funcionario.builder()
                .nome(funcionarioDTO.getNome())
                .build();
        funcionarioRepository.save(funcionario);
    }

    @Override
    public List<DadosProjetoDTO> buscarProjetos(Integer idFuncionario) {
        // validar existência do funcionário e dar feedback adequado
        if (!funcionarioRepository.existsById(idFuncionario)) {
            throw new RegraNegocioException("Funcionário não encontrado");
        }

        return funcionarioRepository.findProjetosByFuncionarioId(idFuncionario)
                .stream()
                .map(p -> {
                    return DadosProjetoDTO.builder()
                        .id(p.getId())
                        .descricao(p.getDescricao())
                        .dataInicio(p.getDataInicio())
                        .dataFim(p.getDataFim())
                        .build();
                })
                .collect(Collectors.toList());
    }

}
