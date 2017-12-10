package com.mtg.rigbaby.server.external;

import java.io.UnsupportedEncodingException;

import org.apache.http.HttpRequest;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;

import com.google.gson.Gson;
import com.mtg.rigbaby.models.Operator;
import com.mtg.rigbaby.server.external.request.KasaTokenRequest;

public class KasaRequestFactory {
  
  public static String TP_LINK_URL = "https://wap.tplinkcloud.com";
  
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
   * @throws UnsupportedEncodingException 
   */
  public static HttpUriRequest createPostTokenRequest(Operator op) throws UnsupportedEncodingException {
    HttpPost post = new HttpPost(TP_LINK_URL);
    Gson gson = new Gson();
    StringEntity requestEntity = new StringEntity(
        gson.toJson(new KasaTokenRequest(op.getKasaUsername(), op.getKasaPassword())));
    requestEntity.setContentType("application/json");
    post.setEntity(requestEntity);
    return post;
  }
}
