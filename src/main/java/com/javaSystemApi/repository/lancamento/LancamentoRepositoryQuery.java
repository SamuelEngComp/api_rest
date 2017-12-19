package com.javaSystemApi.repository.lancamento;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.javaSystemApi.model.Lancamento;
import com.javaSystemApi.repository.filter.LancamentoFilter;

public interface LancamentoRepositoryQuery{
	
	public Page<Lancamento> filtrar(LancamentoFilter lancamentoFilter, Pageable pageable);

}
