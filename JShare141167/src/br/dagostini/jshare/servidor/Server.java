package br.dagostini.jshare.servidor;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.dagostini.exemplos.LeituraEscritaDeArquivos;
import br.dagostini.exemplos.LerIp;
import br.dagostini.jshare.comum.pojos.Arquivo;
import br.dagostini.jshare.comun.Cliente;
import br.dagostini.jshare.comun.IServer;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.io.File;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.awt.event.ActionEvent;

public class Server extends JFrame implements IServer {

	private JPanel contentPane;
	private JTextField txtF_Porta;
	private JButton btnIniciarServico;
	private JComboBox cBx_IP;
	private JButton btnEncerrarServico;
	private JTextArea txtA_Status;
	
	/*
	 * VARIÁVEIS DE INSTÂNCIA
	 */
	private IServer servidor;
	private Registry registry;
	
	private SimpleDateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy H:mm:ss:SSS");
	// lista dos clientes registrados no servidor
	private Map<String, Cliente> clientes = new HashMap<>();
	// lista dos arquivos disponibilizados no servidor
	private Map<Cliente, List<Arquivo>> arquivos = new HashMap<>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Server frame = new Server();
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
	public Server() {
		setTitle("Servidor");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 640, 480);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lblIp = new JLabel("END. IP:");
		GridBagConstraints gbc_lblIp = new GridBagConstraints();
		gbc_lblIp.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblIp.insets = new Insets(0, 0, 5, 5);
		gbc_lblIp.gridx = 0;
		gbc_lblIp.gridy = 0;
		contentPane.add(lblIp, gbc_lblIp);
		
		cBx_IP = new JComboBox();
		GridBagConstraints gbc_cBx_IP = new GridBagConstraints();
		gbc_cBx_IP.gridwidth = 4;
		gbc_cBx_IP.insets = new Insets(0, 0, 5, 5);
		gbc_cBx_IP.fill = GridBagConstraints.HORIZONTAL;
		gbc_cBx_IP.gridx = 1;
		gbc_cBx_IP.gridy = 0;
		contentPane.add(cBx_IP, gbc_cBx_IP);
		
		JLabel lblPorta = new JLabel("PORTA:");
		GridBagConstraints gbc_lblPorta = new GridBagConstraints();
		gbc_lblPorta.insets = new Insets(0, 0, 5, 5);
		gbc_lblPorta.anchor = GridBagConstraints.EAST;
		gbc_lblPorta.gridx = 6;
		gbc_lblPorta.gridy = 0;
		contentPane.add(lblPorta, gbc_lblPorta);
		
		txtF_Porta = new JTextField();
		GridBagConstraints gbc_txtF_Porta = new GridBagConstraints();
		gbc_txtF_Porta.gridwidth = 3;
		gbc_txtF_Porta.insets = new Insets(0, 0, 5, 5);
		gbc_txtF_Porta.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtF_Porta.gridx = 7;
		gbc_txtF_Porta.gridy = 0;
		contentPane.add(txtF_Porta, gbc_txtF_Porta);
		txtF_Porta.setColumns(10);
		
