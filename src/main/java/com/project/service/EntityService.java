package com.project.service;

import com.project.dto.request.EntityBodyReq;
import com.project.model.Entity;
import com.project.repository.EntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EntityService {

  @Autowired private EntityRepository entityRepository;

  public Entity getEntityByCriteria(EntityBodyReq entityReq) {
    return entityRepository.getEntityByCriteria(entityReq);
  }
}
