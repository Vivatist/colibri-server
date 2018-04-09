package ru.colibri.colibriserver.security;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import ru.colibri.colibriserver.security.model.Role;


/**
 * A converter class used in views to map id's to actual userProfile objects.
 */
@Component
public class RoleToUserProfileConverter implements Converter<Object, Role>{

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    RoleRepository roleRepository;

    /**
     * Gets UserProfile by Id
     * @see org.springframework.core.convert.converter.Converter#convert(java.lang.Object)
     */
    public Role convert(Object element) {
        Integer id = Integer.parseInt((String)element);
        Role role= roleRepository.findById(id);
        log.debug("Convert id " + id.toString() + " to role " + role.toString());
        return role;
    }

}