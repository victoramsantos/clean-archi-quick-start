package com.example.enums;

import com.example.entities.LoanStatus;

public enum LoanStatusDB {
    ONGOING,
    RETURNED;

    public static LoanStatusDB fromEntity(LoanStatus status) {
        return LoanStatusDB.valueOf(status.toString());
    }

    public static LoanStatus toEntity(LoanStatusDB status) {
        return LoanStatus.valueOf(status.toString());
    }
}