		btnIniciarServico = new JButton("Iniciar serviço");
		btnIniciarServico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// invoca o método para que o serviço do servidor seja iniciado.
				startService();
				
			}
		});
		GridBagConstraints gbc_btnIniciarServico = new GridBagConstraints();
		gbc_btnIniciarServico.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnIniciarServico.insets = new Insets(0, 0, 5, 0);
		gbc_btnIniciarServico.gridx = 10;
		gbc_btnIniciarServico.gridy = 0;
		contentPane.add(btnIniciarServico, gbc_btnIniciarServico);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridheight = 12;
		gbc_scrollPane.gridwidth = 11;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 1;
		contentPane.add(scrollPane, gbc_scrollPane);
		
		txtA_Status = new JTextArea();
		scrollPane.setViewportView(txtA_Status);
		
		btnEncerrarServico = new JButton("Encerrar serviço");
		btnEncerrarServico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// invoca o método para que o serviço do servidor seja encerrado.
				endService();
			}
		});
		GridBagConstraints gbc_btnEncerrarServico = new GridBagConstraints();
		gbc_btnEncerrarServico.gridx = 10;
		gbc_btnEncerrarServico.gridy = 13;
		contentPane.add(btnEncerrarServico, gbc_btnEncerrarServico);
		
		configIP();
	}
	
	// carrega e lista todos os ip presentes no terminal
	private void configIP() {
		try {
			for (String endIP : new LerIp().ListIp() ) {
				cBx_IP.addItem(endIP);
			} 
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	protected void startService() {
		// TODO Auto-generated method stub
		
		String porta = txtF_Porta.getText().trim();
		
		if (!porta.matches("[0-9]+") || porta.length() > 5) {
			JOptionPane.showMessageDialog(this, "A porta deve conter 5 dígitos");
			return;
		}
		
		int numporta = Integer.parseInt(porta);
		if (numporta < 1024 || numporta > 65535) {
			JOptionPane.showMessageDialog(this, "A porta deve ser entre 1024 e 65535");
			return;
		}
		
		try {
			servidor = (IServer) UnicastRemoteObject.exportObject(this, 0);
			registry = LocateRegistry.createRegistry(numporta);
			registry.rebind(IServer.NOME_SERVICO, servidor);
			
			//System.out.println("SERVIÇO INICIADO...");
			exibirMsg("SERVIÇO INICIADO...");
			
			cBx_IP.setEnabled(false);
			txtF_Porta.setEnabled(false);
			btnIniciarServico.setEnabled(false);
			btnEncerrarServico.setEnabled(true);

		} catch (RemoteException e) {
			JOptionPane.showMessageDialog(this, "Erro ao iniciar o serviço");
			e.printStackTrace();
		}
		
	}
	
	protected void endService() {
		// TODO Auto-generated method stub
		
		System.out.println("ENCERRANDO SERVIÇO...");
		
		try {
			UnicastRemoteObject.unexportObject(this, true);
			UnicastRemoteObject.unexportObject(registry, true);
			
			cBx_IP.setEnabled(true);
			txtF_Porta.setEnabled(true);
			btnIniciarServico.setEnabled(true);
			btnEncerrarServico.setEnabled(false);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
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
		
		exibirMsg(c.getNome() + ", com IP: " + c.getIp() + " se conectou.");
		clientes.put(c.getIp(), c);
		
	}

	@Override
	public void publicarListaArquivos(Cliente c, List<Arquivo> lista) throws RemoteException {
		// TODO Auto-generated method stub
		
		for (Arquivo arquivo : lista) {
			exibirMsg("Cliente: " + c.getNome() + "/ Disponibilizou o arquivo: " + arquivo.getNome() + " : " + arquivo.getTamanho());
		}
		arquivos.put(c, lista);
		
	}

	@Override
	public Map<Cliente, List<Arquivo>> procurarArquivo(String nome) throws RemoteException {
		// TODO Auto-generated method stub
		
		exibirMsg("Pesquisado o arquivo: " + nome);
		
		Map<Cliente, List<Arquivo>> procuraArquivo = new HashMap<>();
		
		for (Map.Entry<Cliente, List<Arquivo>> entry : arquivos.entrySet()) {
			
			List<Arquivo> listaArquivo = new ArrayList<>();
			for (Arquivo arquivo : entry.getValue()) {
				if (arquivo.getNome().equals(nome)) {
					listaArquivo.add(arquivo);
				}
			}
			if (listaArquivo.size() > 0) {
				procuraArquivo.put(entry.getKey(), listaArquivo);
			}
		}
		return procuraArquivo;
	}

	@Override
	public byte[] baixarArquivo(Arquivo arq) throws RemoteException {
		// TODO Auto-generated method stub
		
		File file = new File(".\\Share\\Download\\" + arq.getNome() );
		byte[] dados = new LeituraEscritaDeArquivos().leia(file);
		exibirMsg("Dados " + dados);
		
		return dados;
	}

	@Override
	public void desconectar(Cliente c) throws RemoteException {
		// TODO Auto-generated method stub
		
		clientes.remove(c);
		arquivos.remove(c);
		exibirMsg("Cliente: " + c.getNome().toUpperCase() + "desconectou-se.");
		
	}

}
