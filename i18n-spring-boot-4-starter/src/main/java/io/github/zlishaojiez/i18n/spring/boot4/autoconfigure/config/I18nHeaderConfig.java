package io.github.zlishaojiez.i18n.spring.boot4.autoconfigure.config;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;
import java.util.TreeMap;

import jakarta.servlet.http.HttpServletRequest;

import org.apache.tomcat.util.http.parser.AcceptLanguage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

@ConditionalOnProperty(name = "i18n.resolver", havingValue = "header")
public class I18nHeaderConfig {

    private static final String DEFAULT_HEADER_LOCALE_PARAM = "Accept-Language";

    private final String parameter;

    public I18nHeaderConfig(@Value("${i18n.parameter:Accept-Language}") final String parameter) {
        this.parameter = parameter;
    }

    @Bean
    public LocaleResolver localeResolver() {
        final HeaderLocaleResolver localeResolver = new HeaderLocaleResolver(this.parameter);
        localeResolver.setDefaultLocale(Locale.CHINA);

        return localeResolver;
    }

    private static class HeaderLocaleResolver extends AcceptHeaderLocaleResolver {

        private final String parameter;

        HeaderLocaleResolver(final String parameter) {
            this.parameter = parameter;
        }

        @Override
        public Locale resolveLocale(final HttpServletRequest request) {
            if (this.parameter.equalsIgnoreCase(DEFAULT_HEADER_LOCALE_PARAM)) {
                return super.resolveLocale(request);
            } else  {
                final String localeStr = request.getHeader(this.parameter);
                Locale defaultLocale = getDefaultLocale();
                if (defaultLocale == null) {
                    defaultLocale = Locale.CHINA;
                }
                if (!StringUtils.hasText(localeStr)) {
                    return defaultLocale;
                }

                final List<Locale> locales = this.parseLocales(request);

                return !locales.isEmpty() ? locales.get(0) : defaultLocale;
            }
        }

        /**
         * 像解析Accept-Language一样解析自定义header locale 参数
         *
         * @param request Http 请求
         * @return locale 列表
         */
        private List<Locale> parseLocales(final HttpServletRequest request) {
            final ArrayList<Locale> locales = new ArrayList<>();
            final Enumeration<String> values = request.getHeaders(this.parameter);

            final TreeMap<Double, ArrayList<Locale>> localesMap = new TreeMap<>();
            while (values.hasMoreElements()) {
                final String value = values.nextElement();
                this.parseLocalesHeader(value, localesMap);
            }

            for (final ArrayList<Locale> list : localesMap.values()) {
                locales.addAll(list);
            }

            return locales;
        }

        private void parseLocalesHeader(final String value, final TreeMap<Double, ArrayList<Locale>> locales) {
            final List<AcceptLanguage> acceptLanguages;
            try {
                acceptLanguages = AcceptLanguage.parse(new StringReader(value));
            } catch (final IOException var7) {
                return;
            }

            for (final AcceptLanguage acceptLanguage : acceptLanguages) {
                final Double key = -acceptLanguage.getQuality();
                locales.computeIfAbsent(key, (k) -> new ArrayList<>()).add(acceptLanguage.getLocale());
            }

        }
    }



}
