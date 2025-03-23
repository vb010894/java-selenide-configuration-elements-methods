package com.github.vb010894;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeOptions;

/// Тесты Capabilities
public class ChromeCapabilitiesTest {

    /// Настройка драйвера
    @BeforeAll
    public static void setup() {
        WebDriverManager.chromedriver().setup();
        Configuration.baseUrl = "https://dzen.ru";
        Configuration.browser = "chrome";
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--force-dark-mode");
        Configuration.browserCapabilities = options;
    }

    /// Тест открытия Chrome в темной теме\
    /// с помощью capabilities
    @Test
    public void testWithChrome() {

        Selenide.open("/id/65d7431f1759d9595653fed1");
        // Оставил для точки останова
        System.out.println("opened");
        Selenide.closeWebDriver();
    }

}
