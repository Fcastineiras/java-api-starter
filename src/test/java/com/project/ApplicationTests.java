package com.project;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.project.controller.EntityController;
import com.project.repository.EntityRepository;
import com.project.service.EntityService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ApplicationTests {

  @Autowired private EntityController entityController;
  @Autowired private EntityService entityService;
  @Autowired private EntityRepository entityRepository;

  @Test
  void contextLoads() {
    assertNotNull(entityController);
    assertNotNull(entityService);
    assertNotNull(entityRepository);
  }
}
