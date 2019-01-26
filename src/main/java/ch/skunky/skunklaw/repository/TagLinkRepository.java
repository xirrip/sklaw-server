package ch.skunky.skunklaw.repository;

import ch.skunky.skunklaw.model.TagLink;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface TagLinkRepository extends CrudRepository<TagLink, Long> {

    List<TagLink> findAllByTopicId1(Long topicId1);
    List<TagLink> findAllByTopicId2(Long topicId2);

    Optional<TagLink> findByTopicId1AndTopicId2(Long topicId1, Long topicId2);
}
