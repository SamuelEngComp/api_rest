package com.javaSystemApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.javaSystemApi.model.Lancamento;
import com.javaSystemApi.repository.lancamento.LancamentoRepositoryQuery;


public interface LancamentoRepository extends JpaRepository<Lancamento, Long>, LancamentoRepositoryQuery{

}
