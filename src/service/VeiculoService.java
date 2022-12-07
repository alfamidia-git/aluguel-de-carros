package service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import model.Veiculo;
import model.Veiculo.Status;
import repository.ClienteRepository;
import repository.VeiculoRepository;

public class VeiculoService {
	private VeiculoRepository repository;
	
	private DateTimeFormatter formatter;
	private Scanner sc;
	
	public VeiculoService(Scanner sc, DateTimeFormatter fm) {
		this.repository = new VeiculoRepository();
		this.sc = sc;
		this.formatter = fm;
	}
	
	
	public void buscarTodosCarrosLivres() {
		List<Veiculo> todosVeiculos = this.repository.buscarTodos();
		
		for(Veiculo veiculo : todosVeiculos) {
			if(veiculo.getStatus() == Status.LIVRE) {
				System.out.println(veiculo);
			}
		}
	}
	
	public Veiculo alugaVeiculo(int idVeiculo, int diasAlugado) {
		Veiculo veiculo = this.repository.buscarPorId(idVeiculo);
		
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
	
}
