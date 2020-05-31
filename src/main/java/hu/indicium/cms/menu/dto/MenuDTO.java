package hu.indicium.cms.menu.dto;

import hu.indicium.cms.page.Page;
import hu.indicium.cms.page.dto.PageDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class MenuDTO {
    private Long id;
    private String naam;
    private PageDTO page;
    private List<MenuItemDTO> MenuItems = new ArrayList<>();
}
