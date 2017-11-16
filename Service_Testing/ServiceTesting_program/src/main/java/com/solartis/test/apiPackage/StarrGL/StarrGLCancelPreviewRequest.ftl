{
    "EndClientUserUniqueSessionId": "Uniquesession",
    "OwnerId": "30",
    "Policy": {
    <#list Policy as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
  </#list>
      },
    "ServiceRequestDetail": {
    <#list servicedetail as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
  </#list>
  }
 }