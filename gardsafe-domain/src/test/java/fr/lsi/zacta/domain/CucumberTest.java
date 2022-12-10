package fr.lsi.zacta.domain;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(monochrome = true, glue = "fr.lsi.zacta.domain",
    features = "src/test/resources/fr/lsi/zacta/domain",
    plugin = {"pretty", "html:target/cucumber"}, publish = true)
public class CucumberTest {

}
