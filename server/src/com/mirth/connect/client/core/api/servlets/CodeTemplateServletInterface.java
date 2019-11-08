/*
 * Copyright (c) Mirth Corporation. All rights reserved.
 * 
 * http://www.mirthcorp.com
 * 
 * The software in this package is published under the terms of the MPL license a copy of which has
 * been included with this distribution in the LICENSE.txt file.
 */

package com.mirth.connect.client.core.api.servlets;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.media.multipart.FormDataParam;

import com.mirth.connect.client.core.ClientException;
import com.mirth.connect.client.core.Operation.ExecuteType;
import com.mirth.connect.client.core.Permissions;
import com.mirth.connect.client.core.api.BaseServletInterface;
import com.mirth.connect.client.core.api.MirthOperation;
import com.mirth.connect.client.core.api.Param;
import com.mirth.connect.model.codetemplates.CodeTemplate;
import com.mirth.connect.model.codetemplates.CodeTemplateLibrary;
import com.mirth.connect.model.codetemplates.CodeTemplateLibrarySaveResult;
import com.mirth.connect.model.codetemplates.CodeTemplateSummary;

@Path("/")

@Consumes(MediaType.APPLICATION_XML)
@Produces(MediaType.APPLICATION_XML)
public interface CodeTemplateServletInterface extends BaseServletInterface {

    @GET
    @Path("/codeTemplateLibraries")
    @Operation(summary="Retrieves multiple code template libraries by ID, or all libraries if not specified.")
    @MirthOperation(name = "getCodeTemplateLibraries", display = "Get code template libraries", permission = Permissions.CODE_TEMPLATES_VIEW, type = ExecuteType.ASYNC, auditable = false)
    public List<CodeTemplateLibrary> getCodeTemplateLibraries(// @formatter:off
            @Param("libraryIds") @Parameter(description = "The ID of the library(s) to retrieve.") @QueryParam("libraryId") Set<String> libraryIds,
            @Param("includeCodeTemplates") @Parameter(description = "If true, full code templates will be included inside each library.") @QueryParam("includeCodeTemplates") boolean includeCodeTemplates) throws ClientException;
    // @formatter:on

    @POST
    @Path("/codeTemplateLibraries/_getCodeTemplateLibraries")
    @Operation(summary="Retrieves multiple code template libraries by ID, or all libraries if not specified. This is a POST request alternative to GET /codeTemplateLibraries that may be used when there are too many library IDs to include in the query parameters.")
    @MirthOperation(name = "getCodeTemplateLibraries", display = "Get code template libraries", permission = Permissions.CODE_TEMPLATES_VIEW, type = ExecuteType.ASYNC, auditable = false)
    public List<CodeTemplateLibrary> getCodeTemplateLibrariesPost(// @formatter:off
            @Param("libraryIds") @Parameter(description = "The ID of the library(s) to retrieve.") Set<String> libraryIds,
            @Param("includeCodeTemplates") @Parameter(description = "If true, full code templates will be included inside each library.") @QueryParam("includeCodeTemplates") boolean includeCodeTemplates) throws ClientException;
    // @formatter:on

    @GET
    @Path("/codeTemplateLibraries/{libraryId}")
    @Operation(summary="Retrieves a single code template library.")
    @MirthOperation(name = "getCodeTemplateLibrary", display = "Get code template library", permission = Permissions.CODE_TEMPLATES_VIEW, type = ExecuteType.ASYNC)
    public CodeTemplateLibrary getCodeTemplateLibrary(// @formatter:off
            @Param("libraryId") @Parameter(description = "The ID of the library to retrieve.") @PathParam("libraryId") String libraryId,
            @Param("includeCodeTemplates") @Parameter(description = "If true, full code templates will be included inside each library.") @QueryParam("includeCodeTemplates") boolean includeCodeTemplates) throws ClientException;
    // @formatter:on

    @PUT
    @Path("/codeTemplateLibraries")
    @Operation(summary="Replaces all code template libraries.")
    @MirthOperation(name = "updateCodeTemplateLibraries", display = "Update code template libraries", permission = Permissions.CODE_TEMPLATES_MANAGE)
    public boolean updateCodeTemplateLibraries(// @formatter:off
            @Param("libraries") @Parameter(description = "The list of code template libraries to replace with.", required = true) List<CodeTemplateLibrary> libraries,
            @Param("override") @Parameter(description = "If true, the code template library will be updated even if a different revision exists on the server.") @QueryParam("override") boolean override) throws ClientException;
    // @formatter:on

