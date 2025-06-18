package com.github.vb010894;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

/// Тест FastSetValue
public class FastSetValueTest {

    String randString = UUID.randomUUID().toString();

    /// Настройка драйвера
    @BeforeEach
    public void setup() {
        WebDriverManager.chromedriver().setup();
    }

    /// Тест FastSetValue
    @Test
    @DisplayName("Тест с FastSetValue")
    public void testFastSetValue() {
        Configuration.fastSetValue = true;
        Selenide.open("https://ya.ru");
        long start = System.currentTimeMillis();
        Selenide.$x(".//input[@aria-label='Запрос']").shouldBe(Condition.exist).setValue(randString);
        long end = System.currentTimeMillis();
        System.out.println("Duration: " + (end - start));
    }

    /// Тест без FastSetValue
    @Test
    @DisplayName("Тест без FastSetValue")
    public void testNonFastSetValue() {
        Selenide.open("https://ya.ru");
        long start = System.currentTimeMillis();
        Selenide.$x(".//input[@aria-label='Запрос']").shouldBe(Condition.exist).setValue(randString);
        long end = System.currentTimeMillis();
        System.out.println("Duration: " + (end - start));
    }


}
