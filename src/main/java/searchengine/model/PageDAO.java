package searchengine.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "`pages`",indexes = {@Index(name = "`path_index`", columnList="`path`")})
@Data
public class PageDAO implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`id`", columnDefinition = "INT NOT NULL AUTO_INCREMENT")
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

    @Column(name = "`path`",columnDefinition = "VARCHAR(512) NOT NULL")
    String path;
    @Column(name = "`code`", columnDefinition = "INT NOT NULL")
    int code;
    @Column(name = "`content`",columnDefinition = "MEDIUMTEXT NOT NULL")
    String content;
    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "`site_id`", referencedColumnName = "`id`")
    SiteDAO site;
}
