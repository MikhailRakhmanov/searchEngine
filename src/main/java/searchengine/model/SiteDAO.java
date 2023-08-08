package searchengine.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "sites")
@Getter
@Setter
public class SiteDAO {
    public SiteDAO() {
    }

    @Override
    public String toString() {
        return "SiteDAO{" +
                "id=" + id +
                ", status=" + status +
                ", statusTime=" + statusTime +
                ", lastError='" + lastError + '\'' +
                ", url='" + url + '\'' +
                ", name='" + name + '\'' +
                '}';
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
    @Column(columnDefinition = "ENUM('INDEXING', 'INDEXED','FAILED') NOT NULL")
    Status status;
    @Column(columnDefinition = "DATETIME NOT NULL")
    LocalDateTime statusTime;
    @Column(columnDefinition = "TEXT")
    String lastError;
    String url;
    String name;
    @OneToMany(mappedBy = "site",cascade = CascadeType.ALL)
    List<PageDAO> pages;


}
