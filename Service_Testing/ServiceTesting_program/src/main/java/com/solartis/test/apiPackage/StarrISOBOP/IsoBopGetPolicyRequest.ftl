 {
  "Policy": {
   <#list Policy as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
    </#list>
  },
  "ServiceRequestDetail": {
   <#list ServiceRequestDetail as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
   </#list>
   },
   "OwnerId": "24",
   "EndClientUserUniqueSessionId": "Uniquesession"
 }
