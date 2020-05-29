package hu.indicium.cms.page;

import com.fasterxml.jackson.databind.ObjectMapper;
import hu.indicium.cms.page.dto.PageDTO;
import hu.indicium.cms.page.request.CreatePageRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@WebMvcTest(PageController.class)
@Tag("Controller")
public class PageControllerTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mvc;

    private PageDTO pageDTO;



    @BeforeEach
    void setup(){
        pageDTO = new PageDTO();
        pageDTO.setId(1L);
        pageDTO.setTitle("Contact");
        pageDTO.setSlug("contact");
        pageDTO.setLastEdit("22");
        pageDTO.setLastEditedBy("Rhett van Korlaar");
    }

    @Test
    @DisplayName("Create page")
    void shouldCreatePage() throws Exception{
        ArgumentCaptor<PageDTO> pageDTOArgumentCaptor = ArgumentCaptor.forClass(PageDTO.class);

        CreatePageRequest createPageRequest = new CreatePageRequest();
        createPageRequest.setTitle(pageDTO.getTitle());
        createPageRequest.setSlug(pageDTO.getSlug());

        mvc.perform(post("/api/pages")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writer().writeValueAsString(createPageRequest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.data", notNullValue()))
                .andExpect(jsonPath("$.data.firstName", is(pageDTO.getTitle())))
                .andExpect(jsonPath("$.data.middleName", is(pageDTO.getSlug())))
                .andExpect(jsonPath("$.error", nullValue()))
                .andExpect(jsonPath("$.timestamp", notNullValue()))
                .andExpect(jsonPath("$.status", is(HttpStatus.CREATED.value())));

    }
}
