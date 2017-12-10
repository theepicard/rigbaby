package com.mtg.rigbaby.server.external.response;

/**
 * Expected response from Kasa
 * {
 *  "error_code": 0,
 *  "result": {
 *  "regTime": "2017-01-06 08:42:35",
 *  "email": "XXXXX",
 *  "token": "YOUR_TOKEN_HERE"
 *  }
 * }
 * @author admin
 *
 */
public class KasaTokenResponse {
  public String error_code;
  public Result result;
  
  class Result {
    public String regTime;
    public String email;
    public String token;
  }
}
