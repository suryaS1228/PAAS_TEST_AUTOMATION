
   {
  "ServiceRequestDetail": {
   <#list ServiceRequestDetail as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if></#list>
      
  },
  
  "dynamicInformation": [
    {
     <#list dynamicInformationDetail as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if></#list>
     
    }
  ],
  "deviceInformation": [
    {
      <#list deviceInformationDetail as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if></#list>
     
    }
  ],
  "Claim": [
    {
     <#list ClaimDetail as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if></#list>
      
    }
  ],
  <#list Policy as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if></#list>
      
}
    
    
  