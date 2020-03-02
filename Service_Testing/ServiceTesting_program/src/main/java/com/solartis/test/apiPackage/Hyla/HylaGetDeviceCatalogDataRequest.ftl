<#assign Numofdevices=[]><#list Noofdevices as x><#assign Numofdevices=Numofdevices+[x.value]></#list>
{    
	
      
     "ServiceRequestDetail": {
     <#list ServiceRequestDetail as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if></#list>
        },
      
 
        "DeviceCatalog": [
         <#assign i=1>
        <#list 1..Numofdevices[0] as result>
        <#assign DeviceCatalogDetails="DeviceCatalogDetail"+i>
            {
               <#list DeviceCatalogDetails?eval as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if></#list>
        }<#if result?is_last><#else>,</#if><#assign i=i+1>
        </#list>  
           
         ]
          }