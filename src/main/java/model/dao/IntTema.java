package model.dao;

import java.util.List;
import model.beans.Tema;

public interface IntTema {
	List<Tema> findTopics();
	int insertTopic(Tema tema);
	Tema findTopicById(String id);
}
