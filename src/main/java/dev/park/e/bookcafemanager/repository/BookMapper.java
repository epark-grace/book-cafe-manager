package dev.park.e.bookcafemanager.repository;

import dev.park.e.bookcafemanager.dto.BookDto;
import dev.park.e.bookcafemanager.dto.Search;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BookMapper {
    long count(@Param("search") Search search);

    List<BookDto.Response> findAllBy(@Param("rowCount") long rowCount, @Param("rowLimit") int rowLimit, @Param("search") Search search);
}
