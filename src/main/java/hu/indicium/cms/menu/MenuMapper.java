package hu.indicium.cms.menu;

import hu.indicium.cms.menu.dto.MenuDTO;
import hu.indicium.cms.menu.request.CreateMenuRequest;
import hu.indicium.cms.menu.request.UpdateMenuRequest;
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
}
