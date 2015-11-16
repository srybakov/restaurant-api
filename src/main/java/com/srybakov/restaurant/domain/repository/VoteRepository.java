package com.srybakov.restaurant.domain.repository;

import com.srybakov.restaurant.domain.model.Vote;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author <a href="mailto:sarybako@gmail.com">Sergey Rybakov</a>
 */
@Repository
public interface VoteRepository extends CrudRepository<Vote, Long> {

    @Query("select new map(v.restaurant, count(*)) from Vote v group by v.restaurant order by count(*) desc")
    List<Map<String, Object>> findMostVoted(Pageable pageable);

    @Query("select new map(v.restaurant, count(*)) from Vote v "
            + " where v.voteDate BETWEEN CURRENT_DATE and CURRENT_DATE + 1 "
            + " group by v.restaurant order by count(*) desc")
    List<Map<String, Object>> findMostVotedForToday(Pageable pageable);

    @Query("select v from Vote v where v.user.name = :userName and "
            + " v.voteDate BETWEEN CURRENT_DATE and CURRENT_DATE + 1 ")
    Vote findUserVoteForToday(@Param(value = "userName") String userName);

}
