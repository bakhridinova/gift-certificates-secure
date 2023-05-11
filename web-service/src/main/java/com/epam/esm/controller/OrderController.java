package com.epam.esm.controller;

import com.epam.esm.controller.response.CustomResponse;
import com.epam.esm.dto.OrderDto;
import com.epam.esm.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderService orderService;

    /**
     * GET endpoint to retrieve list of orders
     *
     * @param page page number requested (default is 0)
     * @param size number of items per page (default is 5)
     * @return List of orders
     */
    @GetMapping
    public Page<OrderDto> getAllByPage(@RequestParam(defaultValue = "0") int page,
                                       @RequestParam(defaultValue = "5") int size) {
       return orderService.findAllByPage(page, size);
    }

    /**
     * GET endpoint to retrieve specific order by its ID
     *
     * @param id of required order
     * @return specified order
     */
    @GetMapping("/{id}")
    public CustomResponse<OrderDto> getById(@PathVariable Long id) {
       return new CustomResponse<>(orderService.findById(id));
    }

    /**
     * PATCH endpoint to pay for specific order by its ID
     *
     * @param id of required order
     * @return specified order
     */
    @PatchMapping("/{id}/pay")
    public CustomResponse<OrderDto> payById(@PathVariable Long id) {
        return new CustomResponse<>(orderService.payById(id));
    }

    /**
     * PATCH endpoint to cancel specific order by its ID
     *
     * @param id of required order
     * @return specified order
     */
    @PatchMapping("/{id}/cancel")
    public CustomResponse<OrderDto> cancelById(@PathVariable Long id) {
        return new CustomResponse<>(orderService.cancelById(id));
    }

    /**
     * handles POST requests for creating new order
     *
     * @param order representing new order to be created
     * @return order that was created
     */
    @PostMapping
    public CustomResponse<OrderDto> create(@RequestBody OrderDto order) {
        return new CustomResponse<>(orderService.create(order));
    }

    /**
     * GET endpoint to search for retrieving orders associated with specific user
     *
     * @param userId ID of user for which to retrieve orders
     * @param page page number requested (default is 0)
     * @param size number of items per page (default is 5)
     * @return List of orders associated with certificate
     */
    @GetMapping("/search")
    public Page<OrderDto> getByUserId(@RequestParam(required = false) Long userId,
                                      @RequestParam(defaultValue = "0") int page,
                                      @RequestParam(defaultValue = "5") int size) {
        return orderService.findByUserIdAndPage(userId, page, size);
    }
}
