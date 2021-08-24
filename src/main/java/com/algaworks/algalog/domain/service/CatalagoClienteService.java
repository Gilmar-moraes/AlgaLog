package com.algaworks.algalog.domain.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algalog.domain.exception.NegocioException;
import com.algaworks.algalog.domain.model.ClienteModel;
import com.algaworks.algalog.domain.repository.ClienteRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CatalagoClienteService {
	
	private ClienteRepository clienteRepository;
	
	@Transactional
	public ClienteModel savar(ClienteModel clienteMetodo) {
		boolean emailEmUso = clienteRepository.findByEmail(clienteMetodo.getEmail())
				.stream()
				.anyMatch(clienteExistente -> !clienteExistente.equals(clienteMetodo));
		if (emailEmUso) {
			throw new NegocioException("Já exixte este email");
		}
		return clienteRepository.save(clienteMetodo);
	}
	
	@Transactional
	public void excluir(Long clienteId) {
		clienteRepository.deleteById(clienteId);
	}
}
