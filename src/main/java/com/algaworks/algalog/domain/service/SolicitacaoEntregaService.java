package com.algaworks.algalog.domain.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algalog.domain.exception.NegocioException;
import com.algaworks.algalog.domain.model.ClienteModel;
import com.algaworks.algalog.domain.model.EntregaModel;
import com.algaworks.algalog.domain.model.StatusEntrega;
import com.algaworks.algalog.domain.repository.ClienteRepository;
import com.algaworks.algalog.domain.repository.EntregaRepository;

import lombok.AllArgsConstructor;

/**
 * 
 * @author Gilmar Junior
 *
 */
@AllArgsConstructor
@Service
public class SolicitacaoEntregaService {

	private CatalagoClienteService catalagoClienteService;
	private EntregaRepository entregaRepository;
	
	@Transactional
	public EntregaModel solicitar(EntregaModel entregaMetodo) {
		
		ClienteModel cliente = catalagoClienteService.buscar(entregaMetodo.getCliente().getId());
		
		entregaMetodo.setCliente(cliente);
		entregaMetodo.setStatusPedido(StatusEntrega.PENDENTE);
		entregaMetodo.setDataDoPedido(LocalDateTime.now());
		return entregaRepository.save(entregaMetodo);
	}
}
