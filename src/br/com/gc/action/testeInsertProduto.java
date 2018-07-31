package br.com.gc.action;

import br.com.gc.dao.ProdutoDAO;
import br.com.gc.vo.ProdutoVO;

public class testeInsertProduto {

	public static void main(String[] args) {

		ProdutoVO produto = new ProdutoVO();

		produto.setProduto("TESTE");
		produto.setValor("3000");
		produto.setIdCliente(1);
		produto.setQuantidade(10);

		ProdutoDAO insereProdutoDAO = new ProdutoDAO();
		insereProdutoDAO.insereProduto(produto);


	}

}
