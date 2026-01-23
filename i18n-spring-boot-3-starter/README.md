# I18n Spring Boot Starter

Spring Boot i18n common library

## Overview
The project can choose the following methods for parsing locale through the spring properties configuration by the library
> + HTTP Header parameter
> + HTTP Cookie parameter
> + HTTP URL parameter
> + System environment

## Quick Start
1. Add the daas-common-i18n library
    ```xml
        <dependency>
            <groupId>io.github.zlishaojiez</groupId>
            <artifactId>i18n-spring-boot-3-starter</artifactId>           
            <version>1.1.1</version>
        </dependency>
    ```
2. Set the properties in your project
    ```yaml
        i18n:
          resolver: environment
          parameter: lang
    ```
    2.1. Settings description
   > 1. resolver: Where does i18n obtain the locale parameter for parsing  
   >> resolver supports environment,header,cookie,url  
   >> environment: System environment  
   >> header: HTTPRequest Header  
   >> cookie: HTTPRequest Cookie  
   >> url: HTTPRequest URL 
   > 2. parameter: i18n parses the locale from the parameter
   >> parameter is optional  
   >> environment default parameter is locale  
   >> header default parameter is Accept-Language  
   >> cookie default parameter is locale  
   >> url default parameter is locale 
   
3. Inject the I18nService bean into the project, and use the getMessage method of I18nService to obtain the configured internationalization (i8n) information