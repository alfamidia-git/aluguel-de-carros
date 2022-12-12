package service;

import java.util.List;
import java.util.Scanner;

import javax.net.ssl.SSLEngineResult.Status;

import model.Admin;
import model.Cliente;
import model.Cliente.TipoPessoa;
import model.Veiculo;
import model.Veiculo.Segmento;
import repository.AdminRepository;
import repository.ClienteRepository;
import repository.VeiculoRepository;
import repository.VendedorRepository;

public class AdminService {

	private AdminRepository repository;
	private ClienteRepository clienteRepository;
	private VendedorRepository vendedorRepository;
	private VeiculoRepository veiculoRepository;
	private Scanner sc;
	
	public AdminService(Scanner sc, ClienteRepository clienteRepository, VendedorRepository vendedorRepository, 
			VeiculoRepository veiculoRepository) {
		this.repository = new AdminRepository();
		this.clienteRepository = clienteRepository;
		this.vendedorRepository = vendedorRepository;
		this.veiculoRepository = veiculoRepository;
		this.sc = sc;
	}

	public Admin buscarAdminPorCpf(String cpf) {
		List<Admin> admins = this.repository.buscarTodos();
		
		for(Admin admin : admins) {
			if(admin.getCpf().equals(cpf)) {
				return admin;
			}
		}
		
		return null;
	}

	public void cadastrarCliente() {
		sc.nextLine();
		System.out.println("Digite o nome do cliente: ");
		String nome = sc.nextLine();
		
		System.out.println("Digite o cpf/cnpj do cliente: ");
		String cpf = sc.nextLine();
		
		System.out.println("Digite o endereço do cliente");
		String endereco = sc.nextLine();
		
		System.out.println("Digite a senha do cliente");
		String senha = sc.nextLine();
		
		System.out.println("Digite o tipo do cliente (PF ou PJ)");
		String tipo = sc.nextLine();
		
		Cliente novoCliente = new Cliente(nome, cpf, senha, endereco, TipoPessoa.valueOf(tipo));
		
		this.clienteRepository.salvar(novoCliente);
	}
	
	public void removerCliente() {
		List<Cliente> todosClientes = this.clienteRepository.buscarTodos();
		
		for(Cliente cliente : todosClientes) {
			System.out.println(cliente.getId() + " - " + cliente.getNome() + " - " + cliente.getCpf());
		}
		
		System.out.println("Escolha qual cliente você deseja excluir");
		
		int opcao = sc.nextInt();
		
		this.clienteRepository.excluirPorId(opcao);
	}

	public void cadastrarUmVeiculo() {
		sc.nextLine();
		System.out.println("Digite a marca do veículo: ");
		String marca = sc.nextLine();
		
		System.out.println("Digite o modelo do veículo: ");
		String modelo = sc.nextLine();
		
		System.out.println("Digite a placa do veículo");
		String placa = sc.nextLine();
		
		System.out.println("Digite a cor do veículo");
		String cor = sc.nextLine();
		
		System.out.println("Digite o ano do veículo");
		String ano = sc.nextLine();
		
		System.out.println("Digite o segmento do veículo");
		String segmento = sc.nextLine();
		
		System.out.println("Digite o valor do veículo");
		double valor = sc.nextDouble();
		
		
		Veiculo veiculo = new Veiculo(marca, modelo, placa, cor, ano, Segmento.valueOf(segmento), valor);
		
		this.veiculoRepository.salvar(veiculo);
		
	}
	
	public void removerVeiculo() {
		List<Veiculo> todosVeiculos = this.veiculoRepository.buscarTodos();
		
		for(Veiculo veiculo : todosVeiculos) {
			System.out.println(veiculo);
		}
		
		System.out.println("Escolha qual cliente você deseja excluir");
		
		int opcao = sc.nextInt();
		
		this.veiculoRepository.excluirPorId(opcao);
	}
}
