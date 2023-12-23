package tests.files.home.assignment;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ZipReadingTest {
    private final ClassLoader cl = ZipReadingTest.class.getClassLoader();

    @Test
    void readXlsxFromZipTest() throws IOException {
        try (InputStream is = cl.getResourceAsStream("sampleArchive.zip")) {
            assert is != null;
            try (ZipInputStream zin = new ZipInputStream(is)) {
                ZipEntry entry;
                while ((entry = zin.getNextEntry()) != null) {
                    if (entry.getName().equals("file_example_XLSX_50.xlsx")) {
                        Assertions.assertEquals("file_example_XLSX_50.xlsx", entry.getName());
                        Assertions.assertEquals(7360, entry.getSize());
                    }
                }
            }
        }
    }

    @Test
    void readCsvFromZipTest() throws IOException {
        try (InputStream is = cl.getResourceAsStream("sampleArchive.zip")) {
            assert is != null;
            try (ZipInputStream zin = new ZipInputStream(is)) {
                ZipEntry entry;
                while ((entry = zin.getNextEntry()) != null) {
                    if (entry.getName().equals("cities.csv")) {
                        Assertions.assertEquals("cities.csv", entry.getName());
                        Assertions.assertEquals(8402, entry.getSize());
                    }
                }
            }
        }
    }

    @Test
    void readPDFFromZipTest() throws IOException {
        try (InputStream is = cl.getResourceAsStream("sampleArchive.zip")) {
            assert is != null;
            try (ZipInputStream zin = new ZipInputStream(is)) {
                ZipEntry entry;
                while ((entry = zin.getNextEntry()) != null) {
                    if (entry.getName().equals("sample.pdf")) {
                        Assertions.assertEquals("sample.pdf", entry.getName());
                        Assertions.assertEquals(3028, entry.getSize());
                    }
                }
            }
        }
    }
}