package com.example.ac2.services;

import java.util.List;

import com.example.ac2.dtos.DadosProjetoDTO;
import com.example.ac2.dtos.FuncionarioDTO;

public interface FuncionarioService {
    void adicionar(FuncionarioDTO funcionarioDTO);

    List<DadosProjetoDTO> buscarProjetos(Integer idFuncionario);
}


