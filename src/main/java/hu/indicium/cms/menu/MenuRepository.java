package hu.indicium.cms.menu;

import hu.indicium.cms.page.Content;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MenuRepository extends JpaRepository<Menu, Long> {
    List<Menu> findByPageId(Long pageId);
}
