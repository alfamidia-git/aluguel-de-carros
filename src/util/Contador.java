package util;

public class Contador {
	
	private static int contador = 0;

	
	public static int proximo() {
		contador++;
		return contador;
	}
}
