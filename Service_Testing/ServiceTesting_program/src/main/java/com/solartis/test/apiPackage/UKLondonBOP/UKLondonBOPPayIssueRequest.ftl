<#assign NoOfClaimsListarray=[]><#list NumofClaim as x><#assign NoOfClaimsListarray=NoOfClaimsListarray+[x.value]></#list>
<#assign NoOfLocationDetailarray=[]><#list NumofLocation as x><#assign NoOfLocationDetailarray=NoOfLocationDetailarray+[x.value]></#list>
<#assign NoOfBuildingDetailarray=[]><#list NumofBuilding as x><#assign NoOfBuildingDetailarray=NoOfBuildingDetailarray+[x.value]></#list>
<#assign NoOfClassificationDetailarray=[]><#list NumofClassification as x><#assign NoOfClassificationDetailarray=NoOfClassificationDetailarray+[x.value]></#list>
<#assign i=1>
<#assign j=1>
<#assign k=1>
<#assign l=1>
{
"ServiceRequestDetail": {
  <#list ServiceRequestDetail as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
  </#list>
  },  
  "OwnerId": "38",
  "EndClientUserUniqueSessionId": "Uniquesession",
  "Policy": {
  <#list Policy as result>"${result.atrib}":"${result.value}",
  </#list> 
  "Location": 
  [
  <#list 1..NoOfLocationDetailarray[0] as result>
  <#assign LocationDetail ="LocationDetail"+i>  
      {
        "LocationDetail": {
        <#list LocationDetail?eval as result>
	    "${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
		</#list>        
         "Building": [         
         <#list 1..NoOfBuildingDetailarray[0] as result>
         <#assign BuildingDetail ="BuildingDetail"+j>         
            {
              "BuildingDetail": {
               <#list BuildingDetail?eval as result>
	           "${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
		       </#list>               
              "Classification": 
              [
              <#list 1..NoOfClassificationDetailarray[0] as result>
              <#assign ClassificationDetail ="ClassificationDetail"+k>              
                  {
                    "ClassificationDetail": {                    
               <#list ClassificationDetail?eval as result>
	           "${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
		       </#list>                                     
                   }
                  }               
                 <#if k=NoOfClassificationDetailarray[0]><#else>,</#if><#assign k=k+1>
                 </#list>                    
                ]
              }
            }            
      <#if j=NoOfBuildingDetailarray[0]><#else>,</#if><#assign j=j+1>
      </#list>                       
         ]
        }
      }
      <#if i=NoOfLocationDetailarray[0]><#else>,</#if><#assign i=i+1>
      </#list>  
    ],    
    "ClaimsList":
     [
  <#list 1..NoOfClaimsListarray[0] as result>
  <#assign ClaimsList ="ClaimsList"+l>        
      {      
      <#list ClaimsList?eval as result>
	           "${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
		       </#list>             
      }      
 <#if l=NoOfClaimsListarray[0]><#else>,</#if><#assign l=l+1>
 </#list>                 
    ]
  }
 }

