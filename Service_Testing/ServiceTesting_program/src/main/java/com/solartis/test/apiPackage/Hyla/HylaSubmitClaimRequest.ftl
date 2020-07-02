
{    
	<#list Policy as result>"${result.atrib}":"${result.value}",</#list>
	 "deviceInformation":[
       {
       <#list device as result>"${result.atrib}":"${result.value}",</#list>
       
        "loss": [
        {
         <#list loss as result>"${result.atrib}":"${result.value}",</#list>
        "claim": [
            {
       <#list claim as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if></#list>
        
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
