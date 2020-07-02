{
  "ServiceRequestDetail": {
  <#list ServiceRequestDetail as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if></#list>
       
  },
  "UserCredential": {
   <#list UserCredential as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if></#list>
      
  }
}