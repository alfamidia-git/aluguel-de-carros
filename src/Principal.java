import java.util.Scanner;

import menu.Menu;
import model.Cliente;
import service.ClienteService;

public class Principal {

	public static void main(String[] args) {
		boolean continua = true;
		Scanner sc = new Scanner(System.in);
		ClienteService clienteService = new ClienteService(sc);
		
		do {
			Menu.bemVindo();
			String cpf = sc.next();
			if(cpf.equals("0")) {
				break;
			}
			
			Cliente clienteLogado = clienteService.buscarClientePorCpf(cpf);
			
			if(clienteLogado != null) {
				System.out.println("LOGADO!!!");
			}
			
			Menu.opcoesCliente();

			Menu.opcoesAdmin();

			Menu.opcoesVendedor();
		}while(continua);
		

	}

}
