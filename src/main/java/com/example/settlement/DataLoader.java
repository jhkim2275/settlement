package com.example.settlement;

import com.example.settlement.dto.DataWrapper;
import com.example.settlement.dto.CreatorDTO;
import com.example.settlement.dto.CourseDTO;
import com.example.settlement.entity.SaleRecord;
import com.example.settlement.repository.SaleRecordRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.io.InputStream;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner loadData(SaleRecordRepository saleRepo) {
        return args -> {

            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            InputStream input = getClass()
                    .getClassLoader()
                    .getResourceAsStream("data.json");

            DataWrapper data = mapper.readValue(input, DataWrapper.class);

            // creators
            for (CreatorDTO c : data.getCreators()) {
                System.out.println("Creator: " + c.getName());
            }

            // courses
            for (CourseDTO c : data.getCourses()) {
                System.out.println("Course: " + c.getTitle());
            }

            // sales
            for (SaleRecord s : data.getSaleRecords()) {
                saleRepo.save(s);
            }
        };
    }
}
