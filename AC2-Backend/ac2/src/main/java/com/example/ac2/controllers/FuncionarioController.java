package com.example.ac2.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ac2.dtos.DadosProjetoDTO;
import com.example.ac2.dtos.FuncionarioDTO;
import com.example.ac2.services.FuncionarioService;

@RestController
@RequestMapping("/funcionario")
public class FuncionarioController {
    private FuncionarioService funcionarioService;

    public FuncionarioController(FuncionarioService funcionarioService) {
        this.funcionarioService = funcionarioService;  
    }

    @PostMapping
    public void adicionar(@RequestBody FuncionarioDTO funcionarioDTO) {
        funcionarioService.adicionar(funcionarioDTO);
    }

    @GetMapping("/{id}")
    public List<DadosProjetoDTO> buscarProjeto(@PathVariable Integer id) {
        return funcionarioService.buscarProjetos(id);
    }
}