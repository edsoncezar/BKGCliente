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
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

import jxl.read.biff.BiffException;
import br.com.gc.dao.Cliente;
import br.com.gc.dao.ClienteDAO;
import br.com.gc.relatorio.RelatorioPersil;
import br.com.gc.vo.ClienteVO;

/**
 * <strong>Definição</strong><br>
 * Gera a tela de consulta.
 * <br>
 * <strong>Histórico de Versões</strong><br>
 * <br>
 * 1.0 - Criacão da Classe
 * <br>
 *
 * @author
 * @version 1.0 - 10/02/2009 -
 */
public class JIFConsulta extends JInternalFrame  implements ActionListener {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	JLabel lblCliente = new JLabel("Cliente :");

	JLabel lblTelefone = new JLabel("Telefone :");

	JLabel lblLogradouro = new JLabel("Logradouro :");

	JLabel lblIncrementoTela = new JLabel("    ");

	JTextField txtLogradouro = new JTextField(18);

	JTextField txtTelefone = new JTextField(18);

	JTextField txtCliente = new JTextField(18);

	JButton btnVenda = new JButton("Carregar");

	JButton btnRelatorio = new JButton("Relatório");

	JTable table = new JTable();

	JScrollPane pane;

	String[] columnNames = { "Cliente","Endereço", "Bairro", "Cidade",
	"Telefone"};

    Object[][] data = {, };

	// Construtor
	public JIFConsulta(JDesktopPane jdesktopPane) {

		super("Cadastro de JIFConsulta");

		final JDesktopPane desktopPane = jdesktopPane;

	    TableModel dataModel = new AbstractTableModel() {
   	     public int getColumnCount() { return columnNames.length; }
   	     public int getRowCount() { return data.length;}
   	     public Object getValueAt(int row, int col) {return data[row][col];}
   	     public String getColumnName(int column) {return columnNames[column];}
   	     public Class getColumnClass(int col) {return getValueAt(0,col).getClass();}
   	     public void setValueAt(Object aValue, int row, int column) {
   	    	 data[row][column] = aValue;
   	     }
   	    };

	    table.setModel(new javax.swing.table.DefaultTableModel(data, columnNames)
	        {
	    	public boolean isCellEditable(int rowIndex, int mColIndex) {
	            return false;
	        }
	    	}
	    );

	    table.addKeyListener(new java.awt.event.KeyAdapter() {
	    	             public void keyReleased(java.awt.event.KeyEvent evt) {
	    	                 if(evt.getKeyCode()==evt.VK_RIGHT
	    	                 || evt.getKeyCode()==evt.VK_LEFT
	    	                 || evt.getKeyCode()==evt.VK_UP
	    	                 || evt.getKeyCode()==evt.VK_DOWN){
	    	                	 table.getTableHeader().repaint();
	    	                	 table.repaint();
	    	                 }
	    	             }
	    	         });

	    table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
	      public void valueChanged(ListSelectionEvent e) {
	    	    if (!e.getValueIsAdjusting() /* outros testes*/) {
	    	    	 int maxRows;
	    	    	 int[] selRows;
	    	    	 Object value;

	    	    	selRows = table.getSelectedRows();

	    	    	ClienteVO clienteVO = new ClienteVO();
	    	    	Cliente cliente = new ClienteDAO();
	    	    	List listaCliente = new ArrayList();

	    	        if(selRows.length > 0) {
	    	          for (int i= 0; i < 5 ; i++){
	    	            TableModel tm = table.getModel();
	    	            value = tm.getValueAt(selRows[0],i);

	    	         switch (i) {
						case 0:
							clienteVO.setNome(String.valueOf(value));
							break;
                        case 1:
                        	clienteVO.setEndereco(String.valueOf(value));
							break;
                        case 2:
                        	clienteVO.setBairro(String.valueOf(value));
	                        break;
                        case 3:
                        	clienteVO.setCidade(String.valueOf(value));
	                        break;
                        case 4:
                        	clienteVO.setTelefone(String.valueOf(value));
	                        break;
						default:
							break;
						}
	    	            System.out.println("Selecao : " + value );
	    	            }
	    	          listaCliente = cliente.getAllDataCliente(clienteVO);
	    	        }

	    	    	JInternalFrame jiframeCliente = new JIFCliente(listaCliente);
	    	    	jiframeCliente.setResizable(true);
                    jiframeCliente.setClosable(true);
                    jiframeCliente.setVisible(true);
                    desktopPane.add(jiframeCliente);
                    disposeOnClosed();

	    	    }
	    }});

		table.setPreferredScrollableViewportSize(new Dimension(500, 70));
		table.getColumnModel().getColumn(1).setPreferredWidth(150);
		table.setFillsViewportHeight(true);
		pane = new JScrollPane(table);

		// Titulo do JIFCliente.
		this.btnVenda.addActionListener(this);
		this.btnRelatorio.addActionListener(this);

		txtLogradouro.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		txtTelefone.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		txtCliente.setBorder(BorderFactory.createLineBorder(Color.BLACK));

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

		gridBagConstraints.insets = new Insets(10, -490, 0, 0);
		gridBagConstraints.gridx = x++;
		gridBagConstraints.gridy = y;
		gridBagLayout.setConstraints(this.lblLogradouro, gridBagConstraints);
		painel.add(this.lblLogradouro);

		gridBagConstraints.insets = new Insets(10, -200, 0, 0);
		gridBagConstraints.gridx = x++;
		gridBagConstraints.gridy = y;
		gridBagLayout.setConstraints(this.txtLogradouro, gridBagConstraints);
		painel.add(this.txtLogradouro);

		x = 0;
		y++;

		gridBagConstraints.insets = new Insets(10, -70, 0, 0);
		gridBagConstraints.gridx = x++;
		gridBagConstraints.gridy = y;
		gridBagLayout.setConstraints(this.lblTelefone, gridBagConstraints);
		painel.add(this.lblTelefone);

		gridBagConstraints.insets = new Insets(10, -300, 0, 0);
		gridBagConstraints.gridx = x++;
		gridBagConstraints.gridy = y;
		gridBagLayout.setConstraints(this.txtTelefone, gridBagConstraints);
		painel.add(this.txtTelefone);

		gridBagConstraints.insets = new Insets(10, -272, 0, 0);
		gridBagConstraints.gridx = x++;
		gridBagConstraints.gridy = y;
		gridBagLayout.setConstraints(this.lblIncrementoTela, gridBagConstraints);
		painel.add(this.lblIncrementoTela);

		gridBagConstraints.insets = new Insets(10, -100, 0, 0);
		gridBagConstraints.gridx = x++;
		gridBagConstraints.gridy = y;
		gridBagLayout.setConstraints(this.lblIncrementoTela, gridBagConstraints);
		painel.add(this.lblIncrementoTela);

		x = 0;
		y+=2;

		gridBagConstraints.insets = new Insets(10, -500, 0, 0);
		gridBagConstraints.gridx = x++;
		gridBagConstraints.gridy = y;
		gridBagLayout.setConstraints(this.lblIncrementoTela, gridBagConstraints);
		painel.add(this.lblIncrementoTela);

		gridBagConstraints.insets = new Insets(10, -0, 0, 0);
		gridBagConstraints.gridx = x++;
		gridBagConstraints.gridy = y;
		gridBagLayout.setConstraints(this.pane, gridBagConstraints);
		painel.add(this.pane);

		x = 1;
		y++;

		gridBagConstraints.insets = new Insets(10, 0, 0, 0);
		gridBagConstraints.gridx = x++;
		gridBagConstraints.gridy = y;
		gridBagLayout.setConstraints(painelBotoes, gridBagConstraints);

		// Monta o painel dos botões.
		painelBotoes.add(this.btnVenda);
		painelBotoes.add(this.btnRelatorio);

		painel.add(painelBotoes);

