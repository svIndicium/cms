package hu.indicium.cms.page;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "Contents")
@NoArgsConstructor
@Getter
@Setter
public class Content {
    @Id
    @SequenceGenerator(name = "user_id_generator", sequenceName = "user_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_generator")
    private Long id;
    private String title;
    private String text;

    @ManyToOne
    @JoinColumn(name = "page_id", nullable = false)
    private Page page;
}
