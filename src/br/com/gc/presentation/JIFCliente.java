/*
 * @(#)JFPrincipal.java 28/08/2005
 *
 * Copyright (c) 2005 X-Team.
 * All Rights Reserved.
 */
package br.com.gc.presentation;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.TableModel;

import br.com.gc.dao.Cliente;
import br.com.gc.dao.ClienteDAO;
import br.com.gc.dao.Produto;
import br.com.gc.dao.ProdutoDAO;
import br.com.gc.utils.Validation;
import br.com.gc.vo.ClienteVO;
import br.com.gc.vo.ProdutoVO;

/**
 * <strong>Definição</strong><br>
 * Gera a tela para cadastro de clientes.
 * <br>
 * <strong>Histórico de Versões</strong><br>
 * <br>
 * 1.0 - Criacão da Classe
 * <br>
 *
 * @author
 * @author
 * @version 1.0 - 10/02/2009 -
 */
public class JIFCliente extends JInternalFrame  implements ActionListener {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    JLabel lblCliente = new JLabel("Cliente :");

    JLabel lblCPF = new JLabel("CPF :");

    JLabel lblRG = new JLabel("RG :");

    JLabel lblTelefone = new JLabel("Telefone :");

    JLabel lblCelular = new JLabel("Celular :");

    JLabel lblLogradouro = new JLabel("Endereço :");

    JLabel lblObservacao = new JLabel("Observação :");

    JLabel lblBairro = new JLabel("Bairro :");

    JLabel lblCidade = new JLabel("Cidade :");

    JLabel lblCep = new JLabel("Cep :");

    JLabel lblDdd = new JLabel("Ddd :");

    JLabel lblEstado = new JLabel("Estado :");

    JLabel lblTeste = new JLabel("       ");

    JTextArea txtObs = new JTextArea(4,18);

    JTextField txtLogradouro = new JTextField(18);

    JTextField txtCpf = new JTextField(18);

    JTextField txtRg = new JTextField(18);

    JTextField txtTelefone = new JTextField(18);

    JTextField txtCelular = new JTextField(18);

    JTextField txtCliente = new JTextField(18);

    JTextField txtCidade = new JTextField(18);

    JTextField txtBairro = new JTextField(18);

    JTextField txtCep = new JTextField(18);

    JTextField txtDdd = new JTextField(8);

    JTextField txtEstado = new JTextField(8);

    JButton btnInsere = new JButton("Salvar");

    JButton btnExcluirCliente = new JButton("Excluir");

    JButton btnAdicionaLinha = new JButton(">>");

    JButton btnRemoveLinha = new JButton("<<");

    JTable table = new JTable();

    JScrollPane pane;

    JScrollPane scrollJTextArea;

    Integer idClienteGlobal;

