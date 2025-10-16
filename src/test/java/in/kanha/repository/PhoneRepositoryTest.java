// src/test/java/in/kanha/repository/PhoneRepositoryTest.java
package in.kanha.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import in.kanha.entity.Phone;

@DataJpaTest
class PhoneRepositoryTest {

    @Autowired
    private PhoneRepository repository;

    @Test
    @DisplayName("save and findByName returns saved phone")
    void saveAndFindByName() {
        Phone p = new Phone();
        p.setName("Galaxy");
        p.setPrice(500);
        p.setStorage("128GB");
        p.setCamera("12MP");
        Phone saved = repository.save(p);

        List<Phone> found = repository.findByName("Galaxy");
        assertThat(found).isNotEmpty();
        assertThat(found).anySatisfy(phone -> {
            assertThat(phone.getId()).isEqualTo(saved.getId());
            assertThat(phone.getName()).isEqualTo("Galaxy");
        });
    }

    @Test
    @DisplayName("findByPriceGreaterThanEqual returns expected results")
    void findByPriceGreaterThanEqual() {
        Phone p1 = new Phone(); p1.setName("A"); p1.setPrice(100);
        Phone p2 = new Phone(); p2.setName("B"); p2.setPrice(300);
        Phone p3 = new Phone(); p3.setName("C"); p3.setPrice(500);
        repository.saveAll(List.of(p1, p2, p3));

        List<Phone> result = repository.findByPriceGreaterThanEqual(300);
        assertThat(result).hasSize(2).extracting(Phone::getName).containsExactlyInAnyOrder("B", "C");
    }
}
