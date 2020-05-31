package hu.indicium.cms.menu.request;

import hu.indicium.cms.page.Page;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateMenuRequest {
    private Long id;
    private String naam;
    private Page page;
}
