package dev.park.e.bookcafemanager.repository;

import dev.park.e.bookcafemanager.ObjectFactory;
import dev.park.e.bookcafemanager.domain.Book;
import dev.park.e.bookcafemanager.domain.Category;
import dev.park.e.bookcafemanager.dto.BookDto;
import dev.park.e.bookcafemanager.dto.Pagination;
import dev.park.e.bookcafemanager.dto.Search;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.context.ApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@MybatisTest
@AutoConfigureDataJpa
class BookMapperTest {

    static String[] words = {
            "제주도 고무줄 배영",
            "제주도고무줄배영",
            "제주도!고무줄@배영",
            "제$주도고무!줄배%영",
            "제주%^도고무@^줄배영",
            "제주도  고무줄  배영",
            "제주도(고무줄)배영",
            "제주도./고무줄'=배영",
            "제%주^도@고(무#줄&배@영",
            "제주^^도고##$무줄^^배영",
            "제주도DFS고무줄dsf배영", //구분자없는x
            "제주s도고무줄배e영",   //x
            "제주#도고무줄배&영",
            "제주고도무줄배영"};    //x
    @Autowired
    BookMapper bookMapper;

    @BeforeAll
    static void setUp(ApplicationContext applicationContext) {

        CategoryRepository categoryRepository = applicationContext.getBean(CategoryRepository.class);
        BookRepository bookRepository = applicationContext.getBean(BookRepository.class);

        Category category = ObjectFactory.getCategoryEntityWithoutId(1);
        categoryRepository.save(category);

        List<Book> books = new ArrayList<>();

        for (String word : words) {
            books.add(Book.builder()
                    .title(word)
                    .author(word)
                    .publisher("출판사")
                    .volume((short) 1)
                    .shelfName("1")
                    .rowNumber((short) 1)
                    .finished(true)
                    .forAdult(true)
                    .category(category)
                    .build());
        }

        bookRepository.saveAll(books);
    }

    @Test
    void 구분자있는_순서유지_도서_검색() {
        //given
        String criteria = "title";
        String keyword = "제주도 고무줄 배영";
        Search search = new Search(criteria, keyword);

        //when
        long count = bookMapper.count(search);
        List<BookDto.Response> books = bookMapper.findUsingLimitBy(0, Pagination.ROW_LIMIT, search);

        //then
        assertThat(count).isEqualTo(12);
        assertThat(books.size()).isEqualTo(10);
        assertThat(books.get(0).getTitle()).isEqualTo("제주도고무줄배영");
    }

    @Test
    void 구분자있는_순서변경_도서_검색() {
        //given
        String criteria = "title";
        String keyword = "제주도 고무줄 배영";
        Search search = new Search(criteria, keyword);

        //when
        long count = bookMapper.count(search);
        List<BookDto.Response> books = bookMapper.findUsingLimitBy(0, Pagination.ROW_LIMIT, search);

        //then
        assertThat(count).isEqualTo(12);
        assertThat(books.size()).isEqualTo(10);
        assertThat(books.get(0).getTitle()).isEqualTo("제주도고무줄배영");
    }

    @Test
    void 구분자없는_순서유지_도서_검색() {
        //given
        String criteria = "title";
        String keyword = "제주도고무줄배영";
        Search search = new Search(criteria, keyword);

        //when
        long count = bookMapper.count(search);
        List<BookDto.Response> books = bookMapper.findUsingLimitBy(0, Pagination.ROW_LIMIT, search);

        //then
        assertThat(count).isEqualTo(11);
        assertThat(books.size()).isEqualTo(10);
        assertThat(books.get(0).getTitle()).isEqualTo("제주도고무줄배영");
    }

    @Test
    void 구분자없는_순서변경_도서_검색() {
        //given
        String criteria = "title";
        String keyword = "제주도배영고무줄";
        Search search = new Search(criteria, keyword);

        //when
        long count = bookMapper.count(search);
        List<BookDto.Response> books = bookMapper.findUsingLimitBy(0, Pagination.ROW_LIMIT, search);

        //then
        assertThat(count).isEqualTo(0);
        assertThat(books.size()).isEqualTo(0);
    }


    @Test
    void 구분자있는_순서유지_도서_자동완성_검색() {
        //given
        String criteria = "title";
        String keyword = "제주도 고무줄 배영";
        Search search = new Search(criteria, keyword);

        //when
        long count = bookMapper.count(search);
        List<BookDto.Response> books = bookMapper.findBy(search);

        //then
        assertThat(count).isEqualTo(12);
        assertThat(books.size()).isEqualTo(12);
        assertThat(books.get(0).getTitle()).isEqualTo("제주도고무줄배영");
    }

    @Test
    void 구분자있는_순서변경_도서_자동완성_검색() {
        //given
        String criteria = "title";
        String keyword = "제주도 고무줄 배영";
        Search search = new Search(criteria, keyword);

        //when
        long count = bookMapper.count(search);
        List<BookDto.Response> books = bookMapper.findBy(search);

        //then
        assertThat(count).isEqualTo(12);
        assertThat(books.size()).isEqualTo(12);
        assertThat(books.get(0).getTitle()).isEqualTo("제주도고무줄배영");
    }

    @Test
    void 구분자없는_순서유지_도서_자동완성_검색() {
        //given
        String criteria = "title";
        String keyword = "제주도고무줄배영";
        Search search = new Search(criteria, keyword);

        //when
        long count = bookMapper.count(search);
        List<BookDto.Response> books = bookMapper.findBy(search);

        //then
        assertThat(count).isEqualTo(11);
        assertThat(books.size()).isEqualTo(11);
        assertThat(books.get(0).getTitle()).isEqualTo("제주도고무줄배영");
    }

    @Test
    void 구분자없는_순서변경_도서_자동완성_검색() {
        //given
        String criteria = "title";
        String keyword = "제주도배영고무줄";
        Search search = new Search(criteria, keyword);

        //when
        long count = bookMapper.count(search);
        List<BookDto.Response> books = bookMapper.findBy(search);

        //then
        assertThat(count).isEqualTo(0);
        assertThat(books.size()).isEqualTo(0);
    }

}