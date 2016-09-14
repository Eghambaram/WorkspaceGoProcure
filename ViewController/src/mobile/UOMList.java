package mobile;

import java.io.BufferedReader;
import java.io.File;

import java.io.InputStreamReader;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import java.util.Random;

import javax.el.MethodExpression;
import javax.el.ValueExpression;

import oracle.adf.model.datacontrols.device.DeviceManagerFactory;
import oracle.adf.model.datacontrols.device.Location;

import oracle.adfmf.bindings.iterator.BasicIterator;
import oracle.adfmf.dc.ws.rest.RestServiceAdapter;
import oracle.adfmf.framework.FeatureContext;
import oracle.adfmf.framework.api.AdfmfContainerUtilities;
import oracle.adfmf.framework.api.AdfmfJavaUtilities;
import oracle.adfmf.framework.api.Model;
import oracle.adfmf.framework.exception.AdfException;
import oracle.adfmf.java.beans.PropertyChangeSupport;
import oracle.adfmf.java.beans.ProviderChangeSupport;

import org.json.JSONArray;
import org.json.JSONObject;


public class UOMList {
    private  transient ProviderChangeSupport providerChangeSupport = new ProviderChangeSupport(this);
    private  transient PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
    public static List s_jobs = new ArrayList();
    public static ArrayList<Integer> selectedServiceLocations = new ArrayList<Integer>(); 
    public static ArrayList<String> selectedImages = new ArrayList<String>(); 
    public static ArrayList<ServiceLocation> serviceLocationList = new ArrayList<ServiceLocation>(); 
    public static ArrayList<Lookup> contractLookupList = new ArrayList<Lookup>(); 
    public static ArrayList<Lookup> frequencyPeriodList = new ArrayList<Lookup>(); 
    public static ArrayList<Lookup> serviceFrequencyTypeList = new ArrayList<Lookup>(); 
    public static int pageNo=1;
    public static List Specification = new ArrayList();
    
    public UOMList() {
        super();
        if (s_jobs == null) {
            s_jobs = new ArrayList();
        }
    }
    
    public UOM[] getUOM() {
        UOM e[] = null;
        e = (UOM[])s_jobs.toArray(new UOM[s_jobs.size()]);
        return e;
    }
    
    
    public void getUOMList() {
        
        UOM j = new UOM(); 
        s_jobs.add(j);
        
          
        
    }
    
