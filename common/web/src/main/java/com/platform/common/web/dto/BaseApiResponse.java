package com.platform.common.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "BaseApiResponse", title = "응답 정보")
public class BaseApiResponse<T> {

    @Schema(description = "성공 여부", requiredMode = Schema.RequiredMode.REQUIRED)
    private boolean succeeded;
    @Schema(description = "응답 메세지")
    private String message;
    @Schema(description = "응답 데이터")
    private T payload;

    public static <T> BaseApiResponse<T> of(String message, T payload) {
        var apiResponse = new BaseApiResponse<T>();
        apiResponse.succeeded = true;
        apiResponse.message = message;
        apiResponse.payload = payload;

        return apiResponse;
    }

    public static BaseApiResponse<Void> ofError(Exception exception) {
        return ofError(exception, null);
    }

    public static <T> BaseApiResponse<T> ofError(Exception exception, T payload) {
        var apiResponse = new BaseApiResponse<T>();
        apiResponse.succeeded = false;
        apiResponse.message = exception.getMessage();
        apiResponse.payload = payload;

        return apiResponse;
    }

    public static <T> BaseApiResponse<T> payload(T payload) {
        return of(null, payload);
    }
}
