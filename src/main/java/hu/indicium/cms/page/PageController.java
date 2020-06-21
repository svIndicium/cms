package hu.indicium.cms.page;

import hu.indicium.cms.menu.MenuService;
import hu.indicium.cms.menu.dto.MenuDTO;
import hu.indicium.cms.page.dto.ContentDTO;
import hu.indicium.cms.page.dto.PageDTO;
import hu.indicium.cms.page.request.CreatePageRequest;
import hu.indicium.cms.page.request.UpdatePageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/pages")
public class PageController {

    private final PageService pageService;
    private final ContentService contentService;
    private final MenuService menuService;

    public PageController(PageService pageService, ContentService contentService, MenuService menuService) {
        this.pageService = pageService;
        this.contentService = contentService;
        this.menuService = menuService;
    }

    @PreAuthorize("hasAnyAuthority('Admin', 'Auteur')")
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

    @PreAuthorize("hasAnyAuthority('Admin', 'Auteur')")
    @PutMapping("/{pageId}")
    @ResponseStatus(HttpStatus.OK)
    public PageDTO updatePage(@RequestBody UpdatePageRequest page, @PathVariable Long pageId, Principal principal){
        PageDTO pageDTO = PageMapper.map(page);
        pageDTO.setLastEditedBy(principal.getName());
        pageDTO.setId(pageId);
        return pageService.updatePage(pageDTO);
    }

    @PreAuthorize("hasAnyAuthority('Admin', 'Auteur')")
    @DeleteMapping("/{pageId}")
    @ResponseStatus(HttpStatus.OK)
    public void deletePage(@PathVariable Long pageId){

        PageDTO pageDTO = pageService.getPageById(pageId);

        //Content opzoeken en verwijderen
        List<ContentDTO> contents = pageDTO.getContents();

        for (ContentDTO contentDTO: contents) {
            contentService.deleteContent(contentDTO.getId());
        }

        //kijken of er menus zijn zoja zet page op null
        List<MenuDTO> menuDTOS = menuService.getMenusByPageId(pageId);

        for(MenuDTO menuDTO: menuDTOS){
            menuDTO.setPage(null);
            menuService.updateMenu(menuDTO);
        }

        pageService.deletePage(pageId);
    }
}
