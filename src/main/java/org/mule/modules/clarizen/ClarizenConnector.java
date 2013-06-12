/**
 * Mule Clarizen Cloud Connector
 *
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */

/**
 * This file was automatically generated by the Mule Development Kit
 */
package org.mule.modules.clarizen;

import java.util.List;

import org.mule.api.ConnectionException;
import org.mule.api.annotations.Connect;
import org.mule.api.annotations.ConnectionIdentifier;
import org.mule.api.annotations.Connector;
import org.mule.api.annotations.Disconnect;
import org.mule.api.annotations.InvalidateConnectionOn;
import org.mule.api.annotations.Processor;
import org.mule.api.annotations.ValidateConnection;
import org.mule.api.annotations.display.Password;
import org.mule.api.annotations.display.Placement;
import org.mule.api.annotations.param.ConnectionKey;
import org.mule.api.annotations.param.Default;
import org.mule.api.annotations.param.Optional;

import org.mule.modules.clarizen.api.ClarizenClient;
import org.mule.modules.clarizen.api.ClarizenClientFactory;
import org.mule.modules.clarizen.api.model.AllIssueType;
import org.mule.modules.clarizen.api.model.BaseClarizenEntity;
import org.mule.modules.clarizen.api.model.Login;
import org.mule.modules.clarizen.api.model.WorkItemFilter;
import org.mule.modules.clarizen.api.model.WorkItemState;
import org.mule.modules.clarizen.api.model.WorkItemType;

import com.clarizen.api.AccessType;
import com.clarizen.api.EntityId;
import com.clarizen.api.GetCalendarInfoResult;
import com.clarizen.api.Recipient;
import com.clarizen.api.files.FileInformation;
import com.clarizen.api.metadata.EntityDescription;
import com.clarizen.api.queries.Condition;

/**
 * Clarizen Cloud Connector
 * 
 * Clarizen project management software is a leading global provider of collaborative 
 * online project management that allows businesses to easily manage all of their projects 
 * and resources in a single environment.
 * 
 * For futher information visit http://www.clarizen.com 
 *
 * @author MuleSoft, Inc.
 */
@Connector(name="clarizen", friendlyName = "Clarizen")
public class ClarizenConnector
{

    /**
     * Username
     */
    private String connectionUser;

    /**
     * Password
     */
    private String connectionPassword;
    
    /**
     * Clarizen Partner ID
     */
    private String partnerId;
    
    /**
     * Clarizen Application ID
     */
    private String applicationId;
    
    private String sessionId;

    /**
     * ClarizenClient client. Default ClarizenClientImpl.
     */
    private ClarizenClient clarizenClient;
    
    /**
     * Login to Clarizen
     *
     * @param username           the login user
     * @param password           the login pass
     * @param applicationId      the id of a specific partner application that can be used for licensing purposed
     * @param partnerId          the id of a Clarizen partner
     * 
     * @return {@link Login} Login result with the created user session id
     */
    public Login login(String username, String password, 
                             @Optional String applicationId, @Optional String partnerId) {
        return clarizenClient.login(username, password, applicationId, partnerId);
    }
    
    /**
     * Cleans up an authentication token that was previously created with a call to login
     *
     */
    public void logout() {
        if (clarizenClient != null) {
            clarizenClient.logout();
        }
    }
    
    /**
     * Retrieves a workitem by id.
     * 
     * <p/>
     * {@sample.xml ../../../doc/clarizen-connector.xml.sample clarizen:get-work-item-by-id}
     *
     * @param workItemType      the work item type. For further information about the specific values check {@link WorkItemType}
     * @param workItemId        the work item id
     * @param fieldsToRetrieve  the list of the work item fields to be retrieved. The fields names are the keys of the map
     * 
     * @return Work item with fields indicated through fieldToRetrieve
     */
    @Processor
    @InvalidateConnectionOn(exception = ClarizenSessionTimeoutException.class)
    public BaseClarizenEntity getWorkItemById(WorkItemType workItemType, String workItemId,  
            @Optional @Default("#[payload]") @Placement(group = "Fields") List<String> fieldsToRetrieve) {
        return clarizenClient.getWorkItemById(workItemType, workItemId, fieldsToRetrieve, true);
    }
    
