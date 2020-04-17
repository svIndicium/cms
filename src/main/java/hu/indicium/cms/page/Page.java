package hu.indicium.cms.page;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Pages")
@NoArgsConstructor
@Getter
@Setter
public class Page {
    @Id
    @SequenceGenerator(name = "user_id_generator", sequenceName = "user_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_generator")
    private Long id;
    private String title;
    private String slug;
    @UpdateTimestamp
    private Date lastEdit;
    private String lastEditedBy;
}
