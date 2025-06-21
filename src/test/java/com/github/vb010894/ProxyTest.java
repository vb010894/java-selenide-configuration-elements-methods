package com.github.vb010894;

import com.browserup.bup.proxy.CaptureType;
import com.codeborne.selenide.*;
import de.sstoehr.harreader.model.HarEntry;
import de.sstoehr.harreader.model.HarPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

/// Тесты работы с прокси
public class ProxyTest {

    /// Настройка драйвера
    @BeforeAll
    public static void setup() {
        WebDriverManager.chromedriver().setup();
    }

    /// Тест открытия с прокси
    @Test
    @DisplayName("Тест открытия канала через Proxy")
    public void testWithProxy() {
        Configuration.baseUrl = "https://dzen.ru";
        Configuration.proxyEnabled = true;
        Configuration.proxyHost = "127.0.0.2";
        Configuration.proxyPort = 12345;
        Selenide.open("/id/65d7431f1759d9595653fed1");

        // Получаем прокси
        com.browserup.bup.BrowserUpProxy proxy = WebDriverRunner.getSelenideProxy().getProxy();

        // Говорим что будем отслеживать все (При желании можно выбрать конкретные типы файлов)
        proxy.setHarCaptureTypes(CaptureType.getAllContentCaptureTypes());

        // Говорим, что будем запоминать контент запросов и ответов
        proxy.enableHarCaptureTypes(CaptureType.REQUEST_CONTENT, CaptureType.RESPONSE_CONTENT);

        // Отсюда начинается запись трафика
        proxy.newHar("MyHar");

        Selenide.$x(".//form[@action='/search']").as("Форма запроса").shouldBe(Condition.visible).click();
        Selenide.$x(".//input[@name='query']").as("Строка запроса").shouldBe(Condition.visible).val("Записки тестировщика");
        Selenide.$x(".//button[@aria-label='Кнопка Найти']").as("Кнопка найти").shouldBe(Condition.visible).click();
        Selenide.$x(".//div[@id='LayoutContentMicroRoot']").as("Контент запроса").shouldBe(Condition.visible);

        // Сетевые запросы
        List<HarEntry> records = proxy.getHar().getLog().getEntries();

        System.out.println("proxy port: " + proxy.getPort());
        System.out.println(records.size());

        Selenide.closeWebDriver();
    }

    /// Загрузка файла методом HTTP
    @Test
    @DisplayName("Загрузка файла методом HTTP")
    public void downloadFileViaHttp() throws URISyntaxException, IOException {
        // Складывать файлы загрузки будем в папку download/http рядом с проектом
        Path root = Paths.get("download", "http");
        if(!Files.exists(root))
            Files.createDirectories(root);

        // Настройка папки для скачивания файлов
        Configuration.fileDownload = FileDownloadMode.HTTPGET;
        Configuration.downloadsFolder = root.toAbsolutePath().toString();
        Selenide.open("https://google.com/");
        File download = Selenide.download("https://drive.usercontent.google.com/u/0/uc?id=1jd_gIsMtUwHodtL_NoJ-s6h1_gRHKRtl&export=download");
        System.out.println(download.getAbsolutePath());
    }

    /// Загрузка файла методом FOLDER
    @Test
    @DisplayName("Загрузка файла методом FOLDER")
    public void downloadFileViaFolder() throws URISyntaxException, IOException {
        // Складывать файлы загрузки будем в папку download/folder рядом с проектом
        Path root = Paths.get("download", "folder");
        if(!Files.exists(root))
            Files.createDirectories(root);

        // Настройка папки для скачивания файлов
        Configuration.fileDownload = FileDownloadMode.FOLDER;
        Configuration.downloadsFolder = root.toAbsolutePath().toString();
        Selenide.open("https://google.com/");
        File download = Selenide.download("https://drive.usercontent.google.com/u/0/uc?id=1jd_gIsMtUwHodtL_NoJ-s6h1_gRHKRtl&export=download");
        System.out.println(download.getAbsolutePath());
    }

    /// Загрузка файла методом CDP
    @Test
    @DisplayName("Загрузка файла методом CDP")
    public void downloadFileViaCDP() throws URISyntaxException, IOException {
        // Складывать файлы загрузки будем в папку download/cdp рядом с проектом
        Path root = Paths.get("download", "cdp");
        if(!Files.exists(root))
            Files.createDirectories(root);

        // Настройка папки для скачивания файлов
        Configuration.fileDownload = FileDownloadMode.CDP;
        Configuration.downloadsFolder = root.toAbsolutePath().toString();
        Selenide.open("https://google.com/");
        File download = Selenide.download("https://drive.usercontent.google.com/u/0/uc?id=1jd_gIsMtUwHodtL_NoJ-s6h1_gRHKRtl&export=download");
        System.out.println(download.getAbsolutePath());
    }

    /// Загрузка файла методом Proxy
    @Test
    @DisplayName("Загрузка файла методом Proxy")
    public void downloadFileViaProxy() throws URISyntaxException, IOException {
        // Складывать файлы загрузки будем в папку download/cdp рядом с проектом
        Path root = Paths.get("download", "proxy");
        if(!Files.exists(root))
            Files.createDirectories(root);

        // Настройка папки для скачивания файлов
        Configuration.proxyEnabled = true;
        Configuration.fileDownload = FileDownloadMode.PROXY;
        Configuration.downloadsFolder = root.toAbsolutePath().toString();
        Selenide.open("https://google.com/");

        // Получаем прокси
        com.browserup.bup.BrowserUpProxy proxy = WebDriverRunner.getSelenideProxy().getProxy();

        // Говорим что будем отслеживать все (При желании можно выбрать конкретные типы файлов)
        proxy.setHarCaptureTypes(CaptureType.getAllContentCaptureTypes());

        // Говорим, что будем запоминать контент запросов и ответов
        proxy.enableHarCaptureTypes(CaptureType.REQUEST_CONTENT, CaptureType.RESPONSE_CONTENT, CaptureType.RESPONSE_BINARY_CONTENT);

        // Отсюда начинается запись трафика
        proxy.newHar("MyFileHar");

        File download = Selenide.download("https://drive.usercontent.google.com/u/0/uc?id=1jd_gIsMtUwHodtL_NoJ-s6h1_gRHKRtl&export=download");
        // Сетевые запросы
        List<HarEntry> records = proxy.getHar().getLog().getEntries();
        System.out.println("Количество запросов: " + records.size());
        System.out.println(download.getAbsolutePath());
    }
}
