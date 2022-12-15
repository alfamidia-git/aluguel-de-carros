package util;

public class Normaliza {
	
	public static boolean validaCPF(String cpf) throws Exception {
		
		cpf = cpf.replace(".", "");
		cpf = cpf.replace("-", "");
		
		System.out.println(cpf);
		if(cpf.length() != 11 ) {
			throw new Exception("CPF Inválido");
		}
	
		return true;
		
	}
	
	public static boolean validaPlaca(String placa) throws Exception {
		
		if(placa.length() < 7 && placa.length() > 8 ) {
			throw new Exception("Quantidade caractares inválida");
		}
		
		if(!placa.matches("[A-Z]{3}[0-9][A-Z][0-9]{2}") && !placa.matches("[A-Z]{3}-\\d{4}") && !placa.matches("[A-Z]{3}[0-9]{4}")) {
			throw new Exception("Placa inválida");
		}
		
		return true;
	}

}
