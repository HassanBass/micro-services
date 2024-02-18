package com.hassan.dev.microservices.controller;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.hassan.dev.microservices.entity.FilterableEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("filter")
public class FilterController {
    @GetMapping("/single-bean")
    public MappingJacksonValue getEntity(){
        FilterableEntity entity = new FilterableEntity("val1", "val2", "val3");

        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(entity);
        //adding the filter
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1");
        //add the filter to the filter provide and link that filter to the @JSONFILTER provided in the bean
        FilterProvider filters = new SimpleFilterProvider().addFilter("entityFilter", filter);
        mappingJacksonValue.setFilters(filters);
        return mappingJacksonValue;
        //return entity;
    }

    @GetMapping("/list")
    public MappingJacksonValue getEntities(){
        List<FilterableEntity> entities = new ArrayList<>();
        entities.add(new FilterableEntity("val1", "val2", "val3"));
        entities.add(new FilterableEntity("val4", "val5", "val6"));

        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(entities);
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field2", "field3");
        FilterProvider filters = new SimpleFilterProvider().addFilter("entityFilter", filter);
        mappingJacksonValue.setFilters(filters);


        return mappingJacksonValue;
    }
}
