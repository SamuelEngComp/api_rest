package com.javaSystemApi.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.javaSystemApi.model.Categoria;
import com.javaSystemApi.model.Pessoa;
import com.javaSystemApi.repository.CategoriaRepository;
import com.javaSystemApi.repository.PessoaRepository;

@Service
public class PessoaService {

	@Autowired
	private PessoaRepository pessoaRepository;

	public Pessoa atualizar(Long codigo, Pessoa pessoa) {


		Pessoa pessoaSalva = buscarPessoaPeloCodigo(codigo);

		BeanUtils.copyProperties(pessoa,pessoaSalva, "codigo");

		return pessoaRepository.save(pessoaSalva);

	}

	public Pessoa buscarPessoaPeloCodigo(Long codigo) {
		Pessoa pessoaSalva = pessoaRepository.findOne(codigo);

		if(pessoaSalva == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return pessoaSalva;
	}

	public void atualizarPropriedadeAtivo(Long codigo, Boolean ativo) {
		Pessoa pessoaSalva = pessoaRepository.findOne(codigo);
		pessoaSalva.setAtivo(ativo);
		pessoaRepository.save(pessoaSalva);
	}

}
