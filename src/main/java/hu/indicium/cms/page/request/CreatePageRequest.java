package hu.indicium.cms.page.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreatePageRequest {
    private String title;
    private String slug;
}
