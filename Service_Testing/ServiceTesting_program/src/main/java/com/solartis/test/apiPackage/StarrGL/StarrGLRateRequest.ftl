{
  "AdditionalInsuredList": [
      <#list additionalInsuredtype as result>{
        "${result.atrib}":"${result.value}",
        "AdditionalInsuredDetail": [
          {
          <#list additionalInsureddetail as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
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
              <#list classification as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
              </#list>
              }
            ]
          },
        <#list location as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
        </#list>
        }
        ]
      },
   "Policy": {
  <#list Policy as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
  </#list>
   },
   "OwnerId": "30",
   "ServiceRequestDetail": {
   <#list servicedetail as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
  </#list>
  },
  "EndClientUserUniqueSessionId": "Uniquesession"
}        
