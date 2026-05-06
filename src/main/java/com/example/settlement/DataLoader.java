package com.example.settlement;

import com.example.settlement.dto.DataWrapper;
import com.example.settlement.dto.CreatorDTO;
import com.example.settlement.dto.CourseDTO;
import com.example.settlement.entity.Course;
import com.example.settlement.entity.Creator;
import com.example.settlement.entity.SaleRecord;
import com.example.settlement.repository.CreatorRepository;
import com.example.settlement.repository.SaleRecordRepository;
import com.example.settlement.repository.CourseRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.io.InputStream;
import java.util.HashMap;

@Configuration
public class DataLoader {

    public static HashMap<String, String> courseToCreator = new HashMap<>();
    @Bean
    CommandLineRunner loadData(
        SaleRecordRepository saleRepo,
        CreatorRepository creatorRepo,
        CourseRepository courseRepo
    ) {
        return args -> {

            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());

            InputStream input = getClass()
                    .getClassLoader()
                    .getResourceAsStream("data.json");

            DataWrapper data = mapper.readValue(input, DataWrapper.class);

            // creators
            HashMap<String, Creator> creatorMap = new HashMap<>();
            for (CreatorDTO c : data.getCreators()) {
                Creator creator = new Creator();
                creator.setId(c.getId());
                creator.setName(c.getName());

                creatorRepo.save(creator);
                creatorMap.put(c.getId(), creator);
            }

            // courses
            HashMap<String, Course> courseMap = new HashMap<>();
            for (CourseDTO c : data.getCourses()) {
                Course course = new Course();
                course.setId(c.getId());
                course.setTitle(c.getTitle());

                course.setCreator(creatorMap.get(c.getCreatorId())); // ⭐ 핵심

                courseRepo.save(course);
                courseMap.put(c.getId(), course);
            }
            // sales
            for (SaleRecord s : data.getSaleRecords()) {
                s.setCourse(courseMap.get(s.getCourseId())); // ⭐ 핵심

                saleRepo.save(s);
            }
        };
    }
}
