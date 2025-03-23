package com.github.vb010894;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/// Тесты headless
public class HeadlessTest {

    /// Настройка драйвера
    @BeforeAll
    public static void setup() {
        WebDriverManager.chromedriver().setup();
        Configuration.baseUrl = "https://dzen.ru";
        Configuration.browser = "chrome";
        Configuration.headless = true;
    }

    /// Тест открытия Chrome в режиме headless
    @Test
    public void testWithChrome() {
        Selenide.open("/id/65d7431f1759d9595653fed1");
        // Оставил для точки останова
        System.out.println("opened");
        Selenide.closeWebDriver();
    }
}
