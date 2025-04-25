package com.github.jonatas00.ordenadorApi.dto.response;

public record ApiResponse<T> (
  int status,
  T message
){}
