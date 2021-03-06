/**
 * Mule Clarizen Cloud Connector
 *
 * (c) 2003-2014 MuleSoft, Inc. The software in this package is published under
 * the terms of the CPAL v1.0 license, a copy of which has been included with this
 * distribution in the LICENSE.md file.
 */

package org.mule.modules.clarizen.api;

import java.util.List;

import com.clarizen.api.*;
import org.mule.modules.clarizen.api.model.AllIssueType;
import org.mule.modules.clarizen.api.model.BaseClarizenEntity;
import org.mule.modules.clarizen.api.model.Login;
import org.mule.modules.clarizen.api.model.WorkItemFilter;
import org.mule.modules.clarizen.api.model.WorkItemState;
import org.mule.modules.clarizen.api.model.WorkItemType;

import com.clarizen.api.files.FileInformation;
import com.clarizen.api.metadata.EntityDescription;
import com.clarizen.api.queries.Condition;

public interface ClarizenClient {

    /**
     * Creates an entity
     * @param entity model class extending BaseClarizenClient
     * @return created entity
     */
    BaseClarizenEntity createEntity(BaseClarizenEntity entity);

    /**
     * Creates a generic entity
     * @param entity model class extending GenericEntity
     * @return created entity
     */
    GenericEntity createEntity(GenericEntity entity);
    
    /**
     * Creates an entity query
     * 
     * @param fieldsToRetrieve
     * @param queryTypeName
     * @param condition
     * @param pageSize
     * @param maxNumberOfPages
     * @param useFlatClasses use flat model classes org.mule.modules.clarizen.api.model.flat
     * @return a list of entities taking into account depending on the parameter queryTypeName
     */
    <T extends BaseClarizenEntity> List<T> createEntityQuery(List<String> fieldsToRetrieve, String queryTypeName, 
            Condition condition, Integer pageSize, Integer maxNumberOfPages, boolean useFlatClasses);
    
    /**
     * Creates milestones and projects from templates
     * @param templateName
     * @param entity
     * @return created entity
     */
    BaseClarizenEntity createFromTemplate(String templateName, BaseClarizenEntity entity);
    
    /**
     * Creates an issue query
     * @param fieldsToRetrieve
     * @param issueType
     * @param condition
     * @param pageSize
     * @param maxNumberOfPages
     * @param useFlatClasses use flat model classes org.mule.modules.clarizen.api.model.flat
     * @return a list of entities taking into account depending on the parameter issueType
     */
    <T extends BaseClarizenEntity> List<T> createIssuesQuery(List<String> fieldsToRetrieve, AllIssueType issueType,
            Condition condition, Integer pageSize, Integer maxNumberOfPages, boolean useFlatClasses);

    /**
     * Deletes en entity
     * @param entity to be deleted
     * @return true if the entity was successfully deleted
     */
    Boolean deleteEntity(BaseClarizenEntity entity);

    /**
     * Deletes en entity, defined by its entity Id
     * @param entityId identifies the Entity to delete
     * @return true if the entity was successfully deleted
     */
    Boolean deleteEntity(EntityId entityId);
    
    /**
     * Download file information
     * @param entity to be downloaded
     * @return File information
     */
    FileInformation downloadFileInformation(BaseClarizenEntity entity);

    /**
     * Describes an entity querying the metadata information provided by the webservice
     * @param typeName
     * @return entityDescription object
     */
    List<EntityDescription> describeEntities(List<String> typeNames);

    /**
     * Get Calendar Information
     * @param userId
     * @return calendar information
     */
    GetCalendarInfoResult getCalendarInfo(EntityId userId);
    
    /**
     * Gets System settings
     * @param settings list
     * @return system settings values
     */
    List<Object> getSystemSettings(List<String> settingList);
    
