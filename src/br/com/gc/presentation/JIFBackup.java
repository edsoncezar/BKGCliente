/*
 * @(#)JFPrincipal.java 28/08/2005
 *
 * Copyright (c) 2005 X-Team.
 * All Rights Reserved.
 */
package br.com.gc.presentation;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import br.com.gc.backup.Backup;
import br.com.gc.backup.Restore;
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
public class JIFBackup extends JInternalFrame  implements ActionListener {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	JButton btnBackup = new JButton("Gerar Backup");

	JButton btnRestore = new JButton("Carregar Backup");

	// Construtor
	public JIFBackup() {

		super("Backup and Restore");

		// Titulo do JIFCliente.
		this.btnBackup.addActionListener(this);
		this.btnRestore.addActionListener(this);

		JPanel painel = new JPanel();

		JPanel painelBotoes = new JPanel();

		int x = 0;
		int y = 0;

		Container container = getContentPane();
		GridBagLayout gridBagLayout = new GridBagLayout();
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		painel.setLayout(gridBagLayout);

		x = 1;
		y++;

		gridBagConstraints.insets = new Insets(10, 0, 0, 0);
		gridBagConstraints.gridx = x++;
		gridBagConstraints.gridy = y;
		gridBagLayout.setConstraints(painelBotoes, gridBagConstraints);

		// Monta o painel dos botões.
		painelBotoes.add(this.btnBackup);
		painelBotoes.add(this.btnRestore);

		painel.add(painelBotoes);

        //Adicona os componentes de tela ao conteiner.
		container.add(painel);

		closeInternalFrame();

		setSize(400, 300);
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
	static public void main(String args[]) {
		JIFBackup consulta = new JIFBackup();
		consulta.show();
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == this.btnRestore) {
		    Restore restore = new Restore();
		    try {
				restore.execute();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		// Caso o botão limpar foi pressionado.
		if (e.getSource() == this.btnBackup) {
           Backup backup = new Backup();
           try {
			backup.execute();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		}
	}
}
