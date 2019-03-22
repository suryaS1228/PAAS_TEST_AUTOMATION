{
<#list Common as result>"${result.atrib}":"${result.value}",
  </#list>
  "ServiceRequestDetail": {
      <#list ServiceRequestDetail as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
      </#list>
  },
  "Policy":{
     <#list Policy as result>"${result.atrib}":"${result.value}",
     </#list>
      "AdditionalNamedInsuredList": [
    {
    "AdditionalNamedInsuredDetail":{
         <#list AdditionalNamedInsuredDetail as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
         </#list>
         }
      }
   ], 
   "PrivatePassenger":[
         {
            "PrivatePassengerDetail":{
         <#list PrivatePassengerDetail as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
         </#list>
         }
      }
    ]
  },
}