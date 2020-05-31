package hu.indicium.cms.page.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateContentRequest {
    private String title;
    private String text;
    private Long pageId;
}
