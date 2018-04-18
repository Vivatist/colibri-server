package ru.colibri.colibriserver.testclasses.colibri;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.colibri.colibriserver.testclasses.colibri.model.Arduino;

import java.util.Set;


@Repository
public interface ArduinoRepository extends CrudRepository<Arduino, Long> {

    Set<Arduino> findAllByEnabled(boolean enabled);

    Arduino findById(int id);
}
