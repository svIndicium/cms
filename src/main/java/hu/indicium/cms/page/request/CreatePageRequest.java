package hu.indicium.cms.page.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;

@Getter
@Setter
public class CreatePageRequest {
    private String title;
    private String slug;
}
