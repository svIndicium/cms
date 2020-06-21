package hu.indicium.cms.page;

import hu.indicium.cms.page.dto.PageDTO;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PageServiceImpl implements PageService {

    private final PageRepository pageRepository;

    public PageServiceImpl(PageRepository pageRepository) {
        this.pageRepository = pageRepository;
    }

    //POST
    @Override
    public PageDTO createPage(PageDTO pageDTO) {
        Page page = PageMapper.map(pageDTO);
        page = pageRepository.save(page);
        return PageMapper.map(page);
    }
    //GET
    @Override
    public PageDTO getPageById(long pageId) {
        Page page = findById(pageId);
        return PageMapper.map(page);
    }

    @Override
    public List<PageDTO> getAllPages() {
        List<Page> pages = pageRepository.findAll();
        return pages.stream()
                .map(PageMapper::map)
                .collect(Collectors.toList());
    }

    //PUT
    @Override
    public PageDTO updatePage(PageDTO pageDTO) {
        Page findPage = findById(pageDTO.getId());
        Page page = PageMapper.map(pageDTO);

        page.setLastEdit(findPage.getLastEdit());

        page = pageRepository.save(page);
        return PageMapper.map(page);
    }

    //DELETE
    @Override
    public void deletePage(long pageId) {
        Page page = findById(pageId);
        pageRepository.delete(page);
    }

    private Page findById(long pageId){
        return pageRepository.findById(pageId).orElseThrow(EntityNotFoundException::new);
    }
}
