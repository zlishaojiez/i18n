package io.github.zlishaojiez.i18n.spring.boot4.autoconfigure.service;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

public interface I18nService {
    String getMessage(String code, @Nullable Object[] args, @NonNull String defaultMessage);
    String getMessage(String code, @Nullable Object[] args);
    String getMessage(String code, @NonNull String defaultMessage);
    String getMessage(String code);
}
