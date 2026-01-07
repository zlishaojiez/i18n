package io.github.zlishaojiez.i18n.spring.boot3.autoconfigure.service.impl;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import io.github.zlishaojiez.i18n.spring.boot3.autoconfigure.service.I18nService;

public class I18nServiceImpl implements I18nService {

    private static final String DEFAULT_MESSAGE_CODE = "i18n.default-message";

    private final MessageSource messageSource;

    public I18nServiceImpl(final MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Override
    public String getMessage(final String code, @Nullable final Object[] args, @NonNull final String defaultMessage) {
        return this.messageSource.getMessage(code, args, defaultMessage, LocaleContextHolder.getLocale());
    }

    @Override
    public String getMessage(final String code, @Nullable final Object[] args) {
        return this.getMessage(code, args, this.getDefaultNoSuchMessage(code));
    }

    @Override
    public String getMessage(final String code, @NonNull final String defaultMessage) {
        return this.getMessage(code, null, defaultMessage);
    }

    @Override
    public String getMessage(final String code) {
        return this.getMessage(code, this.getDefaultNoSuchMessage(code));
    }

    private String getDefaultNoSuchMessage(final String code) {
        final Locale locale = LocaleContextHolder.getLocale();
        return this.getMessage(DEFAULT_MESSAGE_CODE, new Object[] { code, locale.getDisplayName() }, "");
    }

}
