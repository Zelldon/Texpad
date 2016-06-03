/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.zell.texpad.camunda.resources;

import de.zell.texpad.camunda.entities.TexEntity;
import java.util.logging.Logger;
import javax.ws.rs.Consumes;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import org.camunda.bpm.BpmPlatform;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.runtime.ProcessInstance;


/**
 *
 * @author Christopher Zell <christopher.zell@camunda.com>
 */
@Path(TexpadServlet.PROC_DEF_KEY)
public class TexpadServlet {

  public static final String PROC_DEF_KEY = "Texpad";
  private final UriBuilder builder = UriBuilder.fromResource(TexpadServlet.class);
  private static final Logger LOGGER = Logger.getLogger(TexpadServlet.class.getName());

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Response buildTexFile(TexEntity entity) {


    startProcessInstance(entity);
    return Response.created(builder.clone().path("").build()).build();
  }


  private void startProcessInstance(TexEntity entity) {
    ProcessEngine engine = BpmPlatform.getProcessEngineService().getDefaultProcessEngine();
    if (engine == null)
      throw new InternalServerErrorException("No process engine available!");

//    ProcessDefinition processDefinition = engine.getRepositoryService().createProcessDefinitionQuery().processDefinitionKey(PROC_DEF_KEY).singleResult();
    ProcessInstance instance = engine.getRuntimeService().startProcessInstanceByKey(PROC_DEF_KEY, entity.getAttributes());
  }

}
