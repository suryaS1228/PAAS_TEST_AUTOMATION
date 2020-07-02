{    
	  
     "ServiceRequestDetail": {
     <#list ServiceRequestDetail as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if></#list>
        },
         <#list Policy as result>"${result.atrib}":"${result.value}",</#list>
       "deviceInformation": [
       {
         <#list deviceInformationDetail as result>"${result.atrib}":"${result.value}",</#list>
       "loss": [
        {
          "claim": [
            {
             <#list ClaimDetail as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if></#list>
      
             
            }
          ]
        }
      ]
    }
  ]
}