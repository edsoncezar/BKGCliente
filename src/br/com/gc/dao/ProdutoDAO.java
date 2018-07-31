package br.com.gc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import br.com.gc.connection.ConnectionFactory;
import br.com.gc.vo.ProdutoVO;


/**
 *
 * @author Administrador
 *
 */
public class ProdutoDAO implements Produto{

	private Connection conn = null;

	public ProdutoDAO() {

		this.conn = ConnectionFactory.getConnection();

	}
	public void insereProduto(ProdutoVO produto) {
		try {

			StringBuffer sb = new StringBuffer();
			sb.append(" insert into tb_produto ( produto, valor, id_cliente, quantidade )");
			sb.append(" values (?, ?, ?, ?)");

			PreparedStatement psmt = conn.prepareStatement(sb.toString());

			psmt.setString(1, produto.getProduto());
			psmt.setString(2, produto.getValor());
			psmt.setInt(3, produto.getIdCliente());
			psmt.setInt(4, produto.getQuantidade());

			psmt.executeUpdate();
			psmt.close();


		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			System.out.println("Produto inserido");
		}
	}

	public void restoreProduto(ProdutoVO produto) {
		try {

			StringBuffer sb = new StringBuffer();
			sb.append(" insert into tb_produto ( id_produto, produto, valor, id_cliente, quantidade )");
			sb.append(" values (?, ?, ?, ?, ?)");

			PreparedStatement psmt = conn.prepareStatement(sb.toString());

			psmt.setInt(1, produto.getIdProduto());
			psmt.setString(2, produto.getProduto());
			psmt.setString(3, produto.getValor());
			psmt.setInt(4, produto.getIdCliente());
			psmt.setInt(5, produto.getQuantidade());

			psmt.executeUpdate();
			psmt.close();


		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			System.out.println("Produto inserido");
		}
	}

	public List<ProdutoVO> getLista() {

		List<ProdutoVO> list = null;

		try {

			StringBuffer sbSelect = new StringBuffer();
			sbSelect.append(" select * from tb_produto");

			ResultSet rs = null;

			PreparedStatement stm = null;

			stm = conn.prepareStatement(sbSelect.toString());
			rs = stm.executeQuery();

			list = new ArrayList<ProdutoVO>();

			while (rs.next()) {

				ProdutoVO produto = new ProdutoVO();
				produto.setIdProduto(rs.getInt(1));
				produto.setProduto(rs.getString(2));
				produto.setValor(rs.getString(3));
				produto.setIdCliente(rs.getInt(4));
				produto.setQuantidade(rs.getInt(5));

				list.add(produto);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}

	public List<ProdutoVO> getListToCliente(Integer idCliente) {

		List<ProdutoVO> list = null;

		try {

			StringBuffer sbSelect = new StringBuffer();
			sbSelect.append(" select * from tb_produto where id_cliente ='"+idCliente+"'");

			ResultSet rs = null;
			PreparedStatement stm = null;

			stm = conn.prepareStatement(sbSelect.toString());
			rs = stm.executeQuery();

			list = new ArrayList<ProdutoVO>();

			while (rs.next()) {

				ProdutoVO produto = new ProdutoVO();

				produto.setIdProduto(rs.getInt(1));
				produto.setProduto(rs.getString(2));
				produto.setValor(rs.getString(3));
				produto.setIdCliente(rs.getInt(4));
				produto.setQuantidade(rs.getInt(5));

				list.add(produto);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}

	public int idMaximoCliente() {

		int max = 0;

		try {
			StringBuffer sbSelect = new StringBuffer();
			sbSelect.append("select max(id_cliente) from tb_cliente ");

			ResultSet rs = null;

			PreparedStatement stm = null;

			stm = conn.prepareStatement(sbSelect.toString());
			rs = stm.executeQuery();

			while (rs.next()) {
				max = rs.getInt(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return max;
	}

	public void deleteProduto(Integer idCliente) {
		try {

			StringBuffer sb = new StringBuffer();
			sb.append("delete from tb_produto where id_cliente = "+"'"+idCliente+"'");

			PreparedStatement psmt = conn.prepareStatement(sb.toString());
			psmt.executeUpdate();
			psmt.close();

			System.out.println("Produto deletado");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
            //JOptionPane.showMessageDialog(null, "Produtos apagados !");
		}
	}

	public void deleteProdutoToId(Integer idProduto) {
		try {

			StringBuffer sb = new StringBuffer();
			sb.append("delete from tb_produto where id_produto = "+"'"+idProduto+"'");

			PreparedStatement psmt = conn.prepareStatement(sb.toString());
			psmt.executeUpdate();
			psmt.close();

			System.out.println("Produtos deletados");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
            JOptionPane.showMessageDialog(null, "Produto apagado !");
		}
	}

	public void exclusionProdutoToId(Integer idProduto) {
		try {

			StringBuffer sb = new StringBuffer();
			sb.append("delete from tb_produto where id_produto = "+"'"+idProduto+"'");

			PreparedStatement psmt = conn.prepareStatement(sb.toString());
			psmt.executeUpdate();
			psmt.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			System.out.println("Produtos deletados");
		}
	}
	public Integer getIdProduto(ProdutoVO produtoVO){

		Integer idProduto = 0;

		StringBuilder select = new StringBuilder();
		select.append("SELECT id_produto FROM tb_produto p ");
		select.append("where p.produto = '"+produtoVO.getProduto()+"' ");
		select.append("and  p.valor = '"+produtoVO.getValor()+"' ");
		select.append("and  p.quantidade = '"+produtoVO.getQuantidade()+"' ");
		select.append("and  p.id_cliente = '"+produtoVO.getIdCliente()+"' ");

		try {
			ResultSet rs = null;
			PreparedStatement stm = null;

			stm = conn.prepareStatement(select.toString());
			rs = stm.executeQuery();

			while(rs.next()){
				produtoVO = new ProdutoVO();

				idProduto = rs.getInt(1);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return idProduto;
	}
}