    public static void populateUOM(){
       
        ValueExpression ve11 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.item_categories}", String.class);
        ve11.setValue(AdfmfJavaUtilities.getAdfELContext(),"");
        ValueExpression ve12 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.suppliers}", String.class);
        ve12.setValue(AdfmfJavaUtilities.getAdfELContext(),"");
        ValueExpression ve13 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.web_url}", String.class);
        ve13.setValue(AdfmfJavaUtilities.getAdfELContext(),"");
        ValueExpression ve14 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.part_no}", String.class);
        ve14.setValue(AdfmfJavaUtilities.getAdfELContext(),"");
        ValueExpression ve15 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.brand}", String.class);
        ve15.setValue(AdfmfJavaUtilities.getAdfELContext(),"");
        ValueExpression ve16 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.upc}", String.class);
        ve16.setValue(AdfmfJavaUtilities.getAdfELContext(),"");
        ValueExpression ve17 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.quantity}", String.class);
        ve17.setValue(AdfmfJavaUtilities.getAdfELContext(),"");
        ValueExpression ve18 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.uom1}", String.class);
        ve18.setValue(AdfmfJavaUtilities.getAdfELContext(),"");
        
        ValueExpression ve41 = AdfmfJavaUtilities.getValueExpression("#{applicationScope.rdItemType}", String.class);
        String itemType=(String)ve41.getValue(AdfmfJavaUtilities.getAdfELContext());
        String search="";
        ValueExpression ve3 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.searchValue}", String.class);
        search = (String)ve3.getValue(AdfmfJavaUtilities.getAdfELContext()); 
        
       if(!itemType.equalsIgnoreCase("") && !search.equalsIgnoreCase("")){ 
       
        try{
     
        RestServiceAdapter restServiceAdapter = Model.createRestServiceAdapter();
        // Clear any previously set request properties, if any
        restServiceAdapter.clearRequestProperties();
        // Set the connection name
        restServiceAdapter.setConnectionName("enrich");
        
        restServiceAdapter.setRequestType(RestServiceAdapter.REQUEST_TYPE_POST);
        restServiceAdapter.addRequestProperty("Accept", "application/json; charset=UTF-8");
            ///
        restServiceAdapter.addRequestProperty("Authorization", "Basic " + "WFhFX1JFU1RfU0VSVklDRVNfQURNSU46b3JhY2xlMTIz");
        restServiceAdapter.addRequestProperty("Content-Type", "application/json");
        restServiceAdapter.setRequestURI("/webservices/rest/XXETailSpendAPI/get_uom/");
        String postData= "{\n" + 
        "\n" + 
        "  \"GET_UOM_Input\" : {\n" + 
        "\n" + 
        "    \"@xmlns\" : \"http://xmlns.oracle.com/apps/po/rest/XXETailSpendAPI/get_uom/\",\n" + 
        "\n" + 
        "   \"RESTHeader\": {\n" + 
        "\n" + 
        "   \"@xmlns\" : \"http://xmlns.oracle.com/apps/po/rest/XXETailSpendAPI/header\"\n" + 
        "\n" + 
        "    },\n" + 
        "\n" + 
        "   \"InputParameters\": {\n" + 
        "\n" + 
        "     }\n" + 
        "\n" + 
        "   } \n" + 
        "\n" + 
        "}";
                                    restServiceAdapter.setRetryLimit(0);
           System.out.println("postData===============================" + postData);
            
           String response = restServiceAdapter.send(postData);
            
            JSONObject resp=new JSONObject(response);
            JSONObject output=resp.getJSONObject("OutputParameters");
            JSONObject tbl=output.getJSONObject("X_UOM_TL");
            UOMList.s_jobs.clear();
            UOM u1=new UOM("Please Select");
            UOMList.s_jobs.add(u1);
            
                if(tbl.get("X_UOM_TL_ITEM") instanceof  JSONArray){
                   JSONArray segments=tbl.getJSONArray("X_UOM_TL_ITEM");
                   for(int i=0;i<segments.length();i++) {
                       JSONObject item=(JSONObject)segments.get(i);
                       UOM u=new UOM(item.getString("UNIT_OF_MEASURE"));
                       if(item.getString("UNIT_OF_MEASURE").equalsIgnoreCase("Each")){
                           System.out.println("Each match "+i);
                       ValueExpression ve421 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.uom1}", String.class);
                       ve421.setValue(AdfmfJavaUtilities.getAdfELContext(), String.valueOf((i+1)));
                       }
                       
                       
                       UOMList.s_jobs.add(u);
                       
                   }
                
                }
                
                else if(tbl.get("X_UOM_TL_ITEM") instanceof  JSONObject){
                    
                    JSONObject item=tbl.getJSONObject("X_UOM_TL_ITEM");
                    UOM u=new UOM(item.getString("UNIT_OF_MEASURE"));
                    if(item.getString("UNIT_OF_MEASURE").equalsIgnoreCase("Each")){
                    ValueExpression ve421 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.uom1}", String.class);
                    ve421.setValue(AdfmfJavaUtilities.getAdfELContext(), String.valueOf(1));
                    }
                    UOMList.s_jobs.add(u);
                    
                    //JSONObject segments=data.getJSONObject("X_SEGMENT_VALUES_TL_ITEM");
                    
                }
            
            
            String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            
            ValueExpression ve421 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.need_by_date}", String.class);
            ve421.setValue(AdfmfJavaUtilities.getAdfELContext(), "");
            DeliverToLocationList deliverToLocationList =new DeliverToLocationList();
            
            ValueExpression ve = AdfmfJavaUtilities.getValueExpression("#{applicationScope.user_id}", String.class);
            String userId = (String)ve.getValue(AdfmfJavaUtilities.getAdfELContext());
            
            if(itemType.equalsIgnoreCase("services")){
                
                
                
                //get Service locations
                
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
                restServiceAdapter.setRequestURI("/webservices/rest/XXETailSpendAPI/get_service_location/");
                postData= "{\n" + 
                "  \"GET_SERVICE_LOCATION_Input\" : {\n" + 
                "   \"RESTHeader\": {\n" + 
                "    },\n" + 
                "   \"InputParameters\": {\n" + 
                "   	   \"P_USER_ID\" : \""+userId+"\"\n" + 
                "       }	   \n" + 
                "   }\n" + 
                "}";
                                            restServiceAdapter.setRetryLimit(0);
                   System.out.println("postData===============================" + postData);
                    
                   response = restServiceAdapter.send(postData);
                    
                    System.out.println("response===============================" + response); 
                     resp=new JSONObject(response);
                     output=resp.getJSONObject("OutputParameters");
                   JSONObject data=new JSONObject();
                 try{
                     data=output.getJSONObject("X_SERVICE_LOC_TL");
                    ServiceLocationsList.s_jobs.clear();
                        serviceLocationList.clear();
                    if(data.get("X_SERVICE_LOC_TL_ITEM") instanceof  JSONArray){
                      JSONArray segments=data.getJSONArray("X_SERVICE_LOC_TL_ITEM");
                        ServiceLocation c2=new ServiceLocation("Please Select","Please Select","Please Select"); 
                        ServiceLocationsList.s_jobs.add(c2);
                        serviceLocationList.add(c2);
                      for(int i=0;i<segments.length();i++) {
                        JSONObject location=segments.getJSONObject(i);
                        String locationId=location.getString("LOCATION_ID");
                        String locationCode=location.getString("LOCATION_CODE");
                        String locationDescription=location.getString("DESCRIPTION");
                        ServiceLocation loc=new ServiceLocation(locationId, locationCode, locationDescription);
                        ServiceLocationsList.s_jobs.add(loc);
                        serviceLocationList.add(loc);
                      }
                    
                    }
                    
                    else if(data.get("X_SERVICE_LOC_TL_ITEM") instanceof  JSONObject){
                        ServiceLocation c2=new ServiceLocation("Please Select","Please Select","Please Select"); 
                        ServiceLocationsList.s_jobs.add(c2);
                        serviceLocationList.add(c2);
                       JSONObject location=data.getJSONObject("X_DELIVER_TO_TL_ITEM");
                        String locationId=location.getString("LOCATION_ID");
                        String locationCode=location.getString("LOCATION_CODE");
                        String locationDescription=location.getString("DESCRIPTION");
                        ServiceLocation loc=new ServiceLocation(locationId, locationCode, locationDescription);
                        ServiceLocationsList.s_jobs.add(loc);
                        serviceLocationList.add(loc);
                        
                    }
                    }
                    catch(Exception e) {
                        e.printStackTrace();
                    }
                
                
                 System.out.println("Deliver to location list is "+ServiceLocationsList.s_jobs.size());
                
                
                // contract types
                 
                restServiceAdapter = Model.createRestServiceAdapter();
                // Clear any previously set request properties, if any
                restServiceAdapter.clearRequestProperties();
                // Set the connection name
                restServiceAdapter.setConnectionName("enrich");
                
                restServiceAdapter.setRequestType(RestServiceAdapter.REQUEST_TYPE_POST);
                restServiceAdapter.addRequestProperty("Accept", "application/json; charset=UTF-8");
                restServiceAdapter.addRequestProperty("Authorization", "Basic " + "WFhFX1JFU1RfU0VSVklDRVNfQURNSU46b3JhY2xlMTIz");
                restServiceAdapter.addRequestProperty("Content-Type", "application/json");
                restServiceAdapter.setRequestURI("/webservices/rest/XXETailSpendAPI/get_contract_type_lkup/");
                postData= "{\n" + 
                "  \"GET_CONTRACT_TYPE_Input\" : {\n" + 
                "   \"RESTHeader\": {\n" + 
                "    },\n" + 
                "   \"InputParameters\": {\n" + 
                "       }	   \n" + 
                "   }\n" + 
                "}\n";
                                            restServiceAdapter.setRetryLimit(0);
                   System.out.println("postData===============================" + postData);
                    
                   response = restServiceAdapter.send(postData);
                    
                    System.out.println("response===============================" + response); 
                     resp=new JSONObject(response);
                     output=resp.getJSONObject("OutputParameters");
                     data=new JSONObject();
                 try{
                     data=output.getJSONObject("X_CONTRACT_TYPE_TL");
                    ContractLookupList.s_jobs.clear();
                     contractLookupList.clear();
                        
                    if(data.get("X_CONTRACT_TYPE_TL_ITEM") instanceof  JSONArray){
                      JSONArray segments=data.getJSONArray("X_CONTRACT_TYPE_TL_ITEM");
//                       Lookup c2=new Lookup("Please Select","0","Please Select",""); 
//                        ContractLookupList.s_jobs.add(c2);
                       
                      for(int i=0;i<segments.length();i++) {
                        JSONObject lookup=segments.getJSONObject(i);
                        String lookupType=lookup.getString("LOOKUP_TYPE");
                        String lookupCode=lookup.getString("LOOKUP_CODE");
                        String meaning=lookup.getString("MEANING");
                        String tag=lookup.getString("TAG");  
                        Lookup loc=new Lookup(lookupType, lookupCode, meaning,tag);
                        ContractLookupList.s_jobs.add(loc);
                          contractLookupList.add(loc);
                          
                          
                      }
                    
                    }
                    
                    else if(data.get("X_CONTRACT_TYPE_TL_ITEM") instanceof  JSONObject){
//                        Lookup c2=new Lookup("Please Select","0","Please Select",""); 
//                        ContractLookupList.s_jobs.add(c2);
                         
                        JSONObject lookup=data.getJSONObject("X_CONTRACT_TYPE_TL_ITEM");
                        String lookupType=lookup.getString("LOOKUP_TYPE");
                        String lookupCode=lookup.getString("LOOKUP_CODE");
                        String meaning=lookup.getString("MEANING");
                        String tag=lookup.getString("TAG");  
                        Lookup loc=new Lookup(lookupType, lookupCode, meaning,tag);
                        ContractLookupList.s_jobs.add(loc);
                        contractLookupList.add(loc);
                        
                    }
                    }
                    catch(Exception e) {
                        e.printStackTrace();
                    }
                 
                 
                 
                 // frequency periods
                 
                 restServiceAdapter = Model.createRestServiceAdapter();
                 // Clear any previously set request properties, if any
                 restServiceAdapter.clearRequestProperties();
                 // Set the connection name
                 restServiceAdapter.setConnectionName("enrich");
                 
                 restServiceAdapter.setRequestType(RestServiceAdapter.REQUEST_TYPE_POST);
                 restServiceAdapter.addRequestProperty("Accept", "application/json; charset=UTF-8");
                 restServiceAdapter.addRequestProperty("Authorization", "Basic " + "WFhFX1JFU1RfU0VSVklDRVNfQURNSU46b3JhY2xlMTIz");
                 restServiceAdapter.addRequestProperty("Content-Type", "application/json");
                 restServiceAdapter.setRequestURI("/webservices/rest/XXETailSpendAPI/get_freq_period_lkup/");
                 postData= "{\n" + 
                 "  \"GET_FREQ_PERIOD_LKUP_Input\" : {\n" + 
                 "   \"RESTHeader\": {\n" + 
                 "    },\n" + 
                 "   \"InputParameters\": {\n" + 
                 "       }	   \n" + 
                 "   }\n" + 
                 "}";
                                             restServiceAdapter.setRetryLimit(0);
                    System.out.println("postData===============================" + postData);
                     
                    response = restServiceAdapter.send(postData);
                     
                     System.out.println("response===============================" + response); 
                      resp=new JSONObject(response);
                      output=resp.getJSONObject("OutputParameters");
                      data=new JSONObject();
                  try{
                      data=output.getJSONObject("X_FREQ_PERIOD_TL");
                        FrequencyPeriodList.s_jobs.clear();
                      frequencyPeriodList.clear();
                         
                     if(data.get("X_FREQ_PERIOD_TL_ITEM") instanceof  JSONArray){
                       JSONArray segments=data.getJSONArray("X_FREQ_PERIOD_TL_ITEM");
                         Lookup c2=new Lookup("Please Select","0","Please Select",""); 
                          FrequencyPeriodList.s_jobs.add(c2);
                          frequencyPeriodList.add(c2);
                       for(int i=0;i<segments.length();i++) {
                         JSONObject lookup=segments.getJSONObject(i);
                         String lookupType=lookup.getString("LOOKUP_TYPE");
                         String lookupCode=lookup.getString("LOOKUP_CODE");
                         String meaning=lookup.getString("MEANING");
                         String tag=lookup.getString("TAG");  
                         Lookup loc=new Lookup(lookupType, lookupCode, meaning,tag);
                                FrequencyPeriodList.s_jobs.add(loc);
                           frequencyPeriodList.add(loc);
                           
                       }
                     
                     }
                     
                     else if(data.get("X_FREQ_PERIOD_TL_ITEM") instanceof  JSONObject){
                         Lookup c2=new Lookup("Please Select","0","Please Select",""); 
                          FrequencyPeriodList.s_jobs.add(c2);
                         frequencyPeriodList.add(c2);
                         JSONObject lookup=data.getJSONObject("X_FREQ_PERIOD_TL_ITEM");
                         String lookupType=lookup.getString("LOOKUP_TYPE");
                         String lookupCode=lookup.getString("LOOKUP_CODE");
                         String meaning=lookup.getString("MEANING");
                         String tag=lookup.getString("TAG");  
                         Lookup loc=new Lookup(lookupType, lookupCode, meaning,tag);
                            FrequencyPeriodList.s_jobs.add(loc);
                         frequencyPeriodList.add(loc);
                         
                     }
                     }
                     catch(Exception e) {
                         e.printStackTrace();
                     }
                 
                 
                 
                 // service frequency types
                  
                 restServiceAdapter = Model.createRestServiceAdapter();
                 // Clear any previously set request properties, if any
                 restServiceAdapter.clearRequestProperties();
                 // Set the connection name
                 restServiceAdapter.setConnectionName("enrich");
                 
                 restServiceAdapter.setRequestType(RestServiceAdapter.REQUEST_TYPE_POST);
                 restServiceAdapter.addRequestProperty("Accept", "application/json; charset=UTF-8");
                 restServiceAdapter.addRequestProperty("Authorization", "Basic " + "WFhFX1JFU1RfU0VSVklDRVNfQURNSU46b3JhY2xlMTIz");
                 restServiceAdapter.addRequestProperty("Content-Type", "application/json");
                 restServiceAdapter.setRequestURI("/webservices/rest/XXETailSpendAPI/get_service_freq_lkup/");
                 postData= "{\n" + 
                 "  \"GET_SERVICE_FREQ_LKUP_Input\" : {\n" + 
                 "   \"RESTHeader\": {\n" + 
                 "    },\n" + 
                 "   \"InputParameters\": {\n" + 
                 "       }	   \n" + 
                 "   }\n" + 
                 "}";
                                             restServiceAdapter.setRetryLimit(0);
                    System.out.println("postData===============================" + postData);
                     
                    response = restServiceAdapter.send(postData);
                     
                     System.out.println("response===============================" + response); 
                      resp=new JSONObject(response);
                      output=resp.getJSONObject("OutputParameters");
                      data=new JSONObject();
                  try{
                      data=output.getJSONObject("X_SERVICE_FREQUENCY_TL");
                     ServiceFrequencyTypeList.s_jobs.clear();
                      serviceFrequencyTypeList.clear();
                         
                     if(data.get("X_SERVICE_FREQUENCY_TL_ITEM") instanceof  JSONArray){
                       JSONArray segments=data.getJSONArray("X_SERVICE_FREQUENCY_TL_ITEM");
//                         Lookup c2=new Lookup("Please Select","0","Please Select",""); 
//                          ServiceFrequencyTypeList.s_jobs.add(c2);
                       for(int i=0;i<segments.length();i++) {
                         JSONObject lookup=segments.getJSONObject(i);
                         String lookupType=lookup.getString("LOOKUP_TYPE");
                         String lookupCode=lookup.getString("LOOKUP_CODE");
                         String meaning=lookup.getString("MEANING");
                         String tag=lookup.getString("TAG");  
                         Lookup loc=new Lookup(lookupType, lookupCode, meaning,tag);
                         ServiceFrequencyTypeList.s_jobs.add(loc);
                           serviceFrequencyTypeList.add(loc);
                           
                       }
                     
                     }
                     
                     else if(data.get("X_SERVICE_FREQUENCY_TL_ITEM") instanceof  JSONObject){
//                         Lookup c2=new Lookup("Please Select","0","Please Select",""); 
//                          ServiceFrequencyTypeList.s_jobs.add(c2);
                         JSONObject lookup=data.getJSONObject("X_SERVICE_FREQUENCY_TL_ITEM");
                         String lookupType=lookup.getString("LOOKUP_TYPE");
                         String lookupCode=lookup.getString("LOOKUP_CODE");
                         String meaning=lookup.getString("MEANING");
                         String tag=lookup.getString("TAG");  
                         Lookup loc=new Lookup(lookupType, lookupCode, meaning,tag);
                         ServiceFrequencyTypeList.s_jobs.add(loc);
                         serviceFrequencyTypeList.add(loc);
                         
                     }
                     }
                     catch(Exception e) {
                         e.printStackTrace();
                     }
                 
               
                  
                 
                
            }
            
            
            ValueExpression showFrequencyPeriod = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.showFrequencyPeriod}", String.class);
           showFrequencyPeriod.setValue(AdfmfJavaUtilities.getAdfELContext(),"false");
            
            
            ValueExpression showContractTypeAttb = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.showContractTypeAttrb}", String.class);
            showContractTypeAttb.setValue(AdfmfJavaUtilities.getAdfELContext(),"false");
            
            AdfmfJavaUtilities.flushDataChangeEvent();      
                  
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        
        //Defaulting UOM to each
        
       
        AdfmfJavaUtilities.flushDataChangeEvent();
        
        
        
        }
        else{
            AdfmfContainerUtilities.invokeContainerJavaScriptFunction(AdfmfJavaUtilities.getFeatureId(),
                                                                       "SearchTextEmpty",
                                                                       new Object[] { });
           /* AdfmfContainerUtilities.invokeContainerJavaScriptFunction(
                                         AdfmfJavaUtilities.getFeatureName(),
                                         "adf.mf.api.amx.addMessage", new Object[] {AdfException.ERROR,
                                         "Item Type is mandatory and cannot be empty.",
                                         null,
                                         null });   */ 
            
        }
            
        clearAttachments();    
        ValueExpression ve123 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.selectedImagesCount}", String.class);
        ve123.setValue(AdfmfJavaUtilities.getAdfELContext(),"");
        
        ImageList.imageList.clear();
        selectedImages.clear();
        Rest.selectedImages.clear();
        
            
    }
    
    
    public static void clearAttachments() {
        // Add event code here...
        
        try{
            //path for android
            File folder;
            
            ValueExpression ve15 = AdfmfJavaUtilities.getValueExpression("#{deviceScope.device.os}", String.class);
            String device_os=(String)ve15.getValue(AdfmfJavaUtilities.getAdfELContext());
            if(device_os.equalsIgnoreCase("iOS")){
                    String path=System.getProperty("user.dir");
                    path=path.replace("/Documents", "/tmp");
                    folder = new File(path);
                }
                else{
                    folder = new File("//data//data//com.enrich.goprocure//cache");    
                }
           // File folder = new File("//data//data//com.enrich.goprocure//cache");
            
            File[] listOfFiles = folder.listFiles();

            for (File file : listOfFiles) {
                if (file.isFile()) {
                    file.delete();
                }
            }           
            
        }catch(Exception e){
            e.printStackTrace();    
        }
        
            
    }
    
    public void onFrequencyPeriodChange(){
        ValueExpression ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.frequencyPeriodText}", String.class);
        String frequencyPeriodText = (String)ve.getValue(AdfmfJavaUtilities.getAdfELContext());
        String newValue="";
        
        
        if(frequencyPeriodText!=null && !frequencyPeriodText.equalsIgnoreCase("") && !frequencyPeriodText.equalsIgnoreCase("0")) {
            Lookup loc=(Lookup)ServiceFrequencyTypeList.s_jobs.get(Integer.parseInt(frequencyPeriodText));
            newValue=loc.getMeaning();
        }
        else{
            Lookup loc=(Lookup)ServiceFrequencyTypeList.s_jobs.get(0);
            newValue=loc.getMeaning();
        }
        
        
        System.out.println("Selected values is "+newValue.toString());
        
        if(newValue.toString().equalsIgnoreCase("Recurring")) {
            ValueExpression showFrequencyPeriod = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.showFrequencyPeriod}", String.class);
                      showFrequencyPeriod.setValue(AdfmfJavaUtilities.getAdfELContext(),"true");
            ValueExpression ve11 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.end_date}", String.class);
            ve11.setValue(AdfmfJavaUtilities.getAdfELContext(),"");
            ValueExpression ve12 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.need_by_date}", String.class);
            ve12.setValue(AdfmfJavaUtilities.getAdfELContext(),"");
            
                
        }
        else{
            ValueExpression showFrequencyPeriod = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.showFrequencyPeriod}", String.class);
                      showFrequencyPeriod.setValue(AdfmfJavaUtilities.getAdfELContext(),"false");
            ValueExpression ve11 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.end_date}", String.class);
            ve11.setValue(AdfmfJavaUtilities.getAdfELContext(),"");
            ValueExpression ve12 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.need_by_date}", String.class);
            ve12.setValue(AdfmfJavaUtilities.getAdfELContext(),"");
        }
        AdfmfJavaUtilities.flushDataChangeEvent();
    }
    
    public void onContractTypeChanged(){
        ValueExpression ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.contractText}", String.class);
        String contractText = (String)ve.getValue(AdfmfJavaUtilities.getAdfELContext());
        String newValue="";
        
        
        
        if(contractText!=null && !contractText.equalsIgnoreCase("") && !contractText.equalsIgnoreCase("0")) {
            Lookup loc=(Lookup)ContractLookupList.s_jobs.get(Integer.parseInt(contractText));
            newValue=loc.getMeaning();
        }
        else{
            Lookup loc=(Lookup)ContractLookupList.s_jobs.get(0);
            newValue=loc.getMeaning();
        }
        
        
        
        System.out.println("Selected values is "+newValue.toString());
        if(newValue.toString().equalsIgnoreCase("Time and Material")) {
            ValueExpression showContractTypeAttb = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.showContractTypeAttrb}", String.class);
                       showContractTypeAttb.setValue(AdfmfJavaUtilities.getAdfELContext(),"true");
        }
        else{
            ValueExpression showContractTypeAttb = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.showContractTypeAttrb}", String.class);
                       showContractTypeAttb.setValue(AdfmfJavaUtilities.getAdfELContext(),"false");
        }
    
        AdfmfJavaUtilities.flushDataChangeEvent();
    }
    
    
    public void serviceLocationChange(){
        ValueExpression contractTypeText = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.serviceLocationText}", String.class);
        String contractText=(String)contractTypeText.getValue(AdfmfJavaUtilities.getAdfELContext());
        
        ValueExpression ve = AdfmfJavaUtilities.getValueExpression("#{applicationScope.user_id}", String.class);
        String userId = (String)ve.getValue(AdfmfJavaUtilities.getAdfELContext());
        
        String newValue="";
        
        
        
        if(contractText!=null && !contractText.equalsIgnoreCase("") && !contractText.equalsIgnoreCase("0")) {
            ServiceLocation loc=(ServiceLocation)ServiceLocationsList.s_jobs.get(Integer.parseInt(contractText));
            newValue=loc.getCode();
        }
        else{
            ServiceLocation loc=(ServiceLocation)ServiceLocationsList.s_jobs.get(0);
            newValue=loc.getCode();
        }
        
        if(newValue.toString().equalsIgnoreCase("Multiple")) {
            
            try{
            
            //get deliver to list
            RestServiceAdapter restServiceAdapter = Model.createRestServiceAdapter();
            // Clear any previously set request properties, if any
            restServiceAdapter.clearRequestProperties();
            // Set the connection name
            restServiceAdapter.setConnectionName("enrich");
            
            restServiceAdapter.setRequestType(RestServiceAdapter.REQUEST_TYPE_POST);
            restServiceAdapter.addRequestProperty("Accept", "application/json; charset=UTF-8");
            restServiceAdapter.addRequestProperty("Authorization", "Basic " + "WFhFX1JFU1RfU0VSVklDRVNfQURNSU46b3JhY2xlMTIz");
            restServiceAdapter.addRequestProperty("Content-Type", "application/json");
            restServiceAdapter.setRequestURI("/webservices/rest/XXETailSpendAPI/get_deliver_to/");
            String postData= "{\n" + 
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
            "        \"P_USER_ID\":"+userId+"\n" + 
            "\n" + 
            "     }\n" + 
            "\n" + 
            "  }\n" + 
            "\n" + 
            "}  ";
                                        restServiceAdapter.setRetryLimit(0);
               System.out.println("postData===============================" + postData);
                
              String response = restServiceAdapter.send(postData);
                
                System.out.println("response===============================" + response); 
                 JSONObject resp=new JSONObject(response);
                JSONObject output=resp.getJSONObject("OutputParameters");
               JSONObject data=new JSONObject();
             try{
                 data=output.getJSONObject("X_DELIVER_TO_TL");
                DeliverToLocationList.s_jobs.clear();
                   
                if(data.get("X_DELIVER_TO_TL_ITEM") instanceof  JSONArray){
                  JSONArray segments=data.getJSONArray("X_DELIVER_TO_TL_ITEM");
                  for(int i=0;i<segments.length();i++) {
                    JSONObject location=segments.getJSONObject(i);
                    String locationId=location.getString("LOCATION_ID");
                    String locationCode=location.getString("LOCATION_CODE");
                    String locationDescription=location.getString("DESCRIPTION");
                    DeliverToLocation loc=new DeliverToLocation(locationId, locationCode, locationDescription);
                    DeliverToLocationList.s_jobs.add(loc);
                    
                  }
                
                }
                
                else if(data.get("X_DELIVER_TO_TL_ITEM") instanceof  JSONObject){
                   
                   JSONObject location=data.getJSONObject("X_DELIVER_TO_TL_ITEM");
                    String locationId=location.getString("LOCATION_ID");
                    String locationCode=location.getString("LOCATION_CODE");
                    String locationDescription=location.getString("DESCRIPTION");
                    DeliverToLocation loc=new DeliverToLocation(locationId, locationCode, locationDescription);
                    DeliverToLocationList.s_jobs.add(loc);
                   
                }
                }
                catch(Exception e) {
                    e.printStackTrace();
                }
            
            
                ValueExpression showMultipleLocation = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.showMultipleServiceLocations}", String.class);
                showMultipleLocation.setValue(AdfmfJavaUtilities.getAdfELContext(),"true");
            
                ValueExpression showCurrentLocations = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.showCurrentLocations}", String.class);
                showCurrentLocations.setValue(AdfmfJavaUtilities.getAdfELContext(),"false");                 
            
            Object errorMsg = AdfmfContainerUtilities.invokeContainerJavaScriptFunction(FeatureContext.getCurrentFeatureId(),
                                                                      "popupUtilsShowPopup", new Object[] {
                                                                      "_popShowServiceLocation" });
            
            
            }
            catch(Exception e){
                e.printStackTrace();
            }
            
        }
        else if(newValue.toString().equalsIgnoreCase("Current")){
            ValueExpression showMultipleLocation = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.showMultipleServiceLocations}", String.class);
            showMultipleLocation.setValue(AdfmfJavaUtilities.getAdfELContext(),"false");
            
            ValueExpression showCurrentLocations = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.showCurrentLocations}", String.class);
            showCurrentLocations.setValue(AdfmfJavaUtilities.getAdfELContext(),"true");  
            
            //Location currentPosition = DeviceManagerFactory.getDeviceManager().getCurrentPosition(60000, true);
            
            MethodExpression me = AdfmfJavaUtilities.getMethodExpression("#{bindings.startLocationMonitor.execute}", Object.class, new Class[] {});
            me.invoke(AdfmfJavaUtilities.getAdfELContext(), new Object[]{});
        }
        else{
            ValueExpression showMultipleLocation = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.showMultipleServiceLocations}", String.class);
            showMultipleLocation.setValue(AdfmfJavaUtilities.getAdfELContext(),"false");
            
            ValueExpression showCurrentLocations = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.showCurrentLocations}", String.class);
            showCurrentLocations.setValue(AdfmfJavaUtilities.getAdfELContext(),"false");  
        }
        
        AdfmfJavaUtilities.flushDataChangeEvent();
        
        
        
    }
    
    public void clearServiceLocations(){
        selectedServiceLocations.clear();
    }
    
    public void selectServiceLocations(){
        ValueExpression ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.multipleServiceLocations}", String.class);
        String serviceLocations = (String)ve.getValue(AdfmfJavaUtilities.getAdfELContext());
        
        for(int i=0;i<selectedServiceLocations.size();i++) {
           int indx=selectedServiceLocations.get(i);
           DeliverToLocation s=(DeliverToLocation)DeliverToLocationList.s_jobs.get(indx);
           if(serviceLocations==null || serviceLocations.equalsIgnoreCase("")) {
               
               serviceLocations=s.getCode();
           }
           else{
               serviceLocations=serviceLocations+","+s.getCode();
           }
           
        }
        
        System.out.println("Selected suppliers are "+serviceLocations);
        ve.setValue(AdfmfJavaUtilities.getAdfELContext(), serviceLocations);
        AdfmfJavaUtilities.flushDataChangeEvent();
    }
    public void onServiceLocationAdd(){
        ValueExpression ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.multipleServiceLocationSelection}", String.class);
        String serviceLocationNames = (String)ve.getValue(AdfmfJavaUtilities.getAdfELContext());
        selectedServiceLocations.add(new Integer(serviceLocationNames));
    }
    
    public void proceedRefinedSearchServices(){
        String itemCategories="";
        String quantity="";
        String needByDate="";
        String itemType="";
        boolean found=false;
        String resultCount="";
                  
        try{
            ItemsList.s_jobs.clear();
            ItemsList.items_list.clear();
            ValueExpression ve130 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.searchValue}", String.class);
            String search = (String)ve130.getValue(AdfmfJavaUtilities.getAdfELContext()); 
            
            ValueExpression res = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.resultCountServices}", String.class);
            resultCount = (String)res.getValue(AdfmfJavaUtilities.getAdfELContext()); 
            
            System.out.println("resultCount =>"+resultCount);
            

            ValueExpression ve411 = AdfmfJavaUtilities.getValueExpression("#{applicationScope.rdItemType}", String.class);
            itemType=(String)ve411.getValue(AdfmfJavaUtilities.getAdfELContext());
            
            
            ValueExpression ve41 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.searchType}", String.class);
            ve41.setValue(AdfmfJavaUtilities.getAdfELContext(), "R");
            
            ValueExpression ve1 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.item_categories}", String.class);
            itemCategories = (String)ve1.getValue(AdfmfJavaUtilities.getAdfELContext());
            
            ValueExpression ve5 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.quantity}", String.class);
            quantity = (String)ve5.getValue(AdfmfJavaUtilities.getAdfELContext());
            
            ValueExpression ve6 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.uom1}", String.class);
            String uom = (String)ve6.getValue(AdfmfJavaUtilities.getAdfELContext());
            
            ValueExpression ve61 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.searchValue}", String.class);
            String searchText = (String)ve61.getValue(AdfmfJavaUtilities.getAdfELContext());
            
            
            ValueExpression ve62 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.need_by_date}", String.class);
            needByDate = (String)ve62.getValue(AdfmfJavaUtilities.getAdfELContext());
            
            ValueExpression ve_supp = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.suppliers}", String.class);
            String supplierNames = (String)ve_supp.getValue(AdfmfJavaUtilities.getAdfELContext());
            
            ValueExpression ve = AdfmfJavaUtilities.getValueExpression("#{applicationScope.user_id}", String.class);
            String userName = (String)ve.getValue(AdfmfJavaUtilities.getAdfELContext());
            
            
            ValueExpression serviceFrequency = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.contractLookups1}", String.class);
            String txtServiceFrequency = (String)serviceFrequency.getValue(AdfmfJavaUtilities.getAdfELContext());
            
            
            ValueExpression contractType = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.contractLookups}", String.class);
            String txtContractType = (String)contractType.getValue(AdfmfJavaUtilities.getAdfELContext());
            
            ValueExpression serviceLocation = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.serviceLocations}", String.class);
            String txtServiceLocation = (String)serviceLocation.getValue(AdfmfJavaUtilities.getAdfELContext());
            System.out.println("serviceLocation => "+txtServiceLocation);
            
            ValueExpression serviceComments = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.rfqCommentsServices}", String.class);
            String txtServiceComments = (String)serviceComments.getValue(AdfmfJavaUtilities.getAdfELContext());
            
            ValueExpression multipleServiceLocations = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.multipleServiceLocations}", String.class);
            String txtMultipleServiceLocations = (String)multipleServiceLocations.getValue(AdfmfJavaUtilities.getAdfELContext());
            
            ValueExpression currentServiceLocation = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.currentServicelocation}", String.class);
            String txtCurrentServiceLocation = (String)currentServiceLocation.getValue(AdfmfJavaUtilities.getAdfELContext()); 
            
            ValueExpression frequencyPeriod = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.contractLookups2}", String.class);
            String txtFrequencyPeriod = (String)frequencyPeriod.getValue(AdfmfJavaUtilities.getAdfELContext()); 
            
            System.out.println("txtFrequencyPeriod => "+txtFrequencyPeriod);
            
            ValueExpression serviceEndDate = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.end_date}", String.class);
            String txtServiceEndDate = (String)serviceEndDate.getValue(AdfmfJavaUtilities.getAdfELContext()); 
        
        
                 
        
        
        Random randomGenerator = new Random();
        int randomInt = randomGenerator.nextInt(100);
        
        ve = AdfmfJavaUtilities.getValueExpression("#{applicationScope.user_name}", String.class);
        userName = (String)ve.getValue(AdfmfJavaUtilities.getAdfELContext());
        
        ValueExpression ve121 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.searchValue}", String.class);
        String productTitle = (String)ve121.getValue(AdfmfJavaUtilities.getAdfELContext());
        //
        
        StringBuffer sb = new StringBuffer("[\n");
        sb.append("{\n");
        sb.append("    \"SEARCH_ID\":\""+randomInt+"\",\n");
        sb.append("    \"DEVICE_SERIAL_ID\":\"ABCD\",\n");
        sb.append("    \"USER_ID\":\""+userName+"\",\n");
        sb.append("    \"SEARCH_TYPE\":\"R\",\n");
        sb.append("    \"SEARCH_TEXT\":\""+productTitle+"\",\n");
        sb.append("    \"RESULT_COUNT\":\""+resultCount+"\",\n");
        sb.append("    \"REQUEST_TYPE\":\"RFQ\"\n");
        sb.append("},");
        
        String header_value = sb.substring(0, sb.length() - 1).concat("]");
        /////////////////////
        ValueExpression ve191 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.uom1}", String.class);
        String u1=(String)ve191.getValue(AdfmfJavaUtilities.getAdfELContext());
        String u="";
        if(!u1.equalsIgnoreCase("")){
           UOM uo=(UOM)UOMList.s_jobs.get((Integer.parseInt(u1)));
           u=uo.getName();
           
        }
        else{
            UOM uo=(UOM)UOMList.s_jobs.get(0);
            u=uo.getName();
        }
            
            
            if(!txtContractType.equalsIgnoreCase("")){
               //Lookup uo=(Lookup)ContractLookupList.s_jobs.get((Integer.parseInt(txtContractType)));
               Lookup uo=(Lookup)contractLookupList.get((Integer.parseInt(txtContractType)));
               txtContractType=uo.getCode();
               
            }
            else{
                //Lookup uo=(Lookup)ContractLookupList.s_jobs.get(0);
                Lookup uo=(Lookup)contractLookupList.get(0);
               txtContractType=uo.getCode();
            }
            
            
            if(!txtServiceFrequency.equalsIgnoreCase("")){
              // Lookup uo=(Lookup)ServiceFrequencyTypeList.s_jobs.get((Integer.parseInt(txtServiceFrequency)));
              Lookup uo=(Lookup)serviceFrequencyTypeList.get((Integer.parseInt(txtServiceFrequency)));
               txtServiceFrequency=uo.getCode();
               
            }
            else{
                //Lookup uo=(Lookup)ServiceFrequencyTypeList.s_jobs.get(0);
                Lookup uo=(Lookup)serviceFrequencyTypeList.get(0);
               txtServiceFrequency=uo.getCode();
            }
            
            
            if(!txtFrequencyPeriod.equalsIgnoreCase("")){
               //Lookup uo=(Lookup)FrequencyPeriodList.s_jobs.get((Integer.parseInt(txtFrequencyPeriod)));                
                Lookup uo=(Lookup)frequencyPeriodList.get((Integer.parseInt(txtFrequencyPeriod)));
               txtFrequencyPeriod=uo.getCode();
               
            }
            else{
                //Lookup uo=(Lookup)FrequencyPeriodList.s_jobs.get(0);
                Lookup uo=(Lookup)frequencyPeriodList.get(0);
               txtFrequencyPeriod=uo.getCode();
            }
            
            
            
            if(!txtServiceLocation.equalsIgnoreCase("")){
               ServiceLocation uo=(ServiceLocation)serviceLocationList.get((Integer.parseInt(txtServiceLocation)));
               txtServiceLocation=uo.getCode();
               
            }
            else{
                ServiceLocation uo=(ServiceLocation)serviceLocationList.get(0);
               txtServiceLocation=uo.getCode();
            }
            
            
                

        sb = new StringBuffer("[\n");
        
        
        //  for(int i=0;i<ItemsList.items_ref.size();i++)
        //  {
           // Item it=(Item)ItemsList.items_ref.get(i);
        sb.append("{\n");
        sb.append("    \"SEARCH_ID\":\""+randomInt+"\",\n");
        sb.append("    \"PRODUCT_SOURCE\":\"U\",\n");
        sb.append("    \"PRODUCT_CATEGORY\":\""+itemCategories+"\",\n");
        sb.append("    \"PRODUCT_TITLE\":\""+productTitle+"\",\n");
        sb.append("    \"QUANTITY\":\""+quantity+"\",\n");
        sb.append("    \"UOM_CODE\":\""+u+"\",\n");
        sb.append("    \"UNIT_PRICE\":\"\",\n");
        sb.append("    \"CURRENCY_CODE\":\"USD\",\n");
        sb.append("    \"DELIVER_TO_LOCATION\":\""+txtServiceLocation+"\",\n");
        String arr[]=needByDate.split("T");
        sb.append("    \"NEED_BY_DATE\":\""+arr[0]+"\",\n");
        sb.append("    \"SELECTED_FLAG\":\"Y\",\n");
        sb.append("    \"ITEM_TYPE\":\""+itemType+"\", \n");
        sb.append("    \"COST_CENTER\":\"\",\n");
        sb.append("    \"CHARGE_ACCOUNT\":\"\",\n");
        sb.append("    \"MARKUP_PRICE\":\"\",\n");
        sb.append("    \"REQUISITION_HEADER_ID\":\"\",\n");
        sb.append("    \"REQUISITION_LINE_ID\":\"\",\n");
        
        ValueExpression veComments = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.rfqCommentsServices}", String.class);
        String comments=(String)veComments.getValue(AdfmfJavaUtilities.getAdfELContext());
        sb.append("    \"COMMENTS\":\""+URLEncoder.encode(comments)+"\", \n");
        
        ValueExpression veImage = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.rfqImage}", String.class);
        String img=(String)veImage.getValue(AdfmfJavaUtilities.getAdfELContext());
        sb.append("    \"ATTACHMENT_FILE\":\""+img+"\",\n");
            if(!img.equalsIgnoreCase("")){
                sb.append("    \"CONTRACT_ITEM_EXCEPTION\":\"\",\n");
                 
                sb.append("    \"INDIX_CATEGORY_ID\":\"\",\n");
                sb.append("    \"FILE_NAME\":\""+randomInt+".jpg\",\n");
                sb.append("    \"FILE_CONTENT_TYPE\":\"image/jpeg\",\n");
                sb.append("    \"FILE_FORMAT\":\"image\",\n");
                sb.append("    \"CONTRACT_TYPE\":\""+txtContractType+"\", \n");
                sb.append("    \"SERVICE_FREQUENCY\":\""+txtServiceFrequency+"\", \n");
                sb.append("    \"FREQUENCY_PERIOD\":\""+txtFrequencyPeriod+"\", \n");
            }
            else{
                sb.append("    \"CONTRACT_ITEM_EXCEPTION\":\"\",\n");
                
                sb.append("    \"INDIX_CATEGORY_ID\":\"\",\n");
                sb.append("    \"FILE_NAME\":\"\",\n");
                sb.append("    \"FILE_CONTENT_TYPE\":\"\",\n");
                sb.append("    \"FILE_FORMAT\":\"\",\n");
                sb.append("    \"CONTRACT_TYPE\":\""+txtContractType+"\", \n");
                sb.append("    \"SERVICE_FREQUENCY\":\""+txtServiceFrequency+"\", \n");
                sb.append("    \"FREQUENCY_PERIOD\":\""+txtFrequencyPeriod+"\", \n");
            }    
            
            
            
       
       if(!txtServiceEndDate.equalsIgnoreCase("")){
             arr=txtServiceEndDate.split("T");
             sb.append("    \"END_DATE\":\""+arr[0]+"\", \n");
         }
        else{
                sb.append("    \"END_DATE\":\"\", \n");
            }
        sb.append("    \"SERVICE_LOCATION\":\""+txtServiceLocation+"\", \n");
        sb.append("    \"MULTIPLE_LOCATION\":\""+txtMultipleServiceLocations+"\", \n");
        sb.append("    \"CURRENT_LOCATION\":\""+txtCurrentServiceLocation+"\"\n");
        sb.append("},");
            
        //  }

        String body_value = sb.substring(0, sb.length() - 1).concat("]");
           
           
           
        sb = new StringBuffer("[\n");
        
        
        
            
        sb.append("{\n");
        sb.append("    \"SEARCH_ID\":\""+randomInt+"\",\n");
        sb.append("    \"VENDOR_NAME\":\"\",\n");
        sb.append("    \"VENDOR_SITE\":\"\",\n");
        sb.append("    \"VENDOR_SITE_ADDRESS1\":\"\",\n");
        sb.append("    \"VENDOR_SITE_CITY\":\"\",\n");
        sb.append("    \"VENDOR_SITE_STATE\":\"\",\n");
        sb.append("    \"VENDOR_SITE_ZIP\":\"\",\n");
        sb.append("    \"VENDOR_SITE_COUNTRY\":\"\",\n");
        sb.append("    \"VENDOR_CONTACT_LAST_NAME\":\"\",\n");
        sb.append("    \"VENDOR_CONTACT_PHONE\":\"\",\n");
        sb.append("    \"VENDOR_CONTACT_EMAIL\":\"\"\n");
        sb.append("},");
            
        
           
        String vendor_value = sb.substring(0, sb.length() - 1).concat("]");
            
            
       //checkout 
