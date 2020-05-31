package hu.indicium.cms.menu.request;

import hu.indicium.cms.menu.dto.MenuItemDTO;
import hu.indicium.cms.page.Page;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class UpdateMenuRequest {
    private Long id;
    private String naam;
    private Page page;
}
