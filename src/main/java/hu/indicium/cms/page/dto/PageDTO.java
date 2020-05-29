package hu.indicium.cms.page.dto;

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
