package com.example.ac2.services;

import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.ac2.dtos.DadosSetorDTO;
import com.example.ac2.dtos.SetorDTO;
import com.example.ac2.dtos.FuncionarioDTO;
import com.example.ac2.dtos.RegraNegocioException;
import com.example.ac2.models.Setor;
import com.example.ac2.repositories.SetorRepository;

import jakarta.transaction.Transactional;

@Service
public class SetorServiceImpl implements SetorService {

    private final SetorRepository setorRepository;

    public SetorServiceImpl(SetorRepository setorRepository) {
        this.setorRepository = setorRepository;
    }

    @Override
    @Transactional
    public void adicionar(SetorDTO setorDTO) {
        Setor s = Setor.builder().nome(setorDTO.getNome()).build();
        setorRepository.save(s);
    }

    @Override
    public DadosSetorDTO buscarSetorPorId(Integer id) {
        Setor s = setorRepository.findByIdWithFuncionarios(id)
                .orElseThrow(() -> new RegraNegocioException("Setor não encontrado"));

        return DadosSetorDTO.builder()
                .id(s.getId())
                .nome(s.getNome())
                .funcionarios(s.getFuncionarios() == null ? java.util.List.of()
                        : s.getFuncionarios().stream().map(f -> new FuncionarioDTO(f.getId(), f.getNome()))
                                .collect(Collectors.toList()))
                .build();
    }
}

