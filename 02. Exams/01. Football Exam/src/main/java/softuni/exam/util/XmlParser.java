package softuni.exam.util;

import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBException;

@Component
public interface XmlParser {
    <T> T ummarshalFromFile(Class<T> objectClass, String filePath) throws JAXBException;

    <T> void marshalToFile(String filePath,T rootDto) throws JAXBException;

}
