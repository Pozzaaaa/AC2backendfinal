package com.example.ac2.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ac2.dtos.DadosProjetoDTO;
import com.example.ac2.dtos.ProjetoDTO;
import com.example.ac2.services.ProjetoService;

@RestController
@RequestMapping("/projeto")
public class ProjetoController {

    private final ProjetoService projetoService;

    public ProjetoController(ProjetoService projetoService) {
        this.projetoService = projetoService;
    }

    @PostMapping
    public void adicionar(@RequestBody ProjetoDTO projetoDTO) {
        projetoService.adicionar(projetoDTO);
    }

    @GetMapping("/{id}")
    public DadosProjetoDTO buscarProjetoPorId(@PathVariable("id") Integer id) {
        return projetoService.buscarProjetoPorId(id);
    }

    @PostMapping("/{idProjeto}/vincular/{idFuncionario}")
    public void vincularFuncionario(
            @PathVariable("idProjeto") Integer idProjeto,
            @PathVariable("idFuncionario") Integer idFuncionario) {
        projetoService.vincularFuncionario(idProjeto, idFuncionario);
    }
}
