package raje.com.rajebackend.user.application.internal.commandservices;

import org.springframework.stereotype.Service;
import raje.com.rajebackend.user.domain.model.aggregates.ListEntity;
import raje.com.rajebackend.user.domain.model.commands.CreateListCommand;
import raje.com.rajebackend.user.domain.model.commands.UpdateListCommand;
import raje.com.rajebackend.user.domain.services.ListCommandService;
import raje.com.rajebackend.user.infrastructure.persistence.jpa.repositories.ListRepository;

import java.util.NoSuchElementException;

@Service
public class ListCommandServiceImpl implements ListCommandService {

    private final ListRepository listRepository;

    public ListCommandServiceImpl(ListRepository listRepository) {
        this.listRepository = listRepository;
    }

    @Override
    public ListEntity handle(CreateListCommand command) {
        var list = new ListEntity(command);
        return listRepository.save(list);
    }

    @Override
    public ListEntity handle(String id, UpdateListCommand command) {
        var list = listRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("List with ID " + id + " not found"));

        list.update(command);
        return listRepository.save(list);
    }

    @Override
    public void handleDelete(String id) {
        listRepository.deleteById(id);
    }
}
