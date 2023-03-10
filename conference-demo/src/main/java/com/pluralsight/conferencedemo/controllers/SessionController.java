package com.pluralsight.conferencedemo.controllers;

import com.pluralsight.conferencedemo.models.Session;
import com.pluralsight.conferencedemo.repositories.SessionRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vi/sessions")
public class SessionController {
      @Autowired
      private SessionRepository sessionRepository;

      @GetMapping
      public List<Session> list(){
          return sessionRepository.findAll();
      }

      @GetMapping
      @RequestMapping("{id}")
      public  Session get(@PathVariable long id){
          return sessionRepository.findById(id).get();
      }

      @PostMapping
      public  Session create(@RequestBody final  Session session){
          return sessionRepository.saveAndFlush(session);
      }
      @RequestMapping(value = {"id"}, method = RequestMethod.DELETE)
      public void delete (@PathVariable Long id){
            //Also need to check for children records before deleting
            sessionRepository.deleteById(id);
      }

//      @RequestMapping(value = {"id"}, method =  RequestMethod.PUT)
      @PutMapping(path = "/{id}")
      public  Session update (@PathVariable Long id , @RequestBody final Session session){
            Session existingSession = sessionRepository.findById(id).get();
            //TODO: add  validation that all attribute are passed in otherwise return a 400 bad payload
            BeanUtils.copyProperties(session,existingSession,"session_id");
            return sessionRepository.saveAndFlush(existingSession);
      }





}
