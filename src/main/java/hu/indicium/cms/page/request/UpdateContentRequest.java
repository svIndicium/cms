package hu.indicium.cms.page.request;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UpdateContentRequest {
    private String title;
    private String text;
    private Long pageId;
}
