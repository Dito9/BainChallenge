package bain.interview.DistanceService;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DistanceQueryRepository extends JpaRepository<DistanceQuery, Integer> {

}