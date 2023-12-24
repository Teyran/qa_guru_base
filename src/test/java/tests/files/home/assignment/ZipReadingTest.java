package tests.files.home.assignment;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static org.junit.jupiter.api.Assertions.*;

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
                        XLS xls = new XLS(zin);
                        assertEquals("Franklyn", xls.excel.getSheetAt(0).getRow(12)
                                .getCell(1).getStringCellValue());
                    }
                }
            }
        }
    }

    @Test
    void readCsvFromZipTest() throws Exception {
        try (InputStream is = cl.getResourceAsStream("sampleArchive.zip")) {
            assert is != null;
            try (ZipInputStream zin = new ZipInputStream(is)) {
                ZipEntry entry;
                while ((entry = zin.getNextEntry()) != null) {
                    if (entry.getName().equals("csvFile.csv")) {
                        CSVReader csvReader = new CSVReader(new InputStreamReader(zin));
                        List<String[]> data = csvReader.readAll();
                        assertArrayEquals(new String[]{"City", "State"}, data.get(0));
                        assertArrayEquals(new String[]{"Youngstown", "OH"}, data.get(1));
                        break;
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
                        PDF pdf = new PDF(zin);
                        assertEquals(pdf.numberOfPages, 2);
                        assertTrue(pdf.text.contains("This is a small demonstration .pdf"));
                    }
                }
            }
        }
    }
}