package repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import model.Admin;

public class AdminRepository implements Repository<Admin>{

	private Map<Integer, Admin> adminRepository;
	
	public AdminRepository() {
		this.adminRepository = new HashMap<>();
	}
	@Override
	public List<Admin> buscarTodos() {
		return this.adminRepository.values().stream().collect(Collectors.toList());
	}

	@Override
	public Admin buscarPorId(Integer id) {
		return this.adminRepository.get(id);
	}

	@Override
	public void excluirPorId(Integer id) {
		this.adminRepository.remove(id);
		
	}

	@Override
	public Admin salvar(Admin t) {
		this.adminRepository.put(t.getId(), t);
		return t;
	}

}
