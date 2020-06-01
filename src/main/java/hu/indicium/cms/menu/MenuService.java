package hu.indicium.cms.menu;

import hu.indicium.cms.menu.dto.MenuDTO;
import hu.indicium.cms.menu.request.CreateMenuRequest;
import hu.indicium.cms.user.dto.UserDTO;
import hu.indicium.cms.user.request.CreateUserRequest;

import java.util.List;

public interface MenuService {
    //POST
    MenuDTO createMenu(MenuDTO menuDTO);

    //GET
    MenuDTO getMenuById(Long menuId);
    List<MenuDTO> getAllMenus();
    List<MenuDTO> getMenusByPageId(Long pageId);

    //PUT
    MenuDTO updateMenu(MenuDTO menuDTO);

    //DELETE
    void deleteMenu(Long menuId);
}
