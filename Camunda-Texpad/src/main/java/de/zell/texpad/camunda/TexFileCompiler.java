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
 * Compiles the given .tex file and creates a PDF resource.
 *
 * @author Christopher Zell <christopher.zell@camunda.com>
 */
public class TexFileCompiler implements JavaDelegate {

  private final static Logger LOGGER = Logger.getLogger(TexFileCompiler.class.getName());

  public void execute(DelegateExecution execution) throws Exception {
    for( String name : execution.getVariableNames()) {
      LOGGER.log(Level.INFO, name);
    }
//    try {
//    // Execute command
//    String command = "pdflatex {0} ";
//    Process child = Runtime.getRuntime().exec(command);
//
//    // Get output stream to write from it
//    OutputStream out = child.getOutputStream();
//
//    out.write("cd C:/ /r/n".getBytes());
//    out.flush();
//    out.write("dir /r/n".getBytes());
//    out.close();
//} catch (IOException e) {
//}
  }
}
