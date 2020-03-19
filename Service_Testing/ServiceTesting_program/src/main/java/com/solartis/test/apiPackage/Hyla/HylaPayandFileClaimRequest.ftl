
{    
	<#list Policy as result>"${result.atrib}":"${result.value}",</#list>
	 "deviceInformation":[
       {
       <#list device as result>"${result.atrib}":"${result.value}",</#list>
       
        "loss": [
        {
        "claim": [
            {
       <#list claim as result>"${result.atrib}":"${result.value}",</#list>
        "settlementDetail": {
               <#list settlement as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if></#list>
              }
       }
       ]
       }
       ]
         }  
      ], 
	  
     "ServiceRequestDetail": {
     <#list ServiceRequestDetail as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if></#list>
        }
    }    