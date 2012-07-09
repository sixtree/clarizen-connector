/**
 * Mule Clarizen Cloud Connector
 *
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */

package org.mule.modules.clarizen.api;

import java.util.List;
import java.util.Map;

import org.mule.modules.clarizen.api.model.AllIssueType;
import org.mule.modules.clarizen.api.model.ArrayOfEntity;
import org.mule.modules.clarizen.api.model.Entity;
import org.mule.modules.clarizen.api.model.Login;
import org.mule.modules.clarizen.api.model.Operator;
import org.mule.modules.clarizen.api.model.WorkItemFilter;
import org.mule.modules.clarizen.api.model.WorkItemState;
import org.mule.modules.clarizen.api.model.WorkItemType;

public interface ClarizenClient {

    Entity addWorkItemResources(Entity workItem, String resourceId,
                                           String units);
    
    Entity createAllIssue(AllIssueType issueType, String title);

    Entity createEntity(String entityType, String entityId, 
            Map<String, Object> entityFields);

    ArrayOfEntity createEntityQuery(List<String> fieldsToRetrieve, String queryTypeName, 
            String expression, Operator operator, String conditionValue);
    
    ArrayOfEntity createIssuesQuery(List<String> fieldsToRetrieve, AllIssueType issueType,
            String expression, Operator operator, String conditionValue);

    Entity createWorkItem(Entity parentEntity, WorkItemType workItemType, 
                                 String workItemName, Map<String, Object> workItemFields);
    
    Entity createWorkItemByParentId(WorkItemType parentType, String parentId, 
            WorkItemType workItemType, String workItemName,
            String workItemDescription, String startDate);

    Entity createWorkItemSingleValues(Entity parentEntity,
            WorkItemType workItemType, String workItemName,
            String workItemDescription, String startDate);

    ArrayOfEntity getMyWorkItems(List<String> fieldsToRetrieve,
            WorkItemState workItemState, WorkItemType workItemType,
            WorkItemFilter workItemFilter);

    Entity getWorkItemById(WorkItemType workItemType, String workItemId, List<String> fieldsToRetrieve);
    
    Login login(String username, String password, String applicationId, String partnerId);
    
    void logout();
    
    Entity updateAllIssue(Entity issue, Map<String, Object> fieldsToUpdate);
    
    Entity updateWorkItem(Entity workItem, Map<String, Object> fieldsToUpdate);
    
    Entity updateWorkItemProgress(Entity workItem, Double percentCompleted);

    ArrayOfEntity workItemsQuery(List<String> fieldsToRetrieve, WorkItemState workItemState,
                               WorkItemType workItemType, WorkItemFilter workItemFilter);
}