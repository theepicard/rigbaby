package com.mtg.rigbaby.server.external.request;

import java.util.UUID;

/**
 * When registering against https://wap.tplinkcloud.com,
 * a token is generated for a particular username/password combo.
 * 
 * This token is used to then make further requests on behalf of the user.
 * POST https://wap.tplinkcloud.com
 * BODY:
 * Ex:  {
 *    "method": "login",
 *    "params": {
 *    "appType": "Kasa_Android",
 *    "cloudUserName": "XXXXX",
 *    "cloudPassword": "XXXXX",
 *    "terminalUUID": "MY_UUID_v4"
 *    }
 *   }
 * @author admin
 *
 */
public class KasaTokenRequest {
  public String method = "login";
  public KasaTokenRequestParams params;
  
  public KasaTokenRequest(String username, String password) {
    params = new KasaTokenRequestParams(username, password);
  }
  
  class KasaTokenRequestParams {
    public String appType = "Kasa_Android";
    public String cloudUserName;
    public String cloudPassword;
    public String terminalUUID;
    
    public KasaTokenRequestParams(String username, String password) {
      cloudUserName = username;
      cloudPassword = password;
      terminalUUID = UUID.randomUUID().toString();
    }
  }
  
}
