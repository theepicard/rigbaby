package com.mtg.rigbaby.server.external;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.util.EntityUtils;

import com.google.appengine.repackaged.com.google.gson.Gson;
import com.mtg.rigbaby.models.Operator;
import com.mtg.rigbaby.server.external.response.KasaTokenResponse;

public class KasaClient {

  private HttpClient _client;
  
  public KasaClient(HttpClient client) {
    _client = client;
  }
  
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
   * @param op
   * @return
   * @throws IOException 
   * @throws ClientProtocolException 
   */
  public KasaTokenResponse getToken(Operator op) throws ClientProtocolException, IOException {
    HttpUriRequest request = KasaRequestFactory.createPostTokenRequest(op);
    
    //TODO validation
    return _client.execute(request, new ResponseHandler<KasaTokenResponse>() {

      @Override
      public KasaTokenResponse handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
        Gson gson = new Gson();
        return gson.fromJson(EntityUtils.toString(response.getEntity()), KasaTokenResponse.class);
      }
      
    });
  }
  
}
