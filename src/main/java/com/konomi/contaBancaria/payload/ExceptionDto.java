package com.konomi.contaBancaria.payload;

public record ExceptionDto(
        String message,
        String statusCode) {
}
