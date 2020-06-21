package hu.indicium.cms.menu.request;

import hu.indicium.cms.menu.Menu;
import hu.indicium.cms.page.Page;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateMenuItemRequest {
    private String naam;
    private Long pageId;
}
