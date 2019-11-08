/*
 * Copyright (c) Mirth Corporation. All rights reserved.
 * 
 * http://www.mirthcorp.com
 * 
 * The software in this package is published under the terms of the MPL license a copy of which has
 * been included with this distribution in the LICENSE.txt file.
 */

package com.mirth.connect.plugins.directoryresource;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.mirth.connect.client.core.ClientException;
import com.mirth.connect.client.core.Operation.ExecuteType;
import com.mirth.connect.client.core.api.BaseServletInterface;
import com.mirth.connect.client.core.api.MirthOperation;
import com.mirth.connect.client.core.api.Param;

@Path("/extensions/directoryresource")

@Consumes(MediaType.APPLICATION_XML)
@Produces(MediaType.APPLICATION_XML)
public interface DirectoryResourceServletInterface extends BaseServletInterface {

    public static final String PLUGIN_POINT = DirectoryResourceProperties.PLUGIN_POINT;

    @GET
    @Path("/resources/{resourceId}/libraries")
    @Operation(summary="Retrieves all library URLs for the given directory resource.")
    @MirthOperation(name = "getLibraries", display = "Get libraries", type = ExecuteType.ASYNC)
    public List<String> getLibraries(@Param("resourceId") @Parameter(description = "The ID of the directory resource.", required = true) @PathParam("resourceId") String resourceId) throws ClientException;
}