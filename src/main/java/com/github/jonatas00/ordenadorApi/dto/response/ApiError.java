package com.github.jonatas00.ordenadorApi.dto.response;

import java.util.List;

public record ApiError(int status, List<String> errors) {
}
