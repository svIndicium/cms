package hu.indicium.cms.page;

import hu.indicium.cms.page.dto.PageDTO;
import hu.indicium.cms.page.request.CreatePageRequest;
import hu.indicium.cms.page.request.UpdatePageRequest;
import org.modelmapper.ModelMapper;

public class PageMapper {
    public static PageDTO map(Page page){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(page, PageDTO.class);
    }

    public static Page map(PageDTO pageDTO){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(pageDTO, Page.class);
    }

    public static PageDTO map(CreatePageRequest createPageRequest){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(createPageRequest, PageDTO.class);
    }

    public static PageDTO map(UpdatePageRequest updatePageRequest){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(updatePageRequest, PageDTO.class);
    }
}
