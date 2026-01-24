package io.github.zlishaojiez.i18n.spring.boot4.autoconfigure.processor;

import java.util.Set;

import org.springframework.context.MessageSource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class I18nDefaultMessageProcessor {

    private static final String MESSAGE_SOURCE_BASE_NAME = "i18n/daas-common-i18n";

    private final MessageSource messageSource;

    public I18nDefaultMessageProcessor(final MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    private void init() {
        if (this.messageSource instanceof final ReloadableResourceBundleMessageSource reloadableResourceBundleMessageSource) {
            final String sourcePath = "classpath:%s".formatted(MESSAGE_SOURCE_BASE_NAME);
            final Set<String> basenameSet = reloadableResourceBundleMessageSource.getBasenameSet();
            if (!basenameSet.contains(sourcePath)) {
                reloadableResourceBundleMessageSource.addBasenames(sourcePath);
            }

        } else if (this.messageSource instanceof final ResourceBundleMessageSource resourceBundleMessageSource) {
            final Set<String> basenameSet = resourceBundleMessageSource.getBasenameSet();
            if (!basenameSet.contains(MESSAGE_SOURCE_BASE_NAME)) {
                resourceBundleMessageSource.addBasenames(MESSAGE_SOURCE_BASE_NAME);
            }
        }
    }
}
