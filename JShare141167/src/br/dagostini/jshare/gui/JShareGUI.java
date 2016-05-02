package br.dagostini.jshare.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.dagostini.jshare.comum.pojos.Arquivo;
import br.dagostini.jshare.comun.Cliente;
import br.dagostini.jshare.comun.IServer;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.JScrollPane;
import javax.swing.JList;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.JTextArea;

public class JShareGUI extends JFrame implements IServer {

	private JPanel contentPane;
	private JTextField txtF_Uporta;
	private JTextField txtF_ipserver;
	private JTextField txtF_arquivo;
	private JTable table_Download;
	private JTextField txtF_nome;
	private JTextField txtF_Sporta;
	private JTextArea txtA_Status;
	private JList list_Arquivos;
	
	/*
	 * VARIÁVEIS DE INSTÂNCIA
	 */
	private SimpleDateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy H:mm:ss:SSS");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JShareGUI frame = new JShareGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public JShareGUI() {
		setTitle("TERMINAL P2P");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 880, 470);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lblUsurio = new JLabel("USUÁRIO:");
		GridBagConstraints gbc_lblUsurio = new GridBagConstraints();
		gbc_lblUsurio.anchor = GridBagConstraints.WEST;
		gbc_lblUsurio.gridwidth = 13;
		gbc_lblUsurio.insets = new Insets(0, 0, 5, 5);
		gbc_lblUsurio.gridx = 0;
		gbc_lblUsurio.gridy = 0;
		contentPane.add(lblUsurio, gbc_lblUsurio);
		
		JLabel lblServidor = new JLabel("SERVIDOR:");
		GridBagConstraints gbc_lblServidor = new GridBagConstraints();
		gbc_lblServidor.anchor = GridBagConstraints.WEST;
		gbc_lblServidor.gridwidth = 13;
		gbc_lblServidor.insets = new Insets(0, 0, 5, 5);
		gbc_lblServidor.gridx = 14;
		gbc_lblServidor.gridy = 0;
		contentPane.add(lblServidor, gbc_lblServidor);
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridheight = 12;
		gbc_panel.gridwidth = 13;
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 1;
		contentPane.add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{1.0, 1.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel lblNome = new JLabel("Nome:");
		GridBagConstraints gbc_lblNome = new GridBagConstraints();
		gbc_lblNome.anchor = GridBagConstraints.EAST;
		gbc_lblNome.insets = new Insets(0, 0, 5, 5);
		gbc_lblNome.gridx = 0;
		gbc_lblNome.gridy = 0;
		panel.add(lblNome, gbc_lblNome);
		
		txtF_nome = new JTextField();
		GridBagConstraints gbc_txtF_nome = new GridBagConstraints();
		gbc_txtF_nome.gridwidth = 11;
		gbc_txtF_nome.insets = new Insets(0, 0, 5, 5);
		gbc_txtF_nome.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtF_nome.gridx = 1;
		gbc_txtF_nome.gridy = 0;
		panel.add(txtF_nome, gbc_txtF_nome);
		txtF_nome.setColumns(10);
		
		JLabel lblIpServer = new JLabel("IP Server:");
		GridBagConstraints gbc_lblIpServer = new GridBagConstraints();
		gbc_lblIpServer.anchor = GridBagConstraints.EAST;
		gbc_lblIpServer.insets = new Insets(0, 0, 5, 5);
		gbc_lblIpServer.gridx = 0;
		gbc_lblIpServer.gridy = 1;
		panel.add(lblIpServer, gbc_lblIpServer);
		
		txtF_ipserver = new JTextField();
		GridBagConstraints gbc_txtF_ipserver = new GridBagConstraints();
		gbc_txtF_ipserver.gridwidth = 6;
		gbc_txtF_ipserver.insets = new Insets(0, 0, 5, 5);
		gbc_txtF_ipserver.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtF_ipserver.gridx = 1;
		gbc_txtF_ipserver.gridy = 1;
		panel.add(txtF_ipserver, gbc_txtF_ipserver);
		txtF_ipserver.setColumns(10);
		
		JLabel lblPorta = new JLabel("Porta:");
		GridBagConstraints gbc_lblPorta = new GridBagConstraints();
		gbc_lblPorta.anchor = GridBagConstraints.EAST;
		gbc_lblPorta.insets = new Insets(0, 0, 5, 5);
		gbc_lblPorta.gridx = 7;
		gbc_lblPorta.gridy = 1;
		panel.add(lblPorta, gbc_lblPorta);
		
		txtF_Uporta = new JTextField();
		GridBagConstraints gbc_txtF_Uporta = new GridBagConstraints();
		gbc_txtF_Uporta.gridwidth = 4;
		gbc_txtF_Uporta.insets = new Insets(0, 0, 5, 5);
		gbc_txtF_Uporta.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtF_Uporta.gridx = 8;
		gbc_txtF_Uporta.gridy = 1;
		panel.add(txtF_Uporta, gbc_txtF_Uporta);
		txtF_Uporta.setColumns(10);
		
		JButton btn_conectar = new JButton("Conectar");
		GridBagConstraints gbc_btn_conectar = new GridBagConstraints();
		gbc_btn_conectar.insets = new Insets(0, 0, 5, 0);
		gbc_btn_conectar.gridx = 12;
		gbc_btn_conectar.gridy = 1;
		panel.add(btn_conectar, gbc_btn_conectar);
		
		JSeparator separator = new JSeparator();
		GridBagConstraints gbc_separator = new GridBagConstraints();
		gbc_separator.insets = new Insets(0, 0, 5, 5);
		gbc_separator.gridx = 0;
		gbc_separator.gridy = 2;
		panel.add(separator, gbc_separator);
		
		JLabel lblArquivo = new JLabel("Arquivo:");
		GridBagConstraints gbc_lblArquivo = new GridBagConstraints();
		gbc_lblArquivo.anchor = GridBagConstraints.EAST;
		gbc_lblArquivo.insets = new Insets(0, 0, 5, 5);
		gbc_lblArquivo.gridx = 0;
		gbc_lblArquivo.gridy = 3;
		panel.add(lblArquivo, gbc_lblArquivo);
		
		txtF_arquivo = new JTextField();
		GridBagConstraints gbc_txtF_arquivo = new GridBagConstraints();
		gbc_txtF_arquivo.gridwidth = 11;
		gbc_txtF_arquivo.insets = new Insets(0, 0, 5, 5);
		gbc_txtF_arquivo.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtF_arquivo.gridx = 1;
		gbc_txtF_arquivo.gridy = 3;
		panel.add(txtF_arquivo, gbc_txtF_arquivo);
		txtF_arquivo.setColumns(10);
		
		JButton btn_Pesquisar = new JButton("Pesquisar");
		GridBagConstraints gbc_btn_Pesquisar = new GridBagConstraints();
		gbc_btn_Pesquisar.insets = new Insets(0, 0, 5, 0);
		gbc_btn_Pesquisar.gridx = 12;
		gbc_btn_Pesquisar.gridy = 3;
		panel.add(btn_Pesquisar, gbc_btn_Pesquisar);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 13;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 4;
		panel.add(scrollPane, gbc_scrollPane);
		
		list_Arquivos = new JList();
		scrollPane.setViewportView(list_Arquivos);
		
		JButton btn_Download = new JButton("Download");
		btn_Download.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		GridBagConstraints gbc_btn_Download = new GridBagConstraints();
		gbc_btn_Download.insets = new Insets(0, 0, 5, 0);
		gbc_btn_Download.gridx = 12;
		gbc_btn_Download.gridy = 5;
		panel.add(btn_Download, gbc_btn_Download);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
		gbc_scrollPane_1.gridheight = 3;
		gbc_scrollPane_1.gridwidth = 13;
		gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_1.gridx = 0;
		gbc_scrollPane_1.gridy = 6;
		panel.add(scrollPane_1, gbc_scrollPane_1);
		
		table_Download = new JTable();
		scrollPane_1.setViewportView(table_Download);
		
		JPanel panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.gridheight = 12;
		gbc_panel_1.gridwidth = 13;
		gbc_panel_1.insets = new Insets(0, 0, 5, 0);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 14;
		gbc_panel_1.gridy = 1;
		contentPane.add(panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_panel_1.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel_1.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		JLabel lblEndip = new JLabel("End.IP:");
		GridBagConstraints gbc_lblEndip = new GridBagConstraints();
		gbc_lblEndip.anchor = GridBagConstraints.WEST;
		gbc_lblEndip.insets = new Insets(0, 0, 5, 5);
		gbc_lblEndip.gridx = 0;
		gbc_lblEndip.gridy = 0;
		panel_1.add(lblEndip, gbc_lblEndip);
		
		JComboBox cBx_endip = new JComboBox();
		GridBagConstraints gbc_cBx_endip = new GridBagConstraints();
		gbc_cBx_endip.gridwidth = 3;
		gbc_cBx_endip.insets = new Insets(0, 0, 5, 5);
		gbc_cBx_endip.fill = GridBagConstraints.HORIZONTAL;
		gbc_cBx_endip.gridx = 1;
		gbc_cBx_endip.gridy = 0;
		panel_1.add(cBx_endip, gbc_cBx_endip);
		
		JLabel lblPorta_1 = new JLabel("Porta:");
		GridBagConstraints gbc_lblPorta_1 = new GridBagConstraints();
		gbc_lblPorta_1.anchor = GridBagConstraints.EAST;
		gbc_lblPorta_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblPorta_1.gridx = 4;
		gbc_lblPorta_1.gridy = 0;
		panel_1.add(lblPorta_1, gbc_lblPorta_1);
		
		txtF_Sporta = new JTextField();
		GridBagConstraints gbc_txtF_Sporta = new GridBagConstraints();
		gbc_txtF_Sporta.insets = new Insets(0, 0, 5, 0);
		gbc_txtF_Sporta.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtF_Sporta.gridx = 5;
		gbc_txtF_Sporta.gridy = 0;
		panel_1.add(txtF_Sporta, gbc_txtF_Sporta);
		txtF_Sporta.setColumns(10);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_2 = new GridBagConstraints();
		gbc_scrollPane_2.gridheight = 10;
		gbc_scrollPane_2.gridwidth = 6;
		gbc_scrollPane_2.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane_2.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_2.gridx = 0;
		gbc_scrollPane_2.gridy = 1;
		panel_1.add(scrollPane_2, gbc_scrollPane_2);
		
		txtA_Status = new JTextArea();
		scrollPane_2.setViewportView(txtA_Status);
		
		JButton btn_IniciarServico = new JButton("Iniciar serviço");
		GridBagConstraints gbc_btn_IniciarServico = new GridBagConstraints();
		gbc_btn_IniciarServico.insets = new Insets(0, 0, 0, 5);
		gbc_btn_IniciarServico.gridx = 4;
		gbc_btn_IniciarServico.gridy = 11;
		panel_1.add(btn_IniciarServico, gbc_btn_IniciarServico);
		
		JButton btn_PararServico = new JButton("Parar serviço");
		GridBagConstraints gbc_btn_PararServico = new GridBagConstraints();
		gbc_btn_PararServico.gridx = 5;
		gbc_btn_PararServico.gridy = 11;
		panel_1.add(btn_PararServico, gbc_btn_PararServico);
	}
	
	private void exibirMsg(String string) {
		txtA_Status.append(dateformat.format(new Date()));
		txtA_Status.append(" => ");
		txtA_Status.append(string);
		txtA_Status.append("\n");
	}

	@Override
	public void registrarCliente(Cliente c) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void publicarListaArquivos(Cliente c, List<Arquivo> lista) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Map<Cliente, List<Arquivo>> procurarArquivo(String nome) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public byte[] baixarArquivo(Arquivo arq) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void desconectar(Cliente c) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

}
