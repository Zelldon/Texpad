/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.zell.texpad.camunda;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.MessageFormat;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import static de.zell.texpad.camunda.TexpadConstants.VAR_KEY_TEX_CONTENT;

/**
 * Compiles the given .tex file and creates a PDF resource.
 *
 * @author Christopher Zell <christopher.zell@camunda.com>
 */
public class TexFileCompiler implements JavaDelegate {

  public static final String CONST_STR_UTF_8 = "UTF-8";
  public static final String TEMP_FOLDER = "/tmp/{0}/";
  public static final String TEMP_TEX_FILE_FORMAT = TEMP_FOLDER + "{1}.tex";
  public static final String ERROR_MESSAGE = "Compilation failed!";
  public static final String COMPILATION_CMD = "pdflatex -output-directory " + TEMP_FOLDER + " {1}";
  public static final String VAR_KEY_PDF_FILE_PATH = "pdfFile";

  private final static Logger LOGGER = Logger.getLogger(TexFileCompiler.class.getName());

  public void execute(DelegateExecution execution) throws Exception {

    Object varTexFile = execution.getVariable(VAR_KEY_TEX_CONTENT);
    if (varTexFile != null) {
      //create tmp .tex file
      UUID uuid = UUID.randomUUID();
      File tmpDir = new File(MessageFormat.format(TEMP_FOLDER, uuid.toString()));
      tmpDir.mkdirs();
      String tmpTexFileName = MessageFormat.format(TEMP_TEX_FILE_FORMAT, uuid.toString(), uuid.toString());
      PrintWriter writer = new PrintWriter(tmpTexFileName, CONST_STR_UTF_8);
      writer.write(varTexFile.toString());
      writer.close();

      try {
        // Execute command
        String command = MessageFormat.format(COMPILATION_CMD, uuid.toString(), tmpTexFileName);
        Runtime.getRuntime().exec(command);
        LOGGER.log(Level.INFO, "Successful compiled and created pdf file: {0}.pdf", uuid.toString());
        execution.setVariable(VAR_KEY_PDF_FILE_PATH, tmpTexFileName.replace(".tex", ".pdf"));
      } catch (IOException e) {
        LOGGER.log(Level.SEVERE, ERROR_MESSAGE, e);
      }
    }
  }
}
