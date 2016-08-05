package com.cairone.sdlpocjpa.services;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;

import scala.Option;

import com.cairone.sdlpocjpa.dtos.LocalidadFrmDto;
import com.cairone.sdlpocjpa.dtos.validators.LocalidadFrmDtoValidator;
import com.cairone.sdlpocjpa.edm.LocalidadEdm;
import com.cairone.sdlpocjpa.entities.LocalidadEntity;
import com.cairone.sdlpocjpa.entities.LocalidadPKEntity;
import com.cairone.sdlpocjpa.entities.PaisEntity;
import com.cairone.sdlpocjpa.entities.ProvinciaEntity;
import com.cairone.sdlpocjpa.entities.ProvinciaPKEntity;
import com.cairone.sdlpocjpa.repositories.LocalidadRepository;
import com.cairone.sdlpocjpa.repositories.PaisRepository;
import com.cairone.sdlpocjpa.repositories.ProvinciaRepository;
import com.cairone.sdlpocjpa.strategyBuilders.LocalidadesStrategyBuilder;
import com.mysema.query.types.expr.BooleanExpression;
import com.sdl.odata.api.ODataException;
import com.sdl.odata.api.ODataNotImplementedException;
import com.sdl.odata.api.ODataSystemException;
import com.sdl.odata.api.edm.model.EntityDataModel;
import com.sdl.odata.api.edm.util.EdmUtil;
import com.sdl.odata.api.parser.ODataUri;
import com.sdl.odata.api.parser.ODataUriUtil;
import com.sdl.odata.api.parser.TargetType;
import com.sdl.odata.api.processor.datasource.DataSource;
import com.sdl.odata.api.processor.datasource.DataSourceProvider;
import com.sdl.odata.api.processor.datasource.ODataDataSourceException;
import com.sdl.odata.api.processor.datasource.TransactionalDataSource;
import com.sdl.odata.api.processor.link.ODataLink;
import com.sdl.odata.api.processor.query.QueryOperation;
import com.sdl.odata.api.processor.query.QueryResult;
import com.sdl.odata.api.processor.query.strategy.QueryOperationStrategy;
import com.sdl.odata.api.service.ODataRequestContext;

@Service
public class LocalidadesDataSource implements DataSource, DataSourceProvider {
	
	@Autowired private PaisRepository paisRepository = null;
	@Autowired private ProvinciaRepository provinciaRepository = null;
	@Autowired private LocalidadRepository localidadRepository = null;
	
	@Autowired private LocalidadFrmDtoValidator localidadFrmDtoValidator = null;

	@Override @PreAuthorize("hasAuthority('ADMINISTRADOR')")
	public Object create(ODataUri oDataUri, Object object, EntityDataModel entityDataModel) throws ODataException {

    	if(object instanceof LocalidadEdm) {
			
    		LocalidadEdm localidad = (LocalidadEdm) object;
    		LocalidadFrmDto localidadFrmDto = new LocalidadFrmDto(localidad);
    		
    		DataBinder binder = new DataBinder(localidadFrmDto);
			
			binder.setValidator(localidadFrmDtoValidator);
			binder.validate();
			
			BindingResult bindingResult = binder.getBindingResult();
						
			if(bindingResult.hasFieldErrors()) {
				throw new ODataDataSourceException("HAY DATOS INVALIDOS EN LA SOLICITUD ENVIADA");
			}
			
			PaisEntity paisEntity = paisRepository.findOne(localidad.getPaisID());
			ProvinciaEntity provinciaEntity = provinciaRepository.findOne(new ProvinciaPKEntity(localidad.getPaisID(), localidad.getProvinciaID()));
			
			if(paisEntity == null) {
				throw new ODataDataSourceException(String.format("EL PAIS CON ID %s NO EXISTE!", localidad.getPaisID()));
			}
			
			if(provinciaEntity == null) {
				throw new ODataDataSourceException(String.format("LA PROVINCIA CON ID %s Y PAIS %s NO EXISTE!", localidad.getProvinciaID(), paisEntity));
			}
			
			if(!provinciaEntity.getPais().equals(paisEntity)) {
				throw new ODataDataSourceException(String.format("LA PROVINCIA %s NO PERTENECE AL PAIS %s!", provinciaEntity, paisEntity));
			}
			
			LocalidadEntity localidadEntity = new LocalidadEntity();
			
			localidadEntity.setPais(paisEntity);
			localidadEntity.setProvincia(provinciaEntity);
			localidadEntity.setLocPostal(localidadFrmDto.getCodigoPostal());
			localidadEntity.setDescripcion(localidadFrmDto.getDescripcion());
			localidadEntity.setPrefijo(localidadFrmDto.getPrefijo());
			
			localidadRepository.save(localidadEntity);
			
    		return new LocalidadEdm(localidadEntity);
		}
		
		throw new ODataDataSourceException("LOS DATOS NO CORRESPONDEN A LA ENTIDAD PAIS");
	}