    // Construtor
    public JIFCliente(List clienteVO) {

        super("Cadastro de JIFCliente");

        String[] columnNames = { "Quant.","Produto", "Valor" };

        Object[][] data = {,};

        table.setModel(new javax.swing.table.DefaultTableModel(data, columnNames)
            {}
        );

        javax.swing.table.DefaultTableModel dtm = (javax.swing.table.DefaultTableModel)table.getModel();

        table.setPreferredScrollableViewportSize(new Dimension(500, 70));
        table.getColumnModel().getColumn(0).setPreferredWidth(50);
        table.getColumnModel().getColumn(1).setPreferredWidth(150);
        table.setFillsViewportHeight(true);
        pane = new JScrollPane(table);

        // Titulo do JIFCliente.
        this.btnInsere.addActionListener(this);
        this.btnExcluirCliente.addActionListener(this);
        this.btnAdicionaLinha.addActionListener(this);
        this.btnRemoveLinha.addActionListener(this);

        txtObs.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        txtObs.setSize(4, 18);
        txtObs.setLineWrap(true);
        scrollJTextArea = new JScrollPane(txtObs);
        txtLogradouro.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        txtCpf.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        txtRg.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        txtTelefone.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        txtCelular.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        txtCliente.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        txtBairro.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        txtCep.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        txtCidade.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        txtEstado.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        txtDdd.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        if(clienteVO != null){
           for (Iterator iter = clienteVO.iterator(); iter.hasNext();) {
            ClienteVO cliente = (ClienteVO) iter.next();

            txtLogradouro.setText(cliente.getEndereco());
            txtCpf.setText(cliente.getCic());
            txtRg.setText(cliente.getRg());
            txtTelefone.setText(cliente.getTelefone());
            txtCelular.setText(cliente.getCelular());
            txtCliente.setText(cliente.getNome());
            txtBairro.setText(cliente.getBairro());
            txtCep.setText(cliente.getCep());
            txtCidade.setText(cliente.getCidade());
            txtEstado.setText(cliente.getEstado());
            txtDdd.setText(cliente.getDdd());
            txtObs.setText(cliente.getObservacao());

            Cliente clienteDAO = new ClienteDAO();

            idClienteGlobal = clienteDAO.getIdCliente(cliente);

            for (Iterator iterator = cliente.getListaItens().iterator(); iterator.hasNext();) {
                ProdutoVO produtoVO = (ProdutoVO) iterator.next();

                dtm.addRow(new Object[]{produtoVO.getQuantidade(),produtoVO.getProduto(),produtoVO.getValor()});
            }

        }
        }

        JPanel painel = new JPanel();
        JPanel painelBotoes = new JPanel();
        int x = 0;
        int y = 0;

        Container container = getContentPane();
        GridBagLayout gridBagLayout = new GridBagLayout();
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        painel.setLayout(gridBagLayout);

        gridBagConstraints.insets = new Insets(10, -60, 0, 0);
        gridBagConstraints.gridx = x++;
        gridBagConstraints.gridy = y;
        gridBagLayout.setConstraints(this.lblCliente, gridBagConstraints);
        painel.add(this.lblCliente);

        gridBagConstraints.insets = new Insets(10, -300, 0, 0);
        gridBagConstraints.gridx = x++;
        gridBagConstraints.gridy = y;
        gridBagLayout.setConstraints(this.txtCliente, gridBagConstraints);
        painel.add(this.txtCliente);

        gridBagConstraints.insets = new Insets(10, -290, 0, 0);
        gridBagConstraints.gridx = x++;
        gridBagConstraints.gridy = y;
        gridBagLayout.setConstraints(this.lblLogradouro, gridBagConstraints);
        painel.add(this.lblLogradouro);

        gridBagConstraints.insets = new Insets(10, -100, 0, 0);
        gridBagConstraints.gridx = x++;
        gridBagConstraints.gridy = y;
        gridBagLayout.setConstraints(this.txtLogradouro, gridBagConstraints);
        painel.add(this.txtLogradouro);

        x = 0;
        y++;

        gridBagConstraints.insets = new Insets(10, -60, 0, 0);
        gridBagConstraints.gridx = x++;
        gridBagConstraints.gridy = y;
        gridBagLayout.setConstraints(this.lblBairro, gridBagConstraints);
        painel.add(this.lblBairro);

        gridBagConstraints.insets = new Insets(10, -300, 0, 0);
        gridBagConstraints.gridx = x++;
        gridBagConstraints.gridy = y;
        gridBagLayout.setConstraints(this.txtBairro, gridBagConstraints);
        painel.add(this.txtBairro);

        gridBagConstraints.insets = new Insets(10, -272, 0, 0);
        gridBagConstraints.gridx = x++;
        gridBagConstraints.gridy = y;
        gridBagLayout.setConstraints(this.lblCidade, gridBagConstraints);
        painel.add(this.lblCidade);

        gridBagConstraints.insets = new Insets(10, -100, 0, 0);
        gridBagConstraints.gridx = x++;
        gridBagConstraints.gridy = y;
        gridBagLayout.setConstraints(this.txtCidade, gridBagConstraints);
        painel.add(this.txtCidade);

        x = 0;
        y++;

        gridBagConstraints.insets = new Insets(10, -65, 0, 0);
        gridBagConstraints.gridx = x++;
        gridBagConstraints.gridy = y;
        gridBagLayout.setConstraints(this.lblEstado, gridBagConstraints);
        painel.add(this.lblEstado);

        gridBagConstraints.insets = new Insets(10, -410, 0, 0);
        gridBagConstraints.gridx = x++;
        gridBagConstraints.gridy = y;
        gridBagLayout.setConstraints(this.txtEstado, gridBagConstraints);
        painel.add(this.txtEstado);

        gridBagConstraints.insets = new Insets(10, -255, 0, 0);
        gridBagConstraints.gridx = x++;
        gridBagConstraints.gridy = y;
        gridBagLayout.setConstraints(this.lblCep, gridBagConstraints);
        painel.add(this.lblCep);

        gridBagConstraints.insets = new Insets(10, -100, 0, 0);
        gridBagConstraints.gridx = x++;
        gridBagConstraints.gridy = y;
        gridBagLayout.setConstraints(this.txtCep, gridBagConstraints);
        painel.add(this.txtCep);

        x = 0;
        y++;

        gridBagConstraints.insets = new Insets(10, -50, 0, 0);
        gridBagConstraints.gridx = x++;
        gridBagConstraints.gridy = y;
        gridBagLayout.setConstraints(this.lblCPF, gridBagConstraints);
        painel.add(this.lblCPF);

        gridBagConstraints.insets = new Insets(10, -300, 0, 0);
        gridBagConstraints.gridx = x++;
        gridBagConstraints.gridy = y;
        gridBagLayout.setConstraints(this.txtCpf, gridBagConstraints);
        painel.add(this.txtCpf);

        gridBagConstraints.insets = new Insets(10, -250, 0, 0);
        gridBagConstraints.gridx = x++;
        gridBagConstraints.gridy = y;
        gridBagLayout.setConstraints(this.lblRG, gridBagConstraints);
        painel.add(this.lblRG);

        gridBagConstraints.insets = new Insets(10, -100, 0, 0);
        gridBagConstraints.gridx = x++;
        gridBagConstraints.gridy = y;
        gridBagLayout.setConstraints(this.txtRg, gridBagConstraints);
        painel.add(this.txtRg);

        x = 0;
        y++;

        gridBagConstraints.insets = new Insets(10, -50, 0, 0);
        gridBagConstraints.gridx = x++;
        gridBagConstraints.gridy = y;
        gridBagLayout.setConstraints(this.lblDdd, gridBagConstraints);
        painel.add(this.lblDdd);

        gridBagConstraints.insets = new Insets(10, -410, 0, 0);
        gridBagConstraints.gridx = x++;
        gridBagConstraints.gridy = y;
        gridBagLayout.setConstraints(this.txtDdd, gridBagConstraints);
        painel.add(this.txtDdd);

        gridBagConstraints.insets = new Insets(10, -280, 0, 0);
        gridBagConstraints.gridx = x++;
        gridBagConstraints.gridy = y;
        gridBagLayout.setConstraints(this.lblTelefone, gridBagConstraints);
        painel.add(this.lblTelefone);

        gridBagConstraints.insets = new Insets(10, -100, 0, 0);
        gridBagConstraints.gridx = x++;
        gridBagConstraints.gridy = y;
        gridBagLayout.setConstraints(this.txtTelefone, gridBagConstraints);
        painel.add(this.txtTelefone);

        x = 0;
        y+=2;

        gridBagConstraints.insets = new Insets(10, -70, 0, 0);
        gridBagConstraints.gridx = x++;
        gridBagConstraints.gridy = y;
        gridBagLayout.setConstraints(this.lblCelular, gridBagConstraints);
        painel.add(this.lblCelular);

        gridBagConstraints.insets = new Insets(10, -300, 0, 0);
        gridBagConstraints.gridx = x++;
        gridBagConstraints.gridy = y;
        gridBagLayout.setConstraints(this.txtCelular, gridBagConstraints);
        painel.add(this.txtCelular);

        gridBagConstraints.insets = new Insets(10, -305, 0, 0);
        gridBagConstraints.gridx = x++;
        gridBagConstraints.gridy = y;
        gridBagLayout.setConstraints(this.lblObservacao, gridBagConstraints);
        painel.add(this.lblObservacao);

        gridBagConstraints.insets = new Insets(10, -100, 0, 0);
        gridBagConstraints.gridx = x++;
        gridBagConstraints.gridy = y;
        gridBagLayout.setConstraints(this.scrollJTextArea, gridBagConstraints);
        painel.add(this.scrollJTextArea);

        x = 0;
        y+=2;

        gridBagConstraints.insets = new Insets(10, -300, 0, 0);
        gridBagConstraints.gridx = x++;
        gridBagConstraints.gridy = y;
        gridBagLayout.setConstraints(this.lblTeste, gridBagConstraints);
        painel.add(this.lblTeste);

        gridBagConstraints.insets = new Insets(10, -0, 0, 0);
        gridBagConstraints.gridx = x++;
        gridBagConstraints.gridy = y;
        gridBagLayout.setConstraints(this.pane, gridBagConstraints);
        painel.add(this.pane);

        // Adicona os componentes de tela ao conteiner.
        container.add(painel);

        // Monta o painel dos botões.
        painelBotoes.add(this.btnAdicionaLinha);
        painelBotoes.add(this.btnRemoveLinha);
        painelBotoes.add(this.btnInsere);
        painelBotoes.add(this.btnExcluirCliente);

        x = 1;
        y++;
        gridBagConstraints.insets = new Insets(10, 0, 0, 0);
        gridBagConstraints.gridx = x++;
        gridBagConstraints.gridy = y;
        gridBagLayout.setConstraints(painelBotoes, gridBagConstraints);
        painel.add(painelBotoes);

        this.setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);

