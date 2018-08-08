package com.zipportal.zipportal.Repository;

import com.zipportal.zipportal.Model.Assignment;
import com.zipportal.zipportal.Model.PullRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigInteger;
import java.util.List;

public interface PullRequestRepository extends JpaRepository<PullRequest, Long> {
    List<PullRequest> findAllByUser_Id(@Param("userId") Long userId);

    @Query(value = "SELECT DISTINCT p.assignment_id FROM pullrequest p WHERE p.user_id = :userid", nativeQuery = true)
    List<BigInteger> findDistinctByUser(@Param("userid") Long userid);
}