    /**
     * Creates a new Clarizen entity
     * 
     * <p/>
     * {@sample.xml ../../../doc/clarizen-connector.xml.sample clarizen:create-entity}
     *
     * @param entity          Entity to be created
     * 
     * @return Created entity
     */
    @Processor
    @InvalidateConnectionOn(exception = ClarizenSessionTimeoutException.class)
    public BaseClarizenEntity createEntity(@Optional @Default("#[payload]") BaseClarizenEntity entity) {
        return clarizenClient.createEntity(entity);
    }
    
    /**
     * Updates a new Clarizen entity
     * 
     * <p/>
     * {@sample.xml ../../../doc/clarizen-connector.xml.sample clarizen:update-entity}
     *
     * @param entity          Entity to be updated
     * 
     * @return Created entity
     */
    @Processor
    @InvalidateConnectionOn(exception = ClarizenSessionTimeoutException.class)
    public BaseClarizenEntity updateEntity(@Optional @Default("#[payload]") BaseClarizenEntity entity) {
        return clarizenClient.updateEntity(entity);
    }
    
    /**
     * Search for work items
     * 
     * <p/>
     * {@sample.xml ../../../doc/clarizen-connector.xml.sample clarizen:work-items-query}
     *
     * @param fieldsToRetrieve      the fields to be retrieved. The fields names are the keys of the map
     * @param workItemState         the work items state. For further information about the specific values check {@link WorkItemState}
     * @param workItemType          the work item type. For further information about the specific values check {@link WorkItemType}
     * @param workItemFilter        the work items filter. For further information about the specific values check {@link WorkItemFilter}
     * @param pageSize              the number of results to be retrieved per page
     * @param maxNumberOfPages      the maximum number of pages to be retrieved
     * 
     * @return {@link ArrayOfEntity} List of work item results
     */
    @Processor
    @InvalidateConnectionOn(exception = ClarizenSessionTimeoutException.class)
    public List<BaseClarizenEntity> workItemsQuery(@Optional @Default("#[payload]") @Placement(group = "Fields") List<String> fieldsToRetrieve, 
            WorkItemState workItemState, WorkItemType workItemType, WorkItemFilter workItemFilter, 
            @Optional @Default("100") Integer pageSize, @Optional Integer maxNumberOfPages) {
        return clarizenClient.workItemsQuery(fieldsToRetrieve, workItemState, workItemType, 
                workItemFilter, pageSize, maxNumberOfPages, true);
    }
    
    /**
     * Create an entity query
     * 
     * <p/>
     * {@sample.xml ../../../doc/clarizen-connector.xml.sample clarizen:entity-query}
     *
     * @param fieldsToRetrieve      the fields to be retrieved. The fields names are the keys of the map
     * @param queryTypeName         the entity type
     * @param condition             the query condition. For further information about the condition object check {@link Condition}
     * @param pageSize              the number of results to be retrieved per page
     * @param maxNumberOfPages      the maximum number of pages to be retrieved
     * 
     * @return {@link ArrayOfEntity} List of work item results
     */
    @Processor
    @InvalidateConnectionOn(exception = ClarizenSessionTimeoutException.class)
    public List<BaseClarizenEntity> entityQuery(@Placement(group = "Fields") List<String> fieldsToRetrieve, 
            String queryTypeName, @Optional Condition condition, 
            @Optional @Default("100") Integer pageSize, @Optional Integer maxNumberOfPages) {
        return clarizenClient.createEntityQuery(fieldsToRetrieve, queryTypeName, condition, pageSize, 
                maxNumberOfPages, true);
    }
    
    /**
     * Create an issues query
     * 
     * <p/>
     * {@sample.xml ../../../doc/clarizen-connector.xml.sample clarizen:issue-query}
     *
     * @param fieldsToRetrieve      the fields to be retrieved. The fields names are the keys of the map
     * @param issueType             the issue type to be retrieved
     * @param condition             the query condition. For further information about the condition object check {@link Condition}
     * @param pageSize              the number of results to be retrieved per page.
     * @param maxNumberOfPages      the maximum number of pages to be retrieved
     * @return {@link ArrayOfEntity} List of issues results
     */
    @Processor
    @InvalidateConnectionOn(exception = ClarizenSessionTimeoutException.class)
    public List<BaseClarizenEntity> issueQuery(@Placement(group = "Fields") List<String> fieldsToRetrieve, AllIssueType issueType,
            @Optional Condition condition, 
            @Optional @Default("100") Integer pageSize, @Optional Integer maxNumberOfPages) {
        return clarizenClient.createIssuesQuery(fieldsToRetrieve, issueType, condition, pageSize, 
                maxNumberOfPages, true);
    }

