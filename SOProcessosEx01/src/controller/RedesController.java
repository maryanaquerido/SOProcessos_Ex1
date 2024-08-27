package controller;
import java.io.*;

public class RedesController {
	public RedesController() {
		super();
	}
	
	 private String os (){
		 return System.getProperty("os.name");
	 }
	 
	 public void ip () {
		 String nomeSO = os(); 
		 String proc;
		 
		 if (nomeSO.contains("Win")) {
			 proc = "ipconfig";
		 } else {
			 proc = "ifconfig";
		 }
		 
		 try {
			 Process p = Runtime.getRuntime().exec(proc);
			 BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
			 String linha;
			 boolean showAdapter = false;
			 
			 while ((linha = reader.readLine()) != null) {
				 
				 if (linha.contains("flags") && linha.contains("mtu")) { 
					 System.out.println(linha.trim());
		
					} else if (linha.trim().contains("inet ") && !linha.trim().contains("inet6 ")) { 
					 String[] partes = linha.trim().split(" "); 
					 
					 for (String parte : partes) { 
						  if (parte.matches("\\d+\\.\\d+\\.\\d+\\.\\d+")) { 
							  System.out.println("IPv4: " + parte);
							  break;  
						  }
					  }
					}
			 }
	
		 reader.close();
		 
		 } catch (Exception e) {
			 String msg = e.getMessage();
			 
		 }
	 }
	 
	 public void ping() {
		    String nomeSO = os();
		    String proc = "";

		    if (nomeSO.contains("Win")) {
		        proc = "ping -n 10 www.google.com.br";
		    } else {
		        proc = "ping -c 10 www.google.com.br";
		    }

		    try {
		        Process p = Runtime.getRuntime().exec(proc);
		        BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
		        String linha;

		        while ((linha = reader.readLine()) != null) {
		            if (nomeSO.contains("Win")) {
		                if (linha.contains("Média =")) {
		                    String[] partes = linha.split("=");
		                    if (partes.length > 1) {
		                        String tempoWin = partes[1].trim();
		                        System.out.println("Tempo médio de ping: " + tempoWin);
		                    }
		                }
		            } else if (linha.contains("rtt min/avg/max/mdev")) {
		                    String[] partes = linha.split("/");
		                    if (partes.length > 4) {
		                        String tempoLin = partes[4].trim();
		                        System.out.println("Tempo médio de ping: " + tempoLin + " ms");
		                    }
		                }
		            }

		        reader.close();
		        
		    } catch (Exception e) {
				 String msg = e.getMessage();
		    }
		}
		 
	 }

