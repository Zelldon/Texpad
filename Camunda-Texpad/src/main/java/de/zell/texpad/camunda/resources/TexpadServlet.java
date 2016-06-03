/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.zell.texpad.camunda.resources;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import org.camunda.bpm.BpmPlatform;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.repository.ProcessDefinition;


/**
 *
 * @author Christopher Zell <christopher.zell@camunda.com>
 */
@Path("/Texpad")
public class TexpadServlet {

  private final UriBuilder builder = UriBuilder.fromResource(TexpadServlet.class);
  private static final Logger LOGGER = Logger.getLogger(TexpadServlet.class.getName());

  @POST
  public Response buildTexFile() {
    ProcessEngine engine = BpmPlatform.getProcessEngineService().getDefaultProcessEngine();
    LOGGER.log(Level.INFO, "Get ENGINE");

    ProcessDefinition processDefinition = engine.getRepositoryService().createProcessDefinitionQuery().processDefinitionKey("Texpad").singleResult();
    engine.getRuntimeService().startProcessInstanceById(processDefinition.getId());

    return Response.created(builder.clone().path("").build()).build();
  }

}
