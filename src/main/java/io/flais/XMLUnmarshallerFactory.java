package io.flais;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;

import javax.xml.transform.stream.StreamSource;
import java.io.StringReader;


public class XMLUnmarshallerFactory {

    public static <T> T unmarshallObject(String value, Class<T> objectType) {
        try {
            JAXBContext context = JAXBContext.newInstance(objectType);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            JAXBElement<T> unmarshal = unmarshaller.unmarshal(new StreamSource(new StringReader(value)), objectType);

            return unmarshal.getValue();

        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }
}
