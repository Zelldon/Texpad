/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.zell.texpad.camunda;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

/**
 * Validates the given tex file.
 *
 * @author Christopher Zell <christopher.zell@camunda.com>
 */
public class TexFileValidator implements JavaDelegate {

  private final static Logger LOGGER = Logger.getLogger(TexFileValidator.class.getName());

  public void execute(DelegateExecution execution) throws Exception {
    execution.setVariable("valid", Boolean.TRUE);
    LOGGER.log(Level.INFO, "Processing request by ''{0}''...", execution.getVariable("customerId"));
  }

}
