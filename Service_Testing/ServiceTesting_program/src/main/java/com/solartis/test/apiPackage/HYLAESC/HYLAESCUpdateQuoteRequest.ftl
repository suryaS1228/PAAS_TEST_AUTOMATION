{
 "ServiceRequestDetail": {
    <#list ServiceRequestDetail as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
  </#list>
  
  },
    "deviceInformation": [{
    <#list deviceInformation as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
  </#list>
  }],

    <#list InsuredDetail as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
  </#list>  
}