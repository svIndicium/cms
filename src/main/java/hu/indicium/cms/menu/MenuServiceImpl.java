package hu.indicium.cms.menu;

import hu.indicium.cms.menu.dto.MenuDTO;
import hu.indicium.cms.menu.dto.MenuItemDTO;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MenuServiceImpl implements MenuService{
    private final MenuRepository menuRepository;
    private final MenuItemRepository menuItemRepository;

    public MenuServiceImpl(MenuRepository menuRepository, MenuItemRepository menuItemRepository) {
        this.menuRepository = menuRepository;
        this.menuItemRepository = menuItemRepository;
    }

    //POST
    @Override
    public MenuDTO createMenu(MenuDTO menuDTO) {
        Menu menu = MenuMapper.map(menuDTO);
        menu = menuRepository.save(menu);
        return MenuMapper.map(menu);

    }

    //GET
    @Override
    public MenuDTO getMenuById(Long menuId) {
        Menu menu = findById(menuId);
        if(menu == null){
            return null;
        }
        return MenuMapper.map(menu);
    }

    @Override
    public List<MenuDTO> getAllMenus() {
        List<Menu> Menus = menuRepository.findAll();
        return Menus.stream()
                .map(MenuMapper::map)
                .collect(Collectors.toList());
    }

    @Override
    public List<MenuDTO> getMenusByPageId(Long pageId) {
        List<Menu> menus = menuRepository.findByPageId(pageId);
        return menus.stream()
                .map(MenuMapper::map)
                .collect(Collectors.toList());
    }

    //PUT
    @Override
    public MenuDTO updateMenu(MenuDTO menuDTO) {
        Menu menu = MenuMapper.map(menuDTO);
        menu = menuRepository.save(menu);
        return MenuMapper.map(menu);
    }

    //DELETE
    @Override
    public void deleteMenu(Long menuId) {
        Menu menu = findById(menuId);
        menuRepository.delete(menu);
    }

    @Override
    public MenuItemDTO createMenuItem(MenuItemDTO menuItemDTO, Menu menu) {
        MenuItem menuItem = MenuMapper.map(menuItemDTO);
        menuItem.setMenu(menu);
        menuItem = menuItemRepository.save(menuItem);

        return MenuMapper.map(menuItem);
    }

    private Menu findById(Long menuId){
        return menuRepository.findById(menuId).orElseThrow(EntityNotFoundException::new);
    }
}
