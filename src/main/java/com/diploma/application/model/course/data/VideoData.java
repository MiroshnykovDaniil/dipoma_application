package com.diploma.application.model.course.data;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Entity(name = "video_data")
@Data
@NoArgsConstructor
public class VideoData extends CourseData{


    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name="uuid",strategy = "uuid2")
    private String id;


    @Override
    public String getDataType() {
        return "video";
    }

    public String youtubeLink;

    // path in file system to get file from
    private String path;

    public void saveVideo(byte[] PdfByteArray, String filename, String path){
        try{
            OutputStream out = new FileOutputStream(path+filename+".mp4");
            out.write(PdfByteArray);
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public byte[] getVideo(String path, String filename) throws IOException {
        Path pdfPath = Paths.get(path+filename+".mp4");
        return Files.readAllBytes(pdfPath);
    }

}