    @GET
    @Path("/codeTemplates")
    @Operation(summary="Retrieves multiple code templates by ID, or all templates if not specified.")
    @MirthOperation(name = "getCodeTemplates", display = "Get code templates", permission = Permissions.CODE_TEMPLATES_VIEW)
    public List<CodeTemplate> getCodeTemplates(@Param("codeTemplateIds") @Parameter(description = "The ID of the code template(s) to retrieve.") @QueryParam("codeTemplateId") Set<String> codeTemplateIds) throws ClientException;

    @POST
    @Path("/codeTemplates/_getCodeTemplates")
    @Operation(summary="Retrieves multiple code templates by ID, or all templates if not specified. This is a POST request alternative to GET /codeTemplates that may be used when there are too many code template IDs to include in the query parameters.")
    @MirthOperation(name = "getCodeTemplates", display = "Get code templates", permission = Permissions.CODE_TEMPLATES_VIEW)
    public List<CodeTemplate> getCodeTemplatesPost(@Param("codeTemplateIds") @Parameter(description = "The ID of the code template(s) to retrieve.") Set<String> codeTemplateIds) throws ClientException;

    @GET
    @Path("/codeTemplates/{codeTemplateId}")
    @Operation(summary="Retrieves a single code template.")
    @MirthOperation(name = "getCodeTemplates", display = "Get code templates", permission = Permissions.CODE_TEMPLATES_VIEW)
    public CodeTemplate getCodeTemplate(@Param("codeTemplateId") @Parameter(description = "The ID of the code template to retrieve.") @PathParam("codeTemplateId") String codeTemplateId) throws ClientException;

    @POST
    @Path("/codeTemplates/_getSummary")
    @Operation(summary="Returns a list of code template summaries, indicating to a client which code templates have changed. If a code template was modified, the entire CodeTemplate object will be returned.")
    @MirthOperation(name = "getCodeTemplateSummary", display = "Get code template summary", permission = Permissions.CODE_TEMPLATES_VIEW, type = ExecuteType.ASYNC, auditable = false)
    public List<CodeTemplateSummary> getCodeTemplateSummary(@Param("clientRevisions") @Parameter(description = "A map of revisions telling the server the state of the client-side code template cache.", required = true) Map<String, Integer> clientRevisions) throws ClientException;

    @PUT
    @Path("/codeTemplates/{codeTemplateId}")
    @Operation(summary="Updates a single code template.")
    @MirthOperation(name = "updateCodeTemplate", display = "Update code template", permission = Permissions.CODE_TEMPLATES_MANAGE)
    public boolean updateCodeTemplate(// @formatter:off
            @Param("codeTemplateId") @Parameter(description = "The ID of the code template.", required = true) @PathParam("codeTemplateId") String codeTemplateId,
            @Param("codeTemplate") @Parameter(description = "The CodeTemplate object to update with.", required = true) CodeTemplate codeTemplate,
            @Param("override") @Parameter(description = "If true, the code template will be updated even if a different revision exists on the server.") @QueryParam("override") boolean override) throws ClientException;
    // @formatter:on

    @DELETE
    @Path("/codeTemplates/{codeTemplateId}")
    @Operation(summary="Removes a single code template.")
    @MirthOperation(name = "removeCodeTemplate", display = "Remove code template", permission = Permissions.CODE_TEMPLATES_MANAGE)
    public void removeCodeTemplate(@Param("codeTemplateId") @Parameter(description = "The ID of the code template.", required = true) @PathParam("codeTemplateId") String codeTemplateId) throws ClientException;

    @POST
    @Path("/codeTemplateLibraries/_bulkUpdate")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Operation(summary="Updates all libraries and updates/removes selected code templates in one request.")
    @MirthOperation(name = "updateCodeTemplatesAndLibraries", display = "Update code templates and libraries", permission = Permissions.CODE_TEMPLATES_MANAGE)
    public CodeTemplateLibrarySaveResult updateLibrariesAndTemplates(// @formatter:off
            @Param("libraries") @Parameter(description = "The set of code template libraries to replace with.") @FormDataParam("libraries") List<CodeTemplateLibrary> libraries,
            @Param("removedLibraryIds") @Parameter(description = "All library IDs known to be removed.") @FormDataParam("removedLibraryIds") Set<String> removedLibraryIds,
            @Param("updatedCodeTemplates") @Parameter(description = "The set of code templates to update.") @FormDataParam("updatedCodeTemplates") List<CodeTemplate> updatedCodeTemplates,
            @Param("removedCodeTemplateIds") @Parameter(description = "All code template IDs known to be removed.") @FormDataParam("removedCodeTemplateIds") Set<String> removedCodeTemplateIds,
            @Param("override") @Parameter(description = "If true, the libraries and code templates will be updated even if different revisions exist on the server.") @QueryParam("override") boolean override) throws ClientException;
    // @formatter:on
}