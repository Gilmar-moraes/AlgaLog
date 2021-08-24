package com.algaworks.algalog.api.controller;

import java.util.List;
//import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algalog.domain.model.ClienteModel;
import com.algaworks.algalog.domain.repository.ClienteRepository;
import com.algaworks.algalog.domain.service.CatalagoClienteService;

/**
 * 
 * @author Junior
 *
 */
@RestController
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
	private ClienteRepository clienteRepository;
	private CatalagoClienteService catalagoClienteService;

	/**
	 * Metodo reponsavel pelo retorno de todos os clientes. 
	 * @return todas as informações de todos os clientes na base de dados.
	 * @since 08/2021
	 * 
	 */
	@GetMapping
	public List<ClienteModel> listar() {
		return clienteRepository.findAll();
	}
	
	/**
	 * Metodo responsável pelo retorno dos clientes ordenados por nomes.
	 * @return  informação de um cliente especifico por nome.
	 * @since 08/2021
	 * 
	 
	@GetMapping
	public List<ClienteModel> listarPorNomes() {
		return clienteRepository.findByNome("Gilmar");
	}
	*/
	/**
	 * Metodo responsável pelo busca de clientes por determinado caractere/string.
	 * @return inforção de clientes que tenham o caracter ou string nome.
	 * @since 08/2021
	 * 
	 
	@GetMapping
	public List<ClienteModel> listarPorNomesContaining() {
		return clienteRepository.findByNomeContaining("M");
	}
	*/
	/**
	 * 
	 * @param idMetodo
	 * @return
	 * @since 08/2021
	 * 
	 */
	@GetMapping("/{idMetodo}")
	public ResponseEntity<ClienteModel> buscarPorId(@PathVariable Long idMetodo) {
		
		return clienteRepository.findById(idMetodo)
				.map(cliente -> ResponseEntity.ok(cliente))
				.orElse(ResponseEntity.notFound().build());
//		
//		Optional<ClienteModel> cliente = clienteRepository.findById(idMetodo);
//				
//		if (cliente.isPresent()) {
//			return ResponseEntity.ok(cliente.get());
//		}
//		return ResponseEntity.notFound().build();
//		
	}
	
	/**
	 * 
	 * @param clienteMetodo
	 * @return
	 */
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ClienteModel adicionarCliente(@Valid @RequestBody ClienteModel clienteMetodo) {
	
		return catalagoClienteService.savar(clienteMetodo);
	}
	
	/**
	 * 
	 * @param idMetodo
	 * @param clienteMetodo
	 * @return
	 */
	@PutMapping("/{idMetodo}")
	public ResponseEntity<ClienteModel> atualizarCliente(@Valid @PathVariable Long idMetodo, 
			@RequestBody ClienteModel clienteMetodo){
		
		if (!clienteRepository.existsById(idMetodo)) {
			return ResponseEntity.notFound().build();
		}
		clienteMetodo.setId(idMetodo);
		clienteMetodo = catalagoClienteService.savar(clienteMetodo);
		return ResponseEntity.ok(clienteMetodo);
	}
	
	/**
	 * 
	 * @param idMetodo
	 * @return
	 */
	@DeleteMapping
	public ResponseEntity<Void> excluirCliente(@PathVariable Long idMetodo){
		if (!clienteRepository.existsById(idMetodo)) {
			return ResponseEntity.notFound().build();
		}
		
		catalagoClienteService.excluir(idMetodo);
		return ResponseEntity.noContent().build();
	}
}
