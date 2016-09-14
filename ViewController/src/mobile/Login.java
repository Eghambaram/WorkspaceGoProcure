package mobile;

import oracle.adfmf.amx.event.ActionEvent;
import java.io.InputStreamReader;

import java.io.PrintWriter;
import java.io.StringWriter;

import java.net.HttpURLConnection;
import java.net.URL;

import java.net.URLEncoder;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import java.util.Random;

import java.util.concurrent.TimeUnit;

import java.util.logging.Level;

import javax.el.ValueExpression;

import oracle.adfmf.amx.event.ActionEvent;
import oracle.adfmf.amx.event.ValueChangeEvent;
import oracle.adfmf.bindings.DataControl;
import oracle.adfmf.bindings.dbf.AmxAttributeBinding;
import oracle.adfmf.bindings.dbf.AmxIteratorBinding;
import oracle.adfmf.bindings.iterator.BasicIterator;
import oracle.adfmf.dc.ws.rest.RestServiceAdapter;
import oracle.adfmf.framework.FeatureContext;
import oracle.adfmf.framework.api.AdfmfContainerUtilities;
import oracle.adfmf.framework.api.AdfmfJavaUtilities;
import oracle.adfmf.framework.api.GenericTypeBeanSerializationHelper;
import oracle.adfmf.framework.api.Model;
import oracle.adfmf.framework.exception.AdfException;

import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;
import oracle.adfmf.java.beans.ProviderChangeSupport;

import oracle.adfmf.util.GenericType;

