package hu.indicium.cms.page;

import hu.indicium.cms.page.dto.ContentDTO;
import hu.indicium.cms.page.dto.PageDTO;
import hu.indicium.cms.page.request.CreateContentRequest;
import hu.indicium.cms.page.request.UpdateContentRequest;
import org.modelmapper.ModelMapper;

public class ContentMapper {
    public static Content map(ContentDTO contentDTO){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(contentDTO, Content.class);
    }

    public static ContentDTO map(Content content){
        ModelMapper modelMapper = new ModelMapper();
        ContentDTO contentDTO = modelMapper.map(content, ContentDTO.class);
        PageDTO pageDTO = PageMapper.map(content.getPage());
        pageDTO.setContents(null);
        contentDTO.setPageDTO(pageDTO);
        return contentDTO;
    }

    public static ContentDTO map(CreateContentRequest createContentRequest){
        ContentDTO contentDTO = new ContentDTO();
        contentDTO.setText(createContentRequest.getText());
        contentDTO.setTitle(createContentRequest.getTitle());
        PageDTO pageDTO = new PageDTO();
        pageDTO.setId(createContentRequest.getPageId());
        contentDTO.setPageDTO(pageDTO);
        return contentDTO;
    }

    public static ContentDTO map(UpdateContentRequest updateContentRequest){
        ModelMapper modelMapper = new ModelMapper();
        PageDTO pageDTO = new PageDTO();
        pageDTO.setId(updateContentRequest.getPageId());

        ContentDTO contentDTO = new ContentDTO();
        contentDTO.setTitle(updateContentRequest.getTitle());
        contentDTO.setText(updateContentRequest.getText());
        contentDTO.setPageDTO(pageDTO);
        
        return contentDTO;
    }


}
