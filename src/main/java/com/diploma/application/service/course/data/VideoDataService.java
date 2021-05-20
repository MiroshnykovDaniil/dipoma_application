package com.diploma.application.service.course.data;

import com.diploma.application.model.course.data.PdfData;
import com.diploma.application.model.course.data.VideoData;
import com.diploma.application.repository.course.PdfDataRepository;
import com.diploma.application.repository.course.VideoRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class VideoDataService {

    private final Logger logger = LogManager.getLogger();


    @Autowired
    private final VideoRepository videoRepository;

    @Value("${my.property}")
    private String pathToSave;

    public VideoDataService(VideoRepository videoRepository) {
        this.videoRepository = videoRepository;
    }


    public VideoData createVideo(String path){
        VideoData videoData = new VideoData();
        videoData.setPath(path);
        videoRepository.save(videoData);
        return videoData;
    }

    public VideoData saveVideo(String title, String description, byte[] file){
        VideoData videoData = new VideoData();
        videoData.setTitle(title);
        videoData.setDescription(description);
        videoData.setPath(pathToSave);
        videoRepository.save(videoData);
        return saveVideo(videoData,file);
    }

    public VideoData saveVideo(VideoData videoData, byte[] file){
        videoData.saveVideo(file,videoData.getId(),videoData.getPath());
        return videoData;
    }

    public byte[] getVideoByteArray(String id) throws IOException {
        VideoData videoData = videoRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException(id));
        logger.info("getPdfByteArray: path:"+videoData.getPath()+";id: "+videoData.getId());
        return videoData.getVideo(videoData.getPath(),videoData.getId());
    }

    public VideoData getVideoData(String id){
        return videoRepository.getOne(id);
    }


}