import oracle.adfmf.util.Utility;
import oracle.adfmf.util.logging.Trace;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Login {
    public Login() {
    }

    
    public void keyboardLogin(String password) {
       
        ValueExpression ve3 = AdfmfJavaUtilities.getValueExpression("#{applicationScope.password}", String.class);
        ve3.setValue(AdfmfJavaUtilities.getAdfELContext(),password);
        
       
        System.out.println("password:::" + password+"-------"+ve3);
        
        // UserName and Password Validation
        
        try{
            List deliverToLocationList=new ArrayList();
            List costCenterList=new ArrayList();
        ValueExpression ve = AdfmfJavaUtilities.getValueExpression("#{applicationScope.user_name}", String.class);
        String userName = (String)ve.getValue(AdfmfJavaUtilities.getAdfELContext());
        userName=userName.trim();
        
      /*  ValueExpression ve1 = AdfmfJavaUtilities.getValueExpression("#{applicationScope.password}", String.class);
        String password = (String)ve1.getValue(AdfmfJavaUtilities.getAdfELContext());*/
        
        RestServiceAdapter restServiceAdapter = Model.createRestServiceAdapter();
        // Clear any previously set request properties, if any
        restServiceAdapter.clearRequestProperties();
        // Set the connection name
        restServiceAdapter.setConnectionName("enrich");
        
        restServiceAdapter.setRequestType(RestServiceAdapter.REQUEST_TYPE_POST);
        restServiceAdapter.addRequestProperty("Accept", "application/json; charset=UTF-8");
        restServiceAdapter.addRequestProperty("Authorization", "Basic " + "WFhFX1JFU1RfU0VSVklDRVNfQURNSU46b3JhY2xlMTIz");
        restServiceAdapter.addRequestProperty("Content-Type", "application/json");
        restServiceAdapter.setRequestURI("/webservices/rest/XXETailSpendAPI/validate_user_login/");
        String postData= "{\n" + 
        "  \"VALIDATE_USER_LOGIN_Input\" : {\n" + 
        "   \"@xmlns\" : \"http://xmlns.oracle.com/apps/po/rest/XXETailSpendAPI/validate_user_login/\",\n" + 
        "   \"RESTHeader\": {\n" + 
        "   \"@xmlns\" : \"http://xmlns.oracle.com/apps/po/rest/XXETailSpendAPI/header\"\n" + 
        "    },\n" + 
        "   \"InputParameters\": {\n" + 
        "                  \"P_USER\":\""+userName+"\",\n" + 
        "                  \"P_PWD\":\""+password+"\"\n" + 
        "       }          \n" + 
        "   }\n" + 
        "}\n";
                                    restServiceAdapter.setRetryLimit(0);
           System.out.println("postData===============================" + postData);
            
           String response = restServiceAdapter.send(postData);
            System.out.println("response===============================" + response);   
            JSONObject resp=new JSONObject(response);
            JSONObject output=resp.getJSONObject("OutputParameters");
             String status=output.getString("X_RESULT");
        
             if(status.equalsIgnoreCase("Y")) {
                 
                 
                 
                 String user_id=output.getString("X_USER_ID");
                 String org_id=output.getString("X_ORG_ID");
                 
                 //Get User Fisrt/Last Name
                 String user_firstName=output.getString("X_USER_FIRST_NAME");
                 String user_lastName=output.getString("X_USER_FIRST_NAME");
                 
                 
                 ValueExpression ve12 = AdfmfJavaUtilities.getValueExpression("#{applicationScope.user_name}", String.class);
                 ve12.setValue(AdfmfJavaUtilities.getAdfELContext(),userName);
                 
                 ValueExpression ve13 = AdfmfJavaUtilities.getValueExpression("#{applicationScope.user_id}", String.class);
                 ve13.setValue(AdfmfJavaUtilities.getAdfELContext(),user_id);
               
                 ValueExpression ove13 = AdfmfJavaUtilities.getValueExpression("#{applicationScope.org_id}", String.class);
                 ove13.setValue(AdfmfJavaUtilities.getAdfELContext(),org_id);
                 
                 ValueExpression fve13 = AdfmfJavaUtilities.getValueExpression("#{applicationScope.user_firstName}", String.class);
                 fve13.setValue(AdfmfJavaUtilities.getAdfELContext(),user_firstName);
                 
                 ValueExpression lve13 = AdfmfJavaUtilities.getValueExpression("#{applicationScope.user_lastName}", String.class);
                 lve13.setValue(AdfmfJavaUtilities.getAdfELContext(),user_lastName);
                 
                 ValueExpression ve14 = AdfmfJavaUtilities.getValueExpression("#{deviceScope.device.uuid}", String.class);
                 String device_serial_id=(String)ve14.getValue(AdfmfJavaUtilities.getAdfELContext());
                 
                 ValueExpression ve15 = AdfmfJavaUtilities.getValueExpression("#{deviceScope.device.platform}", String.class);
                 String device_type=(String)ve15.getValue(AdfmfJavaUtilities.getAdfELContext());
                 
                 ValueExpression ve16 = AdfmfJavaUtilities.getValueExpression("#{deviceScope.device.model}", String.class);
                 String device_model=(String)ve16.getValue(AdfmfJavaUtilities.getAdfELContext());
                 
                 
                 ValueExpression ve17 = AdfmfJavaUtilities.getValueExpression("#{applicationScope.deviceToken}", String.class);
                 String device_token=(String)ve17.getValue(AdfmfJavaUtilities.getAdfELContext());
                 
                 
                 ValueExpression ve18 = AdfmfJavaUtilities.getValueExpression("#{applicationScope.default_cost_center}", String.class);
                 ve18.setValue(AdfmfJavaUtilities.getAdfELContext(),"");
                 
                 ValueExpression ve19 = AdfmfJavaUtilities.getValueExpression("#{applicationScope.default_deliver_to}", String.class);
                 ve19.setValue(AdfmfJavaUtilities.getAdfELContext(),"");
                 
                 //get deliver to list
                 restServiceAdapter = Model.createRestServiceAdapter();
                 // Clear any previously set request properties, if any
                 restServiceAdapter.clearRequestProperties();
                 // Set the connection name
                 restServiceAdapter.setConnectionName("enrich");
                 
                 restServiceAdapter.setRequestType(RestServiceAdapter.REQUEST_TYPE_POST);
                 restServiceAdapter.addRequestProperty("Accept", "application/json; charset=UTF-8");
                 restServiceAdapter.addRequestProperty("Authorization", "Basic " + "WFhFX1JFU1RfU0VSVklDRVNfQURNSU46b3JhY2xlMTIz");
                 restServiceAdapter.addRequestProperty("Content-Type", "application/json");
                 restServiceAdapter.setRequestURI("/webservices/rest/XXETailSpendAPI/get_deliver_to/");
                 postData= "{\n" + 
                 "\n" + 
                 "  \"GET_DELIVER_TO_Input\" : {\n" + 
                 "\n" + 
                 "   \"@xmlns\" : \"http://xmlns.oracle.com/apps/po/rest/XXETailSpendAPI/get_deliver_to/\",\n" + 
                 "\n" + 
                 "   \"RESTHeader\": {\n" + 
                 "\n" + 
                 "   \"@xmlns\" : \"http://xmlns.oracle.com/apps/po/rest/XXETailSpendAPI/header\"\n" + 
                 "    },\n" + 
                 "\n" + 
                 "   \"InputParameters\": {\n" + 
                 "\n" + 
                 "        \"P_USER_ID\":"+user_id+"\n" + 
                 "\n" + 
                 "     }\n" + 
                 "\n" + 
                 "  }\n" + 
                 "\n" + 
                 "}  ";
                                             restServiceAdapter.setRetryLimit(0);
                    System.out.println("postData===============================" + postData);
                     
                    response = restServiceAdapter.send(postData);
                     
                     System.out.println("response===============================" + response); 
                      resp=new JSONObject(response);
                      output=resp.getJSONObject("OutputParameters");
                    JSONObject data=new JSONObject();
                  try{
                      data=output.getJSONObject("X_DELIVER_TO_TL");
                     DeliverToLocationList.s_jobs.clear();
                         deliverToLocationList.clear();
                     if(data.get("X_DELIVER_TO_TL_ITEM") instanceof  JSONArray){
                       JSONArray segments=data.getJSONArray("X_DELIVER_TO_TL_ITEM");
                       for(int i=0;i<segments.length();i++) {
                         JSONObject location=segments.getJSONObject(i);
                         String locationId=location.getString("LOCATION_ID");
                         String locationCode=location.getString("LOCATION_CODE");
                         String locationDescription=location.getString("DESCRIPTION");
                         DeliverToLocation loc=new DeliverToLocation(locationId, locationCode, locationDescription);
                         DeliverToLocationList.s_jobs.add(loc);
                           deliverToLocationList.add(loc);
                       }
                     
                     }
                     
                     else if(data.get("X_DELIVER_TO_TL_ITEM") instanceof  JSONObject){
                        
                        JSONObject location=data.getJSONObject("X_DELIVER_TO_TL_ITEM");
                         String locationId=location.getString("LOCATION_ID");
                         String locationCode=location.getString("LOCATION_CODE");
                         String locationDescription=location.getString("DESCRIPTION");
                         DeliverToLocation loc=new DeliverToLocation(locationId, locationCode, locationDescription);
                         DeliverToLocationList.s_jobs.add(loc);
                         deliverToLocationList.add(loc);
                     }
                     }
                     catch(Exception e) {
                         e.printStackTrace();
                     }
                 
                 //get cost center list
                  
                 //Cost center     
                 
                 restServiceAdapter = Model.createRestServiceAdapter();
                 // Clear any previously set request properties, if any
                 restServiceAdapter.clearRequestProperties();
                 // Set the connection name
                 restServiceAdapter.setConnectionName("enrich");
                 
                 restServiceAdapter.setRequestType(RestServiceAdapter.REQUEST_TYPE_POST);
                 restServiceAdapter.addRequestProperty("Accept", "application/json; charset=UTF-8");
                 restServiceAdapter.addRequestProperty("Authorization", "Basic " + "WFhFX1JFU1RfU0VSVklDRVNfQURNSU46b3JhY2xlMTIz");
                 restServiceAdapter.addRequestProperty("Content-Type", "application/json");
                 restServiceAdapter.setRequestURI("/webservices/rest/XXETailSpendAPI/get_cost_center/");
                 postData= "{\n" +
                 "\n" +
                 "  \"GET_COST_CENTER_Input\" : {\n" +
                 "\n" +
                 "   \"@xmlns\" : \"http://xmlns.oracle.com/apps/po/rest/XXETailSpendAPI/get_cost_center/\",\n" +
                 "\n" +
                 "   \"RESTHeader\": {\n" +
                 "\n" +
                 "   \"@xmlns\" : \"http://xmlns.oracle.com/apps/po/rest/XXETailSpendAPI/header\"\n" +
                 "    },\n" +
                 "\n" +
                 "   \"InputParameters\": {\n" +
                 "\n" +
                 "          \"P_USER_ID\" : "+user_id+"\n" +
                 "\n" +
                 "     }\n" +
                 "\n" +
                 "  }\n" +
                 "\n" +
                 "}  ";
                                            restServiceAdapter.setRetryLimit(0);
                   System.out.println("postData===============================" + postData);
                    
                    response = restServiceAdapter.send(postData);
                    
                    System.out.println("response===============================" + response); 
                     resp=new JSONObject(response);
                     output=resp.getJSONObject("OutputParameters");
                 try{
                     data=output.getJSONObject("X_COST_CENTER_TL");
                    CostCenterList.s_jobs.clear();
                    costCenterList.clear();
                    
                    if(data.get("X_COST_CENTER_TL_ITEM") instanceof  JSONArray){
                      JSONArray segments=data.getJSONArray("X_COST_CENTER_TL_ITEM");
                      for(int i=0;i<segments.length();i++) {
                          //String name=(String)segments.get(i);
                          JSONObject ci=(JSONObject)segments.get(i);
                          String name=ci.getString("SEGMENT_VALUE");
                          String description=ci.getString("DESCRIPTION");
                          CostCenter c=new CostCenter(name,description);
                          CostCenterList.s_jobs.add(c);
                          costCenterList.add(c);
                          
                      }
                    
                    }
                    
                    else if(data.get("X_COST_CENTER_TL_ITEM") instanceof  JSONObject){
                       
                       JSONObject ci=data.getJSONObject("X_COST_CENTER_TL_ITEM");
                        String name=ci.getString("SEGMENT_VALUE");
                        String description=ci.getString("DESCRIPTION");
                        CostCenter c=new CostCenter(name,description);
                        CostCenterList.s_jobs.add(c);
                        costCenterList.add(c);
                       
                    }
                    }
                    catch(Exception e) {
                        e.printStackTrace();
                    }
                  
                 
                 
                 
                 //register the device related info to oracle
                 
                 restServiceAdapter = Model.createRestServiceAdapter();
                         // Clear any previously set request properties, if any
                         restServiceAdapter.clearRequestProperties();
                         // Set the connection name
                         restServiceAdapter.setConnectionName("enrich");
                         
                         restServiceAdapter.setRequestType(RestServiceAdapter.REQUEST_TYPE_POST);
                         restServiceAdapter.addRequestProperty("Accept", "application/json; charset=UTF-8");
                         restServiceAdapter.addRequestProperty("Authorization", "Basic " + "WFhFX1JFU1RfU0VSVklDRVNfQURNSU46b3JhY2xlMTIz");
                         restServiceAdapter.addRequestProperty("Content-Type", "application/json");
                         restServiceAdapter.setRequestURI("/webservices/rest/XXETailSpendAPI/register_user/");
                         postData= "{\n" + 
                         "\n" + 
                         "  \"REGISTER_USER_Input\" : {\n" + 
                         "\n" + 
                         "   \"@xmlns\" : \"http://xmlns.oracle.com/apps/po/rest/XXETailSpendAPI/register_user/\",\n" + 
                         "\n" + 
                         "   \"RESTHeader\": {\n" + 
                         "\n" + 
                         "   \"@xmlns\" : \"http://xmlns.oracle.com/apps/po/rest/XXETailSpendAPI/header\"\n" + 
                         "    },\n" + 
                         "\n" + 
                         "   \"InputParameters\": {\n" + 
                         "\n" + 
                         "        \"P_USER_ID\" : \""+user_id+"\",\n" + 
                         "\n" + 
                         "        \"P_DEVICE_SERIAL_ID\" : \""+device_serial_id+"\",\n" + 
                         "\n" + 
                         "        \"P_DEVICE_TYPE\" : \""+device_type+"\",\n" + 
                         "\n" + 
                         "        \"P_DEVICE_MODEL\" : \""+device_model+"\",\n" + 
                         "\n" + 
                         "        \"P_TOKEN\" : \""+device_token+"\"\n" + 
                         "\n" + 
                         "     }\n" + 
                         "\n" + 
                         "  }\n" + 
                         "\n" + 
                         "}  ";
                                                     restServiceAdapter.setRetryLimit(0);
                            System.out.println("postData===============================" + postData);
                             
                             response = restServiceAdapter.send(postData);
                 
                           System.out.println("response===============================" + response);
                 
                 
                 
                 //get user preferences
                           
                  String homeScreen="";         
                           
                 restServiceAdapter = Model.createRestServiceAdapter();
                 // Clear any previously set request properties, if any
                 restServiceAdapter.clearRequestProperties();
                 // Set the connection name
                 restServiceAdapter.setConnectionName("enrich");
                 
                 restServiceAdapter.setRequestType(RestServiceAdapter.REQUEST_TYPE_POST);
                 restServiceAdapter.addRequestProperty("Accept", "application/json; charset=UTF-8");
                 restServiceAdapter.addRequestProperty("Authorization", "Basic " + "WFhFX1JFU1RfU0VSVklDRVNfQURNSU46b3JhY2xlMTIz");
                 restServiceAdapter.addRequestProperty("Content-Type", "application/json");
                 restServiceAdapter.setRequestURI("/webservices/rest/XXETailSpendAPI/get_user_preferences/");
                 postData= "\n" + 
                 "{\n" + 
                 "  \"GET_USER_PREFERENCES_Input\" : {\n" + 
                 "   \"@xmlns\" : \"http://xmlns.oracle.com/apps/po/rest/XXETailSpendAPI/get_user_preferences/\",\n" + 
                 "   \"RESTHeader\": {\n" + 
                 "   \"@xmlns\" : \"http://xmlns.oracle.com/apps/po/rest/XXETailSpendAPI/header\"\n" + 
                 "    },\n" + 
                 "   \"InputParameters\": {\n" + 
                 "                   \"P_USER_ID\" :"+user_id+"\n" + 
                 "       }         \n" + 
                 "   }\n" + 
                 "}\n";
                                             restServiceAdapter.setRetryLimit(0);
                    System.out.println("postData===============================" + postData);
                     
                     response = restServiceAdapter.send(postData);
                     
                     System.out.println("response===============================" + response); 
                      resp=new JSONObject(response);
                      output=resp.getJSONObject("OutputParameters");
                 try{
                      data=output.getJSONObject("X_USER_PREFERENCE_TL");
                     if(data.get("X_USER_PREFERENCE_TL_ITEM") instanceof  JSONArray){
                       JSONArray segments=data.getJSONArray("X_USER_PREFERENCE_TL_ITEM");
                       for(int i=0;i<segments.length();i++) {
                           JSONObject pref= segments.getJSONObject(i);
                           if(pref.getString("ATTRIBUTE_NAME").equalsIgnoreCase("HOME_PAGE")) {
                               
                               homeScreen=pref.getString("ATTRIBUTE_VALUE");
                           }
                           
                           if(pref.getString("ATTRIBUTE_NAME").equalsIgnoreCase("COST_CENTER")) {
                               
                               for(int k=0;k<deliverToLocationList.size();k++) {
                                   CostCenter c=(CostCenter)costCenterList.get(k);
                                   System.out.println(c.getName().trim()+"="+pref.getString("ATTRIBUTE_VALUE").trim());
                                   if(c.getName().trim().equalsIgnoreCase(pref.getString("ATTRIBUTE_VALUE").trim()))     {
                                       System.out.println("Match occurs "+c.getDescription());
                                       ve18.setValue(AdfmfJavaUtilities.getAdfELContext(),String.valueOf(k));
                                   }
                               }
                               
                               
                           }
                           
                           if(pref.getString("ATTRIBUTE_NAME").equalsIgnoreCase("DELIVER_TO")) {
                               
                               for(int k=0;k<deliverToLocationList.size();k++) {
                                   DeliverToLocation loc=(DeliverToLocation)deliverToLocationList.get(k);
                                   System.out.println(loc.getId().trim()+"="+pref.getString("ATTRIBUTE_VALUE").trim());
                                   if(loc.getId().trim().equalsIgnoreCase(pref.getString("ATTRIBUTE_VALUE").trim()))     {
                                       System.out.println("Match occurs "+loc.getCode());
                                       ve19.setValue(AdfmfJavaUtilities.getAdfELContext(),String.valueOf(k));
                                   }
                               }
                               
                               
                           }
                           
                       }
                     
                     }
                     
                     else if(data.get("X_USER_PREFERENCE_TL_ITEM") instanceof  JSONObject){
                        
                        JSONObject segments=data.getJSONObject("X_USER_PREFERENCE_TL_ITEM");
                         JSONObject pref= segments;
                         if(pref.getString("ATTRIBUTE_NAME").equalsIgnoreCase("HOME_PAGE")) {
                             
                             homeScreen=pref.getString("ATTRIBUTE_VALUE");
                         }
                         if(pref.getString("ATTRIBUTE_NAME").equalsIgnoreCase("COST_CENTER")) {
                             
                             for(int k=0;k<deliverToLocationList.size();k++) {
                                 CostCenter c=(CostCenter)costCenterList.get(k);
                                 System.out.println(c.getName().trim()+"="+pref.getString("ATTRIBUTE_VALUE").trim());
                                 if(c.getName().trim().equalsIgnoreCase(pref.getString("ATTRIBUTE_VALUE").trim()))     {
                                     System.out.println("Match occurs "+c.getDescription());
                                     ve18.setValue(AdfmfJavaUtilities.getAdfELContext(),String.valueOf(k));
                                 }
                             }
                         }
                         
                         if(pref.getString("ATTRIBUTE_NAME").equalsIgnoreCase("DELIVER_TO")) {
                             
                             for(int k=0;k<deliverToLocationList.size();k++) {
                                 DeliverToLocation loc=(DeliverToLocation)deliverToLocationList.get(k);
                                 System.out.println(loc.getId().trim()+"="+pref.getString("ATTRIBUTE_VALUE").trim());
                                 if(loc.getId().trim().equalsIgnoreCase(pref.getString("ATTRIBUTE_VALUE").trim()))     {
                                     System.out.println("Match occurs "+loc.getCode());
                                     ve19.setValue(AdfmfJavaUtilities.getAdfELContext(),String.valueOf(k));
                                 }
                             }
                         }
                     }
                     }
                     catch(Exception e) {
                         e.printStackTrace();
                     }
                 
                 
                 
                 
                 
                 
                 ValueExpression ve91 = AdfmfJavaUtilities.getValueExpression("#{applicationScope.rdItemType}", String.class);
                 ve91.setValue(AdfmfJavaUtilities.getAdfELContext(), "goods");
                 
                 ////////////////
                 
                 if(homeScreen.equalsIgnoreCase("req_sts")){
                     AdfmfContainerUtilities.gotoFeature("mp.Requisition"); 
                    // AdfmfContainerUtilities.resetFeature("mp.Requisition");
                 }
                 else if(homeScreen.equalsIgnoreCase("qts_screen")){
                     AdfmfContainerUtilities.gotoFeature("mp.Quotation"); 
                     //AdfmfContainerUtilities.resetFeature("mp.Quotation");
                 }
                 else{
                     //check-feature
                     AdfmfContainerUtilities.resetFeature("feature1",true);
                    //AdfmfContainerUtilities.gotoFeature("feature1");
                   //  AdfmfContainerUtilities.resetFeature("feature1");
        //                     AdfmfContainerUtilities.invokeContainerJavaScriptFunction(AdfmfJavaUtilities.getFeatureName(),
        //                                                                                    "adf.mf.api.amx.doNavigation", new Object[] { "valid_login" });
                 }
                 
                 
        //                  AdfmfContainerUtilities.invokeContainerJavaScriptFunction(AdfmfJavaUtilities.getFeatureName(),
        //                                                                                    "adf.mf.api.amx.doNavigation", new Object[] { "valid_login" });
        //
                
                 
                 
                 
             }
             else{
                 AdfmfContainerUtilities.invokeContainerJavaScriptFunction(AdfmfJavaUtilities.getFeatureId(),
                                                                            "InvalidUser",
                                                                            new Object[] { });
                 /*AdfmfContainerUtilities.invokeContainerJavaScriptFunction(
                                              AdfmfJavaUtilities.getFeatureName(),
                                              "adf.mf.api.amx.addMessage", new Object[] {AdfException.ERROR,
                                              "Invalid UserName or Password",
                                              null,
                                              null });*/
             }
            
            
        }
        catch(Exception e) {
            
            e.printStackTrace();
            AdfmfContainerUtilities.invokeContainerJavaScriptFunction(
                                         AdfmfJavaUtilities.getFeatureName(),
                                         "adf.mf.api.amx.addMessage", new Object[] {AdfException.ERROR,
                                         "Cannot connect to Services on Oracle Server.",
                                         null,
                                         null }); 
        }
        
    }
    
    public void validateLogin(ActionEvent actionEvent) {
        // Add event code here...
        try{
            List deliverToLocationList=new ArrayList();
            List costCenterList=new ArrayList();
        ValueExpression ve = AdfmfJavaUtilities.getValueExpression("#{applicationScope.user_name}", String.class);
        String userName = (String)ve.getValue(AdfmfJavaUtilities.getAdfELContext());
        userName=userName.trim();
        
        ValueExpression ve1 = AdfmfJavaUtilities.getValueExpression("#{applicationScope.password}", String.class);
        String password = (String)ve1.getValue(AdfmfJavaUtilities.getAdfELContext());
        
        RestServiceAdapter restServiceAdapter = Model.createRestServiceAdapter();
        // Clear any previously set request properties, if any
        restServiceAdapter.clearRequestProperties();
        // Set the connection name
        restServiceAdapter.setConnectionName("enrich");
        
        restServiceAdapter.setRequestType(RestServiceAdapter.REQUEST_TYPE_POST);
        restServiceAdapter.addRequestProperty("Accept", "application/json; charset=UTF-8");
        restServiceAdapter.addRequestProperty("Authorization", "Basic " + "WFhFX1JFU1RfU0VSVklDRVNfQURNSU46b3JhY2xlMTIz");
        restServiceAdapter.addRequestProperty("Content-Type", "application/json");
        restServiceAdapter.setRequestURI("/webservices/rest/XXETailSpendAPI/validate_user_login/");
        String postData= "{\n" + 
        "  \"VALIDATE_USER_LOGIN_Input\" : {\n" + 
        "   \"@xmlns\" : \"http://xmlns.oracle.com/apps/po/rest/XXETailSpendAPI/validate_user_login/\",\n" + 
        "   \"RESTHeader\": {\n" + 
        "   \"@xmlns\" : \"http://xmlns.oracle.com/apps/po/rest/XXETailSpendAPI/header\"\n" + 
        "    },\n" + 
        "   \"InputParameters\": {\n" + 
        "                  \"P_USER\":\""+userName+"\",\n" + 
        "                  \"P_PWD\":\""+password+"\"\n" + 
        "       }          \n" + 
        "   }\n" + 
        "}\n";
                                    restServiceAdapter.setRetryLimit(0);
           System.out.println("postData===============================" + postData);
            
           String response = restServiceAdapter.send(postData);
            System.out.println("response===============================" + response);   
            JSONObject resp=new JSONObject(response);
            JSONObject output=resp.getJSONObject("OutputParameters");
             String status=output.getString("X_RESULT");
        
             if(status.equalsIgnoreCase("Y")) {
                 
                 
                 
                 String user_id=output.getString("X_USER_ID");
                 String org_id=output.getString("X_ORG_ID");
                 
                 //Get User Fisrt/Last Name
                 String user_firstName=output.getString("X_USER_FIRST_NAME");
                 String user_lastName=output.getString("X_USER_FIRST_NAME");
                 
                 
                 ValueExpression ve12 = AdfmfJavaUtilities.getValueExpression("#{applicationScope.user_name}", String.class);
                 ve12.setValue(AdfmfJavaUtilities.getAdfELContext(),userName);
                 
                 ValueExpression ve13 = AdfmfJavaUtilities.getValueExpression("#{applicationScope.user_id}", String.class);
                 ve13.setValue(AdfmfJavaUtilities.getAdfELContext(),user_id);
               
                 ValueExpression ove13 = AdfmfJavaUtilities.getValueExpression("#{applicationScope.org_id}", String.class);
                 ove13.setValue(AdfmfJavaUtilities.getAdfELContext(),org_id);
                 
                 ValueExpression fve13 = AdfmfJavaUtilities.getValueExpression("#{applicationScope.user_firstName}", String.class);
                 fve13.setValue(AdfmfJavaUtilities.getAdfELContext(),user_firstName);
                 
                 ValueExpression lve13 = AdfmfJavaUtilities.getValueExpression("#{applicationScope.user_lastName}", String.class);
                 lve13.setValue(AdfmfJavaUtilities.getAdfELContext(),user_lastName);
                 
                 ValueExpression ve14 = AdfmfJavaUtilities.getValueExpression("#{deviceScope.device.uuid}", String.class);
                 String device_serial_id=(String)ve14.getValue(AdfmfJavaUtilities.getAdfELContext());
                 
                 ValueExpression ve15 = AdfmfJavaUtilities.getValueExpression("#{deviceScope.device.platform}", String.class);
                 String device_type=(String)ve15.getValue(AdfmfJavaUtilities.getAdfELContext());
                 
                 ValueExpression ve16 = AdfmfJavaUtilities.getValueExpression("#{deviceScope.device.model}", String.class);
                 String device_model=(String)ve16.getValue(AdfmfJavaUtilities.getAdfELContext());
                 
                 
                 ValueExpression ve17 = AdfmfJavaUtilities.getValueExpression("#{applicationScope.deviceToken}", String.class);
                 String device_token=(String)ve17.getValue(AdfmfJavaUtilities.getAdfELContext());
                 
                 
                 ValueExpression ve18 = AdfmfJavaUtilities.getValueExpression("#{applicationScope.default_cost_center}", String.class);
                 ve18.setValue(AdfmfJavaUtilities.getAdfELContext(),"");
                 
                 ValueExpression ve19 = AdfmfJavaUtilities.getValueExpression("#{applicationScope.default_deliver_to}", String.class);
                 ve19.setValue(AdfmfJavaUtilities.getAdfELContext(),"");
                 
                 //get deliver to list
                 restServiceAdapter = Model.createRestServiceAdapter();
                 // Clear any previously set request properties, if any
                 restServiceAdapter.clearRequestProperties();
                 // Set the connection name
                 restServiceAdapter.setConnectionName("enrich");
                 
                 restServiceAdapter.setRequestType(RestServiceAdapter.REQUEST_TYPE_POST);
                 restServiceAdapter.addRequestProperty("Accept", "application/json; charset=UTF-8");
                 restServiceAdapter.addRequestProperty("Authorization", "Basic " + "WFhFX1JFU1RfU0VSVklDRVNfQURNSU46b3JhY2xlMTIz");
                 restServiceAdapter.addRequestProperty("Content-Type", "application/json");
                 restServiceAdapter.setRequestURI("/webservices/rest/XXETailSpendAPI/get_deliver_to/");
                 postData= "{\n" + 
                 "\n" + 
                 "  \"GET_DELIVER_TO_Input\" : {\n" + 
                 "\n" + 
                 "   \"@xmlns\" : \"http://xmlns.oracle.com/apps/po/rest/XXETailSpendAPI/get_deliver_to/\",\n" + 
                 "\n" + 
                 "   \"RESTHeader\": {\n" + 
                 "\n" + 
                 "   \"@xmlns\" : \"http://xmlns.oracle.com/apps/po/rest/XXETailSpendAPI/header\"\n" + 
                 "    },\n" + 
                 "\n" + 
                 "   \"InputParameters\": {\n" + 
                 "\n" + 
                 "        \"P_USER_ID\":"+user_id+"\n" + 
                 "\n" + 
                 "     }\n" + 
                 "\n" + 
                 "  }\n" + 
                 "\n" + 
                 "}  ";
                                             restServiceAdapter.setRetryLimit(0);
                    System.out.println("postData===============================" + postData);
                     
                    response = restServiceAdapter.send(postData);
                     
                     System.out.println("response===============================" + response); 
                      resp=new JSONObject(response);
                      output=resp.getJSONObject("OutputParameters");
                    JSONObject data=new JSONObject();
                  try{
                      data=output.getJSONObject("X_DELIVER_TO_TL");
                     DeliverToLocationList.s_jobs.clear();
                         deliverToLocationList.clear();
                     if(data.get("X_DELIVER_TO_TL_ITEM") instanceof  JSONArray){
                       JSONArray segments=data.getJSONArray("X_DELIVER_TO_TL_ITEM");
                       for(int i=0;i<segments.length();i++) {
                         JSONObject location=segments.getJSONObject(i);
                         String locationId=location.getString("LOCATION_ID");
                         String locationCode=location.getString("LOCATION_CODE");
                         String locationDescription=location.getString("DESCRIPTION");
                         DeliverToLocation loc=new DeliverToLocation(locationId, locationCode, locationDescription);
                         DeliverToLocationList.s_jobs.add(loc);
                           deliverToLocationList.add(loc);
                       }
                     
                     }
                     
                     else if(data.get("X_DELIVER_TO_TL_ITEM") instanceof  JSONObject){
                        
                        JSONObject location=data.getJSONObject("X_DELIVER_TO_TL_ITEM");
                         String locationId=location.getString("LOCATION_ID");
                         String locationCode=location.getString("LOCATION_CODE");
                         String locationDescription=location.getString("DESCRIPTION");
                         DeliverToLocation loc=new DeliverToLocation(locationId, locationCode, locationDescription);
                         DeliverToLocationList.s_jobs.add(loc);
                         deliverToLocationList.add(loc);
                     }
                     }
                     catch(Exception e) {
                         e.printStackTrace();
                     }
                 
                 //get cost center list
                  
                 //Cost center     
                 
                 restServiceAdapter = Model.createRestServiceAdapter();
                 // Clear any previously set request properties, if any
                 restServiceAdapter.clearRequestProperties();
                 // Set the connection name
                 restServiceAdapter.setConnectionName("enrich");
                 
                 restServiceAdapter.setRequestType(RestServiceAdapter.REQUEST_TYPE_POST);
                 restServiceAdapter.addRequestProperty("Accept", "application/json; charset=UTF-8");
                 restServiceAdapter.addRequestProperty("Authorization", "Basic " + "WFhFX1JFU1RfU0VSVklDRVNfQURNSU46b3JhY2xlMTIz");
                 restServiceAdapter.addRequestProperty("Content-Type", "application/json");
                 restServiceAdapter.setRequestURI("/webservices/rest/XXETailSpendAPI/get_cost_center/");
                 postData= "{\n" +
                 "\n" +
                 "  \"GET_COST_CENTER_Input\" : {\n" +
                 "\n" +
                 "   \"@xmlns\" : \"http://xmlns.oracle.com/apps/po/rest/XXETailSpendAPI/get_cost_center/\",\n" +
                 "\n" +
                 "   \"RESTHeader\": {\n" +
                 "\n" +
                 "   \"@xmlns\" : \"http://xmlns.oracle.com/apps/po/rest/XXETailSpendAPI/header\"\n" +
                 "    },\n" +
                 "\n" +
                 "   \"InputParameters\": {\n" +
                 "\n" +
                 "          \"P_USER_ID\" : "+user_id+"\n" +
                 "\n" +
                 "     }\n" +
                 "\n" +
                 "  }\n" +
                 "\n" +
                 "}  ";
                                            restServiceAdapter.setRetryLimit(0);
                   System.out.println("postData===============================" + postData);
                    
                    response = restServiceAdapter.send(postData);
                    
                    System.out.println("response===============================" + response); 
                     resp=new JSONObject(response);
                     output=resp.getJSONObject("OutputParameters");
                 try{
                     data=output.getJSONObject("X_COST_CENTER_TL");
                    CostCenterList.s_jobs.clear();
                    costCenterList.clear();
                    
                    if(data.get("X_COST_CENTER_TL_ITEM") instanceof  JSONArray){
                      JSONArray segments=data.getJSONArray("X_COST_CENTER_TL_ITEM");
                      for(int i=0;i<segments.length();i++) {
                          //String name=(String)segments.get(i);
                          JSONObject ci=(JSONObject)segments.get(i);
                          String name=ci.getString("SEGMENT_VALUE");
                          String description=ci.getString("DESCRIPTION");
                          CostCenter c=new CostCenter(name,description);
                          CostCenterList.s_jobs.add(c);
                          costCenterList.add(c);
                          
                      }
                    
                    }
                    
                    else if(data.get("X_COST_CENTER_TL_ITEM") instanceof  JSONObject){
                       
                       JSONObject ci=data.getJSONObject("X_COST_CENTER_TL_ITEM");
                        String name=ci.getString("SEGMENT_VALUE");
                        String description=ci.getString("DESCRIPTION");
                        CostCenter c=new CostCenter(name,description);
                        CostCenterList.s_jobs.add(c);
                        costCenterList.add(c);
                       
                    }
                    }
                    catch(Exception e) {
                        e.printStackTrace();
                    }
                  
                 
                 
                 
                 //register the device related info to oracle
                 
                 restServiceAdapter = Model.createRestServiceAdapter();
                         // Clear any previously set request properties, if any
                         restServiceAdapter.clearRequestProperties();
                         // Set the connection name
                         restServiceAdapter.setConnectionName("enrich");
                         
                         restServiceAdapter.setRequestType(RestServiceAdapter.REQUEST_TYPE_POST);
                         restServiceAdapter.addRequestProperty("Accept", "application/json; charset=UTF-8");
                         restServiceAdapter.addRequestProperty("Authorization", "Basic " + "WFhFX1JFU1RfU0VSVklDRVNfQURNSU46b3JhY2xlMTIz");
                         restServiceAdapter.addRequestProperty("Content-Type", "application/json");
                         restServiceAdapter.setRequestURI("/webservices/rest/XXETailSpendAPI/register_user/");
                         postData= "{\n" + 
                         "\n" + 
                         "  \"REGISTER_USER_Input\" : {\n" + 
                         "\n" + 
                         "   \"@xmlns\" : \"http://xmlns.oracle.com/apps/po/rest/XXETailSpendAPI/register_user/\",\n" + 
                         "\n" + 
                         "   \"RESTHeader\": {\n" + 
                         "\n" + 
                         "   \"@xmlns\" : \"http://xmlns.oracle.com/apps/po/rest/XXETailSpendAPI/header\"\n" + 
                         "    },\n" + 
                         "\n" + 
                         "   \"InputParameters\": {\n" + 
                         "\n" + 
                         "        \"P_USER_ID\" : \""+user_id+"\",\n" + 
                         "\n" + 
                         "        \"P_DEVICE_SERIAL_ID\" : \""+device_serial_id+"\",\n" + 
                         "\n" + 
                         "        \"P_DEVICE_TYPE\" : \""+device_type+"\",\n" + 
                         "\n" + 
                         "        \"P_DEVICE_MODEL\" : \""+device_model+"\",\n" + 
                         "\n" + 
                         "        \"P_TOKEN\" : \""+device_token+"\"\n" + 
                         "\n" + 
                         "     }\n" + 
                         "\n" + 
                         "  }\n" + 
                         "\n" + 
                         "}  ";
                                                     restServiceAdapter.setRetryLimit(0);
                            System.out.println("postData===============================" + postData);
                             
                             response = restServiceAdapter.send(postData);
                 
                           System.out.println("response===============================" + response);
                 
                 
                 
                 //get user preferences
                           
                  String homeScreen="";         
                           
                 restServiceAdapter = Model.createRestServiceAdapter();
                 // Clear any previously set request properties, if any
                 restServiceAdapter.clearRequestProperties();
                 // Set the connection name
                 restServiceAdapter.setConnectionName("enrich");
                 
                 restServiceAdapter.setRequestType(RestServiceAdapter.REQUEST_TYPE_POST);
                 restServiceAdapter.addRequestProperty("Accept", "application/json; charset=UTF-8");
                 restServiceAdapter.addRequestProperty("Authorization", "Basic " + "WFhFX1JFU1RfU0VSVklDRVNfQURNSU46b3JhY2xlMTIz");
                 restServiceAdapter.addRequestProperty("Content-Type", "application/json");
                 restServiceAdapter.setRequestURI("/webservices/rest/XXETailSpendAPI/get_user_preferences/");
                 postData= "\n" + 
                 "{\n" + 
                 "  \"GET_USER_PREFERENCES_Input\" : {\n" + 
                 "   \"@xmlns\" : \"http://xmlns.oracle.com/apps/po/rest/XXETailSpendAPI/get_user_preferences/\",\n" + 
                 "   \"RESTHeader\": {\n" + 
                 "   \"@xmlns\" : \"http://xmlns.oracle.com/apps/po/rest/XXETailSpendAPI/header\"\n" + 
                 "    },\n" + 
                 "   \"InputParameters\": {\n" + 
                 "                   \"P_USER_ID\" :"+user_id+"\n" + 
                 "       }         \n" + 
                 "   }\n" + 
                 "}\n";
                                             restServiceAdapter.setRetryLimit(0);
                    System.out.println("postData===============================" + postData);
                     
                     response = restServiceAdapter.send(postData);
                     
                     System.out.println("response===============================" + response); 
                      resp=new JSONObject(response);
                      output=resp.getJSONObject("OutputParameters");
                 try{
                      data=output.getJSONObject("X_USER_PREFERENCE_TL");
                     if(data.get("X_USER_PREFERENCE_TL_ITEM") instanceof  JSONArray){
                       JSONArray segments=data.getJSONArray("X_USER_PREFERENCE_TL_ITEM");
                       for(int i=0;i<segments.length();i++) {
                           JSONObject pref= segments.getJSONObject(i);
                           if(pref.getString("ATTRIBUTE_NAME").equalsIgnoreCase("HOME_PAGE")) {
                               
                               homeScreen=pref.getString("ATTRIBUTE_VALUE");
                           }
                           
                           if(pref.getString("ATTRIBUTE_NAME").equalsIgnoreCase("COST_CENTER")) {
                               
                               for(int k=0;k<deliverToLocationList.size();k++) {
                                   CostCenter c=(CostCenter)costCenterList.get(k);
                                   System.out.println(c.getName().trim()+"="+pref.getString("ATTRIBUTE_VALUE").trim());
                                   if(c.getName().trim().equalsIgnoreCase(pref.getString("ATTRIBUTE_VALUE").trim()))     {
                                       System.out.println("Match occurs "+c.getDescription());
                                       ve18.setValue(AdfmfJavaUtilities.getAdfELContext(),String.valueOf(k));
                                   }
                               }
                               
                               
                           }
                           
                           if(pref.getString("ATTRIBUTE_NAME").equalsIgnoreCase("DELIVER_TO")) {
                               
                               for(int k=0;k<deliverToLocationList.size();k++) {
                                   DeliverToLocation loc=(DeliverToLocation)deliverToLocationList.get(k);
                                   System.out.println(loc.getId().trim()+"="+pref.getString("ATTRIBUTE_VALUE").trim());
                                   if(loc.getId().trim().equalsIgnoreCase(pref.getString("ATTRIBUTE_VALUE").trim()))     {
                                       System.out.println("Match occurs "+loc.getCode());
                                       ve19.setValue(AdfmfJavaUtilities.getAdfELContext(),String.valueOf(k));
                                   }
                               }
                               
                               
                           }
                           
                       }
                     
                     }
                     
                     else if(data.get("X_USER_PREFERENCE_TL_ITEM") instanceof  JSONObject){
                        
                        JSONObject segments=data.getJSONObject("X_USER_PREFERENCE_TL_ITEM");
                         JSONObject pref= segments;
                         if(pref.getString("ATTRIBUTE_NAME").equalsIgnoreCase("HOME_PAGE")) {
                             
                             homeScreen=pref.getString("ATTRIBUTE_VALUE");
                         }
                         if(pref.getString("ATTRIBUTE_NAME").equalsIgnoreCase("COST_CENTER")) {
                             
                             for(int k=0;k<deliverToLocationList.size();k++) {
                                 CostCenter c=(CostCenter)costCenterList.get(k);
                                 System.out.println(c.getName().trim()+"="+pref.getString("ATTRIBUTE_VALUE").trim());
                                 if(c.getName().trim().equalsIgnoreCase(pref.getString("ATTRIBUTE_VALUE").trim()))     {
                                     System.out.println("Match occurs "+c.getDescription());
                                     ve18.setValue(AdfmfJavaUtilities.getAdfELContext(),String.valueOf(k));
                                 }
                             }
                         }
                         
                         if(pref.getString("ATTRIBUTE_NAME").equalsIgnoreCase("DELIVER_TO")) {
                             
                             for(int k=0;k<deliverToLocationList.size();k++) {
                                 DeliverToLocation loc=(DeliverToLocation)deliverToLocationList.get(k);
                                 System.out.println(loc.getId().trim()+"="+pref.getString("ATTRIBUTE_VALUE").trim());
                                 if(loc.getId().trim().equalsIgnoreCase(pref.getString("ATTRIBUTE_VALUE").trim()))     {
                                     System.out.println("Match occurs "+loc.getCode());
                                     ve19.setValue(AdfmfJavaUtilities.getAdfELContext(),String.valueOf(k));
                                 }
                             }
                         }
                     }
                     }
                     catch(Exception e) {
                         e.printStackTrace();
                     }
                 
                 
                 
                 
                 
                 
                 ValueExpression ve91 = AdfmfJavaUtilities.getValueExpression("#{applicationScope.rdItemType}", String.class);
                 ve91.setValue(AdfmfJavaUtilities.getAdfELContext(), "goods");
                 
                 ////////////////
                 
                 if(homeScreen.equalsIgnoreCase("req_sts")){
                     AdfmfContainerUtilities.gotoFeature("mp.Requisition"); 
                    // AdfmfContainerUtilities.resetFeature("mp.Requisition");
                 }
                 else if(homeScreen.equalsIgnoreCase("qts_screen")){
                     AdfmfContainerUtilities.gotoFeature("mp.Quotation"); 
                     //AdfmfContainerUtilities.resetFeature("mp.Quotation");
                 }
                 else{
                     //check-feature
                     AdfmfContainerUtilities.resetFeature("feature1",true);
                    //AdfmfContainerUtilities.gotoFeature("feature1");
                   //  AdfmfContainerUtilities.resetFeature("feature1");
    //                     AdfmfContainerUtilities.invokeContainerJavaScriptFunction(AdfmfJavaUtilities.getFeatureName(),
    //                                                                                    "adf.mf.api.amx.doNavigation", new Object[] { "valid_login" });
                 }
                 
                 
    //                  AdfmfContainerUtilities.invokeContainerJavaScriptFunction(AdfmfJavaUtilities.getFeatureName(),
    //                                                                                    "adf.mf.api.amx.doNavigation", new Object[] { "valid_login" });
    //
                
                 
                 
                 
             }
             else{
                 AdfmfContainerUtilities.invokeContainerJavaScriptFunction(AdfmfJavaUtilities.getFeatureId(),
                                                                            "InvalidUser",
                                                                            new Object[] { });
                 /*AdfmfContainerUtilities.invokeContainerJavaScriptFunction(
                                              AdfmfJavaUtilities.getFeatureName(),
                                              "adf.mf.api.amx.addMessage", new Object[] {AdfException.ERROR,
                                              "Invalid UserName or Password",
                                              null,
                                              null });*/
             }
            
            
        }
        catch(Exception e) {
            
            e.printStackTrace();
            AdfmfContainerUtilities.invokeContainerJavaScriptFunction(
                                         AdfmfJavaUtilities.getFeatureName(),
                                         "adf.mf.api.amx.addMessage", new Object[] {AdfException.ERROR,
                                         "Cannot connect to Services on Oracle Server.",
                                         null,
                                         null }); 
        }
        
    }
    public void password_forgot(ActionEvent actionEvent) {
        // Add event code here...
        
        
          
        try{
            String userName="";
        ValueExpression ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.fuser_name}", String.class);
        userName = (String)ve.getValue(AdfmfJavaUtilities.getAdfELContext());
            if(!userName.equalsIgnoreCase("")){
        RestServiceAdapter restServiceAdapter = Model.createRestServiceAdapter();
        // Clear any previously set request properties, if any
        restServiceAdapter.clearRequestProperties();
        // Set the connection name
        restServiceAdapter.setConnectionName("enrich");
        
        restServiceAdapter.setRequestType(RestServiceAdapter.REQUEST_TYPE_POST);
        restServiceAdapter.addRequestProperty("Accept", "application/json; charset=UTF-8");
        restServiceAdapter.addRequestProperty("Authorization", "Basic " + "WFhFX1JFU1RfU0VSVklDRVNfQURNSU46b3JhY2xlMTIz");
        restServiceAdapter.addRequestProperty("Content-Type", "application/json");
        restServiceAdapter.setRequestURI("/webservices/rest/XXETailSpendAPI/forgot_password/");
        String postData= "{\n" + 
        "\n" + 
        "    \"FORGOT_PASSWORD_Input\":{\n" + 
        "        \"@xmlns\":\"http://xmlns.oracle.com/apps/po/rest/XXETailSpendAPI/forgot_password/\",\n" + 
        "        \"RESTHeader\":{\n" + 
        "            \"@xmlns\":\"http://xmlns.oracle.com/apps/po/rest/XXETailSpendAPI/header\"\n" + 
        "        },\n" + 
        "        \"InputParameters\":{\n" + 
        "            \"P_USERNAME\":\""+userName+"\"\n" + 
        "        }\n" + 
        "    }\n" + 
        "\n" + 
        "}";
                                    restServiceAdapter.setRetryLimit(0);
           System.out.println("postData===============================" + postData);
            
           String response = restServiceAdapter.send(postData);
            System.out.println("ResponseData===============================" + response);
            
            JSONObject resp=new JSONObject(response);
            JSONObject output=resp.getJSONObject("OutputParameters");
             String status=output.getString("X_MESSAGE_TEXT");
            AdfmfContainerUtilities.invokeContainerJavaScriptFunction(AdfmfJavaUtilities.getFeatureId(),
                                                                       "PasswordForgot",
                                                                       new Object[] {status });
                 /*AdfmfContainerUtilities.invokeContainerJavaScriptFunction(
                                              AdfmfJavaUtilities.getFeatureName(),
                                              "adf.mf.api.amx.addMessage", new Object[] {null,
                                              status,
                                              null,
                                              null });*/
            
            ValueExpression clearUsername = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.fuser_name}", String.class);
            clearUsername.setValue(AdfmfJavaUtilities.getAdfELContext(),"");
        }
           else{
               System.out.println("Imside-->else"+userName);
               
               AdfmfContainerUtilities.invokeContainerJavaScriptFunction(AdfmfJavaUtilities.getFeatureId(),
                                                                          "ForgotPassword",
                                                                          new Object[] { });
               
           }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        
        
       }
        
    
    public void user_forgot(ActionEvent actionEvent) {
        // Add event code here...
        try{
            String emailaddress="";
        ValueExpression ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.femail_address}", String.class);
        emailaddress = (String)ve.getValue(AdfmfJavaUtilities.getAdfELContext());
         if(!emailaddress.equalsIgnoreCase("")){
        RestServiceAdapter restServiceAdapter = Model.createRestServiceAdapter();
        // Clear any previously set request properties, if any
        restServiceAdapter.clearRequestProperties();
        // Set the connection name
        restServiceAdapter.setConnectionName("enrich");
        
        restServiceAdapter.setRequestType(RestServiceAdapter.REQUEST_TYPE_POST);
        restServiceAdapter.addRequestProperty("Accept", "application/json; charset=UTF-8");
        restServiceAdapter.addRequestProperty("Authorization", "Basic " + "WFhFX1JFU1RfU0VSVklDRVNfQURNSU46b3JhY2xlMTIz");
        restServiceAdapter.addRequestProperty("Content-Type", "application/json");
        restServiceAdapter.setRequestURI("/webservices/rest/XXETailSpendAPI/forgot_username/");
        String postData= "{\n" + 
        "\n" + 
        "    \"FORGOT_PASSWORD_Input\":{\n" + 
        "        \"@xmlns\":\"http://xmlns.oracle.com/apps/po/rest/XXETailSpendAPI/forgot_username/\",\n" + 
        "        \"RESTHeader\":{\n" + 
        "            \"@xmlns\":\"http://xmlns.oracle.com/apps/po/rest/XXETailSpendAPI/header\"\n" + 
        "        },\n" + 
        "        \"InputParameters\":{\n" + 
        "            \"P_EMAIL\":\""+emailaddress+"\"\n" + 
        "        }\n" + 
        "    }\n" + 
        "\n" + 
        "}";
            
                                    restServiceAdapter.setRetryLimit(0);
           System.out.println("postData===============================" + postData);
            
           String response = restServiceAdapter.send(postData);
            System.out.println("ResponseData===============================" + response);
            
            JSONObject resp=new JSONObject(response);
            JSONObject output=resp.getJSONObject("OutputParameters");
             String status=output.getString("X_MESSAGE_TEXT");
                AdfmfContainerUtilities.invokeContainerJavaScriptFunction(AdfmfJavaUtilities.getFeatureId(),
                                                                           "UserForgot",
                                                                           new Object[] {status });
                 /*AdfmfContainerUtilities.invokeContainerJavaScriptFunction(
                                              AdfmfJavaUtilities.getFeatureName(),
                                              "adf.mf.api.amx.addMessage", new Object[] {AdfException.ERROR,
                                              status,
                                              null,
                                              null });*/
            
            ValueExpression clearemailaddress = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.femail_address}", String.class);
            clearemailaddress.setValue(AdfmfJavaUtilities.getAdfELContext(),"");
            }
         else{
             System.out.println("Imside-->else"+emailaddress);
             
             AdfmfContainerUtilities.invokeContainerJavaScriptFunction(AdfmfJavaUtilities.getFeatureId(),
                                                                        "ForgotUsername",
                                                                        new Object[] { });
             
         }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    
}
