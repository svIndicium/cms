package hu.indicium.cms.page.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContentDTO {
    private Long id;
    private String title;
    private String text;
    private PageDTO pageDTO;
}
