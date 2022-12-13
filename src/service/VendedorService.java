package service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Scanner;

import exception.VendedorException;
import model.Veiculo;
import model.Vendedor;
import repository.ClienteRepository;
import repository.VendedorRepository;

public class VendedorService {
	private VendedorRepository repository;	
	
	public VendedorRepository getRepository() {
		return this.repository;
	}
	
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
	
	public void gerarComissao(int vendedorId, Veiculo veiculoAlugado) throws VendedorException {
		LocalDate dataAtual = LocalDate.now();
		long quantidadeDias = dataAtual.until(veiculoAlugado.getDataEntrega(), ChronoUnit.DAYS);
		double totalVenda = veiculoAlugado.getValor() * quantidadeDias;
		double comissao = totalVenda * Vendedor.PORCENTAGEM_COMISSAO;
		
		Vendedor vendedor = this.repository.buscarPorId(vendedorId);
		if(vendedor == null) {
			throw new VendedorException("Vendedor não localizado");
		}
		
		vendedor.setComissao(vendedor.getComissao() + comissao);
		this.repository.salvar(vendedor);
	}

	public Vendedor buscarVendedorPorCpf(String cpf) {
		List<Vendedor> vendedores = this.repository.buscarTodos();
		
		for(Vendedor vendedor : vendedores) {
			if(vendedor.getCpf().equals(cpf)){
				return vendedor;
			}
		}
		
		return null;
	}
	
	public void mostrarSalarioAtual(Vendedor vendedorLogado) {
		System.out.println(vendedorLogado.getNome() + ", seu salário com a comissão atual é: " + (vendedorLogado.getComissao() + vendedorLogado.getSalario()));
	}
}
