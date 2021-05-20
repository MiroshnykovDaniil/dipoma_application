package com.diploma.application.service.course.data;

import com.diploma.application.model.course.data.PdfData;
import com.diploma.application.repository.course.PdfDataRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class PdfDataService {
    private final Logger logger = LogManager.getLogger();


    @Autowired
    private final PdfDataRepository pdfDataRepository;

    public PdfDataService(PdfDataRepository pdfDataRepository) {
        this.pdfDataRepository = pdfDataRepository;
    }


    public PdfData createPdf(String path){
        PdfData pdfData = new PdfData();
        pdfData.setPath(path);
        pdfDataRepository.save(pdfData);
        return pdfData;
    }

    public PdfData savePdf(PdfData pdfData, byte[] file){
        pdfData.savePdf(file,pdfData.getId(),pdfData.getPath());
        return pdfData;
    }

    @Value("${my.property}")
    private String pathToSave;

    public PdfData savePdf(String title, String description, byte[] file){
        PdfData pdfData = new PdfData();
        pdfData.setTitle(title);
        pdfData.setDescription(description);
        pdfData.setPath(pathToSave);
        pdfDataRepository.save(pdfData);
        return savePdf(pdfData,file);
    }

    public byte[] getPdfByteArray(String id) throws IOException {
        PdfData pdfData = pdfDataRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException(id));
        logger.info("getPdfByteArray: path:"+pdfData.getPath()+";id: "+pdfData.getId());
        return pdfData.getPdf(pdfData.getPath(),pdfData.getId());
    }


    public PdfData getPdfData(String id){
        return pdfDataRepository.getOne(id);
    }

}
