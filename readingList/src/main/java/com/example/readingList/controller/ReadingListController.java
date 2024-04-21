package com.example.readingList.controller;

import com.example.readingList.model.Book;
import com.example.readingList.properties.AmazonPropreties;
import com.example.readingList.repository.ReadingListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import java.util.List;

@Controller
@RequestMapping("/")
public class ReadingListController {

    private ReadingListRepository readingListRepository;
    private AmazonPropreties amazonPropreties;


    private String associateId;
    @Autowired
    public ReadingListController(
            ReadingListRepository readingListRepository, AmazonPropreties amazonPropreties) {
        this.readingListRepository = readingListRepository;
        this.amazonPropreties = amazonPropreties;
    }

    public void setAssociateId(String associateId) {
        this.associateId = associateId;
    }
    @RequestMapping(value="/{reader}", method=RequestMethod.GET)
    public String readersBooks(
            @PathVariable("reader") String reader,
            Model model) {
        List<Book> readingList =
                readingListRepository.findByReader(reader);
        if (readingList != null) {
            model.addAttribute("books", readingList);
            model.addAttribute("reader", reader);
            System.out.println("Hello Properties " + amazonPropreties.getAssociateId());
            model.addAttribute("amazonID", amazonPropreties.getAssociateId());
        }
        return "index";
    }

    @RequestMapping(value = "/{reader}", method=RequestMethod.POST)
    public String addToReadingList(
            @PathVariable("reader") String reader, Book book) {
        book.setReader(reader);
        readingListRepository.save(book);
        return "redirect:/{reader}";
    }

}
