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
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @OneToMany(mappedBy = "book")
    final private List<Isbn> isbn = new ArrayList<>();

    @Column(length = 50, nullable = false)
    private String title;

    @Column(length = 50)
    private String author;

    @Column(length = 50)
    private String publisher;

    @Column(nullable = false)
    private Short volume;

    @Column(length = 10, nullable = false)
    private String shelfName;

    @Column(nullable = false)
    private Short rowNumber;

    @Column(nullable = false)
    private Boolean finished;

    @Column(nullable = false)
    private Boolean forAdult;

    @Builder
    public Book(Category category, String title, String author, String publisher, Short volume, String shelfName, Short rowNumber, Boolean finished, Boolean forAdult) {
        this.category = category;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.volume = volume;
        this.shelfName = shelfName;
        this.rowNumber = rowNumber;
        this.finished = finished;
        this.forAdult = forAdult;

        category.getBook().add(this);
    }

    public void update(Category category, String title, String author, String publisher, Short volume, String shelfName, Short rowNumber, Boolean finished, Boolean forAdult) {
        this.category = category;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.volume = volume;
        this.shelfName = shelfName;
        this.rowNumber = rowNumber;
        this.finished = finished;
        this.forAdult = forAdult;
    }

    public void updateShelfName(String newShelfName) {
        this.shelfName = newShelfName;
    }
}