//       sb = new StringBuffer("[\n");
//       for(int i=0;i<selectedImages.size();i++) {
//                System.out.println("Selected Images are ==>"+selectedImages.get(i));
//                String s=selectedImages.get(i);
//                String filepath[]=s.split("/");
//                int length=filepath.length;
//                String filename=filepath[length-1];
//                System.out.println("File name is ==>"+filename);
//           
//                sb = new StringBuffer("[\n");
//           
//           
//                sb.append("{\n");
//                sb.append("    \"SEARCH_ID\":\""+randomInt+"\",\n");
//                sb.append("    \"ATTACHMENT_FILE\":\""+EncodeBased64Binary.encodeFileToBase64Binary(selectedImages.get(i))+"\",\n");
//                sb.append("    \"FILE_NAME\":\""+filename+"\",\n");
//                sb.append("    \"FILE_CONTENT_TYPE\":\"image/jpeg\",\n");
//                sb.append("    \"FILE_FORMAT\":\"image\"\n");
//                sb.append("},");
//                
//                
//            }
//            
//            String image_value = sb.substring(0, sb.length() - 1).concat("]");
//            
            
        
        
        RestServiceAdapter restServiceAdapter = Model.createRestServiceAdapter();
        // Clear any previously set request properties, if any
        restServiceAdapter.clearRequestProperties();
        // Set the connection name
        restServiceAdapter.setConnectionName("enrich");
        
        restServiceAdapter.setRequestType(RestServiceAdapter.REQUEST_TYPE_POST);
        restServiceAdapter.addRequestProperty("Accept", "application/json; charset=UTF-8");
        restServiceAdapter.addRequestProperty("Authorization", "Basic " + "WFhFX1JFU1RfU0VSVklDRVNfQURNSU46b3JhY2xlMTIz");
        restServiceAdapter.addRequestProperty("Content-Type", "application/json");
        restServiceAdapter.setRequestURI("/webservices/rest/XXEReqService/submit_requisition/");
        
//        String data= "{\n" + 
//        "  \"CHECKOUT_Input\" : {\n" +
//        "   \"@xmlns\" : \"http://xmlns.oracle.com/apps/po/rest/XXETailSpendAPI/checkout/\",\n" +
//        "   \"RESTHeader\": {\n" +
//        "   \"@xmlns\" : \"http://xmlns.oracle.com/apps/po/rest/XXETailSpendAPI/header\"\n" +
//        "    },\n" +
//        "   \"InputParameters\": {\n" +
//        "        \"P_SEARCH_HEADER\": {\"P_SEARCH_HEADER_ITEM\": \n"+header_value+"\n" +
//        "       },\n" +
//        "        \"P_SEARCH_LINES\": {\"P_SEARCH_LINES_ITEM\": \n"+body_value+"         \n" +
//        "       }, \n" +
//        "        \"P_RFQ_VENDORS\": {\"P_RFQ_VENDORS_ITEM\": \n"+vendor_value+"         \n" +
//        "       }, \n" +
//        "        \"P_CHECKOUT_FILES\": {\"P_CHECKOUT_FILES_ITEM\": \n"+image_value+"         \n" +
//        "       } \n" +              
//        "      }\n" +
//        "   }\n" +
//        "}\n";

           String data= "{\n" + 
            "  \"CHECKOUT_Input\" : {\n" +
            "   \"@xmlns\" : \"http://xmlns.oracle.com/apps/po/rest/XXEReqService/submit_requisition/\",\n" +
            "   \"RESTHeader\": {\n" +
            "   \"@xmlns\" : \"http://xmlns.oracle.com/apps/po/rest/XXEReqService/header\"\n" +
            "    },\n" +
            "   \"InputParameters\": {\n" +
            "        \"P_SEARCH_HEADER\": {\"P_SEARCH_HEADER_ITEM\": \n"+header_value+"\n" +
            "       },\n" +
            "        \"P_SEARCH_LINES\": {\"P_SEARCH_LINES_ITEM\": \n"+body_value+"\n" +
            "       }, \n" +
            "        \"P_RFQ_VENDORS\": {\"P_RFQ_VENDORS_ITEM\": \n"+vendor_value+"\n" +
            "       } \n" +           
            "      }\n" +
            "   }\n" +
            "}\n";
            

        restServiceAdapter.setRetryLimit(0);
        System.out.println("postData===============================" + data);
        String response = restServiceAdapter.send(data);
        System.out.println("response===============================" + response);
        
        //*-*-*-*-*-*-*-*-*-*-*-*
        //get groupid from requitision
        JSONObject groupIdResp=new JSONObject(response);
        JSONObject groupIdRespOutput = groupIdResp.getJSONObject("OutputParameters");
        String groupId = groupIdRespOutput.getString("X_SEARCH_GROUP_ID");
        String groupIdStatus = groupIdRespOutput.getString("X_RETURN_STATUS");
        
        
        //checkout 
        // sb = new StringBuffer("[\n");
        for(int i=0;i<selectedImages.size();i++) {
                 System.out.println("Selected Images are ==>"+selectedImages.get(i));
                 String s=selectedImages.get(i);
                 String filepath[]=s.split("/");
                 int length=filepath.length;
                 String filename=filepath[length-1];
                 System.out.println("File name is ==>"+filename);
            sb = new StringBuffer("[\n");
            
                 sb.append("{\n");
                 sb.append("    \"SEARCH_ID\":\""+randomInt+"\",\n");
                 sb.append("    \"ATTACHMENT_FILE\":\""+EncodeBased64Binary.encodeFileToBase64Binary(selectedImages.get(i))+"\",\n");
                 sb.append("    \"FILE_NAME\":\""+filename+"\",\n");
                 sb.append("    \"FILE_CONTENT_TYPE\":\"image/jpeg\",\n");
                 sb.append("    \"FILE_FORMAT\":\"image\"\n");
                 sb.append("},");
             
             
        String image_value = sb.substring(0, sb.length() - 1).concat("]");    
        
         restServiceAdapter = Model.createRestServiceAdapter();
         // Clear any previously set request properties, if any
         restServiceAdapter.clearRequestProperties();
         // Set the connection name
         restServiceAdapter.setConnectionName("enrich");
         
         restServiceAdapter.setRequestType(RestServiceAdapter.REQUEST_TYPE_POST);
         restServiceAdapter.addRequestProperty("Accept", "application/json; charset=UTF-8");
         restServiceAdapter.addRequestProperty("Authorization", "Basic " + "WFhFX1JFU1RfU0VSVklDRVNfQURNSU46b3JhY2xlMTIz");
         restServiceAdapter.addRequestProperty("Content-Type", "application/json");
         restServiceAdapter.setRequestURI("/webservices/rest/XXEReqService/submit_attachments/");
        
        String imageRequest = "{\n" + 
                                "\n" + 
                                "    \"SUBMIT_ATTACHMENTS_Input\":{\n" + 
                                "        \"@xmlns\":\"http://xmlns.oracle.com/apps/po/rest/XXEReqService/submit_attachments/\",\n" + 
                                "        \"RESTHeader\":{\n" + 
                                "            \"@xmlns\":\"http://xmlns.oracle.com/apps/po/rest/XXEReqService/header\"\n" + 
                                "        },\n" + 
                                "        \"InputParameters\":{\n" + 
                                "            \"P_SEARCH_GROUP_ID\":\""+groupId+"\",\n" + 
                                "            \"P_CHECKOUT_FILES\":{\n" + 
                                "                \"P_CHECKOUT_FILES_ITEM\":"+image_value+"\n" + 
                                
                                "            }\n" + 
                                "        }\n" + 
                                "    }\n" + 
                                "\n" + 
                                "}";
        
        restServiceAdapter.setRetryLimit(0);
        System.out.println("postData===============================" + imageRequest);
        response = restServiceAdapter.send(imageRequest);
        System.out.println("response===============================" + response);
        //-----------------------
        }
        
        
        restServiceAdapter = Model.createRestServiceAdapter();
         // Clear any previously set request properties, if any
         restServiceAdapter.clearRequestProperties();
         // Set the connection name
         restServiceAdapter.setConnectionName("enrich");
         
         restServiceAdapter.setRequestType(RestServiceAdapter.REQUEST_TYPE_POST);
         restServiceAdapter.addRequestProperty("Accept", "application/json; charset=UTF-8");
         restServiceAdapter.addRequestProperty("Authorization", "Basic " + "WFhFX1JFU1RfU0VSVklDRVNfQURNSU46b3JhY2xlMTIz");
         restServiceAdapter.addRequestProperty("Content-Type", "application/json");
         restServiceAdapter.setRequestURI("/webservices/rest/XXEReqService/start_requisition_process/");
        
         String startRequisition = "{\n" + 
                                     "\n" + 
                                     "    \"START_REQUISITION_PROCESS_Input\":{\n" + 
                                     "        \"@xmlns\":\"http://xmlns.oracle.com/apps/po/rest/XXEReqService/start_requisition_process/\",\n" + 
                                     "        \"RESTHeader\":{\n" + 
                                     "            \"@xmlns\":\"http://xmlns.oracle.com/apps/po/rest/XXEReqService/header\"\n" + 
                                     "        },\n" + 
                                     "        \"InputParameters\":{\n" + 
                                     "            \"P_SEARCH_GROUP_ID\":\""+groupId+"\"\n" + 
                                     "        }\n" + 
                                     "    }\n" + 
                                     "\n" + 
                                     "}";
         
        restServiceAdapter.setRetryLimit(0);
        System.out.println("postData===============================" + startRequisition);
        response = restServiceAdapter.send(startRequisition);
        System.out.println("response===============================" + response);    
        
        //*-*-*-*-*-*-*-*-*-*-*-
        
        itemCategories="";
        quantity="";
        needByDate="";
        itemType="";
        found=false;
        
        
        
        AdfmfContainerUtilities.invokeContainerJavaScriptFunction(AdfmfJavaUtilities.getFeatureId(),
                                                                   "displayAlert",
                                                                   new Object[] { });
        
        
        ValueExpression ve11 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.item_categories}", String.class);
        ve11.setValue(AdfmfJavaUtilities.getAdfELContext(),"");
        ValueExpression ve12 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.suppliers}", String.class);
        ve12.setValue(AdfmfJavaUtilities.getAdfELContext(),"");
        ValueExpression ve13 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.web_url}", String.class);
        ve13.setValue(AdfmfJavaUtilities.getAdfELContext(),"");
        ValueExpression ve14 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.part_no}", String.class);
        ve14.setValue(AdfmfJavaUtilities.getAdfELContext(),"");
        ValueExpression ve15 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.brand}", String.class);
        ve15.setValue(AdfmfJavaUtilities.getAdfELContext(),"");
        ValueExpression ve16 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.upc}", String.class);
        ve16.setValue(AdfmfJavaUtilities.getAdfELContext(),"");
        ValueExpression ve17 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.quantity}", String.class);
        ve17.setValue(AdfmfJavaUtilities.getAdfELContext(),"");
        ValueExpression ve18 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.uom1}", String.class);
        ve18.setValue(AdfmfJavaUtilities.getAdfELContext(),"");
        
        
        
        ValueExpression ve19 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.displayAddToCart}", String.class);
        ve19.setValue(AdfmfJavaUtilities.getAdfELContext(),"false");
        
        ValueExpression ve20 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.displaySearchCount}", String.class);
        ve20.setValue(AdfmfJavaUtilities.getAdfELContext(),"false");
         
        veComments = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.rfqComments}", String.class);
        veComments.setValue(AdfmfJavaUtilities.getAdfELContext(),"");
                    
         veImage = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.rfqImage}", String.class);
         veImage.setValue(AdfmfJavaUtilities.getAdfELContext(),"");
            
         serviceFrequency = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.contractLookups1}", String.class);
         serviceFrequency.setValue(AdfmfJavaUtilities.getAdfELContext(),"");
            
            
         contractType = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.contractLookups}", String.class);
         contractType.setValue(AdfmfJavaUtilities.getAdfELContext(),"");
            
         serviceLocation = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.serviceLocations}", String.class);
          serviceLocation.setValue(AdfmfJavaUtilities.getAdfELContext(),"");
            
         serviceComments = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.rfqCommentsServices}", String.class);
            serviceComments.setValue(AdfmfJavaUtilities.getAdfELContext(),"");
            
          multipleServiceLocations = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.multipleServiceLocations}", String.class);
            multipleServiceLocations.setValue(AdfmfJavaUtilities.getAdfELContext(),"");
            
          currentServiceLocation = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.currentServicelocation}", String.class);
            currentServiceLocation.setValue(AdfmfJavaUtilities.getAdfELContext(),"");
            
          frequencyPeriod = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.contractLookups2}", String.class);
          frequencyPeriod.setValue(AdfmfJavaUtilities.getAdfELContext(),"");
            
          serviceEndDate = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.end_date}", String.class);
          serviceEndDate.setValue(AdfmfJavaUtilities.getAdfELContext(),"");
            
            
        ValueExpression veImageCount = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.selectedImagesCount}", String.class);
        veImageCount.setValue(AdfmfJavaUtilities.getAdfELContext(),"");                
        ValueExpression need_by_date = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.need_by_date}", String.class);
        need_by_date.setValue(AdfmfJavaUtilities.getAdfELContext(),""); 
            
            
        
        
        AdfmfJavaUtilities.flushDataChangeEvent();
            
            File folder;
            
            ValueExpression vf16 = AdfmfJavaUtilities.getValueExpression("#{deviceScope.device.os}", String.class);
            String device_os=(String)vf16.getValue(AdfmfJavaUtilities.getAdfELContext());
            if(device_os.equalsIgnoreCase("iOS")){
                    String path=System.getProperty("user.dir");
                    path=path.replace("/Documents", "/tmp");
                    folder = new File(path);
                }
                else{
                    folder = new File("//data//data//com.enrich.goprocure//cache");    
                }
            // File folder = new File("//data//data//com.enrich.goprocure//cache");
            
            File[] listOfFiles = folder.listFiles();

            for (File file : listOfFiles) {
                if (file.isFile()) {
                    file.delete();
                }
            }
            
            
        
//            File folder = new File("//data//data//com.enrich.goprocure//cache");
//            File[] listOfFiles = folder.listFiles();
//
//            for (File file : listOfFiles) {
//                 file.delete();
//            }
            selectedImages.clear();
            Rest.selectedImages.clear();
            ImageList.imageList.clear();
            
            ValueExpression ve10 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.selectedImagesCount}", String.class);
            ve10.setValue(AdfmfJavaUtilities.getAdfELContext(),"");
            
        
//        AdfmfContainerUtilities.invokeContainerJavaScriptFunction(AdfmfJavaUtilities.getFeatureName(),
//                                                                       "adf.mf.api.amx.doNavigation", new Object[] { "ref_search" });   
        
        
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
        
        
        ////////////////////
    }
    
    
    public void LocationHandler(Location currentPosition) {
        
        AdfmfContainerUtilities.invokeContainerJavaScriptFunction(
                                     AdfmfJavaUtilities.getFeatureName(),
                                     "adf.mf.api.amx.addMessage", new Object[] {AdfException.ERROR,
                                     "Lat:"+currentPosition.getLatitude()+" Long:"+currentPosition.getLongitude(),
                                     null,
                                     null });    
    }
    
    public void addToSelectedImage(){
        
        ValueExpression imagePath = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.refinedImagePath}", String.class);
        String path = (String)imagePath.getValue(AdfmfJavaUtilities.getAdfELContext());
        if(!selectedImages.contains(path)){
                selectedImages.add(path);
                Image img=new Image(path);
                ImageList.imageList.add(img);
        }
        
        
