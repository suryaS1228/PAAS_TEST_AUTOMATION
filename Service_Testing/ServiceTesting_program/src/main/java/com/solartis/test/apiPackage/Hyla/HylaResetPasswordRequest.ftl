{    
	 "ServiceRequestDetail": {
     <#list ServiceRequestDetail as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if></#list>
        },
         "loginCredentials": {
     <#list loginCredentials as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if></#list>
        }
        
    }
    
    
    
  