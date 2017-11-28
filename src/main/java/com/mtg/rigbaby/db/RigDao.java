package com.mtg.rigbaby.db;

import java.io.IOException;

import com.google.api.client.util.store.DataStore;
import com.mtg.rigbaby.models.Rig;

public class RigDao {
  
  private DataStore<Rig> _client;
  
  public RigDao(DataStore<Rig> client) {
    _client = client;
  }
  
  public Rig read(String key) throws IOException {
    return _client.get(key);
  }
  
  public String set(String key, Rig value) throws IOException {
    _client.set(key, value);
    return this.read(key).getKey();
  }
}
