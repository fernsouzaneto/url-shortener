package desafio.tdcompany.urlshortener.util;

import org.apache.commons.lang3.RandomStringUtils;

public class GeneratorID {

    private static int NUM_MAX_GENERATED_ID = 6;

    public static String generate() {
        return RandomStringUtils.randomAlphanumeric(NUM_MAX_GENERATED_ID);
    }
}
