package com.mtg.rigbaby.db;

import com.google.api.client.util.store.DataStore;
import com.mtg.rigbaby.models.Rig;

public class RigDao extends AbstractDao<Rig>{
  
  public RigDao(DataStore<Rig> client) {
    super(client);
  }
}
