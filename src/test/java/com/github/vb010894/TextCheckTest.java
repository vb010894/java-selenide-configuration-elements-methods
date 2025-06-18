package com.github.vb010894;

import com.codeborne.selenide.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

/// Тест FastSetValue
public class TextCheckTest {

    String randString = UUID.randomUUID().toString();

    /// Настройка драйвера
    @BeforeEach
    public void setup() {
        WebDriverManager.chromedriver().setup();
    }

    /// Тест PARTIAL_TEXT
    @Test
    @DisplayName("Тест PARTIAL_TEXT")
    public void testFastSetValue() {
        Configuration.textCheck = TextCheck.PARTIAL_TEXT;
        Selenide.open("https://ya.ru");
        long start = System.currentTimeMillis();
        SelenideElement search = Selenide.$x(".//input[@aria-label='Запрос']").shouldBe(Condition.exist);
        search.setValue(randString);
        long end = System.currentTimeMillis();
        System.out.println("Duration: " + (end - start));
        String partialValue = randString.split("-")[0];
        System.out.println("Partial value: " + partialValue);
        search.shouldHave(Condition.value(partialValue));
    }

    /// Тест FULL_TEXT
    @Test
    @DisplayName("Тест FULL_TEXT")
    public void testNonFastSetValue() {
        Configuration.textCheck = TextCheck.FULL_TEXT;
        Selenide.open("https://ya.ru");
        long start = System.currentTimeMillis();
        SelenideElement search = Selenide.$x(".//input[@aria-label='Запрос']").shouldBe(Condition.exist);
        search.setValue(randString);
        long end = System.currentTimeMillis();
        System.out.println("Duration: " + (end - start));
        String partialValue = randString.split("-")[0];
        System.out.println("Partial value: " + partialValue);
        search.shouldHave(Condition.value(partialValue));
    }


}
