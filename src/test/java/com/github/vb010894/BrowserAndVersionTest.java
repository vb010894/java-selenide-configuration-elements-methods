package com.github.vb010894;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/// Тесты открытия различных браузеров и версий
public class BrowserAndVersionTest {

    /// Настройка драйвера
    @BeforeAll
    public static void setup() {
        WebDriverManager.chromedriver().setup();
    }

    /// Тест открытия с помощью Chrome
    @Test
    public void testWithChrome() {
        Configuration.baseUrl = "https://dzen.ru";
        Configuration.browser = "chrome";
        Selenide.open("/id/65d7431f1759d9595653fed1");
        // Оставил для точки останова
        System.out.println("opened");
        Selenide.closeWebDriver();
    }

    /// Тест открытия с помощью Edge
    @Test
    public void testWithEdge() {
        Configuration.baseUrl = "https://dzen.ru";
        Configuration.browser = "edge";
        Selenide.open("/id/65d7431f1759d9595653fed1");
        // Оставил для точки останова
        System.out.println("opened");
        Selenide.closeWebDriver();
    }
}
