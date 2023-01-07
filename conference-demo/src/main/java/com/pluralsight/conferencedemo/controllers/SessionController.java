package com.pluralsight.conferencedemo.controllers;

import com.pluralsight.conferencedemo.models.Session;
import com.pluralsight.conferencedemo.repositories.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vi/sessions ")
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
          return sessionRepository.getOne(id);
      }

      @PostMapping
      public  Session create(@RequestBody final  Session session){
          return sessionRepository.saveAndFlush(session);
      }
}
