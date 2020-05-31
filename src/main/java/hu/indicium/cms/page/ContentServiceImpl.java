package hu.indicium.cms.page;

import hu.indicium.cms.page.dto.ContentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContentServiceImpl implements ContentService{
    private final ContentRepository contentRepository;

    public ContentServiceImpl(ContentRepository contentRepository) {
        this.contentRepository = contentRepository;
    }

    //POST
    @Override
    public ContentDTO createContent(ContentDTO contentDTO) {
        Content content = ContentMapper.map(contentDTO);
        content = contentRepository.save(content);
        return ContentMapper.map(content);
    }

    //GET
    @Override
    public ContentDTO getContentById(long contentId) {
        Content content = findById(contentId);
        return ContentMapper.map(content);
    }

    @Override
    public List<ContentDTO> getAllContents() {
        List<Content> contents = contentRepository.findAll();
        return contents.stream()
                .map(ContentMapper::map)
                .collect(Collectors.toList());
    }

    //PUT
    @Override
    public ContentDTO updateContent(ContentDTO contentDTO) {
        Content content = ContentMapper.map(contentDTO);
        content = contentRepository.save(content);
        return ContentMapper.map(content);
    }

    //DELETE
    @Override
    public void deleteContent(long contentId) {
        Content content = findById(contentId);
        contentRepository.delete(content);
    }

    private Content findById(long contentId){
        return contentRepository.findById(contentId).orElseThrow(EntityNotFoundException::new);
    }
}
