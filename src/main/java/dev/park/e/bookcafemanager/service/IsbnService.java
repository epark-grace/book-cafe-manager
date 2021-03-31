package dev.park.e.bookcafemanager.service;

import dev.park.e.bookcafemanager.domain.Book;
import dev.park.e.bookcafemanager.domain.Isbn;
import dev.park.e.bookcafemanager.repository.IsbnRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class IsbnService {
    private final IsbnRepository isbnRepository;

    @Transactional(readOnly = true)
    public Isbn getIsbn(String isbn) {
        return isbnRepository.findByIsbn(isbn).orElseThrow(() -> new IllegalArgumentException("IsbnService.getIsbn(" + isbn + ") : 존재하지 않는 ISBN"));
    }

    @Transactional(readOnly = true)
    public Book getBookByIsbn(String isbn) {
        return getIsbn(isbn).getBook();
    }
}
