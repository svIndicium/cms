package hu.indicium.cms.menu.dto;

import hu.indicium.cms.menu.Menu;
import hu.indicium.cms.page.Page;
import hu.indicium.cms.page.dto.PageDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MenuItemDTO {
    private Long id;
    private String naam;
    private PageDTO page;
}