//        for(int i=0;i<selectedImages.size();i++) {
//            System.out.println("Selected Images are ==>"+selectedImages.get(i));
//            String s=selectedImages.get(i);
//            String filepath[]=s.split("/");
//            int length=filepath.length;
//            String filename=filepath[length-1];
//            System.out.println("File name is ==>"+filename);
//            
//        }
        
    }
    
    public void clearSelectedImage(){
        selectedImages.clear();
        Rest.selectedImages.clear();
        ImageList.imageList.clear();
    }
  
    public void proceedRefinedSearch()  {
        String itemCategories="";
        String quantity="";
        String needByDate="";
        String itemType="";
        boolean found=false;  
        try{
            ItemsList.s_jobs.clear();
            ItemsList.items_list.clear();
            ValueExpression ve130 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.searchValue}", String.class);
            String search = (String)ve130.getValue(AdfmfJavaUtilities.getAdfELContext()); 
            

            ValueExpression ve411 = AdfmfJavaUtilities.getValueExpression("#{applicationScope.rdItemType}", String.class);
            itemType=(String)ve411.getValue(AdfmfJavaUtilities.getAdfELContext());
            
            
            ValueExpression ve41 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.searchType}", String.class);
            ve41.setValue(AdfmfJavaUtilities.getAdfELContext(), "R");
            
            ValueExpression ve1 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.item_categories}", String.class);
            itemCategories = (String)ve1.getValue(AdfmfJavaUtilities.getAdfELContext());
            
            ValueExpression ve2 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.part_no}", String.class);
            String partNo = (String)ve2.getValue(AdfmfJavaUtilities.getAdfELContext());
            
            ValueExpression ve3 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.brand}", String.class);
            String brand = (String)ve3.getValue(AdfmfJavaUtilities.getAdfELContext());
            
            ValueExpression ve4 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.upc}", String.class);
            String upc = (String)ve4.getValue(AdfmfJavaUtilities.getAdfELContext());
            
            ValueExpression ve5 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.quantity}", String.class);
            quantity = (String)ve5.getValue(AdfmfJavaUtilities.getAdfELContext());
            
            ValueExpression ve6 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.uom1}", String.class);
            String uom = (String)ve6.getValue(AdfmfJavaUtilities.getAdfELContext());
            
            ValueExpression ve61 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.searchValue}", String.class);
            String searchText = (String)ve61.getValue(AdfmfJavaUtilities.getAdfELContext());
            
            
            ValueExpression ve62 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.need_by_date}", String.class);
            needByDate = (String)ve62.getValue(AdfmfJavaUtilities.getAdfELContext());
            
            ValueExpression ve_supp = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.suppliers}", String.class);
            String supplierNames = (String)ve_supp.getValue(AdfmfJavaUtilities.getAdfELContext());
            
            ValueExpression ve_product_url = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.web_url}", String.class);
            String product_url = (String)ve_product_url.getValue(AdfmfJavaUtilities.getAdfELContext());
            
       
            System.out.println("Inside proceed refined Search");
        //send the oracle category and find equivalent indix category
       RestServiceAdapter restServiceAdapter = Model.createRestServiceAdapter();
        // Clear any previously set request properties, if any
        restServiceAdapter.clearRequestProperties();
        // Set the connection name
        restServiceAdapter.setConnectionName("enrich");
        
        restServiceAdapter.setRequestType(RestServiceAdapter.REQUEST_TYPE_POST);
        restServiceAdapter.addRequestProperty("Accept", "application/json; charset=UTF-8");
        restServiceAdapter.addRequestProperty("Authorization", "Basic " + "WFhFX1JFU1RfU0VSVklDRVNfQURNSU46b3JhY2xlMTIz");
        restServiceAdapter.addRequestProperty("Content-Type", "application/json");
        restServiceAdapter.setRequestURI("/webservices/rest/XXETailSpendAPI/get_indix_category/");
        String data= "{\n" + 
        "\n" + 
        "  \"GET_INDIX_CATEGORY_Input\" : {\n" + 
        "\n" + 
        "   \"@xmlns\" : \"http://xmlns.oracle.com/apps/po/rest/XXETailSpendAPI/get_indix_category/\",\n" + 
        "\n" + 
        "   \"RESTHeader\": {\n" + 
        "\n" + 
        "   \"@xmlns\" : \"http://xmlns.oracle.com/apps/po/rest/XXETailSpendAPI/header\"\n" + 
        "    },\n" + 
        "\n" + 
        "   \"InputParameters\": {\n" + 
        "\n" + 
        "        \"P_ORACLE_CATEGORY\": \""+itemCategories+"\"\n" + 
        "\n" + 
        "      }         \n" + 
        "\n" + 
        "   }\n" + 
        "\n" + 
        "}";                         
        restServiceAdapter.setRetryLimit(0);
        System.out.println("postData 1212 ===============================" + data);
        String response = restServiceAdapter.send(data);
        System.out.println("response1213 ===============================" + response);
        JSONObject resp=new JSONObject(response);
        JSONObject output=resp.getJSONObject("OutputParameters");
        String indix_category=output.getString("X_INDIX_CATEGORY");
        //System.out.println("indix_category===============================" + indix_category);
    //        //quey indix to find categoryId for found category
    //
    //
    //        String url = "https://api.indix.com/v2/categories?app_id=9867e55c&app_key=8d79be1be9b9d8ce50af3a978b4d5ccc";
    //
    //        URL obj = new URL(url);
    //        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
    //        // optional default is GET
    //        con.setRequestMethod("GET");
    //        //add request header
    //        int responseCode = con.getResponseCode();
    //        System.out.println("\nSending 'GET' request to URL : " + url);
    //        System.out.println("Response Code : " + responseCode);
    //        BufferedReader in = new BufferedReader(
    //           new InputStreamReader(con.getInputStream()));
    //        String inputLine;
    //        StringBuffer response1 = new StringBuffer();
    //        while ((inputLine = in.readLine()) != null) {
    //                  response1.append(inputLine);
    //             }
    //           in.close();
    //             //print result
    //        //    System.out.println(response1.toString());
    //        resp=new JSONObject(response1.toString());
    //        output=resp.getJSONObject("result");
    //        JSONArray resArr=output.getJSONArray("categories");
    //        int categoryId=0;
    //        for(int i=0;i<resArr.length();i++) {
    //            JSONObject resObj=(JSONObject)resArr.get(i);
    //            if(indix_category.equalsIgnoreCase(resObj.getString("name"))) {
    //                System.out.println("************* Match occurs *********************");
    //                categoryId=Integer.parseInt(resObj.getString("id"));
    //
    //                //******************test************************
    //            }
    //
    //        }
            int categoryId=0;
            
            JSONObject indix_category_tl=output.getJSONObject("X_INDIX_CATEGORY_TL");   
            String categoryRef="&";
         if(indix_category_tl.get("X_INDIX_CATEGORY_TL_ITEM") instanceof JSONArray){
               
            JSONArray items=(JSONArray)indix_category_tl.get("X_INDIX_CATEGORY_TL_ITEM");
            for(int i=0;i<items.length();i++){
              categoryRef=categoryRef+"categoryId="+items.getString(i)+"&";
                categoryId=1;
            }
                
          }
          if(indix_category_tl.get("X_INDIX_CATEGORY_TL_ITEM") instanceof String){
              categoryRef=categoryRef+"categoryId="+indix_category_tl.getString("X_INDIX_CATEGORY_TL_ITEM")+"&";   
              categoryId=2;
                
          }
            System.out.println("indix_category===============================" + indix_category+" categoryId "+categoryId);
            ArrayList<String> suppliers=new ArrayList<String>(); 
            int resultSize=0;
            
            if(categoryId!=0){
        
      
      //String  url = "https://api.indix.com/v2/offersPremium/products?countryCode=US"+categoryRef+"facetBy=storeId&availability=IN_STOCK&lastRecordedIn=30&app_id=9867e55c&app_key=8d79be1be9b9d8ce50af3a978b4d5ccc";
       String  url = "https://api.indix.com/v2/universal/products?countryCode=US"+categoryRef+"facetBy=storeId&availability=IN_STOCK&lastRecordedIn=30&app_id=9867e55c&app_key=8d79be1be9b9d8ce50af3a978b4d5ccc";
        //quey indix for the category and facet by suppliers                                        
       URL obj = new URL(url);
     HttpURLConnection   con = (HttpURLConnection) obj.openConnection();
        // optional default is GET
        con.setRequestMethod("GET");
        //add request header
       int responseCode = con.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);
       BufferedReader in = new BufferedReader(
                   new InputStreamReader(con.getInputStream()));
                String inputLine;
      StringBuffer  response1 = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
              response1.append(inputLine);
             }
         in.close();
         //print result
            System.out.println(response1.toString());
        resp=new JSONObject(response1.toString());
        output=resp.getJSONObject("result");
        JSONObject jsobj=output.getJSONObject("facets");
         JSONArray supplierArr=jsobj.getJSONArray("storeId");
         JSONArray resArr=output.getJSONArray("products");
        // System.out.println("resArr.length() "+resArr.length());
       
         for(int i=0;i<resArr.length();i++) {
              int rowCount=1;
               //    System.out.println("***********");
               JSONObject productObj=resArr.getJSONObject(i);
         //     System.out.println(productObj.toString());
           //   System.out.println("***********");
                String poNo="";
                String vendorName="";
                String vendorSiteCode="";
                String productCategory=productObj.getString("categoryName");
                String productTitle=productObj.getString("title");
                //String unitPrice=productObj.getString("minSalePrice");
                //String imageUrl=productObj.getString("imageUrl");
                String indixCategoryId=productObj.getString("categoryId");
                JSONObject stores=productObj.getJSONObject("stores");
                                                     
                Iterator<?> keys = stores.keys();
                while( keys.hasNext() ) {
                     String key = (String)keys.next();
                     JSONObject store=stores.getJSONObject(key);
                     vendorName=store.getString("storeName");
                   
                    Random randomGenerator = new Random();
                    int randomInt = randomGenerator.nextInt(1000000000);
                    String showDiverSeImage="false";
                    String diverseImageURL="";
                      /////
                      JSONArray Offer=store.getJSONArray("offers");
                             
                               for(int k=0;k<Offer.length();k++) {
                                   rowCount=1;
                                   JSONObject offer=Offer.getJSONObject(k);
                                   String imageUrl=offer.getString("imageUrl");
                                   String seller=offer.getString("seller");
                               String unitPrice=offer.getString("salePrice");
                               System.out.println("*-*-*-Image Url is "+imageUrl+"*-*-*-Seller Is"+seller+"*-*-*-*-Seller Price"+unitPrice);
                               String showSeller="true";
                               if (seller.equalsIgnoreCase("")) {
                                                       showSeller="false";
                                                   }
                                   
                               JSONObject attValues=offer.getJSONObject("attributes");
                               ItemsList.Specification.clear();
                               String showAttrib="true";
                               String showSpec="true";
                               String resultVal = "";
                               String spec = "";
                                  System.out.println(offer+"======>num===>"+attValues.length());
                               if (attValues.length() == 0) {
                                  System.out.println("Length 0");
                                  showAttrib = "false";
                                  showSpec="false";
                                  Specification.clear();
                                  /*BasicIterator vex = (BasicIterator) AdfmfJavaUtilities.getELValue("#{bindings.assets5.iterator}");
                                  vex.refresh();*/
                               }else{
                                 
                                 Iterator<?> att = attValues.keys(); 
                                 if(attValues.length() == 1){
                                     StringBuffer sb2 = new StringBuffer();
                                     System.out.println("Length 1");
                                     showAttrib = "true";
                                     showSpec="false";
                                     while(att.hasNext()) {
                                         String attributeKey = (String)att.next();
                               //                                                                          System.out.println("JSON Key Single---------------->"+attributeKey);
                                         JSONArray attributeValues = attValues.getJSONArray(attributeKey);
                               //                                                                          System.out.println("JSON Value Single ---------------->"+attributeValues);
                                         resultVal =attributeKey+" : "+attributeValues.getString(0);
                                         
                                         System.out.println("");
                                         ItemsList.Specification.add(resultVal+"#");
                               //                                                                          System.out.println("KEY&Value---------------->"+resultVal);
                               //                                                                          System.out.println("Single Attributes---------------->"+Specification);
                                         sb2.append(resultVal+"#,");
                                     }
                                     /*BasicIterator vex = (BasicIterator) AdfmfJavaUtilities.getELValue("#{bindings.assets5.iterator}");
                                     vex.refresh();*/
                               spec = sb2.toString();
                               System.out.println("Value at 1===> "+spec);
                               Specification.clear();
                               }else{
                               System.out.println("Length >1");
                               StringBuffer sb2 = new StringBuffer();
                               showAttrib = "false";
                               showSpec="true";
                               while(att.hasNext()) {
                                   String attributeKey = (String)att.next();
                                   JSONArray attributeValues = attValues.getJSONArray(attributeKey);
                                   resultVal =attributeKey+" : "+attributeValues.getString(0);
                                   ItemsList.Specification.add(resultVal+"#");
                                   /*
                                   for(int a=0; a < attributeValues.length(); a++) {
                                       resultVal =attributeKey+" : "+attributeValues.getString(a);
                                       ItemsList.Specification.add(resultVal);    
                               //                                                                              System.out.println("MultiAttribute Json<------------->"+attributeKey+"  :  "+resultVal);
                                   }
                                   */
                                 sb2.append(resultVal+"#,");
                                   
                               //                                                                          System.out.println("Multi-Attribute LIST <------------->"+spec);
                              /* BasicIterator vex = (BasicIterator) AdfmfJavaUtilities.getELValue("#{bindings.assets5.iterator}");
                               vex.refresh();*/
                               }
                               //spec = Specification.toString();
                               spec = sb2.toString();
                               System.out.println("Value at >1===> "+spec);
                               Specification.clear();
                               }  
                                
                                
                                
                               }
                              
                            
                               
                    Item j = new Item(poNo, vendorName, vendorSiteCode, productCategory, productTitle, unitPrice,imageUrl,"/images/uncheck.png","","Each",String.valueOf(randomInt),"-1",showDiverSeImage,diverseImageURL,pageNo,indixCategoryId,"","","","","","");
                    ItemsList.items_ref.add(j); 
                  //   System.out.println("***********");
                               }
                }
             
             
                  }
        
        
          resultSize=output.getJSONArray("products").length();
        
        for(int i=0;i<supplierArr.length();i++) {
            JSONObject jsObj=(JSONObject)supplierArr.get(i);
            suppliers.add(jsObj.getString("name"));
        }
        
        
        
            }
            else{
                suppliers=new ArrayList<String>(); 
                resultSize=0;
            }
        
        //make RFQ with suppliers 
            System.out.println("Supplier Size is ========>"+suppliers.size());
        
        if(suppliers.size()>0) {
            
            Random randomGenerator = new Random();
            int randomInt = randomGenerator.nextInt(100);
            ValueExpression ve = AdfmfJavaUtilities.getValueExpression("#{applicationScope.user_name}", String.class);
            String userName = (String)ve.getValue(AdfmfJavaUtilities.getAdfELContext());
            
            ValueExpression ve121 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.searchValue}", String.class);
            String productTitle = (String)ve121.getValue(AdfmfJavaUtilities.getAdfELContext());
            
            
            StringBuffer sb = new StringBuffer("[\n");
            sb.append("{\n");
            sb.append("    \"SEARCH_ID\":\""+randomInt+"\",\n");
            sb.append("    \"DEVICE_SERIAL_ID\":\"ABCD\",\n");
            sb.append("    \"USER_ID\":\""+userName+"\",\n");
            sb.append("    \"SEARCH_TYPE\":\"R\",\n");
            sb.append("    \"SEARCH_TEXT\":\""+productTitle+"\",\n");
            sb.append("    \"RESULT_COUNT\":\""+resultSize+"\",\n");
            sb.append("    \"REQUEST_TYPE\":\"RFQ\"\n");
            sb.append("},");
            
            String header_value = sb.substring(0, sb.length() - 1).concat("]");
            System.out.println("header_value===============================" + header_value);
            
            
            ValueExpression ve191 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.uom1}", String.class);
            String u1=(String)ve191.getValue(AdfmfJavaUtilities.getAdfELContext());
            String u="";
            if(!u1.equalsIgnoreCase("")){
               UOM uo=(UOM)UOMList.s_jobs.get((Integer.parseInt(u1)));
               u=uo.getName();
               
            }
            else{
                UOM uo=(UOM)UOMList.s_jobs.get(0);
                u=uo.getName();
            }

            sb = new StringBuffer("[\n");
           
            
          //  for(int i=0;i<ItemsList.items_ref.size();i++)
          //  {
               // Item it=(Item)ItemsList.items_ref.get(i);
            sb.append("{\n");
            sb.append("    \"SEARCH_ID\":\""+randomInt+"\",\n");
            sb.append("    \"PRODUCT_SOURCE\":\"U\",\n");
            sb.append("    \"PRODUCT_CATEGORY\":\""+itemCategories+"\",\n");
            sb.append("    \"PRODUCT_TITLE\":\""+productTitle+"\",\n");
            sb.append("    \"QUANTITY\":\""+quantity+"\",\n");
            sb.append("    \"UOM_CODE\":\""+u+"\",\n");
            sb.append("    \"UNIT_PRICE\":\"\",\n");
            sb.append("    \"CURRENCY_CODE\":\"USD\",\n");
            sb.append("    \"DELIVER_TO_LOCATION\":\"Metlife\",\n");
            String arr[]=needByDate.split("T");
            sb.append("    \"NEED_BY_DATE\":\""+arr[0]+"\",\n");
            sb.append("    \"SELECTED_FLAG\":\"Y\",\n");
            sb.append("    \"ITEM_TYPE\":\""+itemType+"\", \n");
            sb.append("    \"COST_CENTER\":\"\",\n");
            sb.append("    \"CHARGE_ACCOUNT\":\"\",\n");
            sb.append("    \"MARKUP_PRICE\":\"\",\n");
            sb.append("    \"REQUISITION_HEADER_ID\":\"\",\n");
            sb.append("    \"REQUISITION_LINE_ID\":\"\",\n");
            
            ValueExpression veComments = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.rfqComments}", String.class);
            String comments=(String)veComments.getValue(AdfmfJavaUtilities.getAdfELContext());
            sb.append("    \"COMMENTS\":\""+URLEncoder.encode(comments)+"\", \n");
            
            ValueExpression veImage = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.rfqImage}", String.class);
            String img=(String)veImage.getValue(AdfmfJavaUtilities.getAdfELContext());
            sb.append("    \"ATTACHMENT_FILE\":\""+img+"\",\n");
            
            if(!img.equalsIgnoreCase("")){
            sb.append("    \"CONTRACT_ITEM_EXCEPTION\":\"\",\n");
            sb.append("    \"INDIX_CATEGORY_ID\":\"\",\n");
            sb.append("    \"FILE_NAME\":\""+randomInt+".jpg\",\n");
            sb.append("    \"FILE_CONTENT_TYPE\":\"image/jpeg\",\n");
            sb.append("    \"FILE_FORMAT\":\"image\"\n");
            }
            else{
                sb.append("    \"CONTRACT_ITEM_EXCEPTION\":\"\",\n");
                sb.append("    \"INDIX_CATEGORY_ID\":\"\",\n");
                sb.append("    \"FILE_NAME\":\"\",\n");
                sb.append("    \"FILE_CONTENT_TYPE\":\"\",\n");
                sb.append("    \"FILE_FORMAT\":\"\"\n");
            }
            
            sb.append("},");
                
          //  }

            String body_value = sb.substring(0, sb.length() - 1).concat("]");
               
               
            
               
               
            sb = new StringBuffer("[\n");
            
            
            for(int i=0;i<suppliers.size();i++)
            {
                
            sb.append("{\n");
            sb.append("    \"SEARCH_ID\":\""+randomInt+"\",\n");
            sb.append("    \"VENDOR_NAME\":\""+suppliers.get(i)+"\",\n");
            sb.append("    \"VENDOR_SITE\":\"\",\n");
            sb.append("    \"VENDOR_SITE_ADDRESS1\":\"\",\n");
            sb.append("    \"VENDOR_SITE_CITY\":\"\",\n");
            sb.append("    \"VENDOR_SITE_STATE\":\"\",\n");
            sb.append("    \"VENDOR_SITE_ZIP\":\"\",\n");
            sb.append("    \"VENDOR_SITE_COUNTRY\":\"\",\n");
            sb.append("    \"VENDOR_CONTACT_LAST_NAME\":\"\",\n");
            sb.append("    \"VENDOR_CONTACT_PHONE\":\"\",\n");
            sb.append("    \"VENDOR_CONTACT_EMAIL\":\"\"\n");
            sb.append("},");
                
            }
               
            String vendor_value = sb.substring(0, sb.length() - 1).concat("]");
            
        
           restServiceAdapter = Model.createRestServiceAdapter();
            // Clear any previously set request properties, if any
            restServiceAdapter.clearRequestProperties();
            // Set the connection name
            restServiceAdapter.setConnectionName("enrich");
            
            restServiceAdapter.setRequestType(RestServiceAdapter.REQUEST_TYPE_POST);
            restServiceAdapter.addRequestProperty("Accept", "application/json; charset=UTF-8");
            restServiceAdapter.addRequestProperty("Authorization", "Basic " + "WFhFX1JFU1RfU0VSVklDRVNfQURNSU46b3JhY2xlMTIz");
            restServiceAdapter.addRequestProperty("Content-Type", "application/json");
            restServiceAdapter.setRequestURI("/webservices/rest/XXEReqService/submit_requisition/");
        
    //            data= "{\n" +
    //        "  \"CHECKOUT_Input\" : {\n" +
    //        "   \"@xmlns\" : \"http://xmlns.oracle.com/apps/po/rest/XXETailSpendAPI/checkout/\",\n" +
    //        "   \"RESTHeader\": {\n" +
    //        "   \"@xmlns\" : \"http://xmlns.oracle.com/apps/po/rest/XXETailSpendAPI/header\"\n" +
    //        "    },\n" +
    //        "   \"InputParameters\": {\n" +
    //        "        \"P_SEARCH_HEADER\": {\"P_SEARCH_HEADER_ITEM\": \n"+header_value+"\n" +
    //        "       },\n" +
    //        "        \"P_SEARCH_LINES\": {\"P_SEARCH_LINES_ITEM\": \n"+body_value+"         \n" +
    //        "       }, \n" +
    //            "        \"P_RFQ_VENDORS\": {\"P_RFQ_VENDORS_ITEM\": \n"+vendor_value+"         \n" +
    //            "       }, \n" +
    //            "        \"P_CHECKOUT_FILES\": {\"P_CHECKOUT_FILES_ITEM\": \n"+image_value+"         \n" +
    //            "       } \n" +
    //            "      }\n" +
    //            "   }\n" +
    //            "}\n";
            
            data= "{\n" + 
            "  \"CHECKOUT_Input\" : {\n" +
            "   \"@xmlns\" : \"http://xmlns.oracle.com/apps/po/rest/XXEReqService/submit_requisition/\",\n" +
            "   \"RESTHeader\": {\n" +
            "   \"@xmlns\" : \"http://xmlns.oracle.com/apps/po/rest/XXEReqService/header\"\n" +
            "    },\n" +
            "   \"InputParameters\": {\n" +
            "        \"P_SEARCH_HEADER\": {\"P_SEARCH_HEADER_ITEM\": \n"+header_value+"\n" +
            "       },\n" +
            "        \"P_SEARCH_LINES\": {\"P_SEARCH_LINES_ITEM\": \n"+body_value+"\n" +
            "       }, \n" +
            "        \"P_RFQ_VENDORS\": {\"P_RFQ_VENDORS_ITEM\": \n"+vendor_value+"\n" +
            "       } \n" +           
            "      }\n" +
            "   }\n" +
            "}\n";

            restServiceAdapter.setRetryLimit(0);
            System.out.println("postData===============================" + data);
            response = restServiceAdapter.send(data);
            System.out.println("response===============================" + response);
            
            //get groupid from requitision
            JSONObject groupIdResp=new JSONObject(response);
            JSONObject groupIdRespOutput = groupIdResp.getJSONObject("OutputParameters");
            String groupId = groupIdRespOutput.getString("X_SEARCH_GROUP_ID");
            String groupIdStatus = groupIdRespOutput.getString("X_RETURN_STATUS");
            
            
            //checkout 
           // sb = new StringBuffer("[\n");
            for(int i=0;i<selectedImages.size();i++) {
                     System.out.println("Selected Images are ==>"+selectedImages.get(i));
                     String s=selectedImages.get(i);
                     String filepath[]=s.split("/");
                     int length=filepath.length;
                     String filename=filepath[length-1];
                     System.out.println("File name is ==>"+filename);
                sb = new StringBuffer("[\n");
                
                     sb.append("{\n");
                     sb.append("    \"SEARCH_ID\":\""+randomInt+"\",\n");
                     sb.append("    \"ATTACHMENT_FILE\":\""+EncodeBased64Binary.encodeFileToBase64Binary(selectedImages.get(i))+"\",\n");
                     sb.append("    \"FILE_NAME\":\""+filename+"\",\n");
                     sb.append("    \"FILE_CONTENT_TYPE\":\"image/jpeg\",\n");
                     sb.append("    \"FILE_FORMAT\":\"image\"\n");
                     sb.append("},");
                 
                 
            String image_value = sb.substring(0, sb.length() - 1).concat("]");    
            
             restServiceAdapter = Model.createRestServiceAdapter();
             // Clear any previously set request properties, if any
             restServiceAdapter.clearRequestProperties();
             // Set the connection name
             restServiceAdapter.setConnectionName("enrich");
             
             restServiceAdapter.setRequestType(RestServiceAdapter.REQUEST_TYPE_POST);
             restServiceAdapter.addRequestProperty("Accept", "application/json; charset=UTF-8");
             restServiceAdapter.addRequestProperty("Authorization", "Basic " + "WFhFX1JFU1RfU0VSVklDRVNfQURNSU46b3JhY2xlMTIz");
             restServiceAdapter.addRequestProperty("Content-Type", "application/json");
             restServiceAdapter.setRequestURI("/webservices/rest/XXEReqService/submit_attachments/");
            
            String imageRequest = "{\n" + 
                                    "\n" + 
                                    "    \"SUBMIT_ATTACHMENTS_Input\":{\n" + 
                                    "        \"@xmlns\":\"http://xmlns.oracle.com/apps/po/rest/XXEReqService/submit_attachments/\",\n" + 
                                    "        \"RESTHeader\":{\n" + 
                                    "            \"@xmlns\":\"http://xmlns.oracle.com/apps/po/rest/XXEReqService/header\"\n" + 
                                    "        },\n" + 
                                    "        \"InputParameters\":{\n" + 
                                    "            \"P_SEARCH_GROUP_ID\":\""+groupId+"\",\n" + 
                                    "            \"P_CHECKOUT_FILES\":{\n" + 
                                    "                \"P_CHECKOUT_FILES_ITEM\":"+image_value+"\n" + 
                                    
                                    "            }\n" + 
                                    "        }\n" + 
                                    "    }\n" + 
                                    "\n" + 
                                    "}";
            
            restServiceAdapter.setRetryLimit(0);
            System.out.println("postData===============================" + imageRequest);
            response = restServiceAdapter.send(imageRequest);
            System.out.println("response===============================" + response);
            //-----------------------
            }
            
            
            restServiceAdapter = Model.createRestServiceAdapter();
             // Clear any previously set request properties, if any
             restServiceAdapter.clearRequestProperties();
             // Set the connection name
             restServiceAdapter.setConnectionName("enrich");
             
             restServiceAdapter.setRequestType(RestServiceAdapter.REQUEST_TYPE_POST);
             restServiceAdapter.addRequestProperty("Accept", "application/json; charset=UTF-8");
             restServiceAdapter.addRequestProperty("Authorization", "Basic " + "WFhFX1JFU1RfU0VSVklDRVNfQURNSU46b3JhY2xlMTIz");
             restServiceAdapter.addRequestProperty("Content-Type", "application/json");
             restServiceAdapter.setRequestURI("/webservices/rest/XXEReqService/start_requisition_process/");
            
             String startRequisition = "{\n" + 
                                         "\n" + 
                                         "    \"START_REQUISITION_PROCESS_Input\":{\n" + 
                                         "        \"@xmlns\":\"http://xmlns.oracle.com/apps/po/rest/XXEReqService/start_requisition_process/\",\n" + 
                                         "        \"RESTHeader\":{\n" + 
                                         "            \"@xmlns\":\"http://xmlns.oracle.com/apps/po/rest/XXEReqService/header\"\n" + 
                                         "        },\n" + 
                                         "        \"InputParameters\":{\n" + 
                                         "            \"P_SEARCH_GROUP_ID\":\""+groupId+"\"\n" + 
                                         "        }\n" + 
                                         "    }\n" + 
                                         "\n" + 
                                         "}";
             
            restServiceAdapter.setRetryLimit(0);
            System.out.println("postData===============================" + startRequisition);
            response = restServiceAdapter.send(startRequisition);
            System.out.println("response===============================" + response);
            //-----------------------
            
            
            
            itemCategories="";
            quantity="";
            needByDate="";
            itemType="";
            found=false;
            
           
           
            AdfmfContainerUtilities.invokeContainerJavaScriptFunction(AdfmfJavaUtilities.getFeatureId(),
                                                                       "displayAlert",
                                                                       new Object[] { });
            
            
            ValueExpression ve11 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.item_categories}", String.class);
            ve11.setValue(AdfmfJavaUtilities.getAdfELContext(),"");
            ValueExpression ve12 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.suppliers}", String.class);
            ve12.setValue(AdfmfJavaUtilities.getAdfELContext(),"");
            ValueExpression ve13 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.web_url}", String.class);
            ve13.setValue(AdfmfJavaUtilities.getAdfELContext(),"");
            ValueExpression ve14 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.part_no}", String.class);
            ve14.setValue(AdfmfJavaUtilities.getAdfELContext(),"");
            ValueExpression ve15 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.brand}", String.class);
            ve15.setValue(AdfmfJavaUtilities.getAdfELContext(),"");
            ValueExpression ve16 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.upc}", String.class);
            ve16.setValue(AdfmfJavaUtilities.getAdfELContext(),"");
            ValueExpression ve17 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.quantity}", String.class);
            ve17.setValue(AdfmfJavaUtilities.getAdfELContext(),"");
            ValueExpression ve18 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.uom1}", String.class);
            ve18.setValue(AdfmfJavaUtilities.getAdfELContext(),"");
           
           
            /*ValueExpression ve19 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.displayAddToCart}", String.class);
            ve19.setValue(AdfmfJavaUtilities.getAdfELContext(),"true");*/
            
            ValueExpression ve19 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.displayAddToCart}", String.class);
            ve19.setValue(AdfmfJavaUtilities.getAdfELContext(),"false");
            
            ValueExpression ve20 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.displaySearchCount}", String.class);
            ve20.setValue(AdfmfJavaUtilities.getAdfELContext(),"false");
             
            veComments = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.rfqComments}", String.class);
            veComments.setValue(AdfmfJavaUtilities.getAdfELContext(),"");
                        
             veImage = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.rfqImage}", String.class);
             veImage.setValue(AdfmfJavaUtilities.getAdfELContext(),"");
            
            
            ValueExpression veImageCount = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.selectedImagesCount}", String.class);
            veImageCount.setValue(AdfmfJavaUtilities.getAdfELContext(),"");
            
            AdfmfJavaUtilities.flushDataChangeEvent();
            
            selectedImages.clear();
            
            MethodExpression me = AdfmfJavaUtilities.getMethodExpression("#{bindings.clearSelectedImage.execute}", Object.class, new Class[] {});
            me.invoke(AdfmfJavaUtilities.getAdfELContext(), new Object[]{});
            
           
    //            AdfmfContainerUtilities.invokeContainerJavaScriptFunction(AdfmfJavaUtilities.getFeatureName(),
    //                                                                           "adf.mf.api.amx.doNavigation", new Object[] { "ref_search" });
            
        }
        else{
            
            //if no suppliers are found from indix trigger the manual triage process
            
            if(!found){
            
            Random randomGenerator = new Random();
            int randomInt = randomGenerator.nextInt(100);
            ValueExpression ve = AdfmfJavaUtilities.getValueExpression("#{applicationScope.user_name}", String.class);
            String userName = (String)ve.getValue(AdfmfJavaUtilities.getAdfELContext());
            
            ValueExpression ve121 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.searchValue}", String.class);
            String productTitle = (String)ve121.getValue(AdfmfJavaUtilities.getAdfELContext());
            
            
            StringBuffer sb = new StringBuffer("[\n");
            sb.append("{\n");
            sb.append("    \"SEARCH_ID\":\""+randomInt+"\",\n");
            sb.append("    \"DEVICE_SERIAL_ID\":\"ABCD\",\n");
            sb.append("    \"USER_ID\":\""+userName+"\",\n");
            sb.append("    \"SEARCH_TYPE\":\"R\",\n");
            sb.append("    \"SEARCH_TEXT\":\""+productTitle+"\",\n");
            sb.append("    \"RESULT_COUNT\":\""+resultSize+"\",\n");
            sb.append("    \"REQUEST_TYPE\":\"MANUAL_TRIAGE\"\n");
            sb.append("},");
            
            String header_value = sb.substring(0, sb.length() - 1).concat("]");
            System.out.println("header_value===============================" + header_value);
            
                ValueExpression ve191 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.uom1}", String.class);
                String u1=(String)ve191.getValue(AdfmfJavaUtilities.getAdfELContext());
                String u="";
                if(!u1.equalsIgnoreCase("")){
                   UOM uo=(UOM)UOMList.s_jobs.get((Integer.parseInt(u1)));
                   u=uo.getName();
                   
                }
                else{
                    UOM uo=(UOM)UOMList.s_jobs.get(0);
                    u=uo.getName();
                }


            sb = new StringBuffer("[\n");
            
            
            //  for(int i=0;i<ItemsList.items_ref.size();i++)
            //  {
               // Item it=(Item)ItemsList.items_ref.get(i);
            sb.append("{\n");
            sb.append("    \"SEARCH_ID\":\""+randomInt+"\",\n");
            sb.append("    \"PRODUCT_SOURCE\":\"U\",\n");
            sb.append("    \"PRODUCT_CATEGORY\":\""+itemCategories+"\",\n");
            sb.append("    \"PRODUCT_TITLE\":\""+productTitle+"\",\n");
            sb.append("    \"QUANTITY\":\""+quantity+"\",\n");
            sb.append("    \"UOM_CODE\":\""+u+"\",\n");
            sb.append("    \"UNIT_PRICE\":\"\",\n");
            sb.append("    \"CURRENCY_CODE\":\"USD\",\n");
            sb.append("    \"DELIVER_TO_LOCATION\":\"Metlife\",\n");
            String arr[]=needByDate.split("T");
            sb.append("    \"NEED_BY_DATE\":\""+arr[0]+"\",\n");
            sb.append("    \"SELECTED_FLAG\":\"Y\",\n");
                sb.append("    \"ITEM_TYPE\":\""+itemType+"\", \n");
                sb.append("    \"COST_CENTER\":\"\",\n");
                sb.append("    \"CHARGE_ACCOUNT\":\"\",\n");
                sb.append("    \"MARKUP_PRICE\":\"\",\n");
                sb.append("    \"REQUISITION_HEADER_ID\":\"\",\n");
                sb.append("    \"REQUISITION_LINE_ID\":\"\",\n");
                
                ValueExpression veComments = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.rfqComments}", String.class);
                String comments=(String)veComments.getValue(AdfmfJavaUtilities.getAdfELContext());
                sb.append("    \"COMMENTS\":\""+URLEncoder.encode(comments)+"\", \n");
                
                ValueExpression veImage = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.rfqImage}", String.class);
                String img=(String)veImage.getValue(AdfmfJavaUtilities.getAdfELContext());
                sb.append("    \"ATTACHMENT_FILE\":\""+img+"\",\n");
                if(!img.equalsIgnoreCase("")){
                sb.append("    \"CONTRACT_ITEM_EXCEPTION\":\"\",\n");
                sb.append("    \"INDIX_CATEGORY_ID\":\"\",\n");
                sb.append("    \"FILE_NAME\":\""+randomInt+".jpg\",\n");
                sb.append("    \"FILE_CONTENT_TYPE\":\"image/jpeg\",\n");
                sb.append("    \"FILE_FORMAT\":\"image\"\n");
                }
                else{
                    sb.append("    \"CONTRACT_ITEM_EXCEPTION\":\"\",\n");
                    sb.append("    \"INDIX_CATEGORY_ID\":\"\",\n");
                    sb.append("    \"FILE_NAME\":\"\",\n");
                    sb.append("    \"FILE_CONTENT_TYPE\":\"\",\n");
                    sb.append("    \"FILE_FORMAT\":\"\"\n");
                }
                sb.append("},");
                
            //  }

            String body_value = sb.substring(0, sb.length() - 1).concat("]");
               
            
            restServiceAdapter = Model.createRestServiceAdapter();
            // Clear any previously set request properties, if any
            restServiceAdapter.clearRequestProperties();
            // Set the connection name
            restServiceAdapter.setConnectionName("enrich");
            
            restServiceAdapter.setRequestType(RestServiceAdapter.REQUEST_TYPE_POST);
            restServiceAdapter.addRequestProperty("Accept", "application/json; charset=UTF-8");
            restServiceAdapter.addRequestProperty("Authorization", "Basic " + "WFhFX1JFU1RfU0VSVklDRVNfQURNSU46b3JhY2xlMTIz");
            restServiceAdapter.addRequestProperty("Content-Type", "application/json");
            restServiceAdapter.setRequestURI("/webservices/rest/XXEReqService/submit_requisition/");
            
    //            data= "{\n" +
    //            "  \"CHECKOUT_Input\" : {\n" +
    //            "   \"@xmlns\" : \"http://xmlns.oracle.com/apps/po/rest/XXETailSpendAPI/checkout/\",\n" +
    //            "   \"RESTHeader\": {\n" +
    //            "   \"@xmlns\" : \"http://xmlns.oracle.com/apps/po/rest/XXETailSpendAPI/header\"\n" +
    //            "    },\n" +
    //            "   \"InputParameters\": {\n" +
    //            "        \"P_SEARCH_HEADER\": {\"P_SEARCH_HEADER_ITEM\": \n"+header_value+"\n" +
    //            "       },\n" +
    //            "        \"P_SEARCH_LINES\": {\"P_SEARCH_LINES_ITEM\": \n"+body_value+"         \n" +
    //            "       } \n" +
    //            "      }\n" +
    //            "   }\n" +
    //            "}\n";
            
            data= "{\n" + 
            "  \"CHECKOUT_Input\" : {\n" +
            "   \"@xmlns\" : \"http://xmlns.oracle.com/apps/po/rest/XXEReqService/submit_requisition/\",\n" +
            "   \"RESTHeader\": {\n" +
            "   \"@xmlns\" : \"http://xmlns.oracle.com/apps/po/rest/XXEReqService/header\"\n" +
            "    },\n" +
            "   \"InputParameters\": {\n" +
            "        \"P_SEARCH_HEADER\": {\"P_SEARCH_HEADER_ITEM\": \n"+header_value+"\n" +
            "       },\n" +
            "        \"P_SEARCH_LINES\": {\"P_SEARCH_LINES_ITEM\": \n"+body_value+"         \n" +
            "       } \n" +
            "      }\n" +
            "   }\n" +
            "}\n";

            restServiceAdapter.setRetryLimit(0);
            System.out.println("postData===============================" + data);
            response = restServiceAdapter.send(data);
            System.out.println("response===============================" + response);
            //**********************
                //get groupid from requitision
                JSONObject groupIdResp=new JSONObject(response);
                JSONObject groupIdRespOutput = groupIdResp.getJSONObject("OutputParameters");
                String groupId = groupIdRespOutput.getString("X_SEARCH_GROUP_ID");
                String groupIdStatus = groupIdRespOutput.getString("X_RETURN_STATUS");
                
                
                //checkout 
                // sb = new StringBuffer("[\n");
                for(int i=0;i<selectedImages.size();i++) {
                         System.out.println("Selected Images are ==>"+selectedImages.get(i));
                         String s=selectedImages.get(i);
                         String filepath[]=s.split("/");
                         int length=filepath.length;
                         String filename=filepath[length-1];
                         System.out.println("File name is ==>"+filename);
                    sb = new StringBuffer("[\n");
                    
                         sb.append("{\n");
                         sb.append("    \"SEARCH_ID\":\""+randomInt+"\",\n");
                         sb.append("    \"ATTACHMENT_FILE\":\""+EncodeBased64Binary.encodeFileToBase64Binary(selectedImages.get(i))+"\",\n");
                         sb.append("    \"FILE_NAME\":\""+filename+"\",\n");
                         sb.append("    \"FILE_CONTENT_TYPE\":\"image/jpeg\",\n");
                         sb.append("    \"FILE_FORMAT\":\"image\"\n");
                         sb.append("},");
                     
                     
                String image_value = sb.substring(0, sb.length() - 1).concat("]");    
                
                 restServiceAdapter = Model.createRestServiceAdapter();
                 // Clear any previously set request properties, if any
                 restServiceAdapter.clearRequestProperties();
                 // Set the connection name
                 restServiceAdapter.setConnectionName("enrich");
                 
                 restServiceAdapter.setRequestType(RestServiceAdapter.REQUEST_TYPE_POST);
                 restServiceAdapter.addRequestProperty("Accept", "application/json; charset=UTF-8");
                 restServiceAdapter.addRequestProperty("Authorization", "Basic " + "WFhFX1JFU1RfU0VSVklDRVNfQURNSU46b3JhY2xlMTIz");
                 restServiceAdapter.addRequestProperty("Content-Type", "application/json");
                 restServiceAdapter.setRequestURI("/webservices/rest/XXEReqService/submit_attachments/");
                
                String imageRequest = "{\n" + 
                                        "\n" + 
                                        "    \"SUBMIT_ATTACHMENTS_Input\":{\n" + 
                                        "        \"@xmlns\":\"http://xmlns.oracle.com/apps/po/rest/XXEReqService/submit_attachments/\",\n" + 
                                        "        \"RESTHeader\":{\n" + 
                                        "            \"@xmlns\":\"http://xmlns.oracle.com/apps/po/rest/XXEReqService/header\"\n" + 
                                        "        },\n" + 
                                        "        \"InputParameters\":{\n" + 
                                        "            \"P_SEARCH_GROUP_ID\":\""+groupId+"\",\n" + 
                                        "            \"P_CHECKOUT_FILES\":{\n" + 
                                        "                \"P_CHECKOUT_FILES_ITEM\":"+image_value+"\n" + 
                                        
                                        "            }\n" + 
                                        "        }\n" + 
                                        "    }\n" + 
                                        "\n" + 
                                        "}";
                
                restServiceAdapter.setRetryLimit(0);
                System.out.println("postData===============================" + imageRequest);
                response = restServiceAdapter.send(imageRequest);
                System.out.println("response===============================" + response);
                //-----------------------
                }
                
                
                restServiceAdapter = Model.createRestServiceAdapter();
                 // Clear any previously set request properties, if any
                 restServiceAdapter.clearRequestProperties();
                 // Set the connection name
                 restServiceAdapter.setConnectionName("enrich");
                 
                 restServiceAdapter.setRequestType(RestServiceAdapter.REQUEST_TYPE_POST);
                 restServiceAdapter.addRequestProperty("Accept", "application/json; charset=UTF-8");
                 restServiceAdapter.addRequestProperty("Authorization", "Basic " + "WFhFX1JFU1RfU0VSVklDRVNfQURNSU46b3JhY2xlMTIz");
                 restServiceAdapter.addRequestProperty("Content-Type", "application/json");
                 restServiceAdapter.setRequestURI("/webservices/rest/XXEReqService/start_requisition_process/");
                
                 String startRequisition = "{\n" + 
                                             "\n" + 
                                             "    \"START_REQUISITION_PROCESS_Input\":{\n" + 
                                             "        \"@xmlns\":\"http://xmlns.oracle.com/apps/po/rest/XXEReqService/start_requisition_process/\",\n" + 
                                             "        \"RESTHeader\":{\n" + 
                                             "            \"@xmlns\":\"http://xmlns.oracle.com/apps/po/rest/XXEReqService/header\"\n" + 
                                             "        },\n" + 
                                             "        \"InputParameters\":{\n" + 
                                             "            \"P_SEARCH_GROUP_ID\":\""+groupId+"\"\n" + 
                                             "        }\n" + 
                                             "    }\n" + 
                                             "\n" + 
                                             "}";
                 
                restServiceAdapter.setRetryLimit(0);
                System.out.println("postData===============================" + startRequisition);
                response = restServiceAdapter.send(startRequisition);
                System.out.println("response===============================" + response);
                //-----------------------
                
                
            
            //**********************
            
            itemCategories="";
            quantity="";
            needByDate="";
            itemType="";
            found=false;
            
            
            
            AdfmfContainerUtilities.invokeContainerJavaScriptFunction(AdfmfJavaUtilities.getFeatureId(),
                                                                       "displayAlert",
                                                                       new Object[] { });
            
            
            ValueExpression ve11 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.item_categories}", String.class);
            ve11.setValue(AdfmfJavaUtilities.getAdfELContext(),"");
            ValueExpression ve12 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.suppliers}", String.class);
            ve12.setValue(AdfmfJavaUtilities.getAdfELContext(),"");
            ValueExpression ve13 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.web_url}", String.class);
            ve13.setValue(AdfmfJavaUtilities.getAdfELContext(),"");
            ValueExpression ve14 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.part_no}", String.class);
            ve14.setValue(AdfmfJavaUtilities.getAdfELContext(),"");
            ValueExpression ve15 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.brand}", String.class);
            ve15.setValue(AdfmfJavaUtilities.getAdfELContext(),"");
            ValueExpression ve16 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.upc}", String.class);
            ve16.setValue(AdfmfJavaUtilities.getAdfELContext(),"");
            ValueExpression ve17 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.quantity}", String.class);
            ve17.setValue(AdfmfJavaUtilities.getAdfELContext(),"");
            ValueExpression ve18 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.uom1}", String.class);
            ve18.setValue(AdfmfJavaUtilities.getAdfELContext(),"");
            
            
            
            ValueExpression ve19 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.displayAddToCart}", String.class);
            ve19.setValue(AdfmfJavaUtilities.getAdfELContext(),"false");
            
            ValueExpression ve20 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.displaySearchCount}", String.class);
            ve20.setValue(AdfmfJavaUtilities.getAdfELContext(),"false");
            
                veComments = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.rfqComments}", String.class);
                veComments.setValue(AdfmfJavaUtilities.getAdfELContext(),"");
                            
                 veImage = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.rfqImage}", String.class);
                 veImage.setValue(AdfmfJavaUtilities.getAdfELContext(),"");
                 
            
            AdfmfJavaUtilities.flushDataChangeEvent();
            
            
    //            AdfmfContainerUtilities.invokeContainerJavaScriptFunction(AdfmfJavaUtilities.getFeatureName(),
    //                                                                           "adf.mf.api.amx.doNavigation", new Object[] { "ref_search" });
            
            }
            else{
                
                //means a match occured on the oracle side but not on the indix side
                
                Random randomGenerator = new Random();
                int randomInt = randomGenerator.nextInt(100);
                ValueExpression ve = AdfmfJavaUtilities.getValueExpression("#{applicationScope.user_name}", String.class);
                String userName = (String)ve.getValue(AdfmfJavaUtilities.getAdfELContext());
                
                ValueExpression ve121 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.searchValue}", String.class);
                String productTitle = (String)ve121.getValue(AdfmfJavaUtilities.getAdfELContext());
                
                
                StringBuffer sb = new StringBuffer("[\n");
                sb.append("{\n");
                sb.append("    \"SEARCH_ID\":\""+randomInt+"\",\n");
                sb.append("    \"DEVICE_SERIAL_ID\":\"ABCD\",\n");
                sb.append("    \"USER_ID\":\""+userName+"\",\n");
                sb.append("    \"SEARCH_TYPE\":\"R\",\n");
                sb.append("    \"SEARCH_TEXT\":\""+productTitle+"\",\n");
                sb.append("    \"RESULT_COUNT\":\""+resultSize+"\",\n");
                sb.append("    \"REQUEST_TYPE\":\"RFQ\"\n");
                sb.append("},");
                
                String header_value = sb.substring(0, sb.length() - 1).concat("]");
                System.out.println("header_value===============================" + header_value);
                
                

                sb = new StringBuffer("[\n");
                
                ValueExpression ve191 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.uom1}", String.class);
                String u1=(String)ve191.getValue(AdfmfJavaUtilities.getAdfELContext());
                String u="";
                if(!u1.equalsIgnoreCase("")){
                   UOM uo=(UOM)UOMList.s_jobs.get((Integer.parseInt(u1)));
                   u=uo.getName();
                   
                }
                else{
                    UOM uo=(UOM)UOMList.s_jobs.get(0);
                    u=uo.getName();
                }

                
                
                //  for(int i=0;i<ItemsList.items_ref.size();i++)
                //  {
                   // Item it=(Item)ItemsList.items_ref.get(i);
                sb.append("{\n");
                sb.append("    \"SEARCH_ID\":\""+randomInt+"\",\n");
                sb.append("    \"PRODUCT_SOURCE\":\"U\",\n");
                sb.append("    \"PRODUCT_CATEGORY\":\""+itemCategories+"\",\n");
                sb.append("    \"PRODUCT_TITLE\":\""+productTitle+"\",\n");
                sb.append("    \"QUANTITY\":\""+quantity+"\",\n");
                sb.append("    \"UOM_CODE\":\""+u+"\",\n");
                sb.append("    \"UNIT_PRICE\":\"\",\n");
                sb.append("    \"CURRENCY_CODE\":\"USD\",\n");
                sb.append("    \"DELIVER_TO_LOCATION\":\"Metlife\",\n");
                String arr[]=needByDate.split("T");
                sb.append("    \"NEED_BY_DATE\":\""+arr[0]+"\",\n");
                sb.append("    \"SELECTED_FLAG\":\"Y\",\n");
                sb.append("    \"ITEM_TYPE\":\""+itemType+"\", \n");
                sb.append("    \"COST_CENTER\":\"\",\n");
                sb.append("    \"CHARGE_ACCOUNT\":\"\",\n");
                sb.append("    \"MARKUP_PRICE\":\"\",\n");
                sb.append("    \"REQUISITION_HEADER_ID\":\"\",\n");
                sb.append("    \"REQUISITION_LINE_ID\":\"\",\n");
                
                ValueExpression veComments = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.rfqComments}", String.class);
                String comments=(String)veComments.getValue(AdfmfJavaUtilities.getAdfELContext());
                sb.append("    \"COMMENTS\":\""+URLEncoder.encode(comments)+"\", \n");
                
                ValueExpression veImage = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.rfqImage}", String.class);
                String img=(String)veImage.getValue(AdfmfJavaUtilities.getAdfELContext());
                sb.append("    \"ATTACHMENT_FILE\":\""+img+"\",\n");
                if(!img.equalsIgnoreCase("")){
                sb.append("    \"CONTRACT_ITEM_EXCEPTION\":\"\",\n");
                sb.append("    \"INDIX_CATEGORY_ID\":\"\",\n");
                sb.append("    \"FILE_NAME\":\""+randomInt+".jpg\",\n");
                sb.append("    \"FILE_CONTENT_TYPE\":\"image/jpeg\",\n");
                sb.append("    \"FILE_FORMAT\":\"image\"\n");
                }
                else{
                    sb.append("    \"CONTRACT_ITEM_EXCEPTION\":\"\",\n");
                    sb.append("    \"INDIX_CATEGORY_ID\":\"\",\n");
                    sb.append("    \"FILE_NAME\":\"\",\n");
                    sb.append("    \"FILE_CONTENT_TYPE\":\"\",\n");
                    sb.append("    \"FILE_FORMAT\":\"\"\n");
                }
                sb.append("},");
                    
                //  }

                String body_value = sb.substring(0, sb.length() - 1).concat("]");
                   
                   
                   
                sb = new StringBuffer("[\n");
                
                
                    
                sb.append("{\n");
                sb.append("    \"SEARCH_ID\":\""+randomInt+"\",\n");
                sb.append("    \"VENDOR_NAME\":\"\",\n");
                sb.append("    \"VENDOR_SITE\":\"\",\n");
                sb.append("    \"VENDOR_SITE_ADDRESS1\":\"\",\n");
                sb.append("    \"VENDOR_SITE_CITY\":\"\",\n");
                sb.append("    \"VENDOR_SITE_STATE\":\"\",\n");
                sb.append("    \"VENDOR_SITE_ZIP\":\"\",\n");
                sb.append("    \"VENDOR_SITE_COUNTRY\":\"\",\n");
                sb.append("    \"VENDOR_CONTACT_LAST_NAME\":\"\",\n");
                sb.append("    \"VENDOR_CONTACT_PHONE\":\"\",\n");
                sb.append("    \"VENDOR_CONTACT_EMAIL\":\"\"\n");
                sb.append("},");
                   
                String vendor_value = sb.substring(0, sb.length() - 1).concat("]");
                
                
                restServiceAdapter = Model.createRestServiceAdapter();
                // Clear any previously set request properties, if any
                restServiceAdapter.clearRequestProperties();
                // Set the connection name
                restServiceAdapter.setConnectionName("enrich");
                
                restServiceAdapter.setRequestType(RestServiceAdapter.REQUEST_TYPE_POST);
                restServiceAdapter.addRequestProperty("Accept", "application/json; charset=UTF-8");
                restServiceAdapter.addRequestProperty("Authorization", "Basic " + "WFhFX1JFU1RfU0VSVklDRVNfQURNSU46b3JhY2xlMTIz");
                restServiceAdapter.addRequestProperty("Content-Type", "application/json");
                restServiceAdapter.setRequestURI("/webservices/rest/XXEReqService/submit_requisition/");
                
                data= "{\n" + 
                "  \"CHECKOUT_Input\" : {\n" +
                "   \"@xmlns\" : \"http://xmlns.oracle.com/apps/po/rest/XXEReqService/submit_requisition/\",\n" +
                "   \"RESTHeader\": {\n" +
                "   \"@xmlns\" : \"http://xmlns.oracle.com/apps/po/rest/XXEReqService/header\"\n" +
                "    },\n" +
                "   \"InputParameters\": {\n" +
                "        \"P_SEARCH_HEADER\": {\"P_SEARCH_HEADER_ITEM\": \n"+header_value+"\n" +
                "       },\n" +
                "        \"P_SEARCH_LINES\": {\"P_SEARCH_LINES_ITEM\": \n"+body_value+"         \n" +
                "       }, \n" +
                "        \"P_RFQ_VENDORS\": {\"P_RFQ_VENDORS_ITEM\": \n"+vendor_value+"         \n" +
                "       } \n" +
                "      }\n" +
                "   }\n" +
                "}\n";

                restServiceAdapter.setRetryLimit(0);
                System.out.println("postData===============================" + data);
                response = restServiceAdapter.send(data);
                System.out.println("response===============================" + response);
                //-*-*-*-*-*-*-*-*-*-*-*-*
                //**********************
                    //get groupid from requitision
                    JSONObject groupIdResp=new JSONObject(response);
                    JSONObject groupIdRespOutput = groupIdResp.getJSONObject("OutputParameters");
                    String groupId = groupIdRespOutput.getString("X_SEARCH_GROUP_ID");
                    String groupIdStatus = groupIdRespOutput.getString("X_RETURN_STATUS");
                    
                    
                    //checkout 
                    // sb = new StringBuffer("[\n");
                    for(int i=0;i<selectedImages.size();i++) {
                             System.out.println("Selected Images are ==>"+selectedImages.get(i));
                             String s=selectedImages.get(i);
                             String filepath[]=s.split("/");
                             int length=filepath.length;
                             String filename=filepath[length-1];
                             System.out.println("File name is ==>"+filename);
                        sb = new StringBuffer("[\n");
                        
                             sb.append("{\n");
                             sb.append("    \"SEARCH_ID\":\""+randomInt+"\",\n");
                             sb.append("    \"ATTACHMENT_FILE\":\""+EncodeBased64Binary.encodeFileToBase64Binary(selectedImages.get(i))+"\",\n");
                             sb.append("    \"FILE_NAME\":\""+filename+"\",\n");
                             sb.append("    \"FILE_CONTENT_TYPE\":\"image/jpeg\",\n");
                             sb.append("    \"FILE_FORMAT\":\"image\"\n");
                             sb.append("},");
                         
                         
                    String image_value = sb.substring(0, sb.length() - 1).concat("]");    
                    
                     restServiceAdapter = Model.createRestServiceAdapter();
                     // Clear any previously set request properties, if any
                     restServiceAdapter.clearRequestProperties();
                     // Set the connection name
                     restServiceAdapter.setConnectionName("enrich");
                     
                     restServiceAdapter.setRequestType(RestServiceAdapter.REQUEST_TYPE_POST);
                     restServiceAdapter.addRequestProperty("Accept", "application/json; charset=UTF-8");
                     restServiceAdapter.addRequestProperty("Authorization", "Basic " + "WFhFX1JFU1RfU0VSVklDRVNfQURNSU46b3JhY2xlMTIz");
                     restServiceAdapter.addRequestProperty("Content-Type", "application/json");
                     restServiceAdapter.setRequestURI("/webservices/rest/XXEReqService/submit_attachments/");
                    
                    String imageRequest = "{\n" + 
                                            "\n" + 
                                            "    \"SUBMIT_ATTACHMENTS_Input\":{\n" + 
                                            "        \"@xmlns\":\"http://xmlns.oracle.com/apps/po/rest/XXEReqService/submit_attachments/\",\n" + 
                                            "        \"RESTHeader\":{\n" + 
                                            "            \"@xmlns\":\"http://xmlns.oracle.com/apps/po/rest/XXEReqService/header\"\n" + 
                                            "        },\n" + 
                                            "        \"InputParameters\":{\n" + 
                                            "            \"P_SEARCH_GROUP_ID\":\""+groupId+"\",\n" + 
                                            "            \"P_CHECKOUT_FILES\":{\n" + 
                                            "                \"P_CHECKOUT_FILES_ITEM\":"+image_value+"\n" + 
                                            
                                            "            }\n" + 
                                            "        }\n" + 
                                            "    }\n" + 
                                            "\n" + 
                                            "}";
                    
                    restServiceAdapter.setRetryLimit(0);
                    System.out.println("postData===============================" + imageRequest);
                    response = restServiceAdapter.send(imageRequest);
                    System.out.println("response===============================" + response);
                    //-----------------------
                    }
                    
                    
                    restServiceAdapter = Model.createRestServiceAdapter();
                     // Clear any previously set request properties, if any
                     restServiceAdapter.clearRequestProperties();
                     // Set the connection name
                     restServiceAdapter.setConnectionName("enrich");
                     
                     restServiceAdapter.setRequestType(RestServiceAdapter.REQUEST_TYPE_POST);
                     restServiceAdapter.addRequestProperty("Accept", "application/json; charset=UTF-8");
                     restServiceAdapter.addRequestProperty("Authorization", "Basic " + "WFhFX1JFU1RfU0VSVklDRVNfQURNSU46b3JhY2xlMTIz");
                     restServiceAdapter.addRequestProperty("Content-Type", "application/json");
                     restServiceAdapter.setRequestURI("/webservices/rest/XXEReqService/start_requisition_process/");
                    
                     String startRequisition = "{\n" + 
                                                 "\n" + 
                                                 "    \"START_REQUISITION_PROCESS_Input\":{\n" + 
                                                 "        \"@xmlns\":\"http://xmlns.oracle.com/apps/po/rest/XXEReqService/start_requisition_process/\",\n" + 
                                                 "        \"RESTHeader\":{\n" + 
                                                 "            \"@xmlns\":\"http://xmlns.oracle.com/apps/po/rest/XXEReqService/header\"\n" + 
                                                 "        },\n" + 
                                                 "        \"InputParameters\":{\n" + 
                                                 "            \"P_SEARCH_GROUP_ID\":\""+groupId+"\"\n" + 
                                                 "        }\n" + 
                                                 "    }\n" + 
                                                 "\n" + 
                                                 "}";
                     
                    restServiceAdapter.setRetryLimit(0);
                    System.out.println("postData===============================" + startRequisition);
                    response = restServiceAdapter.send(startRequisition);
                    System.out.println("response===============================" + response);
                    //-----------------------
                    
                    
                
                //**********************
                
                //-*-*-*-*-*-*-*-*-*-*-*-*
                itemCategories="";
                quantity="";
                needByDate="";
                itemType="";
                found=false;
                
                
                
                AdfmfContainerUtilities.invokeContainerJavaScriptFunction(AdfmfJavaUtilities.getFeatureId(),
                                                                           "displayAlert",
                                                                           new Object[] { });
                
                ValueExpression ve123 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.selectedImagesCount}", String.class);
                ve123.setValue(AdfmfJavaUtilities.getAdfELContext(),"");
                
                ImageList.imageList.clear();
                selectedImages.clear();
                
                MethodExpression me1 = AdfmfJavaUtilities.getMethodExpression("#{bindings.clearSelectedImage.execute}", Object.class, new Class[] {});
                me1.invoke(AdfmfJavaUtilities.getAdfELContext(), new Object[]{});
                
                
                ValueExpression ve11 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.item_categories}", String.class);
                ve11.setValue(AdfmfJavaUtilities.getAdfELContext(),"");
                ValueExpression ve12 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.suppliers}", String.class);
                ve12.setValue(AdfmfJavaUtilities.getAdfELContext(),"");
                ValueExpression ve13 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.web_url}", String.class);
                ve13.setValue(AdfmfJavaUtilities.getAdfELContext(),"");
                ValueExpression ve14 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.part_no}", String.class);
                ve14.setValue(AdfmfJavaUtilities.getAdfELContext(),"");
                ValueExpression ve15 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.brand}", String.class);
                ve15.setValue(AdfmfJavaUtilities.getAdfELContext(),"");
                ValueExpression ve16 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.upc}", String.class);
                ve16.setValue(AdfmfJavaUtilities.getAdfELContext(),"");
                ValueExpression ve17 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.quantity}", String.class);
                ve17.setValue(AdfmfJavaUtilities.getAdfELContext(),"");
                ValueExpression ve18 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.uom1}", String.class);
                ve18.setValue(AdfmfJavaUtilities.getAdfELContext(),"");
                
                ValueExpression veImageCount = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.selectedImagesCount}", String.class);
                veImageCount.setValue(AdfmfJavaUtilities.getAdfELContext(),"");                
                ValueExpression need_by_date = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.need_by_date}", String.class);
                need_by_date.setValue(AdfmfJavaUtilities.getAdfELContext(),"");
                
                
                
                
                ValueExpression ve19 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.displayAddToCart}", String.class);
                ve19.setValue(AdfmfJavaUtilities.getAdfELContext(),"false");
                
                ValueExpression ve20 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.displaySearchCount}", String.class);
                ve20.setValue(AdfmfJavaUtilities.getAdfELContext(),"false");
                
                veComments = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.rfqComments}", String.class);
                veComments.setValue(AdfmfJavaUtilities.getAdfELContext(),"");
                            
                 veImage = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.rfqImage}", String.class);
                 veImage.setValue(AdfmfJavaUtilities.getAdfELContext(),"");
                
                AdfmfJavaUtilities.flushDataChangeEvent();
                
                
    //                AdfmfContainerUtilities.invokeContainerJavaScriptFunction(AdfmfJavaUtilities.getFeatureName(),
    //                                                                               "adf.mf.api.amx.doNavigation", new Object[] { "ref_search" });
            }
            
        }
         
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
        
    }
    /*
        
     */
    public void doRefinedSearch() {
        // Add event code here...
       
        String itemCategories="";
        String quantity="";
        String needByDate="";
        String itemType="";
        boolean found=false;    
        
                    ValueExpression ve130 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.searchValue}", String.class);
                    String search = (String)ve130.getValue(AdfmfJavaUtilities.getAdfELContext()); 
        

                    ValueExpression ve411 = AdfmfJavaUtilities.getValueExpression("#{applicationScope.rdItemType}", String.class);
                    itemType=(String)ve411.getValue(AdfmfJavaUtilities.getAdfELContext());
       
            
        ValueExpression ve41 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.searchType}", String.class);
        ve41.setValue(AdfmfJavaUtilities.getAdfELContext(), "R");
        
        ValueExpression ve1 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.item_categories}", String.class);
        itemCategories = (String)ve1.getValue(AdfmfJavaUtilities.getAdfELContext());
        
        ValueExpression ve2 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.part_no}", String.class);
        String partNo = (String)ve2.getValue(AdfmfJavaUtilities.getAdfELContext());
        
        ValueExpression ve3 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.brand}", String.class);
        String brand = (String)ve3.getValue(AdfmfJavaUtilities.getAdfELContext());
        
        ValueExpression ve4 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.upc}", String.class);
        String upc = (String)ve4.getValue(AdfmfJavaUtilities.getAdfELContext());  
        
        ValueExpression ve5 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.quantity}", String.class);
        quantity = (String)ve5.getValue(AdfmfJavaUtilities.getAdfELContext());
        
        ValueExpression ve6 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.uom1}", String.class);
        String uom = (String)ve6.getValue(AdfmfJavaUtilities.getAdfELContext());  
        
        ValueExpression ve61 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.searchValue}", String.class);
        String searchText = (String)ve61.getValue(AdfmfJavaUtilities.getAdfELContext());  
        
        
        ValueExpression ve62 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.need_by_date}", String.class);
        needByDate = (String)ve62.getValue(AdfmfJavaUtilities.getAdfELContext());  
        
        ValueExpression ve_supp = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.suppliers}", String.class);
        String supplierNames = (String)ve_supp.getValue(AdfmfJavaUtilities.getAdfELContext());
        
        ValueExpression ve_product_url = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.web_url}", String.class);
        String product_url = (String)ve_product_url.getValue(AdfmfJavaUtilities.getAdfELContext());
        
        ValueExpression ve = AdfmfJavaUtilities.getValueExpression("#{applicationScope.user_id}", String.class);
         String userName = (String)ve.getValue(AdfmfJavaUtilities.getAdfELContext());
        ValueExpression isContractedItemPresent = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.isContractedItemPresent}", String.class);
        isContractedItemPresent.setValue(AdfmfJavaUtilities.getAdfELContext(), "false");
          
                    
        //dsfsdf            
                    
                    
                    
                    
        System.out.println("supplierNames===============================" + supplierNames);
        
        
        
        boolean checkError = false;
        String errorMessage = "";
        String query="";
        String brandId="";
        
        
        
        //quantity
        
               // if( !itemCategories.equalsIgnoreCase("") && !quantity.equalsIgnoreCase("") && !uom.equalsIgnoreCase("") ){
                    
    //                            if(!brand.equalsIgnoreCase("")){
    //                                    if( !partNo.equalsIgnoreCase("") ){
    //                                        checkError = false;
    //                                    }else{
    //
    //                                        AdfmfContainerUtilities.invokeContainerJavaScriptFunction(
    //                                                                                 AdfmfJavaUtilities.getFeatureName(),
    //                                                                                 "adf.mf.api.amx.addMessage", new Object[] {AdfException.ERROR,
    //                                                                                 "Brand works in combination with Manufacturer Part No. and so please enter the same too",
    //                                                                                 null,
    //                                                                                 null });
    //                                        checkError = true;
    //                                    }
    //                            }else{
    //                                checkError = false;
    //                            }
        
        
        
        
        
        
                            
                     System.out.println("itemCategories===============================" + itemCategories); 
                     System.out.println("quantity===============================" + quantity); 
                     System.out.println("uom===============================" + uom); 
                    if( quantity.equalsIgnoreCase("") || uom.equalsIgnoreCase("") || needByDate.equalsIgnoreCase("") ){
                        
                      
                        checkError = true; 
                    }
                    else if(  (!brand.equalsIgnoreCase("") || !supplierNames.equalsIgnoreCase("")) && itemCategories.equalsIgnoreCase("")   ){
                        
                        checkError = true; 
                        
                    }
                    //itemCategories,supplierNames,product_url,partNo,brand,upc,quantity,uom,needByDate
                    else if(  (itemCategories.equalsIgnoreCase("") && supplierNames.equalsIgnoreCase("") && product_url.equalsIgnoreCase("") &&partNo.equalsIgnoreCase("")&& brand.equalsIgnoreCase("") && upc.equalsIgnoreCase("")&& !quantity.equalsIgnoreCase("")&& !uom.equalsIgnoreCase("") && !needByDate.equalsIgnoreCase(""))){
                   
                        System.out.println("Qty, UOM, Needdate present other empty-----------");
                        AdfmfContainerUtilities.invokeContainerJavaScriptFunction(
                                                     AdfmfJavaUtilities.getFeatureName(),
                                                     "adf.mf.api.amx.addMessage", new Object[] {AdfException.ERROR,
                                                     "Item Category is mandatory...Please enter it and resubmit the search",
                                                     null,
                                                     null }); 
                        checkError = true; 
                        
                    }
                    
                    else if(itemType.equalsIgnoreCase("goods") && !quantity.equalsIgnoreCase("") &&(Double.parseDouble(quantity)%1!=0)) {
                            
                            
                            AdfmfContainerUtilities.invokeContainerJavaScriptFunction( AdfmfJavaUtilities.getFeatureName(),
                                                                                             "adf.mf.api.amx.addMessage", new Object[] {AdfException.ERROR,
                                                                                             "Quantity for item type goods cannot be decimal",
                                                                                             null,
                                                                                             null });
                        }

                   
                    else{
                        checkError=false;
                    }
                          
                           if(!partNo.equalsIgnoreCase("")&& !brand.equalsIgnoreCase("")&& !quantity.equalsIgnoreCase("") &&!uom.equalsIgnoreCase("")&& !needByDate.equalsIgnoreCase("")&& product_url.equalsIgnoreCase("") ){
                                        checkError = false; 
                                        ValueExpression ve611 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.brandId}", String.class);
                                        brandId = (String)ve611.getValue(AdfmfJavaUtilities.getAdfELContext()); 
                                        
                                    }
                          /* else{
                                        
                                        AdfmfContainerUtilities.invokeContainerJavaScriptFunction(
                                                                                 AdfmfJavaUtilities.getFeatureName(),
                                                                                 "adf.mf.api.amx.addMessage", new Object[] {AdfException.ERROR,
                                                                                 "Manufacturer Part No works in combination with Brand. and so please enter the same too",
                                                                                 null,
                                                                                 null });                                        
                                    }  */
                    
                           
                    if(!brand.equalsIgnoreCase("") && itemCategories.equalsIgnoreCase("") && upc.equalsIgnoreCase("") && partNo.equalsIgnoreCase("") && product_url.equalsIgnoreCase("") &&!quantity.equalsIgnoreCase("")&&!uom.equalsIgnoreCase("") && !needByDate.equalsIgnoreCase("")){
                        checkError = false; 
                        ValueExpression ve611 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.brandId}", String.class);
                        brandId = (String)ve611.getValue(AdfmfJavaUtilities.getAdfELContext());  
                    }
                    
                           
                    if(!brand.equalsIgnoreCase("") && !itemCategories.equalsIgnoreCase("") && upc.equalsIgnoreCase("") && partNo.equalsIgnoreCase("") && product_url.equalsIgnoreCase("") &&!quantity.equalsIgnoreCase("")&&!uom.equalsIgnoreCase("") && !needByDate.equalsIgnoreCase("")){
                        checkError = false; 
                        ValueExpression ve611 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.brandId}", String.class);
                        brandId = (String)ve611.getValue(AdfmfJavaUtilities.getAdfELContext());  
                    }      
                           
                    System.out.println("checkError===============================" + checkError);
                            
                           if(checkError == false){
                                
                                ItemsList.s_jobs.clear();
                                String response="";
                                String postData="";
                                JSONObject resp=new JSONObject();
                                JSONObject output=new JSONObject();
                                int rowCount=0;
                                boolean isUPCPresent=false,isBrandPresent=false,isPartNoPresent=false,isProductURLPresent=false;
                                
                              
                                
                                
                                if(upc!=null && !upc.equalsIgnoreCase("")) {
                                    query=query+"&upc="+upc;
                                    isUPCPresent=true;
                                    System.out.println("UPC alone present");
                                }
                                
                                //&& isUPCPresent==false
                                
                                if(brand!=null && !brand.equalsIgnoreCase("") && isUPCPresent==false) {
                                    query=query+"&brandId="+brandId;
                                    isBrandPresent=true;
                                    System.out.println("brand alone present");
                                }
                                
                                //&& isUPCPresent==false
                                if(partNo!=null && !partNo.equalsIgnoreCase("") && isUPCPresent==false) {
                                    query=query+"&mpn="+partNo;
                                    isPartNoPresent=true;
                                    System.out.println("partno and brand present");
                                }
                                
                                //&& isUPCPresent==false && isBrandPresent==false && isPartNoPresent==false
                               if(product_url!=null && !product_url.equalsIgnoreCase("") && isUPCPresent==false && isBrandPresent==false && isPartNoPresent==false) {
                                   query=query+"&url="+URLEncoder.encode(product_url);
                                   System.out.println("product url alone present");
                                   
                               }
                                
                                
                                
                                boolean categoryAloneCreated=false;
                                boolean categoryBrandSelected=false;
                                
                                if( !itemCategories.equalsIgnoreCase("") && upc.equalsIgnoreCase("") && brand.equalsIgnoreCase("") && partNo.equalsIgnoreCase("") && product_url.equalsIgnoreCase("")) {
                                    categoryAloneCreated=true;
                                }
                                
                               if( !itemCategories.equalsIgnoreCase("") && upc.equalsIgnoreCase("") && !brand.equalsIgnoreCase("") && partNo.equalsIgnoreCase("") && product_url.equalsIgnoreCase("")) {
                                   categoryBrandSelected=true;
                               }
                                
                             /*  boolean BrandAloneSelected=false; 
                                   
                               if( itemCategories.equalsIgnoreCase("") && upc.equalsIgnoreCase("") && !brand.equalsIgnoreCase("") && partNo.equalsIgnoreCase("") && product_url.equalsIgnoreCase("") && !quantity.equalsIgnoreCase("")&& !uom.equalsIgnoreCase("") && !needByDate.equalsIgnoreCase("") ) {
                                   BrandAloneSelected=true;
                               }
                                */
                                
                               System.out.println("categoryAloneCreated ===============================" + categoryAloneCreated);
                                
                                if(categoryAloneCreated)
                                {
                                    
                                    //get the suppliers list from oracle for category
                                    try{
                                    RestServiceAdapter restServiceAdapter = Model.createRestServiceAdapter();
                                    // Clear any previously set request properties, if any
                                    restServiceAdapter.clearRequestProperties();
                                    // Set the connection name
                                    restServiceAdapter.setConnectionName("enrich");
                                    
                                    restServiceAdapter.setRequestType(RestServiceAdapter.REQUEST_TYPE_POST);
                                    restServiceAdapter.addRequestProperty("Accept", "application/json; charset=UTF-8");
                                    restServiceAdapter.addRequestProperty("Authorization", "Basic " + "WFhFX1JFU1RfU0VSVklDRVNfQURNSU46b3JhY2xlMTIz");
                                    restServiceAdapter.addRequestProperty("Content-Type", "application/json");
                                    restServiceAdapter.setRequestURI("/webservices/rest/XXETailSpendAPI/refined_search/");
                                    String data="";
                                        
                                        if(supplierNames==null || supplierNames.equalsIgnoreCase("")){
                                        
                                    data= "{\n" + 
                                    "  \"REFINED_SEARCH_Input\" : {\n" + 
                                    "   \"@xmlns\" : \"http://xmlns.oracle.com/apps/po/rest/XXETailSpendAPI/refined_search/\",\n" + 
                                    "   \"RESTHeader\": {\n" + 
                                    "   \"@xmlns\" : \"http://xmlns.oracle.com/apps/po/rest/XXETailSpendAPI/header\"\n" + 
                                     
                                     
                                     
                                     
                                     
                                    "    }, \n" + 
                                    "   \"InputParameters\": {\n" + 
                                    "        \"P_REF_SEARCH_ATTRIBS_TL\": { \n" + 
                                    "             \"CATEGORY\"  : \""+itemCategories+"\", \n" + 
                                            "             \"SUPPLIER_TL\"  :{\n" +             
                                            "             \"SUPPLIER_TL_ITEM\"  : [\n ]\n"+                      
                                            "             }, \n" +    
                                    "                 \"USER_ID\"  : \""+userName+"\"\n" +                                           
                                    "        }\n" + 
                                    "     }\n" + 
                                    "  }\n" + 
                                    "}  \n";       
                                        }
                                        else{
                                            
                                            StringBuffer sb=new StringBuffer();
                                            String arr[]=supplierNames.split(",");
                                            String supp="";
                                            for(int k=0;k<arr.length;k++)
                                            {
                                                supp=supp+"\""+arr[k]+"\",";
                                            }
                                            
                                            if (supp.length() > 0 && supp.charAt(supp.length()-1)==',') {
                                                  supp = supp.substring(0, supp.length()-1);
                                                }
                                            
                                            
                                            System.out.println("Suppliers "+supp);
                                            
                                            data= "{\n" + 
                                            "  \"REFINED_SEARCH_Input\" : {\n" + 
                                            "   \"@xmlns\" : \"http://xmlns.oracle.com/apps/po/rest/XXETailSpendAPI/refined_search/\",\n" + 
                                            "   \"RESTHeader\": {\n" + 
                                            "   \"@xmlns\" : \"http://xmlns.oracle.com/apps/po/rest/XXETailSpendAPI/header\"\n" + 
                                             
                                             
                                             
                                             
                                             
                                            "    }, \n" + 
                                            "   \"InputParameters\": {\n" + 
                                            "        \"P_REF_SEARCH_ATTRIBS_TL\": { \n" + 
                                            "             \"CATEGORY\"  : \""+itemCategories+"\", \n" + 
                                            "             \"SUPPLIER_TL\"  :{\n" +             
                                            "             \"SUPPLIER_TL_ITEM\"  : [\n" +supp+"]\n"+                      
                                            "             }, \n" +                     
                                            "     \"USER_ID\"  : \""+userName+"\"\n" +              
                                            "        }\n" + 
                                            "     }\n" + 
                                            "  }\n" + 
                                            "}  \n";       
                                            
                                        }
                                        
                                       
                                       restServiceAdapter.setRetryLimit(0);
                                       System.out.println("postData===============================" + data);
                                        
                                        response = restServiceAdapter.send(data);
                                        System.out.println("response===============================" + response);
                                        resp=new JSONObject(response);
                                        output=resp.getJSONObject("OutputParameters");
                                        String result_count=output.getString("X_RESULT_COUNT");
                                        
                                        if(supplierNames==null || supplierNames.equalsIgnoreCase("")){
                                            System.out.println("Inside found false");
                                            proceedRefinedSearch();
                                            found=false;
                                        }
                                        else{
                                        if(result_count!=null && !result_count.equalsIgnoreCase("") && !result_count.equalsIgnoreCase("0")){
                                            found=true;
                                        }
                                        
                                        
                                        if(!found) {
                                            AdfmfContainerUtilities.invokeContainerJavaScriptFunction(AdfmfJavaUtilities.getFeatureId(),
                                                                                                       "confirm_refined_search",
                                                                                                       new Object[] { });
                                        }
                                        else{
                                            proceedRefinedSearch();
                                        }
                                        
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
                                else if(categoryBrandSelected){
                                    
                                    try{
                                     
                                    //get category Id from oracle and send the category id  along with the brand id
                                    
                                    RestServiceAdapter restServiceAdapter = Model.createRestServiceAdapter();
                                     // Clear any previously set request properties, if any
                                     restServiceAdapter.clearRequestProperties();
                                     // Set the connection name
                                     restServiceAdapter.setConnectionName("enrich");
                                     
                                     restServiceAdapter.setRequestType(RestServiceAdapter.REQUEST_TYPE_POST);
                                     restServiceAdapter.addRequestProperty("Accept", "application/json; charset=UTF-8");
                                     restServiceAdapter.addRequestProperty("Authorization", "Basic " + "WFhFX1JFU1RfU0VSVklDRVNfQURNSU46b3JhY2xlMTIz");
                                     restServiceAdapter.addRequestProperty("Content-Type", "application/json");
                                     restServiceAdapter.setRequestURI("/webservices/rest/XXETailSpendAPI/get_indix_category/");
                                     String data= "{\n" + 
                                     "\n" + 
                                     "  \"GET_INDIX_CATEGORY_Input\" : {\n" + 
                                     "\n" + 
                                     "   \"@xmlns\" : \"http://xmlns.oracle.com/apps/po/rest/XXETailSpendAPI/get_indix_category/\",\n" + 
                                     "\n" + 
                                     "   \"RESTHeader\": {\n" + 
                                     "\n" + 
                                     "   \"@xmlns\" : \"http://xmlns.oracle.com/apps/po/rest/XXETailSpendAPI/header\"\n" + 
                                     "    },\n" + 
                                     "\n" + 
                                     "   \"InputParameters\": {\n" + 
                                     "\n" + 
                                     "        \"P_ORACLE_CATEGORY\": \""+itemCategories+"\"\n" + 
                                     "\n" + 
                                     "      }         \n" + 
                                     "\n" + 
                                     "   }\n" + 
                                     "\n" + 
                                     "}";                         
                                     restServiceAdapter.setRetryLimit(0);
                                     System.out.println("postData===============================" + data);
                                     response = restServiceAdapter.send(data);
                                     System.out.println("response===============================" + response);
                                     resp=new JSONObject(response);
                                     output=resp.getJSONObject("OutputParameters");
                                     String indix_category=output.getString("X_INDIX_CATEGORY");
                                     System.out.println("indix_category===============================" + indix_category);
                                     //quey indix to find categoryId for found category
                                     
                                        int categoryId=0;
                                                JSONObject indix_category_tl=output.getJSONObject("X_INDIX_CATEGORY_TL");   
                                                String categoryRef="&";
                                             if(indix_category_tl.get("X_INDIX_CATEGORY_TL_ITEM") instanceof JSONArray){
                                                   
                                                JSONArray items=(JSONArray)indix_category_tl.get("X_INDIX_CATEGORY_TL_ITEM");
                                                for(int i=0;i<items.length();i++){
                                                 // categoryRef=categoryRef+"categoryId="+items.getString(i)+"&";
                                                    categoryRef=categoryRef+"categoryId="+items.getString(i);
                                                    categoryId=1;
                                                }
                                                    
                                              }
                                              if(indix_category_tl.get("X_INDIX_CATEGORY_TL_ITEM") instanceof String){
                                                  categoryRef=categoryRef+"categoryId="+indix_category_tl.getString("X_INDIX_CATEGORY_TL_ITEM")+"&";   
                                                  categoryId=2;
                                                    
                                              }
    //
    //
    //                                     String url = "https://api.indix.com/v2/categories?app_id=9867e55c&app_key=8d79be1be9b9d8ce50af3a978b4d5ccc";
    //
    //                                     URL obj = new URL(url);
    //                                     HttpURLConnection con = (HttpURLConnection) obj.openConnection();
    //                                     // optional default is GET
    //                                     con.setRequestMethod("GET");
    //                                     //add request header
    //                                     int responseCode = con.getResponseCode();
    //                                     System.out.println("\nSending 'GET' request to URL : " + url);
    //                                     System.out.println("Response Code : " + responseCode);
    //                                     BufferedReader in = new BufferedReader(
    //                                        new InputStreamReader(con.getInputStream()));
    //                                     String inputLine;
    //                                     StringBuffer response1 = new StringBuffer();
    //                                     while ((inputLine = in.readLine()) != null) {
    //                                               response1.append(inputLine);
    //                                          }
    //                                        in.close();
    //                                          //print result
    //                                     //    System.out.println(response1.toString());
    //                                     resp=new JSONObject(response1.toString());
    //                                     output=resp.getJSONObject("result");
    //                                     JSONArray resArr=output.getJSONArray("categories");
    //                                     categoryId=0;
    //                                     for(int i=0;i<resArr.length();i++) {
    //                                         JSONObject resObj=(JSONObject)resArr.get(i);
    //                                         if(indix_category.equalsIgnoreCase(resObj.getString("name"))) {
    //                                             System.out.println("************* Match occurs *********************");
    //                                             categoryId=Integer.parseInt(resObj.getString("id"));
    //                                         }
    //
    //                                     }
    //
                                        
                                        //String url = "https://api.indix.com/v2/offersPremium/products?countryCode=US&brandId="+brandId+categoryRef+"&availability=IN_STOCK&lastRecordedIn=30&app_id=9867e55c&app_key=8d79be1be9b9d8ce50af3a978b4d5ccc";
                                         
                                        String url = "https://api.indix.com/v2/universal/products?countryCode=US&brandId="+brandId+categoryRef+"availability=IN_STOCK&lastRecordedIn=30&app_id=9867e55c&app_key=8d79be1be9b9d8ce50af3a978b4d5ccc";
                                         
                                        ValueExpression ve_is_brand_category_selected = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.brandCategorySelected}", String.class);
                                        ve_is_brand_category_selected.setValue(AdfmfJavaUtilities.getAdfELContext(),"true");
                                        
                                        ValueExpression ve_brand_ref = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.brandRef}", String.class);
                                        ve_brand_ref.setValue(AdfmfJavaUtilities.getAdfELContext(),brandId);
                                        
                                        ValueExpression ve_category_ref = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.categoryRef}", String.class);
                                        ve_category_ref.setValue(AdfmfJavaUtilities.getAdfELContext(),categoryRef);
                                                                                  
                                         
                                         
                                                                               
                                        URL obj = new URL(url);
                                        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
                                        // optional default is GET
                                        con.setRequestMethod("GET");
                                        //add request header
                                        int responseCode = con.getResponseCode();
                                        System.out.println("\nSending 'GET' request to URL : " + url);
                                        System.out.println("Response Code : " + responseCode);
                                        BufferedReader in = new BufferedReader(
                                           new InputStreamReader(con.getInputStream()));
                                        String inputLine;
                                        StringBuffer response1 = new StringBuffer();
                                            while ((inputLine = in.readLine()) != null) {
                                                      response1.append(inputLine);
                                                 }
                                               in.close();
                                                 //print result
                                            //    System.out.println(response1.toString());
                                            resp=new JSONObject(response1.toString());
                                            output=resp.getJSONObject("result");
                                            JSONArray resArr=output.getJSONArray("products");
                                            // System.out.println("resArr.length() "+resArr.length());
                                         
                                            for(int i=0;i<resArr.length();i++) {
                                                  rowCount=1;
                                                  //    System.out.println("***********");
                                                  JSONObject productObj=resArr.getJSONObject(i);
                                            //     System.out.println(productObj.toString());
                                              //   System.out.println("***********");
                                                   String poNo="";
                                                   String vendorName="";
                                                   String vendorSiteCode="";
                                                   String productCategory=productObj.getString("categoryName");
                                                   
                                                  // String unitPrice=productObj.getString("minSalePrice");
                                                  // String imageUrl=productObj.getString("imageUrl");
                                                   JSONObject stores=productObj.getJSONObject("stores");
                                                   String indixCategoryId=productObj.getString("categoryId");                                      
                                                   Iterator<?> keys = stores.keys();
                                                   while( keys.hasNext() ) {
                                                        String key = (String)keys.next();
                                                        JSONObject store=stores.getJSONObject(key);
                                                        vendorName=store.getString("storeName");
                                                String productTitle=store.getString("title");
                                                       Random randomGenerator = new Random();
                                                       
                                                         String showDiverSeImage="false";
                                                         String diverseImageURL="";
                                                
                                                /////
                                                JSONArray Offer=store.getJSONArray("offers");
                                                       
                                                         for(int k=0;k<Offer.length();k++) {
                                                             rowCount=1;
                                                             JSONObject offer=Offer.getJSONObject(k);
                                                             String imageUrl=offer.getString("imageUrl");
                                                             String seller=offer.getString("seller");
                                                         String unitPrice=offer.getString("salePrice");
                                                         System.out.println("*-*-*-Image Url is "+imageUrl+"*-*-*-Seller Is"+seller+"*-*-*-*-Seller Price"+unitPrice);
                                                         String showSeller="true";
                                                         if (seller.equalsIgnoreCase("")) {
                                                                                 showSeller="false";
                                                                             }
                                                             
                                                         JSONObject attValues=offer.getJSONObject("attributes");
                                                         ItemsList.Specification.clear();
                                                         String showAttrib="true";
                                                         String showSpec="true";
                                                         String resultVal = "";
                                                         String spec = "";
                                                            System.out.println(offer+"======>num===>"+attValues.length());
                                                         if (attValues.length() == 0) {
                                                            System.out.println("Length 0");
                                                            showAttrib = "false";
                                                            showSpec="false";
                                                            Specification.clear();
                                                            /*BasicIterator vex = (BasicIterator) AdfmfJavaUtilities.getELValue("#{bindings.assets5.iterator}");
                                                            vex.refresh();*/
                                                         }else{
                                                           
                                                           Iterator<?> att = attValues.keys(); 
                                                           if(attValues.length() == 1){
                                                               StringBuffer sb2 = new StringBuffer();
                                                               System.out.println("Length 1");
                                                               showAttrib = "true";
                                                               showSpec="false";
                                                               while(att.hasNext()) {
                                                                   String attributeKey = (String)att.next();
                                                         //                                                                          System.out.println("JSON Key Single---------------->"+attributeKey);
                                                                   JSONArray attributeValues = attValues.getJSONArray(attributeKey);
                                                         //                                                                          System.out.println("JSON Value Single ---------------->"+attributeValues);
                                                                   resultVal =attributeKey+" : "+attributeValues.getString(0);
                                                                   
                                                                   System.out.println("");
                                                                   ItemsList.Specification.add(resultVal+"#");
                                                         //                                                                          System.out.println("KEY&Value---------------->"+resultVal);
                                                         //                                                                          System.out.println("Single Attributes---------------->"+Specification);
                                                                   sb2.append(resultVal+"#,");
                                                               }
                                                               /*BasicIterator vex = (BasicIterator) AdfmfJavaUtilities.getELValue("#{bindings.assets5.iterator}");
                                                               vex.refresh();*/
                                                         spec = sb2.toString();
                                                         System.out.println("Value at 1===> "+spec);
                                                         Specification.clear();
                                                         }else{
                                                         System.out.println("Length >1");
                                                         StringBuffer sb2 = new StringBuffer();
                                                         showAttrib = "false";
                                                         showSpec="true";
                                                         while(att.hasNext()) {
                                                             String attributeKey = (String)att.next();
                                                             JSONArray attributeValues = attValues.getJSONArray(attributeKey);
                                                             resultVal =attributeKey+" : "+attributeValues.getString(0);
                                                             ItemsList.Specification.add(resultVal+"#");
                                                             /*
                                                             for(int a=0; a < attributeValues.length(); a++) {
                                                                 resultVal =attributeKey+" : "+attributeValues.getString(a);
                                                                 ItemsList.Specification.add(resultVal);    
                                                         //                                                                              System.out.println("MultiAttribute Json<------------->"+attributeKey+"  :  "+resultVal);
                                                             }
                                                             */
                                                           sb2.append(resultVal+"#,");
                                                             
                                                         //                                                                          System.out.println("Multi-Attribute LIST <------------->"+spec);
                                                        /* BasicIterator vex = (BasicIterator) AdfmfJavaUtilities.getELValue("#{bindings.assets5.iterator}");
                                                         vex.refresh();*/
                                                         }
                                                         //spec = Specification.toString();
                                                         spec = sb2.toString();
                                                         System.out.println("Value at >1===> "+spec);
                                                         //Specification.clear();
                                                         }  
                                                          
                                                          
                                                          
                                                         }
                                                        
                                                      
                                                             int randomInt = randomGenerator.nextInt(1000000000); 
                                                       Item j = new Item(poNo, vendorName, vendorSiteCode, productCategory, productTitle, unitPrice,imageUrl,"/images/uncheck.png","","Each",String.valueOf(randomInt),"-1",showDiverSeImage,diverseImageURL,pageNo,indixCategoryId,seller,showSeller,resultVal,showAttrib,spec,showSpec);
                                                       ItemsList.s_jobs.add(j); 
                                                 //   System.out.println("***********");
                                                
                                                     }
                                        }
                                    }
                                        
                                        
                                    }
                                    catch(Exception e) {
                                        e.printStackTrace();
                                    }
                                    
                                    if(ItemsList.s_jobs.size()>0){
                                        
                                        ValueExpression ve71 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.displayPrev}", String.class);
                                        ve71.setValue(AdfmfJavaUtilities.getAdfELContext(),"false");
                                        
                                        ValueExpression ve72 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.displayNext}", String.class);
                                        ve72.setValue(AdfmfJavaUtilities.getAdfELContext(),"false");
                                        ValueExpression ve19 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.displayAddToCart}", String.class);
                                        ve19.setValue(AdfmfJavaUtilities.getAdfELContext(),"true");
                                     
                                    
                                        /*AdfmfContainerUtilities.invokeContainerJavaScriptFunction(AdfmfJavaUtilities.getFeatureId(),
                                                                                                   "displayAlert",
                                                                                                   new Object[] { });*/
                                       AdfmfContainerUtilities.invokeContainerJavaScriptFunction(AdfmfJavaUtilities.getFeatureName(),
                                                                                                   "adf.mf.api.amx.doNavigation", new Object[] { "ref_search" });   
                                    }
                                    else{
                                        AdfmfContainerUtilities.invokeContainerJavaScriptFunction(
                                                                     AdfmfJavaUtilities.getFeatureName(),
                                                                     "adf.mf.api.amx.addMessage", new Object[] {AdfException.ERROR,
                                                                     "Brand and Category given are not a valid combination",
                                                                     null,
                                                                     null }); 
                                    }
                                    ValueExpression ve7 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.searchCount}", String.class);
                                    ve7.setValue(AdfmfJavaUtilities.getAdfELContext(),ItemsList.s_jobs.size()+" matches found");
                                    System.out.println("ItemList Count:-++++---->"+ItemsList.s_jobs.size()); 
                                    
                                }
                                
                                else{
                                
                        try{
                            
                              
                               //String url = "https://api.indix.com/v2/offersPremium/products"+"?"+"countryCode=US&"+query+"&availability=IN_STOCK&lastRecordedIn=30&app_id=9867e55c&app_key=8d79be1be9b9d8ce50af3a978b4d5ccc";
                           String url="";
                            /*  if(itemCategories.equalsIgnoreCase("") && upc.equalsIgnoreCase("") && !brand.equalsIgnoreCase("") && partNo.equalsIgnoreCase("") && product_url.equalsIgnoreCase("")&& !quantity.equalsIgnoreCase("") && !uom.equalsIgnoreCase("") && !needByDate.equalsIgnoreCase("")){
                                          
                                  url = "https://api.indix.com/v2/universal/products?countryCode=US&q="+search+query+"&availability=IN_STOCK&lastRecordedIn=30&app_id=9867e55c&app_key=8d79be1be9b9d8ce50af3a978b4d5ccc";
                                }
                              else{
                                 url = "https://api.indix.com/v2/universal/products?countryCode=US"+query+"&app_id=9867e55c&app_key=8d79be1be9b9d8ce50af3a978b4d5ccc";
                              }
                            */
                            
                            if(!brand.equalsIgnoreCase("")){
                                
                                if(itemCategories.equalsIgnoreCase("") && upc.equalsIgnoreCase("") && !brand.equalsIgnoreCase("") && partNo.equalsIgnoreCase("") && product_url.equalsIgnoreCase("")&& !quantity.equalsIgnoreCase("") && !uom.equalsIgnoreCase("") && !needByDate.equalsIgnoreCase("")){
                                            
                                    url = "https://api.indix.com/v2/universal/products?countryCode=US&q="+search+query+"&availability=IN_STOCK&lastRecordedIn=30&app_id=9867e55c&app_key=8d79be1be9b9d8ce50af3a978b4d5ccc";
                                  }
                                else{
                                   url = "https://api.indix.com/v2/universal/products?countryCode=US"+query+"&app_id=9867e55c&app_key=8d79be1be9b9d8ce50af3a978b4d5ccc";
                                }
                                   
                                }
                            else{
                               url = "https://api.indix.com/v2/universal/products?countryCode=US"+query+"&app_id=9867e55c&app_key=8d79be1be9b9d8ce50af3a978b4d5ccc";
                            }
                               ValueExpression ve_is_query_ref = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.isQueryRefSet}", String.class);
                               ve_is_query_ref.setValue(AdfmfJavaUtilities.getAdfELContext(),"true");
                             
                               
                               ValueExpression ve_query_ref = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.queryRef}", String.class);
                               ve_query_ref.setValue(AdfmfJavaUtilities.getAdfELContext(),query);  
                            
                                                                      
                               URL obj = new URL(url);
                               HttpURLConnection con = (HttpURLConnection) obj.openConnection();
                               // optional default is GET
                               con.setRequestMethod("GET");
                               //add request header
                               int responseCode = con.getResponseCode();
                               System.out.println("\nSending 'GET' request to URL : " + url);
                               System.out.println("Response Code : " + responseCode);
                               BufferedReader in = new BufferedReader(
                                  new InputStreamReader(con.getInputStream()));
                               String inputLine;
                               StringBuffer response1 = new StringBuffer();
                                   while ((inputLine = in.readLine()) != null) {
                                             response1.append(inputLine);
                                        }
                                      in.close();
                                        //print result
                                   //    System.out.println(response1.toString());
                                   resp=new JSONObject(response1.toString());
                                   output=resp.getJSONObject("result");
                                   JSONArray resArr=output.getJSONArray("products");
                                   // System.out.println("resArr.length() "+resArr.length());
                                
                                   for(int i=0;i<resArr.length();i++) {
                                         rowCount=1;
                                         //    System.out.println("***********");
                                         JSONObject productObj=resArr.getJSONObject(i);
                                   //     System.out.println(productObj.toString());
                                     //   System.out.println("***********");
                                          String poNo="";
                                          String vendorName="";
                                          String vendorSiteCode="";
                                          String productCategory=productObj.getString("categoryName");
                                          
                                         // String unitPrice=productObj.getString("minSalePrice");
                                         // String imageUrl=productObj.getString("imageUrl");
                                          JSONObject stores=productObj.getJSONObject("stores");
                                          String indixCategoryId=productObj.getString("categoryId");                                      
                                          Iterator<?> keys = stores.keys();
                                          while( keys.hasNext() ) {
                                               String key = (String)keys.next();
                                               JSONObject store=stores.getJSONObject(key);
                                               vendorName=store.getString("storeName");
                                       String productTitle=store.getString("title");
                                              Random randomGenerator = new Random();
                                              
                                                String showDiverSeImage="false";
                                                String diverseImageURL="";
                                       
                                       /////
                                       JSONArray Offer=store.getJSONArray("offers");
                                              
                                                for(int k=0;k<Offer.length();k++) {
                                                    rowCount=1;
                                                    JSONObject offer=Offer.getJSONObject(k);
                                                    String imageUrl=offer.getString("imageUrl");
                                                    String seller=offer.getString("seller");
                                                String unitPrice=offer.getString("salePrice");
                                                System.out.println("*-*-*-Image Url is "+imageUrl+"*-*-*-Seller Is"+seller+"*-*-*-*-Seller Price"+unitPrice);
                                                String showSeller="true";
                                                if (seller.equalsIgnoreCase("")) {
                                                                        showSeller="false";
                                                                    }
                                                    
                                                JSONObject attValues=offer.getJSONObject("attributes");
                                                ItemsList.Specification.clear();
                                                String showAttrib="true";
                                                String showSpec="true";
                                                String resultVal = "";
                                                String spec = "";
                                                   System.out.println(offer+"======>num===>"+attValues.length());
                                                if (attValues.length() == 0) {
                                                   System.out.println("Length 0");
                                                   showAttrib = "false";
                                                   showSpec="false";
                                                   Specification.clear();
                                                   /*BasicIterator vex = (BasicIterator) AdfmfJavaUtilities.getELValue("#{bindings.assets5.iterator}");
                                                   vex.refresh();*/
                                                }else{
                                                  
                                                  Iterator<?> att = attValues.keys(); 
                                                  if(attValues.length() == 1){
                                                      StringBuffer sb2 = new StringBuffer();
                                                      System.out.println("Length 1");
                                                      showAttrib = "true";
                                                      showSpec="false";
                                                      while(att.hasNext()) {
                                                          String attributeKey = (String)att.next();
                                                //                                                                          System.out.println("JSON Key Single---------------->"+attributeKey);
                                                          JSONArray attributeValues = attValues.getJSONArray(attributeKey);
                                                //                                                                          System.out.println("JSON Value Single ---------------->"+attributeValues);
                                                          resultVal =attributeKey+" : "+attributeValues.getString(0);
                                                          
                                                          System.out.println("");
                                                          ItemsList.Specification.add(resultVal+"#");
                                                //                                                                          System.out.println("KEY&Value---------------->"+resultVal);
                                                //                                                                          System.out.println("Single Attributes---------------->"+Specification);
                                                          sb2.append(resultVal+"#,");
                                                      }
                                                      /*BasicIterator vex = (BasicIterator) AdfmfJavaUtilities.getELValue("#{bindings.assets5.iterator}");
                                                      vex.refresh();*/
                                                spec = sb2.toString();
                                                System.out.println("Value at 1===> "+spec);
                                                Specification.clear();
                                                }else{
                                                System.out.println("Length >1");
                                                StringBuffer sb2 = new StringBuffer();
                                                showAttrib = "false";
                                                showSpec="true";
                                                while(att.hasNext()) {
                                                    String attributeKey = (String)att.next();
                                                    JSONArray attributeValues = attValues.getJSONArray(attributeKey);
                                                    resultVal =attributeKey+" : "+attributeValues.getString(0);
                                                    ItemsList.Specification.add(resultVal+"#");
                                                    /*
                                                    for(int a=0; a < attributeValues.length(); a++) {
                                                        resultVal =attributeKey+" : "+attributeValues.getString(a);
                                                        ItemsList.Specification.add(resultVal);    
                                                //                                                                              System.out.println("MultiAttribute Json<------------->"+attributeKey+"  :  "+resultVal);
                                                    }
                                                    */
                                                  sb2.append(resultVal+"#,");
                                                    
                                                //                                                                          System.out.println("Multi-Attribute LIST <------------->"+spec);
                                               /* BasicIterator vex = (BasicIterator) AdfmfJavaUtilities.getELValue("#{bindings.assets5.iterator}");
                                                vex.refresh();*/
                                                }
                                                //spec = Specification.toString();
                                                spec = sb2.toString();
                                                System.out.println("Value at >1===> "+spec);
                                                //Specification.clear();
                                                }  
                                                 
                                                 
                                                 
                                                }
                                               
                                             
                                                    int randomInt = randomGenerator.nextInt(1000000000); 
                                              Item j = new Item(poNo, vendorName, vendorSiteCode, productCategory, productTitle, unitPrice,imageUrl,"/images/uncheck.png","","Each",String.valueOf(randomInt),"-1",showDiverSeImage,diverseImageURL,pageNo,indixCategoryId,seller,showSeller,resultVal,showAttrib,spec,showSpec);
                                              ItemsList.s_jobs.add(j); 
                                            //   System.out.println("***********");
                                           
                                                }
                                   }
                                   }
                                   
                                   
                                   }
                                            catch(Exception e){
                                                e.printStackTrace();
                                            }
                                            
        ValueExpression ve7 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.searchCount}", String.class);
        ve7.setValue(AdfmfJavaUtilities.getAdfELContext(),ItemsList.s_jobs.size()+" matches found");
                                    
                                        System.out.println("ItemList Count:----->"+ItemsList.s_jobs.size());    
                                    if(ItemsList.s_jobs.size()>0)    
                                    {
                                ValueExpression ve19 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.displayAddToCart}", String.class);
                                ve19.setValue(AdfmfJavaUtilities.getAdfELContext(),"true");
                                    }
                                    
                                ValueExpression ve11 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.item_categories}", String.class);
                                ve11.setValue(AdfmfJavaUtilities.getAdfELContext(),"");
                                ValueExpression ve12 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.suppliers}", String.class);
                                ve12.setValue(AdfmfJavaUtilities.getAdfELContext(),"");
                                ValueExpression ve13 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.web_url}", String.class);
                                ve13.setValue(AdfmfJavaUtilities.getAdfELContext(),"");
                                ValueExpression ve14 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.part_no}", String.class);
                                ve14.setValue(AdfmfJavaUtilities.getAdfELContext(),"");
                                ValueExpression ve15 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.brand}", String.class);
                                ve15.setValue(AdfmfJavaUtilities.getAdfELContext(),"");
                                ValueExpression ve16 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.upc}", String.class);
                                ve16.setValue(AdfmfJavaUtilities.getAdfELContext(),"");
                                ValueExpression ve17 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.quantity}", String.class);
                                ve17.setValue(AdfmfJavaUtilities.getAdfELContext(),"");
                                ValueExpression ve18 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.uom1}", String.class);
                                ve18.setValue(AdfmfJavaUtilities.getAdfELContext(),"");
                                    
                            /*-*-*/
                             /*ValueExpression vf19 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.searchValue}", String.class);
                                    vf19.setValue(AdfmfJavaUtilities.getAdfELContext(),"");*/

                                    ValueExpression vf20 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.need_by_date}", String.class);
                                    vf20.setValue(AdfmfJavaUtilities.getAdfELContext(),"");
                                    ValueExpression vf21 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.rfqComments}", String.class);
                                    vf21.setValue(AdfmfJavaUtilities.getAdfELContext(),"");
                                    ValueExpression vf22 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.selectedImagesCount}", String.class);
                                    vf22.setValue(AdfmfJavaUtilities.getAdfELContext(),"");
                                    ValueExpression vf23 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.categoryDescription}", String.class);
                                    vf23.setValue(AdfmfJavaUtilities.getAdfELContext(),"");
                                    ValueExpression vf24 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.categoryDescription1}", String.class);
                                    vf24.setValue(AdfmfJavaUtilities.getAdfELContext(),"");
                                    ValueExpression vf25 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.categoryDescription2}", String.class);
                                    vf25.setValue(AdfmfJavaUtilities.getAdfELContext(),"");
                                    ValueExpression vf26 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.categoryDescription3}", String.class);
                                    vf26.setValue(AdfmfJavaUtilities.getAdfELContext(),"");
                                    ValueExpression vf27 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.searchSupplierValue}", String.class);
                                    vf27.setValue(AdfmfJavaUtilities.getAdfELContext(),"");
                             /*-*-*/   
                           
                                AdfmfJavaUtilities.flushDataChangeEvent();    
                                    
                                ValueExpression ve71 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.displayPrev}", String.class);
                                ve71.setValue(AdfmfJavaUtilities.getAdfELContext(),"false");
                                
                                ValueExpression ve72 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.displayNext}", String.class);
                                ve72.setValue(AdfmfJavaUtilities.getAdfELContext(),"false");
                                            
                                AdfmfContainerUtilities.invokeContainerJavaScriptFunction(AdfmfJavaUtilities.getFeatureName(),
                                                                                               "adf.mf.api.amx.doNavigation", new Object[] { "ref_search" });   
                                            
                            }
                                    
                           }
                           else{
                               /*if((!brand.equalsIgnoreCase("") || !supplierNames.equalsIgnoreCase("")) &&itemCategories.equalsIgnoreCase("")) {
                                   AdfmfContainerUtilities.invokeContainerJavaScriptFunction(
                                                                AdfmfJavaUtilities.getFeatureName(),
                                                                "adf.mf.api.amx.addMessage", new Object[] {AdfException.ERROR,
                                                                "Item Category is mandatory...Please enter it and resubmit the search",
                                                                null,
                                                                null }); 
                               }*/
                               if((!supplierNames.equalsIgnoreCase("")) &&itemCategories.equalsIgnoreCase("")) {
                                   AdfmfContainerUtilities.invokeContainerJavaScriptFunction(
                                                                AdfmfJavaUtilities.getFeatureName(),
                                                                "adf.mf.api.amx.addMessage", new Object[] {AdfException.ERROR,
                                                                "Item Category is mandatory...Please enter it and resubmit the search",
                                                                null,
                                                                null }); 
                               }
                               if(!partNo.equalsIgnoreCase("") && brand.equalsIgnoreCase("")) 
                                   {
                                       AdfmfContainerUtilities.invokeContainerJavaScriptFunction(
                                                                            AdfmfJavaUtilities.getFeatureName(),
                                                                            "adf.mf.api.amx.addMessage", new Object[] {AdfException.ERROR,
                                                                            "Manufacturer Part No works in combination with Brand. and so please enter the same too",
                                                                            null,
                                                                            null });     
                                   }
                               if(quantity.equalsIgnoreCase("")) {
                                   
                                   AdfmfContainerUtilities.invokeContainerJavaScriptFunction(
                                                                AdfmfJavaUtilities.getFeatureName(),
                                                                "adf.mf.api.amx.addMessage", new Object[] {AdfException.ERROR,
                                                                "Quantity is mandatory...Please enter it and resubmit the search",
                                                                null,
                                                                null }); 
                               }
                               else if(uom.equalsIgnoreCase("")) {
                                   
                                   AdfmfContainerUtilities.invokeContainerJavaScriptFunction(
                                                                AdfmfJavaUtilities.getFeatureName(),
                                                                "adf.mf.api.amx.addMessage", new Object[] {AdfException.ERROR,
                                                                "UOM is mandatory...Please enter it and resubmit the search",
                                                                null,
                                                                null }); 
                               }
                               
                               else if(needByDate.equalsIgnoreCase("")) {
                                   
                                   AdfmfContainerUtilities.invokeContainerJavaScriptFunction(
                                                                AdfmfJavaUtilities.getFeatureName(),
                                                                "adf.mf.api.amx.addMessage", new Object[] {AdfException.ERROR,
                                                                "Need By Date is mandatory...Please enter it and resubmit the search",
                                                                null,
                                                                null }); 
                               }
                              
                              
                               }
                }
    
    
 
}
