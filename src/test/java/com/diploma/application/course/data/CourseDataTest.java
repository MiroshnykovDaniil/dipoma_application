package com.diploma.application.course.data;

import com.diploma.application.DiplomaApplicationTests;
import com.diploma.application.model.course.data.PdfData;
import org.junit.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class CourseDataTest extends DiplomaApplicationTests {



    @Test
    public void PdfFileTest() throws IOException {
        PdfData pdfData = new PdfData();

        byte[] pdfDataPdf = pdfData.getPdf("d:\\","sample");

        pdfData.savePdf(pdfDataPdf,"test","d:\\");

        assertThat(pdfData.getPdf("d:\\","test")).isEqualTo(pdfDataPdf);
    }

}
