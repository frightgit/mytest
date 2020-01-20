//package com.example.springdemo.common.jsonReturn;
//
//import org.springframework.core.MethodParameter;
//import org.springframework.http.MediaType;
//import org.springframework.web.context.request.NativeWebRequest;
//import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
//import org.springframework.web.method.support.ModelAndViewContainer;
//
//import javax.servlet.http.HttpServletResponse;
//import java.lang.annotation.Annotation;
//import java.util.Arrays;
//
//
///**
// * @Description:
// * @Auther: wjc
// * @Date: 2019/11/28 10:49
// */
//public class JsonReturnHandlerback implements HandlerMethodReturnValueHandler {
//
////    @Override
////    public void handleReturnValue(Object returnObject, MethodParameter paramter,
////                                  ModelAndViewContainer container, NativeWebRequest request) throws Exception {
////        container.setRequestHandled(true);
////        JsonFilterSerializer serializer = new JsonFilterSerializer();
////        if(paramter.hasMethodAnnotation(JsonFieldFilter.class)) {//如果有JsonFieldFilter注解，则过滤返回的对象returnObject
////            JsonFieldFilter jsonFilter = paramter.getMethodAnnotation(JsonFieldFilter.class);
////            serializer.filter(jsonFilter.type() == null ?returnObject.getClass() : jsonFilter.type(), jsonFilter.include(), jsonFilter.exclude());//调用过滤方法
////        }
////        HttpServletResponse response = request.getNativeResponse(HttpServletResponse.class);
////        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
////        response.getWriter().write(serializer.toJson(returnObject));
////    }
//
//    @Override
//    public void handleReturnValue(Object returnObject, MethodParameter paramter,
//                                  ModelAndViewContainer container, NativeWebRequest request) throws Exception {
//        container.setRequestHandled(true);
//        Annotation[] annos = paramter.getMethodAnnotations();
//        Arrays.asList(annos).forEach(a -> {
//            if (a instanceof JsonFieldFilter) {
//
//            }
//        });
//
//
//        JsonFilterSerializer serializer = new JsonFilterSerializer();
//        if(paramter.hasMethodAnnotation(JsonFieldFilter.class)) {//如果有JsonFieldFilter注解，则过滤返回的对象returnObject
//            JsonFieldFilter jsonFilter = paramter.getMethodAnnotation(JsonFieldFilter.class);
//            serializer.filter(jsonFilter.type() == null ?returnObject.getClass() : jsonFilter.type(), jsonFilter.include(), jsonFilter.exclude());//调用过滤方法
//        }
//        HttpServletResponse response = request.getNativeResponse(HttpServletResponse.class);
//        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
//        response.getWriter().write(serializer.toJson(returnObject));
//    }
//
//
//
//
//    @Override
//    public boolean supportsReturnType(MethodParameter methodParameter) {
//        return methodParameter.hasMethodAnnotation(JsonFieldFilter.class);
//    }
//
//}