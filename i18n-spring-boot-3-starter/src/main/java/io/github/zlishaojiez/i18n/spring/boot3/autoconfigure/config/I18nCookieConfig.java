package io.github.zlishaojiez.i18n.spring.boot3.autoconfigure.config;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;

@ConditionalOnProperty(name = "i18n.resolver", havingValue = "cookie")
public class I18nCookieConfig {

    private final String parameter;

    public I18nCookieConfig(@Value("${i18n.parameter:locale}") final String parameter) {
        this.parameter = parameter;
    }

    @Bean
    public LocaleResolver localeResolver() {
        final CookieLocaleResolver localeResolver = new CookieLocaleResolver(this.parameter);
        localeResolver.setDefaultLocale(Locale.CHINA);
        return localeResolver;
    }

}
