import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestInstancePostProcessor;

import java.util.logging.Logger;

public class LoggerExtension implements TestInstancePostProcessor, BeforeAllCallback, BeforeEachCallback, AfterTestExecutionCallback, AfterEachCallback {
    @Override
    public void afterEach(ExtensionContext context) throws Exception {
        Logger LOGGER = Logger.getLogger(JunitPracticalTask1.class.getName());
        LOGGER.info("afterEach called");
    }

    @Override
    public void afterTestExecution(ExtensionContext context) throws Exception {
        Logger LOGGER = Logger.getLogger(JunitPracticalTask1.class.getName());
        LOGGER.info("afterTestExecution called");
    }

    @Override
    public void beforeAll(ExtensionContext context) throws Exception {
        Logger LOGGER = Logger.getLogger(JunitPracticalTask1.class.getName());
        LOGGER.info("beforeAll called");
    }

    @Override
    public void beforeEach(ExtensionContext context) throws Exception {
        Logger LOGGER = Logger.getLogger(JunitPracticalTask1.class.getName());
        LOGGER.info("beforeEach called");
    }

    @Override
    public void postProcessTestInstance(Object testInstance, ExtensionContext context) throws Exception {
        Logger LOGGER = Logger.getLogger(JunitPracticalTask1.class.getName());
        LOGGER.info("postProcessTestInstance called");
    }
}

