package hu.indicium.cms.menu;

import hu.indicium.cms.menu.dto.MenuDTO;
import hu.indicium.cms.menu.dto.MenuItemDTO;
import hu.indicium.cms.menu.request.CreateMenuItemRequest;
import hu.indicium.cms.menu.request.CreateMenuRequest;
import hu.indicium.cms.menu.request.UpdateMenuRequest;
import hu.indicium.cms.page.Page;
import hu.indicium.cms.page.PageService;
import hu.indicium.cms.page.dto.PageDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/menus")
public class MenuController {

    private final MenuService menuService;
    private final PageService pageService;

    public MenuController(MenuService menuService, PageService pageService) {
        this.menuService = menuService;
        this.pageService = pageService;
    }

    //POST
    @PreAuthorize("hasAnyAuthority('Admin', 'Auteur')")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MenuDTO createMenu(@RequestBody CreateMenuRequest createMenuRequest){
        MenuDTO menuDTO = MenuMapper.map(createMenuRequest);
        return  menuService.createMenu(menuDTO);
    }

    //GET
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<MenuDTO> getAllMenus(){
        return menuService.getAllMenus();
    }

    @GetMapping("/{menuId}")
    @ResponseStatus(HttpStatus.OK)
    public MenuDTO getMenu(@PathVariable Long menuId){
        return menuService.getMenuById(menuId);
    }

    //PUT
    @PreAuthorize("hasAnyAuthority('Admin', 'Auteur')")
    @PutMapping("/{menuId}")
    @ResponseStatus(HttpStatus.OK)
    public MenuDTO updateMenu(@RequestBody UpdateMenuRequest updateMenuRequest, @PathVariable Long menuId){
        MenuDTO menuDTO = MenuMapper.map(updateMenuRequest);
        menuDTO.setId(menuId);
        return menuService.updateMenu(menuDTO);
    }

    //DELETE
    @PreAuthorize("hasAnyAuthority('Admin', 'Auteur')")
    @DeleteMapping("/{menuId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteMenu(@PathVariable Long menuId){
        menuService.deleteMenu(menuId);
    }

    //POST MENU ITEM
    @PreAuthorize("hasAnyAuthority('Admin', 'Auteur')")
    @PostMapping("/{menuId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<MenuItemDTO> createMenuItem(@PathVariable Long menuId, @RequestBody CreateMenuItemRequest createMenuItemRequest){
        //GET MENU
        MenuDTO menuDTO = menuService.getMenuById(menuId);
        //GET PAGE
        PageDTO pageDTO = pageService.getPageById(createMenuItemRequest.getPageId());
        //Check if they exist
        if(menuDTO == null || pageDTO == null){
            return new ResponseEntity<MenuItemDTO>(HttpStatus.NOT_FOUND);
        }
        //ASSEMBLE MENUITEM DTO
        MenuItemDTO menuItemDTO = new MenuItemDTO();

        PageDTO findPage =  pageService.getPageById(createMenuItemRequest.getPageId());

        Menu menu = new Menu();
        menu.setId(menuId);

        menuItemDTO.setPage(findPage);
        menuItemDTO.setNaam(createMenuItemRequest.getNaam());

        menuItemDTO = menuService.createMenuItem(menuItemDTO, menu);

        return new ResponseEntity<MenuItemDTO>(menuItemDTO, HttpStatus.OK);
    }
}