    /**
     * Query for current user's workitems
     * @param fieldsToRetrieve
     * @param workItemState
     * @param workItemType
     * @param workItemFilter
     * @param pageSize
     * @param maxNumberOfPages
     * @param useFlatClasses
     * @return list containing the workitems objects
     */
    <T extends BaseClarizenEntity> List<T> getMyWorkItems(List<String> fieldsToRetrieve,
            WorkItemState workItemState, WorkItemType workItemType,
            WorkItemFilter workItemFilter, Integer pageSize, Integer maxNumberOfPages,
            Boolean useFlatClasses);
    
    /**
     * Gets workItem information by id
     * @param workItemType
     * @param workItemId
     * @param fieldsToRetrieve
     * @param useFlatClasses use flat model classes org.mule.modules.clarizen.api.model.flat
     * @return GenericEntity
     */
    BaseClarizenEntity getWorkItemById(WorkItemType workItemType, String workItemId, 
            List<String> fieldsToRetrieve, boolean useFlatClasses);

    /**
     * Changes the state of an entity
     * @param entityIdList list of entityIds to be updated
     * @param operation lifecycle change
     * @param recursive
     * @return true if the change was successful
     */
    Boolean lifecycleChange(List<EntityId> entityIdList, String operation, Boolean recursive);

    /**
     * Queries metadata information provided by the webservice for available entities
     * @return list of entities names
     */
    List<String> listEntities();
    
    /**
     * Login to the webservice
     * @param username
     * @param password
     * @param applicationId
     * @param partnerId
     * @return Login information including session id
     */
    Login login(String username, String password, String applicationId, String partnerId);
    
    /**
     * Logout
     */
    void logout();

    /**
     * Sends an email
     * @param accessType PUBLIC or PRIVATE
     * @param body
     * @param subject
     * @param recipients
     * @param relatedEntity
     * @return true if the action was successful
     */
    Boolean sendEmail(AccessType accessType, String body, String subject, List<Recipient> recipients, 
            BaseClarizenEntity relatedEntity);

    /**
     * Attaches a file to an entity, given that the file resides in an external storage and can be accessed via a URL.
     * @param entityId ID of the entity to which the file URL will be attached
     * @param attachmentUrl URL pointing to the attachment
     * @param attachmentFilename Filename of the attachment
     * @return true if the action was successful
     */
    Boolean attachFileUrlToEntity(EntityId entityId, String attachmentUrl, String attachmentFilename);

    /**
     * Given a certain Entity Id, retrieves all associated attachments.
     *
     * @param entityId The entity Id associated with the attachments
     * @return The list containing all the attachments.
     */
    List<FileInformation> downloadEntityAttachments(EntityId entityId);

    /**
     * Given a certain Entity Id representing a work item, retrieves all associated human resources.
     *
     * @param entityId The entity Id associated with a work item
     * @param fieldsToRetrieve
     * @return The list containing all the human resources.
     */
    List<GenericEntity> retrieveWorkItemHumanResources(EntityId entityId, List<String> fieldsToRetrieve);

    /**
     * Updates an entity
     * @param entity model class extending BaseClarizenClient
     * @return updated entity
     */
    BaseClarizenEntity updateEntity(BaseClarizenEntity entity);

    /**
     * Updates an entity
     * @param entity model class extending GenericEntity
     * @return updated entity
     */
    GenericEntity updateEntity(GenericEntity entity);
    
    /**
     * Queries workItems
     * @param fieldsToRetrieve
     * @param workItemState
     * @param workItemType
     * @param workItemFilter
     * @param pageSize
     * @param maxNumberOfPages
     * @param useFlatClasses
     * @return list containing the workitems objects
     */
    <T extends BaseClarizenEntity> List<T> workItemsQuery(List<String> fieldsToRetrieve, 
                               WorkItemState workItemState, WorkItemType workItemType, 
                               WorkItemFilter workItemFilter, 
                               Integer pageSize, Integer maxNumberOfPages, Boolean useFlatClasses);
}