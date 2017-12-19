package com.javaSystemApi.resource;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.javaSystemApi.evento.RecursoCriadoEvent;
import com.javaSystemApi.model.Categoria;
import com.javaSystemApi.model.Pessoa;
import com.javaSystemApi.repository.CategoriaRepository;
import com.javaSystemApi.service.CategoriaService;

/**
 * @author samue
 *
 */
@RestController//controlador rest, esse retorno sera convertido para JASON
@RequestMapping("/categorias")//mapeando a requisição
public class CategoriaResource {

	@Autowired
	private CategoriaService categoriaService;
	
	@Autowired //injetando...
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	
	@GetMapping//metodo que sera chamado com a requisição categorias
	public List<Categoria> listar(){
		return categoriaRepository.findAll();
	}
	
	@PostMapping   // para a minha anotação @notNull funcionar, foi necessario colocar o @valid nessa classe para poder validar
	public ResponseEntity<Categoria> criar(@Valid @RequestBody Categoria categoria, HttpServletResponse response) {
		Categoria categoriaSalva = categoriaRepository.save(categoria);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, categoriaSalva.getCodigo()));
		return ResponseEntity.status(HttpStatus.CREATED).body(categoriaSalva);
	}
	
	
	@GetMapping("/{codigo}")//categoria e codigo, passo o codigo e ele retorna a categoria
	public ResponseEntity<Categoria> buscarPeloCodigo(@PathVariable Long codigo) {
		Categoria categoria = categoriaRepository.findOne(codigo);
		return categoria != null ? ResponseEntity.ok(categoria) : ResponseEntity.notFound().build();
	}
	
	
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT) //204 - removeu com sucesso, mas não tem nada pra retornar
	public void remover(@PathVariable Long codigo) {
		categoriaRepository.delete(codigo);
	}
	
	@PutMapping("/{codigo}")
	public ResponseEntity<Categoria> atualizar(@PathVariable Long codigo, @Valid @RequestBody Categoria categoria){
		Categoria categoriaSalva = categoriaService.atualizar(codigo, categoria);
		return ResponseEntity.ok(categoriaSalva);
	}	
	
}