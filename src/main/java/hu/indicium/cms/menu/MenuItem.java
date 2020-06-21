package hu.indicium.cms.menu;

import hu.indicium.cms.page.Page;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "Menu_Items")
@NoArgsConstructor
@Getter
@Setter
public class MenuItem {
    @Id
    @SequenceGenerator(name = "menu_item_id_generator", sequenceName = "menu_item_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "menu_item_id_generator")
    private Long id;
    private String naam;
    @ManyToOne
    @JoinColumn(name = "page_id", nullable = false)
    private Page page;
    @ManyToOne
    @JoinColumn(name = "menu_id", nullable = false)
    private Menu menu;
}
