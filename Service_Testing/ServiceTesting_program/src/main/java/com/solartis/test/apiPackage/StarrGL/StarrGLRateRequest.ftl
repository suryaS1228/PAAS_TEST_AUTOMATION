{
  "Policy": {
  "AdditionalInsuredList": [
      <#list additionalInsuredtype as result>{
        "${result.atrib}":"${result.value}",
        "AdditionalInsuredDetail": [
          {
          <#list AdditionalInsuredDetail as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
          </#list>
          }
        ]
      }
      </#list>
    ],
  "Location": {
      "LocationDetail": [
        {        
        "Classification": {
            "ClassificationDetail": [
              {
              <#list Classification as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
              </#list>
              }
            ]
          },
        <#list Location as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
        </#list>
        }
        ]
      },
  <#list Policy as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
  </#list>
   },
   "OwnerId": "30",
   "ServiceRequestDetail": {
   <#list ServiceRequestDetail as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
  </#list>
  },
  "EndClientUserUniqueSessionId": "Uniquesession"
}        
