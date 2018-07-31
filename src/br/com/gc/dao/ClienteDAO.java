/**
 *
 */
package br.com.gc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import br.com.gc.connection.ConnectionFactory;
import br.com.gc.vo.ClienteVO;

/**
 * @author Administrador
 *
 */
public class ClienteDAO implements Cliente{

	private Connection conn = null;

	public ClienteDAO() {

		this.conn = ConnectionFactory.getConnection();

	}

	public void insereCliente(ClienteVO cliente) {
		try {

			StringBuffer sb = new StringBuffer();
			sb.append(" insert into tb_cliente (nm_cliente, cic, rg, endereco, bairro, " +
					"cidade, cep, telefone, celular, ddd, estado, observacao )");
			sb.append(" values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

			PreparedStatement psmt = conn.prepareStatement(sb.toString());

			psmt.setString(1, cliente.getNome());
			psmt.setString(2, cliente.getCic());
			psmt.setString(3, cliente.getRg());
			psmt.setString(4, cliente.getEndereco());
			psmt.setString(5, cliente.getBairro());
			psmt.setString(6, cliente.getCidade());
			psmt.setString(7, cliente.getCep());
			psmt.setString(8, cliente.getTelefone());
			psmt.setString(9, cliente.getCelular());
			psmt.setString(10, cliente.getDdd());
			psmt.setString(11, cliente.getEstado());
			psmt.setString(12, cliente.getObservacao());

			psmt.executeUpdate();
			psmt.close();

			System.out.println("Cliente inserido");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

		}

	}


	public void restoreCliente(ClienteVO cliente) {
		try {

			StringBuffer sb = new StringBuffer();
			sb.append(" insert into tb_cliente (id_cliente, nm_cliente, cic, rg, endereco, bairro, " +
					"cidade, cep, telefone, celular, ddd, estado, observacao )");
			sb.append(" values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

			PreparedStatement psmt = conn.prepareStatement(sb.toString());

			psmt.setInt(1, cliente.getId());
			psmt.setString(2, cliente.getNome());
			psmt.setString(3, cliente.getCic());
			psmt.setString(4, cliente.getRg());
			psmt.setString(5, cliente.getEndereco());
			psmt.setString(6, cliente.getBairro());
			psmt.setString(7, cliente.getCidade());
			psmt.setString(8, cliente.getCep());
			psmt.setString(9, cliente.getTelefone());
			psmt.setString(10, cliente.getCelular());
			psmt.setString(11, cliente.getDdd());
			psmt.setString(12, cliente.getEstado());
			psmt.setString(13, cliente.getObservacao());

			psmt.executeUpdate();
			psmt.close();



		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			System.out.println("Cliente inserido");
		}

	}

	public void atualizaCliente(ClienteVO cliente) {
		try {

			StringBuffer sb = new StringBuffer();
			sb.append(" update tb_cliente set nm_cliente = ?, cic = ?, rg = ?, " +
					"endereco = ?, bairro = ?, cidade = ?, cep = ?, telefone = ?, " +
					"celular = ?, ddd = ?, estado = ?, observacao = ?, where id_cliente = ? ;");

			PreparedStatement psmt = conn.prepareStatement(sb.toString());

			psmt.setString(1, cliente.getNome());
			psmt.setString(2, cliente.getCic());
			psmt.setString(3, cliente.getRg());
			psmt.setString(4, cliente.getEndereco());
			psmt.setString(5, cliente.getBairro());
			psmt.setString(6, cliente.getCidade());
			psmt.setString(7, cliente.getCep());
			psmt.setString(8, cliente.getTelefone());
			psmt.setString(9, cliente.getCelular());
			psmt.setString(10, cliente.getDdd());
			psmt.setString(11, cliente.getEstado());
			psmt.setString(12, cliente.getObservacao());
			psmt.setInt(13, cliente.getId());

			psmt.executeUpdate();
			psmt.close();

			System.out.println("Cliente atualizado");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

		}

	}

	public List<ClienteVO> getClienteCriteria(ClienteVO clienteVO){

		List<ClienteVO> listaCliente = null;

		if(clienteVO.getNome().equalsIgnoreCase("") &&
		   clienteVO.getEndereco().equalsIgnoreCase("")	&&
		   clienteVO.getTelefone().equalsIgnoreCase("")){

			JOptionPane.showMessageDialog(null, "Favor entrar com um dos dados para a consulta");

		}else{


		StringBuilder select = new StringBuilder();
		select.append("SELECT t.nm_cliente, t.endereco, t.bairro, t.cidade, t.telefone ");
		select.append("FROM TB_CLIENTE t ");
		select.append("WHERE t.nm_cliente like '%"+clienteVO.getNome()+"%' ");
		select.append("AND t.endereco like '%"+clienteVO.getEndereco()+"%' ");
		select.append("AND t.telefone like '%"+clienteVO.getTelefone()+"%' ");

		try {
			ResultSet rs = null;
			PreparedStatement stm = null;

			stm = conn.prepareStatement(select.toString());
			rs = stm.executeQuery();

			listaCliente = new ArrayList<ClienteVO>();

			while(rs.next()){
				clienteVO = new ClienteVO();

				clienteVO.setNome(rs.getString(1));
				clienteVO.setEndereco(rs.getString(2));
				clienteVO.setBairro(rs.getString(3));
				clienteVO.setCidade(rs.getString(4));
				clienteVO.setTelefone(rs.getString(5));

				listaCliente.add(clienteVO);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
      }
		return listaCliente;
	}

	public List<ClienteVO> getAllDataCliente(ClienteVO clienteVO){

		List<ClienteVO> listaCliente = null;
		Produto produto = new ProdutoDAO();

		StringBuilder select = new StringBuilder();
		select.append("SELECT * FROM tb_cliente t ");
		select.append("where t.nm_cliente = '"+clienteVO.getNome()+"' ");
		select.append("and  t.endereco = '"+clienteVO.getEndereco()+"' ");
		select.append("and  t.bairro = '"+clienteVO.getBairro()+"' ");
		select.append("and  t.cidade = '"+clienteVO.getCidade()+"' ");
		select.append("and  t.telefone = '"+clienteVO.getTelefone()+"' ");

		try {
			ResultSet rs = null;
			PreparedStatement stm = null;

			stm = conn.prepareStatement(select.toString());
			rs = stm.executeQuery();

			listaCliente = new ArrayList<ClienteVO>();

			while(rs.next()){
				clienteVO = new ClienteVO();

				clienteVO.setId(rs.getInt(1));
				clienteVO.setNome(rs.getString(2));
				clienteVO.setCic(rs.getString(3));
				clienteVO.setRg(rs.getString(4));
				clienteVO.setEndereco(rs.getString(5));
				clienteVO.setBairro(rs.getString(6));
				clienteVO.setCidade(rs.getString(7));
				clienteVO.setCep(rs.getString(8));
				clienteVO.setTelefone(rs.getString(9));
				clienteVO.setCelular(rs.getString(10));
				clienteVO.setDdd(rs.getString(11));
				clienteVO.setEstado(rs.getString(12));
				clienteVO.setObservacao(rs.getString(13));
				clienteVO.setListaItens(produto.getListToCliente(clienteVO.getId()));
				listaCliente.add(clienteVO);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listaCliente;

	}

	public List<ClienteVO> getAllItemCliente(){

		List<ClienteVO> listaCliente = null;
		Produto produto = new ProdutoDAO();
		ClienteVO clienteVO;

		StringBuilder select = new StringBuilder();
		select.append("SELECT * FROM tb_cliente t ");

		try {
			ResultSet rs = null;
			PreparedStatement stm = null;

			stm = conn.prepareStatement(select.toString());
			rs = stm.executeQuery();

			listaCliente = new ArrayList<ClienteVO>();

			while(rs.next()){
				clienteVO = new ClienteVO();

				clienteVO.setId(rs.getInt(1));
				clienteVO.setNome(rs.getString(2));
				clienteVO.setCic(rs.getString(3));
				clienteVO.setRg(rs.getString(4));
				clienteVO.setEndereco(rs.getString(5));
				clienteVO.setBairro(rs.getString(6));
				clienteVO.setCidade(rs.getString(7));
				clienteVO.setCep(rs.getString(8));
				clienteVO.setTelefone(rs.getString(9));
				clienteVO.setCelular(rs.getString(10));
				clienteVO.setDdd(rs.getString(11));
				clienteVO.setEstado(rs.getString(12));
				clienteVO.setObservacao(rs.getString(13));
				clienteVO.setListaItens(produto.getListToCliente(clienteVO.getId()));
				listaCliente.add(clienteVO);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listaCliente;

	}

	public Integer getIdCliente(ClienteVO clienteVO){

		StringBuilder select = new StringBuilder();
		select.append("SELECT id_cliente FROM tb_cliente t ");
		select.append("where t.nm_cliente = '"+clienteVO.getNome()+"' ");
		select.append("and  t.cic = '"+clienteVO.getCic()+"' ");
		select.append("and  t.rg = '"+clienteVO.getRg()+"' ");
		select.append("and  t.cep = '"+clienteVO.getCep()+"' ");
		select.append("and  t.telefone = '"+clienteVO.getTelefone()+"' ");

		try {
			ResultSet rs = null;
			PreparedStatement stm = null;

			stm = conn.prepareStatement(select.toString());
			rs = stm.executeQuery();

			while(rs.next()){
				clienteVO = new ClienteVO();

				clienteVO.setId(rs.getInt(1));

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return clienteVO.getId();
	}

	public void deleteCliente(Integer idCliente) {
		try {

			StringBuffer sb = new StringBuffer();
			sb.append("delete from tb_cliente where id_cliente = "+"'"+idCliente+"'");

			PreparedStatement psmt = conn.prepareStatement(sb.toString());
			psmt.executeUpdate();
			psmt.close();

			System.out.println("Cliente deletado");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
            JOptionPane.showMessageDialog(null, "Cliente apagado !");
		}

	}

	public void exclusionCliente(Integer idCliente) {
		try {

			StringBuffer sb = new StringBuffer();
			sb.append("delete from tb_cliente where id_cliente = "+"'"+idCliente+"'");

			PreparedStatement psmt = conn.prepareStatement(sb.toString());
			psmt.executeUpdate();
			psmt.close();

			System.out.println("Cliente deletado");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
            //JOptionPane.showMessageDialog(null, "Cliente apagado !");
		}

	}

}
