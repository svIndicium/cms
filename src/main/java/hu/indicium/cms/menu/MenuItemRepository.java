package hu.indicium.cms.menu;

import hu.indicium.cms.page.Content;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {
}
