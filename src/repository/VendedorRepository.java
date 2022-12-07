package repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import model.Vendedor;

public class VendedorRepository implements Repository<Vendedor>{

	private Map<Integer, Vendedor> vendedoresRepository;
	
	public VendedorRepository() {
		this.vendedoresRepository = new HashMap<>();
	}
	
	@Override
	public List<Vendedor> buscarTodos() {
		Collection<Vendedor> vendedoresColl = this.vendedoresRepository.values();
		
		List<Vendedor> vendedores = vendedoresColl.stream().collect(Collectors.toList());
		
		return vendedores;
	}

	@Override
	public Vendedor buscarPorId(Integer id) {
		return this.vendedoresRepository.get(id);
	}

	@Override
	public void excluirPorId(Integer id) {
		this.vendedoresRepository.remove(id);
		
	}

	@Override
	public Vendedor salvar(Vendedor t) {
		this.vendedoresRepository.put(t.getId(), t);
		return t;
	}

}
