package dev.park.e.bookcafemanager.dto;

import dev.park.e.bookcafemanager.domain.Book;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

public class BookDto {

    @Getter
    @Setter
    public static class Request implements Convertible {
        private Long categoryId;
        private String title;
        private String author;
        private String publisher;
        private Short volume;
        private String shelfName;
        private Short rowNumber;
        private Boolean finished;
        private Boolean forAdult;
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Response {

        private Long id;
        private Long categoryId;
        private String title;
        private String author;
        private String publisher;
        private Short volume;
        private String shelfName;
        private Short rowNumber;
        private Boolean finished;
        private Boolean forAdult;

        public Response(Book entity) {
            this.id = entity.getId();
            this.categoryId = entity.getCategory().getId();
            this.title = entity.getTitle();
            this.author = entity.getAuthor();
            this.publisher = entity.getPublisher();
            this.volume = entity.getVolume();
            this.shelfName = entity.getShelfName();
            this.rowNumber = entity.getRowNumber();
            this.finished = entity.getFinished();
            this.forAdult = entity.getForAdult();
        }

        public static List<Response> listOf(List<Book> entities) {
            return entities.stream().map(Response::new).collect(Collectors.toList());
        }
    }
}
