package com.tinqinacademy.comments.core.errors;

import com.tinqinacademy.comments.api.errors.Errors;

public interface ErrorMapper {
    Errors map(Throwable throwable);
}
