<#assign array=[]><#list numofai as x><#assign array=array+[x.value]></#list><#assign i=0>
{
  "Policy": {
  "AdditionalInsuredList": [
     <#list AdditionalInsuredType as result>{
        "${result.atrib}":"${result.value}",
        "AdditionalInsuredDetail": [
        <#list 1..array[i] as x>
          {
          <#list AdditionalInsuredDetail as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
          </#list>
          }<#if x?is_last><#else>,</#if>
          </#list>
        ]<#assign i=i+1>
      }<#if result?is_last><#else>,</#if>
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