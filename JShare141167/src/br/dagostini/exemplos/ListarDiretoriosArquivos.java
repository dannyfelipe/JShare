package br.dagostini.exemplos;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import br.dagostini.jshare.comum.pojos.Arquivo;
import br.dagostini.jshare.comum.pojos.Diretorio;

public class ListarDiretoriosArquivos {
	
	private File dirArquivo = new File(".\\Share\\Upload");

	public static void main(String[] args) {

		File dirStart = new File(".\\");
		
		List<Arquivo> listaArquivos = new ArrayList<>();
		List<Diretorio> listaDiretorios = new ArrayList<>();
		for (File file : dirStart.listFiles()) {
			if (file.isFile()) {
				Arquivo arq = new Arquivo();
				arq.setNome(file.getName());
				arq.setTamanho(file.length());
				listaArquivos.add(arq);
			} else {
				Diretorio dir = new Diretorio();
				dir.setNome(file.getName());
				listaDiretorios.add(dir);				
			}
		}

		System.out.println("Diretórios");
		for (Diretorio dir : listaDiretorios) {
			System.out.println("\t" + dir.getNome());
		}
		
		System.out.println("Arquivos");
		for (Arquivo arq : listaArquivos) {
			System.out.println("\t" + arq.getTamanho() + "\t" + arq.getNome());
		}

	}

	public List<Arquivo> listarArquivos() {
		// TODO Auto-generated method stub
		List<Arquivo> listaArquivos = new ArrayList<>();
		
		for (File file : dirArquivo.listFiles()) {
			if (file.isFile()) {
				Arquivo arq = new Arquivo();
				arq.setNome(file.getName());
				arq.setTamanho(file.length());
				arq.setFile(file);
				listaArquivos.add(arq);
			}
		}
		return listaArquivos;
	}
	
	public List<Diretorio> listarDiretorios(){
		List<Diretorio> listaDiretorios = new ArrayList<>();
		for (File file : dirArquivo.listFiles()) {
			if (!file.isFile()) {
				Diretorio dir = new Diretorio();
				dir.setNome(file.getName());
				listaDiretorios.add(dir);	
			}
		}
		return listaDiretorios;
	}
}
