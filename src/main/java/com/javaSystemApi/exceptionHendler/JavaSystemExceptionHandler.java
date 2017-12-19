package com.javaSystemApi.exceptionHendler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * 
 * @author samuel
 *classe responsavel pela captura de exceções de resposta da entidades
 *ResponseEntityExceptionHandler -> essa classe já vem com muitos metodos
 *prontos para trabalhar
 */


@ControllerAdvice //controle advice, ele controla toda a aplicação
public class JavaSystemExceptionHandler extends ResponseEntityExceptionHandler{
	
	@Autowired
	private MessageSource messageSource;
	
	
	@Override//Não conseguiu ler as mensagens
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		String mensagemUsuario = messageSource.getMessage("mensagem.invalida", null,LocaleContextHolder.getLocale());
		String mensagemDesenvolvedor = ex.getCause() != null ? ex.getCause().toString() : ex.toString();
		
		List<Erro> erros = Arrays.asList(new Erro(mensagemUsuario, mensagemDesenvolvedor));
		
		
		return handleExceptionInternal(ex, erros, headers, HttpStatus.BAD_REQUEST, request);
	}
	
	@Override  //trata o argumento do metodo quando ele não é valido
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		List<Erro> erros = criaListaDeErros(ex.getBindingResult());
		
		return handleExceptionInternal(ex, erros, headers, HttpStatus.BAD_REQUEST, request);
	}
	
	
	@ExceptionHandler({EmptyResultDataAccessException.class})
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<Object> handleEmptyResultDataAccessException(EmptyResultDataAccessException ex,WebRequest request) {
		
		String mensagemUsuario = messageSource.getMessage("recurso.nao-encontrado", null,LocaleContextHolder.getLocale());
		String mensagemDesenvolvedor = ex.toString();
		List<Erro> erros = Arrays.asList(new Erro(mensagemUsuario, mensagemDesenvolvedor));
		
		return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}
	
	@ExceptionHandler({DataIntegrityViolationException.class})
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex,WebRequest request){
		
		String mensagemUsuario = messageSource.getMessage("recurso.operacao-nao-permitida", null,LocaleContextHolder.getLocale());
		String mensagemDesenvolvedor = ExceptionUtils.getRootCauseMessage(ex);
		List<Erro> erros = Arrays.asList(new Erro(mensagemUsuario, mensagemDesenvolvedor));
		
		return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
		
	}
	
	
	
	
	private List<Erro> criaListaDeErros(BindingResult bindingResult){
		
		List<Erro> erros = new ArrayList<>();
		
		//devolve todos os erros da entidades q estão sendo validados
		for(FieldError fieldError : bindingResult.getFieldErrors()) {
			String mensagemUsuario = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
			String mensagemDesenvolvedor = fieldError.toString();
			erros.add(new Erro(mensagemUsuario, mensagemDesenvolvedor));
		}
		
		return erros;
	}
	 
	
	
	public static class Erro{
		private String mensagemUsuario;
		private String mensagemDesenvolvedor;
		
		
		public Erro(String mensagemUsuario, String mensagemDesenvolvedor) {
			this.mensagemUsuario = mensagemUsuario;
			this.mensagemDesenvolvedor = mensagemDesenvolvedor;
		}


		public String getMensagemUsuario() {
			return mensagemUsuario;
		}


		public void setMensagemUsuario(String mensagemUsuario) {
			this.mensagemUsuario = mensagemUsuario;
		}


		public String getMensagemDesenvolvedor() {
			return mensagemDesenvolvedor;
		}


		public void setMensagemDesenvolvedor(String mensagemDesenvolvedor) {
			this.mensagemDesenvolvedor = mensagemDesenvolvedor;
		}
	}
}
