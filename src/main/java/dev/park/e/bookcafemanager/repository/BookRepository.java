package dev.park.e.bookcafemanager.repository;

import dev.park.e.bookcafemanager.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM book ORDER BY id DESC LIMIT :rowCount, :rowLimit")
    List<Book> findAll(@Param("rowCount") long rowCount, @Param("rowLimit") int rowLimit);


}
