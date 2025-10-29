package com.example.ac2.services;

import com.example.ac2.dtos.SetorDTO;
import com.example.ac2.dtos.DadosSetorDTO;

public interface SetorService {
	void adicionar(SetorDTO setorDTO);

	DadosSetorDTO buscarSetorPorId(Integer id);
}
