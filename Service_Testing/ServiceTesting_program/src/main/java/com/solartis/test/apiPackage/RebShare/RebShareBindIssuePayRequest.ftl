{    
  "Policy":{
    <#list Policy as result>"${result.atrib}":"${result.value}",
    </#list>
    "OptionalForms":{
      <#list OptionalForms as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
      </#list>
    },
    "PrivatePassenger": [
      {
        "PrivatePassengerDetail": {
          <#list PrivatePassengerDetail as result>"${result.atrib}":"${result.value}",
          </#list>
          "AdditionalInsuredList": [
            {
              "AdditionalInsuredDetail": {
            <#list AdditionalInsuredDetail as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
            </#list>
              }
            }
          ],
          "OptionalForms":{
            <#list OptionalForms as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
            </#list>
          }
        }
      }
    ],    
    "TransactionInformation":{
      <#list TransactionInformation as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
      </#list>
    }
  },  
  <#list Common as result>"${result.atrib}":"${result.value}",
  </#list>
  "ServiceRequestDetail": {
    <#list ServiceRequestDetail as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
    </#list>
  }
}
     