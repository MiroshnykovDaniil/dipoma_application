package com.diploma.application.course.data;

import com.diploma.application.DiplomaApplicationTests;
import com.diploma.application.model.course.data.PdfData;
import com.diploma.application.repository.course.PdfDataRepository;
import com.diploma.application.service.course.data.PdfDataService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class CourseDataTest extends DiplomaApplicationTests {

    private final Logger logger = LogManager.getLogger();

    @Autowired
    private PdfDataRepository pdfDataRepository;
    @Autowired
    private PdfDataService pdfDataService;



    @Test
    public void PdfFileTest() throws IOException {
        PdfData pdfData = new PdfData();

        byte[] pdfDataPdf = pdfData.getPdf("d:\\","sample");

        pdfData.savePdf(pdfDataPdf,"test","d:\\");

        assertThat(pdfData.getPdf("d:\\","test")).isEqualTo(pdfDataPdf);
    }

    @Test
    @Transactional
    public void PdfServiceTest() throws IOException {
        PdfData pdfData = new PdfData();
        byte[] pdfDataPdf = pdfData.getPdf("d:\\","sample");
        pdfData = pdfDataService.createPdf("d:\\");
        pdfData = pdfDataService.savePdf(pdfData,pdfDataPdf);
        logger.info("PdfServiceTest: pdfData:id"+pdfData.getId());
        byte[] pdfDataPdf2 = pdfDataService.getPdfByteArray(pdfData.getId());

        assertThat(pdfDataPdf).isEqualTo(pdfDataPdf2);
    }

}
