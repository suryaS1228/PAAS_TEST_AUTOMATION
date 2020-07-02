{    
	
      
     "ServiceRequestDetail": {
     <#list ServiceRequestDetail as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if></#list>
        },
         "loginCredentials": {
		<#list Policypswd as result>"${result.atrib}":"${result.value}"</#list>
 
  },
<#list Policy as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if></#list>
    }