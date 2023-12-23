package tests.files.code.from.lesson;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.opencsv.CSVReader;
import model.Glossary;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class FilesParsingTests {

    private ClassLoader cl = FilesParsingTests.class.getClassLoader();
    private static final Gson gson = new Gson();

    @Test
    void pdfFileParsingTest() throws Exception {
        open("https://junit.org/junit5/docs/current/user-guide/");
        File downloaded = $("[href*='junit-user-guide-5.10.1.pdf']").download();
        PDF pdf = new PDF(downloaded);
        String expectedAuthor = "Stefan Bechtold, Sam Brannen, Johannes Link, Matthias Merdes, Marc Philipp, Juliette" +
                " de Rancourt, Christian Stein";
        Assertions.assertEquals(expectedAuthor, pdf.author);
    }

    @Test
    void excelFileParsingTest() {
        open("https://www.exceldemy.com/excel-data-for-practice-free-download/");
        File downloaded = $("[href*='Data-for-Practice.xlsx']").download();
        XLS xls = new XLS(downloaded);
        System.out.println();
        String expected = "Excel Data for Practice Free Download";
        String actualValue = xls.excel.getSheetAt(0).getRow(1).getCell(1).getStringCellValue();
        Assertions.assertTrue(actualValue.contains(expected));
    }

    @Test
    void csvFileParsingTest() throws Exception {
        try (InputStream is = cl.getResourceAsStream("example.csv");
             CSVReader csvReader = new CSVReader(new InputStreamReader(is))) {

            List<String[]> data = csvReader.readAll();

            Assertions.assertEquals(2, data.size());
            Assertions.assertArrayEquals(new String[]{"Selenide", "https://ru.selenide.org/"}, data.get(0));
            Assertions.assertArrayEquals(new String[]{"JUnit 5", "https://junit.org/junit5/"}, data.get(1));

        }
    }

    @Test
    void zipFileParsingTest() throws Exception {
        try (ZipInputStream zis = new ZipInputStream(cl.getResourceAsStream("example.zip"))) {

            ZipEntry entry;

            while ((entry = zis.getNextEntry()) != null) {
                System.out.println(entry.getName());
            }
        }
    }

    @Test
    void jsonFileParsingTest() throws Exception {
        try (Reader reader = new InputStreamReader(cl.getResourceAsStream("glossary.json"))) {
            JsonObject actual = gson.fromJson(reader, JsonObject.class);

            Assertions.assertEquals("example glossary", actual.get("title").getAsString());
            Assertions.assertEquals(36485, actual.get("ID").getAsInt());

            JsonObject inner = actual.get("glossary").getAsJsonObject();

            Assertions.assertEquals("SGML", inner.get("SortAs").getAsString());
            Assertions.assertEquals("Standard Generalized Markup Language", inner.get("GlossTerm").getAsString());
            Assertions.assertEquals("SGML", inner.get("Acronym").getAsString());

        }
    }

    @Test
    void jsonFileParsingImprovedTest() throws Exception {
        try (Reader reader = new InputStreamReader(cl.getResourceAsStream("glossary.json"))) {
            Glossary actual = gson.fromJson(reader, Glossary.class);

            Assertions.assertEquals("example glossary", actual.getTitle());
            Assertions.assertEquals(36485, actual.getId());
            Assertions.assertEquals("SGML", actual.getGlossary().getSortAs());
            Assertions.assertEquals("Standard Generalized Markup Language", actual.getGlossary().getGlossTerm());
            Assertions.assertEquals("SGML", actual.getGlossary().getAcronym());
        }
    }
}
