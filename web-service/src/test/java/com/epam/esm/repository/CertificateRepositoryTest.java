package com.epam.esm.repository;

import com.epam.esm.entity.Certificate;
import com.epam.esm.entity.Tag;
import com.epam.esm.util.filter.SearchFilter;
import org.instancio.Instancio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

import static org.instancio.Select.field;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@AutoConfigureTestDatabase
class CertificateRepositoryTest {
    @Autowired
    private CertificateRepository certificateRepository;

    @Test
    void contextLoads() {
        assertNotNull(certificateRepository);
    }

    @Test
    @Order(7)
    public void findByFilter_ShouldReturnInitialList_InAscendingIdOrder() {
        SearchFilter searchFilter = Instancio.of(SearchFilter.class)
                .set(field(SearchFilter::name), "test")
                .set(field(SearchFilter::description), "test")
                .set(field(SearchFilter::sortProperty), "id")
                .set(field(SearchFilter::sortOrder), "asc")
                .create();

        List<Certificate> certificates =
                certificateRepository.findByFilter(searchFilter);

        IntStream.range(1, certificates.size())
                .mapToObj(i -> certificates.get(i - 1).getId()
                        .compareTo(certificates.get(i).getId()) < 0)
                .forEach(Assertions::assertTrue);
    }

    @Test
    @Order(8)
    public void findByFilter_ShouldReturnInitialList_InDescendingIdOrder() {
        SearchFilter searchFilter = Instancio.of(SearchFilter.class)
                .set(field(SearchFilter::name), "test")
                .set(field(SearchFilter::description), "test")
                .set(field(SearchFilter::sortProperty), "id")
                .set(field(SearchFilter::sortOrder), "desc")
                .create();

        List<Certificate> certificates =
                certificateRepository.findByFilter(searchFilter);

        IntStream.range(1, certificates.size())
                .mapToObj(i -> certificates.get(i - 1).getId()
                        .compareTo(certificates.get(i).getId()) > 0)
                .forEach(Assertions::assertTrue);
    }

    @Test
    @Order(9)
    public void findByFilter_ShouldReturnInitialList_InAscendingNameOrder() {
        SearchFilter searchFilter = Instancio.of(SearchFilter.class)
                .set(field(SearchFilter::name), "test")
                .set(field(SearchFilter::description), "test")
                .set(field(SearchFilter::sortProperty), "name")
                .set(field(SearchFilter::sortOrder), "asc")
                .create();

        List<Certificate> certificates =
                certificateRepository.findByFilter(searchFilter);

        IntStream.range(1, certificates.size())
                .mapToObj(i -> certificates.get(i - 1).getId()
                        .compareTo(certificates.get(i).getId()) < 0)
                .forEach(Assertions::assertTrue);
    }

    @Test
    @Order(10)
    public void findByFilter_ShouldReturnInitialList_InDescendingNameOrder() {
        SearchFilter searchFilter = Instancio.of(SearchFilter.class)
                .set(field(SearchFilter::name), "test")
                .set(field(SearchFilter::description), "test")
                .set(field(SearchFilter::sortProperty), "name")
                .set(field(SearchFilter::sortOrder), "desc")
                .create();

        List<Certificate> certificates =
                certificateRepository.findByFilter(searchFilter);

        IntStream.range(1, certificates.size())
                .mapToObj(i -> certificates.get(i - 1).getName()
                        .compareTo(certificates.get(i).getName()) > 0)
                .forEach(Assertions::assertTrue);
    }

    @Test
    @Order(11)
    public void findByFilter_ShouldReturnInitialList_InAscendingDescriptionOrder() {
        SearchFilter searchFilter = Instancio.of(SearchFilter.class)
                .set(field(SearchFilter::name), "test")
                .set(field(SearchFilter::description), "test")
                .set(field(SearchFilter::sortProperty), "description")
                .set(field(SearchFilter::sortOrder), "asc")
                .create();

        List<Certificate> certificates =
                certificateRepository.findByFilter(searchFilter);

        IntStream.range(1, certificates.size())
                .mapToObj(i -> certificates.get(i - 1).getDescription()
                        .compareTo(certificates.get(i).getDescription()) < 0)
                .forEach(Assertions::assertTrue);
    }

