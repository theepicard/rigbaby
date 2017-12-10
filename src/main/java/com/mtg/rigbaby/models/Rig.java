package com.mtg.rigbaby.models;

import java.io.Serializable;

public class Rig implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  
  private String key;
  private String name;
  private String deviceId;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getKey() {
    return key;
  }

  public void setKey(String id) {
    this.key = id;
  }

  public String getDeviceId() {
    return deviceId;
  }

  public void setDeviceId(String deviceId) {
    this.deviceId = deviceId;
  }
  
  
}
