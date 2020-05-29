package hu.indicium.cms.page;

import hu.indicium.cms.page.dto.PageDTO;
import hu.indicium.cms.page.request.CreatePageRequest;
import hu.indicium.cms.page.request.UpdatePageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pages")
public class PageController {

    private final PageService pageService;

    public PageController(PageService pageService) {
        this.pageService = pageService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PageDTO createPage(@RequestBody CreatePageRequest page){
        PageDTO pageDTO = PageMapper.map(page);
        return pageService.createPage(pageDTO);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<PageDTO> getPages(){
        return pageService.getAllPages();
    }

    @GetMapping("/{pageId}")
    @ResponseStatus(HttpStatus.OK)
    public PageDTO getPage(@PathVariable long pageId){
        return pageService.getPageById(pageId);
    }

    @PutMapping("/{pageId}")
    @ResponseStatus(HttpStatus.OK)
    public PageDTO updatePage(@RequestBody UpdatePageRequest page, @PathVariable Long pageId){
        PageDTO pageDTO = PageMapper.map(page);
        pageDTO.setId(pageId);
        return pageService.updatePage(pageDTO);
    }

    @DeleteMapping("/{pageId}")
    @ResponseStatus(HttpStatus.OK)
    public void deletePage(@PathVariable Long pageId){
        pageService.deletePage(pageId);
    }
}
