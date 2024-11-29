package umc.spring.repository.RegionRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import umc.spring.domain.Region;
@Repository
public interface RegionRepository extends JpaRepository<Region, Long> {
}
