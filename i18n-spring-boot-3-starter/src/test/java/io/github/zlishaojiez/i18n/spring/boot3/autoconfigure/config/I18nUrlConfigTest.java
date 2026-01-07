package io.github.zlishaojiez.i18n.spring.boot3.autoconfigure.config;

import java.util.Locale;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import io.github.zlishaojiez.i18n.spring.boot3.controller.I18nTestController;

import lombok.SneakyThrows;

@WebMvcTest(controllers = { I18nTestController.class}, properties = {"i18n.resolver=url", "i18n.parameter=lang"})
@Import(I18nUrlConfig.class)
class I18nUrlConfigTest {

    @Autowired
    private MockMvc mockMvc;

    @SneakyThrows
    @Test
    void testLocaleResolver() {
        final Locale localeSetting = Locale.CANADA;
        this.mockMvc.perform(MockMvcRequestBuilders.get("/locale")
                        .param("lang", localeSetting.toLanguageTag()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(localeSetting.toString()));
    }

}