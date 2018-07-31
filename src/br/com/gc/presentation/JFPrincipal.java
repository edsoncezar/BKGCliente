/*
 * @(#)JFPrincipal.java 28/08/2005
 *
 * Copyright (c) 2005 X-Team.
 * All Rights Reserved.
 */
package br.com.gc.presentation;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JToolBar;

/**
 * <strong>Definição</strong><br>
 * Centraliza toda a navegação do sistema.
 * <br>
 *
 * @author
 */
public class JFPrincipal extends JFrame {

	private JPanel			jpanel;
	private JMenuBar		jmenubar;
	private JToolBar		jtoolbar;
	private JDesktopPane	jdesktopPane;
    private Container       container;

	public JFPrincipal() {
		super();
		init();
        centralize();
	}

    /**
     * Inicia as variáveis que serão utlizadas pela classe.
     * <br>
     *
     */
	private void init() {
		setTitle("GC - Gerenciador de Clientes");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(800, 600);
		initMenus();
		initToolbar();
		initVisual();
		setJMenuBar(jmenubar);
		setVisible(true);
	}

    /**
     * Centralizar a tela de acordo com a definição usada.
     * <br>
     *
     */
    private void centralize() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension windowSize = this.getSize();
        int x = (int)((screenSize.getWidth()  - windowSize.getWidth()) / 2);
        int y = (int)((screenSize.getHeight() - windowSize.getHeight()) / 2);
        this.setLocation(x, y);
    }

    /**
     * Inicia os componentes visuais.
     * <br>
     *
     */
	private void initVisual() {
        container = this.getContentPane();
		jpanel = new JPanel();
		jdesktopPane = new JDesktopPane();
		jdesktopPane.setName("jdesktopPane");
		jpanel.setLayout(new BorderLayout());
		jpanel.add(jtoolbar, BorderLayout.NORTH);
		jpanel.add(jdesktopPane, BorderLayout.CENTER);
		container.add(jpanel);
	}

    /**
     * Inicia os componentes de menu.
     * <br>
     *
     */
	private void initMenus() {
		JMenuItem jmenuItemSair = new JMenuItem();
		jmenuItemSair.setName("jmenuItemSair");
		jmenuItemSair.setText("Sair");
		jmenuItemSair.setMnemonic(KeyEvent.VK_S);
		jmenuItemSair.setToolTipText("Sair da Aplicação");
		jmenuItemSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		JMenu jmenuArquivo = new JMenu();
		jmenuArquivo.setName("jmenuArquivo");
		jmenuArquivo.setText("Arquivo");
		jmenuArquivo.setMnemonic(KeyEvent.VK_A);
		jmenuArquivo.add(jmenuItemSair);

		jmenubar = new JMenuBar();
		jmenubar.setName("jmenubar");
		jmenubar.add(jmenuArquivo);
	}

    /**
     * Inicia os componentes de toolbar.
     * <br>
     *
     */
	private void initToolbar() {
		ActionListener acao = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String comandosEmAcao = e.getActionCommand();
				if (comandosEmAcao.equals("cliente")) {
					List list = new ArrayList();
					JInternalFrame jiframeCliente = new JIFCliente(list);
//					jinternalFrame.setTitle("Cadastro de JIFCliente");
                    jiframeCliente.setResizable(true);
                    jiframeCliente.setClosable(true);
//					jinternalFrame.setSize(280, 273);
					jdesktopPane.add(jiframeCliente);
//					jinternalFrame.setVisible(true);
				}
				if (comandosEmAcao.equals("consulta")) {
					JInternalFrame jiframeConsulta = new JIFConsulta(jdesktopPane);
//					jinternalFrame.setTitle("Cadastro de JIFCliente");
					jiframeConsulta.setResizable(true);
					jiframeConsulta.setClosable(true);
//					jinternalFrame.setSize(280, 273);
					jdesktopPane.add(jiframeConsulta);
//					jinternalFrame.setVisible(true);
				}
				if (comandosEmAcao.equals("backup")) {
					JInternalFrame jiframeBackup = new JIFBackup();
//					jinternalFrame.setTitle("Cadastro de JIFCliente");
					jiframeBackup.setResizable(true);
					jiframeBackup.setClosable(true);
//					jinternalFrame.setSize(280, 273);
					jdesktopPane.add(jiframeBackup);
//					jinternalFrame.setVisible(true);
				}
			}
		};

        // Cria os botões de menus.
		JButton jbuttonCliente = new JButton("Cadastro");
        jbuttonCliente.setActionCommand("cliente");
        jbuttonCliente.addActionListener(acao);

        JButton jbuttonConsulta = new JButton("Consulta");
        jbuttonConsulta.setActionCommand("consulta");
        jbuttonConsulta.addActionListener(acao);

        JButton jbuttonBackup = new JButton("Backup");
        jbuttonBackup.setActionCommand("backup");
        jbuttonBackup.addActionListener(acao);

		jtoolbar = new JToolBar();
		jtoolbar.setFloatable(false);
		jtoolbar.setPreferredSize(new Dimension(38, 24));
		jtoolbar.add(jbuttonCliente);
		jtoolbar.add(jbuttonConsulta);
		jtoolbar.add(jbuttonBackup);
		jtoolbar.addSeparator();

	}

    /**
     * Método de execução da classe.
     * <br>
     *
     */
	public static void main(String[] args) {
		new JFPrincipal();
	}

}
