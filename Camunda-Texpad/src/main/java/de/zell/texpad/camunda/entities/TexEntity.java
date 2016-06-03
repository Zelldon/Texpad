/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.zell.texpad.camunda.entities;

import de.zell.texpad.camunda.TexpadConstants;
import java.util.HashMap;
import java.util.Map;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Christopher Zell <christopher.zell@camunda.com>
 */
@XmlRootElement(name = "tex")
@XmlAccessorType(XmlAccessType.NONE)
public class TexEntity {

  @XmlElement(name = "content", nillable = false)
  private String content;
  @XmlElement(name = "title")
  private String title;
  @XmlElement(name = "version")
  private long version;

  public TexEntity() {
  }


  public Map<String, Object> getAttributes() {
    Map<String, Object> attributes = new HashMap<String, Object>();
    if (content == null)
      throw new IllegalStateException();

    attributes.put(TexpadConstants.VAR_KEY_TEX_CONTENT, content);
    attributes.put(TexpadConstants.VAR_KEY_TEX_TITLE, title);
    attributes.put(TexpadConstants.VAR_KEY_TEX_VERSION, version);

    return attributes;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public long getVersion() {
    return version;
  }

  public void setVersion(long version) {
    this.version = version;
  }
}
