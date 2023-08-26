package io.nilmani.management.customId;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;
import java.util.Random;

public class CustomIdGenerator  implements IdentifierGenerator {
    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) {
        String prefiz ="NIL";
        int randomValue = new Random().nextInt(900)+100;
        return prefiz+randomValue;
    }
}
