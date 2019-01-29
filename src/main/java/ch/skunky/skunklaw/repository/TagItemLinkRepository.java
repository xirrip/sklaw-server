package ch.skunky.skunklaw.repository;

import ch.skunky.skunklaw.model.tags.TagItemLink;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface TagItemLinkRepository extends CrudRepository<TagItemLink, Long> {
    List<TagItemLink> findAllByTopicId(Long topicId);
    List<TagItemLink> findAllByItemId(Long itemId);
}
