package io.github.zlishaojiez.i18n.spring.boot3.autoconfigure.service;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

public interface I18nService {
    String getMessage(String code, @Nullable Object[] args, @NonNull String defaultMessage);
    String getMessage(String code, @Nullable Object[] args);
    String getMessage(String code, @NonNull String defaultMessage);
    String getMessage(String code);
}
