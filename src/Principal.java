import java.util.Scanner;

import menu.Menu;

public class Principal {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);		
		Menu.bemVindo();		
		String cpf = sc.next();
		
		if( !cpf.equals("0") ) {
			Menu.opcoesCliente();
			
			Menu.opcoesAdmin();
			
			Menu.opcoesVendedor();			
		}


		
	}

}
