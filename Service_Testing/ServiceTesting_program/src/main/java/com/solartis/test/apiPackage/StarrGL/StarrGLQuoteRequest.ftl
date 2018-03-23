{
  "Policy": {
  "AdditionalInsuredList": [
      <#list AdditionalInsuredType as result>{
        "${result.atrib}":"${result.value}",
        "AdditionalInsuredDetail": [
          {
          <#list AdditionalInsuredDetail as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
          </#list>
          }
        ]
      }<#if result?is_last><#else>,</#if>
      </#list>
    ],
  "Location": {
      "LocationDetail": [
        {        
        "Classification": {
            "ClassificationDetail": [
              {
              <#list ClassificationDetail as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
              </#list>
              }
            ]
          },
        <#list LocationDetail as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
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