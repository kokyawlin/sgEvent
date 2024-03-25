package com.nus.sgevent;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;
 
import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import com.nus.sgevent.extservices.JwtUtil;

@Provider
public class AuthenticationFilter implements javax.ws.rs.container.ContainerRequestFilter
{
     
    @Context
    private ResourceInfo resourceInfo;
     
    private static final String AUTHORIZATION_PROPERTY = "Authorization";
    private static final String AUTHENTICATION_SCHEME = "Basic";
    private static final Response ACCESS_DENIED = Response.status(Response.Status.UNAUTHORIZED)
                                                        .entity("You cannot access this resource").build();
    private static final Response ACCESS_FORBIDDEN = Response.status(Response.Status.FORBIDDEN)
                                                        .entity("Access blocked for all users !!").build();
      
    @Override
    public void filter(ContainerRequestContext requestContext)
    {
        Method method = resourceInfo.getResourceMethod();
        //Access allowed for all
        if( ! method.isAnnotationPresent(PermitAll.class))
        {
            //Access denied for all
            if(method.isAnnotationPresent(DenyAll.class))
            {
                requestContext.abortWith(ACCESS_FORBIDDEN);
                return;
            }
              
            //Get request headers
            final MultivaluedMap<String, String> headers = requestContext.getHeaders();
              
            //Fetch authorization header
            final List<String> authorization = headers.get(AUTHORIZATION_PROPERTY);
              
            //If no authorization information present; block access
            if(authorization == null || authorization.isEmpty())
            {
                requestContext.abortWith(ACCESS_DENIED);
                return;
            }
              
            //Get encoded username
            final String tokenstring = authorization.get(0).replaceFirst(AUTHENTICATION_SCHEME + " ", "");
            final String username = JwtUtil.extractUsername(tokenstring);
          
              
            //Verify user access
            if(method.isAnnotationPresent(RolesAllowed.class))
            {
            	UserController UCtrl = new UserController();
                //Is user valid?
                if(!UCtrl.CheckUserName(username))
                {
                    requestContext.abortWith(ACCESS_DENIED);
                    return;
                }
            }
        }
    }
   
    
}