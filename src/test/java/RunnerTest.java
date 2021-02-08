import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;




    @RunWith(Cucumber.class)
    @CucumberOptions(
            features = "src/test/java",
            glue={"org.example"},
            plugin ={"pretty",
                    "html:target/cucumber/test-report.html",
                    "json:target/cucumber/test-report.json",
                    "junit:target/cucumber/test-report.xml"},
            monochrome = true

    )
    class Runner_Test {

    }