    /**
     * Returns the work items for the current user
     * 
     * <p/>
     * {@sample.xml ../../../doc/clarizen-connector.xml.sample clarizen:get-my-work-items}
     *
     * @param fieldsToRetrieve      the fields to be retrieved. The fields names are the keys of the map
     * @param workItemState         the work items state. For further information about the specific values check {@link WorkItemState}
     * @param workItemType          the work item type. For further information about the specific values check {@link WorkItemType}
     * @param workItemFilter        the work items filter. For further information about the specific values check {@link WorkItemFilter} 
     * @param pageSize              the number of results to be retrieved per page
     * @param maxNumberOfPages      the maximum number of pages to be retrieved
     * @return {@link ArrayOfEntity} List of work items
     */
    @Processor
    @InvalidateConnectionOn(exception = ClarizenSessionTimeoutException.class)
    public List<BaseClarizenEntity> getMyWorkItems(@Optional @Default("#[payload]") @Placement(group = "Fields") List<String> fieldsToRetrieve,
            WorkItemState workItemState, WorkItemType workItemType,
            WorkItemFilter workItemFilter, @Optional @Default("100") Integer pageSize, 
            @Optional Integer maxNumberOfPages) {

        return clarizenClient.getMyWorkItems(fieldsToRetrieve, workItemState, workItemType, 
                workItemFilter, pageSize, maxNumberOfPages, true);
    }

    /**
     * Returns the description of an entity
     * 
     * <p/>
     * {@sample.xml ../../../doc/clarizen-connector.xml.sample clarizen:describe-entities}
     *
     * @param typeNames          list of entity types to be described
     * @return {@link EntityDescription} Entity description
     */
    @Processor
    @InvalidateConnectionOn(exception = ClarizenSessionTimeoutException.class)
    public List<EntityDescription> describeEntities(@Optional @Default("#[payload]") List<String> typeNames) {
        return clarizenClient.describeEntities(typeNames);
    }
    
    /**
     * Returns the list of entities
     * 
     * <p/>
     * {@sample.xml ../../../doc/clarizen-connector.xml.sample clarizen:list-entities}
     *
     * @return List of entities
     */
    @Processor
    @InvalidateConnectionOn(exception = ClarizenSessionTimeoutException.class)
    public List<String> listEntities() {
        return clarizenClient.listEntities();
    }
    
    /**
     * Performs lifecycle changes on an entity or a group of entities
     * 
     * <p/>
     * {@sample.xml ../../../doc/clarizen-connector.xml.sample clarizen:lifecycle-change}
     * @param entityIdList the list of entities to be updated
     * @param operation the operation to be performed
     * @param recursive if the operation will be recursived
     * @return true if the change was successful
     */
    @Processor
    @InvalidateConnectionOn(exception = ClarizenSessionTimeoutException.class)
    public Boolean lifecycleChange(@Optional @Default("#[payload]") List<EntityId> entityIdList, 
            String operation, Boolean recursive) {
        return clarizenClient.lifecycleChange(entityIdList, operation, recursive);
    }
    
    /**
     * Deletes an entity
     * 
     * <p/>
     * {@sample.xml ../../../doc/clarizen-connector.xml.sample clarizen:delete-entity}
     *
     * @param entity the entity to be deleted
     * @return true if the entity was successfully deleted
     */
    @Processor
    @InvalidateConnectionOn(exception = ClarizenSessionTimeoutException.class)
    public Boolean deleteEntity(@Optional @Default("#[payload]") BaseClarizenEntity entity) {
        return clarizenClient.deleteEntity(entity);
    }
    
    /**
     * Gets calendar information for the specified user
     * 
     * <p/>
     * {@sample.xml ../../../doc/clarizen-connector.xml.sample clarizen:get-calendar-information}
     *
     * @param userId the userId entityId
     * @return calendar information
     */
    @Processor
    @InvalidateConnectionOn(exception = ClarizenSessionTimeoutException.class)
    public GetCalendarInfoResult getCalendarInformation(@Optional @Default("#[payload]") EntityId userId) {
        return clarizenClient.getCalendarInfo(userId);
    }
    
