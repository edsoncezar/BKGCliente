package br.com.gc.dao;

import java.util.List;

import br.com.gc.vo.ProdutoVO;

public interface Produto {

	void insereProduto(ProdutoVO produto);

	List<ProdutoVO> getListToCliente(Integer idCliente);

	void deleteProduto(Integer idCliente);

	Integer getIdProduto(ProdutoVO produtoVO);

	void deleteProdutoToId(Integer idProduto);

	void exclusionProdutoToId(Integer idProduto);

	void restoreProduto(ProdutoVO produto);

	int idMaximoCliente();

}
