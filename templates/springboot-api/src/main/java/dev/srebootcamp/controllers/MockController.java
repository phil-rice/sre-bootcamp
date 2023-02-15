package ${group}.${packageDetails.name}.controllers;

import org.springframework.web.bind.annotation.${packageDetails.sre.verb}Mapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ${packageDetails.name|toFirstUpper}Controller {

  @${packageDetails.sre.verb}Mapping(value = "${packageDetails.sre.endpoint}", produces = "application/json")
  public String endPoint() throws Exception {
    return "Hello world";
  }
}
