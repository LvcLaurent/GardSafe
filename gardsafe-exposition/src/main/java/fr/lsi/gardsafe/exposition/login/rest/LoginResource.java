package fr.lsi.gardsafe.exposition.login.rest;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import fr.lsi.gardsafe.exposition.common.ErrorModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * Controller pour login
 * 
 * @author Laurent SION
 *
 */
@RestController
@RequestMapping("/api/login")
@Validated
public class LoginResource {

  /**
   * The LOGGER
   */
  private static final Logger LOGGER = LoggerFactory.getLogger(LoginResource.class);

  /**
   * Constructor with services & mapper injection
   */
  public LoginResource() {
    super();
  }

  @ApiOperation(value = "Create Account", nickname = "createAccountUsingPost", //
      notes = "create a new account for GardSafe", response = ErrorModel.class,
      tags = {"login-resource"})
  @ApiResponses(value = {@ApiResponse(code = 201, message = "created", response = ErrorModel.class), //
      @ApiResponse(code = 401, message = "Unauthorized", response = ErrorModel.class),
      @ApiResponse(code = 403, message = "Forbidden", response = ErrorModel.class),
      @ApiResponse(code = 404, message = "Not Found", response = ErrorModel.class)})
  @PostMapping(value = "create", produces = APPLICATION_JSON_VALUE,
      consumes = APPLICATION_JSON_VALUE)
  public ErrorModel test(@Valid final String chaine) {
    LOGGER.info("test");
    return ErrorModel.builder().code("200").message("test").description("toto").build();
  }

}
