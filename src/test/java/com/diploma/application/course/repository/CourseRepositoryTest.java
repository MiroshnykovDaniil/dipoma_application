package com.diploma.application.course.repository;


import com.diploma.application.DiplomaApplicationTests;
import com.diploma.application.model.User;
import com.diploma.application.model.course.Course;
import com.diploma.application.model.course.data.CourseData;
import com.diploma.application.model.course.data.ImageData;
import com.diploma.application.model.course.data.TextData;
import com.diploma.application.model.course.data.TextImageData;
import com.diploma.application.repository.UserRepository;
import com.diploma.application.repository.course.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class CourseRepositoryTest extends DiplomaApplicationTests {

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    CourseDataRepository courseDataRepository;

    @Autowired
    ImageDataRepository imageDataRepository;

    @Autowired
    TextDataRepository textDataRepository;

    @Autowired
    TextImageDataRepository textImageDataRepository;

    @Autowired
    UserRepository userRepository;

    @Test
    public void courseRepositoryTest(){
        Course course = new Course();
        User creator = new User();
        userRepository.save(creator);
        course.setCreator(creator);
        course.setTitle("title");
        course.setDescription("desc");
        courseRepository.save(course);

        Course test = courseRepository.getOne(course.getId());

        assertThat(test.equals(course));
    }

    @Test
    public void courseDataRepositoryTest(){
        CourseData courseData = new CourseData();
        courseData.setTitle("title");
        courseData.setDescription("desc");
        courseDataRepository.save(courseData);
        assertThat(courseDataRepository.getOne(courseData.getId())).isEqualTo(courseData);
    }

    @Test
    public void imageDataRepositoryTest(){
        ImageData imageData = new ImageData();
        imageData.setImage(new byte[] {1,2,3,43,'s',1,2,3,127});
        imageDataRepository.save(imageData);
        assertThat(imageDataRepository.getOne(imageData.getId())).isEqualTo(imageData);
    }

    @Test
    public void textDataRepositoryTest(){
        TextData textData = new TextData();
        textData.setTitle("title");
        textData.setDescription("desc");
        textData.setText("some text here \r\n");
        textDataRepository.save(textData);
        TextData res = textDataRepository.getOne(textData.getId());
        assertThat(res).isEqualTo(textData);
    }

    @Test
    public void textImageDataRepositoryTest(){
        TextImageData textImageData = new TextImageData();
        textImageData.setTitle("title");
        textImageData.setDescription("desc");
        textImageData.setText("some text here \r\n");
        ImageData imageData = new ImageData();
        List<ImageData> dataList = new ArrayList<>();
        imageDataRepository.save(imageData);
        dataList.add(imageData);
        dataList.add(imageData);
        dataList.add(imageData);
        textImageData.setImages(dataList);
        textDataRepository.save(textImageData);
        ImageData res = imageDataRepository.getOne(textImageData.getId());
        assertThat(res).isEqualTo(textImageData);
    }


}
