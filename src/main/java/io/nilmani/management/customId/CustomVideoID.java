package io.nilmani.management.customId;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;
import java.util.Random;

public class CustomVideoID  implements IdentifierGenerator {
    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) {
        String prefiz ="AYU";
        int randomValue = new Random().nextInt(900)+100;
        return prefiz+randomValue;
    }
}
