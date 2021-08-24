package com.algaworks.algalog.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.algaworks.algalog.domain.model.ClienteModel;

/**
 * calsse repositorio é uma classe 
 * que tem como responsabilidade 
 * implementar metodos que fazem a 
 * pesistencia com o banco de dados
 * 
 * @author Gilmar Junior
 * @since 08/2021
 * @version 01
 **/

@Repository
public interface ClienteRepository extends JpaRepository<ClienteModel, Long> {
	
	/**
	 * Assinatura do metodo que busca por nome do cliente.
	 * @param recebe um nome para realizar a busca.
	 * @return a busca com o nome do cliente.
	 * @since 08/2021
	 */
	List<ClienteModel> findByNome(String nome);
	
	/**
	 * Assinatura do metodo que busca por informações que contenham 
	 * determinados caracteres passado como paramentos.
	 * @param um nome ou um caracter para realizar a busca.
	 * @return registros que contenham o caracter.
	 * c
	 */
	List<ClienteModel> findByNomeContaining(String nome);
	
	/**
	 * 
	 * @param email
	 * @return
	 */
	List<ClienteModel> findByEmail(String email);
}
