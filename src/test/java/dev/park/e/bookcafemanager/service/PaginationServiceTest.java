package dev.park.e.bookcafemanager.service;

import dev.park.e.bookcafemanager.dto.Pagination;
import dev.park.e.bookcafemanager.dto.Search;
import dev.park.e.bookcafemanager.repository.BookMapper;
import dev.park.e.bookcafemanager.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class PaginationServiceTest {

    @Mock
    BookMapper bookMapper;
    @Mock
    BookRepository bookRepository;
    @InjectMocks
    PaginationService paginationService;

    @Test
    void 쿼리스트링이_없는_경우_도서목록_페이징() {
        //given
        long bookCount = 259L;
        int currentPage = 11;
        Search search = new Search();

        given(bookRepository.count()).willReturn(bookCount);

        //when
        Pagination pagination = paginationService.getPagination(currentPage, search);

        //then
        verify(bookRepository).count();
        verify(bookMapper, never()).count(any(Search.class));
        assertThat(pagination.getCurrentPage()).isEqualTo(currentPage);
        assertThat(pagination.getBookCount()).isEqualTo(bookCount);
    }

    @Test
    void 쿼리스트링이_있는_경우_도서검색_페이징() {
        //given
        long bookCount = 259L;
        int currentPage = 11;
        Search search = new Search("title", "word1 word2");
        given(bookMapper.count(any(Search.class))).willReturn(bookCount);

        //when
        Pagination pagination = paginationService.getPagination(currentPage, search);

        //then
        verify(bookMapper).count(any(Search.class));
        verify(bookRepository, never()).count();
        assertThat(pagination.getCurrentPage()).isEqualTo(currentPage);
        assertThat(pagination.getBookCount()).isEqualTo(bookCount);
    }
}
