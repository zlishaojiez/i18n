package io.github.zlishaojiez.i18n.spring.boot3.autoconfigure.config;

import java.util.Locale;

import jakarta.servlet.http.Cookie;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.servlet.LocaleResolver;

import io.github.zlishaojiez.i18n.spring.boot3.I18nSpringBoot3Application;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = { I18nSpringBoot3Application.class}, properties = {"i18n.resolver=cookie", "i18n.parameter=locale"})
@Import(I18nCookieConfig.class)
class I18nCookieConfigTest {

    @Autowired
    private LocaleResolver localeResolver;

    @Test
    void testLocaleResolver() {
        final Locale localeSetting = Locale.GERMANY;
        final MockHttpServletRequest request = new MockHttpServletRequest();
        request.setCookies(new Cookie("locale", localeSetting.toLanguageTag()));
        final Locale locale = this.localeResolver.resolveLocale(request);
        assertThat(locale).isEqualTo(localeSetting);
    }
}