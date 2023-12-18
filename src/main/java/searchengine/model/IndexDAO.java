package searchengine.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="`index`")
@Data
public class IndexDAO {
    @Id
    @Column(columnDefinition = "INT NOT NULL AUTO_INCREMENT")
    Long id;
    @Column(columnDefinition = "INT NOT NULL")
    Integer page_id;
    @Column(columnDefinition = "INT NOT NULL")
    Integer lemma_id;
    @Column(name="`rank`",columnDefinition = "FLOAT NOT NULL")
    Float rank;
}
