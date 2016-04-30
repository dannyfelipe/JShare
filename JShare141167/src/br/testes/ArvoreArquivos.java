package br.testes;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;

import br.dagostini.jshare.comum.pojos.Arquivo;
import br.dagostini.jshare.comun.Cliente;

public class ArvoreArquivos {
	
	public static void main(String[] args) {
		
		//List<Integer> lista;
		// lista = praticamente um vetor, um trenzinho, valor do lado do outro 
		
		//Map<Integer, String> mapaBasico;
		// mesma coisa, cada vagão, dois valores, um chave, outro valor
		// 
		
		Map<Cliente, List<Arquivo>> mapa = new HashMap<Cliente, List<Arquivo>>();
		
		for (int iK = 1; iK <= 100; iK++){
			
			Cliente c = new Cliente();
			
			c.setIp("192.168.104.180" + iK);
			c.setNome("Danny Felipe" + iK);
			c.setPorta(1818);
			
			List<Arquivo> lista = new ArrayList<>();
			
			for (int iV = 1; iV < 100; iV++) {
				
				Arquivo arq = new Arquivo();
				arq.setNome("Arquivo.txt" + iV);
				arq.setTamanho((long)(Math.random()*100));
				lista.add(arq);
				
			}
			
			mapa.put(c, lista);
			
		}
		
		TableModel tb = new MeuModelo(mapa);
		
		JFrame jf = new JFrame();
		JPanel jp = new JPanel();
		jp.setLayout(new BorderLayout());
		JTable jt = new JTable(tb);
		
		JScrollPane sp = new JScrollPane(jt);
		
		jp.add(jt, BorderLayout.CENTER);
		jf.setContentPane(jp);
		
		jf.setBounds(0, 0, 640, 480);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		
	}

}
