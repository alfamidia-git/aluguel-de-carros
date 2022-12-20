package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import model.Veiculo;
import model.Veiculo.Segmento;
import model.Veiculo.Status;

public class VeiculoRepository implements Repository<Veiculo>{

	Connection conexao;
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
		
		this.conexao = Conexao.getConexao();
		
		String query = "select * from veiculo";
		
		try {
			PreparedStatement ps = this.conexao.prepareStatement(query);
			ResultSet result = ps.executeQuery();
			
			List<Veiculo> veiculos = new ArrayList<>();
			
			while(result.next()) {
				Integer id = result.getInt("id");
				String marca = result.getString("marca");
				String modelo = result.getString("modelo");
				String placa = result.getString("placa");
				String cor = result.getString("cor");
				Double valor = result.getDouble("valor");
				String ano = result.getString("ano");
				Segmento segmento = Segmento.valueOf(result.getString("segmento"));
				Status status = Status.valueOf(result.getString("status_veiculo"));
				
				Veiculo veiculo = new Veiculo(id, marca, modelo, placa, cor, ano, segmento, valor, status);
				
				veiculos.add(veiculo);
			}
			
			return veiculos;
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println(e.getCause());
		}finally{
			if(this.conexao != null) {
				try {
					this.conexao.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
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
		this.conexao = Conexao.getConexao();
		
		String query = "delete from veiculo where id = " + id;
		
		try {
			PreparedStatement ps = this.conexao.prepareStatement(query);
			int excluiu	 = ps.executeUpdate();
			
			if(excluiu == 1) {
				System.out.println("Excluído com sucesso!");
			}else {
				System.out.println("Não foi possível excluir o veículo com id: " + id);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			if(this.conexao != null) {
				try {
					this.conexao.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public Veiculo salvar(Veiculo t) {
		this.veiculosRepository.put(t.getId(), t);
		return t;
	}

}
