package com.diploma.application.model.course.data;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Entity(name = "pdf_data")
@Data
@NoArgsConstructor
public class PdfData {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name="uuid",strategy = "uuid2")
    private String id;

    // path in file system to get file from
    @NotBlank(message = "PDF path cannot be blank")
    private String path;

    public void savePdf(byte[] PdfByteArray,String filename, String path){
        try{
            OutputStream out = new FileOutputStream(path+filename+".pdf");
            out.write(PdfByteArray);
            out.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public byte[] getPdf(String path, String filename) throws IOException {
        Path pdfPath = Paths.get(path+filename+".pdf");
        return Files.readAllBytes(pdfPath);
    }

}
