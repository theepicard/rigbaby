package com.mtg.rigbaby.models;

import java.io.Serializable;

public class Operator implements Serializable {
  String key;
  String name;
  String kasaToken;
  String kasaUsername;
  String kasaPassword;
  
  public String getKey() {
    return key;
  }
  public void setKey(String key) {
    this.key = key;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public String getKasaToken() {
    return kasaToken;
  }
  public void setKasaToken(String kasaToken) {
    this.kasaToken = kasaToken;
  }
  public String getKasaUsername() {
    return kasaUsername;
  }
  public void setKasaUsername(String kasaUsername) {
    this.kasaUsername = kasaUsername;
  }
  public String getKasaPassword() {
    return kasaPassword;
  }
  public void setKasaPassword(String kasaPassword) {
    this.kasaPassword = kasaPassword;
  }  
}
