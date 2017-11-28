package com.mtg.rigbaby.modules;

import java.io.IOException;

import com.google.api.client.util.store.DataStore;
import com.google.api.client.util.store.MemoryDataStoreFactory;
import com.google.inject.AbstractModule;
import com.mtg.rigbaby.db.RigDao;
import com.mtg.rigbaby.models.Rig;

public class MyDbModule extends AbstractModule {

  @Override
  protected void configure() {
    // TODO Auto-generated method stub
    try {
      System.out.println("instantiating db module 2");
      DataStore<Rig> datastore = MemoryDataStoreFactory.getDefaultInstance().getDataStore("rigs");
      bind(RigDao.class).toInstance(new RigDao(datastore));
    } catch (IOException e1) {
      // TODO Auto-generated catch block
      e1.printStackTrace();
    }
  }
}
