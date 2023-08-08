package searchengine.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

@Entity
@Table(name = "pages")
@Getter
@Setter
public class PageDAO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "INT NOT NULL AUTO_INCREMENT")
    long id;

    @Override
    public String toString() {
        return "PageDAO{" +
                "id=" + id +
                ", site=" + site +
                ", path='" + path + '\'' +
                ", code=" + code +
                '}';
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "site_id", referencedColumnName = "id")
    SiteDAO site;
    @Column(columnDefinition = "TEXT NOT NULL")
    String path;
    @Column(columnDefinition = "INT NOT NULL")
    int code;
    @Column(columnDefinition = "MEDIUMTEXT NOT NULL")
    String content;
}
