package dev.park.e.bookcafemanager.service;

import dev.park.e.bookcafemanager.dto.Pagination;
import dev.park.e.bookcafemanager.dto.Search;
import dev.park.e.bookcafemanager.repository.BookMapper;
import dev.park.e.bookcafemanager.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PaginationService {

    final BookRepository bookRepository;
    final BookMapper bookMapper;

    public Pagination getPagination(int currentPage, Search search) {
        Pagination pagination;

        if (search.getCriteria() == null) {
            pagination = new Pagination(bookRepository.count(), currentPage);
        } else {
            pagination = new Pagination(bookMapper.count(search), currentPage);
        }
        return pagination;
    }
}
