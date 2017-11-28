package com.mtg.rigbaby.resources;

import java.io.IOException;

import com.google.api.server.spi.ServiceException;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiMethod.HttpMethod;
import com.google.api.server.spi.config.Named;
import com.google.api.server.spi.response.ConflictException;
import com.google.api.server.spi.response.NotFoundException;
import com.google.api.server.spi.response.ServiceUnavailableException;
import com.google.inject.Inject;
import com.mtg.rigbaby.db.RigDao;
import com.mtg.rigbaby.models.Rig;

@Api(
    name = "rig",
    version = "v1"
    )
public class RigResource {
  
  @Inject RigDao _dao;

  /**
   * GET /rig/v1/rig
   * @param id
   * @return
   * @throws NotFoundException
   */
  public Rig getRig(@Named("id") String id) throws NotFoundException {
    try {
      return _dao.read(id);
    } catch (IOException e) {
      throw new NotFoundException(e);
    }
  }
  
  @ApiMethod(
      name = "rig.insert",
      httpMethod = HttpMethod.POST)
  public Rig insertRig(Rig rig) throws ServiceException {
    try {
      if (_dao.read(rig.getKey()) == null) {
        _dao.set(rig.getKey(), rig);
        return _dao.read(rig.getKey());
      } else {
        throw new ConflictException(
            String.format("Rig with key %s already exists", rig.getKey()));
      }
    } catch (IOException e) {
      throw new ServiceUnavailableException(e);
    }
  }
}
