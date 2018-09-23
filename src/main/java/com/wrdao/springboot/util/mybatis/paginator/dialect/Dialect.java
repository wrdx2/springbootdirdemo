package com.wrdao.springboot.util.mybatis.paginator.dialect;

import com.wrdao.springboot.util.mybatis.paginator.domain.Order;
import com.wrdao.springboot.util.mybatis.paginator.domain.PageBounds;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.type.TypeHandlerRegistry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 类似hibernate的Dialect,但只精简出分页部分
 * @author badqiu
 * @author miemiedev
 */
public class Dialect {
    protected TypeHandlerRegistry typeHandlerRegistry;
    protected MappedStatement mappedStatement;
    protected PageBounds pageBounds;
    protected Object parameterObject;
    protected BoundSql boundSql;
    private Configuration configuration;
    protected List<ParameterMapping> parameterMappings;
    protected Map<String, Object> pageParameters = new HashMap<String, Object>();

    private String pageSQL;
    private String countSQL;


    public Dialect(MappedStatement mappedStatement, Object parameterObject, PageBounds pageBounds){
    	this.mappedStatement = mappedStatement;
        this.configuration = mappedStatement.getConfiguration();
        this.typeHandlerRegistry = mappedStatement.getConfiguration().getTypeHandlerRegistry();
        this.parameterObject = parameterObject;
        this.pageBounds = pageBounds;
        init();
    }

    protected void init(){

        boundSql = mappedStatement.getBoundSql(parameterObject);
        parameterMappings = new ArrayList<>(boundSql.getParameterMappings());
        
        
        for (ParameterMapping parameterMapping : parameterMappings) {
        	String propertyName = parameterMapping.getProperty();
        	Object value;
        	if (boundSql.hasAdditionalParameter(propertyName)) { // issue #448 ask first for additional params
        		value = boundSql.getAdditionalParameter(propertyName);
        	} else if (parameterObject == null) {
        		value = null;
        	} else if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {
        		value = parameterObject;
        	} else {
        		MetaObject metaObject = configuration.newMetaObject(parameterObject);
        		value = metaObject.getValue(propertyName);
        	}
        	pageParameters.put(propertyName, value);
		}
        
        
    	/*if(parameterObject instanceof Map){
    		pageParameters.putAll((Map)parameterObject);
    	}else if( parameterObject != null){
    		Class cls = parameterObject.getClass();
    		if(cls.isPrimitive() || cls.isArray() ||
    				SimpleTypeRegistry.isSimpleType(cls) ||
    				Enum.class.isAssignableFrom(cls) ||
    				Collection.class.isAssignableFrom(cls) || typeHandlerRegistry.hasTypeHandler(cls)){
    			for (ParameterMapping parameterMapping : parameterMappings) {
    				pageParameters.put(parameterMapping.getProperty(),parameterObject);
    			}
    		}else{
    			MetaObject metaObject = mappedStatement.getConfiguration().newMetaObject(parameterObject);
    			ObjectWrapper wrapper = metaObject.getObjectWrapper();
    			
    			
    			
    			for (ParameterMapping parameterMapping : parameterMappings) {
    				PropertyTokenizer prop = new PropertyTokenizer(parameterMapping.getProperty());
    				//pageParameters.put(parameterMapping.getProperty(),wrapper.get(prop));
    				pageParameters.put(parameterMapping.getProperty(),parameterObject);
    			}
    		}
    		
    	}*/


        StringBuffer bufferSql = new StringBuffer(boundSql.getSql().trim());
        if(bufferSql.lastIndexOf(";") == bufferSql.length()-1){
            bufferSql.deleteCharAt(bufferSql.length()-1);
        }
        String sql = bufferSql.toString();
        pageSQL = sql;
        if(pageBounds.getOrders() != null && !pageBounds.getOrders().isEmpty()){
            pageSQL = getSortString(sql, pageBounds.getOrders());
        }
        if(pageBounds.getOffset() != RowBounds.NO_ROW_OFFSET
                || pageBounds.getLimit() != RowBounds.NO_ROW_LIMIT){
            pageSQL = getLimitString(pageSQL, "__offset", pageBounds.getOffset(), "__limit",pageBounds.getLimit());
        }


        countSQL = getCountString(sql);
    }


    public List<ParameterMapping> getParameterMappings(){
        return parameterMappings;
    }

    public Object getParameterObject(){
        return pageParameters;
    }


    public String getPageSQL(){
        return pageSQL;
    }

    protected void setPageParameter(String name, Object value, Class type){
        ParameterMapping parameterMapping = new ParameterMapping.Builder(mappedStatement.getConfiguration(), name, type).build();
        parameterMappings.add(parameterMapping);
        pageParameters.put(name, value);
    }


    public String getCountSQL(){
        return countSQL;
    }

    
    /**
     * 将sql变成分页sql语句
     */
    protected String getLimitString(String sql, String offsetName,int offset, String limitName, int limit) {
        throw new UnsupportedOperationException("paged queries not supported");
    }

    /**
     * 将sql转换为总记录数SQL
     * @param sql SQL语句
     * @return 总记录数的sql
     */
    protected String getCountString(String sql){
        return "select count(1) from (" + sql + ") tmp_count";
    }

    /**
     * 将sql转换为带排序的SQL
     * @param sql SQL语句
     * @return 总记录数的sql
     */
    protected String getSortString(String sql, List<Order> orders){
        if(orders == null || orders.isEmpty()){
            return sql;
        }

        StringBuffer buffer = new StringBuffer("select * from (").append(sql).append(") temp_order order by ");
        for(Order order : orders){
            if(order != null){
                buffer.append(order.toString())
                        .append(", ");
            }

        }
        buffer.delete(buffer.length()-2, buffer.length());
        return buffer.toString();
    }
}
