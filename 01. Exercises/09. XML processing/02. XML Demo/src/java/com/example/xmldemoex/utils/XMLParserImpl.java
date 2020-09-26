package com.example.xmldemoex.utils;


import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;


public class XMLParserImpl implements XMLParser{


    @Override
    @SuppressWarnings("unchecked")
    public <T> T ummarshalFromFile(Class<T> objectClass, String filePath) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(objectClass);
        Unmarshaller unmarshaller = context.createUnmarshaller();

        return (T) unmarshaller.unmarshal(new File(filePath));
    }

    @Override
    public <T> void marshalToFile(String filePath,T rootDto) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(rootDto.getClass());
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(rootDto, new File(filePath));
    }
}
