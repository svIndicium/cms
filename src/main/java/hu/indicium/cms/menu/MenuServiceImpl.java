package hu.indicium.cms.menu;

import hu.indicium.cms.menu.dto.MenuDTO;
import hu.indicium.cms.menu.request.CreateMenuRequest;
import hu.indicium.cms.page.Page;
import hu.indicium.cms.page.PageMapper;
import hu.indicium.cms.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MenuServiceImpl implements MenuService{
    private final MenuRepository menuRepository;

    public MenuServiceImpl(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
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
        return MenuMapper.map(menu);
    }

    @Override
    public List<MenuDTO> getAllMenus() {
        List<Menu> Menus = menuRepository.findAll();
        return Menus.stream()
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

    private Menu findById(Long menuId){
        return menuRepository.findById(menuId).orElseThrow(EntityNotFoundException::new);
    }
}
