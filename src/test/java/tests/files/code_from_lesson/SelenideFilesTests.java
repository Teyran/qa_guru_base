package tests.files.code_from_lesson;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class SelenideFilesTests {

//    static {
//        fileDownload = FileDownloadMode.PROXY;
//    }
//    should be declared if 'href' attribute is not presented in button element

    @Test
    void downLoadFileTest() throws Exception {
        open("https://github.com/junit-team/junit5/blob/main/README.md");
        File downloaded = $(".react-blob-header-edit-and-raw-actions [href*='main/README.md']").download();

        try (InputStream is = new FileInputStream(downloaded)) {
            byte[] data = is.readAllBytes();
            String dataAsString = new String(data, StandardCharsets.UTF_8);
            Assertions.assertTrue(dataAsString.contains("Contributions to JUnit 5 are both welcomed"));
        }

        //FileUtils.readFileToString(downloaded, StandardCharsets.UTF_8);
    }

    @Test
    void uploadFileTest() {
        open("https://fineuploader.com/demos.html");
        $("input[type='file']").uploadFromClasspath("JUnit hierarchy.jpeg");
        $(".qq-file-name").shouldHave(text("JUnit hierarchy.jpeg"));
    }

    //Working with content of files
    //Reading from file: InputStream (works with bytes) / Reader (works with formats: txt, json ...)
    //Writing to files: OutStream (works with bytes) / Writer (works with formats: txt, json ...)

    //Throwable - Super class for all exceptions
    //Checked exceptions - Exception
    //Unchecked exceptions - RuntimeException, Error

}
