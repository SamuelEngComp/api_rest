package com.javaSystemApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.javaSystemApi.model.Pessoa;


public interface PessoaRepository extends JpaRepository<Pessoa, Long>{

}
