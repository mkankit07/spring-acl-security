package com.example.springaclsecurity.constants.enums;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Status {
    DELETED(0),
    ACTIVE(1),
    INACTIVE(2),
    DISABLED(3);
    public final int statusValue;
}