package Kindle.db;

import java.util.ArrayList;

public interface InterfaceDAO<T> {

	public void adiciona(T referencia);
	
	public void remove(T referencia);
	
	public ArrayList<T> lista();
	
	public T busca(T referencia);
	
}
