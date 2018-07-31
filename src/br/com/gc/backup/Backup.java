/**
 *
 */
package br.com.gc.backup;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.swing.JOptionPane;

import br.com.gc.dao.Cliente;
import br.com.gc.dao.ClienteDAO;
import br.com.gc.utils.Componentes;
import br.com.gc.vo.ClienteVO;
import br.com.gc.vo.ProdutoVO;

/**
 * @author Administrador
 *
 */
public class Backup {

	 /**
     * @param args
     * @throws IOException
     */
    public void execute() throws IOException {

    	Cliente clienteDAO = new ClienteDAO();
    	Componentes componentes = new Componentes();

    	List<ClienteVO> listCliente = null;
    	listCliente = clienteDAO.getAllItemCliente();

        String arquivo = componentes.saveRecordTXT();

        PrintWriter output = new PrintWriter(new FileWriter(arquivo));

        for (Iterator iter = listCliente.iterator(); iter.hasNext();) {
			ClienteVO clienteVO = (ClienteVO) iter.next();

			output.print("C");
			output.print(";");
			output.print(clienteVO.getId());
			output.print(";");
			output.print(clienteVO.getNome());
			output.print(";");
			output.print(clienteVO.getCic());
			output.print(";");
			output.print(clienteVO.getRg());
			output.print(";");
			output.print(clienteVO.getEndereco());
			output.print(";");
			output.print(clienteVO.getBairro());
			output.print(";");
			output.print(clienteVO.getCidade());
			output.print(";");
			output.print(clienteVO.getCep());
			output.print(";");
			output.print(clienteVO.getTelefone());
			output.print(";");
			output.print(clienteVO.getCelular());
			output.print(";");
			output.print(clienteVO.getDdd());
			output.print(";");
			output.print(clienteVO.getEstado());
			output.print(";");
			output.print(clienteVO.getObservacao());
			output.print("\r\n");

			clienteVO.getListaItens();

			for (Iterator iterator = clienteVO.getListaItens().iterator(); iterator.hasNext();) {
				ProdutoVO produtoVO = (ProdutoVO) iterator.next();

				output.print("P");
				output.print(";");
				output.print(produtoVO.getIdProduto());
				output.print(";");
				output.print(produtoVO.getProduto());
				output.print(";");
				output.print(produtoVO.getValor());
				output.print(";");
				output.print(produtoVO.getIdCliente());
				output.print(";");
				output.print(produtoVO.getQuantidade());
				output.print("\r\n");

			}

		}

        output.close();
        output.flush();

        JOptionPane.showMessageDialog(null, "Arquivo gerado com sucesso !");

    }

    public static void main(String[] args) {
		Backup backup = new Backup();
		try {
			backup.execute();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
