package exception;

public class VendedorException extends Exception{

	private static final long serialVersionUID = 1L;

	public VendedorException(String mensagem) {
		super(mensagem);
	}
}
