package org.stackexchange.dumps.importer.services;

import javax.persistence.EntityManager;
import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;

/**
 * Created by linse on 8/12/14.
 */
public interface SofManager {
    EntityManager getEm();
}
