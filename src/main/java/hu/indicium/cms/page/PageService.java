package hu.indicium.cms.page;

import hu.indicium.cms.page.dto.PageDTO;

import java.util.List;

public interface PageService {
    //POST
    PageDTO createPage(PageDTO pageDTO);

    //GET
    PageDTO getPageById(long pageId);
    List<PageDTO> getAllPages();

    //PUT
    PageDTO updatePage(PageDTO pageDTO);

    //DELETE
    void deletePage(long pageId);

}
