package umc.spring.repository.MemberRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import umc.spring.domain.FoodCategory;

@Repository
public interface FoodCategoryRepository extends JpaRepository<FoodCategory, Long> {
}