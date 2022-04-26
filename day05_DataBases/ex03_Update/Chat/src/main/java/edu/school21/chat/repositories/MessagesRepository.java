package edu.school21.chat.repositories;

import edu.school21.chat.app.NotSavedSubEntityException;
import edu.school21.chat.models.Message;
import java.util.Optional;

public interface MessagesRepository {
    Optional<Message> findById(Long id);
    void save(Message message) throws NotSavedSubEntityException;
    void update(Message message) throws NotSavedSubEntityException;
}
