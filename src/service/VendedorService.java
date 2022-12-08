package service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Scanner;

import model.Veiculo;
import model.Vendedor;
import repository.VendedorRepository;

public class VendedorService {
	private VendedorRepository repository;
	
	private Scanner sc;
	
	public VendedorService(Scanner sc) {
		this.repository = new VendedorRepository();
		this.sc = sc;
	}
	
	public void mostrarTodosVendedores() {
		List<Vendedor> vendedores = this.repository.buscarTodos();
		
		for(Vendedor vendedor : vendedores) {
			System.out.println(vendedor);
		}
	}
	
	public void gerarComissao(int vendedorId, Veiculo veiculoAlugado) {
		LocalDate dataAtual = LocalDate.now();
		long quantidadeDias = dataAtual.until(veiculoAlugado.getDataEntrega(), ChronoUnit.DAYS);
		double totalVenda = veiculoAlugado.getValor() * quantidadeDias;
		double comissao = totalVenda * Vendedor.PORCENTAGEM_COMISSAO;
		
		Vendedor vendedor = this.repository.buscarPorId(vendedorId);
		vendedor.setComissao(vendedor.getComissao() + comissao);
		
		System.out.println(vendedor.getComissao());
	}
}
