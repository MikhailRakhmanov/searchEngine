package searchengine.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "lemma")
@Data
public class LemmaDAO {
    @Id
    @Column(columnDefinition = "INT NOT NULL AUTO_INCREMENT")
    Long id;

    @Column(columnDefinition = "INT NOT NULL")
    Integer site_id;

    @Column(columnDefinition = "VARCHAR(255) NOT NULL")
    String lemma;

    @Column(columnDefinition = "INT NOT NULL")
    Integer frequency;
}
