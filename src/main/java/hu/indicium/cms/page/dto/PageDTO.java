package hu.indicium.cms.page.dto;

import hu.indicium.cms.menu.Menu;
import hu.indicium.cms.menu.MenuItem;
import hu.indicium.cms.page.Content;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class PageDTO {
    private Long id;
    private String title;
    private String slug;
    private String lastEdit;
    private String lastEditedBy;
    private List<ContentDTO> Contents = new ArrayList<>();
}
