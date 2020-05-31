package hu.indicium.cms.menu.dto;

import hu.indicium.cms.menu.Menu;
import hu.indicium.cms.page.Page;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MenuItemDTO {
    private Long id;
    private String naam;
    private Page page;
    private Menu menu;
}
