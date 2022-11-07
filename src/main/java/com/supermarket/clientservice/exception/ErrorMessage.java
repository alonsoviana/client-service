package com.supermarket.clientservice.exception;

import lombok.Getter;
import lombok.Setter;
import lombok.Builder;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@Builder
public class ErrorMessage {
    private String code;
    private List<Map<String, String>> message;
}