    @Test
    @Order(12)
    public void findByFilter_ShouldReturnInitialList_InDescendingDescriptionOrder() {
        SearchFilter searchFilter = Instancio.of(SearchFilter.class)
                .set(field(SearchFilter::name), "test")
                .set(field(SearchFilter::description), "test")
                .set(field(SearchFilter::sortProperty), "description")
                .set(field(SearchFilter::sortOrder), "desc")
                .create();

        List<Certificate> certificates =
                certificateRepository.findByFilter(searchFilter);

        IntStream.range(1, certificates.size())
                .mapToObj(i -> certificates.get(i - 1).getDescription()
                        .compareTo(certificates.get(i).getDescription()) > 0)
                .forEach(Assertions::assertTrue);
    }

    @Test
    @Order(13)
    public void findByFilter_ShouldReturnInitialList_InAscendingPriceOrder() {
        SearchFilter searchFilter = Instancio.of(SearchFilter.class)
                .set(field(SearchFilter::name), "test")
                .set(field(SearchFilter::description), "test")
                .set(field(SearchFilter::sortProperty), "price")
                .set(field(SearchFilter::sortOrder), "asc")
                .create();

        List<Certificate> certificates =
                certificateRepository.findByFilter(searchFilter);

        IntStream.range(1, certificates.size())
                .mapToObj(i -> certificates.get(i - 1).getPrice()
                        .compareTo(certificates.get(i).getPrice()) < 0)
                .forEach(Assertions::assertTrue);
    }

    @Test
    @Order(14)
    public void findByFilter_ShouldReturnInitialList_InDescendingPriceOrder() {
        SearchFilter searchFilter = Instancio.of(SearchFilter.class)
                .set(field(SearchFilter::name), "test")
                .set(field(SearchFilter::description), "test")
                .set(field(SearchFilter::sortProperty), "price")
                .set(field(SearchFilter::sortOrder), "desc")
                .create();

        List<Certificate> certificates =
                certificateRepository.findByFilter(searchFilter);

        IntStream.range(1, certificates.size())
                .mapToObj(i -> certificates.get(i - 1).getPrice()
                        .compareTo(certificates.get(i).getPrice()) > 0)
                .forEach(Assertions::assertTrue);
    }

    @Test
    @Order(15)
    public void findByFilter_ShouldReturnInitialList_InAscendingDurationOrder() {
        SearchFilter searchFilter = Instancio.of(SearchFilter.class)
                .set(field(SearchFilter::name), "test")
                .set(field(SearchFilter::description), "test")
                .set(field(SearchFilter::sortProperty), "duration")
                .set(field(SearchFilter::sortOrder), "asc")
                .create();

        List<Certificate> certificates =
                certificateRepository.findByFilter(searchFilter);

        IntStream.range(1, certificates.size())
                .mapToObj(i -> certificates.get(i - 1).getDuration()
                        .compareTo(certificates.get(i).getDuration()) < 0)
                .forEach(Assertions::assertTrue);
    }

    @Test
    @Order(16)
    public void findByFilter_ShouldReturnInitialList_InDescendingDurationOrder() {
        SearchFilter searchFilter = Instancio.of(SearchFilter.class)
                .set(field(SearchFilter::name), "test")
                .set(field(SearchFilter::description), "test")
                .set(field(SearchFilter::sortProperty), "duration")
                .set(field(SearchFilter::sortOrder), "desc")
                .create();

        List<Certificate> certificates =
                certificateRepository.findByFilter(searchFilter);

        IntStream.range(1, certificates.size())
                .mapToObj(i -> certificates.get(i - 1).getDuration()
                        .compareTo(certificates.get(i).getDuration()) > 0)
                .forEach(Assertions::assertTrue);
    }

    @Test
    @Order(17)
    public void findByFilter_ShouldReturnInitialList_InAscendingCreatedAtOrder() {
        SearchFilter searchFilter = Instancio.of(SearchFilter.class)
                .set(field(SearchFilter::name), "test")
                .set(field(SearchFilter::description), "test")
                .set(field(SearchFilter::sortProperty), "createdAt")
                .set(field(SearchFilter::sortOrder), "asc")
                .create();

        List<Certificate> certificates =
                certificateRepository.findByFilter(searchFilter);

        IntStream.range(1, certificates.size())
                .mapToObj(i -> !certificates.get(i - 1).getCreatedAt()
                        .isAfter(certificates.get(i).getCreatedAt()))
                .forEach(Assertions::assertTrue);
    }

