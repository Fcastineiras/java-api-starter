package com.project.model;

public enum SomeEnum {
  SOME_VALUE;

  public static SomeEnum valueOfCaseIgnore(String value) {
    return SomeEnum.valueOf(value.trim().toUpperCase());
  }
}
