package com.example.xmldemoex.utils;

import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBException;

@Component
public interface XMLParser {
    <T> T ummarshalFromFile(Class<T> objectClass, String filePath) throws JAXBException;

    <T> void marshalToFile(String filePath,T rootDto) throws JAXBException;

}
