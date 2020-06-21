package hu.indicium.cms.page;

import hu.indicium.cms.page.dto.ContentDTO;
import hu.indicium.cms.page.dto.PageDTO;
import hu.indicium.cms.page.request.CreateContentRequest;
import hu.indicium.cms.page.request.UpdateContentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contents")
public class ContentController {
    private final ContentService contentService;

    public ContentController(ContentService contentService) {
        this.contentService = contentService;
    }

    //POST
    @PreAuthorize("hasAnyAuthority('Admin', 'Auteur')")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ContentDTO createContent(@RequestBody CreateContentRequest createContentRequest) {
        //Map request to DTO
        ContentDTO contentDTO = ContentMapper.map(createContentRequest);

        //Create and return content
        return contentService.createContent(contentDTO);
    }

    //GET
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ContentDTO> getAllContent(){
        return contentService.getAllContents();
    }

    @GetMapping("/{contentId}")
    @ResponseStatus(HttpStatus.OK)
    public ContentDTO getContentById(@PathVariable Long contentId){
        return contentService.getContentById(contentId);
    }

    //PUT
    @PreAuthorize("hasAnyAuthority('Admin', 'Auteur')")
    @PutMapping("/{contentId}")
    @ResponseStatus(HttpStatus.OK)
    public ContentDTO updateContent(@PathVariable Long contentId, @RequestBody UpdateContentRequest updateContentRequest){
        ContentDTO contentDTO = ContentMapper.map(updateContentRequest);
        contentDTO.setId(contentId);
        return contentService.updateContent(contentDTO);
    }

    //DELETE
    @PreAuthorize("hasAnyAuthority('Admin', 'Auteur')")
    @DeleteMapping("/{contentId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteContent(@PathVariable Long contentId){
        contentService.deleteContent(contentId);
    }
}
