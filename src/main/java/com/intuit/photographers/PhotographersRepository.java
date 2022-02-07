package com.intuit.photographers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PhotographersRepository {

    @Value("classpath:photographers.json")
    Resource resourceFile;

    public List<Photographer> findAll() {
        try {
            ObjectMapper jsonMapper = new ObjectMapper();
            jsonMapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
            File jsonFile = resourceFile.getFile();
            List<Photographer> photographers = jsonMapper.readValue
                    (jsonFile, new TypeReference<>() {
                    });
            return photographers;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public Photographer findById(int id) {
        List<Photographer> photographers = findAll()
                .stream()
                .filter(p -> p.getId() == id)
                .collect(Collectors.toList());
        if (photographers.size() == 1) {
            return photographers.get(0);
        }
        return null;
    }

    public List<Photographer> findByEvenType(String eventType) {
        List<String> types = new ArrayList<>();

        return findAll()
                .stream()
                .filter(p -> p.getEvent_type().getType().contains(eventType))
                .collect(Collectors.toList());
    }
}
