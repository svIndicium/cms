package hu.indicium.cms.menu;

import hu.indicium.cms.menu.dto.MenuDTO;
import hu.indicium.cms.menu.dto.MenuItemDTO;
import hu.indicium.cms.menu.request.CreateMenuRequest;
import hu.indicium.cms.menu.request.UpdateMenuRequest;
import hu.indicium.cms.page.PageMapper;
import hu.indicium.cms.page.dto.PageDTO;
import org.modelmapper.ModelMapper;

public class MenuMapper {
    public static Menu map(MenuDTO menuDTO){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(menuDTO, Menu.class);
    }

    public static MenuDTO map(Menu menu){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(menu, MenuDTO.class);
    }

    public static MenuDTO map(CreateMenuRequest createMenuRequest){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(createMenuRequest, MenuDTO.class);
    }

    public static MenuDTO map(UpdateMenuRequest updateMenuRequest){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(updateMenuRequest, MenuDTO.class);
    }

    public static MenuItem map(MenuItemDTO menuItemDTO){
        MenuItem menuItem = new MenuItem();
        menuItem.setNaam(menuItemDTO.getNaam());
        menuItem.setPage(PageMapper.map(menuItemDTO.getPage()));
        return menuItem;
    }

    public static MenuItemDTO map(MenuItem menuItem){
        MenuItemDTO  menuItemDTO = new MenuItemDTO();
        menuItemDTO.setNaam(menuItem.getNaam());
        PageDTO pageDTO = PageMapper.map(menuItem.getPage());

        menuItemDTO.setPage(pageDTO);
        menuItemDTO.setId(menuItem.getId());
        return menuItemDTO;
    }
}
