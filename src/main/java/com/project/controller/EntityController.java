package com.project.controller;

import com.project.dto.request.EntityBodyReq;
import com.project.dto.response.EntityBodyRes;
import com.project.model.Entity;
import com.project.service.EntityService;
import com.project.utils.ApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/entity")
public class EntityController {

  @Autowired private EntityService entityService;

  @GetMapping()
  @SuppressWarnings("unchecked")
  public ResponseEntity<EntityBodyRes> getEntityToApplyByCriteria(
      @RequestBody EntityBodyReq entityReq) {
    validate(entityReq);
    final Entity entity = entityService.getEntityByCriteria(entityReq);
    return new ResponseEntity(new EntityBodyRes(entity), HttpStatus.valueOf(200));
  }

  // many validations, throw when is invalid
  private void validate(EntityBodyReq req) {
    if (req.getSomeBigId() == null
        || req.getSomeSmallId() == null
        || req.getSomePrice() == null
        || req.getSomeDate() == null
        || req.getSomeEnum() == null) {
      throw new ApiException(HttpStatus.BAD_REQUEST, "some value is null");
    }
  }
}
