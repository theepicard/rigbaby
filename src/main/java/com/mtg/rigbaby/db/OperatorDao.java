package com.mtg.rigbaby.db;

import com.google.api.client.util.store.DataStore;
import com.mtg.rigbaby.models.Operator;

public class OperatorDao extends AbstractDao<Operator> {

  public OperatorDao(DataStore<Operator> client) {
    super(client);
  }
}
