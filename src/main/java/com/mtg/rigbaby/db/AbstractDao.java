package com.mtg.rigbaby.db;

import java.io.IOException;
import java.io.Serializable;

import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.api.client.util.store.DataStore;
import com.mtg.rigbaby.db.common.IOFunction;

/**
 * Default implementation for reading and writing to DataStore
 * @author admin
 *
 * @param <T>
 */
public abstract class AbstractDao<T extends Serializable> implements Dao<T> {
  
  protected Logger _log = LoggerFactory.getLogger(this.getClass());

  protected DataStore<T> _client;
  
  public AbstractDao(DataStore<T> client) {
    _client = client;
  }
  
  @Override
  public T read(String key) {
    IOFunction<Pair<String,T>,T> read = (Pair<String,T> in) -> {
      return _client.get(in.getLeft());
    };
    return caught(Pair.of(key, null), read);
  }
  
  @Override
  public T set(String key, T value) {
    IOFunction<Pair<String,T>,T> read = (Pair<String,T> in) -> {
      _client.set(in.getLeft(), in.getRight());
      return read(in.getLeft());
    };
    return caught(Pair.of(key, value), read);
  }

  /**
   * Runs the database operation but intercepts the IOException 
   * such that normal processing can continue and clients don't need to
   * handle exceptions themselves
   * @param input
   * @param inputFunction
   * @return
   */
  protected T caught(Pair<String, T> input, IOFunction<Pair<String, T>, T> inputFunction) {
    try {
      return inputFunction.apply(input);
    } catch (IOException e) {
      _log.error(e.getMessage());
      throw new RuntimeException(e);
    }
  }  
}
