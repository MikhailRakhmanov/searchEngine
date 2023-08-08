package searchengine.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "sites")
@Getter
@Setter
public class SiteDAO {
    public SiteDAO() {
    }

    public SiteDAO(String url) {
        this.url = url;
        this.name = name;
        status=Status.INDEXING;
        LocalDateTime statusTime=LocalDateTime.now();

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('INDEXING', 'INDEXED')")
    Status status;
    LocalDateTime statusTime;
    String lastError;
    String url;
    String name;


}
