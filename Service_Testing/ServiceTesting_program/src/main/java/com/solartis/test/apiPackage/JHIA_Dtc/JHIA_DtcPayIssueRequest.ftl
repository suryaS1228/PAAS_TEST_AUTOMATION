{
  <#list Policy as result>"${result.atrib}":"${result.value}",
  </#list>
  "PolicyInformation": 
  {
  	<#list PolicyInformation as result>
  	"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
    </#list>
  },
  "ServiceRequestDetail": 
  {
  	<#list ServiceRequestDetail as result>
  	"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
    </#list>
  }
}