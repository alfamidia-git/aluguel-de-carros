package repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import model.Veiculo;

public class VeiculoRepository implements Repository<Veiculo>{

	private Map<Integer, Veiculo> veiculosRepository;
	
	public VeiculoRepository() {
		this.veiculosRepository = new HashMap<>();
	}
	
	@Override
	public List<Veiculo> buscarTodos() {
		
		Collection<Veiculo> veiculosColl = this.veiculosRepository.values();
		
		List<Veiculo> veiculos = veiculosColl.stream().collect(Collectors.toList());
		
		return veiculos;
	}

	@Override
	public Veiculo buscarPorId(Integer id) {
		return this.veiculosRepository.get(id);
	}

	@Override
	public void excluirPorId(Integer id) {
		this.veiculosRepository.remove(id);	
	}

	@Override
	public Veiculo salvar(Veiculo t) {
		this.veiculosRepository.put(t.getId(), t);
		return t;
	}

}
