package com.example.readingList.repository;


import java.util.List;

import com.example.readingList.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReadingListRepository extends JpaRepository<Book, Long> {
    List<Book> findByReader(String reader);

}