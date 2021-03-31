package dev.park.e.bookcafemanager.repository;

import dev.park.e.bookcafemanager.domain.Isbn;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IsbnRepository extends JpaRepository<Isbn, Long> {
    Optional<Isbn> findByIsbn(String isbn);
}
