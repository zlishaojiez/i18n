package io.github.zlishaojiez.i18n.spring.boot3.autoconfigure;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

import io.github.zlishaojiez.i18n.spring.boot3.autoconfigure.config.I18nCookieConfig;
import io.github.zlishaojiez.i18n.spring.boot3.autoconfigure.config.I18nEnvironmentConfig;
import io.github.zlishaojiez.i18n.spring.boot3.autoconfigure.config.I18nHeaderConfig;
import io.github.zlishaojiez.i18n.spring.boot3.autoconfigure.config.I18nProperties;
import io.github.zlishaojiez.i18n.spring.boot3.autoconfigure.config.I18nUrlConfig;
import io.github.zlishaojiez.i18n.spring.boot3.autoconfigure.processor.I18nDefaultMessageProcessor;
import io.github.zlishaojiez.i18n.spring.boot3.autoconfigure.service.I18nService;
import io.github.zlishaojiez.i18n.spring.boot3.autoconfigure.service.impl.I18nServiceImpl;

@AutoConfigureBefore({ WebMvcAutoConfiguration.class})
@AutoConfiguration
@EnableConfigurationProperties(I18nProperties.class)
@Import({ I18nHeaderConfig.class, I18nCookieConfig.class, I18nUrlConfig.class, I18nEnvironmentConfig.class})
public class I18nAutoConfiguration {

    @Bean
    public I18nService i18nService(final MessageSource messageSource) {
        return new I18nServiceImpl(messageSource);
    }

    @Bean(initMethod = "init")
    public I18nDefaultMessageProcessor i18nDefaultMessageProcessor(final MessageSource messageSource) {
        return new I18nDefaultMessageProcessor(messageSource);
    }
}
