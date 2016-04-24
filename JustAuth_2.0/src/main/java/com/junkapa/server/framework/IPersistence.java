package com.junkapa.server.framework;

import java.util.Set;

public interface IPersistence<T> {
	public Set<T> getList(T entity);
	public T getOne(T entity);
	public boolean insert(T entity);
	public boolean update(T entity);
	public boolean delete(T entity);
	
}
