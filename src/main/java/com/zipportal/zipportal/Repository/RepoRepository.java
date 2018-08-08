package com.zipportal.zipportal.Repository;

import com.zipportal.zipportal.Model.Repo;
import com.zipportal.zipportal.Model.User;
import org.hibernate.validator.constraints.ParameterScriptAssert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RepoRepository extends JpaRepository<Repo, Long> {

//        @Query("SELECT r FROM Repo r WHERE user_id = :userId")
//        Iterable<Repo> findAllByUser(@Param("userId") Long userId);

    @Query("SELECT r FROM Repo r WHERE r.html_url = :html_url")
    Repo findRepoByHtml_url(@Param("html_url") String html_url);
}
