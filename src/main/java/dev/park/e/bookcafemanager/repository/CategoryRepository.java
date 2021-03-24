package dev.park.e.bookcafemanager.repository;

import dev.park.e.bookcafemanager.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
