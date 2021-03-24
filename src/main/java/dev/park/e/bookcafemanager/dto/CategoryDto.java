package dev.park.e.bookcafemanager.dto;

import dev.park.e.bookcafemanager.domain.Category;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

public class CategoryDto {

    @Getter
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Response {
        private Long id;
        private String name;

        public Response(Category entity) {
            this.id = entity.getId();
            this.name = entity.getName();
        }

        public static List<Response> listOf(List<Category> entities) {
            return entities.stream().map(Response::new).collect(Collectors.toList());
        }
    }
}
