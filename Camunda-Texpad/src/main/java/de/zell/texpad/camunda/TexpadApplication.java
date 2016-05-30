/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.zell.texpad.camunda;

import org.camunda.bpm.application.ProcessApplication;
import org.camunda.bpm.application.impl.ServletProcessApplication;

/**
 *
 * @author Christopher Zell <christopher.zell@camunda.com>
 */
@ProcessApplication("Texpad App")
public class TexpadApplication extends ServletProcessApplication {

}
