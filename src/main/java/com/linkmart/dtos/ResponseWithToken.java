package com.linkmart.dtos;

public record ResponseWithToken(boolean success, String message, String JWTToken) {
}