    @Test
    @Order(18)
    public void findByFilter_ShouldReturnInitialList_InDescendingCreatedAtOrder() {
        SearchFilter searchFilter = Instancio.of(SearchFilter.class)
                .set(field(SearchFilter::name), "test")
                .set(field(SearchFilter::description), "test")
                .set(field(SearchFilter::sortProperty), "createdAt")
                .set(field(SearchFilter::sortOrder), "desc")
                .create();

        List<Certificate> certificates =
                certificateRepository.findByFilter(searchFilter);

        IntStream.range(1, certificates.size())
                .mapToObj(i -> !certificates.get(i - 1).getCreatedAt()
                        .isBefore(certificates.get(i).getCreatedAt()))
                .forEach(Assertions::assertTrue);
    }

    @Test
    @Order(19)
    public void findByFilter_ShouldReturnInitialList_InAscendingLastUpdatedAtOrder() {
        SearchFilter searchFilter = Instancio.of(SearchFilter.class)
                .set(field(SearchFilter::name), "test")
                .set(field(SearchFilter::description), "test")
                .set(field(SearchFilter::sortProperty), "lastUpdatedAt")
                .set(field(SearchFilter::sortOrder), "asc")
                .create();

        List<Certificate> certificates =
                certificateRepository.findByFilter(searchFilter);

        IntStream.range(1, certificates.size())
                .mapToObj(i -> !certificates.get(i - 1).getLastUpdatedAt()
                        .isAfter(certificates.get(i).getLastUpdatedAt()))
                .forEach(Assertions::assertTrue);
    }

    @Test
    @Order(20)
    public void findByFilter_ShouldReturnInitialList_InDescendingLastUpdatedAtOrder() {
        SearchFilter searchFilter = Instancio.of(SearchFilter.class)
                .set(field(SearchFilter::name), "test")
                .set(field(SearchFilter::description), "test")
                .set(field(SearchFilter::sortProperty), "lastUpdatedAt")
                .set(field(SearchFilter::sortOrder), "desc")
                .create();

        List<Certificate> certificates =
                certificateRepository.findByFilter(searchFilter);

        IntStream.range(1, certificates.size())
                .mapToObj(i -> !certificates.get(i - 1).getLastUpdatedAt()
                        .isBefore(certificates.get(i).getLastUpdatedAt()))
                .forEach(Assertions::assertTrue);
    }

    @Test
    @Order(21)
    public void findByFilter_ShouldReturnEmptyList_IfNamesDoNotMatch() {
        SearchFilter searchFilter = Instancio.of(SearchFilter.class)
                .set(field(SearchFilter::name), "test name")
                .set(field(SearchFilter::description), "test")
                .set(field(SearchFilter::sortProperty), "id")
                .set(field(SearchFilter::sortOrder), "asc")
                .create();

        assertTrue(certificateRepository
                .findByFilter(searchFilter).isEmpty());
    }

    @Test
    @Order(22)
    public void findByFilter_ShouldReturnEmptyList_IfDescriptionsDoNotMatch() {
        SearchFilter searchFilter = Instancio.of(SearchFilter.class)
                .set(field(SearchFilter::name), "test")
                .set(field(SearchFilter::description), "test description")
                .set(field(SearchFilter::sortProperty), "id")
                .set(field(SearchFilter::sortOrder), "asc")
                .create();

        assertTrue(certificateRepository
                .findByFilter(searchFilter).isEmpty());
    }

    @Test
    @Order(23)
    public void findByFilter_ShouldReturnEmptyList_IfTagsDoNotMatch() {
        Tag tag = Tag.builder()
                .name("test name").build();
        SearchFilter searchFilter = Instancio.of(SearchFilter.class)
                .set(field(SearchFilter::name), "test")
                .set(field(SearchFilter::description), "test")
                .set(field(SearchFilter::sortProperty), "id")
                .set(field(SearchFilter::sortOrder), "asc")
                .set(field(SearchFilter::tags), Set.of(tag))
                .create();

        assertTrue(certificateRepository
                .findByFilter(searchFilter).isEmpty());
    }
}