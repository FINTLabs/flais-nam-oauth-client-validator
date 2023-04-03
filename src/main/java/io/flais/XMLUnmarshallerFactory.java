package io.flais;

import jakarta.xml.bind.*;

import javax.xml.namespace.QName;
import javax.xml.transform.stream.StreamSource;
import java.io.StringReader;
import java.io.StringWriter;


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

    public static <T> String marshallObject(T object, Class<T> objectType) {
        try {
            JAXBContext context = JAXBContext.newInstance(object.getClass());
            Marshaller marshaller = context.createMarshaller();
            StringWriter stringWriter = new StringWriter();

            JAXBElement<T> jaxbElement =
                    new JAXBElement<T>(
                            new QName("", object.getClass().getSimpleName()),
                            objectType,
                            object
                    );
            marshaller.marshal(jaxbElement, stringWriter);

            return stringWriter.toString();

        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }
}
