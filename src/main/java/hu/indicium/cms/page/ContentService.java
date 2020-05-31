package hu.indicium.cms.page;

import hu.indicium.cms.page.dto.ContentDTO;
import hu.indicium.cms.page.dto.PageDTO;

import java.util.List;

public interface ContentService {
    //POST
    ContentDTO createContent(ContentDTO contentDTO);

    //GET
    ContentDTO getContentById(long contentId);
    List<ContentDTO> getAllContents();

    //PUT
    ContentDTO updateContent(ContentDTO contentDTO);

    //DELETE
    void deleteContent(long contentId);
}
