import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import menu.Menu;
import model.Cliente;
import model.Veiculo;
import service.ClienteService;
import service.VeiculoService;
import service.VendedorService;

public class Principal {

	public static void main(String[] args) throws InterruptedException {
		boolean continua = true;
		Scanner sc = new Scanner(System.in);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		ClienteService clienteService = new ClienteService(sc);
		VeiculoService veiculoService = new VeiculoService(sc, formatter);
		VendedorService vendedorService = new VendedorService(sc);
		
		do {
			Cliente clienteLogado = null;
			boolean validouSenha = false;

			Menu.tipoUsuario();

			int tipoUsuario = sc.nextInt();

			switch (tipoUsuario) {
			case 1:
				do {
					Menu.bemVindo();
					String cpf = sc.next();
					if (cpf.equals("0")) {
						break;
					}

					clienteLogado = clienteService.buscarClientePorCpf(cpf);
					if (clienteLogado != null) {
						sc.nextLine();
						System.out.println("Digite sua senha: ");
						String senha = sc.nextLine();

						validouSenha = clienteService.validarSenha(clienteLogado, senha);
					}

				} while (clienteLogado == null || !validouSenha);

				Menu.opcoesCliente();
				int opcaoCliente = sc.nextInt();
				
				if(opcaoCliente == 1) {
					System.out.println("Digite a opção referente ao veículo escolhido"); 
					veiculoService.buscarTodosCarrosLivres();
					
					int veiculoEscolhido = sc.nextInt();
					System.out.println("Digite quantos dias você ficará com o veículo: ");
					int diasAlugado = sc.nextInt();
					Veiculo veiculo = veiculoService.alugaVeiculo(veiculoEscolhido, diasAlugado);
					clienteService.adicionarVeiculo(clienteLogado, veiculo);
					
					System.out.println("Digite a opção referente ao vendedor que lhe atendeu: ");
					vendedorService.mostrarTodosVendedores();
					int opcaoVendedor = sc.nextInt();
					vendedorService.gerarComissao(opcaoVendedor, veiculo);
					Thread.sleep(5000);
					System.out.println(clienteLogado);
				}else if(opcaoCliente == 2) {
					System.out.println("Digite a opção referente ao veículo escolhido"); 
					clienteService.mostrarVeiculosAlugados(clienteLogado);
					int veiculoEscolhido = sc.nextInt();
					clienteService.removerVeiculo(clienteLogado, veiculoEscolhido);
					veiculoService.devolverVeiculo(veiculoEscolhido);
					
				}
				
				break;
			case 2:
				break;
			case 3:
				break;
			default:
				System.out.println("Opção inválida!");

			}

			Menu.opcoesAdmin();

			Menu.opcoesVendedor();
		} while (continua);

	}

}
