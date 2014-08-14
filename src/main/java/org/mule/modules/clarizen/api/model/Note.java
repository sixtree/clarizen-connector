/**
 * Mule Clarizen Cloud Connector
 *
 * (c) 2003-2014 MuleSoft, Inc. The software in this package is published under
 * the terms of the CPAL v1.0 license, a copy of which has been included with this
 * distribution in the LICENSE.md file.
 */

package org.mule.modules.clarizen.api.model;

import java.util.Date;

import com.clarizen.api.EntityId;

public class Note extends ClarizenEntity {

    private String comment;
    private String plainText;
    private EntityId attachedTo;
    private org.mule.modules.clarizen.api.model.flat.CommentTypeFlat visibility;
    private Date relatedDate;

    public String getComment() {
        return comment;
    }
    public String getPlainText() {
        return plainText;
    }
    public EntityId getAttachedTo() {
        return attachedTo;
    }
    public org.mule.modules.clarizen.api.model.flat.CommentTypeFlat getVisibility() {
        return visibility;
    }
    public Date getRelatedDate() {
        return relatedDate;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }
    public void setPlainText(String plainText) {
        this.plainText = plainText;
    }
    public void setAttachedTo(EntityId attachedTo) {
        this.attachedTo = attachedTo;
    }
    public void setVisibility(
            org.mule.modules.clarizen.api.model.flat.CommentTypeFlat visibility) {
        this.visibility = visibility;
    }
    public void setRelatedDate(Date relatedDate) {
        this.relatedDate = relatedDate;
    }
}
