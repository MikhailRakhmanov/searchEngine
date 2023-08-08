package searchengine.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "pages")
@Getter
@Setter
public class PageDAO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    long siteId;
    String path;
    short code;
    @Column(columnDefinition = "TEXT")
    String content;
}
