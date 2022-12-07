package repository;

import java.util.List;

public interface Repository<T> {

	public List<T> buscarTodos();
	
	public T buscarPorId(Integer id);
	
	public void excluirPorId(Integer id);
	
	public T salvar(T t);
}
