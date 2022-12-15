package repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import model.Cliente;
import model.Vendedor;
import model.Cliente.TipoPessoa;

public class ClienteRepository implements Repository<Cliente>{

	private Map<Integer, Cliente> clientesRepository;
	
	public ClienteRepository() {
		this.clientesRepository = new HashMap<>();
		Cliente cliente = new Cliente("Marlon", "86332295015", "1234", "Rua 1", TipoPessoa.PF);
		this.clientesRepository.put(cliente.getId(), cliente);
	}
	@Override
	public List<Cliente> buscarTodos() {
		Collection<Cliente> clientesColl = this.clientesRepository.values();
		
		List<Cliente> clientes = clientesColl.stream().collect(Collectors.toList());
		
		return clientes;
	}

	@Override
	public Cliente buscarPorId(Integer id) {
		return this.clientesRepository.get(id);
	}

	@Override
	public void excluirPorId(Integer id) {
		this.clientesRepository.remove(id);		
	}

	@Override
	public Cliente salvar(Cliente t) {
		this.clientesRepository.put(t.getId(), t);
		return t;
	}
	
	public Cliente buscarPorCpf(String cpf) {
		List<Cliente> clientes = this.buscarTodos();
		
		for(Cliente cliente : clientes) {
			
			if(cliente.getCpf().equals(cpf)) {
				return cliente;
			}
		}
		
		return null;
	}

}
