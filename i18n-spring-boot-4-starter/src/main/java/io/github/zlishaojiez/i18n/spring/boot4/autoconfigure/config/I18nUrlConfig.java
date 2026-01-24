package io.github.zlishaojiez.i18n.spring.boot4.autoconfigure.config;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@ConditionalOnProperty(name = "i18n.resolver", havingValue = "url")
public class I18nUrlConfig implements WebMvcConfigurer {

    private final String parameter;

    public I18nUrlConfig(@Value("${i18n.parameter:locale}") final String parameter) {
        this.parameter = parameter;
    }

    @Bean
    public LocaleResolver localeResolver() {
        final SessionLocaleResolver localeResolver = new SessionLocaleResolver();
        localeResolver.setDefaultLocale(Locale.CHINA);
        return localeResolver;
    }

    @Override
    public void addInterceptors(final InterceptorRegistry registry) {
        final LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName(this.parameter);
        registry.addInterceptor(localeChangeInterceptor);
    }

}
