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
		
		this.conexao = Conexao.getConexao();
		String query = "select * from veiculo where id = " + id;
		Veiculo veiculo = null;
		
		try {
			PreparedStatement ps = this.conexao.prepareStatement(query);
			ResultSet result = ps.executeQuery();
			
			
			while(result.next()) {
				String marca = result.getString("marca");
				String modelo = result.getString("modelo");
				String placa = result.getString("placa");
				String cor = result.getString("cor");
				Double valor = result.getDouble("valor");
				String ano = result.getString("ano");
				Segmento segmento = Segmento.valueOf(result.getString("segmento"));
				Status status = Status.valueOf(result.getString("status_veiculo"));
				
				veiculo = new Veiculo(id, marca, modelo, placa, cor, ano, segmento, valor, status);
				
			}
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
		
		return veiculo;
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
	public Veiculo salvar(Veiculo veiculo) {
		String query = "INSERT INTO veiculo (modelo, marca, placa, valor, cor, segmento, ano, status_veiculo)"
				+ " values (?, ?, ?, ?, ?, ?, ?, ?)" ;
		
		this.conexao = Conexao.getConexao();
		
		try {
			PreparedStatement ps = this.conexao.prepareStatement(query);
			
			ps.setString(1, veiculo.getModelo());
			ps.setString(2, veiculo.getMarca());
			ps.setString(3, veiculo.getPlaca());
			ps.setDouble(4, veiculo.getValor());
			ps.setString(5, veiculo.getCor());
			ps.setString(6, veiculo.getSegmento().toString());
			ps.setString(7, veiculo.getAno());
			ps.setString(8, Status.LIVRE.toString());
			
			int excluiu	 = ps.executeUpdate();			
			if(excluiu == 1) {
				System.out.println("Criado com sucesso!");
			}else {
				System.out.println("Não foi possível criar o veículo");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return veiculo;
	}

}
