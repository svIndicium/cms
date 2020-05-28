package hu.indicium.cms.page;

import hu.indicium.cms.page.dto.PageDTO;
import hu.indicium.cms.page.request.CreatePageRequest;
import hu.indicium.cms.page.request.UpdatePageRequest;
import org.modelmapper.ModelMapper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PageMapper {
    public static PageDTO map(Page page) {
        ModelMapper modelMapper = new ModelMapper();
        PageDTO pageDTO = modelMapper.map(page, PageDTO.class);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd | HH:mm:ss");

        String dateString = format.format(page.getLastEdit());
        pageDTO.setLastEdit(dateString);
        return pageDTO;
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
