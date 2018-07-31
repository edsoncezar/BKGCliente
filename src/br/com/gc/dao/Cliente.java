package br.com.gc.dao;

import java.util.List;

import br.com.gc.vo.ClienteVO;

public interface Cliente {

	List<ClienteVO> getClienteCriteria(ClienteVO clienteVO);

	void insereCliente(ClienteVO cliente);

	List<ClienteVO> getAllDataCliente(ClienteVO clienteVO);

	Integer getIdCliente(ClienteVO clienteVO);

	void deleteCliente(Integer idCliente);

	void atualizaCliente(ClienteVO cliente);

	void exclusionCliente(Integer idCliente);

	List<ClienteVO> getAllItemCliente();

	void restoreCliente(ClienteVO cliente);

}
