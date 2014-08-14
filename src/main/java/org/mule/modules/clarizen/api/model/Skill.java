/**
 * Mule Clarizen Cloud Connector
 *
 * (c) 2003-2014 MuleSoft, Inc. The software in this package is published under
 * the terms of the CPAL v1.0 license, a copy of which has been included with this
 * distribution in the LICENSE.md file.
 */

package org.mule.modules.clarizen.api.model;

import java.util.Date;

public class Skill extends ClarizenEntity {
    
        private String description;
        private Date lastUpdatedBySystemOn;
        private Double availability;
        private Integer stopwatchesCount;

        public String getDescription() {
            return description;
        }
        public Date getLastUpdatedBySystemOn() {
            return lastUpdatedBySystemOn;
        }
        public Double getAvailability() {
            return availability;
        }
        public Integer getStopwatchesCount() {
            return stopwatchesCount;
        }
        public void setDescription(String description) {
            this.description = description;
        }
        public void setLastUpdatedBySystemOn(Date lastUpdatedBySystemOn) {
            this.lastUpdatedBySystemOn = lastUpdatedBySystemOn;
        }
        public void setAvailability(Double availability) {
            this.availability = availability;
        }
        public void setStopwatchesCount(Integer stopwatchesCount) {
            this.stopwatchesCount = stopwatchesCount;
        }
}
