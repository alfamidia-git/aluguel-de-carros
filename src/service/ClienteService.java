package service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import exception.VeiculoException;
import model.Cliente;
import model.Cliente.TipoPessoa;
import model.Veiculo;
import repository.ClienteRepository;

public class ClienteService {

	private ClienteRepository repository;
	
	private Scanner sc;
	
	public ClienteRepository getRepository() {
		return this.repository;
	}
	
	public ClienteService(Scanner sc) {
		this.repository = new ClienteRepository();
		this.sc = sc;
	}
	
	public Cliente buscarClientePorCpf(String cpf) {
		Cliente cliente = this.repository.buscarPorCpf(cpf);
		
		if(cliente == null) {
			return this.criarCliente(cpf);
		}
		
		return cliente;
	}
	
	private Cliente criarCliente(String cpf) {
		sc.nextLine();
		System.out.println("Digite seu nome: ");
		String nome = sc.nextLine();
		System.out.println("Digite seu endereço: ");
		String endereco = sc.nextLine();
		System.out.println("Digite sua senha");
		String senha = sc.nextLine();
		System.out.println("Digite PF para pessoa ou PJ para pessoa júridica");
		String tipo = sc.next();
		TipoPessoa tipoPessoa;
		if(tipo.equals("PF")) {
			tipoPessoa = TipoPessoa.PF;
		}else {
			tipoPessoa = TipoPessoa.PJ;
		}
		
		Cliente cliente = new Cliente(nome, cpf, senha, endereco, tipoPessoa);
		
		return this.repository.salvar(cliente);
	}
	
	public void adicionarVeiculo(Cliente cliente, Veiculo veiculo) {
		if(cliente.getVeiculosAlugados() == null) {
			cliente.setVeiculosAlugados(new ArrayList<>());
		}
		
		cliente.getVeiculosAlugados().add(veiculo);
		
		LocalDate dataAtual = LocalDate.now();
		long quantidadeDias = veiculo.getDataEntrega().until(dataAtual, ChronoUnit.DAYS);
		cliente.setDebitos(cliente.getDebitos() + (veiculo.getValor() * quantidadeDias));
	}

	public void mostrarVeiculosAlugados(Cliente clienteLogado) throws VeiculoException {
		List<Veiculo> veiculos = clienteLogado.getVeiculosAlugados();
		
		if(veiculos == null) {
			throw new VeiculoException("Sem veículos para devolver");
		}
		
		veiculos.forEach(System.out::println);
		
	}

	public void removerVeiculo(Cliente clienteLogado,int veiculoId) {
		clienteLogado.getVeiculosAlugados().removeIf(v -> v.getId() == veiculoId);

//		for(int i = 0; i < clienteLogado.getVeiculosAlugados().size(); i++) {
//			if(clienteLogado.getVeiculosAlugados().get(i).getId() == veiculoId) {
//				clienteLogado.getVeiculosAlugados().remove(i);
//				return;
//			}
//		}
				
	}
}
