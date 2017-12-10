package com.mtg.rigbaby.resources;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;

import com.google.api.server.spi.ServiceException;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.Named;
import com.google.api.server.spi.config.ApiMethod.HttpMethod;
import com.google.api.server.spi.response.ConflictException;
import com.google.api.server.spi.response.NotFoundException;
import com.google.inject.Inject;
import com.mtg.rigbaby.db.OperatorDao;
import com.mtg.rigbaby.models.Operator;
import com.mtg.rigbaby.server.external.KasaClient;
import com.mtg.rigbaby.server.external.response.KasaTokenResponse;

@Api(
    name = "operator",
    version = "v1"
    )
public class OperatorResource {
  
  @Inject OperatorDao _dao;
  @Inject KasaClient _kasaClient;
  
  /**
   * GET /operator/v1/operator
   * @param id
   * @return
   * @throws NotFoundException
   */
  public Operator getOperator(@Named("id") String id) throws NotFoundException {
    return _dao.read(id);
  }
  
  @ApiMethod(
      name = "operator.getDevices",
      path = "devices",
      httpMethod = HttpMethod.POST)
  public KasaTokenResponse getDevices(Operator op) throws ClientProtocolException, IOException {
    KasaTokenResponse response = _kasaClient.getToken(op);
    return response;
  }
  
  @ApiMethod(
      name = "operator.insert",
      httpMethod = HttpMethod.POST)
  public Operator insertOperator(Operator op) throws ServiceException {
    if (_dao.read(op.getKey()) == null) {
      _dao.set(op.getKey(), op);
      return _dao.read(op.getKey());
    } else {
      throw new ConflictException(
          String.format("Operator with key %s already exists", op.getKey()));
    }
  }
}
