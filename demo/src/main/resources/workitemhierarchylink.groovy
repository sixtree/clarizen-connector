/**
 * Mule Clarizen Cloud Connector
 *
 * (c) 2003-2014 MuleSoft, Inc. The software in this package is published under
 * the terms of the CPAL v1.0 license, a copy of which has been included with this
 * distribution in the LICENSE.md file.
 */

import org.mule.modules.clarizen.api.model.WorkItemHierarchyLink

new WorkItemHierarchyLink(
  child: message.getInvocationProperty('newTask'),
  parent: message.getPayload().get(0).getId()
)