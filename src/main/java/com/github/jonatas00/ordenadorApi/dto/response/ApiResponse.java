package com.github.jonatas00.ordenadorApi.dto.response;

public class ApiResponse<T> {
  private int status;
  private T body;

  public ApiResponse(int status, T body) {
    this.status = status;
    this.body = body;
  }

  public int getStatus() {
    return status;
  }

  public T getBody() {
    return body;
  }
}
