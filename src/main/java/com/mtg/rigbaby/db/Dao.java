package com.mtg.rigbaby.db;

public interface Dao<T> {

  public T read(String key);
  
  public T set(String key, T value);
  
}
