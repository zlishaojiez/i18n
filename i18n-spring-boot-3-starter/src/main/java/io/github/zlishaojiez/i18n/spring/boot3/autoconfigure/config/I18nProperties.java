package io.github.zlishaojiez.i18n.spring.boot3.autoconfigure.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.Setter;

@ConfigurationProperties(prefix = "i18n")
@Getter
@Setter
public class I18nProperties {

    private I18nResolverEnum resolver;
    private String parameter;

    public enum I18nResolverEnum {
        COOKIE("cookie"),
        ENVIRONMENT("environment"),
        HEADER("header"),
        URL("url");

        private final String value;
        I18nResolverEnum(final String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return this.value;
        }
    }
}
