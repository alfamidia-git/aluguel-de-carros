package service;

import java.util.ArrayList;
import java.util.Scanner;

import model.Cliente;
import model.Cliente.TipoPessoa;
import model.Veiculo;
import repository.ClienteRepository;

public class ClienteService {

	private ClienteRepository repository;
	
	private Scanner sc;
	
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

	public boolean validarSenha(Cliente clienteLogado, String senha) {
		return clienteLogado.getSenha().equals(senha);
		
	}
	
	public void adicionarVeiculo(Cliente cliente, Veiculo veiculo) {
		if(cliente.getVeiculosAlugados() == null) {
			cliente.setVeiculosAlugados(new ArrayList<>());
		}
		
		cliente.getVeiculosAlugados().add(veiculo);
	}
}
