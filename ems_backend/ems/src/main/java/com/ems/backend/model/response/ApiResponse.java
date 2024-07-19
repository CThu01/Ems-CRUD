package com.ems.backend.model.response;

public record ApiResponse<T>(boolean status,T payload) {

}
