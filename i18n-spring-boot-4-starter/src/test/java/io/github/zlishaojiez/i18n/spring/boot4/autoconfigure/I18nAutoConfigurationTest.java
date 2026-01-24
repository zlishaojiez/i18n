package io.github.zlishaojiez.i18n.spring.boot4.autoconfigure;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import io.github.zlishaojiez.i18n.spring.boot4.I18nSpringBoot4Application;
import io.github.zlishaojiez.i18n.spring.boot4.autoconfigure.service.I18nService;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = { I18nSpringBoot4Application.class, I18nAutoConfiguration.class},
        properties = {"i18n.resolver=header", "i18n.parameter=accept-language"})
class I18nAutoConfigurationTest {

    @Autowired
    private I18nService i18nService;

    @Test
    void testI18nServiceLoadable() {
        assertThat(this.i18nService).isNotNull();
    }
}