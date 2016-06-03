/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.zell.texpad.camunda;

import static de.zell.texpad.camunda.TexpadConstants.VAR_KEY_VALID;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import static de.zell.texpad.camunda.TexpadConstants.VAR_KEY_TEX_CONTENT;

/**
 * Validates the given tex file.
 *
 * @author Christopher Zell <christopher.zell@camunda.com>
 */
public class TexFileValidator implements JavaDelegate {

  /**
   * The regex contains cmds which are disallowed for the given .tex files.
   */
  private final static String REGEX_DISALLOWED_TEX_CMDS = "((\\\\input)|(\\\\include)|(\\\\.*input))";
  /**
   * Represents the mandatory cmd which should exists in every given .tex file.
   */
  private final static String MANDATORY_TEX_CMD = "document";

  private final static Logger LOGGER = Logger.getLogger(TexFileValidator.class.getName());

  public void execute(DelegateExecution execution) throws Exception {
    Object varTexFile = execution.getVariable(VAR_KEY_TEX_CONTENT);
    Boolean valid = Boolean.FALSE;
    if (varTexFile != null) {
      valid = validateTex(varTexFile.toString());
    }

    execution.setVariable(VAR_KEY_VALID, valid);
    LOGGER.log(Level.INFO, valid ? ".tex file is valid" : "no valid .tex file");
  }

  /**
   * Validates the given tex content.
   * Checks if the tex is not empty, contains no includes to other .tex files
   * and has a document cmd.
   *
   * @param texContent the content of the tex file which should be validated
   * @return true if the content is valid for a tex file, otherwise false
   */
  protected Boolean validateTex(String texContent) {
    Boolean valid = true;

    if (texContent.isEmpty())
      return !valid;

    Matcher matcher = Pattern.compile(REGEX_DISALLOWED_TEX_CMDS).matcher(texContent);
    if (matcher.matches())
      return !valid;

    if (!texContent.contains(MANDATORY_TEX_CMD))
      return !valid;

    return valid;
  }

}
