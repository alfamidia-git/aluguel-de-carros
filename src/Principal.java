import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Scanner;

import exception.VeiculoException;
import exception.VendedorException;
import menu.Menu;
import model.Admin;
import model.Cliente;
import model.Veiculo;
import model.Vendedor;
import service.AdminService;
import service.ClienteService;
import service.VeiculoService;
import service.VendedorService;
import util.Normaliza;

public class Principal {

	public static void main(String[] args) throws InterruptedException {
		boolean continua = true;
		Scanner sc = new Scanner(System.in);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		ClienteService clienteService = new ClienteService(sc);
		VeiculoService veiculoService = new VeiculoService(sc, formatter);
		VendedorService vendedorService = new VendedorService(sc);
		AdminService adminService = new AdminService(sc, clienteService.getRepository(),
				vendedorService.getRepository(), veiculoService.getRepository());

		do {
			try {
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
						
						Normaliza.validaCPF(cpf);

						clienteLogado = clienteService.buscarClientePorCpf(cpf);
						if (clienteLogado != null) {
							sc.nextLine();
							System.out.println("Digite sua senha: ");
							String senha = sc.nextLine();

							validouSenha = clienteLogado.validarSenha(senha);
						}

					} while (clienteLogado == null || !validouSenha);

					Menu.opcoesCliente();
					int opcaoCliente = sc.nextInt();

					if (opcaoCliente == 1) {
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
					} else if (opcaoCliente == 2) {
						System.out.println("Digite a opção referente ao veículo escolhido");
						clienteService.mostrarVeiculosAlugados(clienteLogado);
						int veiculoEscolhido = sc.nextInt();
						clienteService.removerVeiculo(clienteLogado, veiculoEscolhido);
						veiculoService.devolverVeiculo(veiculoEscolhido);

					}

					break;
				case 2:
					Vendedor vendedorLogado = null;
					do {
						Menu.bemVindo();
						String cpf = sc.next();
						if (cpf.equals("0")) {
							break;
						}

						vendedorLogado = vendedorService.buscarVendedorPorCpf(cpf);
						if (vendedorLogado != null) {
							sc.nextLine();
							System.out.println("Digite sua senha: ");
							String senha = sc.nextLine();

							validouSenha = vendedorLogado.validarSenha(senha);
						} else {
							System.out.println("Vendedor não encontrado! Digite novamenteu seu cpf");
						}

					} while (vendedorLogado == null || !validouSenha);

					Menu.opcoesVendedor();

					int opcaoVendedor = sc.nextInt();

					if (opcaoVendedor == 1) {
						veiculoService.veiculosAlugados();
					} else if (opcaoVendedor == 2) {
						vendedorService.mostrarSalarioAtual(vendedorLogado);
					} else {
						System.out.println("Opção inválida!!");
					}

					Thread.sleep(3000);
					break;
				case 3:
					Admin adminLogado = null;
					do {
						Menu.bemVindo();
						String cpf = sc.next();
						if (cpf.equals("0")) {
							break;
						}

						adminLogado = adminService.buscarAdminPorCpf(cpf);
						if (adminLogado != null) {
							sc.nextLine();
							System.out.println("Digite sua senha: ");
							String senha = sc.nextLine();

							validouSenha = adminLogado.validarSenha(senha);
						} else {
							System.out.println("Administrador não encontrado! Digite novamenteu seu cpf");
						}

					} while (adminLogado == null || !validouSenha);

					Menu.opcoesAdmin();
					int opcaoAdmin = sc.nextInt();

					switch (opcaoAdmin) {
					case 1:
						adminService.cadastrarPessoa(true);
						break;
					case 2:
						adminService.removerCliente();
						break;
					case 3:
						adminService.cadastrarUmVeiculo();
						break;
					case 4:
						adminService.removerVeiculo();
						break;
					case 5:
						adminService.cadastrarPessoa(false);
						break;
					case 6:
						adminService.removerVendedor();
						break;
					}
					Thread.sleep(1000);
					break;
				default:
					System.out.println("Opção inválida!");

				}
			} catch (VeiculoException e) {
				System.out.println(e.getMessage());
			} catch (VendedorException e) {
				System.out.println(e.getMessage());
			} catch (InputMismatchException e) {
				System.out.println("Tipo de entrada inválida!");
				sc.nextLine();
			} catch (Exception e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			}
		} while (continua);

	}

}