	@Override @PreAuthorize("hasAuthority('ADMINISTRADOR')")
	public Object update(ODataUri oDataUri, Object object, EntityDataModel entityDataModel) throws ODataException {

    	if(object instanceof LocalidadEdm) {

    		Map<String, Object> oDataUriKeyValues = ODataUriUtil.asJavaMap(ODataUriUtil.getEntityKeyMap(oDataUri, entityDataModel));
			
    		LocalidadEdm localidad = (LocalidadEdm) object;
    		localidad.setPaisID(Integer.valueOf( oDataUriKeyValues.get("paisID").toString() ));
    		localidad.setProvinciaID(Integer.valueOf( oDataUriKeyValues.get("provinciaID").toString() ));
    		localidad.setCodigoPostal(oDataUriKeyValues.get("paisID").toString() );
    		
    		LocalidadFrmDto localidadFrmDto = new LocalidadFrmDto(localidad);
    		
    		DataBinder binder = new DataBinder(localidadFrmDto);
			
			binder.setValidator(localidadFrmDtoValidator);
			binder.validate();
			
			BindingResult bindingResult = binder.getBindingResult();
						
			if(bindingResult.hasFieldErrors()) {
				throw new ODataDataSourceException("HAY DATOS INVALIDOS EN LA SOLICITUD ENVIADA");
			}
			
    		LocalidadPKEntity pk = new LocalidadPKEntity(localidad);
    		LocalidadEntity localidadEntity = localidadRepository.findOne(pk);
    		
			if(localidadEntity == null) {
				throw new ODataDataSourceException(String.format("LA LOCALIDAD CON LA CAVE [PAIS=%s,PROVINCIA=%s,CODIGO_POSTAL=%s] NO EXISTE!", localidad.getPaisID(), localidad.getProvinciaID(), localidad.getCodigoPostal()));
			}
			
			localidadEntity.setDescripcion(localidadFrmDto.getDescripcion() == null ? localidadEntity.getDescripcion() : localidadFrmDto.getDescripcion());
			localidadEntity.setPrefijo(localidadFrmDto.getPrefijo() == null ? localidadEntity.getPrefijo() : localidadFrmDto.getPrefijo());
			
			localidadRepository.save(localidadEntity);
			
    		return new LocalidadEdm(localidadEntity);
		}
		
		throw new ODataDataSourceException("LOS DATOS NO CORRESPONDEN A LA ENTIDAD PAIS");
	}

	@Override @PreAuthorize("hasAuthority('ADMINISTRADOR')")
	public void delete(ODataUri oDataUri, EntityDataModel entityDataModel) throws ODataException {

		Option<Object> entity = ODataUriUtil.extractEntityWithKeys(oDataUri, entityDataModel);
    	
    	if(entity.isDefined()) {
    		
    		LocalidadEdm localidad = (LocalidadEdm) entity.get();
    		
    		LocalidadPKEntity pk = new LocalidadPKEntity(localidad);
    		LocalidadEntity localidadEntity = localidadRepository.findOne(pk);

    		if(localidadEntity == null) {
				throw new ODataDataSourceException(String.format("LA LOCALIDAD CON LA CAVE [PAIS=%s,PROVINCIA=%s,CODIGO_POSTAL=%s] NO EXISTE!", localidad.getPaisID(), localidad.getProvinciaID(), localidad.getCodigoPostal()));
			}
    		
    		localidadRepository.delete(localidadEntity);
    		
    		return;
        }
    	
    	throw new ODataDataSourceException("LOS DATOS NO CORRESPONDEN A LA ENTIDAD PAIS");
	}

	@Override @PreAuthorize("hasAuthority('ADMINISTRADOR')")
	public void createLink(ODataUri arg0, ODataLink arg1, EntityDataModel arg2) throws ODataException {
		throw new ODataNotImplementedException("Not supported for now");
	}

	@Override @PreAuthorize("hasAuthority('ADMINISTRADOR')")
	public void deleteLink(ODataUri arg0, ODataLink arg1, EntityDataModel arg2) throws ODataException {
		throw new ODataNotImplementedException("Not supported for now");
	}

	@Override
	public TransactionalDataSource startTransaction() {
		throw new ODataSystemException("No support for transactions");
	}

	@Override
	public QueryOperationStrategy getStrategy(ODataRequestContext oDataRequestContext, QueryOperation queryOperation, TargetType targetType) throws ODataException {

		LocalidadesStrategyBuilder builder = new LocalidadesStrategyBuilder();
		BooleanExpression expression = builder.buildCriteria(queryOperation, oDataRequestContext);
		List<Sort.Order> orderByList = builder.getOrderByList();
		
		int limit = builder.getLimit();
        int skip = builder.getSkip();
		List<String> propertyNames = builder.getPropertyNames();
		
		List<LocalidadEntity> localidadEntities = (List<LocalidadEntity>) ( orderByList == null || orderByList.size() == 0 ?
				localidadRepository.findAll(expression) :
				localidadRepository.findAll(expression, new Sort(orderByList)) );
		
		return () -> {

			List<LocalidadEdm> filtered = localidadEntities.stream().map(entity -> { return new LocalidadEdm(entity); }).collect(Collectors.toList());
			
			long count = 0;
        	
            if (builder.isCount() || builder.includeCount()) {
                count = filtered.size();

                if (builder.isCount()) {
                    return QueryResult.from(count);
                }
            }

            if (skip != 0 || limit != Integer.MAX_VALUE) {
                filtered = filtered.stream().skip(skip).limit(limit).collect(Collectors.toList());
            }

            if (propertyNames != null && !propertyNames.isEmpty()) {
                try {
                    return QueryResult.from(EdmUtil.getEdmPropertyValue(filtered.get(0), propertyNames.get(0)));
                } catch (IllegalAccessException e) {
                    return QueryResult.from(Collections.emptyList());
                }
            }
            
            QueryResult result = QueryResult.from(filtered);
            if (builder.includeCount()) {
                result = result.withCount(count);
            }
            return result;
		};
	}

	@Override
	public DataSource getDataSource(ODataRequestContext oDataRequestContext) {
		return this;
	}

	@Override
	public boolean isSuitableFor(ODataRequestContext oDataRequestContext, String entityType) throws ODataDataSourceException {
		return oDataRequestContext.getEntityDataModel().getType(entityType).getJavaType().equals(LocalidadEdm.class);
	}
}
