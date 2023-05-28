package com.epam.esm.controller;

import com.epam.esm.controller.response.CustomResponse;
import com.epam.esm.dto.TagDto;
import com.epam.esm.facade.TagFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/tags")
public class TagController {
    private final TagFacade tagFacade;

    /**
     * GET endpoint to retrieve page of tags
     *
     * @param page page number requested (default is 0)
     * @param size number of items per page (default is 5)
     * @return page of tags
     */
    @GetMapping
    public Page<TagDto> getAllByPage(@RequestParam(defaultValue = "0") int page,
                                     @RequestParam(defaultValue = "5") int size) {
       return tagFacade.findAllByPage(page, size);
    }

    /**
     * GET endpoint to retrieve specific tag by its ID
     *
     * @param id of required tag
     * @return specified tag
     */
    @GetMapping("/{id}")
    public CustomResponse<TagDto> getById(@PathVariable Long id) {
        return new CustomResponse<>(tagFacade.findById(id));
    }


    /**
     * GET endpoint to retrieve the most commonly used tag of a user
     * with the highest cost of all orders
     *
     * @return specified tag
     */
    @GetMapping("/special")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public CustomResponse<TagDto> getSpecial() {
        return new CustomResponse<>(tagFacade.findSpecial());
    }

    /**
     * POST endpoint for creating new tag
     *
     * @param tag representing new tag to be created
     * @return tag that was created
     */
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public CustomResponse<TagDto> create(@RequestBody TagDto tag) {
        return new CustomResponse<>(tagFacade.create(tag));
    }

    /**
     * DELETE endpoint for deleting specific tag
     *
     * @param id of tag to delete
     * @return message expressing that tag was successfully deleted
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public CustomResponse<?> deleteById(@PathVariable Long id) {
        tagFacade.deleteById(id);
        return new CustomResponse<>(HttpStatus.OK, "tag was successfully deleted");
    }
}
