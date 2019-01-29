package ch.skunky.skunklaw.repository;

import ch.skunky.skunklaw.model.tags.TagItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface TagItemRepository extends CrudRepository<TagItem, Long> {

}
