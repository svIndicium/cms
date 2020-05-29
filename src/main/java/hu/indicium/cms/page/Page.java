package hu.indicium.cms.page;

import hu.indicium.cms.menu.Menu;
import hu.indicium.cms.menu.MenuItem;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    @OneToMany(mappedBy = "page")
    private List<Content> Contents = new ArrayList<>();

    @OneToMany(mappedBy = "page")
    private List<Menu> Menus = new ArrayList<>();

    @OneToMany(mappedBy = "page")
    private List<MenuItem> MenuItems = new ArrayList<>();
}
