package com.javaSystemApi.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.javaSystemApi.model.Lancamento;
import com.javaSystemApi.model.Pessoa;
import com.javaSystemApi.repository.LancamentoRepository;
import com.javaSystemApi.repository.PessoaRepository;
import com.javaSystemApi.service.exception.PessoaInexistenteOuInativoException;

@Service
public class LancamentoService {
	
	@Autowired
	private LancamentoRepository lancamentoRepository;
	
	@Autowired
	private PessoaRepository pessoaRepository;

	public Lancamento atualizar(Long codigo, Lancamento lancamento) {
		Lancamento lancamentoSalvo = buscarLancamentoPeloCodigo(codigo);
		BeanUtils.copyProperties(lancamento,lancamentoSalvo, "codigo");
		return lancamentoRepository.save(lancamentoSalvo);
	}

	private Lancamento buscarLancamentoPeloCodigo(Long codigo) {
		Lancamento lancamentoSalvo = lancamentoRepository.findOne(codigo);
		if(lancamentoSalvo == null){
			throw new EmptyResultDataAccessException(1);
		}
		return lancamentoSalvo;
	}

	public Lancamento salvar(Lancamento lancamento) {
		Pessoa pessoa = pessoaRepository.findOne(lancamento.getPessoa().getCodigo());
		if(pessoa == null || pessoa.isInativo()) {
			throw new PessoaInexistenteOuInativoException();
			
		}
		
		
		return lancamentoRepository.save(lancamento);
	}


}
