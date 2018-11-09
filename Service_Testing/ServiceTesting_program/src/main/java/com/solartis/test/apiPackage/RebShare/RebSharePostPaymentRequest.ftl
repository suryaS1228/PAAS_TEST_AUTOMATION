{   
  <#list Common as result>"${result.atrib}":"${result.value}",
  </#list>
  "Policy":{
     <#list Policy as result>"${result.atrib}":"${result.value}",
     </#list>
          "TransactionInformation":{
         <#list PrivatePassengerDetail as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
         </#list>
         }
 },
  "ServiceRequestDetail": {
      <#list ServiceRequestDetail as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
      </#list>
  }
}