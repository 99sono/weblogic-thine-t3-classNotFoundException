package remoteejb;

import java.util.Properties;
import java.util.logging.Logger;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.junit.Test;

/**
 * Invoke remote EJB. This test will fail or work depending on the stand-alone client library used to run the test.
 *
 *
 */

public class LogMessageOnServerLogImplTest {
    private static final Logger LOGGER = Logger.getLogger(LogMessageOnServerLogImplTest.class.getCanonicalName());

    @Test
    public void logOnServerTest() throws NamingException {
        // (a) establish a JNDI connection to the server
        InitialContext jndiContext = getWeblogicJndiINitialContext();

        // (b) fetch the remote EJB
        LogMessageOnServerLog remoteEjb = resolveBean(jndiContext, "weblogic-thin-t3",
                LogMessageOnServerLogImpl.class.getSimpleName(), LogMessageOnServerLog.class);

        // (c) log something on the server
        remoteEjb.logOnServer("If the server log shows this message it means the stand alone library could be used."
                + " If we do not see this message, it means we probably could not even establish a JNDI connection to the server."
                + " And this is the bug we are having with the wlthint3client-12.2.1.2 ");

    }

    // geting a JNDI connection
    public InitialContext getWeblogicJndiINitialContext() throws NamingException {
        return new InitialContext(getInitialContextProperties());
    }

    private Properties getInitialContextProperties() {
        Properties result = new Properties();
        result.put("java.naming.factory.initial", "weblogic.jndi.WLInitialContextFactory");
        result.put("java.naming.provider.url", "t3://localhost:7001");
        return result;
    }

    // geting the ejb
    public static <T> T resolveBean(InitialContext jndiContext, String appName, String ejbImpl, Class<T> remoteInterface)
            throws NamingException {
        String name = "java:global/" + appName + "/" + ejbImpl + "!" + remoteInterface.getName();
        LOGGER.info("Call doLookup() for EJB  " + name);
        return remoteInterface.cast(jndiContext.lookup(name));
    }

}
