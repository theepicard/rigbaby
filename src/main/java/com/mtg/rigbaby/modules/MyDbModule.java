package com.mtg.rigbaby.modules;

import java.io.IOException;

import com.google.api.client.util.store.DataStore;
import com.google.api.client.util.store.MemoryDataStoreFactory;
import com.google.inject.AbstractModule;
import com.mtg.rigbaby.db.OperatorDao;
import com.mtg.rigbaby.db.RigDao;
import com.mtg.rigbaby.models.Operator;
import com.mtg.rigbaby.models.Rig;

public class MyDbModule extends AbstractModule {

  @Override
  protected void configure() {
    // TODO Auto-generated method stub
    try {
      System.out.println("instantiating db module 2");
      
      DataStore<Rig> rigDatastore = MemoryDataStoreFactory.getDefaultInstance().getDataStore("rigs");
      bind(RigDao.class).toInstance(new RigDao(rigDatastore));
      
      DataStore<Operator> operatorDatastore = MemoryDataStoreFactory.getDefaultInstance().getDataStore("operators");
      bind(OperatorDao.class).toInstance(new OperatorDao(operatorDatastore));
    } catch (IOException e1) {
      // TODO Auto-generated catch block
      e1.printStackTrace();
    }
  }
}
