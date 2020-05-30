package hu.indicium.cms.menu;

import hu.indicium.cms.page.Content;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<Menu, Long> {
}
