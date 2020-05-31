package hu.indicium.cms.menu;

import hu.indicium.cms.menu.dto.MenuDTO;
import hu.indicium.cms.menu.request.CreateMenuRequest;
import hu.indicium.cms.menu.request.UpdateMenuRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/menus")
public class MenuController {

    private final MenuService menuService;

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    //POST
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
    @PutMapping("/{menuId}")
    @ResponseStatus(HttpStatus.OK)
    public MenuDTO updateMenu(@RequestBody UpdateMenuRequest updateMenuRequest, @PathVariable Long menuId){
        MenuDTO menuDTO = MenuMapper.map(updateMenuRequest);
        menuDTO.setId(menuId);
        return menuService.updateMenu(menuDTO);
    }

    //DELETE
    @DeleteMapping("/{menuId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteMenu(@PathVariable Long menuId){
        menuService.deleteMenu(menuId);
    }
}
