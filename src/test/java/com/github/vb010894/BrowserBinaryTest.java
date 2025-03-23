package com.github.vb010894;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/// Тест пути драйвера
public class BrowserBinaryTest {

    /// Настройка драйвера
    @BeforeAll
    public static void setup() {
        Configuration.browserBinary = "C:/driver";
        Configuration.baseUrl = "https://dzen.ru";
        Configuration.browser = "chrome";
    }

    /// Тест открытия с помощью Chrome
    @Test
    public void testWithChrome() {
        Selenide.open("/id/65d7431f1759d9595653fed1");
        // Оставил для точки останова
        System.out.println("opened");
        Selenide.closeWebDriver();
    }

}
