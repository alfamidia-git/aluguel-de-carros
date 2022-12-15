package service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import exception.VeiculoException;
import model.Veiculo;
import model.Veiculo.Status;
import repository.ClienteRepository;
import repository.VeiculoRepository;

public class VeiculoService {
	private VeiculoRepository repository;
	
	public VeiculoRepository getRepository() {
		return this.repository;
	}
	
	private DateTimeFormatter formatter;
	private Scanner sc;
	
	public VeiculoService(Scanner sc, DateTimeFormatter fm) {
		this.repository = new VeiculoRepository();
		this.sc = sc;
		this.formatter = fm;
	}
	
	
	public void buscarTodosCarrosLivres() {
		this.repository.buscarTodos().stream()
		.filter(v -> v.getStatus() == Status.LIVRE).forEach(System.out::println);;
		
//		for(Veiculo veiculo : todosVeiculos) {
//			if(veiculo.getStatus() == Status.LIVRE) {
//				System.out.println(veiculo);
//			}
//		}
		
		
		
	}
	
	public Veiculo alugaVeiculo(int idVeiculo, int diasAlugado) throws VeiculoException {
		Veiculo veiculo = this.repository.buscarPorId(idVeiculo);
		
		if(veiculo == null) {
			throw new VeiculoException("Veículo não encontrado!");
		}
		
		if(veiculo.getStatus() == Status.ALUGADO) {
			System.out.println("Veículo alugado!!");
			return null;
		}
		LocalDate dataAtual = LocalDate.now();
		LocalDate dataEntrega = dataAtual.plusDays(diasAlugado);
		veiculo.setStatus(Status.ALUGADO);
		veiculo.setDataEntrega(dataEntrega);
		
		System.out.println("A entrega do veículo deverá ocorrer no dia: " + dataEntrega.format(formatter));
		return veiculo;
	}


	public void devolverVeiculo(int veiculoEscolhido) {
		Veiculo veiculo = this.repository.buscarPorId(veiculoEscolhido);		
		veiculo.setStatus(Status.LIVRE);
		
	}
	
	public void veiculosAlugados(){
		this.repository.buscarTodos().stream().filter(v -> v.getStatus() == Status.ALUGADO).forEach(System.out::println);

//		List<Veiculo> veiculos = this.repository.buscarTodos();
//		
//		for(Veiculo veiculo : veiculos) {
//			if(veiculo.getStatus() == Status.ALUGADO) {
//				System.out.println(veiculo);
//			}
//		}
	
	}
	
}
