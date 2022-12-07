package repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import model.Veiculo;
import model.Veiculo.Segmento;

public class VeiculoRepository implements Repository<Veiculo>{

	private Map<Integer, Veiculo> veiculosRepository;
	
	public VeiculoRepository() {
		this.veiculosRepository = new HashMap<>();
		this.criaVeiculos();
	}
	
	private void criaVeiculos() {
		Veiculo carro1 = new Veiculo("Chevrolet","Onix", "IVP3455", "Branco", "2020", Segmento.CARRO, 150);
		Veiculo carro2 = new Veiculo("Hyundai","HB20", "ISP3455", "Preto", "2015", Segmento.CARRO, 160);
		Veiculo carro3 = new Veiculo("Ford","Fiesta", "IVL3455", "Cinza", "2015", Segmento.CARRO, 125);
		Veiculo moto1 = new Veiculo("Honda","CG", "IVP3875", "Vermelha", "2020", Segmento.MOTO, 80);
		
		this.veiculosRepository.put(carro1.getId(), carro1);
		this.veiculosRepository.put(carro2.getId(), carro2);
		this.veiculosRepository.put(carro3.getId(), carro3);
		this.veiculosRepository.put(moto1.getId(), moto1);
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
