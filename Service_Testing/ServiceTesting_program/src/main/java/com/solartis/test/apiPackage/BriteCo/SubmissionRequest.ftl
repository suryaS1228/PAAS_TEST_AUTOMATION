{
  "Policy": {
  <#list "Policy"?eval  as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
	          </#list>
  },
  "InsuredInformation": {
  <#list InsuredInformation?eval  as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
	          </#list>
  },
  "JewelerInformation": {
  <#list JewelerInformation?eval  as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
	          </#list>
  },
  "JewelList": [
    {
      "JewelDetail": {
      <#list JewelDetail?eval  as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
	          </#list>
      }
    }
  ],
  "OwnerId": "41",
  "ServiceRequestDetail": {
  <#list ServiceRequestDetail?eval  as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
	          </#list>
  },
  "EndClientUserUniqueSessionId": "Uniquesession"
}