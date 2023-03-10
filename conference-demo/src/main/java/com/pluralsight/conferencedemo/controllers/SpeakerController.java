package com.pluralsight.conferencedemo.controllers;

import com.pluralsight.conferencedemo.models.Speaker;
import com.pluralsight.conferencedemo.repositories.SpeakerRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vi/speaker")
public class SpeakerController {
    @Autowired
    SpeakerRepository speakerRepository;

    @GetMapping
    List<Speaker> list(){
        return speakerRepository.findAll();
    }

    @GetMapping
    @RequestMapping("{id}")
    public  Speaker  get(@PathVariable long id){
         return speakerRepository.getOne(id);
    }

    @PostMapping
    public  Speaker create(@RequestBody final Speaker speaker){
          return  speakerRepository.saveAndFlush(speaker);
    }

    @RequestMapping(value = {"id"},method = RequestMethod.DELETE)
    public  void  delete(@PathVariable  Long id){
          speakerRepository.deleteById(id);
    }

    @RequestMapping(value = {"id"}, method = RequestMethod.PUT)
    public Speaker update(@PathVariable Long id, @RequestBody Speaker speaker){
           Speaker existingSpeaker = speakerRepository.getOne(id);
           BeanUtils.copyProperties(speaker,existingSpeaker,"speaker_id");
           return speakerRepository.saveAndFlush(existingSpeaker);
    }
}
