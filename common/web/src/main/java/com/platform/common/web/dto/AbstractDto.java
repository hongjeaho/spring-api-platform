package com.platform.common.web.dto;

import com.google.gson.Gson;
import java.io.Serial;
import java.io.Serializable;

public class AbstractDto  implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    public <T> T copy(final Class<T> targetType) {
        return copy(this, targetType);
    }

    public static <T> T copy(final AbstractDto source, final Class<T> targetType) {
        final var gson = new Gson();
        return gson.fromJson(gson.toJson(source), targetType);
    }
}
