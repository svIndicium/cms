package hu.indicium.cms.menu;

import hu.indicium.cms.page.Page;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "Menus")
@NoArgsConstructor
@Getter
@Setter
public class Menu {
    @Id
    @SequenceGenerator(name = "user_id_generator", sequenceName = "user_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_generator")
    private Long id;
    private String naam;
    @ManyToOne
    @JoinColumn(name = "page_id", nullable = false)
    private Page page;
}