//		 Adicona os componentes de tela ao conteiner.
		container.add(painel);

		closeInternalFrame();

		setSize(780, 400);
		setVisible(true);
	}

	/**
	 * Fecha o JInternalFrame a partir da chamada do método
	 */
	private void disposeOnClosed() {
		this.dispose();
	}

	/**
	 * Fecha o JInternalFrame a partir da chamada do botão fechar
	 */
	private void closeInternalFrame() {
		this.setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
	}

	// main
//	static public void main(String args[]) {
//		JIFConsulta consulta = new JIFConsulta();
//		consulta.show();
//	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == this.btnRelatorio) {
            try {
				RelatorioPersil persil = new RelatorioPersil();
			} catch (BiffException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		// Caso o botão limpar foi pressionado.
		if (e.getSource() == this.btnVenda) {
			criaTabelaConsulta();
		}
	}

	private void criaTabelaConsulta() {
		javax.swing.table.DefaultTableModel dtm = (javax.swing.table.DefaultTableModel)table.getModel();

		ClienteVO clienteVO = new ClienteVO();
		clienteVO.setNome(txtCliente.getText());
		clienteVO.setEndereco(txtLogradouro.getText());
		clienteVO.setTelefone(txtTelefone.getText());

		Cliente clienteDAO = new ClienteDAO();
		List<ClienteVO> list = clienteDAO.getClienteCriteria(clienteVO);

        if(list != null){
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			ClienteVO cliente = (ClienteVO) iter.next();
			dtm.addRow(new Object[]{cliente.getNome(),cliente.getEndereco(),cliente.getBairro(),
					cliente.getCidade(),cliente.getTelefone()});
		}
      }
	}
}
