package br.dagostini.exemplos;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class LerIp {

	public LerIp() {

		InetAddress IP;

		try {
			IP = InetAddress.getLocalHost();
			String IPString = IP.getHostAddress();
			System.out.println("Meu IP é: " + IP.getHostAddress());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
	
	// método para listar todos os IP
	public List<String> ListIp() {
		List<String> listaIP = new ArrayList<>();
		
		try {
			Enumeration<NetworkInterface> net = NetworkInterface.getNetworkInterfaces();
			while (net.hasMoreElements()) {
				NetworkInterface networkInterface = (NetworkInterface) net.nextElement();
				
				if (networkInterface.isUp()) {
					Enumeration<InetAddress> netAddress = networkInterface.getInetAddresses();
					
					while (netAddress.hasMoreElements()) {
						InetAddress inetAddress = (InetAddress) netAddress.nextElement();
						
						String ip = inetAddress.getHostAddress();
						
						if (ip.matches("[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}")) {
							listaIP.add(ip.trim());
						}
					}
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listaIP;
		
	}

	public static void main(String[] args) {
		new LerIp();
	}
}
