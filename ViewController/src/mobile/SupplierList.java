package mobile;

import java.util.ArrayList;
import java.util.List;

import javax.el.ValueExpression;

import oracle.adfmf.amx.event.ActionEvent;
import oracle.adfmf.bindings.iterator.BasicIterator;
import oracle.adfmf.dc.ws.rest.RestServiceAdapter;
import oracle.adfmf.framework.api.AdfmfContainerUtilities;
import oracle.adfmf.framework.api.AdfmfJavaUtilities;
import oracle.adfmf.framework.api.Model;
import oracle.adfmf.framework.exception.AdfException;
import oracle.adfmf.java.beans.PropertyChangeSupport;
import oracle.adfmf.java.beans.ProviderChangeSupport;

import org.json.JSONArray;
import org.json.JSONObject;

public class SupplierList {
    private  transient ProviderChangeSupport providerChangeSupport = new ProviderChangeSupport(this);
    private  transient PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
    public static List s_jobs = new ArrayList();
    public static ArrayList<Integer> selectedSuppliers = new ArrayList<Integer>(); 
    
    public SupplierList() {
        super();
        if (s_jobs == null) {
            s_jobs = new ArrayList();
        }
    }
    
    public Supplier[] getSupplier() {
        Supplier e[] = null;
        e = (Supplier[])s_jobs.toArray(new Supplier[s_jobs.size()]);
        return e;
    }
    
    
    public void getSupplierList() {
        
        Supplier j = new Supplier(); 
        s_jobs.add(j);
        
          
        
    }
    
    public void performSupplierSearch() {
        // Add event code here...
        
        try{
        ValueExpression ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.searchSupplierValue}", String.class);
        String supplierName = (String)ve.getValue(AdfmfJavaUtilities.getAdfELContext());
        
       
        
        RestServiceAdapter restServiceAdapter = Model.createRestServiceAdapter();
        // Clear any previously set request properties, if any
        restServiceAdapter.clearRequestProperties();
        // Set the connection name
        restServiceAdapter.setConnectionName("enrich");
        
        restServiceAdapter.setRequestType(RestServiceAdapter.REQUEST_TYPE_POST);
        restServiceAdapter.addRequestProperty("Accept", "application/json; charset=UTF-8");
        restServiceAdapter.addRequestProperty("Authorization", "Basic " + "WFhFX1JFU1RfU0VSVklDRVNfQURNSU46b3JhY2xlMTIz");
        restServiceAdapter.addRequestProperty("Content-Type", "application/json");
        restServiceAdapter.setRequestURI("/webservices/rest/XXETailSpendAPI/predictive_search_supplier/");
        String postData= "{\n" + 
        "  \"PREDICTIVE_SEARCH_SUPPLIER_Input\" : {\n" + 
        "   \"@xmlns\" : \"http://xmlns.oracle.com/apps/po/rest/XXETailSpendAPI/predictive_search_supplier/\",\n" + 
        "   \"RESTHeader\": {\n" + 
        "   \"@xmlns\" : \"http://xmlns.oracle.com/apps/po/rest/XXETailSpendAPI/header\"\n" + 
        "    },\n" + 
        "   \"InputParameters\": {\n" + 
        "        \"P_TEXT\": \""+supplierName+"\"\n" + 
        "     }\n" + 
        "  }\n" + 
        "}  \n";
                                    restServiceAdapter.setRetryLimit(0);
           System.out.println("postData===============================" + postData);
            
           String response = restServiceAdapter.send(postData);
            System.out.println("response===============================" + response);
            
            JSONObject resp=new JSONObject(response);
            JSONObject output=resp.getJSONObject("OutputParameters");
            
            
            SupplierList.s_jobs.clear();
            if(!output.isNull("X_SEARCH_RESULTS_TL")){
                JSONObject data=output.getJSONObject("X_SEARCH_RESULTS_TL");
            if(data.get("X_SEARCH_RESULTS_TL_ITEM") instanceof  JSONArray){
               JSONArray segments=data.getJSONArray("X_SEARCH_RESULTS_TL_ITEM");
               for(int i=0;i<segments.length();i++) {
                   String name=(String)segments.get(i);
                   Supplier s=new Supplier(name);
                   SupplierList.s_jobs.add(s);
                   
               }
            
            }
            
            else if(data.get("X_SEARCH_RESULTS_TL_ITEM") instanceof  JSONObject){
                
                //JSONObject segments=data.getJSONObject("X_SEGMENT_VALUES_TL_ITEM");
                
            }
            else{
                String seg=data.getString("X_SEARCH_RESULTS_TL_ITEM");
                Supplier s=new Supplier(seg);
                SupplierList.s_jobs.add(s);
            }
            
            
            }
            
            
            BasicIterator vex = (BasicIterator) AdfmfJavaUtilities.getELValue("#{bindings.supplier.iterator}");  
            vex.refresh();
            
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
    
    public void selectSuppliers() {
        // Add event code here...
        
        ValueExpression ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.suppliers}", String.class);
        String supplierNames = (String)ve.getValue(AdfmfJavaUtilities.getAdfELContext());
        
        for(int i=0;i<selectedSuppliers.size();i++) {
            int indx=selectedSuppliers.get(i);
            Supplier s=(Supplier)SupplierList.s_jobs.get(indx);
            if(supplierNames==null || supplierNames.equalsIgnoreCase("")) {
                
                supplierNames=s.getName();
            }
            else{
                supplierNames=supplierNames+","+s.getName();
            }
            
        }
        
        System.out.println("Selected suppliers are "+supplierNames);
        ve.setValue(AdfmfJavaUtilities.getAdfELContext(), supplierNames);
        AdfmfJavaUtilities.flushDataChangeEvent();
        
        
    }
    
    public static void onSupplierChange(Object[] newVal){
        selectedSuppliers.clear();
        for (int i = 0; i < newVal.length; i++) {
         System.out.println(newVal[i].toString());
         selectedSuppliers.add(new Integer((String)newVal[i]));
        }
        System.out.println(selectedSuppliers.size());
        
    }
    
    
    
    public void onSupplierAdd(){
        ValueExpression ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.supplierName}", String.class);
        String supplierNames = (String)ve.getValue(AdfmfJavaUtilities.getAdfELContext());
        selectedSuppliers.add(new Integer(supplierNames));
    }
    
    public void clearSuppliers(){
        selectedSuppliers.clear();
    }
    
    
    public void test(String t){
        System.out.println("rrrrrrrr "+t);
    }

}
