package com.project.integrationTest.entity;

import static com.project.utils.AdapterUtils.*;
import static org.junit.jupiter.api.Assertions.*;

import com.project.dto.request.EntityBodyReq;
import com.project.dto.response.EntityBodyRes;
import com.project.integrationTest.BaseIntegrationTest;
import com.project.model.SomeEnum;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

public class EntityIntegrationTest extends BaseIntegrationTest {

  @ParameterizedTest
  @MethodSource("successCases")
  public void GivenExistsRegisterByCriteriaBodyWhenTryToGetPriceThenReturnPrice(
      Integer someSmallId, Long someBigId, BigDecimal somePrice, SomeEnum someEnum, String someDate)
      throws Exception {

    final EntityBodyReq req =
        new EntityBodyReq(someSmallId, someBigId, somePrice, someEnum, stringToTime(someDate));
    final EntityBodyRes res =
        JSON_MAPPER.readValue(
            mvc.perform(
                    MockMvcRequestBuilders.get("/entity/")
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectToJson(req)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            EntityBodyRes.class);

    assertEquals(1, res.getSomeSmallId());
    assertEquals(1L, res.getSomeBigId());
    assertEquals(SomeEnum.SOME_VALUE, res.getSomeEnum());
    assertEquals(new BigDecimal("20.20"), res.getSomePrice());
    assertTrue(LocalDateTime.now().isAfter(res.getSomeDate()));
  }

  @ParameterizedTest
  @MethodSource("notFoundCases")
  public void GivenNotExistsAnyRegisterByCriteriaBodyWhenTryToGetPriceThenThrowNotFound(
      Integer someSmallId, Long someBigId, BigDecimal somePrice, SomeEnum someEnum, String someDate)
      throws Exception {
    final EntityBodyReq req =
        new EntityBodyReq(someSmallId, someBigId, somePrice, someEnum, stringToTime(someDate));

    mvc.perform(
            MockMvcRequestBuilders.get("/entity/")
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectToJson(req)))
        .andDo(MockMvcResultHandlers.print())
        .andExpect(MockMvcResultMatchers.status().isNotFound());
  }

  @Test
  public void whenTryToGetPriceWithInvalidCriteriaBodyThenThrowBadRequest() throws Exception {
    mvc.perform(
            MockMvcRequestBuilders.get("/entity/")
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .contentType(MediaType.APPLICATION_JSON)
                .content("adfas"))
        .andDo(MockMvcResultHandlers.print())
        .andExpect(MockMvcResultMatchers.status().isBadRequest());
  }

  public static Stream<Arguments> notFoundCases() {
    return Stream.of(
        Arguments.of(2, 1L, new BigDecimal("20.20"), "SOME_VALUE", "2020-06-14 00:00:00"),
        Arguments.of(1, 2L, new BigDecimal("20.20"), "SOME_VALUE", "2020-06-14 00:00:00"));
  }

  public static Stream<Arguments> successCases() {
    return Stream.of(
        Arguments.of(1, 1L, new BigDecimal("20.20"), "SOME_VALUE", "2020-06-14 00:00:00"));
  }
}
