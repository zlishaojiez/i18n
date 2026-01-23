package io.github.zlishaojiez.i18n.spring.boot3.autoconfigure.config;

import java.util.Locale;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.lang.Nullable;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AbstractLocaleResolver;

@ConditionalOnProperty(name = "i18n.resolver", havingValue = "environment")
public class I18nEnvironmentConfig {

    private final Environment environment;
    private final String parameter;

    public I18nEnvironmentConfig(@Autowired final Environment environment, @Value("${i18n.parameter:locale}") final String parameter) {
        this.environment = environment;
        this.parameter = parameter;
    }

    @Bean
    public LocaleResolver localeResolver() {
        final EnvironmentLocaleResolver localeResolver = new EnvironmentLocaleResolver(this.environment, this.parameter);
        localeResolver.setDefaultLocale(Locale.CHINA);
        return localeResolver;
    }

    private  static class EnvironmentLocaleResolver extends AbstractLocaleResolver {

        private final Environment environment;
        private final String parameter;

       EnvironmentLocaleResolver(final Environment environment, final String parameter) {
            this.environment = environment;
            this.parameter = parameter;
        }

        @Override
        public Locale resolveLocale(final HttpServletRequest request) {
            final String localeStr = this.environment.getProperty(this.parameter);
            if (!StringUtils.hasText(localeStr)) {
                return Locale.CHINA;
            }
            final Locale locale = StringUtils.parseLocale(localeStr);
            return locale == null ? Locale.CHINA : locale;
        }

        @Override
        public void setLocale(final HttpServletRequest request, @Nullable final HttpServletResponse response, @Nullable final Locale locale) {
            throw new UnsupportedOperationException("Cannot change environment locale - use a different locale resolution strategy");
        }

    }

}
