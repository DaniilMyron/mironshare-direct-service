package com.miron.directservice.infrastructure.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.miron.directservice.domain.entity.User;
import com.miron.directservice.infrastructure.entity.UserEntity;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.function.Function;

@Slf4j
public class UserConverter implements Function<String, User> {

    @SneakyThrows
    @Override
    public User apply(String template) {
        var userParameters = User.class.getConstructor(String.class, String.class, String.class, String.class, String.class).getParameters();
        var userParametersNames = Arrays.stream(userParameters).map(Parameter::getName).toArray(String[]::new);
        log.info("User parameters names: {}", (Object) userParametersNames);
        log.info("UserConverted template: {}", template);
        String[] parametersForUser = new String[6];
        template = template.substring(1, template.length() - 1).replace("\"", "");
        var parameters = template.split(",");
        for (int i = 0; i < parameters.length; i++) {
            var templateParameterName = parameters[i].substring(0,  parameters[i].indexOf(':'));
            if(!templateParameterName.equals(userParametersNames[i])){
                throw new Exception("Invalid template parameter: " + templateParameterName);
            }
            parametersForUser[i] = parameters[i].substring(parameters[i].indexOf(':') + 1);
        }
        return new UserEntity(parametersForUser[0], parametersForUser[1], parametersForUser[2], parametersForUser[4], parametersForUser[5]);
    }
}
