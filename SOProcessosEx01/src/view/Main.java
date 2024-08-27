package view;
import controller.RedesController;
import javax.swing.JOptionPane;
public class Main {
	public static void main (String [] args) {
		
		RedesController cont = new RedesController();
		
		int opc = 0;
		while (opc != 9) {
			opc = Integer.parseInt(JOptionPane.showInputDialog("1- Chamada de configuração do IP \n 2- Chamada de ping em IPv4 \n 9- Finalizar"));
			
			switch (opc) {
			case 1: cont.ip();
			break;
			case 2: cont.ping();
			break;
			case 9: JOptionPane.showMessageDialog(null, "Programa finalizado!");
			break;
			default: JOptionPane.showMessageDialog(null, "Opção Inválida!");
			}
		}
		
	}

}