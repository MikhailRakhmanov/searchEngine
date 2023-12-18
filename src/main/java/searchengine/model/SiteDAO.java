package searchengine.model;

import jakarta.persistence.*;
import lombok.Data;
import searchengine.model.enums.Status;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "sites")
@Data
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
    @Column(name = "`id`")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "`status`", columnDefinition = "ENUM('INDEXING', 'INDEXED','FAILED') NOT NULL")
    Status status;

    @Column(name = "`status_time`", columnDefinition = "DATETIME NOT NULL")
    LocalDateTime statusTime;

    @Column(name = "`last_error`", columnDefinition = "TEXT")
    String lastError;
    @Column(name = "`url`")
    String url;
    @Column(name = "`name`")
    String name;

    @OneToMany(mappedBy = "site", cascade = CascadeType.ALL)
    List<PageDAO> pages;


}
