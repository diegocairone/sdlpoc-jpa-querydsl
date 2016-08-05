package com.cairone.sdlpocjpa.services;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;

import scala.Option;

import com.cairone.sdlpocjpa.dtos.ProvinciaFrmDto;
import com.cairone.sdlpocjpa.dtos.validators.ProvinciaFrmDtoValidator;
import com.cairone.sdlpocjpa.edm.ProvinciaEdm;
import com.cairone.sdlpocjpa.entities.PaisEntity;
import com.cairone.sdlpocjpa.entities.ProvinciaEntity;
import com.cairone.sdlpocjpa.entities.ProvinciaPKEntity;
import com.cairone.sdlpocjpa.repositories.PaisRepository;
import com.cairone.sdlpocjpa.repositories.ProvinciaRepository;
import com.cairone.sdlpocjpa.strategyBuilders.ProvinciasStrategyBuilder;
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
public class ProvinciasDataSource implements DataSource, DataSourceProvider {
	
	@Autowired private PaisRepository paisRepository = null;
	@Autowired private ProvinciaRepository provinciaRepository = null;
	
	@Autowired private ProvinciaFrmDtoValidator provinciaFrmDtoValidator = null;
	
	@Autowired private Logger logger = null;
	
	@Override @PreAuthorize("hasAuthority('ADMINISTRADOR')")
	public Object create(ODataUri oDataUri, Object object, EntityDataModel entityDataModel) throws ODataException {

		if(object instanceof ProvinciaEdm) {
			
			ProvinciaEdm provincia = (ProvinciaEdm) object;
			ProvinciaFrmDto provinciaFrmDto = new ProvinciaFrmDto(provincia);
			
			DataBinder binder = new DataBinder(provinciaFrmDto);
			
			binder.setValidator(provinciaFrmDtoValidator);
			binder.validate();
			
			BindingResult bindingResult = binder.getBindingResult();
						
			if(bindingResult.hasFieldErrors()) {
				throw new ODataDataSourceException("HAY DATOS INVALIDOS EN LA SOLICITUD ENVIADA");
			}
			
			Integer paisID = provinciaFrmDto.getPaisID();
			PaisEntity paisEntity = paisRepository.findOne(paisID);
			
			ProvinciaEntity provinciaEntity = new ProvinciaEntity();
			
			provinciaEntity.setId(provinciaFrmDto.getId());
			provinciaEntity.setPais(paisEntity);
			provinciaEntity.setDescripcion(provinciaFrmDto.getDescripcion());
			provinciaEntity.setDescripcionRed(provinciaFrmDto.getDescripcionReducida());
			
			provinciaRepository.save(provinciaEntity);
						
			return new ProvinciaEdm(provinciaEntity);
		}
		
		throw new ODataDataSourceException("LOS DATOS NO CORRESPONDEN A LA ENTIDAD PROVINCIA");
	}

	@Override @PreAuthorize("hasAuthority('ADMINISTRADOR')")
	public Object update(ODataUri oDataUri, Object object, EntityDataModel entityDataModel) throws ODataException {

		if(object instanceof ProvinciaEdm) {

			Map<String, Object> oDataUriKeyValues = ODataUriUtil.asJavaMap(ODataUriUtil.getEntityKeyMap(oDataUri, entityDataModel));
			
			ProvinciaEdm provincia = (ProvinciaEdm) object;
			provincia.setId(Integer.valueOf( oDataUriKeyValues.get("id").toString() ));
			provincia.setPaisID(Integer.valueOf( oDataUriKeyValues.get("paisID").toString() ));
			
			ProvinciaFrmDto provinciaFrmDto = new ProvinciaFrmDto(provincia);
			
			DataBinder binder = new DataBinder(provinciaFrmDto);
			
			binder.setValidator(provinciaFrmDtoValidator);
			binder.validate();
			
			BindingResult bindingResult = binder.getBindingResult();
						
			if(bindingResult.hasFieldErrors()) {
				throw new ODataDataSourceException("HAY DATOS INVALIDOS EN LA SOLICITUD ENVIADA");
			}
			
			Integer paisID = provinciaFrmDto.getPaisID();
			PaisEntity paisEntity = paisRepository.findOne(paisID);
			
			ProvinciaEntity provinciaEntity = new ProvinciaEntity();
			
			provinciaEntity.setId(provinciaFrmDto.getId());
			provinciaEntity.setPais(paisEntity);
			provinciaEntity.setDescripcion(provinciaFrmDto.getDescripcion());
			provinciaEntity.setDescripcionRed(provinciaFrmDto.getDescripcionReducida());
			
			provinciaRepository.save(provinciaEntity);
			ProvinciaEdm provinciaNueva = new ProvinciaEdm(provinciaEntity);
			
			return provinciaNueva;
		}
		
		throw new ODataDataSourceException("LOS DATOS NO CORRESPONDEN A LA ENTIDAD PROVINCIA");
	}
	
	@Override @PreAuthorize("hasAuthority('ADMINISTRADOR')")
	public void delete(ODataUri oDataUri, EntityDataModel entityDataModel) throws ODataException {
		
		Option<Object> entity = ODataUriUtil.extractEntityWithKeys(oDataUri, entityDataModel);

    	if(entity.isDefined()) {
    		
    		ProvinciaEdm provincia = (ProvinciaEdm) entity.get();
    		
    		ProvinciaPKEntity pk = new ProvinciaPKEntity(provincia.getPaisID(), provincia.getId());
    		ProvinciaEntity provinciaEntity = provinciaRepository.findOne(pk);
            
    		if(provinciaEntity == null) {
    			throw new ODataDataSourceException(String.format("NO SE ENCUENTRA UNA PROVINCIA CON ID %s (pais=%s)", provincia.getId(), provincia.getPais().getId()));
    		}
    		
    		provinciaRepository.delete(provinciaEntity);
    		
    		return;
        }
    	
    	throw new ODataDataSourceException("LOS DATOS NO CORRESPONDEN A LA ENTIDAD PROVINCIA");
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
		
		ProvinciasStrategyBuilder builder = new ProvinciasStrategyBuilder();
		BooleanExpression expression = builder.buildCriteria(queryOperation, oDataRequestContext);
		List<Sort.Order> orderByList = builder.getOrderByList();
		
		int limit = builder.getLimit();
        int skip = builder.getSkip();
		List<String> propertyNames = builder.getPropertyNames();
		
		List<ProvinciaEntity> provinciaEntities = (List<ProvinciaEntity>) ( orderByList == null || orderByList.size() == 0 ?
				provinciaRepository.findAll(expression) :
				provinciaRepository.findAll(expression, new Sort(orderByList)) );
		
		return () -> {

			List<ProvinciaEdm> filtered = provinciaEntities.stream().map(entity -> { return new ProvinciaEdm(entity); }).collect(Collectors.toList());
			
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
		return oDataRequestContext.getEntityDataModel().getType(entityType).getJavaType().equals(ProvinciaEdm.class);
	}
}
