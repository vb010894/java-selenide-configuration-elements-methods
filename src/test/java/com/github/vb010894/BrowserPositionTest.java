package com.github.vb010894;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/// Тесты BrowserPosition
public class BrowserPositionTest {

    /// Настройка драйвера
    @BeforeAll
    public static void setup() {
        WebDriverManager.chromedriver().setup();
        Configuration.baseUrl = "https://dzen.ru";
        Configuration.browser = "chrome";
        Configuration.browserPosition = "10x10";
        Configuration.browserSize = "800x600";
    }

    /// Тест открытия Chrome c заданным размером
    @Test
    public void testWithChrome() {
        Selenide.open("/id/65d7431f1759d9595653fed1");
        // Оставил для точки останова
        System.out.println("opened");
        Selenide.closeWebDriver();
    }
}
