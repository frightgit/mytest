package com.example.springdemo.common.jsonReturn;

import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletResponse;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * @Description:
 * @Auther: wjc
 * @Date: 2019/11/28 10:49
 */
public class JsonReturnHandler implements HandlerMethodReturnValueHandler, BeanPostProcessor {
    private List<ResponseBodyAdvice<Object>> advices = new ArrayList<>();

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName){
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName){
        if (bean instanceof ResponseBodyAdvice) {
            advices.add((ResponseBodyAdvice<Object>) bean);
        } else if (bean instanceof RequestMappingHandlerAdapter) {
            RequestMappingHandlerAdapter handlerAdapter = (RequestMappingHandlerAdapter) bean;
            List<HandlerMethodReturnValueHandler> handlers = new ArrayList<>(handlerAdapter.getReturnValueHandlers());

            JsonReturnHandler jsonHandler = null;
            for (int i = handlers.size() - 1; i >= 0; i--) {
                HandlerMethodReturnValueHandler handler = handlers.get(i);
                if (handler instanceof JsonReturnHandler) {
                    jsonHandler = (JsonReturnHandler) handlers.remove(i);
                    break;
                }
            }

            // 调整 JSON Handler 顺序
            if (jsonHandler != null) {
                handlers.add(0, jsonHandler);
                handlerAdapter.setReturnValueHandlers(handlers);
            }
        }
        return bean;
    }

    @Override
    public boolean supportsReturnType(MethodParameter returnType) {
        return returnType.hasMethodAnnotation(JsonFieldFilter.class);
    }

    @Override
    public void handleReturnValue(Object returnObject, MethodParameter paramter,
                                  ModelAndViewContainer container, NativeWebRequest request) throws Exception {
        container.setRequestHandled(true);
        Annotation[] annos = paramter.getMethodAnnotations();
        Arrays.asList(annos).forEach(a -> {
            if (a instanceof JsonFieldFilter) {

            }
        });


        JsonFilterSerializer serializer = new JsonFilterSerializer();
        if(paramter.hasMethodAnnotation(JsonFieldFilter.class)) {//如果有JsonFieldFilter注解，则过滤返回的对象returnObject
            JsonFieldFilter jsonFilter = paramter.getMethodAnnotation(JsonFieldFilter.class);
            serializer.filter(jsonFilter.type() == null ?returnObject.getClass() : jsonFilter.type(), jsonFilter.include(), jsonFilter.exclude());//调用过滤方法
        }
        HttpServletResponse response = request.getNativeResponse(HttpServletResponse.class);
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        response.getWriter().write(serializer.toJson(returnObject));
    }

}