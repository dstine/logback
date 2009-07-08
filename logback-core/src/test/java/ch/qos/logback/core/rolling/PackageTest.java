package ch.qos.logback.core.rolling;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses( { RenamingTest.class, SizeBasedRollingTest.class,
    TimeBasedRollingTest.class, TimeBasedRollingWithCleanTest.class,
    ch.qos.logback.core.rolling.helper.PackageTest.class })
public class PackageTest {
}
