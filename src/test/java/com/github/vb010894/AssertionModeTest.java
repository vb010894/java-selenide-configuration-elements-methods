package com.github.vb010894;

import com.codeborne.selenide.AssertionMode;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.junit5.SoftAssertsExtension;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

/// Тест AssertionMode
@ExtendWith({SoftAssertsExtension.class})
public class AssertionModeTest {

    /// Настройка драйвера
    @BeforeAll
    public static void setup() {
        WebDriverManager.chromedriver().setup();
    }

    /// Тест STRICT режима AssertionMode
    @Test
    public void testAssertionModeStrict() {
        Selenide.open("https://ya.ru");
        // Выпадет на 'shouldBe'
        Selenide.$x(".//input[@area-label='test']").shouldBe(Condition.exist).click();
    }

    /// Тест SOFT режима AssertionMode
    @Test
    public void testAssertionModeSoft() {
        Configuration.assertionMode = AssertionMode.SOFT;
        Selenide.open("https://ya.ru");

        // Выпадет ошибка на 'click'
        Selenide.$x(".//input[@area-label='test']")
                .shouldBe(Condition.exist)
                .click();
    }
}