    /**
     * Gets system settings values
     * 
     * <p/>
     * {@sample.xml ../../../doc/clarizen-connector.xml.sample clarizen:get-system-settings}
     *
     * @param settings list
     * @return system settings
     */
    @Processor
    @InvalidateConnectionOn(exception = ClarizenSessionTimeoutException.class)
    public List<Object> getSystemSettings(@Optional @Default("#[payload]") List<String> settings) {
        return clarizenClient.getSystemSettings(settings);
    }
    
    /**
     * Creates milestones and projects from templates
     * 
     * <p/>
     * {@sample.xml ../../../doc/clarizen-connector.xml.sample clarizen:create-from-template}
     *
     * @param templateName template to be used
     * @param entity entity to be created from template
     * @return created entity
     */
    @Processor
    @InvalidateConnectionOn(exception = ClarizenSessionTimeoutException.class)
    public BaseClarizenEntity createFromTemplate(String templateName, @Optional @Default("#[payload]") BaseClarizenEntity entity) {
        return clarizenClient.createFromTemplate(templateName, entity);
    }

    /**
     * Download file information
     * 
     * <p/>
     * {@sample.xml ../../../doc/clarizen-connector.xml.sample clarizen:download-file-information}
     *
     * @param entity Document entity
     * @return entity file information
     */
    @Processor
    @InvalidateConnectionOn(exception = ClarizenSessionTimeoutException.class)
    public FileInformation downloadFileInformation(@Optional @Default("#[payload]") BaseClarizenEntity entity) {
        return clarizenClient.downloadFileInformation(entity);
    }
    
    /**
     * Sends an email to the specified recipients
     * 
     * <p/>
     * {@sample.xml ../../../doc/clarizen-connector.xml.sample clarizen:send-email}
     *
     * @param accessType PUBLIC or PRIVATE
     * @param body message body
     * @param subject message subject
     * @param recipients list of recipients
     * @param relatedEntity entity related to the email
     * @return true if the action was successful
     */
    @Processor
    @InvalidateConnectionOn(exception = ClarizenSessionTimeoutException.class)
    public Boolean sendEmail(AccessType accessType, @Optional BaseClarizenEntity relatedEntity, 
            @Optional String body, @Optional String subject, @Optional List<Recipient> recipients) {
        return clarizenClient.sendEmail(accessType, body, subject, recipients, relatedEntity);
    }
    
    public ClarizenClient getClarizenClient() {
        return clarizenClient;
    }

    public void setClarizenClient(ClarizenClient clarizenClient) {
        this.clarizenClient = clarizenClient;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getConnectionUser() {
        return connectionUser;
    }

    public void setConnectionUser(String connectionUser) {
        this.connectionUser = connectionUser;
    }

    public String getConnectionPassword() {
        return connectionPassword;
    }

    public void setConnectionPassword(String connectionPassword) {
        this.connectionPassword = connectionPassword;
    }

    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }
    
    /**
     * Performs a connection to Clarizen by making a login call with the given credentials.
     * 
     * @param connectionUser     the user login user
     * @param connectionPassword the user login pass
     * @param applicationId      the id of a specific partner application that can be used for licensing purposed
     * @param partnerId          the id of a Clarizen partner
     * 
     */
    @Connect
    public void connect(@ConnectionKey String connectionUser, 
                        @Password String connectionPassword, 
                        @Optional String applicationId, 
                        @Optional String partnerId) throws ConnectionException {
        this.connectionUser = connectionUser;
        this.connectionPassword = connectionPassword;
        
        if (clarizenClient == null) {
            setClarizenClient(ClarizenClientFactory.getClient());
        }
        
        setSessionId(login(connectionUser, connectionPassword, applicationId, partnerId).getLoginResult().getSessionId());
    }

    /**
     * Performs a logout call.
     */
    @Disconnect
    public void disconnect() {
        if (sessionId != null) {
            sessionId = null;
            logout();
            clarizenClient = null;
        }
    }

    /**
     * Returns whether the current user is authenticated.
     * It does not mean tell anything whether the current session has expired
     */
    @ValidateConnection
    public boolean validateConnection() {
        return sessionId != null;
    }

    /**
     * Returns a connection identifier.
     */
    @Override
    @ConnectionIdentifier
    public String toString() {
        return "{username='" + connectionUser + "'}";
    }
}
