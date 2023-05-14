package com.epam.esm.repository;

import com.epam.esm.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface TagRepository extends JpaRepository<Tag, Long> {
    Optional<Tag> findByName(String name);

    @Query(nativeQuery = true,
            value = "select t.tag_id, t.name from tags t" +
                    "   inner join certificate_tag ct on t.tag_id = ct.tag_id" +
                    "   inner join certificates c on c.certificate_id = ct.certificate_id" +
                    "       where ct.certificate_id in (select o.certificate_id from orders o" +
                    "           where o.user_id in (select o.user_id from orders o" +
                    "               group by o.user_id order by sum(o.price) desc limit 1))" +
                    "       group by t.tag_id order by count(t.tag_id) desc limit 1;")
    Tag findSpecial();
}
