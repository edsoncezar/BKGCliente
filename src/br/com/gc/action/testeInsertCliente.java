package br.com.gc.action;

import br.com.gc.dao.ClienteDAO;
import br.com.gc.vo.ClienteVO;

public class testeInsertCliente {

	public static void main(String[] args) {

		ClienteVO cliente = new ClienteVO();

		cliente.setNome("Edson");
		cliente.setCic("257.179.999-70");
		cliente.setRg("28.358.999-30");
		cliente.setEndereco("Rua Santa Ifigênia");
		cliente.setBairro("Centro");
		cliente.setCidade("São Paulo");
		cliente.setCep("09000-000");
		cliente.setTelefone("444444444");
		cliente.setCelular("9999999999");
		cliente.setDdd("011");
		cliente.setEstado("SP");
		cliente.setObservacao("TETETETETETETETETETETETTETETETE");

		ClienteDAO insereClienteDAO = new ClienteDAO();
		insereClienteDAO.insereCliente(cliente);

	}

}
