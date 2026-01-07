package io.github.zlishaojiez.i18n.spring.boot3.autoconfigure.config;

import java.util.Locale;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.servlet.LocaleResolver;

import io.github.zlishaojiez.i18n.spring.boot3.I18nSpringBoot3Application;

import static org.assertj.core.api.Assertions.assertThat;

class I18nHeaderConfigTest {

    @Nested
    @SpringBootTest(classes = { I18nSpringBoot3Application.class}, properties = {"i18n.resolver=header", "i18n.parameter=accept-language"})
    @Import(I18nHeaderConfig.class)
    class testLocaleResolverWithAcceptLanguageHeader {

        @Autowired
        private LocaleResolver localeResolver;

        @Test
        void testLocaleResolver() {
            final Locale localeSetting = Locale.CHINA;
            final MockHttpServletRequest request = new MockHttpServletRequest();
            request.addPreferredLocale(localeSetting);
            final Locale locale = this.localeResolver.resolveLocale(request);
            assertThat(locale).isEqualTo(localeSetting);
        }
    }

    @Nested
    @SpringBootTest(classes = { I18nSpringBoot3Application.class}, properties = {"i18n.resolver=header", "i18n.parameter=locale"})
    @Import(I18nHeaderConfig.class)
    class testLocaleResolverWithCustomHeader {

        @Autowired
        private LocaleResolver localeResolver;

        @Test
        void testLocaleResolver() {
            final Locale localeSetting = Locale.US;
            final MockHttpServletRequest request = new MockHttpServletRequest();
            request.addHeader("locale", localeSetting.toLanguageTag());
            final Locale locale = this.localeResolver.resolveLocale(request);
            assertThat(locale).isEqualTo(localeSetting);
        }
    }

}