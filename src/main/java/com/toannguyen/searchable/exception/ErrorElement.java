package com.toannguyen.searchable.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ErrorElement {
    String errorField;
    String errorMsg;
}
