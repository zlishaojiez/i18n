# I18n Spring Boot Starter

Spring Boot i18n common library

## Overview

The project can choose the following methods for parsing locale through the spring properties configuration by the library

> + HTTP Header parameter
> + HTTP Cookie parameter
> + HTTP URL parameter
> + System environment

## Quick Start

### Maven Dependency

    ```xml
        <dependency>
            <groupId>io.github.zlishaojiez</groupId>
            <artifactId>i18n-spring-boot-4-starter</artifactId>           
            <version>0.0.1</version>
        </dependency>
    ```

### Properties

    ```yaml
        i18n:
          resolver: environment
          parameter: lang
    ```

#### Description

   > resolver: Where does i18n obtain the locale parameter for parsing  
   >> resolver supports environment,header,cookie,url  
   >>
   >> environment: System environment  
   >> header: HTTPRequest Header  
   >> cookie: HTTPRequest Cookie  
   >> url: HTTPRequest URL
   >
   > parameter: i18n parses the locale from the parameter
   >> parameter is optional  
   >>
   >> environment default parameter is locale  
   >> header default parameter is Accept-Language  
   >> cookie default parameter is locale  
   >> url default parameter is locale

### Usage

Inject the I18nService bean into the project, and use the getMessage method of I18nService to obtain the configured internationalization (i8n) information

    ```java
        I18nService i18nService;
        String i18nMsg = i18nService.getMessage("Your Message Code(Key)");
    ```
