package com.example.CropBackend.Config;

import com.example.CropBackend.Entity.Crop;
import com.example.CropBackend.Repository.CropRepo;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataLoader implements ApplicationRunner {
    private final CropRepo cropRepo;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (cropRepo.count() == 0) {
            Resource resource = new ClassPathResource("Agriculture_price_dataset.csv");
            try (Reader reader = new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8)) {
                CsvToBean<Crop> csvToBean = new CsvToBeanBuilder<Crop>(reader)
                        .withType(Crop.class)
                        .withIgnoreLeadingWhiteSpace(true)
                        .withIgnoreEmptyLine(true)
                        .withThrowExceptions(true)
                        .build();

                List<Crop> crops = csvToBean.parse();
                csvToBean.getCapturedExceptions().forEach(e ->
                        log.warn("Skipped Line: {}", e.getMessage())
                );
                cropRepo.saveAll(crops);
                log.info("Records loaded into the Database");
            }
        }
    }
}

