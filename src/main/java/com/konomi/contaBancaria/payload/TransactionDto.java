package com.konomi.contaBancaria.payload;

import java.math.BigDecimal;

public record TransactionDto(
        BigDecimal value,
        Long senderId,
        Long receiverId) {
}
