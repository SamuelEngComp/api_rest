package com.javaSystemApi.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.javaSystemApi.model.Categoria;
import com.javaSystemApi.repository.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public Categoria atualizar(Long codigo, Categoria categoria) {
		
		Categoria categoriaSalva = categoriaRepository.findOne(codigo);
		
		if(categoriaSalva == null) {
			throw new EmptyResultDataAccessException(1);
		}
		
		BeanUtils.copyProperties(categoria,categoriaSalva, "codigo");
		
		return categoriaRepository.save(categoriaSalva);
	}

}