        setSize(780, 400);
        setVisible(true);
    }

    // main
    static public void main(String args[]) {
        List lista = new ArrayList();
        JIFCliente cliente = new JIFCliente(lista);
        cliente.show();
    }

    /* (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == this.btnRemoveLinha) {
                int[] l = table.getSelectedRows();
                javax.swing.table.DefaultTableModel dtm = (javax.swing.table.DefaultTableModel)table.getModel();
                ProdutoVO produtoVO = new ProdutoVO();
                Produto produto = new ProdutoDAO();
                int[] selRows;
                selRows = table.getSelectedRows();
                Object value;

                for (int i= 0; i < 3 ; i++){
                   TableModel tm = table.getModel();
                   value = tm.getValueAt(selRows[0], i);
                   switch (i) {
                    case 0:
                       produtoVO.setQuantidade(Integer.parseInt(value.toString()));
                        break;
                   case 1:
                       produtoVO.setProduto(String.valueOf(value));
                        break;
                   case 2:
                       produtoVO.setValor(String.valueOf(value));
                       break;
                    default:
                        break;
                    }
                System.out.println("INICIALIZAÇÃO : " +value);
                }

                ClienteVO clienteVO = new ClienteVO();
                Cliente cliente = new ClienteDAO();
                Integer idCliente = 0;
                Integer idProduto = 0;

                clienteVO.setNome(txtCliente.getText());
                clienteVO.setCic(txtCpf.getText());
                clienteVO.setRg(txtRg.getText());
                clienteVO.setCep(txtCep.getText());
                clienteVO.setTelefone(txtTelefone.getText());

                idCliente = cliente.getIdCliente(clienteVO);

                produtoVO.setIdCliente(idCliente);

                idProduto = produto.getIdProduto(produtoVO);

                produto.deleteProdutoToId(idProduto);

                for(int i = (l.length-1); i >= 0; --i)
                      dtm.removeRow(l[i]);
        }

        if (e.getSource() == this.btnExcluirCliente) {

            excluirCliente();
       }

        // Caso o botão limpar foi pressionado.
        if (e.getSource() == this.btnInsere) {
            Cliente cliente = new ClienteDAO();
            Produto produtoDAO = new ProdutoDAO();
            ClienteVO clienteVO = new ClienteVO();
            ProdutoVO produtoVO = new ProdutoVO();

            excluirClienteGlobal();

            Object quant = "";
            Object produto = "";
            Object valor = "";

            Validation validacion = new Validation();

            if(validacion.validateNulo(txtCliente.getText(),"Cliente")){
              if(validacion.validateNulo(txtLogradouro.getText(),"Endereço")){
 
            clienteVO.setNome(txtCliente.getText());
            clienteVO.setCic(txtCpf.getText());
            clienteVO.setRg(txtRg.getText());
            clienteVO.setEndereco(txtLogradouro.getText());
            clienteVO.setBairro(txtBairro.getText());
            clienteVO.setCidade(txtCidade.getText());
            clienteVO.setCep(txtCep.getText());
            clienteVO.setTelefone(txtTelefone.getText());
            clienteVO.setCelular(txtCelular.getText());
            clienteVO.setDdd(txtDdd.getText());
            clienteVO.setEstado(txtEstado.getText());
            clienteVO.setObservacao(txtObs.getText());

            cliente.insereCliente(clienteVO);

            javax.swing.table.DefaultTableModel dtm = (javax.swing.table.DefaultTableModel)table.getModel();
            int count = 0;

            int maximo = produtoDAO.idMaximoCliente();

        do {
                quant = dtm.getValueAt(count, 0) == null ? 0 : dtm.getValueAt(count, 0)  ;
                produto = dtm.getValueAt(count, 1) == null ? "" :dtm.getValueAt(count, 1);
                valor = dtm.getValueAt(count, 2) == null ? "" :dtm.getValueAt(count, 2);

                produtoVO.setQuantidade(Integer.valueOf(quant.toString()));
                produtoVO.setProduto(produto.toString());
                produtoVO.setValor(valor.toString());
                produtoVO.setIdCliente(maximo);

                produtoDAO.insereProduto(produtoVO);

                count++;

        } while (count != dtm.getRowCount());
             JOptionPane.showMessageDialog(null, "Cadastro realizado !");
        }

         }
        }

        // Caso o botão Adicionar Linha seja pressionado.
        if (e.getSource() == this.btnAdicionaLinha) {
            javax.swing.table.DefaultTableModel dtm = (javax.swing.table.DefaultTableModel)table.getModel();
            dtm.addRow(new Object[]{});
        }
      }



	/**
	 *
	 */
	private void excluirCliente() {
		ClienteVO clienteVO = new ClienteVO();
		Cliente cliente = new ClienteDAO();
		Produto produto = new ProdutoDAO();
		Integer idCliente = 0;

		clienteVO.setNome(txtCliente.getText());
		clienteVO.setCic(txtCpf.getText());
		clienteVO.setRg(txtRg.getText());
		clienteVO.setCep(txtCep.getText());
		clienteVO.setTelefone(txtTelefone.getText());

		idCliente = cliente.getIdCliente(clienteVO);
		produto.deleteProduto(idCliente);
		cliente.deleteCliente(idCliente);

		 txtLogradouro.setText("");
         txtCpf.setText("");
         txtRg.setText("");
         txtTelefone.setText("");
         txtCelular.setText("");
         txtCliente.setText("");
         txtBairro.setText("");
         txtCep.setText("");
         txtCidade.setText("");
         txtEstado.setText("");
         txtDdd.setText("");
         txtObs.setText("");

		javax.swing.table.DefaultTableModel dtm = (javax.swing.table.DefaultTableModel)table.getModel();

		 if (table.getRowCount() > 0){
			   for (int i=0;i<=table.getRowCount();i++){
			        	dtm.removeRow(i);
			   }
		 }
	}

	/**
	 *
	 */
	private void excluirClienteGlobal() {
		Cliente cliente = new ClienteDAO();
		Produto produto = new ProdutoDAO();

		produto.deleteProduto(idClienteGlobal);
		cliente.exclusionCliente(idClienteGlobal);

	}
}
