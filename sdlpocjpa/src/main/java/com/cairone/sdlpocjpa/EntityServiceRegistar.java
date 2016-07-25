package com.cairone.sdlpocjpa;

import java.util.Arrays;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cairone.sdlpocjpa.edm.LocalidadEdm;
import com.cairone.sdlpocjpa.edm.PaisEdm;
import com.cairone.sdlpocjpa.edm.ProvinciaEdm;
import com.sdl.odata.api.ODataException;
import com.sdl.odata.api.edm.registry.ODataEdmRegistry;

@Component
public class EntityServiceRegistar {

	@Autowired private ODataEdmRegistry oDataEdmRegistry = null;

	@PostConstruct
    public void registerEntities() throws ODataException {
		oDataEdmRegistry.registerClasses(Arrays.asList(
        		PaisEdm.class,
        		ProvinciaEdm.class,
        		LocalidadEdm.class));
    }
}
