package searchengine.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import searchengine.model.enums.Status;

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
