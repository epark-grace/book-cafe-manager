package dev.park.e.bookcafemanager.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 10)
    private String name;

    @OneToMany(mappedBy = "category")
    final private List<Book> book = new ArrayList<>();

    @Builder
    public Category(String name) {
        this.name = name;
    }
}
