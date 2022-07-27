package com.devsuperior.bds04.services;

import com.devsuperior.bds04.dto.CityDTO;
import com.devsuperior.bds04.dto.EventDTO;
import com.devsuperior.bds04.entities.City;
import com.devsuperior.bds04.entities.Event;
import com.devsuperior.bds04.repositories.CityRepository;
import com.devsuperior.bds04.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventService {

    @Autowired
    private EventRepository repository;

    public List<EventDTO> findAll() {

        List<Event> result = repository.findAll();

        return result.stream().map( x-> new EventDTO(x)).collect(Collectors.toList());
    }

    public EventDTO insert(EventDTO dto) {

        Event entity = new Event();
        entity.setName(dto.getName());
        entity.setUrl(dto.getUrl());
        entity.setDate(dto.getDate());
        entity.setCity(new City(dto.getCityId(), null));
        entity = repository.save(entity);

        return new EventDTO(entity);
    }

    public Page<EventDTO> findAllPaged(Pageable pageable) {

        Page<Event> result = repository.findAll(pageable);

        return result.map(x -> new EventDTO(x));
    }

//    private void copyDtoToEntity(EventDTO dto, Event entity) {
//
//
//    }

    }
