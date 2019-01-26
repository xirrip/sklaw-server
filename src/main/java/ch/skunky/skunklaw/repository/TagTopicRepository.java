package ch.skunky.skunklaw.repository;

import ch.skunky.skunklaw.model.TagTopic;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface TagTopicRepository extends CrudRepository<TagTopic, Long> {

    List<TagTopic> findTagTopicsByNameContainingAndUniverse(String name, String universe);
    Optional<TagTopic> findByNameAndUniverse(String name, String universe);
}
