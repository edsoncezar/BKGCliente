/**
 *
 */
package br.com.gc.backup;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JOptionPane;

import br.com.gc.dao.Cliente;
import br.com.gc.dao.ClienteDAO;
import br.com.gc.dao.Produto;
import br.com.gc.dao.ProdutoDAO;
import br.com.gc.utils.Componentes;
import br.com.gc.vo.ClienteVO;
import br.com.gc.vo.ProdutoVO;

/**
 * @author Administrador
 *
 */
public class Restore {

	   public void execute() throws IOException {

		    Componentes componentes = new Componentes();
		    Cliente cliente = new ClienteDAO();
		    Produto produto = new ProdutoDAO();
		    ClienteVO clienteVO = new ClienteVO();
		    ProdutoVO produtoVO = new ProdutoVO();

	    	String arquivo = componentes.openFiles();
	        String linha = "";

	        FileReader file = new FileReader(arquivo);
	        BufferedReader reader = new BufferedReader(file);

	        while((linha = reader.readLine()) != null){
	            String[] leitura = linha.split(";");

	            if(leitura[0].equals("C")){

	            	cliente.exclusionCliente(Integer.parseInt(leitura[1]));

	            	clienteVO.setId(Integer.parseInt(leitura[1]));
					clienteVO.setNome(leitura[2]);
					clienteVO.setCic(leitura[3]);
					clienteVO.setRg(leitura[4]);
					clienteVO.setEndereco(leitura[5]);
					clienteVO.setBairro(leitura[6]);
					clienteVO.setCidade(leitura[7]);
					clienteVO.setCep(leitura[8]);
					clienteVO.setTelefone(leitura[9]);
					clienteVO.setCelular(leitura[10]);
					clienteVO.setDdd(leitura[11]);
					clienteVO.setEstado(leitura[12]);
					clienteVO.setObservacao(leitura[13]);

					cliente.restoreCliente(clienteVO);


	            }

	            if(leitura[0].equals("P")){

	            	produto.exclusionProdutoToId(Integer.parseInt(leitura[1]));

	            	produtoVO.setIdProduto(Integer.parseInt(leitura[1]));
					produtoVO.setProduto(leitura[2]);
					produtoVO.setValor(leitura[3]);
					produtoVO.setIdCliente(Integer.parseInt(leitura[4]));
					produtoVO.setQuantidade(Integer.parseInt(leitura[5]));

					produto.restoreProduto(produtoVO);

	            }
	        }
	        JOptionPane.showMessageDialog(null, "Leitura finalizada !");
	    }

	   public static void main(String[] args) {
		Restore restore = new Restore();
		try {
			restore.execute();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
