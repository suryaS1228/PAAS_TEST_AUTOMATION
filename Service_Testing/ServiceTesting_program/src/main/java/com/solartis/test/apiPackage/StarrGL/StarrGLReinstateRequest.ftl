{
  "EndClientUserUniqueSessionId": "Uniquesession",
   "OwnerId": "30",
   "ServiceRequestDetail": {
    <#list ServiceRequestDetail as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
    </#list>
  },
  "Policy": {
   <#list Policy as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
   </#list>
 }
}