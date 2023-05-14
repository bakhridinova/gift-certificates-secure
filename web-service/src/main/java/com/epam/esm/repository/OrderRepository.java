package com.epam.esm.repository;

import com.epam.esm.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OrderRepository extends JpaRepository<Order, Long> {
    @Modifying
    @Query(nativeQuery = true,
            value = "insert into orders (price, created_at, certificate_id, user_id)" +
                    "    select price, now(), certificate_id, :u_id from certificates" +
                    "        where certificate_id = :c_id;")
    Order create(@Param("c_id") Long certificateId, @Param("u_id") Long userId);

    @Query(nativeQuery = true,
            value = "update orders set status = :status where order_id = :id" +
                    "   returning order_id, price, status, created_at, certificate_id, user_id;")
    Order updateStatusById(Long id, String status);

    Page<Order> findByUserId(Long id, Pageable pageable);
}
